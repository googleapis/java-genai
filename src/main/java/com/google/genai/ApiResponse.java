package com.google.genai;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;

/** The API response contains a response to a call to the GenAI APIs. */
public abstract class ApiResponse implements AutoCloseable {
  /** Gets the HttpEntity. */
  public abstract HttpEntity getEntity() throws HttpException;

  public abstract void close() throws IOException;
}
