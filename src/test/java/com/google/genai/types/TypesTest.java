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

package com.google.genai.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/** Tests for model builders, getters, and open enums in com.google.genai.types. */
public class TypesTest {

  @Test
  public void testUsageMetadata() {
    UsageMetadata metadata =
        UsageMetadata.builder()
            .promptTokenCount(100)
            .cachedContentTokenCount(20)
            .responseTokenCount(50)
            .toolUsePromptTokenCount(10)
            .thoughtsTokenCount(15)
            .totalTokenCount(195)
            .build();

    assertEquals(100, metadata.promptTokenCount().orElse(null));
    assertEquals(20, metadata.cachedContentTokenCount().orElse(null));
    assertEquals(50, metadata.responseTokenCount().orElse(null));
    assertEquals(10, metadata.toolUsePromptTokenCount().orElse(null));
    assertEquals(15, metadata.thoughtsTokenCount().orElse(null));
    assertEquals(195, metadata.totalTokenCount().orElse(null));
  }

  @Test
  public void testGenerateContentResponseUsageMetadata() {
    GenerateContentResponseUsageMetadata metadata =
        GenerateContentResponseUsageMetadata.builder()
            .promptTokenCount(100)
            .candidatesTokenCount(50)
            .totalTokenCount(150)
            .cachedContentTokenCount(20)
            .build();

    assertEquals(100, metadata.promptTokenCount().orElse(null));
    assertEquals(50, metadata.candidatesTokenCount().orElse(null));
    assertEquals(150, metadata.totalTokenCount().orElse(null));
    assertEquals(20, metadata.cachedContentTokenCount().orElse(null));
  }

  @Test
  public void testGenerationConfig() {
    GenerationConfig config =
        GenerationConfig.builder()
            .temperature(0.7f)
            .topP(0.95f)
            .topK(40.0f)
            .candidateCount(1)
            .maxOutputTokens(1024)
            .stopSequences(Arrays.asList("END", "STOP"))
            .responseMimeType("application/json")
            .presencePenalty(0.5f)
            .frequencyPenalty(0.5f)
            .seed(42)
            .responseLogprobs(true)
            .logprobs(5)
            .build();

    assertEquals(0.7f, config.temperature().orElse(null));
    assertEquals(0.95f, config.topP().orElse(null));
    assertEquals(40.0f, config.topK().orElse(null));
    assertEquals(1, config.candidateCount().orElse(null));
    assertEquals(1024, config.maxOutputTokens().orElse(null));
    assertEquals(Arrays.asList("END", "STOP"), config.stopSequences().orElse(null));
    assertEquals("application/json", config.responseMimeType().orElse(null));
    assertEquals(0.5f, config.presencePenalty().orElse(null));
    assertEquals(0.5f, config.frequencyPenalty().orElse(null));
    assertEquals(42, config.seed().orElse(null));
    assertEquals(true, config.responseLogprobs().orElse(null));
    assertEquals(5, config.logprobs().orElse(null));
  }

  @Test
  public void testGenerateContentConfig() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .temperature(0.8f)
            .topP(0.9f)
            .topK(20.0f)
            .candidateCount(2)
            .maxOutputTokens(512)
            .responseMimeType("text/plain")
            .seed(100)
            .build();

