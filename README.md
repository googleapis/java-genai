# Google Gen AI Java SDK

Java idiomatic SDK for the
[Gemini Developer APIs][gemini-api-doc] and [Vertex AI][vertex-api-doc] APIs.

[![Maven][maven-version-image]][maven-version-link]

This is an early release. API is subject to change. Please do not use this SDK
in production environments at this stage.

## Add dependency

If you're using Maven, add the following to your dependencies:

<!-- {x-version-update-start:google-genai:released} -->

```xml
<dependencies>
  <dependency>
    <groupId>com.google.genai</groupId>
    <artifactId>google-genai</artifactId>
    <version>0.2.0</version> <!-- {x-version-update:google-genai:released} -->
  </dependency>
</dependencies>
```

<!-- {x-version-update-end} -->

## Getting Started

Follow the instructions in this section to get started using the Google Gen AI
SDK for Java.

You can either set the following environment variables or pass them to the
client builder explicitly:

```bash
GOOGLE_API_KEY=${GEMINI_API_KEY}      # Required to call Gemini APIs
GOOGLE_CLOUD_PROJECT=${PROJECT_NAME}  # Required to call Vertex AI APIs
GOOGLE_CLOUD_LOCATION=${LOCATION}     # Required to call Vertex AI APIs
```

### Create a client
The Google Gen AI Java SDK provides a Client class, simplifying interaction
with both the Gemini API and Vertex AI API. With minimal configuration,
you can seamlessly switch between the 2 backends without rewriting
your code.

#### Instantiate a client that uses Gemini API
```java
import com.google.genai.Client;

// The simplest way for instantiation. The client gets the API key from the
// environment variable `GOOGLE_API_KEY` and use Gemini API when the environment
// variable `GOOGLE_GENAI_USE_VERTEXAI` is not set or set to `false`.
Client client = new Client();

// Use Builder class for instantiation. Explicitly set the API key to use Gemini
// Developer backend.
Client client = Client.builder().apiKey("your-api-key").build();
```

#### Instantiate a client that uses Vertex AI API
```java
import com.google.genai.Client;

// The client gets the project and location from the environment variable
// `GOOGLE_CLOUD_PROJECT` and `GOOGLE_CLOUD_LOCATION`. It uses Vertex AI APIs
// when the environment variable `GOOGLE_GENAI_USE_VERTEXAI` is set to `true`.
Client client = new Client();

// Use Builder class for instantiation. Explicitly set the project and location,
// and set `vertexAI(true)` to use Vertex AI backend.
Client client = Client.builder()
  .project("your-project")
  .location("your-location")
  .vertexAI(true)
  .build();
```

### Interact with models
The Gen AI Java SDK allows you to access the service programmatically.
The following code snippets are some basic usages of model inferencing.

#### Generate Content
Use `generateContent` method for the most basic text generation.

##### with text input

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import org.apache.http.HttpException;

public class GenerateContentWithTextInput {
  public static void main(String[] args) throws IOException, HttpException {
    // Instantiate the client. The client by default uses the Gemini API. It gets
    // the API key from the environment variable `GOOGLE_API_KEY`.
    Client client = new Client();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-001", "What is your name?", null);

    // Gets the text string from the response by the quick accessor method `text()`.
    System.out.println("Unary response: " + response.text());
  }
}
```

##### with text and image input

```java
package <your package name>;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.io.IOException;
import org.apache.http.HttpException;

public class GenerateContentWithImageInput {
  public static void main(String[] args) throws IOException, HttpException {
    // Instantiate the client using Vertex API. The client gets the project and location from the
    // environment variables `GOOGLE_CLOUD_PROJECT` and `GOOGLE_CLOUD_LOCATION`.
    Client client = Client.builder().vertexAI(true).build();

    // Construct a multimodal content with quick constructors
    Content content =
        Content.fromParts(
            Part.fromText("describe the image"),
            Part.fromUri("gs://path/to/image.jpg", "image/jpeg"));

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-001", content, null);

    System.out.println("Response: " + response.text());
  }
}
```

#### Stream Generated Content
To get a streamed response, you can use the `generateContentStream` method:

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import org.apache.http.HttpException;

public class StreamGeneration {
  public static void main(String[] args) throws IOException, HttpException {
    // Instantiate the client using Vertex API. The client gets the project and location from the
    // environment variables `GOOGLE_CLOUD_PROJECT` and `GOOGLE_CLOUD_LOCATION`.
    Client client = Client.builder().vertexAI(true).build();

    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            "gemini-2.0-flash-001", "Tell me a story in 300 words.", null);

    System.out.println("Streaming response: ");
    for (GenerateContentResponse res : responseStream) {
      System.out.print(res.text());
    }

    // To save resources and avoid connection leaks, it is recommended to close the response
    // stream after consumption (or using try block to get the response stream).
    responseStream.close();
  }
}
```

