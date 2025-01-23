/**
 * Usage:
 *
 * <p>1. Install maven in your cloudtop:
 * go/vertex-sdk/java#setup-local-environment-in-cloudtop-or-workstation
 *
 * <p>2a. If you are using Vertex AI, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * <p>Then set Project, Location, and USE_VERTEXAI flag as environment variables:
 *
 * <p>export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * <p>export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * <p>export GOOGLE_GENAI_USE_VERTEXAI=true
 *
 * <p>2b. If you are using Google AI, set an API key environment variable. You can find a list of
 * available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>export GOOGLE_GENAI_USE_VERTEXAI=false
 *
 * <p>3. Compile the java package in the directory: ".../third_party/java/google_genai", then run
 * the sample code.
 *
 * <p>mvn clean compile
 *
 * <p>mvn exec:java -Dexec.mainClass="com.google.genai.examples.Gemini2Examples"
 */
package com.google.genai.examples;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.HttpOptions;
import com.google.genai.ResponseStream;
import com.google.genai.types.Content;
import com.google.genai.types.FileData;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.Part;
import com.google.genai.types.Tool;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpException;

/** Sample class for Gemini 2 functionalities. */
public final class Gemini2Examples {

  public static void main(String[] args) throws IOException, HttpException {
    String apiKey = System.getenv("GOOGLE_API_KEY");
    String project = System.getenv("GOOGLE_CLOUD_PROJECT");
    String location = System.getenv("GOOGLE_CLOUD_LOCATION");

    String model = "gemini-2.0-flash-exp";

    Client mldevClient = Client.builder().setApiKey(apiKey).build();
    Client vertexClient =
        Client.builder().setVertexAI(true).setProject(project).setLocation(location).build();

    // Generate content with text.
    System.out.println("=== Generate content (using mldev API) with text response:");
    GenerateContentResponse response =
        generateContentWithTextSample(mldevClient, model, "Tell me a story in 100 words");
    System.out.println(response.text() + "\n\n");

    System.out.println("=== Generate content (using vertex API) with text response:");
    response = generateContentWithTextSample(vertexClient, model, "Tell me a story in 100 words");
    System.out.println(response.text() + "\n\n");

    // Generate content stream with image.
    // Only run for Vertex AI since Google AI doesn't have a stable image uri.
    System.out.println("=== Generate content stream (using vertex API) with image response:");
    ResponseStream<GenerateContentResponse> responseStream =
        generateContentStreamWithImageSample(
            vertexClient,
            model,
            "describe this image",
            "gs://generativeai-downloads/images/scones.jpg");
    for (GenerateContentResponse res : responseStream) {
      System.out.print(res.text());
    }
    System.out.println("\n\n");
    responseStream.close();

    // Generate content with function calling.
    System.out.println("=== Generate content (using mldev API) with function calling response:");
    response =
        generateContentWithFunctionCallingSample(
            mldevClient, model, "What is the weather in Vancouver?");
    System.out.println(response.functionCalls() + "\n\n");

    System.out.println("=== Generate content (using vertex API) with function calling response:");
    response =
        generateContentWithFunctionCallingSample(
            vertexClient, model, "What is the weather in Vancouver?");
    System.out.println(response.functionCalls() + "\n\n");

    // Generate content with search.
    System.out.println("=== Generate content (using mldev API) with search response:");
    response = generateContentWithSearchSample(mldevClient, model, "Why is the sky blue?");
    System.out.println(response.text() + "\n\n");

    System.out.println("=== Generate content (using vertex API) with search response:");
    response = generateContentWithSearchSample(vertexClient, model, "Why is the sky blue?");
    System.out.println(response.text() + "\n\n");

    // Generate content with safety settings.
    System.out.println("=== Generate content (using mldev API) with safety settings response:");
    response =
        generateContentWithSaftySettingsSample(mldevClient, model, "Tell me a story about guns");
    System.out.println(response.text() + "\n\n");

    System.out.println("=== Generate content (using vertex API) with safety settings response:");
    response =
        generateContentWithSaftySettingsSample(vertexClient, model, "Tell me a story about guns");
    System.out.println(response.text() + "\n\n");

    // Generate content stream with system instruction.
    System.out.println(
        "=== Generate content stream (using mldev API) with system instruction response:");
    responseStream =
        generateContentStreamWithSystemInstructionSample(
            mldevClient, model, "Tell me the history of the world");
    for (GenerateContentResponse res : responseStream) {
      System.out.println(res.text());
    }
    System.out.println("\n\n");
    responseStream.close();

    System.out.println("=== Generate content (using vertex API) with system instruction response:");
    responseStream =
        generateContentStreamWithSystemInstructionSample(
            vertexClient, model, "Tell me the history of the world");
    for (GenerateContentResponse res : responseStream) {
      System.out.println(res.text());
    }
    System.out.println("\n\n");
    responseStream.close();

    // Generate content with response modality audio.
    Client vertexStagingClient =
        Client.builder()
            .setVertexAI(true)
            .setProject(project)
            .setLocation(location)
            .setHttpOptions(
                HttpOptions.builder()
                    .setBaseUrl("https://us-central1-staging-aiplatform.sandbox.googleapis.com/")
                    .build())
            .build();
    System.out.println("=== Generate content (using vertex staging API) with audio response:");
    response =
        generateContentWithResponseModalitiesSample(
            vertexStagingClient,
            model,
            "Generate a 2 seconds classical music",
            ImmutableList.of("AUDIO"));
    System.out.println(response.toJson() + "\n\n");

    // Generate content with response modality image.
    System.out.println("=== Generate content (using vertex staging API) with image response:");
    response =
        generateContentWithResponseModalitiesSample(
            vertexStagingClient, model, "Generate a cat image", ImmutableList.of("TEXT", "IMAGE"));
    System.out.println(response.toJson() + "\n\n");
  }

