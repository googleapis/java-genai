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
 * <p>2. Compile the java package and run the sample code. You might need to grant microphone
 * permissions.
 *
 * <p>mvn clean
 *
 * <p>mvn compile exec:java -Dexec.mainClass="com.google.genai.examples.LiveAudioConversationAsync"
 * -Dexec.args="YOUR_MODEL_ID"
 *
 * <p>3. Speak into the microphone. Press Ctrl+C to exit. Important: This example uses the system
 * default audio input and output, which often won't include echo cancellation. So to prevent the
 * model from interrupting itself it is important that you use headphones.
 */
package com.google.genai.examples;

import com.google.genai.AsyncSession;
import com.google.genai.Client;
import com.google.genai.types.Blob;
import com.google.genai.types.AutomaticActivityDetection;
import com.google.genai.types.EndSensitivity;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveSendRealtimeInputParameters;
import com.google.genai.types.LiveServerMessage;
import com.google.genai.types.Modality;
import com.google.genai.types.RealtimeInputConfig;
import com.google.genai.types.PrebuiltVoiceConfig;
import com.google.genai.types.SpeechConfig;
import com.google.genai.types.StartSensitivity;
import com.google.genai.types.VoiceConfig;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/** Example of using the live module for a streaming audio conversation. */
public final class LiveAudioConversationAsync {

  // --- Audio Configuration ---
  private static final AudioFormat MIC_AUDIO_FORMAT =
      new AudioFormat(16000.0f, 16, 1, true, false); // 16kHz, 16-bit, mono, signed, little-endian
  private static final AudioFormat SPEAKER_AUDIO_FORMAT =
      new AudioFormat(24000.0f, 16, 1, true, false); // 24kHz, 16-bit, mono, signed, little-endian

  // How many bytes to read from mic/send to API at a time
  private static final int CHUNK_SIZE = 4096;
  // --------------------------

  private static volatile boolean running = true;
  private static TargetDataLine microphoneLine;
  private static SourceDataLine speakerLine;
  private static AsyncSession session;
  private static ExecutorService micExecutor = Executors.newSingleThreadExecutor();

  /** Creates the parameters for sending an audio chunk. */
  public static LiveSendRealtimeInputParameters createAudioContent(byte[] audioData) {

    if (audioData == null) {
      System.err.println("Error: Audio is null");
      return null;
    }

    return LiveSendRealtimeInputParameters.builder()
        .media(Blob.builder().mimeType("audio/pcm").data(audioData))
        .build();
  }

  /** Reads audio from the microphone and sends it to the API session. Runs in a separate thread. */
  private static void sendMicrophoneAudio() {
    byte[] buffer = new byte[CHUNK_SIZE];
    int bytesRead;

    while (running && microphoneLine != null && microphoneLine.isOpen()) {
      bytesRead = microphoneLine.read(buffer, 0, buffer.length);

      if (bytesRead > 0) {
        byte[] audioChunk = new byte[bytesRead];
        System.arraycopy(buffer, 0, audioChunk, 0, bytesRead);

        // Send the audio chunk asynchronously
        if (session != null) {
          session
              .sendRealtimeInput(createAudioContent(audioChunk))
              .exceptionally(
                  e -> {
                    System.err.println("Error sending audio chunk: " + e.getMessage());
                    return null;
                  });
        }
      } else if (bytesRead == -1) {
        System.err.println("Microphone stream ended unexpectedly.");
        running = false; // Stop the loop if stream ends
      }
    }
    System.out.println("Microphone reading stopped.");
  }

