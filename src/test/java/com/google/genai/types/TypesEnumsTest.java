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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

public class TypesEnumsTest {

  private <E extends Enum<E>, W> void testEnumWrapper(Class<W> wrapperClass, Class<E> enumClass)
      throws Exception {
    Constructor<W> knownConstructor = wrapperClass.getConstructor(enumClass);
    Constructor<W> stringConstructor = wrapperClass.getConstructor(String.class);
    Method knownEnumMethod = wrapperClass.getMethod("knownEnum");

    // Test Known values
    for (E known : enumClass.getEnumConstants()) {
      W fromKnown = knownConstructor.newInstance(known);
      assertEquals(known, knownEnumMethod.invoke(fromKnown));
      assertEquals(known.toString(), fromKnown.toString());

      W fromString = stringConstructor.newInstance(known.toString());
      assertEquals(fromKnown, fromString);
      assertEquals(fromKnown.hashCode(), fromString.hashCode());
      assertEquals(known, knownEnumMethod.invoke(fromString));
      assertEquals(fromKnown, fromKnown);
      assertFalse(fromKnown.equals(null));
      assertFalse(fromKnown.equals("different-type"));
    }

    // Test Unknown values
    String customValue = "UNKNOWN_CUSTOM_VALUE";
    W unknown = stringConstructor.newInstance(customValue);
    assertEquals(customValue, unknown.toString());
    W unknown2 = stringConstructor.newInstance(customValue);
    assertEquals(unknown, unknown2);
    assertEquals(unknown.hashCode(), unknown2.hashCode());
    assertNotNull(knownEnumMethod.invoke(unknown));
  }

  @Test
  public void testActivityHandling() throws Exception {
    testEnumWrapper(ActivityHandling.class, ActivityHandling.Known.class);
  }

  @Test
  public void testAdapterSize() throws Exception {
    testEnumWrapper(AdapterSize.class, AdapterSize.Known.class);
  }

  @Test
  public void testAggregationMetric() throws Exception {
    testEnumWrapper(AggregationMetric.class, AggregationMetric.Known.class);
  }

  @Test
  public void testApiSpec() throws Exception {
    testEnumWrapper(ApiSpec.class, ApiSpec.Known.class);
  }

  @Test
  public void testAspectRatio() throws Exception {
    testEnumWrapper(AspectRatio.class, AspectRatio.Known.class);
  }

  @Test
  public void testAudioTranscriptionConfigMode() throws Exception {
    testEnumWrapper(AudioTranscriptionConfigMode.class, AudioTranscriptionConfigMode.Known.class);
  }

  @Test
  public void testAuthType() throws Exception {
    testEnumWrapper(AuthType.class, AuthType.Known.class);
  }

  @Test
  public void testBehavior() throws Exception {
    testEnumWrapper(Behavior.class, Behavior.Known.class);
  }

  @Test
  public void testBlockedReason() throws Exception {
    testEnumWrapper(BlockedReason.class, BlockedReason.Known.class);
  }

  @Test
  public void testComputationBasedMetricType() throws Exception {
    testEnumWrapper(ComputationBasedMetricType.class, ComputationBasedMetricType.Known.class);
  }

  @Test
  public void testControlReferenceType() throws Exception {
    testEnumWrapper(ControlReferenceType.class, ControlReferenceType.Known.class);
  }

  @Test
  public void testDelivery() throws Exception {
    testEnumWrapper(Delivery.class, Delivery.Known.class);
  }

  @Test
  public void testDocumentState() throws Exception {
    testEnumWrapper(DocumentState.class, DocumentState.Known.class);
  }

  @Test
  public void testDynamicRetrievalConfigMode() throws Exception {
    testEnumWrapper(DynamicRetrievalConfigMode.class, DynamicRetrievalConfigMode.Known.class);
  }

  @Test
  public void testEditMode() throws Exception {
    testEnumWrapper(EditMode.class, EditMode.Known.class);
  }

  @Test
  public void testEmbeddingApiType() throws Exception {
    testEnumWrapper(EmbeddingApiType.class, EmbeddingApiType.Known.class);
  }

  @Test
  public void testEndSensitivity() throws Exception {
    testEnumWrapper(EndSensitivity.class, EndSensitivity.Known.class);
  }

  @Test
  public void testEnvironment() throws Exception {
    testEnumWrapper(Environment.class, Environment.Known.class);
  }

  @Test
  public void testFeatureSelectionPreference() throws Exception {
    testEnumWrapper(FeatureSelectionPreference.class, FeatureSelectionPreference.Known.class);
  }

