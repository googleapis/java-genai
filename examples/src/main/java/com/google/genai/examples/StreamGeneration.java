package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import org.apache.http.HttpException;

/**
 * An example of using the Unified Gen AI Java SDK to generate stream of content.
 *
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.StreamGeneration"
 */
public class StreamGeneration {
  public static void main(String[] args) throws IOException, HttpException {
    Client geminiAPIClient = Client.builder().setVertexAI(false).build();
    Client vertexClient = Client.builder().setVertexAI(true).build();

    System.out.println("VertexAI responded: ");
    ResponseStream<GenerateContentResponse> vertexResponseStream =
        vertexClient.models.generateContentStream(
            "gemini-2.0-flash-exp", "Tell me a story in 300 words.", null);
    for (GenerateContentResponse res : vertexResponseStream) {
      System.out.print(res.text().get());
    }
    System.out.println("Gemini Dev API responded: ");
    ResponseStream<GenerateContentResponse> geminiAPIResponse =
        geminiAPIClient.models.generateContentStream(
            "gemini-2.0-flash-exp", "Tell me a story in 300 words.", null);
    for (GenerateContentResponse res : geminiAPIResponse) {
      System.out.print(res.text().get());
    }
  }
}