  /** Sample code to generate content with text. */
  public static GenerateContentResponse generateContentWithTextSample(
      Client client, String model, String text) throws IOException, HttpException {
    return client.models.generateContent(model, text, null);
  }

  /** Sample code to generate content stream with text and image. */
  public static ResponseStream<GenerateContentResponse> generateContentStreamWithImageSample(
      Client client, String model, String text, String imageUri) throws IOException, HttpException {
    Content content =
        Content.builder()
            .setRole("user")
            .setParts(
                ImmutableList.of(
                    Part.builder().setText(text).build(),
                    Part.builder()
                        .setFileData(
                            FileData.builder()
                                .setMimeType("image/jpeg")
                                .setFileUri(imageUri)
                                .build())
                        .build()))
            .build();

    return client.models.generateContentStream(model, content, null);
  }

  /** Sample code to generate content with response modalities. */
  public static GenerateContentResponse generateContentWithResponseModalitiesSample(
      Client client, String model, String text, List<String> responseModality)
      throws IOException, HttpException {
    GenerateContentConfig config =
        GenerateContentConfig.builder().setResponseModalities(responseModality).build();

    return client.models.generateContent(model, text, config);
  }

  /** Sample code to generate content with function calling. */
  public static GenerateContentResponse generateContentWithFunctionCallingSample(
      Client client, String model, String text) throws IOException, HttpException {
    FunctionDeclaration functionDeclaration =
        FunctionDeclaration.fromJson(
            "{\"name\":\"get_current_weather\",\"parameters\":{\"type\":\"object\","
                + "\"properties\":{\"location\":{\"type\":\"string\",\"description\":"
                + " \"The location to get the weather for.\"}}}}");
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .setTools(
                ImmutableList.of(
                    Tool.builder()
                        .setFunctionDeclarations(ImmutableList.of(functionDeclaration))
                        .build()))
            .build();

    return client.models.generateContent(model, text, config);
  }

  /** Sample code to generate content with search. */
  public static GenerateContentResponse generateContentWithSearchSample(
      Client client, String model, String text) throws IOException, HttpException {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .setTools(
                ImmutableList.of(
                    Tool.builder().setGoogleSearch(GoogleSearch.builder().build()).build()))
            .build();

    return client.models.generateContent(model, text, config);
  }

  /** Sample code to generate content with safety settings. */
  public static GenerateContentResponse generateContentWithSaftySettingsSample(
      Client client, String model, String text) throws IOException, HttpException {
    GenerateContentConfig config =
        GenerateContentConfig.fromJson(
            "{\"safetySettings\":["
                + "{\"category\":\"HARM_CATEGORY_HATE_SPEECH\",\"threshold\":\"BLOCK_ONLY_HIGH\"},"
                + "{\"category\":\"HARM_CATEGORY_DANGEROUS_CONTENT\",\"threshold\":\"BLOCK_LOW_AND_ABOVE\"}"
                + "]}");

    return client.models.generateContent(model, text, config);
  }

  /** Sample code to generate content stream with system instruction. */
  public static ResponseStream<GenerateContentResponse>
      generateContentStreamWithSystemInstructionSample(Client client, String model, String text)
          throws IOException, HttpException {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .setSystemInstruction(
                Content.fromJson("{\"parts\":[{\"text\":\"Answer as concisely as possible\"}]}"))
            .build();

    return client.models.generateContentStream(model, text, config);
  }
}
