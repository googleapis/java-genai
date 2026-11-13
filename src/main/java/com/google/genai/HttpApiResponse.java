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

import com.google.genai.errors.ApiException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/** Wraps a real HTTP response to expose the methods needed by the GenAI SDK. */
final class HttpApiResponse extends ApiResponse {

  private static final Logger logger = Logger.getLogger(HttpApiResponse.class.getName());
  private static final String AGENT_MESSAGE_HEADER = "Agent-Message";
  private static final Pattern AGENT_MESSAGE_TOKEN_PATTERN =
      Pattern.compile("^[A-Za-z*][A-Za-z0-9!#$%&'*+.^_`|~:/-]*$");
  private static final Set<String> SEEN_AGENT_MESSAGES = ConcurrentHashMap.newKeySet();

  private final Response response;

  /** Constructs a HttpApiResponse instance with the response. */
  public HttpApiResponse(Response response) {
    this.response = response;
  }

  static void clearSeenAgentMessages() {
    SEEN_AGENT_MESSAGES.clear();
  }

  private static List<String> splitUnquoted(String text, char delimiter) {
    List<String> parts = new ArrayList<>();
    int start = 0;
    boolean inQuotes = false;
    boolean isDisplayString = false;
    boolean escaped = false;

    for (int i = 0; i < text.length(); i++) {
      char ch = text.charAt(i);
      if (inQuotes) {
        if (!isDisplayString) {
          if (escaped) {
            escaped = false;
            continue;
          }
          if (ch == '\\') {
            escaped = true;
            continue;
          }
        }
        if (ch == '"') {
          inQuotes = false;
          isDisplayString = false;
        }
      } else {
        if (ch == '"') {
          inQuotes = true;
          isDisplayString = i > 0 && text.charAt(i - 1) == '%';
        } else if (ch == delimiter) {
          parts.add(text.substring(start, i));
          start = i + 1;
        }
      }
    }

    if (inQuotes) {
      return null;
    }
    parts.add(text.substring(start));
    return parts;
  }

  private static String unescapeQuotedString(String inner) {
    StringBuilder out = new StringBuilder(inner.length());
    boolean escaped = false;
    for (int i = 0; i < inner.length(); i++) {
      char ch = inner.charAt(i);
      if (escaped) {
        if (ch != '"' && ch != '\\') {
          return null;
        }
        out.append(ch);
        escaped = false;
      } else if (ch == '\\') {
        escaped = true;
      } else if (ch == '"') {
        return null;
      } else {
        out.append(ch);
      }
    }
    if (escaped) {
      return null;
    }
    return out.toString();
  }

  private static String percentDecodeUtf8(String input) {
    byte[] rawBytes = input.getBytes(StandardCharsets.UTF_8);
    byte[] out = new byte[rawBytes.length];
    int outLen = 0;
    for (int i = 0; i < rawBytes.length; i++) {
      if (rawBytes[i] == '%' && i + 2 < rawBytes.length) {
        int hi = Character.digit((char) (rawBytes[i + 1] & 0xFF), 16);
        int lo = Character.digit((char) (rawBytes[i + 2] & 0xFF), 16);
        if (hi != -1 && lo != -1) {
          out[outLen++] = (byte) ((hi << 4) | lo);
          i += 2;
          continue;
        }
      }
      out[outLen++] = rawBytes[i];
    }
    return new String(out, 0, outLen, StandardCharsets.UTF_8);
  }

  private static String parseStructuredAgentMessageItem(String rawItem) {
    String item = rawItem.trim();
    if (item.isEmpty()) {
      return null;
    }

    List<String> parts = splitUnquoted(item, ';');
    if (parts == null || parts.size() < 2) {
      return null;
    }

    String code = parts.get(0).trim();
    if (!AGENT_MESSAGE_TOKEN_PATTERN.matcher(code).matches()) {
      return null;
    }

    String msgValue = null;
    for (int i = 1; i < parts.size(); i++) {
      String param = parts.get(i).trim();
      if (param.isEmpty()) {
        return null;
      }
      int eqIdx = param.indexOf('=');
      if (eqIdx < 0) {
        if (!AGENT_MESSAGE_TOKEN_PATTERN.matcher(param).matches()) {
          return null;
        }
        continue;
      }

      String key = param.substring(0, eqIdx).trim();
      String val = param.substring(eqIdx + 1).trim();
      if (!AGENT_MESSAGE_TOKEN_PATTERN.matcher(key).matches() || val.isEmpty()) {
        return null;
      }

      if ("msg".equals(key)) {
        if (val.startsWith("%\"") && val.endsWith("\"") && val.length() >= 3) {
          String inner = val.substring(2, val.length() - 1);
          if (inner.indexOf('"') >= 0) {
            return null;
          }
          msgValue = percentDecodeUtf8(inner);
        } else if (val.startsWith("\"") && val.endsWith("\"") && val.length() >= 2) {
          msgValue = unescapeQuotedString(val.substring(1, val.length() - 1));
          if (msgValue == null) {
            return null;
          }
        } else {
          return null;
        }
      }
    }

    return (msgValue != null && !msgValue.isEmpty()) ? msgValue : null;
  }

  /** Parses an Agent-Message response header into warning strings. */
  static List<String> parseAgentMessageHeader(String headerValue) {
    if (headerValue == null) {
      return Collections.emptyList();
    }
    String stripped = headerValue.trim();
    if (stripped.isEmpty()) {
      return Collections.emptyList();
    }

    // Plain-text fallback for headers that aren't RFC 9651 structured items.
    if (!stripped.contains(";") && !stripped.contains("=") && stripped.contains(" ")) {
      if (!stripped.startsWith("\"") && !stripped.endsWith("\"")) {
        return Collections.singletonList(stripped);
      }
    }

    List<String> items = splitUnquoted(stripped, ',');
    if (items == null) {
      return Collections.emptyList();
    }

    List<String> messages = new ArrayList<>();
    for (String rawItem : items) {
      String msg = parseStructuredAgentMessageItem(rawItem);
      if (msg != null && !msg.isEmpty()) {
        messages.add(msg);
      }
    }
    return messages;
  }

  /**
   * Logs each message in the Agent-Message response headers as a warning, once per process.
   *
   * <p>Shared by GenerateContent responses (via the constructor) and Interactions (GAOS)
   * responses (via {@code Client.GenAiGaosHttpClient}), so the two APIs share one dedup set.
   */
  static void warnFromAgentMessageHeaders(Headers headers) {
    try {
      for (String value : headers.values(AGENT_MESSAGE_HEADER)) {
        for (String msg : parseAgentMessageHeader(value)) {
          if (SEEN_AGENT_MESSAGES.add(msg)) {
            logger.warning("[Gemini API] " + msg);
          }
        }
      }
    } catch (RuntimeException ignored) {
      // Never fail a response due to malformed warning headers.
    }
  }

  /** Returns the ResponseBody from the response. */
  @Override
  public ResponseBody getBody() {
    ApiException.throwFromResponse(response);
    return response.body();
  }

  /** Returns all of the headers from the response. */
  @Override
  public Headers getHeaders() {
    return response.headers();
  }

  /** Closes the Http response. */
  @Override
  public void close() {
    response.close();
  }
}