  public static void main(String[] args) throws LineUnavailableException {
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

    final String modelId;
    if (args.length != 0) {
      modelId = args[0];
    } else if (client.vertexAI()) {
      modelId = Constants.GEMINI_LIVE_MODEL_NAME;
    } else {
      modelId = Constants.GEMINI_LIVE_MODEL_NAME_PREVIEW;
    }

    // --- Audio Line Setup ---
    microphoneLine = getMicrophoneLine();
    speakerLine = getSpeakerLine();

    // --- Live API Config for Audio ---
    // Choice of ["Aoede", "Puck", "Charon", "Kore", "Fenrir", "Leda", "Orus", "Zephyr"]
    String voiceName = "Aoede";
    LiveConnectConfig config =
        LiveConnectConfig.builder()
            .responseModalities(Modality.Known.AUDIO)
            .speechConfig(
                SpeechConfig.builder()
                    .voiceConfig(
                        VoiceConfig.builder()
                            .prebuiltVoiceConfig(
                                PrebuiltVoiceConfig.builder().voiceName(voiceName)))
                    .languageCode("en-US"))
            .realtimeInputConfig(
                RealtimeInputConfig.builder()
                    .automaticActivityDetection(
                        AutomaticActivityDetection.builder()
                            .startOfSpeechSensitivity(StartSensitivity.Known.START_SENSITIVITY_HIGH)
                            .endOfSpeechSensitivity(EndSensitivity.Known.END_SENSITIVITY_HIGH)
                            .prefixPaddingMs(5)
                            .silenceDurationMs(100)))
            .build();

    // --- Shutdown Hook for Cleanup ---
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  System.out.println("\nShutting down...");
                  running = false; // Signal mic thread to stop
                  micExecutor.shutdown();
                  try {
                    if (!micExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                      System.err.println("Mic executor did not terminate gracefully.");
                      micExecutor.shutdownNow();
                    }
                  } catch (InterruptedException e) {
                    micExecutor.shutdownNow();
                    Thread.currentThread().interrupt();
                  }

                  // Close session first
                  if (session != null) {
                    try {
                      System.out.println("Closing API session...");
                      session.close().get(5, TimeUnit.SECONDS); // Wait with timeout
                      System.out.println("API session closed.");
                    } catch (Exception e) {
                      System.err.println("Error closing API session: " + e.getMessage());
                    }
                  }
                  // Close audio lines
                  closeAudioLine(microphoneLine);
                  closeAudioLine(speakerLine);
                  System.out.println("Audio lines closed.");
                }));

    try {
      // --- Connect to Gemini Live API ---
      System.out.println("Connecting to Gemini Live API...");

      session = client.async.live.connect(modelId, config).get();
      System.out.println("Connected.");

      // --- Start Audio Lines ---
      microphoneLine.start();
      speakerLine.start();
      System.out.println("Microphone and speakers started. Speak now (Press Ctrl+C to exit)...");

      // --- Start Receiving Audio Responses ---
      CompletableFuture<Void> receiveFuture =
          session.receive(LiveAudioConversationAsync::handleAudioResponse);
      System.err.println("Receive stream started."); // Add this line

      // --- Start Sending Microphone Audio ---
      CompletableFuture<Void> sendFuture =
          CompletableFuture.runAsync(LiveAudioConversationAsync::sendMicrophoneAudio, micExecutor);

      // Keep the main thread alive. Wait for sending or receiving to finish (or
      // error).
      // In this continuous streaming case, we rely on the shutdown hook triggered by
      // Ctrl+C.
      // We can wait on the futures, but they might not complete normally in this
      // design.
      CompletableFuture.anyOf(receiveFuture, sendFuture)
          .handle(
              (res, err) -> {
                if (err != null) {
                  System.err.println("An error occurred in sending/receiving: " + err.getMessage());
                  // Trigger shutdown if needed
                  System.exit(1);
                }
                return null;
              })
          .get(); // Wait indefinitely or until an error occurs in send/receive

    } catch (InterruptedException | ExecutionException e) {
      System.err.println("An error occurred during setup or connection: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
    // Note: Normal exit is handled by the shutdown hook when Ctrl+C is pressed.
  }

  /** Gets and opens the microphone line. */
  private static TargetDataLine getMicrophoneLine() throws LineUnavailableException {
    DataLine.Info micInfo = new DataLine.Info(TargetDataLine.class, MIC_AUDIO_FORMAT);
    if (!AudioSystem.isLineSupported(micInfo)) {
      throw new LineUnavailableException(
          "Microphone line not supported for format: " + MIC_AUDIO_FORMAT);
    }
    TargetDataLine line = (TargetDataLine) AudioSystem.getLine(micInfo);
    line.open(MIC_AUDIO_FORMAT);
    System.out.println("Microphone line opened.");
    return line;
  }

  /** Gets and opens the speaker line. */
  private static SourceDataLine getSpeakerLine() throws LineUnavailableException {
    DataLine.Info speakerInfo = new DataLine.Info(SourceDataLine.class, SPEAKER_AUDIO_FORMAT);
    if (!AudioSystem.isLineSupported(speakerInfo)) {
      throw new LineUnavailableException(
          "Speaker line not supported for format: " + SPEAKER_AUDIO_FORMAT);
    }
    SourceDataLine line = (SourceDataLine) AudioSystem.getLine(speakerInfo);
    line.open(SPEAKER_AUDIO_FORMAT);
    System.out.println("Speaker line opened.");
    return line;
  }

  /** Closes an audio line safely. */
  private static void closeAudioLine(Line line) {
    if (line != null && line.isOpen()) {
      line.close();
    }
  }

  /** Callback function to handle incoming audio messages from the server. */
  public static void handleAudioResponse(LiveServerMessage message) {
    message
        .serverContent()
        .ifPresent(
            content -> {
              // Handle interruptions from Gemini.
              if (content.interrupted().orElse(false)) {
                speakerLine.flush();
                return; // Skip processing the rest of this message's audio.
              }

              // Handle Model turn completion.
              if (content.turnComplete().orElse(false)) {
                // The turn is over, no more audio will be sent for this turn.
                return;
              }

              // Process audio content for playback.
              content.modelTurn().stream()
                  .flatMap(modelTurn -> modelTurn.parts().stream())
                  .flatMap(Collection::stream)
                  .map(part -> part.inlineData().flatMap(Blob::data))
                  .flatMap(Optional::stream)
                  .forEach(
                      audioBytes -> {
                        if (speakerLine != null && speakerLine.isOpen()) {
                          // Write audio data to the speaker
                          speakerLine.write(audioBytes, 0, audioBytes.length);
                        }
                      });

              // If this is the last message of a generation, drain the buffer.
              if (content.generationComplete().orElse(false)) {
                speakerLine.drain();
              }
            });
  }

  private LiveAudioConversationAsync() {}
}
