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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.genai.gaos.models.environments.EnvironmentFile;
import com.google.genai.gaos.models.environments.GetEnvironmentFilesResponse;
import com.google.genai.gaos.utils.Response;
import com.google.genai.gaos.utils.Utils;
import com.google.genai.gaos.utils.transport.HttpResponse;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.util.Optional;

public class UploadEnvironmentFileResponse implements Response {
    private final String contentType;
    private final int statusCode;
    private final HttpResponse<InputStream> rawResponse;
    private final EnvironmentFile file;
    private final GetEnvironmentFilesResponse files;

    @JsonCreator
    public UploadEnvironmentFileResponse(
            int statusCode,
            @Nonnull String contentType,
            @Nonnull HttpResponse<InputStream> rawResponse,
            @Nullable EnvironmentFile file,
            @Nullable GetEnvironmentFilesResponse files) {
        this.statusCode = statusCode;
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        this.file = file;
        this.files = files;
    }

    @Override
    public String contentType() {
        return contentType;
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public HttpResponse<InputStream> rawResponse() {
        return rawResponse;
    }

    public Optional<EnvironmentFile> file() {
        return Optional.ofNullable(file);
    }

    public Optional<GetEnvironmentFilesResponse> files() {
        return Optional.ofNullable(files);
    }

    @Override
    public String toString() {
        return Utils.toString(UploadEnvironmentFileResponse.class,
                "statusCode", statusCode,
                "contentType", contentType,
                "file", file,
                "files", files);
    }
}
