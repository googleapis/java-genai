/*
 * Copyright 2026 Google LLC
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class TypesBuildersTest {

  @Test
  public void testLiveConnectConfigBuilder() {
    LiveConnectConfig config =
        LiveConnectConfig.builder()
            .temperature(0.7f)
            .topP(0.9f)
            .topK(40f)
            .maxOutputTokens(100)
            .seed(12345)
            .enableAffectiveDialog(true)
            .explicitVadSignal(false)
            // Call overloaded setters purely for coverage
            .mediaResolution(MediaResolution.Known.MEDIA_RESOLUTION_HIGH)
            .mediaResolution("MEDIA_RESOLUTION_LOW")
            // Call overloaded setters purely for coverage
            .responseModalities(Modality.Known.AUDIO)
            .responseModalities("AUDIO")
            .responseModalitiesFromKnown(Arrays.asList(Modality.Known.TEXT))
            .responseModalitiesFromString(Arrays.asList("TEXT", "AUDIO"))
            .speechConfig(
                SpeechConfig.builder()
                    .voiceConfig(
                        VoiceConfig.builder()
                            .prebuiltVoiceConfig(PrebuiltVoiceConfig.builder().voiceName("Puck"))))
            .thinkingConfig(ThinkingConfig.builder().thinkingLevel(ThinkingLevel.Known.HIGH))
            .systemInstruction(Content.builder().parts(Part.builder().text("instruction").build()))
            .tools(Tool.builder().codeExecution(ToolCodeExecution.builder().build()))
            .sessionResumption(SessionResumptionConfig.builder().handle("handle"))
            .inputAudioTranscription(
                AudioTranscriptionConfig.builder()
                    .languageCodes("en-US"))
            .outputAudioTranscription(
                AudioTranscriptionConfig.builder()
                    .languageCodes("en-US"))
            .realtimeInputConfig(
                RealtimeInputConfig.builder()
                    .activityHandling(ActivityHandling.Known.START_OF_ACTIVITY_INTERRUPTS))
            .contextWindowCompression(ContextWindowCompressionConfig.builder().triggerTokens(1000L))
            .proactivity(ProactivityConfig.builder().proactiveAudio(true))
            .avatarConfig(AvatarConfig.builder().avatarName("avatar"))
            .safetySettings(
                SafetySetting.builder()
                    .category(HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH)
                    .threshold(HarmBlockThreshold.Known.BLOCK_LOW_AND_ABOVE))
            .translationConfig(TranslationConfig.builder().targetLanguageCode("en"))
            .build();

    assertNotNull(config);
    assertEquals(Float.valueOf(0.7f), config.temperature().get());
    assertEquals(Float.valueOf(0.9f), config.topP().get());
    assertEquals(Float.valueOf(40f), config.topK().get());
    assertEquals(Integer.valueOf(100), config.maxOutputTokens().get());
    assertEquals(Integer.valueOf(12345), config.seed().get());
    assertTrue(config.enableAffectiveDialog().get());
    assertFalse(config.explicitVadSignal().get());
    assertEquals(
        MediaResolution.Known.MEDIA_RESOLUTION_LOW, config.mediaResolution().get().knownEnum());
    assertEquals(2, config.responseModalities().get().size());
    assertEquals(
        "Puck",
        config
            .speechConfig()
            .get()
            .voiceConfig()
            .get()
            .prebuiltVoiceConfig()
            .get()
            .voiceName()
            .get());
    assertEquals(
        ThinkingLevel.Known.HIGH, config.thinkingConfig().get().thinkingLevel().get().knownEnum());
    assertEquals("instruction", config.systemInstruction().get().parts().get().get(0).text().get());
    assertTrue(config.tools().get().get(0).codeExecution().isPresent());
    assertEquals("handle", config.sessionResumption().get().handle().get());
    assertEquals(
        Arrays.asList("en-US"),
        config.inputAudioTranscription().get().languageCodes().get());
    assertEquals(
        Arrays.asList("en-US"),
        config.outputAudioTranscription().get().languageCodes().get());
    assertEquals(
        ActivityHandling.Known.START_OF_ACTIVITY_INTERRUPTS,
        config.realtimeInputConfig().get().activityHandling().get().knownEnum());
    assertEquals(
        Long.valueOf(1000L), config.contextWindowCompression().get().triggerTokens().get());
    assertTrue(config.proactivity().get().proactiveAudio().get());
    assertEquals("avatar", config.avatarConfig().get().avatarName().get());
    assertEquals(
        HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH,
        config.safetySettings().get().get(0).category().get().knownEnum());
    assertEquals(
        HarmBlockThreshold.Known.BLOCK_LOW_AND_ABOVE,
        config.safetySettings().get().get(0).threshold().get().knownEnum());
    assertEquals("en", config.translationConfig().get().targetLanguageCode().get());

    LiveConnectConfig copy = config.toBuilder().build();
    assertEquals(config.temperature(), copy.temperature());
    assertEquals(config.mediaResolution(), copy.mediaResolution());
    assertEquals(config.speechConfig(), copy.speechConfig());

    String json = config.toJson();
    assertNotNull(json);
    LiveConnectConfig deserialized = LiveConnectConfig.fromJson(json);
    assertEquals(config.temperature(), deserialized.temperature());
    assertEquals(config.mediaResolution(), deserialized.mediaResolution());
  }

  @Test
  public void testGenerationConfigBuilder() {
    GenerationConfig config =
        GenerationConfig.builder()
            .temperature(0.5f)
            .topP(0.8f)
            .topK(20f)
            .maxOutputTokens(500)
            .candidateCount(1)
            .seed(42)
            .enableEnhancedCivicAnswers(true)
            // Call overloaded setters purely for coverage
            .stopSequences(Arrays.asList("stop1"))
            .stopSequences("stop1", "stop2")
            .responseMimeType("application/json")
            // Call overloaded setters purely for coverage
            .responseModalities(Modality.Known.AUDIO)
            .responseModalities("TEXT")
            .mediaResolution(MediaResolution.Known.MEDIA_RESOLUTION_HIGH)
            .thinkingConfig(ThinkingConfig.builder().thinkingBudget(1024))
            .speechConfig(
                SpeechConfig.builder()
                    .voiceConfig(
                        VoiceConfig.builder()
                            .prebuiltVoiceConfig(PrebuiltVoiceConfig.builder().voiceName("Aoede"))))
            .audioTranscriptionConfig(
                AudioTranscriptionConfig.builder().languageCodes("en-US"))
            .routingConfig(
                GenerationConfigRoutingConfig.builder()
                    .autoMode(
                        GenerationConfigRoutingConfigAutoRoutingMode.builder()
                            .modelRoutingPreference(
                                ModelRoutingPreference.Known.PRIORITIZE_QUALITY)))
            .build();

    assertNotNull(config);
    assertEquals(Float.valueOf(0.5f), config.temperature().get());
    assertEquals(Float.valueOf(0.8f), config.topP().get());
    assertEquals(Float.valueOf(20f), config.topK().get());
    assertEquals(Integer.valueOf(500), config.maxOutputTokens().get());
    assertEquals(Integer.valueOf(1), config.candidateCount().get());
    assertEquals(Integer.valueOf(42), config.seed().get());
    assertTrue(config.enableEnhancedCivicAnswers().get());
    assertEquals(Arrays.asList("stop1", "stop2"), config.stopSequences().get());
    assertEquals("application/json", config.responseMimeType().get());
    assertEquals(Modality.Known.TEXT, config.responseModalities().get().get(0).knownEnum());
    assertEquals(
        MediaResolution.Known.MEDIA_RESOLUTION_HIGH, config.mediaResolution().get().knownEnum());
    assertEquals(Integer.valueOf(1024), config.thinkingConfig().get().thinkingBudget().get());
    assertEquals(
        "Aoede",
        config
            .speechConfig()
            .get()
            .voiceConfig()
            .get()
            .prebuiltVoiceConfig()
            .get()
            .voiceName()
            .get());
    assertEquals(
        Arrays.asList("en-US"),
        config.audioTranscriptionConfig().get().languageCodes().get());
    assertEquals(
        ModelRoutingPreference.Known.PRIORITIZE_QUALITY,
        config.routingConfig().get().autoMode().get().modelRoutingPreference().get().knownEnum());

    GenerationConfig copy = config.toBuilder().build();
    assertEquals(config.temperature(), copy.temperature());
    assertEquals(config.speechConfig(), copy.speechConfig());

    String json = config.toJson();
    assertNotNull(json);
    GenerationConfig deserialized = GenerationConfig.fromJson(json);
    assertEquals(config.temperature(), deserialized.temperature());
  }

  @Test
  public void testUsageMetadataBuilder() {
    UsageMetadata metadata =
        UsageMetadata.builder()
            .promptTokenCount(10)
            .cachedContentTokenCount(5)
            .responseTokenCount(20)
            .toolUsePromptTokenCount(2)
            .thoughtsTokenCount(15)
            .totalTokenCount(47)
            .promptTokensDetails(
                ModalityTokenCount.builder().modality(MediaModality.Known.TEXT).tokenCount(10))
            .cacheTokensDetails(
                ModalityTokenCount.builder().modality(MediaModality.Known.TEXT).tokenCount(5))
            .responseTokensDetails(
                ModalityTokenCount.builder().modality(MediaModality.Known.TEXT).tokenCount(20))
            .toolUsePromptTokensDetails(
                ModalityTokenCount.builder().modality(MediaModality.Known.TEXT).tokenCount(2))
            // Call overloaded setters purely for coverage
            .trafficType(TrafficType.Known.ON_DEMAND)
            .trafficType("ON_DEMAND")
            // Call overloaded setters purely for coverage
            .serviceTier(ServiceTier.Known.STANDARD)
            .serviceTier("STANDARD")
            .build();

    assertNotNull(metadata);
    assertEquals(Integer.valueOf(10), metadata.promptTokenCount().get());
    assertEquals(Integer.valueOf(5), metadata.cachedContentTokenCount().get());
    assertEquals(Integer.valueOf(20), metadata.responseTokenCount().get());
    assertEquals(Integer.valueOf(2), metadata.toolUsePromptTokenCount().get());
    assertEquals(Integer.valueOf(15), metadata.thoughtsTokenCount().get());
    assertEquals(Integer.valueOf(47), metadata.totalTokenCount().get());
    assertEquals(
        Integer.valueOf(10), metadata.promptTokensDetails().get().get(0).tokenCount().get());
    assertEquals(
        MediaModality.Known.TEXT,
        metadata.promptTokensDetails().get().get(0).modality().get().knownEnum());
    assertEquals(Integer.valueOf(5), metadata.cacheTokensDetails().get().get(0).tokenCount().get());
    assertEquals(
        Integer.valueOf(20), metadata.responseTokensDetails().get().get(0).tokenCount().get());
    assertEquals(
        Integer.valueOf(2), metadata.toolUsePromptTokensDetails().get().get(0).tokenCount().get());
    assertEquals(TrafficType.Known.ON_DEMAND, metadata.trafficType().get().knownEnum());
    assertEquals(ServiceTier.Known.STANDARD, metadata.serviceTier().get().knownEnum());

    UsageMetadata copy = metadata.toBuilder().build();
    assertEquals(metadata.promptTokenCount(), copy.promptTokenCount());
    assertEquals(metadata.trafficType(), copy.trafficType());

    String json = metadata.toJson();
    assertNotNull(json);
    UsageMetadata deserialized = UsageMetadata.fromJson(json);
    assertEquals(metadata.promptTokenCount(), deserialized.promptTokenCount());
    assertEquals(metadata.totalTokenCount(), deserialized.totalTokenCount());
  }

  @Test
  public void testTuningJobAndConfigBuilder() {
    DatasetStats stats =
        DatasetStats.builder()
            .totalBillableCharacterCount(100L)
            .totalTuningCharacterCount(200L)
            .tuningDatasetExampleCount(50L)
            .build();
    assertNotNull(stats);
    assertEquals(Long.valueOf(100L), stats.totalBillableCharacterCount().get());
    assertEquals(Long.valueOf(200L), stats.totalTuningCharacterCount().get());
    assertEquals(Long.valueOf(50L), stats.tuningDatasetExampleCount().get());

    PreferenceOptimizationDataStats prefStats =
        PreferenceOptimizationDataStats.builder()
            .totalBillableTokenCount(10L)
            .tuningDatasetExampleCount(5L)
            .build();
    assertNotNull(prefStats);
    assertEquals(Long.valueOf(10L), prefStats.totalBillableTokenCount().get());
    assertEquals(Long.valueOf(5L), prefStats.tuningDatasetExampleCount().get());

    CreateTuningJobConfig config =
        CreateTuningJobConfig.builder()
            .epochCount(5)
            .batchSize(4)
            .learningRate(0.001f)
            .learningRateMultiplier(1.0f)
            .tunedModelDisplayName("MyTunedModel")
            // Call overloaded setters purely for coverage
            .method(TuningMethod.Known.PREFERENCE_TUNING)
            .method(new TuningMethod("SUPERVISED_FINE_TUNING"))
            .build();
    assertNotNull(config);
    assertEquals("MyTunedModel", config.tunedModelDisplayName().get());
    assertEquals(Integer.valueOf(5), config.epochCount().get());
    assertEquals(Integer.valueOf(4), config.batchSize().get());
    assertEquals(Float.valueOf(0.001f), config.learningRate().get());
    assertEquals(Float.valueOf(1.0f), config.learningRateMultiplier().get());
    assertEquals(TuningMethod.Known.SUPERVISED_FINE_TUNING, config.method().get().knownEnum());

    TuningJob job =
        TuningJob.builder()
            .name("tuningJobs/123")
            // Call overloaded setters purely for coverage
            .state(JobState.Known.JOB_STATE_QUEUED)
            .state("JOB_STATE_RUNNING")
            .baseModel("models/gemini-1.5-pro")
            .createTime(Instant.parse("2026-01-01T00:00:00Z"))
            .updateTime(Instant.parse("2026-01-01T01:00:00Z"))
            .build();
    assertNotNull(job);
    assertEquals("tuningJobs/123", job.name().get());
    assertEquals(JobState.Known.JOB_STATE_RUNNING, job.state().get().knownEnum());
    assertEquals("models/gemini-1.5-pro", job.baseModel().get());
    assertEquals(Instant.parse("2026-01-01T00:00:00Z"), job.createTime().get());
    assertEquals(Instant.parse("2026-01-01T01:00:00Z"), job.updateTime().get());

    String json = job.toJson();
    assertNotNull(json);
    TuningJob deserialized = TuningJob.fromJson(json);
    assertEquals(job.name(), deserialized.name());
  }

  @Test
  public void testLiveClientSetupAndMessagesBuilder() {
    LiveClientSetup setup =
        LiveClientSetup.builder()
            .model("models/gemini-2.0-flash")
            .generationConfig(GenerationConfig.builder().temperature(0.5f).build())
            .build();
    assertNotNull(setup);
    assertEquals("models/gemini-2.0-flash", setup.model().get());
    assertEquals(Float.valueOf(0.5f), setup.generationConfig().get().temperature().get());

    LiveServerContent content =
        LiveServerContent.builder()
            .modelTurn(Content.builder().parts(Part.builder().text("hello").build()))
            .turnComplete(true)
            .interrupted(false)
            .interactionStatus(InteractionStatus.Known.IDLE)
            .build();
    assertNotNull(content);
    assertTrue(content.turnComplete().get());
    assertFalse(content.interrupted().get());
    assertEquals("hello", content.modelTurn().get().parts().get().get(0).text().get());
    assertEquals(InteractionStatus.Known.IDLE, content.interactionStatus().get().knownEnum());

    LiveServerMessage message = LiveServerMessage.builder().serverContent(content).build();
    assertNotNull(message);
    assertTrue(message.serverContent().get().turnComplete().get());

    LiveClientRealtimeInput input =
        LiveClientRealtimeInput.builder()
            .mediaChunks(Blob.builder().mimeType("audio/pcm").data(new byte[] {1, 2, 3}).build())
            .build();
    assertNotNull(input);
    assertEquals("audio/pcm", input.mediaChunks().get().get(0).mimeType().get());

    RealtimeInputConfig realtimeConfig =
        RealtimeInputConfig.builder()
            .activityHandling(ActivityHandling.Known.START_OF_ACTIVITY_INTERRUPTS)
            .turnCoverage(TurnCoverage.Known.TURN_INCLUDES_ALL_INPUT)
            .build();
    assertNotNull(realtimeConfig);
    assertEquals(
        ActivityHandling.Known.START_OF_ACTIVITY_INTERRUPTS,
        realtimeConfig.activityHandling().get().knownEnum());
    assertEquals(
        TurnCoverage.Known.TURN_INCLUDES_ALL_INPUT,
        realtimeConfig.turnCoverage().get().knownEnum());
  }

  @Test
  public void testGenerateVideosAndImagesConfigBuilder() {
    GenerateVideosConfig videoConfig =
        GenerateVideosConfig.builder()
            .aspectRatio("16:9")
            .durationSeconds(5)
            .fps(24)
            .personGeneration("ALLOW_ADULT")
            .build();
    assertNotNull(videoConfig);
    assertEquals("16:9", videoConfig.aspectRatio().get());
    assertEquals(Integer.valueOf(5), videoConfig.durationSeconds().get());
    assertEquals(Integer.valueOf(24), videoConfig.fps().get());
    assertEquals("ALLOW_ADULT", videoConfig.personGeneration().get());

    GenerateImagesConfig imageConfig =
        GenerateImagesConfig.builder()
            .aspectRatio("1:1")
            .numberOfImages(2)
            .outputMimeType("image/png")
            // Call overloaded setters purely for coverage
            .personGeneration(PersonGeneration.Known.DONT_ALLOW)
            .personGeneration("ALLOW_ALL")
            .build();
    assertNotNull(imageConfig);
    assertEquals("1:1", imageConfig.aspectRatio().get());
    assertEquals(Integer.valueOf(2), imageConfig.numberOfImages().get());
    assertEquals("image/png", imageConfig.outputMimeType().get());
    assertEquals(
        PersonGeneration.Known.ALLOW_ALL, imageConfig.personGeneration().get().knownEnum());

    EditImageConfig editConfig =
        EditImageConfig.builder()
            .aspectRatio("1:1")
            // Call overloaded setters purely for coverage
            .editMode(EditMode.Known.EDIT_MODE_DEFAULT)
            .editMode("EDIT_MODE_INPAINT_INSERTION")
            .numberOfImages(1)
            .outputMimeType("image/jpeg")
            .build();
    assertNotNull(editConfig);
    assertEquals("1:1", editConfig.aspectRatio().get());
    assertEquals(
        EditMode.Known.EDIT_MODE_INPAINT_INSERTION, editConfig.editMode().get().knownEnum());
    assertEquals(Integer.valueOf(1), editConfig.numberOfImages().get());
    assertEquals("image/jpeg", editConfig.outputMimeType().get());

    VeoHyperParameters veo =
        VeoHyperParameters.builder()
            .epochCount(10L)
            .learningRateMultiplier(1.0)
            .tuningTask(TuningTask.Known.TUNING_TASK_T2V)
            .tuningSpeed(TuningSpeed.Known.FAST)
            .build();
    assertNotNull(veo);
    assertEquals(Long.valueOf(10L), veo.epochCount().get());
    assertEquals(Double.valueOf(1.0), veo.learningRateMultiplier().get());
    assertEquals(TuningTask.Known.TUNING_TASK_T2V, veo.tuningTask().get().knownEnum());
    assertEquals(TuningSpeed.Known.FAST, veo.tuningSpeed().get().knownEnum());

    ImageResponseFormat format =
        ImageResponseFormat.builder()
            .aspectRatio(AspectRatio.Known.ASPECT_RATIO_FOUR_BY_THREE)
            .delivery(Delivery.Known.INLINE)
            .imageSize(ImageSize.Known.IMAGE_SIZE_ONE_K)
            .mimeType("image/png")
            .build();
    assertNotNull(format);
    assertEquals(
        AspectRatio.Known.ASPECT_RATIO_FOUR_BY_THREE, format.aspectRatio().get().knownEnum());
    assertEquals(Delivery.Known.INLINE, format.delivery().get().knownEnum());
    assertEquals(ImageSize.Known.IMAGE_SIZE_ONE_K, format.imageSize().get().knownEnum());
    assertEquals("image/png", format.mimeType().get());
  }

  @Test
  public void testSafetyAndGroundingAndCandidateBuilder() {
    SafetyRating rating =
        SafetyRating.builder()
            // Call overloaded setters purely for coverage
            .category(HarmCategory.Known.HARM_CATEGORY_HARASSMENT)
            .category("HARM_CATEGORY_HATE_SPEECH")
            // Call overloaded setters purely for coverage
            .probability(HarmProbability.Known.LOW)
            .probability("NEGLIGIBLE")
            // Call overloaded setters purely for coverage
            .severity(HarmSeverity.Known.HARM_SEVERITY_LOW)
            .severity("HARM_SEVERITY_NEGLIGIBLE")
            .blocked(false)
            .build();
    assertNotNull(rating);
    assertEquals(HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH, rating.category().get().knownEnum());
    assertEquals(HarmProbability.Known.NEGLIGIBLE, rating.probability().get().knownEnum());
    assertEquals(HarmSeverity.Known.HARM_SEVERITY_NEGLIGIBLE, rating.severity().get().knownEnum());
    assertFalse(rating.blocked().get());

    GroundingMetadata grounding =
        GroundingMetadata.builder()
            // Call overloaded setters purely for coverage
            .webSearchQueries(Arrays.asList("query1"))
            .webSearchQueries("query1", "query2")
            .searchEntryPoint(SearchEntryPoint.builder().renderedContent("html"))
            .build();
    assertNotNull(grounding);
    assertEquals(Arrays.asList("query1", "query2"), grounding.webSearchQueries().get());
    assertEquals("html", grounding.searchEntryPoint().get().renderedContent().get());

    Candidate candidate =
        Candidate.builder()
            .index(0)
            // Call overloaded setters purely for coverage
            .finishReason(FinishReason.Known.MAX_TOKENS)
            .finishReason("STOP")
            .content(Content.builder().parts(Part.builder().text("response text").build()))
            // Call overloaded setters purely for coverage
            .safetyRatings(Arrays.asList(rating))
            .safetyRatings(rating)
            .groundingMetadata(grounding)
            .build();
    assertNotNull(candidate);
    assertEquals(Integer.valueOf(0), candidate.index().get());
    assertEquals(FinishReason.Known.STOP, candidate.finishReason().get().knownEnum());
    assertEquals("response text", candidate.content().get().parts().get().get(0).text().get());
    assertEquals(1, candidate.safetyRatings().get().size());
    assertEquals(grounding, candidate.groundingMetadata().get());

    ComputerUse computerUse =
        ComputerUse.builder()
            // Call overloaded setters purely for coverage
            .environment(Environment.Known.ENVIRONMENT_UNSPECIFIED)
            .environment("ENVIRONMENT_BROWSER")
            .build();
    assertNotNull(computerUse);
    assertEquals(
        Environment.Known.ENVIRONMENT_BROWSER, computerUse.environment().get().knownEnum());

    AggregationResult aggResult =
        AggregationResult.builder()
            // Call overloaded setters purely for coverage
            .aggregationMetric(AggregationMetric.Known.AGGREGATION_METRIC_UNSPECIFIED)
            .aggregationMetric(new AggregationMetric("AVERAGE"))
            .build();
    assertNotNull(aggResult);
    assertEquals(AggregationMetric.Known.AVERAGE, aggResult.aggregationMetric().get().knownEnum());

    AuthConfig authConfig =
        AuthConfig.builder()
            // Call overloaded setters purely for coverage
            .authType(AuthType.Known.NO_AUTH)
            .authType(new AuthType("API_KEY_AUTH"))
            .build();
    assertNotNull(authConfig);
    assertEquals(AuthType.Known.API_KEY_AUTH, authConfig.authType().get().knownEnum());

    EmbedContentParametersPrivate embedPrivate =
        EmbedContentParametersPrivate.builder()
            .content(Content.builder().parts(Part.builder().text("embed text").build()))
            .model("models/embedding-001")
            .build();
    assertNotNull(embedPrivate);
    assertEquals("models/embedding-001", embedPrivate.model().get());
    assertEquals("embed text", embedPrivate.content().get().parts().get().get(0).text().get());
  }
}
