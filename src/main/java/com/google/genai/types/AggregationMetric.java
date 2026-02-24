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
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Ascii;
import java.util.Objects;

/** Aggregation metric. This enum is not supported in Gemini API. */
public class AggregationMetric {

  /** Enum representing the known values for AggregationMetric. */
  public enum Known {
    /** Unspecified aggregation metric. */
    AGGREGATION_METRIC_UNSPECIFIED,

    /** Average aggregation metric. Not supported for Pairwise metric. */
    AVERAGE,

    /** Mode aggregation metric. */
    MODE,

    /** Standard deviation aggregation metric. Not supported for pairwise metric. */
    STANDARD_DEVIATION,

    /** Variance aggregation metric. Not supported for pairwise metric. */
    VARIANCE,

    /** Minimum aggregation metric. Not supported for pairwise metric. */
    MINIMUM,

    /** Maximum aggregation metric. Not supported for pairwise metric. */
    MAXIMUM,

    /** Median aggregation metric. Not supported for pairwise metric. */
    MEDIAN,

    /** 90th percentile aggregation metric. Not supported for pairwise metric. */
    PERCENTILE_P90,

    /** 95th percentile aggregation metric. Not supported for pairwise metric. */
    PERCENTILE_P95,

    /** 99th percentile aggregation metric. Not supported for pairwise metric. */
    PERCENTILE_P99
  }

  private Known aggregationMetricEnum;
  private final String value;

  @JsonCreator
  public AggregationMetric(String value) {
    this.value = value;
    for (Known aggregationMetricEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(aggregationMetricEnum.toString(), value)) {
        this.aggregationMetricEnum = aggregationMetricEnum;
        break;
      }
    }
    if (this.aggregationMetricEnum == null) {
      this.aggregationMetricEnum = Known.AGGREGATION_METRIC_UNSPECIFIED;
    }
  }

  public AggregationMetric(Known knownValue) {
    this.aggregationMetricEnum = knownValue;
    this.value = knownValue.toString();
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  @JsonValue
  public String toString() {
    return this.value;
  }

  @ExcludeFromGeneratedCoverageReport
  @SuppressWarnings("PatternMatchingInstanceof")
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }

    if (!(o instanceof AggregationMetric)) {
      return false;
    }

    AggregationMetric other = (AggregationMetric) o;

    if (this.aggregationMetricEnum != Known.AGGREGATION_METRIC_UNSPECIFIED
        && other.aggregationMetricEnum != Known.AGGREGATION_METRIC_UNSPECIFIED) {
      return this.aggregationMetricEnum == other.aggregationMetricEnum;
    } else if (this.aggregationMetricEnum == Known.AGGREGATION_METRIC_UNSPECIFIED
        && other.aggregationMetricEnum == Known.AGGREGATION_METRIC_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.aggregationMetricEnum != Known.AGGREGATION_METRIC_UNSPECIFIED) {
      return this.aggregationMetricEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.aggregationMetricEnum;
  }
}