    assertEquals(0.8f, config.temperature().orElse(null));
    assertEquals(0.9f, config.topP().orElse(null));
    assertEquals(20.0f, config.topK().orElse(null));
    assertEquals(2, config.candidateCount().orElse(null));
    assertEquals(512, config.maxOutputTokens().orElse(null));
    assertEquals("text/plain", config.responseMimeType().orElse(null));
    assertEquals(100, config.seed().orElse(null));
  }

  @Test
  public void testGroundingMetadata() {
    GroundingMetadata metadata =
        GroundingMetadata.builder()
            .webSearchQueries(Arrays.asList("query1", "query2"))
            .searchEntryPoint(SearchEntryPoint.builder().renderedContent("content").build())
            .build();

    assertEquals(Arrays.asList("query1", "query2"), metadata.webSearchQueries().orElse(null));
    assertTrue(metadata.searchEntryPoint().isPresent());
    assertEquals("content", metadata.searchEntryPoint().get().renderedContent().orElse(null));
  }

  @Test
  public void testSafetyRatingAndSetting() {
    SafetyRating rating =
        SafetyRating.builder()
            .category(HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH)
            .probability(HarmProbability.Known.NEGLIGIBLE)
            .blocked(false)
            .probabilityScore(0.1f)
            .severity(HarmSeverity.Known.HARM_SEVERITY_LOW)
            .severityScore(0.2f)
            .build();

    assertEquals(HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH, rating.category().get().knownEnum());
    assertEquals(HarmProbability.Known.NEGLIGIBLE, rating.probability().get().knownEnum());
    assertEquals(false, rating.blocked().orElse(null));
    assertEquals(0.1f, rating.probabilityScore().orElse(null));
    assertEquals(HarmSeverity.Known.HARM_SEVERITY_LOW, rating.severity().get().knownEnum());
    assertEquals(0.2f, rating.severityScore().orElse(null));

    SafetySetting setting =
        SafetySetting.builder()
            .category(HarmCategory.Known.HARM_CATEGORY_DANGEROUS_CONTENT)
            .threshold(HarmBlockThreshold.Known.BLOCK_LOW_AND_ABOVE)
            .method(HarmBlockMethod.Known.SEVERITY)
            .build();

    assertEquals(HarmCategory.Known.HARM_CATEGORY_DANGEROUS_CONTENT, setting.category().get().knownEnum());
    assertEquals(HarmBlockThreshold.Known.BLOCK_LOW_AND_ABOVE, setting.threshold().get().knownEnum());
    assertEquals(HarmBlockMethod.Known.SEVERITY, setting.method().get().knownEnum());
  }

  @Test
  public void testLiveConnectConfigAndSetup() {
    LiveConnectConfig connectConfig =
        LiveConnectConfig.builder()
            .temperature(0.5f)
            .topP(0.9f)
            .topK(20.0f)
            .maxOutputTokens(512)
            .seed(123)
            .enableAffectiveDialog(true)
            .explicitVadSignal(true)
            .systemInstruction(Content.builder().parts(Part.fromText("Be helpful")).build())
            .build();

    assertEquals(0.5f, connectConfig.temperature().orElse(null));
    assertEquals(0.9f, connectConfig.topP().orElse(null));
    assertEquals(20.0f, connectConfig.topK().orElse(null));
    assertEquals(512, connectConfig.maxOutputTokens().orElse(null));
    assertEquals(123, connectConfig.seed().orElse(null));
    assertEquals(true, connectConfig.enableAffectiveDialog().orElse(null));
    assertEquals(true, connectConfig.explicitVadSignal().orElse(null));
    assertTrue(connectConfig.systemInstruction().isPresent());
  }

  @Test
  public void testTuningJobAndConfig() {
    TuningJob job =
        TuningJob.builder()
            .name("projects/123/locations/us-central1/tuningJobs/456")
            .baseModel("gemini-2.5-flash")
            .tunedModelDisplayName("my-tuned-model")
            .state("JOB_STATE_RUNNING")
            .experiment("exp-1")
            .build();

    assertEquals("projects/123/locations/us-central1/tuningJobs/456", job.name().orElse(null));
    assertEquals("gemini-2.5-flash", job.baseModel().orElse(null));
    assertEquals("my-tuned-model", job.tunedModelDisplayName().orElse(null));
    assertEquals("JOB_STATE_RUNNING", job.state().get().toString());
    assertEquals("exp-1", job.experiment().orElse(null));

    CreateTuningJobConfig config =
        CreateTuningJobConfig.builder()
            .tunedModelDisplayName("my-tuned-model")
            .description("A test tuning job")
            .epochCount(5)
            .batchSize(4)
            .learningRate(0.001f)
            .build();

    assertEquals("my-tuned-model", config.tunedModelDisplayName().orElse(null));
    assertEquals("A test tuning job", config.description().orElse(null));
    assertEquals(5, config.epochCount().orElse(null));
    assertEquals(4, config.batchSize().orElse(null));
    assertEquals(0.001f, config.learningRate().orElse(null));
  }

  @Test
  public void testStatsAndMetrics() {
    DatasetStats stats =
        DatasetStats.builder()
            .totalBillableCharacterCount(5000L)
            .tuningDatasetExampleCount(100L)
            .totalTuningCharacterCount(6000L)
            .build();

    assertEquals(5000L, stats.totalBillableCharacterCount().orElse(null));
    assertEquals(100L, stats.tuningDatasetExampleCount().orElse(null));
    assertEquals(6000L, stats.totalTuningCharacterCount().orElse(null));

    PreferenceOptimizationDataStats prefStats =
        PreferenceOptimizationDataStats.builder()
            .totalBillableTokenCount(4000L)
            .tuningDatasetExampleCount(80L)
            .build();

    assertEquals(4000L, prefStats.totalBillableTokenCount().orElse(null));
    assertEquals(80L, prefStats.tuningDatasetExampleCount().orElse(null));
  }

  @Test
  public void testComputerUseAndImageFormats() {
    ComputerUse cu = ComputerUse.builder().environment(Environment.Known.ENVIRONMENT_BROWSER).build();
    assertEquals(Environment.Known.ENVIRONMENT_BROWSER, cu.environment().get().knownEnum());

    ImageResponseFormat format = ImageResponseFormat.builder().mimeType("image/png").build();
    assertEquals("image/png", format.mimeType().orElse(null));

    GenerateImagesConfig imagesConfig =
        GenerateImagesConfig.builder()
            .numberOfImages(2)
            .outputMimeType("image/jpeg")
            .aspectRatio("16:9")
            .build();

    assertEquals(2, imagesConfig.numberOfImages().orElse(null));
    assertEquals("image/jpeg", imagesConfig.outputMimeType().orElse(null));
    assertEquals("16:9", imagesConfig.aspectRatio().orElse(null));
  }

  @Test
  public void testOpenEnumWrappers() {
    // AspectRatio
    assertNotNull(AspectRatio.Known.values());
    AspectRatio.Known arVal = AspectRatio.Known.values()[0];
    AspectRatio ar1 = new AspectRatio(arVal);
    AspectRatio ar2 = new AspectRatio(arVal.toString());
    AspectRatio ar3 = new AspectRatio("unknown_value");
    assertEquals(arVal, ar1.knownEnum());

    // ServiceTier
    assertNotNull(ServiceTier.Known.values());
    ServiceTier.Known stVal = ServiceTier.Known.values()[0];
    ServiceTier st1 = new ServiceTier(stVal);
    ServiceTier st2 = new ServiceTier(stVal.toString());
    ServiceTier st3 = new ServiceTier("unknown_value");
    assertEquals(stVal, st1.knownEnum());

    // TurnCompleteReason
    assertNotNull(TurnCompleteReason.Known.values());
    TurnCompleteReason.Known tcrVal = TurnCompleteReason.Known.values()[0];
    TurnCompleteReason tcr1 = new TurnCompleteReason(tcrVal);
    TurnCompleteReason tcr2 = new TurnCompleteReason(tcrVal.toString());
    TurnCompleteReason tcr3 = new TurnCompleteReason("unknown_value");
    assertEquals(tcrVal, tcr1.knownEnum());

    // Behavior
    assertNotNull(Behavior.Known.values());
    Behavior.Known behVal = Behavior.Known.values()[0];
    Behavior b1 = new Behavior(behVal);
    Behavior b2 = new Behavior(behVal.toString());
    Behavior b3 = new Behavior("unknown_value");
    assertEquals(behVal, b1.knownEnum());

    // DynamicRetrievalConfigMode
    assertNotNull(DynamicRetrievalConfigMode.Known.values());
    DynamicRetrievalConfigMode.Known drcmVal = DynamicRetrievalConfigMode.Known.values()[0];
    DynamicRetrievalConfigMode drcm = new DynamicRetrievalConfigMode(drcmVal);
    assertEquals(drcmVal, drcm.knownEnum());

    // EndSensitivity
    assertNotNull(EndSensitivity.Known.values());
    EndSensitivity.Known esVal = EndSensitivity.Known.values()[0];
    EndSensitivity es = new EndSensitivity(esVal);
    assertEquals(esVal, es.knownEnum());

    // ModelRoutingPreference
    assertNotNull(ModelRoutingPreference.Known.values());
    ModelRoutingPreference.Known mrpVal = ModelRoutingPreference.Known.values()[0];
    ModelRoutingPreference mrp = new ModelRoutingPreference(mrpVal);
    assertEquals(mrpVal, mrp.knownEnum());

    // ActivityHandling
    assertNotNull(ActivityHandling.Known.values());
    ActivityHandling.Known ahVal = ActivityHandling.Known.values()[0];
    ActivityHandling ah = new ActivityHandling(ahVal);
    assertEquals(ahVal, ah.knownEnum());

    // TuningSpeed
    assertNotNull(TuningSpeed.Known.values());
    TuningSpeed.Known tsVal = TuningSpeed.Known.values()[0];
    TuningSpeed ts = new TuningSpeed(tsVal);
    assertEquals(tsVal, ts.knownEnum());

    // MatchOperation
    assertNotNull(MatchOperation.Known.values());
    MatchOperation.Known moVal = MatchOperation.Known.values()[0];
    MatchOperation mo = new MatchOperation(moVal);
    assertEquals(moVal, mo.knownEnum());

    // TuningJobState
    assertNotNull(TuningJobState.Known.values());
    TuningJobState.Known tjsVal = TuningJobState.Known.values()[0];
    TuningJobState tjs = new TuningJobState(tjsVal);
    assertEquals(tjsVal, tjs.knownEnum());

    // Delivery
    assertNotNull(Delivery.Known.values());
    Delivery.Known delVal = Delivery.Known.values()[0];
    Delivery del = new Delivery(delVal);
    assertEquals(delVal, del.knownEnum());

    // RubricContentType
    assertNotNull(RubricContentType.Known.values());
    RubricContentType.Known rctVal = RubricContentType.Known.values()[0];
    RubricContentType rct = new RubricContentType(rctVal);
    assertEquals(rctVal, rct.knownEnum());

    // ProminentPeople
    assertNotNull(ProminentPeople.Known.values());
    ProminentPeople.Known ppVal = ProminentPeople.Known.values()[0];
    ProminentPeople pp = new ProminentPeople(ppVal);
    assertEquals(ppVal, pp.knownEnum());

    // PairwiseChoice
    assertNotNull(PairwiseChoice.Known.values());
    PairwiseChoice.Known pcVal = PairwiseChoice.Known.values()[0];
    PairwiseChoice pc = new PairwiseChoice(pcVal);
    assertEquals(pcVal, pc.knownEnum());

    // ImageSize
    assertNotNull(ImageSize.Known.values());
    ImageSize.Known isVal = ImageSize.Known.values()[0];
    ImageSize is = new ImageSize(isVal);
    assertEquals(isVal, is.knownEnum());

    // AggregationMetric
    assertNotNull(AggregationMetric.Known.values());
    AggregationMetric.Known amVal = AggregationMetric.Known.values()[0];
    AggregationMetric am = new AggregationMetric(amVal);
    assertEquals(amVal, am.knownEnum());

    // SafetyPolicy
    assertNotNull(SafetyPolicy.Known.values());
    SafetyPolicy.Known spVal = SafetyPolicy.Known.values()[0];
    SafetyPolicy sp = new SafetyPolicy(spVal);
    assertEquals(spVal, sp.knownEnum());

    // ModelStage
    assertNotNull(ModelStage.Known.values());
    ModelStage.Known msVal = ModelStage.Known.values()[0];
    ModelStage ms = new ModelStage(msVal);
    assertEquals(msVal, ms.knownEnum());

    // PhishBlockThreshold
    assertNotNull(PhishBlockThreshold.Known.values());
    PhishBlockThreshold.Known pbtVal = PhishBlockThreshold.Known.values()[0];
    PhishBlockThreshold pbt = new PhishBlockThreshold(pbtVal);
    assertEquals(pbtVal, pbt.knownEnum());

    // ToolType
    assertNotNull(ToolType.Known.values());
    ToolType.Known ttVal = ToolType.Known.values()[0];
    ToolType tt = new ToolType(ttVal);
    assertEquals(ttVal, tt.knownEnum());

    // EditMode
    assertNotNull(EditMode.Known.values());
    EditMode.Known emVal = EditMode.Known.values()[0];
    EditMode em = new EditMode(emVal);
    assertEquals(emVal, em.knownEnum());

    // TuningMethod
    assertNotNull(TuningMethod.Known.values());
    TuningMethod.Known tmVal = TuningMethod.Known.values()[0];
    TuningMethod tm = new TuningMethod(tmVal);
    assertEquals(tmVal, tm.knownEnum());

    // Modality
    assertNotNull(Modality.Known.values());
    Modality.Known modVal = Modality.Known.values()[0];
    Modality mod = new Modality(modVal);
    assertEquals(modVal, mod.knownEnum());

    // ImagePromptLanguage
    assertNotNull(ImagePromptLanguage.Known.values());
    ImagePromptLanguage.Known iplVal = ImagePromptLanguage.Known.values()[0];
    ImagePromptLanguage ipl = new ImagePromptLanguage(iplVal);
    assertEquals(iplVal, ipl.knownEnum());

    // BlockedReason
    assertNotNull(BlockedReason.Known.values());
    BlockedReason.Known brVal = BlockedReason.Known.values()[0];
    BlockedReason br = new BlockedReason(brVal);
    assertEquals(brVal, br.knownEnum());
  }
}
