# Changelog

## [0.4.0](https://github.com/googleapis/java-genai/compare/v0.3.0...v0.4.0) (2025-04-11)


### ⚠ BREAKING CHANGES

* Move generation config to the top level LiveConnectConfig
* Remove AsyncSession.sendContent method which was accidentally added
* No more checked exceptions
* Remove AsyncSession.sendContent method which was accidentally added
* No more checked exceptions

### Features

* Add domain to Web GroundingChunk ([990586b](https://github.com/googleapis/java-genai/commit/990586b3f1d8ca8723fe1823c9f549778787ab5c))
* Add generationComplete notification to Live ServerContent ([de5a762](https://github.com/googleapis/java-genai/commit/de5a76292452d6bf691bc602d1dc45b24883b82b))
* add session resumption. ([5c35c52](https://github.com/googleapis/java-genai/commit/5c35c52eb06e7243eb0b417179a6c3f11a9825ba))
* Add thinking_budget to ThinkingConfig for Gemini Thinking Models ([a3ea4ed](https://github.com/googleapis/java-genai/commit/a3ea4ed04fb1e6251ebabd54a5370c04c9db976e))
* Add traffic type to GenerateContentResponseUsageMetadata ([fac241b](https://github.com/googleapis/java-genai/commit/fac241b9b6095bc965d48051caacd7b1da56d5f1))
* Add types for configurable speech detection ([021d74b](https://github.com/googleapis/java-genai/commit/021d74b97c17d500d6f64a619150b1d940eeef87))
* Add types to support continuous sessions with a sliding window ([d5e8a32](https://github.com/googleapis/java-genai/commit/d5e8a320de67aa947d0df232dcf1d7c811651927))
* Add UsageMetadata to LiveServerMessage ([8b57e99](https://github.com/googleapis/java-genai/commit/8b57e999dbb02e43d6682c36b00b56fb1b7acad4))
* Add Veo 2 generate_videos support in Java SDK ([272649a](https://github.com/googleapis/java-genai/commit/272649a2ebb35110b96b4db0ae52347ae1449bf6))
* expose generation_complete, input/output_transcription & input/output_audio_transcription to SDK for Vertex Live API ([e3b517a](https://github.com/googleapis/java-genai/commit/e3b517a085736e6171730c36e2a88e2d0bdeb36f))
* merge GenerationConfig into LiveConnectConfig ([e2fa59d](https://github.com/googleapis/java-genai/commit/e2fa59df04abe400e8688077bfb2486703fdd841))
* No more checked exceptions ([e452491](https://github.com/googleapis/java-genai/commit/e452491f363f20489e8d4754a74e9fc9ab557e26))
* No more checked exceptions ([cad9eba](https://github.com/googleapis/java-genai/commit/cad9eba9af0b1a2536e9ce7be2c967aa6fdd6bee))
* Remove experimental warnings for generate_videos and operations ([be93305](https://github.com/googleapis/java-genai/commit/be93305b236283b77fcab9d5372d9a77f4dab757))
* Support audio transcription in Vertex Live API ([1ddb6d4](https://github.com/googleapis/java-genai/commit/1ddb6d498adcfb4b67152bdf42a22eb3e7850bfd))
* support media resolution ([ce9e7b0](https://github.com/googleapis/java-genai/commit/ce9e7b04e309198481392a0f0d54406d5d6de6ed))
* Support RealtimeInputConfig, and language_code in SpeechConfig in python ([f5bd874](https://github.com/googleapis/java-genai/commit/f5bd8744471b24792096ee169db3cea95ed7a872))
* Update VertexRagStore ([4a75670](https://github.com/googleapis/java-genai/commit/4a75670dc081e747f157ef632a59f3afa53aaa63))


### Bug Fixes

* Move generation config to the top level LiveConnectConfig ([c71e78e](https://github.com/googleapis/java-genai/commit/c71e78e4d3041798bbf4352a266337f834925104))
* Remove AsyncSession.sendContent method which was accidentally added ([e452491](https://github.com/googleapis/java-genai/commit/e452491f363f20489e8d4754a74e9fc9ab557e26))
* Remove AsyncSession.sendContent method which was accidentally added ([cad9eba](https://github.com/googleapis/java-genai/commit/cad9eba9af0b1a2536e9ce7be2c967aa6fdd6bee))
* schema handling in transformer ([8064028](https://github.com/googleapis/java-genai/commit/8064028e00df143245010c49b468adf04d939266))
* Use `orElseGet` to defer loading of default credentials. ([bc19d79](https://github.com/googleapis/java-genai/commit/bc19d794db6a6111e23a316870ef4a568df9128e))
* Use `orElseGet` to defer loading of default credentials. ([#124](https://github.com/googleapis/java-genai/issues/124)) ([6adac0e](https://github.com/googleapis/java-genai/commit/6adac0effbd5f792c69b3ab1d537c221de392894))


### Documentation

* Add examples for embedContent ([7583ddf](https://github.com/googleapis/java-genai/commit/7583ddfa22ec82a4cc0620615c5f67202ab99923))
* docstring improvements ([9d0ca34](https://github.com/googleapis/java-genai/commit/9d0ca34ef944d075e57b1df370bd751bd9c976b3))
* Update examples and README after removing checked exceptions ([5cb6ee5](https://github.com/googleapis/java-genai/commit/5cb6ee5384c57e5f698c395a97e8f9b197bb5f3b))

## [0.3.0](https://github.com/googleapis/java-genai/compare/v0.2.0...v0.3.0) (2025-03-28)


### ⚠ BREAKING CHANGES

* Only expose `toJson` and `fromJson` as public methods in JsonSerializable
* Rename `Client.isVertexAI()` to `Client.vertexAI()` to align with other places
* change bytes datatype from String to byte[] to avoid potential data loss

### Features

* Add client.models.embedContent methods ([02f177c](https://github.com/googleapis/java-genai/commit/02f177cca6235242c18cd01647aee75687b4b7d0))
* Add engine to VertexAISearch ([8ed56ad](https://github.com/googleapis/java-genai/commit/8ed56ad934c8ecf61320f3590a6ba14db327268e))
* add IMAGE_SAFTY enum value to FinishReason ([02ee351](https://github.com/googleapis/java-genai/commit/02ee3511ee4a143ffb2633635f39ffb3b1e48dbc))
* Add Imagen edit_image support in Java SDK ([4ae1366](https://github.com/googleapis/java-genai/commit/4ae1366f38ff1e6a38671ea16a7a9817b33d8f2b))
* Add quick constructors for Content and Part ([2ff9e86](https://github.com/googleapis/java-genai/commit/2ff9e8611a65d034bc8cf0a2264ec89a19471e5c))
* Add response_id and create_time to GenerateContentResponse ([f52e068](https://github.com/googleapis/java-genai/commit/f52e06850daeb8dea369f2c37cba89a33bf720e7))
* Add sendClientContent, sendRealtimeInput, sendToolResponse to live session ([dbd9cf7](https://github.com/googleapis/java-genai/commit/dbd9cf7a161e9e31b651fd0130d7d899f09bb7e8))
* add types for Live API ([2cdb435](https://github.com/googleapis/java-genai/commit/2cdb43566c4a4e1235c3774a2bf59d0f789c80ba))
* allow title property to be sent to Gemini API. Gemini API now supports the title property, so it's ok to pass this onto both Vertex and Gemini API. ([9afeedc](https://github.com/googleapis/java-genai/commit/9afeedcda9c57b63b1368f2fe0bc5f093caecf3b))
* enable minItem, maxItem, nullable for Schema type when calling Gemini API. ([5975a55](https://github.com/googleapis/java-genai/commit/5975a552ace175fa9a0cd9583bab266a96df3e86))
* enable union type for Schema when calling Gemini API. ([3d26960](https://github.com/googleapis/java-genai/commit/3d2696024fcd3985fac3cbb178bc794c3a6af967))
* Save prompt safety attributes in dedicated field for generate_images ([c8c5aaf](https://github.com/googleapis/java-genai/commit/c8c5aaf98f39f0c0e4cfca1127950a817b38ad72))
* Support aspect ratio for edit_image ([7742349](https://github.com/googleapis/java-genai/commit/774234991f76d46708e83c0744c595122901858c))
* Support global endpoint in java natively ([77e364e](https://github.com/googleapis/java-genai/commit/77e364e26a81a148d89934cbda5c5775c7e50538))
* Support Live API ([5741147](https://github.com/googleapis/java-genai/commit/5741147fb7bc6614cf6250d4270d3cf424e04d1c))
* support new UsageMetadata fields ([062cbe0](https://github.com/googleapis/java-genai/commit/062cbe0d68ba9943f2babd96738f5e5f6439ad61))
* Support returned safety attributes for generate_images ([ae6f5e4](https://github.com/googleapis/java-genai/commit/ae6f5e4ca1b22b6c31a71209feffa9e09962bd83))
* throw error when given method is not supported in Gemini API or Vertex AI ([f055ad8](https://github.com/googleapis/java-genai/commit/f055ad8adb985a1068fe81352fcf9d76cb734616))


### Bug Fixes

* change bytes datatype from String to byte[] to avoid potential data loss ([9979823](https://github.com/googleapis/java-genai/commit/997982313a3c6dc264469af6a8a0cbfd22e91e66))
* Getter methods in Client will return null rather than throw exceptions when the value is not set ([c1b0290](https://github.com/googleapis/java-genai/commit/c1b0290d5f11efff9e402c6df75e17782cbb529d))
* log warning instead of throw error in GenerateContentResponse.text() quick accessor when there are mixed types of parts in the response. ([13c5ccf](https://github.com/googleapis/java-genai/commit/13c5ccfd05be079cc61d8feedd894ca391c4b4da))
* Only expose `toJson` and `fromJson` as public methods in JsonSerializable ([dbef8b4](https://github.com/googleapis/java-genai/commit/dbef8b4fcb8f468cf5c4024e4ffe74c7a2380cc5))
* Remove unsupported parameter negative_prompt from Gemini API generate_images ([1d881d9](https://github.com/googleapis/java-genai/commit/1d881d90549f5c348b0ca94c5bbfa41a3dcd3f10))
* Rename `Client.isVertexAI()` to `Client.vertexAI()` to align with other places ([4211ca1](https://github.com/googleapis/java-genai/commit/4211ca1f0e3949275fb424b6a623ef5bf1925cc3))
* schema transformer logic fix. ([e6ab7d5](https://github.com/googleapis/java-genai/commit/e6ab7d564dfc188b8eef6fd1a0f2fc70ed05c8d4))
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
