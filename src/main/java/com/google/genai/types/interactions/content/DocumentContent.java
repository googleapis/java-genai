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

package com.google.genai.types.interactions.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.DocumentMimeType;
import java.util.Optional;

/**
 * Document content for the Interactions API.
 *
 * <p>Represents document data (e.g., PDF, text files) that can be included in interaction inputs or
 * outputs. Documents can be provided either as base64-encoded data or as a URI.
 *
 * <p>Example usage with data:
 *
 * <pre>{@code
 * DocumentContent doc = DocumentContent.fromData(base64Data, "application/pdf");
 * }</pre>
 *
 * <p>Example usage with URI:
 *
 * <pre>{@code
 * DocumentContent doc = DocumentContent.fromUri("https://example.com/doc.pdf", "application/pdf");
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = DocumentContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("document")
public abstract class DocumentContent extends JsonSerializable implements Content {

  @JsonProperty("data")
  public abstract Optional<String> data();

  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /**
   * The MIME type of the document.
   *
   * <p>Supported values:
   *
   * <ul>
   *   <li>{@link DocumentMimeType.Known#APPLICATION_PDF} - PDF format
   * </ul>
   *
   * <p>This field accepts any MIME type string to support future document formats.
   */
  @JsonProperty("mime_type")
  public abstract Optional<DocumentMimeType> mimeType();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DocumentContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DocumentContent.Builder();
    }

    @JsonProperty("data")
    public abstract Builder data(String data);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder data(Optional<String> data);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearData() {
      return data(Optional.empty());
    }

    @JsonProperty("uri")
    public abstract Builder uri(String uri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder uri(Optional<String> uri);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUri() {
      return uri(Optional.empty());
    }

    @JsonProperty("mime_type")
    public abstract Builder mimeType(DocumentMimeType mimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mimeType(Optional<DocumentMimeType> mimeType);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMimeType() {
      return mimeType(Optional.empty());
    }

    public abstract DocumentContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static DocumentContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DocumentContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static DocumentContent fromData(String data, String mimeType) {
    return builder().data(data).mimeType(new DocumentMimeType(mimeType)).build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static DocumentContent fromUri(String uri, String mimeType) {
    return builder().uri(uri).mimeType(new DocumentMimeType(mimeType)).build();
  }
}
