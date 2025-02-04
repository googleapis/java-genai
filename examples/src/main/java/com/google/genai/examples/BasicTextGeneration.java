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
    Client client = Client.builder().setVertexAI(true).build();

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);
    System.out.println(response.text());
  }
}
