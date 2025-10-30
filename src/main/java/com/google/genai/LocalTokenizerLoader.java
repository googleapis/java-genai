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

import com.google.genai.errors.GenAiIOException;
import com.google.genai.proto.SentencepieceModel.ModelProto;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.util.logging.Logger;

/**
 * Loader for local tokenizers.
 *
 * <p>This class handles loading SentencePiece models for tokenization, with local caching to avoid
 * repeated downloads.
 */
final class LocalTokenizerLoader {
  private static final Map<String, String> GEMINI_MODELS_TO_TOKENIZER_NAMES;
  private static final Logger logger = Logger.getLogger(LocalTokenizerLoader.class.getName());

  static {
    Map<String, String> modelMap = new HashMap<>();
    modelMap.put("gemini-1.0-pro-001", "gemma2");
    modelMap.put("gemini-1.0-pro-002", "gemma2");
    modelMap.put("gemini-1.0-pro", "gemma2");
    modelMap.put("gemini-1.5-flash-001", "gemma2");
    modelMap.put("gemini-1.5-flash-002", "gemma2");
    modelMap.put("gemini-1.5-flash", "gemma2");
    modelMap.put("gemini-1.5-pro-001", "gemma2");
    modelMap.put("gemini-1.5-pro-002", "gemma2");
    modelMap.put("gemini-1.5-pro", "gemma2");
    modelMap.put("gemini-2.0-flash-001", "gemma3");
    modelMap.put("gemini-2.0-flash-lite-001", "gemma3");
    modelMap.put("gemini-2.0-flash-lite", "gemma3");
    modelMap.put("gemini-2.0-flash", "gemma3");
    modelMap.put("gemini-2.5-flash-lite-preview-06-17", "gemma3");
    modelMap.put("gemini-2.5-flash-lite", "gemma3");
    modelMap.put("gemini-2.5-flash-preview-04-17", "gemma3");
    modelMap.put("gemini-2.5-flash-preview-05-20", "gemma3");
    modelMap.put("gemini-2.5-flash", "gemma3");
    modelMap.put("gemini-2.5-pro-exp-03-25", "gemma3");
    modelMap.put("gemini-2.5-pro-preview-05-06", "gemma3");
    modelMap.put("gemini-2.5-pro-preview-06-05", "gemma3");
    modelMap.put("gemini-2.5-pro", "gemma3");
    modelMap.put("gemini-live-2.5-flash", "gemma3");
    GEMINI_MODELS_TO_TOKENIZER_NAMES = Collections.unmodifiableMap(modelMap);
  }

  static final class TokenizerConfig {
    private final String modelUrl;
    private final String modelHash;

    TokenizerConfig(String modelUrl, String modelHash) {
      this.modelUrl = modelUrl;
      this.modelHash = modelHash;
    }

    String modelUrl() {
      return modelUrl;
    }

    String modelHash() {
      return modelHash;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      TokenizerConfig that = (TokenizerConfig) o;
      return Objects.equals(modelUrl, that.modelUrl) && Objects.equals(modelHash, that.modelHash);
    }

    @Override
    public int hashCode() {
      return Objects.hash(modelUrl, modelHash);
    }

    @Override
    public String toString() {
      return "TokenizerConfig[modelUrl=" + modelUrl + ", modelHash=" + modelHash + "]";
    }
  }

  private static Map<String, TokenizerConfig> TOKENIZERS;

  static {
    Map<String, TokenizerConfig> map = new HashMap<>();
    map.put(
        "gemma2",
        new TokenizerConfig(
            "https://raw.githubusercontent.com/google/gemma_pytorch/33b652c465537c6158f9a472ea5700e5e770ad3f/tokenizer/tokenizer.model",
            "61a7b147390c64585d6c3543dd6fc636906c9af3865a5548f27f31aee1d4c8e2"));
    map.put(
        "gemma3",
        new TokenizerConfig(
            "https://raw.githubusercontent.com/google/gemma_pytorch/014acb7ac4563a5f77c76d7ff98f31b568c16508/tokenizer/gemma3_cleaned_262144_v2.spiece.model",
            "1299c11d7cf632ef3b4e11937501358ada021bbdf7c47638d13c0ee982f2e79c"));
    TOKENIZERS = Collections.unmodifiableMap(map);
  }

  private static final Map<String, ModelProto> modelProtoCache = new ConcurrentHashMap<>();
  private static final Map<String, LocalTokenizerProcessor> localTokenizerProcessorCache =
      new ConcurrentHashMap<>();
  private static OkHttpClient httpClient = new OkHttpClient();

  private LocalTokenizerLoader() {}

  /** Gets the tokenizer name for the given model name. */
  public static String getTokenizerName(String modelName) {
    if (GEMINI_MODELS_TO_TOKENIZER_NAMES.containsKey(modelName)) {
      return GEMINI_MODELS_TO_TOKENIZER_NAMES.get(modelName);
    }
    throw new IllegalArgumentException(
        "Model "
            + modelName
            + " is not supported. Supported models: "
            + String.join(", ", GEMINI_MODELS_TO_TOKENIZER_NAMES.keySet()));
  }

