# Google Gen AI Java SDK

Java idiomatic SDK for the
[Gemini Developer APIs][gemini-api-doc] and [Vertex AI][vertex-api-doc] APIs.

[![Maven][maven-version-image]][maven-version-link]
[![Javadoc][javadoc-image]][javadoc-link]

## Add dependency

If you're using Maven, add the following to your dependencies:

[//]: # ({x-version-update-start:google-genai:released})
```xml
<dependencies>
  <dependency>
    <groupId>com.google.genai</groupId>
    <artifactId>google-genai</artifactId>
    <version>1.27.0</version>
  </dependency>
</dependencies>
```
[//]: # ({x-version-update-end})

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

##### Using project and location

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

##### Using API key on Vertex AI (GCP Express Mode)

```java
import com.google.genai.Client;

// Explicitly set the `apiKey` and `vertexAI(true)` to use Vertex AI backend
// in express mode.
Client client = Client.builder()
  .apiKey("your-api-key")
  .vertexAI(true)
  .build();
```

#### (Optional) Using environment variables:

You can create a client by configuring the necessary environment variables.
Configuration setup instructions depends on whether you're using the Gemini
Developer API or the Gemini API in Vertex AI.

**Gemini Developer API:** Set the `GOOGLE_API_KEY`. It will automatically be
picked up by the client. Note that `GEMINI_API_KEY` is a legacy environment
variable, it's recommended to use `GOOGLE_API_KEY` only. But if both are set,
`GOOGLE_API_KEY` takes precedence.

```bash
export GOOGLE_API_KEY='your-api-key'
```

**Gemini API on Vertex AI:** Set `GOOGLE_GENAI_USE_VERTEXAI`,
`GOOGLE_CLOUD_PROJECT` and `GOOGLE_CLOUD_LOCATION`, or `GOOGLE_API_KEY` for
Vertex AI express mode. It's recommended that you set only project & location,
or API key. But if both are set, project & location takes precedence.

```bash
export GOOGLE_GENAI_USE_VERTEXAI=true

// Set project and location for Vertex AI authentication
export GOOGLE_CLOUD_PROJECT='your-project-id'
export GOOGLE_CLOUD_LOCATION='us-central1'
// or API key for express mode
export GOOGLE_API_KEY='your-api-key'
```

After configuring the environment variables, you can instantiate a client
without passing any variables.

```java
import com.google.genai.Client;

Client client = new Client();
```

### API Selection

By default, the SDK uses the beta API endpoints provided by Google to support
preview features in the APIs. The stable API endpoints can be selected by
setting the API version to `v1`.

To set the API version use `HttpOptions`. For example, to set the API version to
`v1` for Vertex AI:

```java
import com.google.genai.Client;
import com.google.genai.types.HttpOptions;

Client client = Client.builder()
  .project("your-project")
  .location("your-location")
  .vertexAI(true)
  .httpOptions(HttpOptions.builder().apiVersion("v1"))
  .build();
```

To set the API version to `v1alpha` for the Gemini Developer API:

```java
import com.google.genai.Client;
import com.google.genai.types.HttpOptions;

Client client = Client.builder()
  .apiKey("your-api-key")
  .httpOptions(HttpOptions.builder().apiVersion("v1alpha"))
  .build();
```

### HttpOptions

Besides `apiVersion`, [HttpOptions](https://github.com/googleapis/java-genai/blob/main/src/main/java/com/google/genai/types/HttpOptions.java)
also allows for flexible customization of HTTP request parameters such as
`baseUrl`, `headers`, and `timeout`:

```java
HttpOptions httpOptions = HttpOptions.builder()
  .baseUrl("your-own-endpoint.com")
  .headers(ImmutableMap.of("key", "value"))
  .timeout(600)
  .build();
```

Beyond client-level configuration, `HttpOptions` can also be set on a
per-request basis, providing maximum flexibility for diverse API call settings.
See [this example](https://github.com/googleapis/java-genai/blob/main/examples/src/main/java/com/google/genai/examples/RequestLevelHttpOptions.java)
for more details.

### HttpRetryOptions

[HttpRetryOptions](https://github.com/googleapis/java-genai/blob/main/src/main/java/com/google/genai/types/HttpRetryOptions.java)
allows you to configure the automatic retry behavior for failed API calls. You
can customize key settings like:

  * Total number of attempts.
  * Which HTTP status codes should trigger a retry (e.g., 429 for rate limits).
  * Backoff strategy, including the initial delay and maximum delay between retries.

```java
HttpOptions httpOptions = HttpOptions.builder()
  .retryOptions(
      HttpRetryOptions.builder()
          .attempts(3)
          .httpStatusCodes(408, 429))
  .build();
```

Since HttpRetryOptions is part of HttpOptions, it supports being set at the
client level (as shown) or on a per-request basis. Note that Providing
`HttpRetryOptions` for a specific request will completely override any default
retry settings configured on the client.

### ClientOptions
[ClientOptions](https://github.com/googleapis/java-genai/blob/main/src/main/java/com/google/genai/types/ClientOptions.java)
enables you to customize the behavior of the HTTP client. It currently supports
configuring the connection pool via `maxConnections` (total maximum connections)
and `maxConnectionsPerHost` (maximum connections to a single host).

```java
import com.google.genai.Client;
import com.google.genai.types.ClientOptions;

Client client = Client.builder()
  .apiKey("your-api-key")
  .clientOptions(ClientOptions.builder().maxConnections(64).maxConnectionsPerHost(16))
  .build();
```

### Interact with models
The Google Gen AI Java SDK allows you to access the service programmatically.
The following code snippets are some basic usages of model inferencing.

#### Generate Content
Use `generateContent` method for the most basic content generation.

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
        client.models.generateContent("gemini-2.5-flash", "What is your name?", null);

    // Gets the text string from the response by the quick accessor method `text()`.
    System.out.println("Unary response: " + response.text());

    // Gets the http headers from the response.
    response
        .sdkHttpResponse()
        .ifPresent(
            httpResponse ->
                System.out.println("Response headers: " + httpResponse.headers().orElse(null)));
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
        client.models.generateContent("gemini-2.5-flash", content, null);

    System.out.println("Response: " + response.text());
  }
}
```

##### Generate Content with extra configs
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
import com.google.genai.types.ThinkingConfig;
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
    Tool googleSearchTool = Tool.builder().googleSearch(GoogleSearch.builder()).build();

    GenerateContentConfig config =
        GenerateContentConfig.builder()
            // Sets the thinking budget to 0 to disable thinking mode
            .thinkingConfig(ThinkingConfig.builder().thinkingBudget(0))
            .candidateCount(1)
            .maxOutputTokens(1024)
            .safetySettings(safetySettings)
            .systemInstruction(systemInstruction)
            .tools(googleSearchTool)
            .build();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.5-flash", "Tell me the history of LLM", config);

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

    // Load the method as a reflected Method object so that it can be
    // automatically executed on the client side.
    Method method =
        GenerateContentWithFunctionCall.class.getMethod(
            "getCurrentWeather", String.class, String.class);

    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(Tool.builder().functions(method))
            .build();

    GenerateContentResponse response =
        client.models.generateContent(
            "gemini-2.5-flash",
            "What is the weather in Vancouver?",
            config);

    System.out.println("The response is: " + response.text());
    System.out.println(
        "The automatic function calling history is: "
            + response.automaticFunctionCallingHistory().get());
  }
}
```

##### Stream Generated Content
To get a streamed response, you can use the `generateContentStream` method:

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;

public class StreamGeneration {
  public static void main(String[] args) {
    Client client = new Client();

    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            "gemini-2.5-flash", "Tell me a story in 300 words.", null);

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

##### Async Generate Content
To get a response asynchronously, you can use the `generateContent` method from
the `client.async.models` namespace.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.util.concurrent.CompletableFuture;

public class GenerateContentAsync {
  public static void main(String[] args) {
    Client client = new Client();

    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            "gemini-2.5-flash", "Introduce Google AI Studio.", null);

    responseFuture
        .thenAccept(
            response -> {
              System.out.println("Async response: " + response.text());
            })
        .join();
  }
}
```

##### Generate Content with JSON response schema
To get a response in JSON by passing in a response schema to the
`GenerateContent` API.

```java
package <your package name>;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;
import com.google.genai.types.Type;

public class GenerateContentWithSchema {
  public static void main(String[] args) {
    Client client = new Client();

    // Define the schema for the response, in Json format.
    ImmutableMap<String, Object> schema = ImmutableMap.of(
        "type", "object",
        "properties", ImmutableMap.of(
            "recipe_name", ImmutableMap.of("type", "string"),
            "ingredients", ImmutableMap.of(
                "type", "array",
                "items", ImmutableMap.of("type", "string")
            )
        ),
        "required", ImmutableList.of("recipe_name", "ingredients")
    );

    // Set the response schema in GenerateContentConfig
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .responseMimeType("application/json")
            .candidateCount(1)
            .responseSchema(schema)
            .build();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.5-flash", "Tell me your name", config);

    System.out.println("Response: " + response.text());
  }
}
```

#### Count Tokens and Compute Tokens

The `countTokens` method allows you to calculate the number of tokens your
prompt will use before sending it to the model, helping you manage costs and
stay within the context window.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.CountTokensResponse;

public class CountTokens {
  public static void main(String[] args) {
    Client client = new Client();

    CountTokensResponse response =
        client.models.countTokens("gemini-2.5-flash", "What is your name?", null);

    System.out.println("Count tokens response: " + response);
  }
}
```

The `computeTokens` method returns the Tokens Info that contains tokens and
token IDs given your prompt. This method is only supported in Vertex AI.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.ComputeTokensResponse;

public class ComputeTokens {
  public static void main(String[] args) {
    Client client = Client.builder().vertexAI(true).build();

    ComputeTokensResponse response =
        client.models.computeTokens("gemini-2.5-flash", "What is your name?", null);

    System.out.println("Compute tokens response: " + response);
  }
}
```

#### Embed Content

The `embedContent` method allows you to generate embeddings for words, phrases,
sentences, and code. Note that only text embedding is supported in this method.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.EmbedContentResponse;

public class EmbedContent {
  public static void main(String[] args) {
    Client client = new Client();

    EmbedContentResponse response =
        client.models.embedContent("gemini-embedding-001", "why is the sky blue?", null);

    System.out.println("Embedding response: " + response);
  }
}
```

### Imagen

Imagen is a text-to-image GenAI service.

#### Generate Images

The `generateImages` method helps you create high-quality, unique images given a
text prompt.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.Image;

public class GenerateImages {
  public static void main(String[] args) {
    Client client = new Client();

    GenerateImagesConfig config =
        GenerateImagesConfig.builder()
            .numberOfImages(1)
            .outputMimeType("image/jpeg")
            .includeSafetyAttributes(true)
            .build();

    GenerateImagesResponse response =
        client.models.generateImages(
            "imagen-3.0-generate-002", "Robot holding a red skateboard", config);

    if (generatedImagesResponse.images().isEmpty()) {
      System.out.println("Unable to generate images.");
    }
    System.out.println("Generated " + generatedImagesResponse.images().size() + " images.");
    Image generatedImage = generatedImagesResponse.images().get(0);
  }
}
```

#### Upscale Image

The `upscaleImage` method allows you to upscale an image. This feature is only
supported in Vertex AI.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.Image;
import com.google.genai.types.UpscaleImageConfig;
import com.google.genai.types.UpscaleImageResponse;

public class UpscaleImage {
  public static void main(String[] args) {
    Client client = Client.builder().vertexAI(true).build();

    Image image = Image.fromFile("path/to/your/image");

    UpscaleImageConfig config =
        UpscaleImageConfig.builder()
            .outputMimeType("image/jpeg")
            .enhanceInputImage(true)
            .imagePreservationFactor(0.6f)
            .build();

    UpscaleImageResponse response =
        client.models.upscaleImage("imagen-3.0-generate-002", image, "x2", config);

    response.generatedImages().ifPresent(
        images -> {
            Image upscaledImage = images.get(0).image().orElse(null);
            // Do something with the upscaled image.
        }
    );
  }
}
```

#### Edit Image

The `editImage` method lets you edit an image. You can input reference images
(ex. mask reference for inpainting, or style reference for style transfer) in
addition to a text prompt to guide the editing.

This feature uses a different model than `generateImages` and `upscaleImage`. It
is only supported in Vertex AI.

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.EditImageConfig;
import com.google.genai.types.EditImageResponse;
import com.google.genai.types.EditMode;
import com.google.genai.types.Image;
import com.google.genai.types.MaskReferenceConfig;
import com.google.genai.types.MaskReferenceImage;
import com.google.genai.types.MaskReferenceMode;
import com.google.genai.types.RawReferenceImage;
import com.google.genai.types.ReferenceImage;
import java.util.ArrayList;

public class EditImage {
  public static void main(String[] args) {
    Client client = Client.builder().vertexAI(true).build();

    Image image = Image.fromFile("path/to/your/image");

    // Edit image with a mask.
    EditImageConfig config =
        EditImageConfig.builder()
            .editMode(EditMode.Known.EDIT_MODE_INPAINT_INSERTION)
            .numberOfImages(1)
            .outputMimeType("image/jpeg")
            .build();

    ArrayList<ReferenceImage> referenceImages = new ArrayList<>();
    RawReferenceImage rawReferenceImage =
        RawReferenceImage.builder().referenceImage(image).referenceId(1).build();
    referenceImages.add(rawReferenceImage);

    MaskReferenceImage maskReferenceImage =
        MaskReferenceImage.builder()
            .referenceId(2)
            .config(
                MaskReferenceConfig.builder()
                    .maskMode(MaskReferenceMode.Known.MASK_MODE_BACKGROUND)
                    .maskDilation(0.0f))
            .build();
    referenceImages.add(maskReferenceImage);

    EditImageResponse response =
        client.models.editImage(
            "imagen-3.0-capability-001", "Sunlight and clear sky", referenceImages, config);

    response.generatedImages().ifPresent(
        images -> {
            Image editedImage = images.get(0).image().orElse(null);
            // Do something with the edited image.
        }
    );
  }
}
```

### Veo

Veo is a video generation GenAI service.

#### Generate Videos (Text to Video)

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.Video;

public class GenerateVideosWithText {
  public static void main(String[] args) {
    Client client = new Client();

    GenerateVideosConfig config =
        GenerateVideosConfig.builder()
            .numberOfVideos(1)
            .enhancePrompt(true)
            .durationSeconds(5)
            .build();

    // generateVideos returns an operation
    GenerateVideosOperation operation =
        client.models.generateVideos(
            "veo-2.0-generate-001", "A neon hologram of a cat driving at top speed", null, config);

    // When the operation hasn't been finished, operation.done() is empty
    while (!operation.done().isPresent()) {
        try {
            System.out.println("Waiting for operation to complete...");
            Thread.sleep(10000);
            // Sleep for 10 seconds and check the operation again
            operation = client.operations.getVideosOperation(operation, null);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted while sleeping.");
            Thread.currentThread().interrupt();
        }
    }

    operation.response().ifPresent(
        response -> {
            response.generatedVideos().ifPresent(
                videos -> {
                    System.out.println("Generated " + videos.size() + " videos.");
                    Video video = videos.get(0).video().orElse(null);
                    // Do something with the generated video
                }
            );
        }
    );
  }
}
```

#### Generate Videos (Image to Video)

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.Image;
import com.google.genai.types.Video;

public class GenerateVideosWithImage {
  public static void main(String[] args) {
    Client client = new Client();

    Image image = Image.fromFile("path/to/your/image");

    GenerateVideosConfig config =
        GenerateVideosConfig.builder()
            .numberOfVideos(1)
            .enhancePrompt(true)
            .durationSeconds(5)
            .build();

    // generateVideos returns an operation
    GenerateVideosOperation operation =
        client.models.generateVideos(
            "veo-2.0-generate-001",
            "Night sky",
            image,
            config);

    // When the operation hasn't been finished, operation.done() is empty
    while (!operation.done().isPresent()) {
        try {
            System.out.println("Waiting for operation to complete...");
            Thread.sleep(10000);
            // Sleep for 10 seconds and check the operation again
            operation = client.operations.getVideosOperation(operation, null);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted while sleeping.");
            Thread.currentThread().interrupt();
        }
    }

    operation.response().ifPresent(
        response -> {
            response.generatedVideos().ifPresent(
                videos -> {
                    System.out.println("Generated " + videos.size() + " videos.");
                    Video video = videos.get(0).video().orElse(null);
                    // Do something with the generated video
                }
            );
        }
    );
  }
}
```

### Files API

Gemini models support various input data types, including text, images, and
audio. The Files API allows you to upload and manage these media files for use
with Gemini models. **This feature is exclusively supported by the Gemini API**.

#### Usage info
You can use the Files API to upload and interact with media files. The Files API
lets you store up to 20 GB of files per project, with a per-file maximum size of
2 GB. Files are stored for 48 hours. During that time, you can use the API to
get metadata about the files, but you can't download the files. The Files API is
available at no cost in all regions where the Gemini API is available.

The basic operations are:

1. **Upload**: You can use the Files API to upload a media file. Always use
the Files API when the total request size (including the files, text prompt,
system instructions, etc.) is larger than 20 MB.

2. **Get**: You can verify that the API successfully stored the uploaded file
and get its metadata.

3. **List**: You can upload multiple files using the Files API. The following
code gets a list of all the files uploaded.

4. **Delete**: Files are automatically deleted after 48 hours. You can also
manually delete an uploaded file:

#### Sample usage

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.Content;
import com.google.genai.types.DeleteFileResponse;
import com.google.genai.types.File;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.ListFilesConfig;
import com.google.genai.types.Part;
import com.google.genai.types.UploadFileConfig;

/** An example of how to use the Files module to upload, retrieve, list, and delete files. */
public final class FileOperations {
  public static void main(String[] args) {
    Client client = new Client();

    // Upload a file to the API.
    try {
      File file =
          client.files.upload(
              "path/to/your/file.pdf",
              UploadFileConfig.builder().mimeType("application/pdf").build());

      // Use the uploaded file in the generateContent
      Content content =
          Content.fromParts(
              Part.fromText("Summary this pdf."),
              Part.fromUri(file.name().get(), file.mimeType().get()));
      GenerateContentResponse response =
          client.models.generateContent("gemini-2.5-flash", content, null);

      // Get the uploaded file.
      File retrievedFile = client.files.get(file.name().get(), null);

      // List all files.
      System.out.println("List files: ");
      for (File f : client.files.list(ListFilesConfig.builder().pageSize(10).build())) {
        System.out.println("File name: " + f.name().get());
      }

      // Delete the uploaded file.
      client.files.delete(file.name().get(), null);

    } catch (GenAiIOException e) {
      System.out.println("An error occurred while uploading the file: " + e.getMessage());
    }
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

