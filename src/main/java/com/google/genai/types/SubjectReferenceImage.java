// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Class that represents a Subject reference image.
 *
 * <p>This encapsulates a subject reference image provided by the user, and additionally optional
 * config parameters for the subject reference image.
 *
 * <p>A raw reference image can also be provided as a destination for the subject to be applied to.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_SubjectReferenceImage.Builder.class)
public abstract class SubjectReferenceImage extends JsonSerializable {
  /** The reference image for the editing operation. */
  public abstract Optional<Image> getReferenceImage();

  /** The id of the reference image. */
  public abstract Optional<Integer> getReferenceId();

  /** The type of the reference image. */
  public abstract Optional<String> getReferenceType();

  /** Configuration for the subject reference image. */
  public abstract Optional<SubjectReferenceConfig> getConfig();

  /** Instantiates a builder for SubjectReferenceImage. */
  public static Builder builder() {
    return new AutoValue_SubjectReferenceImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SubjectReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("referenceImage")
    public abstract Builder setReferenceImage(Image referenceImage);

    @JsonProperty("referenceId")
    public abstract Builder setReferenceId(Integer referenceId);

    @JsonProperty("referenceType")
    public abstract Builder setReferenceType(String referenceType);

    @JsonProperty("config")
    public abstract Builder setConfig(SubjectReferenceConfig config);

    public abstract SubjectReferenceImage build();
  }

  /** Deserializes a JSON string to a SubjectReferenceImage object. */
  public static SubjectReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SubjectReferenceImage.class);
  }
}
