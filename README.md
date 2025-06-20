# Google Gen AI Java SDK

Java idiomatic SDK for the
[Gemini Developer APIs][gemini-api-doc] and [Vertex AI][vertex-api-doc] APIs.

[![Maven][maven-version-image]][maven-version-link]
[![Javadoc][javadoc-image]][javadoc-link]

## Add dependency

If you're using Maven, add the following to your dependencies:

<!-- {x-version-start:google-genai:released} -->

```xml
<dependencies>
  <dependency>
    <groupId>com.google.genai</groupId>
    <artifactId>google-genai</artifactId>
    <version>1.5.0</version>
  </dependency>
</dependencies>
```

<!-- {x-version-end} -->

## Getting Started

Follow the instructions in this section to get started using the Google Gen AI
SDK for Java.

### Create a client
The Google Gen AI Java SDK provides a Client class, simplifying interaction
with both the Gemini API and Vertex AI API. With minimal configuration,
you can seamlessly switch between the 2 backends without rewriting
your code.

#### Instantiate a client that uses Gemini API
```java
import com.google.genai.Client;

// Use Builder class for instantiation. Explicitly set the API key to use Gemini
// Developer backend.
Client client = Client.builder().apiKey("your-api-key").build();
```

#### Instantiate a client that uses Vertex AI API
```java
import com.google.genai.Client;

// Use Builder class for instantiation. Explicitly set the project and location,
// and set `vertexAI(true)` to use Vertex AI backend.
Client client = Client.builder()
  .project("your-project")
  .location("your-location")
  .vertexAI(true)
  .build();
```

#### (Optional) Using environment variables:

You can create a client by configuring the necessary environment variables.
Configuration setup instructions depends on whether you're using the Gemini
Developer API or the Gemini API in Vertex AI.

**Gemini Developer API:** Set `GOOGLE_API_KEY` as shown below:

```bash
export GOOGLE_API_KEY='your-api-key'
```

**Gemini API on Vertex AI:** Set `GOOGLE_GENAI_USE_VERTEXAI`,
`GOOGLE_CLOUD_PROJECT` and `GOOGLE_CLOUD_LOCATION`, as shown below:

```bash
export GOOGLE_GENAI_USE_VERTEXAI=true
export GOOGLE_CLOUD_PROJECT='your-project-id'
export GOOGLE_CLOUD_LOCATION='us-central1'
```

```java
import com.google.genai.Client;

Client client = new Client();
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

public class GenerateContentWithTextInput {
  public static void main(String[] args) {
    // Instantiate the client. The client by default uses the Gemini API. It
    //  gets the API key from the environment variable `GOOGLE_API_KEY`.
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

public class GenerateContentWithImageInput {
  public static void main(String[] args) {
    // Instantiate the client using Vertex API. The client gets the project and
    // location from the environment variables `GOOGLE_CLOUD_PROJECT` and
    // `GOOGLE_CLOUD_LOCATION`.
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

##### Automatic function calling with generate content

The Models.generateContent methods supports automatic function calling (AFC). If
the user passes in a list of public static method in the tool list of the
GenerateContentConfig, by default AFC will be enabled with maximum remote calls
to be 10 times. Follow the following steps to experience this feature.

**Step 1**: enable the compiler to parse parameter name of your methods. In your
`pom.xml`, include the following compiler configuration.

```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.14.0</version>
  <configuration>
    <compilerArgs>
      <arg>-parameters</arg>
    </compilerArgs>
  </configuration>
</plugin>
```

**Step 2**: see the following code example to use AFC, pay special attention to
the code line where the `java.lang.reflect.Method` instance was extracted.

```java
import com.google.common.collect.ImmutableList;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Tool;
import java.lang.reflect.Method;

public class GenerateContentWithFunctionCall {
  public static String getCurrentWeather(String location, String unit) {
    return "The weather in " + location + " is " + "very nice.";
  }

  public static void main(String[] args) throws NoSuchMethodException {
    Client client = new Client();

    Method method =
        GenerateContentWithFunctionCall.class.getMethod(
            "getCurrentWeather", String.class, String.class);

    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                ImmutableList.of(
                    Tool.builder().functions(ImmutableList.of(method)).build()))
            .build();

    GenerateContentResponse response =
        client.models.generateContent(
            "gemini-2.0-flash-001",
            "What is the weather in Vancouver?",
            config);

    System.out.println("The response is: " + response.text());
    System.out.println(
        "The automatic function calling history is: "
            + response.automaticFunctionCallingHistory().get());
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

public class StreamGeneration {
  public static void main(String[] args) {
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
import java.util.concurrent.CompletableFuture;

public class GenerateContentAsync {
  public static void main(String[] args) {
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
import com.google.genai.types.HarmBlockThreshold;
import com.google.genai.types.HarmCategory;
import com.google.genai.types.Part;
import com.google.genai.types.SafetySetting;
import com.google.genai.types.Tool;

public class GenerateContentWithConfigs {
  public static void main(String[] args) {
    Client client = new Client();

    // Sets the safety settings in the config.
    ImmutableList<SafetySetting> safetySettings =
        ImmutableList.of(
            SafetySetting.builder()
                .category(HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH)
                .threshold(HarmBlockThreshold.Known.BLOCK_ONLY_HIGH)
                .build(),
            SafetySetting.builder()
                .category(HarmCategory.Known.HARM_CATEGORY_DANGEROUS_CONTENT)
                .threshold(HarmBlockThreshold.Known.BLOCK_LOW_AND_ABOVE)
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
import com.google.genai.types.Type;

public class GenerateContentWithSchema {
  public static void main(String[] args) {
    Client client = new Client();

    Schema schema =
        Schema.builder()
            .type("object")
            .properties(
                ImmutableMap.of(
                    "name", Schema.builder().type(Type.Known.STRING).description("Your Name").build()))
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
[maven-version-link]: https://central.sonatype.com/artifact/com.google.genai/google-genai
[javadoc-image]: https://img.shields.io/badge/JavaDoc-Online-green
[javadoc-link]: https://googleapis.github.io/java-genai/javadoc/
[license]: https://github.com/googleapis/java-genai/blob/main/LICENSE