  /** Loads model proto from the given tokenizer name. */
  public static ModelProto loadModelProto(String tokenizerName) {
    return modelProtoCache.computeIfAbsent(
        tokenizerName,
        key -> {
          try {
            byte[] protoBytes = loadModelProtoBytes(key);
            return ModelProto.parseFrom(protoBytes);
          } catch (InvalidProtocolBufferException e) {
            throw new IllegalStateException("Failed to parse model proto", e);
          } catch (IOException e) {
            throw new GenAiIOException("Failed to load tokenizer model", e);
          }
        });
  }

  /** Loads sentencepiece tokenizer from the given tokenizer name. */
  public static LocalTokenizerProcessor getSentencePiece(String tokenizerName) {
    return localTokenizerProcessorCache.computeIfAbsent(
        tokenizerName,
        key -> {
          try {
            LocalTokenizerProcessor processor = new LocalTokenizerProcessor(loadModelProto(key));
            return processor;
          } catch (IllegalArgumentException e) {
            throw e;
          }
        });
  }

  private static byte[] loadModelProtoBytes(String tokenizerName) throws IOException {
    if (!TOKENIZERS.containsKey(tokenizerName)) {
      throw new IllegalArgumentException(
          "Tokenizer "
              + tokenizerName
              + " is not supported. Supported tokenizers: "
              + String.join(", ", TOKENIZERS.keySet()));
    }
    TokenizerConfig config = TOKENIZERS.get(tokenizerName);
    return load(config.modelUrl(), config.modelHash());
  }

  private static byte[] load(String fileUrl, String expectedHash) throws IOException {
    Path modelDir = Paths.get(System.getProperty("java.io.tmpdir"), "vertexai_tokenizer_model");
    String filename = sha1(fileUrl);
    Path modelPath = modelDir.resolve(filename);

    Optional<byte[]> modelData = maybeLoadFromCache(modelPath, expectedHash);
    if (modelData.isPresent()) {
      return modelData.get();
    }

    byte[] downloadedData = loadFromUrl(fileUrl, expectedHash);
    maybeSaveToCache(modelDir, modelPath, downloadedData);
    logger.info(
        "Downloaded model from "
            + fileUrl
            + " to "
            + modelPath
            + " with hash "
            + sha256(downloadedData));
    return downloadedData;
  }

  private static Optional<byte[]> maybeLoadFromCache(Path filePath, String expectedHash)
      throws IOException {
    if (!Files.exists(filePath)) {
      return Optional.empty();
    }
    byte[] content = Files.readAllBytes(filePath);
    if (isValidModel(content, expectedHash)) {
      return Optional.of(content);
    }

    try {
      Files.deleteIfExists(filePath);
    } catch (IOException e) {
      // Don't raise if we cannot remove file.
    }
    return Optional.empty();
  }

  private static void maybeSaveToCache(Path cacheDir, Path cachePath, byte[] content) {
    try {
      Files.createDirectories(cacheDir);
      Path tmpPath = cacheDir.resolve("." + UUID.randomUUID() + ".tmp");
      Files.write(tmpPath, content);
      Files.move(tmpPath, cachePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      // Don't raise if we cannot write file.
    }
  }

  private static byte[] loadFromUrl(String fileUrl, String expectedHash) throws IOException {
    Request request = new Request.Builder().url(fileUrl).build();
    try (Response response = httpClient.newCall(request).execute()) {
      if (response == null) {
        throw new GenAiIOException("HTTP request failed: response is null");
      }
      if (!response.isSuccessful()) {
        throw new GenAiIOException("Failed to download tokenizer model: HTTP " + response.code());
      }
      ResponseBody body = response.body();
      if (body == null) {
        throw new GenAiIOException("Failed to download tokenizer model: Response body is null");
      }
      byte[] content = body.bytes();

      if (!isValidModel(content, expectedHash)) {
        String actualHash = sha256(content);
        throw new GenAiIOException(
            "Downloaded model file is corrupted. Expected hash "
                + expectedHash
                + ". Got file hash "
                + actualHash
                + ".");
      }
      return content;
    }
  }

  private static boolean isValidModel(byte[] modelData, String expectedHash) {
    if (expectedHash == null || expectedHash.isEmpty()) {
      throw new IllegalArgumentException("expected_hash is required");
    }
    return sha256(modelData).equals(expectedHash);
  }

  private static String sha256(byte[] data) {
    return hash(data, "SHA-256");
  }

  private static String sha1(String input) {
    return hash(input.getBytes(), "SHA-1");
  }

  private static String hash(byte[] data, String algorithm) {
    try {
      MessageDigest digest = MessageDigest.getInstance(algorithm);
      byte[] hash = digest.digest(data);
      return bytesToHex(hash);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException(e); // Should not happen
    }
  }

  private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
