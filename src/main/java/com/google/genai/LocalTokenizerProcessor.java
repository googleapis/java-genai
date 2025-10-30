/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Logger;

import com.google.genai.proto.SentencepieceModel.ModelProto;
import com.google.genai.proto.SentencepieceModel.ModelProto.SentencePiece;
import com.google.genai.proto.SentencepieceModel.NormalizerSpec;
import com.google.genai.proto.SentencepieceModel.TrainerSpec;

// IMPORTANT: Fetching tokenizer in unit test can pass locally but will fail in github workflow
// because github test env gets messed up and setup in a file affects other files.
// So the BPE algorithm integration test is excluded from mvn test.
// If you change the code in this file, remember to run the integration test:
// `mvn verify -Dtest=com.google.genai.LocalTokenizerProcessorIT`
final class LocalTokenizerProcessor {
  private static final Logger log = Logger.getLogger(LocalTokenizerProcessor.class.getName());

  private Set<String> userDefined = new HashSet<>();
  private Trie uTrie = new Trie();
  private Map<String, Integer> pieces = new HashMap<>();
  private Map<String, Integer> reserved = new HashMap<>();
  private Map<Byte, Token> byte2Token = new HashMap<>();
  private Map<Integer, Byte> idToByte = new HashMap<>();

  private ModelProto model;

  private int unkID;

  public LocalTokenizerProcessor(ModelProto model) {
    this.model = model;

    TrainerSpec tSpec = model.getTrainerSpec();
    if (tSpec.getModelType() != TrainerSpec.ModelType.BPE) {
      throw new IllegalArgumentException(
          String.format("Unsupported model type %s. Only BPE is supported.", tSpec.getModelType()));
    }

    NormalizerSpec nSpec = model.getNormalizerSpec();
    if (nSpec.getAddDummyPrefix() || nSpec.getRemoveExtraWhitespaces()) {
      throw new IllegalArgumentException(
          String.format("Unsupported model normalizer option: %s", nSpec));
    }

    if (!tSpec.hasUnkId()) {
      throw new IllegalArgumentException("Unknown ID is not set.");
    }
    this.unkID = tSpec.getUnkId();

    for (int i = 0; i < model.getPiecesCount(); i++) {
      SentencePiece p = model.getPieces(i);
      if (p.getType() == SentencePiece.Type.NORMAL
          || p.getType() == SentencePiece.Type.USER_DEFINED
          || p.getType() == SentencePiece.Type.UNUSED) {
        this.pieces.put(p.getPiece(), i);
      } else if (p.getType() == SentencePiece.Type.BYTE) {
        if (!tSpec.getByteFallback()) {
          throw new IllegalArgumentException(
              String.format(
                  "byte piece %s is found although byte fallback is not enabled.", p.getPiece()));
        }
        int bValue = convertHexValue(p.getPiece());
        if (bValue >= 0 && bValue < 256) {
          byte2Token.put(Byte.valueOf((byte) bValue), new Token(p.getPiece(), i));
          idToByte.put(i, Byte.valueOf((byte) bValue));
        }
      } else {
        this.reserved.put(p.getPiece(), i);
      }

      if (p.getType() == SentencePiece.Type.USER_DEFINED) {
        this.userDefined.add(p.getPiece());
        this.uTrie.insert(p.getPiece());
      }
    }
  }