#### Async Generate Content
To get a response asynchronously, you can use the `generateContent` method from
the `client.async.models` namespace.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.apache.http.HttpException;

public class GenerateContentAsync {
  public static void main(String[] args) throws IOException, HttpException {
    // Instantiates the client using Gemini API, and sets the API key in the builder.
    Client client = Client.builder().apiKey("your-api-key").build();

    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            "gemini-2.0-flash-001", "Introduce Google AI Studio.", null);

    responseFuture
        .thenAccept(
            response -> {
              System.out.println("Async response: " + response.text());
            })
        .join();
  }
}
```

#### Generate Content with extra configs
To set configurations like System Instructions and Safety Settings, you can pass
a `GenerateContentConfig` to the `GenerateContent` method.

```java
package <your package name>;

import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.Part;
import com.google.genai.types.SafetySetting;
import com.google.genai.types.Tool;
import java.io.IOException;
import org.apache.http.HttpException;

public class GenerateContentWithConfigs {
  public static void main(String[] args) throws IOException, HttpException {
    Client client = new Client();

    // Sets the safety settings in the config.
    ImmutableList<SafetySetting> safetySettings =
        ImmutableList.of(
            SafetySetting.builder()
                .category("HARM_CATEGORY_HATE_SPEECH")
                .threshold("BLOCK_ONLY_HIGH")
                .build(),
            SafetySetting.builder()
                .category("HARM_CATEGORY_DANGEROUS_CONTENT")
                .threshold("BLOCK_LOW_AND_ABOVE")
                .build());

    // Sets the system instruction in the config.
    Content systemInstruction = Content.fromParts(Part.fromText("You are a history teacher."));

    // Sets the Google Search tool in the config.
    Tool googleSearchTool = Tool.builder().googleSearch(GoogleSearch.builder().build()).build();

    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .candidateCount(1)
            .maxOutputTokens(1024)
            .safetySettings(safetySettings)
            .systemInstruction(systemInstruction)
            .tools(ImmutableList.of(googleSearchTool))
            .build();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-001", "Tell me the history of LLM", config);

    System.out.println("Response: " + response.text());
  }
}
```

#### Generate Content with JSON response schema
To get a response in JSON by passing in a response schema to the
`GenerateContent` API.

```java
package <your package name>;

import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;
import java.io.IOException;
import org.apache.http.HttpException;

public class GenerateContentWithSchema {
  public static void main(String[] args) throws IOException, HttpException {
    Client client = new Client();

    Schema schema =
        Schema.builder()
            .type("object")
            .properties(
                ImmutableMap.of(
                    "name", Schema.builder().type("string").description("Your Name").build()))
            .build();
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .responseMimeType("application/json")
            .candidateCount(1)
            .responseSchema(schema)
            .build();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-001", "Tell me your name", config);

    System.out.println("Response: " + response.text());
  }
}
```

## Versioning

This library follows [Semantic Versioning](http://semver.org/).

## Contribute to this library

The Google Gen AI Java SDK will accept contributions in the future.

## License

Apache 2.0 - See [LICENSE][license] for more information.

[gemini-api-doc]: https://ai.google.dev/gemini-api/docs
[vertex-api-doc]: https://cloud.google.com/vertex-ai/generative-ai/docs/learn/overview
[maven-version-image]: https://img.shields.io/maven-central/v/com.google.genai/google-genai.svg
[maven-version-link]: https://central.sonatype.com/artifact/com.google.genai/google-genai/0.1.0
[license]: https://github.com/googleapis/java-genai/blob/main/LICENSE

