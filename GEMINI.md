---
trigger: always_on
description: Read this file when working on the GenAI Java SDK
---

> [!IMPORTANT] When working in this directory, you must also read the
> generator's context file at `google3/google/cloud/aiplatform/sdk/GEMINI.md`.

# Java SDK for Google GenAI

This directory contains the Java SDK for the Google GenAI project. It is designed to provide a Java interface for interacting with Gemini models, supporting both the Gemini Developer API (MLDev) and Gemini Enterprise Agent Platform API (Vertex AI).

## Project Overview

The Java SDK is largely auto-generated based on API discovery documents. However, it also includes hand-written core infrastructure to support authentication, HTTP communication, and custom serialization needs.

### Relationship to the Central Generator

As described in `google3/google/cloud/aiplatform/sdk/GEMINI.md`, this SDK is produced by the central Python-based multi-language SDK generator located at `//google/cloud/aiplatform/sdk/generator`.
- Language-specific logic for Java is contained in `java_generator.py` and `java_datatypes.py` (in the generator directory).
- The generator produces data classes and API methods based on discovery docs and configuration.
- **Important**: The generator updates files in `src/private` from files in `src/main`. Do NOT edit files in `src/private` directly.

## Architecture and Key Components

### Hand-written Core Files

The following files in `src/main/java/com/google/genai/` are hand-written and form the core infrastructure of the SDK:

- **`Client.java`**: The main entry point for the SDK. It provides access to all services (Models, Chats, Files, etc.) in both synchronous and asynchronous modes. Handles initialization with API keys or credentials.
- **`ApiClient.java`**: An abstract base class for issuing HTTP requests. It handles request building, URL construction, header execution (including auth), and OkHttpClient configuration.
- **`HttpApiClient.java`**: The default concrete implementation of `ApiClient` that uses OkHttp to make real network calls.
- **`ApiResponse.java`**: A simple wrapper around the HTTP response, providing access to status code, headers, and body content.
- **`Common.java`**: Contains utility methods for setting and getting values by path in JSON objects, URL encoding, and other common tasks.
- **`JsonSerializable.java`**: Provides base functionality for JSON serialization and deserialization using Jackson.
- **`Transformers.java`**: Contains static methods to transform parameters and types to match API expectations (e.g., converting model names to resource paths).
- **`ResponseStream.java`**: Manages streaming responses, ensuring SSE compliance and detecting application-level errors.
- **`ReplayApiClient.java`**: A testing-specific implementation of `ApiClient` that reads responses from replay files instead of making network calls.
- **`ReplayApiResponse.java`**: Used with `ReplayApiClient` to represent mocked responses.
- **`Chat.java`, `AsyncChat.java`, `ChatBase.java`**: Manage multi-turn chat sessions and history.
- **`UploadClient.java`**: Handles resumable file uploads to the File API.
- **`GoogleCredentialsHttpClient.java`**: Handles authentication using Google Credentials (typically for Vertex AI).
- **`AfcUtil.java`**: Utilities for Auto-Function Calling.

### Custom Serialization (`CustomDurationDeserializer`)

To handle API response fields that return duration as a string with an 's' suffix (e.g., "3.5s"), the SDK uses a custom deserializer in `JsonSerializable.java`: `CustomDurationDeserializer`.
- This deserializer ensures that fields mapped to `java.time.Duration` in Java can be successfully decoded from JSON.
- It parses the numeric part and creates a `Duration` instance.
- `JsonSerializable` also includes a `CustomDurationSerializer` to output the same format when serializing.

## Development and Testing

### Running Tests

The project uses Maven for building and testing.

To run all unit tests, you must set the environment variable for replay tests:

```bash
export GOOGLE_GENAI_REPLAYS_DIRECTORY="`blaze info workspace`/google/cloud/aiplatform/sdk/genai/replays"
mvn clean test
```

### Running Examples

Examples are located in `examples/`. To run all examples (recommended before release):
```bash
./run_examples.sh
```

## Modifying the SDK

- **For changes to generated code**: Do NOT edit the generated files directly in `src/private`. Modify the generator configuration or templates in `//google/cloud/aiplatform/sdk/` or modify files in `src/main` and then run the generator:
  ```bash
  google/cloud/aiplatform/sdk/generator/run_generator.sh --langs java
  ```
- **For changes to core infrastructure**: Modify the hand-written files in `src/main`.
- **Knowledge Sharing**: Every time you make a code change in this Java GenAI SDK, try to add useful knowledge into this `GEMINI.md` file if it is necessary or helpful for future maintainers (e.g., documenting new hand-written files, custom serialization patterns, or specific workflow gotchas).

## Legacy Names

- Similar to other SDKs, you might still find legacy names like "mldev" (representing Gemini API) or "vertex" (representing Gemini Enterprise Agent Platform API) in generator code and test files.
