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

import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

/** TokenProvider that wraps GoogleCredentials. */
public class GoogleCredentialsTokenProvider implements TokenProvider {
  private final GoogleCredentials credentials;
  private static Method refreshMethod;

  static {
    try {
      // Use reflection to avoid static dependency on refreshIfExpired in Boq.
      refreshMethod = GoogleCredentials.class.getMethod("refreshIfExpired");
    } catch (NoSuchMethodException e) {
      // Ignore, GoogleCredentials should have it since it extends OAuth2Credentials.
    }
  }

  public GoogleCredentialsTokenProvider(GoogleCredentials credentials) {
    this.credentials = credentials;
  }

  @Override
  public String getToken() throws IOException {
    if (refreshMethod != null) {
      try {
        refreshMethod.invoke(credentials);
      } catch (Exception e) {
        if (e.getCause() instanceof IOException) {
          throw (IOException) e.getCause();
        }
        throw new IOException("Failed to refresh credentials via reflection", e);
      }
    }
    if (credentials.getAccessToken() == null) {
      throw new IOException("No access token available.");
    }
    return credentials.getAccessToken().getTokenValue();
  }

  @Override
  public Optional<String> getQuotaProjectId() {
    return Optional.ofNullable(credentials.getQuotaProjectId());
  }
}
