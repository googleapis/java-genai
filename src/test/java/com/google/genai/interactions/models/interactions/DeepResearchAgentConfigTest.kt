/*
* Copyright 2025 Google LLC
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

// File generated from our OpenAPI spec by Stainless.

package com.google.genai.interactions.models.interactions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.google.genai.interactions.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeepResearchAgentConfigTest {

    @Test
    fun create() {
        val deepResearchAgentConfig =
            DeepResearchAgentConfig.builder()
                .collaborativePlanning(true)
                .thinkingSummaries(DeepResearchAgentConfig.ThinkingSummaries.AUTO)
                .visualization(DeepResearchAgentConfig.Visualization.OFF)
                .build()

        assertThat(deepResearchAgentConfig.collaborativePlanning()).contains(true)
        assertThat(deepResearchAgentConfig.thinkingSummaries())
            .contains(DeepResearchAgentConfig.ThinkingSummaries.AUTO)
        assertThat(deepResearchAgentConfig.visualization())
            .contains(DeepResearchAgentConfig.Visualization.OFF)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deepResearchAgentConfig =
            DeepResearchAgentConfig.builder()
                .collaborativePlanning(true)
                .thinkingSummaries(DeepResearchAgentConfig.ThinkingSummaries.AUTO)
                .visualization(DeepResearchAgentConfig.Visualization.OFF)
                .build()

        val roundtrippedDeepResearchAgentConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deepResearchAgentConfig),
                jacksonTypeRef<DeepResearchAgentConfig>(),
            )

        assertThat(roundtrippedDeepResearchAgentConfig).isEqualTo(deepResearchAgentConfig)
    }
}
