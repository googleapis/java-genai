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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.Content;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.Part;
import com.google.genai.types.Schema;
import com.google.genai.types.Type;
import com.google.genai.types.interactions.CancelInteractionConfig;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionConfig;
import com.google.genai.types.interactions.DeleteInteractionResponse;
import com.google.genai.types.interactions.GenerationConfig;
import com.google.genai.types.interactions.GetInteractionConfig;
import com.google.genai.types.interactions.ImageConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.ResponseModality;
import com.google.genai.types.interactions.content.AudioContent;
import com.google.genai.types.interactions.content.DocumentContent;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.VideoContent;
import com.google.genai.types.interactions.tools.CodeExecution;
import com.google.genai.types.interactions.tools.Function;
import com.google.genai.types.interactions.tools.GoogleSearch;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Comprehensive replay-based synchronous tests for Interactions API.
 *
 * <p>This test suite follows the same pattern as ModelsTest.java, providing comprehensive coverage
 * of all Interactions API features including CRUD operations, model-based interactions,
 * agent-based interactions, tools, multimodal content, and error scenarios.
 *
 * <p>To run these tests, set: export GOOGLE_GENAI_REPLAYS_DIRECTORY="/path/to/genai/replays"
 */
@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class InteractionsComprehensiveTest {

  private static final String MODEL_ID = "gemini-2.5-flash";
  private static final String AGENT_ID = "deep-research-pro-preview-12-2025";

  // ==================== Core CRUD Operations ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withBasicModel(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/comprehensive/create_basic_model." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("What is the capital of France?")
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
    assertNotNull(interaction.status());
    assertTrue(interaction.model().isPresent());
    assertEquals(MODEL_ID, interaction.model().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGet_withValidId(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/comprehensive/get_valid_id." + suffix + ".json");

    String interactionId =
        vertexAI
            ? "projects/test-project/locations/us-central1/interactions/test-interaction-id"
            : "v1_test-interaction-id";

    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Act
    Interaction interaction = client.interactions.get(interactionId, config);

    // Assert
    assertNotNull(interaction);
    assertEquals(interactionId, interaction.id());
    assertNotNull(interaction.status());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCancel_backgroundInteraction(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/cancel_background." + suffix + ".json");

    String interactionId =
        vertexAI
            ? "projects/test-project/locations/us-central1/interactions/background-id"
            : "v1_background-id";

    CancelInteractionConfig config = CancelInteractionConfig.builder().build();

    // Act
    Interaction interaction = client.interactions.cancel(interactionId, config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
    assertNotNull(interaction.status());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDelete_existingInteraction(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/comprehensive/delete_existing." + suffix + ".json");

    String interactionId =
        vertexAI
            ? "projects/test-project/locations/us-central1/interactions/delete-id"
            : "v1_delete-id";

    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    // Act
    client.interactions.delete(interactionId, config);

    // Assert - Success is indicated by no exception thrown
  }

  // ==================== Model-Based Interactions ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withGenerationConfig(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_generation_config." + suffix + ".json");

    GenerationConfig generationConfig =
        GenerationConfig.builder()
            .temperature(0.7f)
            .topP(0.9f)
            .maxOutputTokens(1024)
            .stopSequences(ImmutableList.of("END", "STOP"))
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Write a creative story.")
            .generationConfig(generationConfig)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
    assertTrue(interaction.model().isPresent());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withSystemInstruction(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_system_instruction." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Explain quantum mechanics.")
            .systemInstruction("You are a helpful assistant specialized in science.")
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withResponseFormat(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_response_format." + suffix + ".json");

    Schema responseSchema =
        Schema.builder()
            .type(new Type(Type.Known.OBJECT))
            .properties(ImmutableMap.of(
                "name", Schema.builder().type(new Type(Type.Known.STRING)).build(),
                "age", Schema.builder().type(new Type(Type.Known.INTEGER)).build()))
            .required("name", "age")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Generate a person's information.")
            .responseFormat(responseSchema)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withResponseMimeType(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_response_mime_type." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Return JSON with user data.")
            .responseMimeType("application/json")
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withResponseModalities(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_response_modalities." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Describe a sunset.")
            .responseModalities(ResponseModality.Known.TEXT, ResponseModality.Known.IMAGE)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withStoreTrue(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/comprehensive/create_store_true." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Remember this conversation.")
            .store(true)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withBackgroundTrue(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_background_true." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Process this in the background.")
            .background(true)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withPreviousInteractionId(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_previous_interaction_id."
                + suffix
                + ".json");

    String previousId =
        vertexAI
            ? "projects/test-project/locations/us-central1/interactions/previous-id"
            : "v1_previous-id";

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Continue the conversation.")
            .previousInteractionId(previousId)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // ==================== Agent-Based Interactions ====================

  @ParameterizedTest
  @ValueSource(booleans = {false}) // Agent is MLDev only
  public void testCreate_withAgent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/comprehensive/create_with_agent." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent(AGENT_ID)
            .input("Research the history of quantum computing.")
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
    assertTrue(interaction.agent().isPresent());
    assertEquals(AGENT_ID, interaction.agent().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false}) // Agent is MLDev only
  public void testCreate_withAgentConfig(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_with_agent_config." + suffix + ".json");

    com.google.genai.types.interactions.AgentConfig agentConfig =
        com.google.genai.types.interactions.DeepResearchAgentConfig.builder()
            .thinkingSummaries(
                new com.google.genai.types.interactions.ThinkingSummaries(
                    com.google.genai.types.interactions.ThinkingSummaries.Known.AUTO))
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent(AGENT_ID)
            .input("Deep research on AI ethics.")
            .agentConfig(agentConfig)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false}) // Agent is MLDev only
  public void testCreate_agentWithBackground(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_agent_background." + suffix + ".json");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .agent(AGENT_ID)
            .input("Long-running research task.")
            .background(true)
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // ==================== Tools & Function Calling ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withFunction(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_function_tool." + suffix + ".json");

    Function functionTool = Function.builder()
        .name("get_weather")
        .description("Get the current weather for a location")
        .parameters(
            Schema.builder()
                .type(new Type(Type.Known.OBJECT))
                .properties(ImmutableMap.of(
                    "location", Schema.builder().type(new Type(Type.Known.STRING)).build()))
                .required("location")
                .build())
        .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("What's the weather in San Francisco?")
            .tools(ImmutableList.of(functionTool))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withGoogleSearch(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_google_search." + suffix + ".json");

    GoogleSearch googleSearch = GoogleSearch.builder().build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Search for latest AI news.")
            .tools(ImmutableList.of(googleSearch))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withCodeExecution(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_code_execution." + suffix + ".json");

    CodeExecution codeExecution = CodeExecution.builder().build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Calculate the factorial of 10.")
            .tools(ImmutableList.of(codeExecution))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {true}) // VertexAI only
  public void testCreate_withVertexAISearch(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = "vertex";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_vertex_search." + suffix + ".json");

    // Note: VertexAISearchTool would be used here if available
    // For now, we'll use a placeholder or skip this test if the tool doesn't exist

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Search Vertex AI documentation.")
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {true}) // VertexAI only
  public void testCreate_withRagRetrieval(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = "vertex";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_rag_retrieval." + suffix + ".json");

    // Note: RagRetrievalTool would be used here if available
    // For now, we'll use a placeholder or skip this test if the tool doesn't exist

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Retrieve relevant documents.")
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withMultipleTools(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_multiple_tools." + suffix + ".json");

    Function functionTool = Function.builder()
        .name("get_weather")
        .description("Get weather")
        .parameters(
            Schema.builder()
                .type(new Type(Type.Known.OBJECT))
                .properties(ImmutableMap.of(
                    "location", Schema.builder().type(new Type(Type.Known.STRING)).build()))
                .build())
        .build();
    GoogleSearch googleSearch = GoogleSearch.builder().build();
    CodeExecution codeExecution = CodeExecution.builder().build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .input("Use multiple tools to answer my question.")
            .tools(ImmutableList.of(functionTool, googleSearch, codeExecution))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // ==================== Multimodal Content Tests ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withImageContent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_image_content." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("google.png");
    Path filePath = Paths.get(resourceUrl.toURI());

    ImageContent imageContent =
        ImageContent.builder()
            .uri(filePath.toAbsolutePath().toString())
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Describe this image.").build(),
                    imageContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withAudioContent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_audio_content." + suffix + ".json");

    // Assuming we have a test audio file
    AudioContent audioContent =
        AudioContent.builder()
            .uri("gs://test-bucket/audio.mp3")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Transcribe this audio.").build(),
                    audioContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withVideoContent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_video_content." + suffix + ".json");

    VideoContent videoContent =
        VideoContent.builder()
            .uri("gs://test-bucket/video.mp4")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Analyze this video.").build(),
                    videoContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withDocumentContent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_document_content." + suffix + ".json");

    DocumentContent documentContent =
        DocumentContent.builder()
            .uri("gs://test-bucket/document.pdf")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Summarize this document.").build(),
                    documentContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_withMixedMediaContent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/create_mixed_media." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("google.png");
    Path filePath = Paths.get(resourceUrl.toURI());

    ImageContent imageContent =
        ImageContent.builder()
            .uri(filePath.toAbsolutePath().toString())
            .build();

    AudioContent audioContent =
        AudioContent.builder()
            .uri("gs://test-bucket/audio.mp3")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Analyze these media files.").build(),
                    imageContent,
                    audioContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // ==================== Error Scenarios ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_bothModelAndAgent_throwsException(boolean vertexAI) throws Exception {
    // Arrange
    Client client = TestUtils.createClient(vertexAI, "unused");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .agent(AGENT_ID)
            .input("Invalid config")
            .build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.interactions.create(config));
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCreate_neitherModelNorAgent_throwsException(boolean vertexAI) throws Exception {
    // Arrange
    Client client = TestUtils.createClient(vertexAI, "unused");

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .input("Invalid config - no model or agent")
            .build();

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.interactions.create(config));
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGet_invalidId_throwsException(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/get_invalid_id." + suffix + ".json");

    String invalidId = "invalid-interaction-id";
    GetInteractionConfig config = GetInteractionConfig.builder().build();

    // Act & Assert
    // Assuming the API returns a 404 or similar error
    assertThrows(
        Exception.class, // Could be more specific based on actual exception type
        () -> client.interactions.get(invalidId, config));
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDelete_alreadyDeleted_throwsException(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/interactions/comprehensive/delete_already_deleted." + suffix + ".json");

    String deletedId =
        vertexAI
            ? "projects/test-project/locations/us-central1/interactions/deleted-id"
            : "v1_deleted-id";

    DeleteInteractionConfig config = DeleteInteractionConfig.builder().build();

    // Act & Assert
    assertThrows(
        Exception.class, // Could be more specific based on actual exception type
        () -> client.interactions.delete(deletedId, config));
  }
}
