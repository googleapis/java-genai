# Changelog

## [0.3.0](https://github.com/googleapis/java-genai/compare/v0.2.0...v0.3.0) (2025-03-14)


### ⚠ BREAKING CHANGES

* Rename `Client.isVertexAI()` to `Client.vertexAI()` to align with other places
* change bytes datatype from String to byte[] to avoid potential data loss

### Features

* Add response_id and create_time to GenerateContentResponse ([f52e068](https://github.com/googleapis/java-genai/commit/f52e06850daeb8dea369f2c37cba89a33bf720e7))
* add types for Live API ([2cdb435](https://github.com/googleapis/java-genai/commit/2cdb43566c4a4e1235c3774a2bf59d0f789c80ba))
* enable minItem, maxItem, nullable for Schema type when calling Gemini API. ([5975a55](https://github.com/googleapis/java-genai/commit/5975a552ace175fa9a0cd9583bab266a96df3e86))
* enable union type for Schema when calling Gemini API. ([3d26960](https://github.com/googleapis/java-genai/commit/3d2696024fcd3985fac3cbb178bc794c3a6af967))
* Support aspect ratio for edit_image ([7742349](https://github.com/googleapis/java-genai/commit/774234991f76d46708e83c0744c595122901858c))
* Support Live API ([5741147](https://github.com/googleapis/java-genai/commit/5741147fb7bc6614cf6250d4270d3cf424e04d1c))
* Support returned safety attributes for generate_images ([ae6f5e4](https://github.com/googleapis/java-genai/commit/ae6f5e4ca1b22b6c31a71209feffa9e09962bd83))
* throw error when given method is not supported in Gemini API or Vertex AI ([f055ad8](https://github.com/googleapis/java-genai/commit/f055ad8adb985a1068fe81352fcf9d76cb734616))


### Bug Fixes

* change bytes datatype from String to byte[] to avoid potential data loss ([9979823](https://github.com/googleapis/java-genai/commit/997982313a3c6dc264469af6a8a0cbfd22e91e66))
* log warning instead of throw error in GenerateContentResponse.text() quick accessor when there are mixed types of parts in the response. ([13c5ccf](https://github.com/googleapis/java-genai/commit/13c5ccfd05be079cc61d8feedd894ca391c4b4da))
* Remove unsupported parameter negative_prompt from Gemini API generate_images ([1d881d9](https://github.com/googleapis/java-genai/commit/1d881d90549f5c348b0ca94c5bbfa41a3dcd3f10))
* Rename `Client.isVertexAI()` to `Client.vertexAI()` to align with other places ([4211ca1](https://github.com/googleapis/java-genai/commit/4211ca1f0e3949275fb424b6a623ef5bf1925cc3))
* set default scope in VertexAI client ADC ([15aeede](https://github.com/googleapis/java-genai/commit/15aeedec30af2d6ec4a0b14aed5f6e5a004942b8))
* Set partial httpOptions in the Client will override all other options to empty ([46369a7](https://github.com/googleapis/java-genai/commit/46369a7575ecceac49066db1b8cc5c7b4a236d8b))

## [0.2.0](https://github.com/googleapis/java-genai/compare/v0.2.0-SNAPSHOT...v0.2.0) (2025-02-24)


### Features

* Add Imagen upscale_image support for Java ([7eb172f](https://github.com/googleapis/java-genai/commit/7eb172f14d211d4a8163cd0e0b0e5ebe21e92f81))
* Enable Media resolution for Gemini API. ([30c8aa6](https://github.com/googleapis/java-genai/commit/30c8aa6d0fbc71c8fd348ae2466327d32bc3d896))
* provide executable code and code execution result quick accessors for GenerateContentResponse class ([548f8c3](https://github.com/googleapis/java-genai/commit/548f8c377ded85fb82fd4c5b77f23efa40f412dd))
* support property_ordering in response_schema (fixes [#236](https://github.com/googleapis/java-genai/issues/236)) ([814aa6f](https://github.com/googleapis/java-genai/commit/814aa6f2ed1f81b4c7c0ede43e3cfcfd8c616251))


### Bug Fixes

* Fix private class/method generation for Java SDK ([0c4f1f8](https://github.com/googleapis/java-genai/commit/0c4f1f88427ff731722608eb1126008af508932b))
* Set request content-type encoding to UTF-8 ([#56](https://github.com/googleapis/java-genai/issues/56)) ([09329f7](https://github.com/googleapis/java-genai/commit/09329f7df2c724bdce6ded6194d396cd6aa80bb9))


### Documentation

* Update the model to gemini-2.0-flash-001 in the docs and the examples. ([a4bc69a](https://github.com/googleapis/java-genai/commit/a4bc69a00c6c2c0903d34f3bb77397e0ca17a38a))
