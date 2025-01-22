package com.google.genai;

/** Data class configuration for debugging or testing the Client. */
final class DebugConfig {
  private final String clientMode;
  private final String replayId;
  private final String replaysDirectory;

  DebugConfig() {
    String clientMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    this.clientMode = clientMode != null ? clientMode : "";

    String replayId = System.getenv("GOOGLE_GENAI_REPLAY_ID");
    this.replayId = replayId != null ? replayId : "";

    String replaysDirectory = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    this.replaysDirectory = replaysDirectory != null ? replaysDirectory : "";
  }

  DebugConfig(String clientMode, String replayId, String replaysDirectory) {
    String envClientMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    this.clientMode =
        clientMode != null ? clientMode : (envClientMode != null ? envClientMode : "");

    String envReplayId = System.getenv("GOOGLE_GENAI_REPLAY_ID");
    this.replayId = replayId != null ? replayId : (envReplayId != null ? envReplayId : "");

    String envReplaysDirectory = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    this.replaysDirectory =
        replaysDirectory != null
            ? replaysDirectory
            : (envReplaysDirectory != null ? envReplaysDirectory : "");
  }

  public String getClientMode() {
    return this.clientMode;
  }

  public String getReplayId() {
    return this.replayId;
  }

  public String getReplaysDirectory() {
    return this.replaysDirectory;
  }
}
