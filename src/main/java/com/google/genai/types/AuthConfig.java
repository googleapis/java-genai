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

// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Auth configuration to run the extension. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = AuthConfig.Builder.class)
public abstract class AuthConfig extends JsonSerializable {
  /** Config for API key auth. */
  @JsonProperty("apiKeyConfig")
  public abstract Optional<ApiKeyConfig> apiKeyConfig();

  /** Type of auth scheme. */
  @JsonProperty("authType")
  public abstract Optional<AuthType> authType();

  /** Config for Google Service Account auth. */
  @JsonProperty("googleServiceAccountConfig")
  public abstract Optional<AuthConfigGoogleServiceAccountConfig> googleServiceAccountConfig();

  /** Config for HTTP Basic auth. */
  @JsonProperty("httpBasicAuthConfig")
  public abstract Optional<AuthConfigHttpBasicAuthConfig> httpBasicAuthConfig();

  /** Config for user oauth. */
  @JsonProperty("oauthConfig")
  public abstract Optional<AuthConfigOauthConfig> oauthConfig();

  /** Config for user OIDC auth. */
  @JsonProperty("oidcConfig")
  public abstract Optional<AuthConfigOidcConfig> oidcConfig();

  /** Instantiates a builder for AuthConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AuthConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AuthConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AuthConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AuthConfig.Builder();
    }

    /**
     * Setter for apiKeyConfig.
     *
     * <p>apiKeyConfig: Config for API key auth.
     */
    @JsonProperty("apiKeyConfig")
    public abstract Builder apiKeyConfig(ApiKeyConfig apiKeyConfig);

    /**
     * Setter for apiKeyConfig builder.
     *
     * <p>apiKeyConfig: Config for API key auth.
     */
    @CanIgnoreReturnValue
    public Builder apiKeyConfig(ApiKeyConfig.Builder apiKeyConfigBuilder) {
      return apiKeyConfig(apiKeyConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder apiKeyConfig(Optional<ApiKeyConfig> apiKeyConfig);

    /** Clears the value of apiKeyConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearApiKeyConfig() {
      return apiKeyConfig(Optional.empty());
    }

    /**
     * Setter for authType.
     *
     * <p>authType: Type of auth scheme.
     */
    @JsonProperty("authType")
    public abstract Builder authType(AuthType authType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder authType(Optional<AuthType> authType);

    /** Clears the value of authType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAuthType() {
      return authType(Optional.empty());
    }

    /**
     * Setter for authType given a known enum.
     *
     * <p>authType: Type of auth scheme.
     */
    @CanIgnoreReturnValue
    public Builder authType(AuthType.Known knownType) {
      return authType(new AuthType(knownType));
    }

    /**
     * Setter for authType given a string.
     *
     * <p>authType: Type of auth scheme.
     */
    @CanIgnoreReturnValue
    public Builder authType(String authType) {
      return authType(new AuthType(authType));
    }

    /**
     * Setter for googleServiceAccountConfig.
     *
     * <p>googleServiceAccountConfig: Config for Google Service Account auth.
     */
    @JsonProperty("googleServiceAccountConfig")
    public abstract Builder googleServiceAccountConfig(
        AuthConfigGoogleServiceAccountConfig googleServiceAccountConfig);

    /**
     * Setter for googleServiceAccountConfig builder.
     *
     * <p>googleServiceAccountConfig: Config for Google Service Account auth.
     */
    @CanIgnoreReturnValue
    public Builder googleServiceAccountConfig(
        AuthConfigGoogleServiceAccountConfig.Builder googleServiceAccountConfigBuilder) {
      return googleServiceAccountConfig(googleServiceAccountConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder googleServiceAccountConfig(
        Optional<AuthConfigGoogleServiceAccountConfig> googleServiceAccountConfig);

    /** Clears the value of googleServiceAccountConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGoogleServiceAccountConfig() {
      return googleServiceAccountConfig(Optional.empty());
    }

    /**
     * Setter for httpBasicAuthConfig.
     *
     * <p>httpBasicAuthConfig: Config for HTTP Basic auth.
     */
    @JsonProperty("httpBasicAuthConfig")
    public abstract Builder httpBasicAuthConfig(AuthConfigHttpBasicAuthConfig httpBasicAuthConfig);

    /**
     * Setter for httpBasicAuthConfig builder.
     *
     * <p>httpBasicAuthConfig: Config for HTTP Basic auth.
     */
    @CanIgnoreReturnValue
    public Builder httpBasicAuthConfig(
        AuthConfigHttpBasicAuthConfig.Builder httpBasicAuthConfigBuilder) {
      return httpBasicAuthConfig(httpBasicAuthConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder httpBasicAuthConfig(
        Optional<AuthConfigHttpBasicAuthConfig> httpBasicAuthConfig);

    /** Clears the value of httpBasicAuthConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHttpBasicAuthConfig() {
      return httpBasicAuthConfig(Optional.empty());
    }

    /**
     * Setter for oauthConfig.
     *
     * <p>oauthConfig: Config for user oauth.
     */
    @JsonProperty("oauthConfig")
    public abstract Builder oauthConfig(AuthConfigOauthConfig oauthConfig);

    /**
     * Setter for oauthConfig builder.
     *
     * <p>oauthConfig: Config for user oauth.
     */
    @CanIgnoreReturnValue
    public Builder oauthConfig(AuthConfigOauthConfig.Builder oauthConfigBuilder) {
      return oauthConfig(oauthConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder oauthConfig(Optional<AuthConfigOauthConfig> oauthConfig);

    /** Clears the value of oauthConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOauthConfig() {
      return oauthConfig(Optional.empty());
    }

    /**
     * Setter for oidcConfig.
     *
     * <p>oidcConfig: Config for user OIDC auth.
     */
    @JsonProperty("oidcConfig")
    public abstract Builder oidcConfig(AuthConfigOidcConfig oidcConfig);

    /**
     * Setter for oidcConfig builder.
     *
     * <p>oidcConfig: Config for user OIDC auth.
     */
    @CanIgnoreReturnValue
    public Builder oidcConfig(AuthConfigOidcConfig.Builder oidcConfigBuilder) {
      return oidcConfig(oidcConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder oidcConfig(Optional<AuthConfigOidcConfig> oidcConfig);

    /** Clears the value of oidcConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOidcConfig() {
      return oidcConfig(Optional.empty());
    }

    public abstract AuthConfig build();
  }

  /** Deserializes a JSON string to a AuthConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static AuthConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AuthConfig.class);
  }
}