  public List<Token> encode(String text) throws IllegalStateException {
    text = normalize(text);
    List<Symbol> symbols = new ArrayList<>(text.length());
    for (int i = 0; i < text.length(); ) {
      int len = uTrie.prefixLen(text.substring(i));
      if (len > 0) {
        symbols.add(
            new Symbol(text.substring(i, i + len), true, symbols.size() - 1, symbols.size() + 1));
        i += len;
      } else {
        symbols.add(
            new Symbol(text.substring(i, i + 1), false, symbols.size() - 1, symbols.size() + 1));
        i++;
      }
    }
    symbols.get(symbols.size() - 1).next = -1;

    PriorityQueue<MergeCandidate> pqSymbols = new PriorityQueue<>();
    for (int i = 1; i < symbols.size(); i++) {
      addNewCandidate(symbols, pqSymbols, i - 1, i);
    }

    // deadSymbolCount
    while (!pqSymbols.isEmpty()) {
      MergeCandidate mc = pqSymbols.poll();
      if (!isMergeCandidateValid(symbols, mc)) {
        continue;
      }

      Symbol leftSymbol = symbols.get(mc.left);
      Symbol rightSymbol = symbols.get(mc.right);

      String merged = maybeMerge(leftSymbol.text, rightSymbol.text);
      if (merged.length() == 0) {
        throw new IllegalStateException(
            String.format("error merge symbols, left %s, right %s", leftSymbol, rightSymbol));
      }

      // Do the merge and update prev, next pointers.
      leftSymbol.text = merged;
      leftSymbol.next = rightSymbol.next;
      rightSymbol.text = "";
      if (rightSymbol.next > 0) {
        symbols.get(rightSymbol.next).prev = mc.left;
      }

      addNewCandidate(symbols, pqSymbols, leftSymbol.prev, mc.left);
      addNewCandidate(symbols, pqSymbols, mc.left, rightSymbol.next);
    }

    List<Token> tokens = new ArrayList<>();
    for (int i = 0; i >= 0; i = symbols.get(i).next) {
      Symbol s = symbols.get(i);
      int id = this.symbolToID(s);
      if (id == this.unkID && this.model.getTrainerSpec().getByteFallback()) {
        byte[] bytes = s.text.getBytes();
        for (int j = 0; j < bytes.length; j++) {
          tokens.add(byte2Token.get(bytes[j]));
        }
      } else {
        tokens.add(new Token(s.text, id));
      }
    }
    return tokens;
  }

  /**
   * Decodes a list of token IDs back into a string.
   *
   * @param ids The list of token IDs.
   * @return The decoded string.
   */
  public String decodeIds(List<Integer> ids) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < ids.size()) {
      int nextNonByte = i;
      while (nextNonByte < ids.size() && isByteId(ids.get(nextNonByte))) {
        nextNonByte++;
      }
      int numBytes = nextNonByte - i;

      if (numBytes > 0) {
        byte[] buf = new byte[numBytes];
        for (int j = 0; j < numBytes; j++) {
          buf[j] = this.idToByte.get(ids.get(i + j));
        }
        // Java's String constructor handles UTF-8 decoding.
        // Invalid sequences will be replaced by the Unicode replacement character
        // U+FFFD.
        sb.append(new String(buf, StandardCharsets.UTF_8));
      }

      if (nextNonByte >= ids.size()) {
        break;
      }

      int currentId = ids.get(nextNonByte);
      SentencePiece pieceProto = this.model.getPieces(currentId);

      if (pieceProto.getType() == SentencePiece.Type.CONTROL) {
        // Don't emit anything for control IDs.
      } else if (currentId == this.unkID) {
        sb.append(this.model.getTrainerSpec().getUnkSurface());
      } else {
        String pieceText = pieceProto.getPiece();
        sb.append(replaceSentencePieceSeparator(pieceText));
      }
      i = nextNonByte + 1;
    }
    return sb.toString();
  }

  private String normalize(String text) {
    return text.replaceAll(" ", "▁");
  }

  private void addNewCandidate(
      List<Symbol> symbols, PriorityQueue<MergeCandidate> pq, int left, int right) {
    if (left == -1 || right == -1 || symbols.get(left).noMerge || symbols.get(right).noMerge) {
      return;
    }
    String merged = maybeMerge(symbols.get(left).text, symbols.get(right).text);
    if (merged.length() == 0) {
      return;
    }
    pq.add(
        new MergeCandidate(
            left,
            right,
            merged.length(),
            this.model.getPieces(this.pieces.get(merged)).getScore()));
    // We finalize symbol merge when the symbol is poped from priority queue.
  }

  private String maybeMerge(String a, String b) {
    String merged = a + b;
    if (this.pieces.containsKey(merged)) {
      return merged;
    }
    return "";
  }

  private boolean isMergeCandidateValid(List<Symbol> symbols, MergeCandidate symbol) {
    String left = symbols.get(symbol.left).text;
    String right = symbols.get(symbol.right).text;
    return left != "" && right != "" && left.length() + right.length() == symbol.length;
  }

  private int symbolToID(Symbol symbol) {
    if (this.pieces.containsKey(symbol.text)) return this.pieces.get(symbol.text);
    if (this.reserved.containsKey(symbol.text)) return this.reserved.get(symbol.text);

    return this.unkID;
  }

  private boolean isByteId(int id) {
    return this.model.getPieces(id).getType() == SentencePiece.Type.BYTE;
  }

  // Replaces the SentencePiece space character U+2581 (Lower One Eighth Block)
  // with a standard space ' '.
  private String replaceSentencePieceSeparator(String pieceText) {
    if (pieceText == null) {
      return "";
    }
    return pieceText.replace('\u2581', ' ');
  }

  private int convertHexValue(String bv) {
    if (bv == null || !bv.startsWith("<0x") || !bv.endsWith(">")) {
      return -1;
    }

    String hexPart = bv.substring(3, bv.length() - 1);

    if (hexPart.isEmpty()) {
      return -1; // Handle cases like "<0x>"
    }

    try {
      return Integer.parseInt(hexPart, 16);
    } catch (NumberFormatException e) {
      return -1;
    }
  }
}

