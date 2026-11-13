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

/**
 * A datatype containing media that is part of a `FunctionResponse` message.
 *
 * <p>A `FunctionResponsePart` consists of data which has an associated datatype. A
 * `FunctionResponsePart` can only contain one of the accepted types in `FunctionResponsePart.data`.
 * A `FunctionResponsePart` must have a fixed IANA MIME type identifying the type and subtype of the
 * media if the `inline_data` field is filled with raw bytes.
 */
@AutoValue
@JsonDeserialize(builder = FunctionResponsePart.Builder.class)
public abstract class FunctionResponsePart extends JsonSerializable {
  /** Inline media bytes. */
  @JsonProperty("inlineData")
  public abstract Optional<FunctionResponseBlob> inlineData();

  /** URI based data. */
  @JsonProperty("fileData")
  public abstract Optional<FunctionResponseFileData> fileData();

  /**
   * Optional. How the model processes this part's media for understanding. Only meaningful for
   * video parts (`inline_data` or `file_data` with video mime). Non-video parts ignore this field.
   * This field is not supported in Vertex AI.
   */
  @JsonProperty("mediaProcessing")
  public abstract Optional<MediaProcessing> mediaProcessing();

  /** Media resolution for the input media. */
  @JsonProperty("mediaResolution")
  public abstract Optional<PartMediaResolution> mediaResolution();

  /**
   * Video metadata. The metadata should only be specified while the video data is presented in
   * inline_data or file_data.
   */
  @JsonProperty("videoMetadata")
  public abstract Optional<VideoMetadata> videoMetadata();

  /**
   * Document metadata. The metadata should only be specified while the document data is presented
   * in inline_data or file_data.
   */
  @JsonProperty("documentMetadata")
  public abstract Optional<DocumentMetadata> documentMetadata();

  /**
   * Spreadsheet metadata. The metadata should only be specified while the spreadsheet data is
   * presented in inline_data or file_data.
   */
  @JsonProperty("spreadsheetMetadata")
  public abstract Optional<SpreadsheetMetadata> spreadsheetMetadata();

  /**
   * Audio metadata. The metadata should only be specified while the audio data is presented in
   * inline_data or file_data.
   */
  @JsonProperty("audioMetadata")
  public abstract Optional<AudioMetadata> audioMetadata();

  /** Instantiates a builder for FunctionResponsePart. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FunctionResponsePart.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionResponsePart. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FunctionResponsePart.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FunctionResponsePart.Builder();
    }

    /**
     * Setter for inlineData.
     *
     * <p>inlineData: Inline media bytes.
     */
    @JsonProperty("inlineData")
    public abstract Builder inlineData(FunctionResponseBlob inlineData);

