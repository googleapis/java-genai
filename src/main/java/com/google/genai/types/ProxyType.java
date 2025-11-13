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

/** Specifies the type of proxy to use for network connections. */
public class ProxyType {

  /** Enum representing the known values for ProxyType. */
  public enum Known {
    /** Represents an HTTP proxy. */
    HTTP,

    /** Represents a SOCKS proxy. */
    SOCKS,

    /**
     * Direct connection without any proxy. This enforces a proxy-free connection, even if one is
     * configured at the system level.
     */
    DIRECT,

    PROXY_TYPE_UNSPECIFIED
  }

  private Known proxyTypeEnum;
  private final String value;

  @JsonCreator
  public ProxyType(String value) {
    this.value = value;
    for (Known proxyTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(proxyTypeEnum.toString(), value)) {
        this.proxyTypeEnum = proxyTypeEnum;
        break;
      }
    }
    if (this.proxyTypeEnum == null) {
      this.proxyTypeEnum = Known.PROXY_TYPE_UNSPECIFIED;
    }
  }

  public ProxyType(Known knownValue) {
    this.proxyTypeEnum = knownValue;
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

    if (!(o instanceof ProxyType)) {
      return false;
    }

    ProxyType other = (ProxyType) o;

    if (this.proxyTypeEnum != Known.PROXY_TYPE_UNSPECIFIED
        && other.proxyTypeEnum != Known.PROXY_TYPE_UNSPECIFIED) {
      return this.proxyTypeEnum == other.proxyTypeEnum;
    } else if (this.proxyTypeEnum == Known.PROXY_TYPE_UNSPECIFIED
        && other.proxyTypeEnum == Known.PROXY_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.proxyTypeEnum != Known.PROXY_TYPE_UNSPECIFIED) {
      return this.proxyTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.proxyTypeEnum;
  }
}