class MergeCandidate implements Comparable<MergeCandidate> {
  int left, right;
  int length;
  double score;

  MergeCandidate(int left, int right, int length, double score) {
    this.left = left;
    this.right = right;
    this.length = length;
    this.score = score;
  }

  @Override
  public int compareTo(MergeCandidate other) {
    // Primary comparison: score (descending - higher score has higher priority)
    int scoreCompare = Double.compare(other.score, this.score);
    if (scoreCompare != 0) {
      return scoreCompare;
    }
    // Secondary comparison: left index (ascending - lower index has higher
    // priority)
    return Integer.compare(this.left, other.left);
  }
}

class Symbol {
  String text;
  int prev, next;
  boolean noMerge;

  public Symbol(String text, boolean noMerge, int prev, int next) {
    this.text = text;
    this.noMerge = noMerge;
    this.prev = prev;
    this.next = next;
  }

  @Override
  public String toString() {
    return "Symbol{"
        + "text='"
        + text
        + '\''
        + ", prev="
        + prev
        + ", next="
        + next
        + ", noMerge="
        + noMerge
        + '}';
  }
}

class TrieNode {
  public Map<Character, TrieNode> childreNode;
  public int freq;

  public TrieNode() {
    childreNode = new HashMap<>();
    freq = 0;
  }
}

class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    int index, i;
    char ch;
    TrieNode node = root;
    for (i = 0; i < word.length(); i++) {
      ch = word.charAt(i);
      index = ch - 'a';
      if (node.childreNode.get(ch) == null) {
        node.childreNode.put(ch, new TrieNode());
      }
      node = node.childreNode.get(ch);
      if (i == word.length() - 1) node.freq++;
    }
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    int index, i;
    char ch;
    TrieNode node = root;
    for (i = 0; i < word.length(); i++) {
      ch = word.charAt(i);
      node = node.childreNode.get(ch);
      if (node == null) return false;
      if (i == word.length() - 1 && node.freq > 0) return true;
    }
    return false;
  }

  public int prefixLen(String word) {
    int index, i;
    char ch;
    TrieNode node = root;
    int result = 0;
    for (i = 0; i < word.length(); i++) {
      ch = word.charAt(i);
      node = node.childreNode.get(ch);
      if (node == null) break;
      if (node.freq > 0) result = i + 1;
    }
    return result;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    int index, i;
    char ch;
    TrieNode node = root;
    for (i = 0; i < prefix.length(); i++) {
      ch = prefix.charAt(i);
      index = ch - 'a';
      node = node.childreNode.get(ch);
      if (node == null) return false;
      if (i == prefix.length() - 1) return true;
    }
    return false;
  }
}

final class Token {
  private final String text;
  private final int id;

  public Token(String text, int id) {
    this.text = text;
    this.id = id;
  }

  public String text() {
    return text;
  }

  public int id() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Token token = (Token) o;
    return id == token.id && Objects.equals(text, token.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, id);
  }

  @Override
  public String toString() {
    return "Token{" + "text='" + text + '\'' + ", id=" + id + '}';
  }
}