  @Test
  public void testFileSource() throws Exception {
    testEnumWrapper(FileSource.class, FileSource.Known.class);
  }

  @Test
  public void testFileState() throws Exception {
    testEnumWrapper(FileState.class, FileState.Known.class);
  }

  @Test
  public void testFinishReason() throws Exception {
    testEnumWrapper(FinishReason.class, FinishReason.Known.class);
  }

  @Test
  public void testFunctionCallingConfigMode() throws Exception {
    testEnumWrapper(FunctionCallingConfigMode.class, FunctionCallingConfigMode.Known.class);
  }

  @Test
  public void testFunctionResponseScheduling() throws Exception {
    testEnumWrapper(FunctionResponseScheduling.class, FunctionResponseScheduling.Known.class);
  }

  @Test
  public void testHarmBlockMethod() throws Exception {
    testEnumWrapper(HarmBlockMethod.class, HarmBlockMethod.Known.class);
  }

  @Test
  public void testHarmBlockThreshold() throws Exception {
    testEnumWrapper(HarmBlockThreshold.class, HarmBlockThreshold.Known.class);
  }

  @Test
  public void testHarmCategory() throws Exception {
    testEnumWrapper(HarmCategory.class, HarmCategory.Known.class);
  }

  @Test
  public void testHarmProbability() throws Exception {
    testEnumWrapper(HarmProbability.class, HarmProbability.Known.class);
  }

  @Test
  public void testHarmSeverity() throws Exception {
    testEnumWrapper(HarmSeverity.class, HarmSeverity.Known.class);
  }

  @Test
  public void testHttpElementLocation() throws Exception {
    testEnumWrapper(HttpElementLocation.class, HttpElementLocation.Known.class);
  }

  @Test
  public void testImagePromptLanguage() throws Exception {
    testEnumWrapper(ImagePromptLanguage.class, ImagePromptLanguage.Known.class);
  }

  @Test
  public void testImageResizeMode() throws Exception {
    testEnumWrapper(ImageResizeMode.class, ImageResizeMode.Known.class);
  }

  @Test
  public void testImageSize() throws Exception {
    testEnumWrapper(ImageSize.class, ImageSize.Known.class);
  }

  @Test
  public void testInteractionStatus() throws Exception {
    testEnumWrapper(InteractionStatus.class, InteractionStatus.Known.class);
  }

  @Test
  public void testJobState() throws Exception {
    testEnumWrapper(JobState.class, JobState.Known.class);
  }

  @Test
  public void testLanguage() throws Exception {
    testEnumWrapper(Language.class, Language.Known.class);
  }

  @Test
  public void testMaskReferenceMode() throws Exception {
    testEnumWrapper(MaskReferenceMode.class, MaskReferenceMode.Known.class);
  }

  @Test
  public void testMatchOperation() throws Exception {
    testEnumWrapper(MatchOperation.class, MatchOperation.Known.class);
  }

  @Test
  public void testMediaModality() throws Exception {
    testEnumWrapper(MediaModality.class, MediaModality.Known.class);
  }

  @Test
  public void testMediaResolution() throws Exception {
    testEnumWrapper(MediaResolution.class, MediaResolution.Known.class);
  }

  @Test
  public void testModality() throws Exception {
    testEnumWrapper(Modality.class, Modality.Known.class);
  }

  @Test
  public void testModelRoutingPreference() throws Exception {
    testEnumWrapper(ModelRoutingPreference.class, ModelRoutingPreference.Known.class);
  }

  @Test
  public void testModelStage() throws Exception {
    testEnumWrapper(ModelStage.class, ModelStage.Known.class);
  }

  @Test
  public void testNullValue() throws Exception {
    testEnumWrapper(NullValue.class, NullValue.Known.class);
  }

  @Test
  public void testOutcome() throws Exception {
    testEnumWrapper(Outcome.class, Outcome.Known.class);
  }

  @Test
  public void testPairwiseChoice() throws Exception {
    testEnumWrapper(PairwiseChoice.class, PairwiseChoice.Known.class);
  }

  @Test
  public void testPartMediaResolutionLevel() throws Exception {
    testEnumWrapper(PartMediaResolutionLevel.class, PartMediaResolutionLevel.Known.class);
  }

  @Test
  public void testPersonGeneration() throws Exception {
    testEnumWrapper(PersonGeneration.class, PersonGeneration.Known.class);
  }

  @Test
  public void testPhishBlockThreshold() throws Exception {
    testEnumWrapper(PhishBlockThreshold.class, PhishBlockThreshold.Known.class);
  }

  @Test
  public void testProminentPeople() throws Exception {
    testEnumWrapper(ProminentPeople.class, ProminentPeople.Known.class);
  }