    /**
     * Setter for inlineData builder.
     *
     * <p>inlineData: Inline media bytes.
     */
    @CanIgnoreReturnValue
    public Builder inlineData(FunctionResponseBlob.Builder inlineDataBuilder) {
      return inlineData(inlineDataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder inlineData(Optional<FunctionResponseBlob> inlineData);

    /** Clears the value of inlineData field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInlineData() {
      return inlineData(Optional.empty());
    }

    /**
     * Setter for fileData.
     *
     * <p>fileData: URI based data.
     */
    @JsonProperty("fileData")
    public abstract Builder fileData(FunctionResponseFileData fileData);

    /**
     * Setter for fileData builder.
     *
     * <p>fileData: URI based data.
     */
    @CanIgnoreReturnValue
    public Builder fileData(FunctionResponseFileData.Builder fileDataBuilder) {
      return fileData(fileDataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileData(Optional<FunctionResponseFileData> fileData);

    /** Clears the value of fileData field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileData() {
      return fileData(Optional.empty());
    }

    /**
     * Setter for mediaProcessing.
     *
     * <p>mediaProcessing: Optional. How the model processes this part's media for understanding.
     * Only meaningful for video parts (`inline_data` or `file_data` with video mime). Non-video
     * parts ignore this field. This field is not supported in Vertex AI.
     */
    @JsonProperty("mediaProcessing")
    public abstract Builder mediaProcessing(MediaProcessing mediaProcessing);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mediaProcessing(Optional<MediaProcessing> mediaProcessing);

    /** Clears the value of mediaProcessing field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMediaProcessing() {
      return mediaProcessing(Optional.empty());
    }

    /**
     * Setter for mediaProcessing given a known enum.
     *
     * <p>mediaProcessing: Optional. How the model processes this part's media for understanding.
     * Only meaningful for video parts (`inline_data` or `file_data` with video mime). Non-video
     * parts ignore this field. This field is not supported in Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder mediaProcessing(MediaProcessing.Known knownType) {
      return mediaProcessing(new MediaProcessing(knownType));
    }

    /**
     * Setter for mediaProcessing given a string.
     *
     * <p>mediaProcessing: Optional. How the model processes this part's media for understanding.
     * Only meaningful for video parts (`inline_data` or `file_data` with video mime). Non-video
     * parts ignore this field. This field is not supported in Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder mediaProcessing(String mediaProcessing) {
      return mediaProcessing(new MediaProcessing(mediaProcessing));
    }

    /**
     * Setter for mediaResolution.
     *
     * <p>mediaResolution: Media resolution for the input media.
     */
    @JsonProperty("mediaResolution")
    public abstract Builder mediaResolution(PartMediaResolution mediaResolution);

    /**
     * Setter for mediaResolution builder.
     *
     * <p>mediaResolution: Media resolution for the input media.
     */
    @CanIgnoreReturnValue
    public Builder mediaResolution(PartMediaResolution.Builder mediaResolutionBuilder) {
      return mediaResolution(mediaResolutionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mediaResolution(Optional<PartMediaResolution> mediaResolution);

    /** Clears the value of mediaResolution field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMediaResolution() {
      return mediaResolution(Optional.empty());
    }

    /**
     * Setter for videoMetadata.
     *
     * <p>videoMetadata: Video metadata. The metadata should only be specified while the video data
     * is presented in inline_data or file_data.
     */
    @JsonProperty("videoMetadata")
    public abstract Builder videoMetadata(VideoMetadata videoMetadata);

    /**
     * Setter for videoMetadata builder.
     *
     * <p>videoMetadata: Video metadata. The metadata should only be specified while the video data
     * is presented in inline_data or file_data.
     */
    @CanIgnoreReturnValue
    public Builder videoMetadata(VideoMetadata.Builder videoMetadataBuilder) {
      return videoMetadata(videoMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder videoMetadata(Optional<VideoMetadata> videoMetadata);

    /** Clears the value of videoMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVideoMetadata() {
      return videoMetadata(Optional.empty());
    }

    /**
     * Setter for documentMetadata.
     *
     * <p>documentMetadata: Document metadata. The metadata should only be specified while the
     * document data is presented in inline_data or file_data.
     */
    @JsonProperty("documentMetadata")
    public abstract Builder documentMetadata(DocumentMetadata documentMetadata);

    /**
     * Setter for documentMetadata builder.
     *
     * <p>documentMetadata: Document metadata. The metadata should only be specified while the
     * document data is presented in inline_data or file_data.
     */
    @CanIgnoreReturnValue
    public Builder documentMetadata(DocumentMetadata.Builder documentMetadataBuilder) {
      return documentMetadata(documentMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder documentMetadata(Optional<DocumentMetadata> documentMetadata);

    /** Clears the value of documentMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDocumentMetadata() {
      return documentMetadata(Optional.empty());
    }

    /**
     * Setter for spreadsheetMetadata.
     *
     * <p>spreadsheetMetadata: Spreadsheet metadata. The metadata should only be specified while the
     * spreadsheet data is presented in inline_data or file_data.
     */
    @JsonProperty("spreadsheetMetadata")
    public abstract Builder spreadsheetMetadata(SpreadsheetMetadata spreadsheetMetadata);

    /**
     * Setter for spreadsheetMetadata builder.
     *
     * <p>spreadsheetMetadata: Spreadsheet metadata. The metadata should only be specified while the
     * spreadsheet data is presented in inline_data or file_data.
     */
    @CanIgnoreReturnValue
    public Builder spreadsheetMetadata(SpreadsheetMetadata.Builder spreadsheetMetadataBuilder) {
      return spreadsheetMetadata(spreadsheetMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder spreadsheetMetadata(Optional<SpreadsheetMetadata> spreadsheetMetadata);

    /** Clears the value of spreadsheetMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSpreadsheetMetadata() {
      return spreadsheetMetadata(Optional.empty());
    }

    /**
     * Setter for audioMetadata.
     *
     * <p>audioMetadata: Audio metadata. The metadata should only be specified while the audio data
     * is presented in inline_data or file_data.
     */
    @JsonProperty("audioMetadata")
    public abstract Builder audioMetadata(AudioMetadata audioMetadata);

    /**
     * Setter for audioMetadata builder.
     *
     * <p>audioMetadata: Audio metadata. The metadata should only be specified while the audio data
     * is presented in inline_data or file_data.
     */
    @CanIgnoreReturnValue
    public Builder audioMetadata(AudioMetadata.Builder audioMetadataBuilder) {
      return audioMetadata(audioMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder audioMetadata(Optional<AudioMetadata> audioMetadata);

    /** Clears the value of audioMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAudioMetadata() {
      return audioMetadata(Optional.empty());
    }

    public abstract FunctionResponsePart build();
  }

  /** Deserializes a JSON string to a FunctionResponsePart object. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResponsePart fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionResponsePart.class);
  }

  /**
   * Constructs a FunctionResponsePart with FunctionResponseBlob from a byte array and MIME type.
   */
  public static FunctionResponsePart fromBytes(byte[] bytes, String mimeType) {
    return builder()
        .inlineData(FunctionResponseBlob.builder().data(bytes).mimeType(mimeType))
        .build();
  }

  /**
   * Constructs a FunctionResponsePart with FunctionResponseFileData from a file URI and MIME type.
   */
  public static FunctionResponsePart fromUri(String fileUri, String mimeType) {
    return builder()
        .fileData(FunctionResponseFileData.builder().fileUri(fileUri).mimeType(mimeType))
        .build();
  }
}
