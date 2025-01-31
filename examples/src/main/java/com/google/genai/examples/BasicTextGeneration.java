package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import java.io.IOException;
import org.apache.http.HttpException;

/**
 * An example of using the Unified Gen AI Java SDK to generate basic text.
 *
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.BasicTextGeneration"
 */
public class BasicTextGeneration {
  public static void main(String[] args) throws IOException, HttpException {
    Client geminiAPIClient = Client.builder().setVertexAI(false).build();
    Client vertexClient = Client.builder().setVertexAI(true).build();

    GenerateContentResponse vertexResponse =
        vertexClient.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);
    System.out.println("VertexAI responded: " + vertexResponse.text().get());
    GenerateContentResponse geminiAPIResponse =
        geminiAPIClient.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);
    System.out.println("Gemini Dev API responded: " + geminiAPIResponse.text().get());
  }
}
