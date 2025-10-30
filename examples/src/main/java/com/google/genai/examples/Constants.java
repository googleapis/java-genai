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

package com.google.genai.examples;

/** A final class to hold constants shared across all examples. */
public final class Constants {

  private Constants() {}

  /** The name of the generative model to be used in the examples. */
  public static final String GEMINI_MODEL_NAME = "gemini-2.5-flash";

  /** The name of the live model to be used in the examples. */
  public static final String GEMINI_LIVE_MODEL_NAME = "gemini-live-2.5-flash";

  /** The name of the preview live model to be used in the examples. */
  public static final String GEMINI_LIVE_MODEL_NAME_PREVIEW = "gemini-live-2.5-flash-preview";

  /** The name of the image generation model to be used in the examples. */
  public static final String GEMINI_IMAGE_GENERATION_MODEL_NAME =
      "gemini-2.0-flash-preview-image-generation";

  /** The name of the Imagen generate model to be used in the examples. */
  public static final String IMAGEN_GENERATE_MODEL_NAME = "imagen-4.0-generate-001";

  /** The name of the Imagen model to be used for image editing in the examples. */
  public static final String IMAGEN_CAPABILITY_MODEL_NAME = "imagen-3.0-capability-001";

  /** The name of the Imagen ingredients model to be used in the examples. */
  public static final String IMAGEN_INGREDIENTS_MODEL_NAME = "imagen-4.0-ingredients-preview";

  /** The name of the Imagen product recontext model to be used in the examples. */
  public static final String IMAGEN_RECONTEXT_MODEL_NAME = "imagen-product-recontext-preview-06-30";

  /** The name of the Virtual try-on model to be used in the examples. */
  public static final String VIRTUAL_TRY_ON_MODEL_NAME = "virtual-try-on-preview-08-04";

  /** The name of the segment image model to be used in the examples. */
  public static final String SEGMENT_IMAGE_MODEL_NAME = "image-segmentation-001";

  /** The name of the Veo model to be used in the examples. */
  public static final String VEO_MODEL_NAME = "veo-2.0-generate-001";

  /** The name of the embedding model to be used in the examples. */
  public static final String EMBEDDING_MODEL_NAME = "text-embedding-004";

  /** The file path to be used in the files operations examples. */
  public static final String UPLOAD_FILE_PATH = "./resources/test.txt";
}
