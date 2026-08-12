/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.gaos.models.operations;

import com.google.genai.gaos.utils.Utils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public class UploadEnvironmentFileRequest {
    private final String environment;
    private final String path;
    private final File file;
    private final byte[] bytes;
    private final InputStream stream;
    private final Long sizeBytes;
    private final String mimeType;
    private final Boolean overwrite;
    private final Boolean extract;
    private final String apiVersion;

    private UploadEnvironmentFileRequest(Builder builder) {
        this.environment = Utils.checkNotNull(builder.environment, "environment");
        this.path = Utils.checkNotNull(builder.path, "path");
        this.file = builder.file;
        this.bytes = builder.bytes;
        this.stream = builder.stream;
        this.sizeBytes = builder.sizeBytes;
        this.mimeType = builder.mimeType;
        this.overwrite = builder.overwrite;
        this.extract = builder.extract;
        this.apiVersion = builder.apiVersion;
    }

    public String environment() { return environment; }
    public String path() { return path; }
    public Optional<File> file() { return Optional.ofNullable(file); }
    public Optional<byte[]> bytes() { return Optional.ofNullable(bytes); }
    public Optional<InputStream> stream() { return Optional.ofNullable(stream); }
    public Optional<Long> sizeBytes() { return Optional.ofNullable(sizeBytes); }
    public Optional<String> mimeType() { return Optional.ofNullable(mimeType); }
    public Optional<Boolean> overwrite() { return Optional.ofNullable(overwrite); }
    public Optional<Boolean> extract() { return Optional.ofNullable(extract); }
    public Optional<String> apiVersion() { return Optional.ofNullable(apiVersion); }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String environment;
        private String path;
        private File file;
        private byte[] bytes;
        private InputStream stream;
        private Long sizeBytes;
        private String mimeType;
        private Boolean overwrite;
        private Boolean extract;
        private String apiVersion;

        public Builder environment(@Nonnull String environment) {
            this.environment = environment;
            return this;
        }

        public Builder path(@Nonnull String path) {
            this.path = path;
            return this;
        }

        public Builder file(@Nonnull File file) {
            this.file = file;
            if (this.sizeBytes == null) {
                this.sizeBytes = file.length();
            }
            return this;
        }

        public Builder bytes(@Nonnull byte[] bytes) {
            this.bytes = bytes;
            if (this.sizeBytes == null) {
                this.sizeBytes = (long) bytes.length;
            }
            return this;
        }

        public Builder stream(@Nonnull InputStream stream, long sizeBytes) {
            this.stream = stream;
            this.sizeBytes = sizeBytes;
            return this;
        }

        public Builder sizeBytes(@Nullable Long sizeBytes) {
            this.sizeBytes = sizeBytes;
            return this;
        }

        public Builder mimeType(@Nullable String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder overwrite(@Nullable Boolean overwrite) {
            this.overwrite = overwrite;
            return this;
        }

        public Builder extract(@Nullable Boolean extract) {
            this.extract = extract;
            return this;
        }

        public Builder apiVersion(@Nullable String apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        public UploadEnvironmentFileRequest build() {
            return new UploadEnvironmentFileRequest(this);
        }
    }
}
