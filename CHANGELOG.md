# Changelog

## [1.1.0](https://github.com/googleapis/java-genai/compare/v1.0.0...v1.1.0) (2025-05-28)


### Features

* Add generate_audio field for private testing of video generation ([16f2b0f](https://github.com/googleapis/java-genai/commit/16f2b0ff8e1d9f3adc54fb8df7cf58de075ce028))
* support new fields in FileData, GenerationConfig, GroundingChunkRetrievedContext, RetrievalConfig, Schema, TuningJob, VertexAISearch, ([6bf3d32](https://github.com/googleapis/java-genai/commit/6bf3d3224f16a9f46b4bc6e2d4af30bb4dbd768e))


### Documentation

* Fix comment typo for Modality.AUDIO ([18dea48](https://github.com/googleapis/java-genai/commit/18dea48164a79936dbb44c8d92e95b4615d914ce)), closes [#620](https://github.com/googleapis/java-genai/issues/620)

## [1.0.0](https://github.com/googleapis/java-genai/compare/v0.8.0...v1.0.0) (2025-05-19)


### ⚠ BREAKING CHANGES

* Support java.time.Instant and java.time.Duration for time/duration fields

### Features

* add `time range filter` to Google Search Tool ([4aba9a6](https://github.com/googleapis/java-genai/commit/4aba9a66ffb8b63fd5498674c386d0a21e67ae0e))
* Add async list methods to Models, Caches, and Files ([cde10ab](https://github.com/googleapis/java-genai/commit/cde10abdc1ef0b9f35510413e7fcc0fa1d484b08))
* add async streaming chat ([df6446a](https://github.com/googleapis/java-genai/commit/df6446a532448023c8e2b670c7fd5f766eca5a2b))
* add async support for chat ([9973df7](https://github.com/googleapis/java-genai/commit/9973df7d1292f959d1fd3c814131a97ef1554e72))
* Add AsyncPager class ([cde10ab](https://github.com/googleapis/java-genai/commit/cde10abdc1ef0b9f35510413e7fcc0fa1d484b08))
* Add basic support for async function calling. ([653094a](https://github.com/googleapis/java-genai/commit/653094af41bf67fcff802ec7bc7ed4f7f4a14f47))
* add Caches.create/delete/get support ([c4d17a0](https://github.com/googleapis/java-genai/commit/c4d17a0e2ac7aef13b82c2a31ba89d98a090724d))
* Add client.models.list() method ([25883eb](https://github.com/googleapis/java-genai/commit/25883eb150f41da5b877dbfaae8b49170e91520c))
* Add Files module with Files.upload, .get and .delete ([e211ca1](https://github.com/googleapis/java-genai/commit/e211ca1f617864f50590a6cc0a4b729a646a41e4))
* Add Files.download methods ([082c890](https://github.com/googleapis/java-genai/commit/082c890920c2bed8d6fe30cf1d4d5de40b676e5e))
* Add Image.fromFile() support in Java SDK ([4ddcaa1](https://github.com/googleapis/java-genai/commit/4ddcaa19bb0a99fabff52add6a3206caec281f23))
* Add list methods in Caches and Files module ([ce6ed85](https://github.com/googleapis/java-genai/commit/ce6ed8543254e762372cc0b8f60585c2325efe9f))
* add live proactivity_audio and enable_affective_dialog ([64c20c7](https://github.com/googleapis/java-genai/commit/64c20c70addcc179c753fda3ac74906c1dd96503))
* add multi-speaker voice config ([1689f77](https://github.com/googleapis/java-genai/commit/1689f77614d67a956999b087f8f453a0ff281bd7))
* Add pagination support ([25883eb](https://github.com/googleapis/java-genai/commit/25883eb150f41da5b877dbfaae8b49170e91520c))
* Add support for lat/long in search. ([f43f00f](https://github.com/googleapis/java-genai/commit/f43f00f1ca51cfd7e3ee9439fad9013a42940d0a))
* Add Video FPS, and enable start/end_offset for MLDev ([bc1df02](https://github.com/googleapis/java-genai/commit/bc1df02c6760c197eb3aa1eb7e58b2f982c06a40))
* Added Async wrappers for the Files Module operations ([b5b608d](https://github.com/googleapis/java-genai/commit/b5b608d69f8a55ad430b0d1b748a1ecbbc0fbc07))
* enable automatic function calling for Models.generateContent method ([ecca800](https://github.com/googleapis/java-genai/commit/ecca80020a3facaad37744d73e1951b655b62df1))
* record automatic function calling history for Chat.sendMessage methods. ([7428d87](https://github.com/googleapis/java-genai/commit/7428d87adcf05fbac863fdd0986f7a08009f0b68))
* support customer-managed encryption key in cached content ([3021aaf](https://github.com/googleapis/java-genai/commit/3021aafb5da9bbbb2852660761728849df924db0))
* Support ephemeral token creation in Python ([7f33c20](https://github.com/googleapis/java-genai/commit/7f33c207b27c32f12817ba839eaf26526f08c195))
* Support java.time.Instant and java.time.Duration for time/duration fields ([460d3d3](https://github.com/googleapis/java-genai/commit/460d3d31912c1003774bd898965d7a1aee90a61b))
* Support models.get/delete/update in Java ([3d8d8ec](https://github.com/googleapis/java-genai/commit/3d8d8ec5eb60c8c8183588389e45300d7f4c82a0))
* Support Url Context Retrieval tool ([f4fd37a](https://github.com/googleapis/java-genai/commit/f4fd37acea16595168a88801c4c50da0abdfd2f1))
* Support using ephemeral token in Live session connection in Python ([7f33c20](https://github.com/googleapis/java-genai/commit/7f33c207b27c32f12817ba839eaf26526f08c195))


### Bug Fixes

* bug in validate history ([7428d87](https://github.com/googleapis/java-genai/commit/7428d87adcf05fbac863fdd0986f7a08009f0b68))
* Transformers are not being used in list types ([a9609db](https://github.com/googleapis/java-genai/commit/a9609db72b5a2051f4fbe655ba32ca3f0def3a5a))


### Miscellaneous Chores

* release 1.0.0 ([d4561d7](https://github.com/googleapis/java-genai/commit/d4561d78447f45f7be63f4bc6f342ab337f35dc0))

## [0.8.0](https://github.com/googleapis/java-genai/compare/v0.7.0...v0.8.0) (2025-05-13)


### ⚠ BREAKING CHANGES

* mark *Parameters classes as internal
* add enum support

### Features

* add Content.text() quick accessor ([c184615](https://github.com/googleapis/java-genai/commit/c184615f697652335b77348fd6b7b5eed2f03185))
* add enum support ([86b0553](https://github.com/googleapis/java-genai/commit/86b0553855b68e7e11b2bc91945326cf14c39a7e))
* Add httpOptions field to type classes ([971177d](https://github.com/googleapis/java-genai/commit/971177ddddf4c896e512bfb79d1cabe845719976))
* add support for audio, video, text and session resumption. ([dcd08a4](https://github.com/googleapis/java-genai/commit/dcd08a4062981b1e2079ed61dd55fa693fa89983))
* automatically parse a java.lang.reflect.Method instance into a FunctionDeclaration when users pass it in as a Tool. ([e5109a0](https://github.com/googleapis/java-genai/commit/e5109a0a3ab3f74116513c962d65e95b80b61178))
* enable request level http options(set through XxxConfig) ([abbe820](https://github.com/googleapis/java-genai/commit/abbe82015dc870c9588f55de8b5074ebebabcd63))
* support display_name for Blob class when calling Vertex AI ([db33f8a](https://github.com/googleapis/java-genai/commit/db33f8a4434f990e3ab8fa543a2d6d787147a540))


### Bug Fixes

* fix import in live sample ([78cc345](https://github.com/googleapis/java-genai/commit/78cc345791331fb662b2a151846bae19086acc2a))


### Documentation

* Improve docs for response_mime_type and response_schema. Relate to [#297](https://github.com/googleapis/java-genai/issues/297) ([4782f56](https://github.com/googleapis/java-genai/commit/4782f56b5541bf62f88dce7e3e778b9e30df1692))


### Miscellaneous Chores

* mark *Parameters classes as internal ([77c9d71](https://github.com/googleapis/java-genai/commit/77c9d714569431d30d11121d897d1608cc81420a))

## [0.7.0](https://github.com/googleapis/java-genai/compare/v0.6.1...v0.7.0) (2025-05-06)


### ⚠ BREAKING CHANGES

* Rename getVideoOperation to getVideosOperation for Java SDK

### Features

* Add `Tool.enterprise_web_search` field ([919c22a](https://github.com/googleapis/java-genai/commit/919c22a79e357a4be03e98bd24b4d02282ca7a0d))
* Add computeTokens support ([85c751e](https://github.com/googleapis/java-genai/commit/85c751e5e8e715ef26cf5524cb57a7d77953a734))
* Add countTokens method support ([8b8fb45](https://github.com/googleapis/java-genai/commit/8b8fb4515d994b24bd1a3bb3b500f415c2dddeca))
* Add support for Grounding with Google Maps ([24b1127](https://github.com/googleapis/java-genai/commit/24b112756b21e1b9650e434ba6f53557c8bf6531))
* enable input transcription for Gemini API. ([9065970](https://github.com/googleapis/java-genai/commit/906597026e36c09cded0d8cb50ffe14a96c5faff))
* **http:** Make HttpApiClient public ([5da0746](https://github.com/googleapis/java-genai/commit/5da0746a818218d22456bfbab9d665cde6c1540e))


### Documentation

* Add javadoc to public classes/methods ([78c2a39](https://github.com/googleapis/java-genai/commit/78c2a390ef7ad34dac5cf14ff10838ba9c0899c4))


### Code Refactoring

* Rename getVideoOperation to getVideosOperation for Java SDK ([4cd355b](https://github.com/googleapis/java-genai/commit/4cd355b51ffe5a551c54bf5e35b22ef47ad545e2))

## [0.6.1](https://github.com/googleapis/java-genai/compare/v0.6.0...v0.6.1) (2025-05-01)


### Bug Fixes

* generateContentStream failed to find converter method GenerateContentResponseFromXxx ([590cb98](https://github.com/googleapis/java-genai/commit/590cb987874e6a812b0b3e5dab3f75fe919adafa))


### Miscellaneous Chores

* release 0.6.1 ([19c6373](https://github.com/googleapis/java-genai/commit/19c6373ac4e8dbc703d84b2d6564468ba810f739))

## [0.6.0](https://github.com/googleapis/java-genai/compare/v0.5.0...v0.6.0) (2025-04-30)


### ⚠ BREAKING CHANGES

* add streaming support for Chat

### Features

* add streaming support for Chat ([59eca52](https://github.com/googleapis/java-genai/commit/59eca52caf9f6a6f0a6d02959e3a41db0270c5d1))
* add support for live grounding metadata ([e90dd15](https://github.com/googleapis/java-genai/commit/e90dd15f58fcbab954c915a89619a035f5edb7ca))
* Enable configurable speech detection in live SDK ([d6bfa76](https://github.com/googleapis/java-genai/commit/d6bfa76599df688dcaf3178cf70a86b292af461a))
* introduce FunctionDeclaration.fromMethod class method to parse a FunctionDeclaration instance from a java.lang.reflect.Method instance. ([aeb63b4](https://github.com/googleapis/java-genai/commit/aeb63b448714026c531aa321369f2272ffc8e3ae))
* make min_property, max_property, min_length, max_length, example, patter fields available for Schema class when calling Gemini API ([8527db5](https://github.com/googleapis/java-genai/commit/8527db52795b1281f3a3de54bb6351e67d5d5229))
* Support continuous window compression in the Live API ([7acb98c](https://github.com/googleapis/java-genai/commit/7acb98c59774282a7c9bad131c5a3ec3ebd93324))
* Support setting the default base URL in clients via Client.setDefaultBaseUrls() ([c119778](https://github.com/googleapis/java-genai/commit/c119778c3cf573e9eeefc0c540313162f1abf3ae))


### Bug Fixes

* do not raise error for `default` field in Schema for Gemini API calls ([8bb1c64](https://github.com/googleapis/java-genai/commit/8bb1c640f4f710b7cd4a1a5a248ced14683bcd0d))
* Throw IllegalArgumentException rather than general Error when an invalid parameter is passed to Gemini/Vertex backend ([1bd4485](https://github.com/googleapis/java-genai/commit/1bd44850b251560cdc4b513aea43bb037f7016b1))
* Update error message when a service from an unsupported backend is called ([4cd81ea](https://github.com/googleapis/java-genai/commit/4cd81ea374f756b418629f25c78d5d8e7d914d20))
* Update Live API websocket base url in java SDK ([8c97572](https://github.com/googleapis/java-genai/commit/8c97572ce6b2b12e5d597ee42bd454ef20f2ad75))


### Documentation

* add a link for where to find the Google Cloud project id, API key and location ([3ac726c](https://github.com/googleapis/java-genai/commit/3ac726c6122bc8fad1cbfd0959ba62d4ba05f489))

## [0.5.0](https://github.com/googleapis/java-genai/compare/v0.4.0...v0.5.0) (2025-04-22)


### Features

* Populate X-Server-Timeout header when a request timeout is set. ([7126995](https://github.com/googleapis/java-genai/commit/71269959b7e376cd9fd8e3d1f2bfbf6a82ca6bc4))
* support `default` field in Schema when users call Gemini API ([1d7c48d](https://github.com/googleapis/java-genai/commit/1d7c48d9327482989cd06999626718ed33d62c6f))

## [0.4.0](https://github.com/googleapis/java-genai/compare/v0.3.0...v0.4.0) (2025-04-15)


### ⚠ BREAKING CHANGES

* Hide internal classes ApiResponse and Common
* Move generation config to the top level LiveConnectConfig
* Remove AsyncSession.sendContent method which was accidentally added
* No more checked exceptions
* Remove AsyncSession.sendContent method which was accidentally added
* No more checked exceptions

### Features

* Add async editImage support for Java SDK ([358c896](https://github.com/googleapis/java-genai/commit/358c896c5130dd1c6ce4335745a8249de5a518ca))
* add Chat module ([03442fc](https://github.com/googleapis/java-genai/commit/03442fcebdb6493671a5883952f34ee446c961e5))
* Add domain to Web GroundingChunk ([990586b](https://github.com/googleapis/java-genai/commit/990586b3f1d8ca8723fe1823c9f549778787ab5c))
* Add generationComplete notification to Live ServerContent ([de5a762](https://github.com/googleapis/java-genai/commit/de5a76292452d6bf691bc602d1dc45b24883b82b))
* add session resumption. ([5c35c52](https://github.com/googleapis/java-genai/commit/5c35c52eb06e7243eb0b417179a6c3f11a9825ba))
* add support for model_selection_config to GenerateContentConfig ([1958d4c](https://github.com/googleapis/java-genai/commit/1958d4cc6df7c0f686f27bd844fee2396be28f48))
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

* fix errors in live samples ([5a9892f](https://github.com/googleapis/java-genai/commit/5a9892f60579b7309b4dd47e0e1a49f9c7848163))
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


### Code Refactoring

* Hide internal classes ApiResponse and Common ([6218143](https://github.com/googleapis/java-genai/commit/6218143384b89bd9267c76a868365ee03b381570))

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
