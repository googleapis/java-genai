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

package com.google.genai.gaos.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@SuppressWarnings("all")
public final class HttpHeaders {
    private final Map<String, List<String>> headers;

    private HttpHeaders(Map<String, List<String>> headers) {
        // Case-insensitive map to match JDK HttpHeaders behavior if needed,
        // although TreeMap with case-insensitive comparator is better.
        Map<String, List<String>> copy = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            copy.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        this.headers = Collections.unmodifiableMap(copy);
    }

    public static HttpHeaders of(Map<String, List<String>> headers) {
        return new HttpHeaders(headers);
    }

    public Map<String, List<String>> map() {
        return headers;
    }

    public Optional<String> firstValue(String name) {
        List<String> values = headers.get(name);
        if (values != null && !values.isEmpty()) {
            return Optional.of(values.get(0));
        }
        return Optional.empty();
    }

    public List<String> allValues(String name) {
        List<String> values = headers.get(name);
        if (values != null) {
            return values;
        }
        return Collections.emptyList();
    }
}
