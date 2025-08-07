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

package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.CreateTuningJobConfig;
import com.google.genai.types.JobState;
import com.google.genai.types.ListTuningJobsConfig;
import com.google.genai.types.TuningDataset;
import com.google.genai.types.TuningJob;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class TuningsTest {
  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testPager(boolean vertexAI) {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(vertexAI, "tests/tunings/list/test_pager." + suffix + ".json");
    ListTuningJobsConfig config = ListTuningJobsConfig.builder().pageSize(2).build();

    // Act
    Pager<TuningJob> pager = client.tunings.list(config);

    // Assert
    assertNotNull(pager);
    assertEquals(2, pager.pageSize());
    for (TuningJob job : pager) {
      assertTrue(job.name().isPresent());
    }

    IndexOutOfBoundsException exception =
        assertThrows(IndexOutOfBoundsException.class, () -> pager.nextPage());
    assertEquals("No more page in the pager.", exception.getMessage());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testHelperProperties(boolean vertexAI) {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/tunings/get/test_helper_properties." + suffix + ".json");

    String vertexJob = "projects/801452371447/locations/us-central1/tuningJobs/4303478340632707072";
    String mldevJob = "tunedModels/testdatasetexamples-model-j0fpgpaksvri";
    String jobName = vertexAI ? vertexJob : mldevJob;

    // Act
    TuningJob job = client.tunings.get(jobName, null);

    // Assert
    assertNotNull(job);
    assertTrue(job.state().get().knownEnum() == JobState.Known.JOB_STATE_SUCCEEDED);
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testTuneUntilSuccess(boolean vertexAI) {
    if (!vertexAI) { // MLDev test removed, tune not supported anymore
      return;
    }

    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/tunings/end_to_end/test_tune_until_success." + suffix + ".json");

    TuningDataset tuningDataset =
        TuningDataset.builder()
            .gcsUri(
                "gs://cloud-samples-data/ai-platform/generative_ai/gemini-2_0/text/sft_train_data.jsonl")
            .build();
    CreateTuningJobConfig tuningConfig = CreateTuningJobConfig.builder().epochCount(1).build();
    TuningJob job = client.tunings.tune("gemini-2.0-flash-001", tuningDataset, tuningConfig);

    // Act
    TuningJob currentJob = job;
    JobState.Known state = job.state().get().knownEnum();

    // Needed to go through the running + pending tuning job states.
    while (state != JobState.Known.JOB_STATE_SUCCEEDED
        && state != JobState.Known.JOB_STATE_FAILED
        && state != JobState.Known.JOB_STATE_CANCELLED) {
      currentJob = client.tunings.get(currentJob.name().get(), null);
      state = currentJob.state().get().knownEnum();
    }

    // Assert
    assertNotNull(currentJob);
    assertTrue(currentJob.state().get().knownEnum() == JobState.Known.JOB_STATE_SUCCEEDED);
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testContinuousTuning(boolean vertexAI) {
    if (!vertexAI) { // MLDev test removed, tune not supported anymore
      return;
    }

    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/tunings/end_to_end/test_continuous_tuning." + suffix + ".json");

    TuningDataset tuningDataset =
        TuningDataset.builder()
            .gcsUri(
                "gs://cloud-samples-data/ai-platform/generative_ai/gemini-2_0/text/sft_train_data.jsonl")
            .build();
    CreateTuningJobConfig tuningConfig = CreateTuningJobConfig.builder().epochCount(1).build();
    TuningJob job = client.tunings.tune("gemini-2.5-flash", tuningDataset, tuningConfig);

    // Act
    TuningJob currentJob = job;
    JobState.Known state = job.state().get().knownEnum();

    // Needed to go through the running + pending tuning job states.
    while (state != JobState.Known.JOB_STATE_SUCCEEDED
        && state != JobState.Known.JOB_STATE_FAILED
        && state != JobState.Known.JOB_STATE_CANCELLED) {
      currentJob = client.tunings.get(currentJob.name().get(), null);
      state = currentJob.state().get().knownEnum();
    }

    // Assert
    assertNotNull(currentJob);
    assertTrue(currentJob.state().get().knownEnum() == JobState.Known.JOB_STATE_SUCCEEDED);

    // Arrange
    CreateTuningJobConfig continuousTuningConfig =
        CreateTuningJobConfig.builder()
            .tunedModelDisplayName("continuous tuning job")
            .epochCount(1)
            .build();
    TuningJob continuousJob =
        client.tunings.tune(
            currentJob.tunedModel().get().model().get(), tuningDataset, continuousTuningConfig);

    // Act
    TuningJob currentContinuousJob = continuousJob;
    JobState.Known continuousState = continuousJob.state().get().knownEnum();

    // Needed to go through the running + pending tuning job states.
    while (continuousState != JobState.Known.JOB_STATE_SUCCEEDED
        && continuousState != JobState.Known.JOB_STATE_FAILED
        && continuousState != JobState.Known.JOB_STATE_CANCELLED) {
      currentContinuousJob = client.tunings.get(currentContinuousJob.name().get(), null);
      continuousState = currentContinuousJob.state().get().knownEnum();
    }

    // Assert
    assertNotNull(currentContinuousJob);
    assertTrue(
        currentContinuousJob.state().get().knownEnum() == JobState.Known.JOB_STATE_SUCCEEDED);
  }
}
