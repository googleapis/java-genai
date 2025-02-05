# Google Unified Gen AI Java SDK

Java idiomatic SDK for the
[Gemini Developer API](https://ai.google.dev/gemini-api/docs) and
[Vertex AI](https://cloud.google.com/vertex-ai/generative-ai/docs/learn/overview)
APIs.

![Maven][maven-version-image][maven-version-link]
![Stability][stability-image]

## Add dependency

If you're using Maven, add the following to your dependencies:

<!-- {x-version-update-start:genai-client:released} -->

```xml
<dependencies>
  <dependency>
    <groupId>com.google.genai</groupId>
    <artifactId>genai-client</artifactId>
    <version>0.1.0</version>
  </dependency>
</dependencies>
```

<!-- {x-version-update-end} -->

## Getting Started

Follow the instructions in this section to get started using the Unified Gen AI
SDK for Java.

You can either set the following environment variables or pass them to the
client builder explicitly:

```bash
GOOGLE_API_KEY=${GEMINI_API_KEY}      # Required to call Gemini APIs
GOOGLE_CLOUD_PROJECT=${PROJECT_NAME}  # Required to call Vertex AI APIs
GOOGLE_CLOUD_LOCATION=${LOCATION}     # Required to call Vertex AI APIs
```

### Call Vertex AI or Gemini API using the same client
By default, the client uses the Gemini Developer API. To use Vertex, set
`VertexAI=true`.

```java
Client client = Client.builder()
  .setAPIKey(System.getenv("GOOGLE_API_KEY"))
  .build();
```

```java
Client client = Client.builder()
  .setVertexAI(true)
  .setProject(System.getenv("GOOGLE_CLOUD_PROJECT"))
  .setLocation(System.getenv("GOOGLE_CLOUD_LOCATION"))
  .build();
```

#### Basic Text Generation
Vertex AI SDK allows you to access the service programmatically. The following
code snippet is the most basic usage of SDK.

```java
package <your package name>;

import com.google.genai.Client;
import java.io.IOException;
import org.apache.http.HttpException;

public class BasicTextGeneration {
  public static void main(String[] args) throws IOException, HttpException {
    Client client = Client.builder().setVertexAI(true).build();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash", "What is your name?", null);
    System.out.println(response.text());
  }
}
```

#### Stream Generated Output

To get a streamed output, you can use the `generateContentStream` method:

```java
package <your package name>;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import org.apache.http.HttpException;

public class StreamGeneration {
  public static void main(String[] args) throws IOException, HttpException {
    Client client = Client.builder().setVertexAI(true).build();

    ResponseStream<GenerateContentResponse> response =
        client.models.generateContentStream(
            "gemini-2.0-flash", "Tell me a story in 300 words.", null);
    for (GenerateContentResponse res : response) {
      System.out.print(res.text());
    }
  }
}
```

## Versioning

This library follows [Semantic Versioning](http://semver.org/).

## Contribute to this library

The Google Gen AI SDK is will accept contributions in the future.

## License

Apache 2.0 - See [LICENSE][license] for more information.