  @Test
  public void testProxyType() throws Exception {
    testEnumWrapper(ProxyType.class, ProxyType.Known.class);
  }

  @Test
  public void testReinforcementTuningThinkingLevel() throws Exception {
    testEnumWrapper(
        ReinforcementTuningThinkingLevel.class, ReinforcementTuningThinkingLevel.Known.class);
  }

  @Test
  public void testResourceScope() throws Exception {
    testEnumWrapper(ResourceScope.class, ResourceScope.Known.class);
  }

  @Test
  public void testResponseParseType() throws Exception {
    testEnumWrapper(ResponseParseType.class, ResponseParseType.Known.class);
  }

  @Test
  public void testRubricContentType() throws Exception {
    testEnumWrapper(RubricContentType.class, RubricContentType.Known.class);
  }

  @Test
  public void testSafetyFilterLevel() throws Exception {
    testEnumWrapper(SafetyFilterLevel.class, SafetyFilterLevel.Known.class);
  }

  @Test
  public void testSafetyPolicy() throws Exception {
    testEnumWrapper(SafetyPolicy.class, SafetyPolicy.Known.class);
  }

  @Test
  public void testSegmentMode() throws Exception {
    testEnumWrapper(SegmentMode.class, SegmentMode.Known.class);
  }

  @Test
  public void testServiceTier() throws Exception {
    testEnumWrapper(ServiceTier.class, ServiceTier.Known.class);
  }

  @Test
  public void testStartSensitivity() throws Exception {
    testEnumWrapper(StartSensitivity.class, StartSensitivity.Known.class);
  }

  @Test
  public void testSubjectReferenceType() throws Exception {
    testEnumWrapper(SubjectReferenceType.class, SubjectReferenceType.Known.class);
  }

  @Test
  public void testThinkingLevel() throws Exception {
    testEnumWrapper(ThinkingLevel.class, ThinkingLevel.Known.class);
  }

  @Test
  public void testToolType() throws Exception {
    testEnumWrapper(ToolType.class, ToolType.Known.class);
  }

  @Test
  public void testTrafficType() throws Exception {
    testEnumWrapper(TrafficType.class, TrafficType.Known.class);
  }

  @Test
  public void testTuningJobState() throws Exception {
    testEnumWrapper(TuningJobState.class, TuningJobState.Known.class);
  }

  @Test
  public void testTuningMethod() throws Exception {
    testEnumWrapper(TuningMethod.class, TuningMethod.Known.class);
  }

  @Test
  public void testTuningMode() throws Exception {
    testEnumWrapper(TuningMode.class, TuningMode.Known.class);
  }

  @Test
  public void testTuningSpeed() throws Exception {
    testEnumWrapper(TuningSpeed.class, TuningSpeed.Known.class);
  }

  @Test
  public void testTuningTask() throws Exception {
    testEnumWrapper(TuningTask.class, TuningTask.Known.class);
  }

  @Test
  public void testTurnCompleteReason() throws Exception {
    testEnumWrapper(TurnCompleteReason.class, TurnCompleteReason.Known.class);
  }

  @Test
  public void testTurnCoverage() throws Exception {
    testEnumWrapper(TurnCoverage.class, TurnCoverage.Known.class);
  }

  @Test
  public void testType() throws Exception {
    testEnumWrapper(Type.class, Type.Known.class);
  }

  @Test
  public void testUrlRetrievalStatus() throws Exception {
    testEnumWrapper(UrlRetrievalStatus.class, UrlRetrievalStatus.Known.class);
  }

  @Test
  public void testVadSignalType() throws Exception {
    testEnumWrapper(VadSignalType.class, VadSignalType.Known.class);
  }

  @Test
  public void testVideoCompressionQuality() throws Exception {
    testEnumWrapper(VideoCompressionQuality.class, VideoCompressionQuality.Known.class);
  }

  @Test
  public void testVideoGenerationMaskMode() throws Exception {
    testEnumWrapper(VideoGenerationMaskMode.class, VideoGenerationMaskMode.Known.class);
  }

  @Test
  public void testVideoGenerationReferenceType() throws Exception {
    testEnumWrapper(VideoGenerationReferenceType.class, VideoGenerationReferenceType.Known.class);
  }

  @Test
  public void testVideoOrientation() throws Exception {
    testEnumWrapper(VideoOrientation.class, VideoOrientation.Known.class);
  }

  @Test
  public void testVoiceActivityType() throws Exception {
    testEnumWrapper(VoiceActivityType.class, VoiceActivityType.Known.class);
  }
}
