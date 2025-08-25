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

/**
 * Usage:
 *
 * <p>1a. If you are using Vertex AI, setup ADC to get credentials:
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
 * <p>1b. If you are using Gemini Developer API, set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * <p>export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * <p>2. Compile the java package and run the sample code.
 *
 * <p>mvn clean compile exec:java -Dexec.mainClass="com.google.genai.examples.ModelManagementAsync"
 * -Dexec.args="YOUR_TUNED_MODEL_ID"
 */
package com.google.genai.examples;

import com.google.genai.AsyncPager;
import com.google.genai.Client;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.Model;
import com.google.genai.types.UpdateModelConfig;
import java.util.concurrent.CompletableFuture;

/** An example of using the Unified Gen AI Java SDK to manage models asynchronously. */
public final class ModelManagementAsync {

  public static void main(String[] args) {
    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else {
      modelId = Constants.GEMINI_MODEL_NAME;
    }

    // Instantiate the client. The client by default uses the Gemini Developer API. It gets the API
    // key from the environment variable `GOOGLE_API_KEY`. Vertex AI API can be used by setting the
    // environment variables `GOOGLE_CLOUD_LOCATION` and `GOOGLE_CLOUD_PROJECT`, as well as setting
    // `GOOGLE_GENAI_USE_VERTEXAI` to "true".
    //
    // Note: Some services are only available in a specific API backend (Gemini or Vertex), you will
    // get a `UnsupportedOperationException` if you try to use a service that is not available in
    // the backend you are using.
    Client client = new Client();

    if (client.vertexAI()) {
      System.out.println("Using Vertex AI");
    } else {
      System.out.println("Using Gemini Developer API");
    }

    CompletableFuture<Model> getModelFuture = client.async.models.get(modelId, null);

    CompletableFuture<Void> allOperations =
        getModelFuture
            .thenCompose(
                modelResponse -> {
                  System.out.println("Get Model response: " + modelResponse);

                  CompletableFuture<Void> updateOperationFuture =
                      CompletableFuture.completedFuture(null);

                  // Update the model if it's a tuned model.
                  boolean isTunedModel =
                      modelResponse
                          .name()
                          .map(name -> name.startsWith("tunedModels"))
                          .orElse(false);

                  if (isTunedModel) {
                    System.out.println(
                        "\nModel "
                            + modelResponse.name().get()
                            + " is a tuned model. Attempting update...");

                    updateOperationFuture =
                        client
                            .async
                            .models
                            .update(
                                modelResponse.name().get(),
                                UpdateModelConfig.builder()
                                    .displayName("My Async Updated Model")
                                    .description("My async updated description")
                                    .build())
                            .thenAccept(
                                updatedModel -> {
                                  System.out.println(
                                      "Update Tuned Model response: " + updatedModel);
                                });
                  }
                  return updateOperationFuture;
                })
            .thenCompose(
                voidResultAfterUpdate -> {
                  System.out.println("List all base models asynchronously:");
                  CompletableFuture<AsyncPager<Model>> asyncPagerFuture =
                      client.async.models.list(ListModelsConfig.builder().pageSize(10).build());

                  return asyncPagerFuture.thenCompose(
                      asyncPager -> {
                        return asyncPager.forEach(
                            model -> System.out.println("  Model: " + model.name().get()));
                      });
                });

    // Wait for all chained asynchronous operations to complete for this example
    try {
      allOperations.join();
    } catch (Exception e) {
      System.err.println(
          "\nAn error occurred during asynchronous model operations: " + e.getMessage());
    }
  }

  private ModelManagementAsync() {}
}
