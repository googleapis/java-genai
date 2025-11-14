# Changelog

## [1.28.0](https://github.com/googleapis/java-genai/compare/v1.27.0...v1.28.0) (2025-11-14)


### Features

* add display name to FunctionResponseBlob ([8db8c57](https://github.com/googleapis/java-genai/commit/8db8c576247813991110e6ea10df999b756771ae))
* support upload to file search stores ([7862ce3](https://github.com/googleapis/java-genai/commit/7862ce38e950e3e6ff269e4f571474c42f4b19e4))

## [1.27.0](https://github.com/googleapis/java-genai/compare/v1.26.0...v1.27.0) (2025-11-12)


### Features

* Add `images()` convenience method to `GenerateImagesResponse` ([155df8d](https://github.com/googleapis/java-genai/commit/155df8d25bdbcf1e6ef4b9859b5be28dfe9f943e))
* Add EvaluationConfig support to tune() in Java ([795cf73](https://github.com/googleapis/java-genai/commit/795cf73d87894243d06c793262bed9b488167f95))
* Automatically set response type in FunctionDeclaration during the AFC ([5ce99df](https://github.com/googleapis/java-genai/commit/5ce99df9136b1606fed3f385ff68b4ef84e931eb))
* Support overriding the max read length in the JSON parser ([29d2fca](https://github.com/googleapis/java-genai/commit/29d2fcac0202bd8ba81c0973f172432b18bc3f79))


### Bug Fixes

* Add missing fields to the model types ([7b7b41f](https://github.com/googleapis/java-genai/commit/7b7b41f05b21a37c4ce9bd712a28d9432d07105f))
* Fix base_steps parameter for recontext_image ([85aaa79](https://github.com/googleapis/java-genai/commit/85aaa79121489181879f1e0ad84d683c1d000f53))
* Fix models.list() filter parameter ([123ada5](https://github.com/googleapis/java-genai/commit/123ada51cba5de22ec755716e551f398a6210a38))


### Documentation

* Add README for Files API ([6d206aa](https://github.com/googleapis/java-genai/commit/6d206aaf3f85cf1460418ddabcd1b6cd07693357))

## [1.26.0](https://github.com/googleapis/java-genai/compare/v1.25.0...v1.26.0) (2025-11-05)


### Features

* Add clearXxx methods to data type builders ([a4900c9](https://github.com/googleapis/java-genai/commit/a4900c97ec7c256b45b729ae68404aea4fbf5830))
* add complete stats to BatchJob ([659c65c](https://github.com/googleapis/java-genai/commit/659c65cc777c35ae5dc8ef84caf00f4aa30bb3db))
* Add FileSearch tool and associated FileSearchStore management APIs ([8ada6ef](https://github.com/googleapis/java-genai/commit/8ada6efb0c2b2a2231acc08c952e2fc76e20a29d))
* Add image_size to ImageConfig (Early Access Program) ([c1af981](https://github.com/googleapis/java-genai/commit/c1af981dc19fad22db68126ec6153d9fa20ec734))
* Added phish filtering feature. ([ed4e12c](https://github.com/googleapis/java-genai/commit/ed4e12c44e9fec3c2131a52d995cb0602dc246a0))
* Return response headers for generateContentStream ([82a8118](https://github.com/googleapis/java-genai/commit/82a8118968bb5da37ec81dce83580bd86767bf62))
* Support lists as function parameters in AFC (fixes [#527](https://github.com/googleapis/java-genai/issues/527)) ([452d2e5](https://github.com/googleapis/java-genai/commit/452d2e50a6c13ab94d5de380d32dcb164379a2cb))


### Bug Fixes

* disable AFC when there are AFC incompatible tool presented. ([6099d87](https://github.com/googleapis/java-genai/commit/6099d871ff703606dffbf533a231869725f32bc6))


### Documentation

* add blank line before version update marker ([82616c2](https://github.com/googleapis/java-genai/commit/82616c23be6cf7295049026985d105c5c2993c7a))
* Update Java SDK README spacing ([82616c2](https://github.com/googleapis/java-genai/commit/82616c23be6cf7295049026985d105c5c2993c7a))

## [1.25.0](https://github.com/googleapis/java-genai/compare/v1.24.0...v1.25.0) (2025-10-29)


### Features

* Add safety_filter_level and person_generation for Imagen upscaling ([09a8075](https://github.com/googleapis/java-genai/commit/09a80754b202fdf903039341f5266f62d9b879cb))
* Add support for preference optimization tuning in the SDK. ([5d4123c](https://github.com/googleapis/java-genai/commit/5d4123c0391d443e94bb1e81524ccae8779462d7))
* Added Operations.get which is a generic method which will handle all Operation types. ([c1dc32f](https://github.com/googleapis/java-genai/commit/c1dc32f84d0e4d14a16345dcb404c8b2bef05338))
* Pass file name to the backend when uploading with a file path ([081a9a6](https://github.com/googleapis/java-genai/commit/081a9a6a67d1ba542edb1d1330dfa56579204a43))
* support default global location when not using api key with vertexai backend ([f9028a7](https://github.com/googleapis/java-genai/commit/f9028a71d4e736a8dc97daa54e6e4275b5016abd))
* Support retries in API requests ([3d5de00](https://github.com/googleapis/java-genai/commit/3d5de000277eb0da172d6b19795c6f2d4b88c213))


### Documentation

* Add docstring for classes and fields that are not supported in Gemini or Vertex API ([7a03dac](https://github.com/googleapis/java-genai/commit/7a03dac0a4e3388f98be199765794fcf511bfe83))
* Add docstring for enum classes that are not supported in Gemini or Vertex API ([830a12f](https://github.com/googleapis/java-genai/commit/830a12f3dcbb8beb1dd5ff3ff82f6b19ebb2af93))
* Add documentation for the retry behavior ([4fbcf51](https://github.com/googleapis/java-genai/commit/4fbcf514321fdc2cbee1393fc6babe33fd0e5e74))

## [1.24.0](https://github.com/googleapis/java-genai/compare/v1.23.0...v1.24.0) (2025-10-22)


### Features

* Add enable_enhanced_civic_answers in GenerationConfig ([684a2c5](https://github.com/googleapis/java-genai/commit/684a2c5b582fa4ca13cb9cfe819ef759778101b0))
* support createEmbeddings in Batches.java ([8947f6f](https://github.com/googleapis/java-genai/commit/8947f6fc20fbdd90a7d17071dee1bd2e5bea0c3e))
* support jailbreak in HarmCategory and BlockedReason ([3dab40b](https://github.com/googleapis/java-genai/commit/3dab40bc367168ed48d8d1acfb278f5bc6edb83f))


### Bug Fixes

* Make async methods in Batches module truly non-blocking ([f2ae75a](https://github.com/googleapis/java-genai/commit/f2ae75ac364702f483c376e458a120d1ffa93b17))
* Make async methods in Caches, Tuning, and Operations modules truly non-blocking ([db56239](https://github.com/googleapis/java-genai/commit/db56239bbebbfe3cb95e00d2d3eac253b76f22fe))
* Make async methods in Models module truly non-blocking ([c205d01](https://github.com/googleapis/java-genai/commit/c205d0172ca40e01f7d8de17a3bc9d38eeb5fc21))

## [1.23.0](https://github.com/googleapis/java-genai/compare/v1.22.0...v1.23.0) (2025-10-15)


### Features

* Support video extension for Veo on Gemini Developer API ([b398509](https://github.com/googleapis/java-genai/commit/b398509697a3e9aa27bad5e804382c5a4db333ab))

## [1.22.0](https://github.com/googleapis/java-genai/compare/v1.21.0...v1.22.0) (2025-10-10)


### Features

* Enable Google Maps tool for Genai. ([a4baf3c](https://github.com/googleapis/java-genai/commit/a4baf3c610ddcb1ed36c1501fcb2248b5a6bd610))
* Support enableWidget feature in GoogleMaps ([aefbd5c](https://github.com/googleapis/java-genai/commit/aefbd5c1519f453cd2fe158a2765c195a9454322))
* Support Gemini batch inline request's metadata and add test coverage to safety setting ([17033b3](https://github.com/googleapis/java-genai/commit/17033b38a93d6952b29699f5a4c79ed9dd862976))

## [1.21.0](https://github.com/googleapis/java-genai/compare/v1.20.0...v1.21.0) (2025-10-08)


### Features

* Add `NO_IMAGE` enum value to `FinishReason` ([6b00c0b](https://github.com/googleapis/java-genai/commit/6b00c0b7dc8c85fcefc5aac643c3588048317614))
* Add labels field to Imagen configs ([e69cf68](https://github.com/googleapis/java-genai/commit/e69cf68583ca581f1a7fad89b04292036433cdb4))
* Add thinking_config for live ([274c21d](https://github.com/googleapis/java-genai/commit/274c21d34310e630b9b4ad296b4c8314a4249d0c))
* Add utility methods for creating `FunctionResponsePart` and creating FunctionResponse `Part` with `FunctionResponseParts` ([af16a4c](https://github.com/googleapis/java-genai/commit/af16a4c994e0cc4e6fbc2cdbda825246df9aa253))
* Enable Ingredients to Video and Advanced Controls for Veo on Gemini Developer API (Early Access Program) ([4c42e65](https://github.com/googleapis/java-genai/commit/4c42e6527a7fe43c0b534e381d65b5d9650e8709))


### Bug Fixes

* Ensure Live server message are properly converted ([206dc88](https://github.com/googleapis/java-genai/commit/206dc88e3b220a875f784a507fc9470bc411de36))

## [1.20.0](https://github.com/googleapis/java-genai/compare/v1.19.0...v1.20.0) (2025-10-01)


### Features

* Add `ImageConfig` to `GenerateContentConfig` ([6fb5eba](https://github.com/googleapis/java-genai/commit/6fb5eba0e916ada8f300dd5ad333f269e9044ea3))

## [1.19.0](https://github.com/googleapis/java-genai/compare/v1.18.0...v1.19.0) (2025-09-30)


### Features

* expose session id in Live API ([b6d5389](https://github.com/googleapis/java-genai/commit/b6d5389899bd1443d5c508776dfe5909eb1d7400))
* rename ComputerUse tool (early access) ([4bbba2b](https://github.com/googleapis/java-genai/commit/4bbba2b53eedec0b28a5d98d7fc193683c565f50))

## [1.18.0](https://github.com/googleapis/java-genai/compare/v1.17.0...v1.18.0) (2025-09-25)


### Features

* Add FunctionResponsePart & ToolComputerUse.excludedPredefinedFunctions ([1a24bed](https://github.com/googleapis/java-genai/commit/1a24bedc752851236b0a7239a7dba7090e4ac4e8))
* Support Imagen 4 Ingredients on Vertex ([b5eed8d](https://github.com/googleapis/java-genai/commit/b5eed8d1323a3d37b53c1d8c5c5557392ce7ed44))


### Bug Fixes

* Expose `JOB_STATE_RUNNING` and `JOB_STATE_EXPIRED` for Gemini Batches states ([c5b4fdf](https://github.com/googleapis/java-genai/commit/c5b4fdf58b9d0d74efdd2c7e740bed8b6b661c99))
* initialization of `pre_tuned_model_checkpoint_id` from tuning config. ([c293633](https://github.com/googleapis/java-genai/commit/c293633a8fe298668f030ba3b257347a8fd0eedf))
* Make async generateContent and generateContentStream truly non-blocking ([5cb18fd](https://github.com/googleapis/java-genai/commit/5cb18fd4f07f9b1f21efb82fe961e473325f6257))
* only run unit tests in github action ([9b2861b](https://github.com/googleapis/java-genai/commit/9b2861bb79d50c10c152aa010bedf0bc48a04ad8))

## [1.17.0](https://github.com/googleapis/java-genai/compare/v1.16.0...v1.17.0) (2025-09-16)


### Features

* Add 'turn_complete_reason' and 'waiting_for_input' fields. ([5bc4873](https://github.com/googleapis/java-genai/commit/5bc48732fd9281162942b158de34173343d7b179))
* Add `VideoGenerationMaskMode` enum for Veo 2 Editing ([e5c8277](https://github.com/googleapis/java-genai/commit/e5c82778586dfee4ed7d04a9eabb2a4d8eac6185))
* Add labels to create tuning job config ([695e17a](https://github.com/googleapis/java-genai/commit/695e17a7b1adebbccb1651d30b768d27f81c3977))
* generate the function_call class's converters ([38703c7](https://github.com/googleapis/java-genai/commit/38703c726606cbe1b6f5f5f4eb809310b0df94a8))
* java local tokenizer ([d774185](https://github.com/googleapis/java-genai/commit/d7741856cafd3b8e05803f7b452335fbc4ce8977))
* Support Veo 2 Editing on Vertex ([d401d3c](https://github.com/googleapis/java-genai/commit/d401d3cf6a5f9ef3d2a76a548eed9d218169170e))


### Bug Fixes

* Enable `id` field in `FunctionCall` for Vertex AI. ([3773fe7](https://github.com/googleapis/java-genai/commit/3773fe75007b9ce83692de0031853f0f607bff3e))
* update Live API audio example with better interruption handling ([cad8df9](https://github.com/googleapis/java-genai/commit/cad8df9c4edaf0806a641869fef6379ed05f0189))

## [1.16.0](https://github.com/googleapis/java-genai/compare/v1.15.0...v1.16.0) (2025-09-02)


### Features

* Add resolution field for Gemini Developer API Veo 3 generation ([eec410c](https://github.com/googleapis/java-genai/commit/eec410c5b68de471e9a824e61f0efb819841dfe6))
* add the response body for generateContent ([a011580](https://github.com/googleapis/java-genai/commit/a0115804e438bac120d5155c91ece53c79ada677))


### Documentation

* Refactor/update docstrings for Imagen and Veo ([2470101](https://github.com/googleapis/java-genai/commit/24701018feb91d147bf1817b04752e2595bf40ab))

## [1.15.0](https://github.com/googleapis/java-genai/compare/v1.14.0...v1.15.0) (2025-08-27)


### Features

* add `sdkHttpResponse.headers` to *Delete responses. ([4be038d](https://github.com/googleapis/java-genai/commit/4be038de86c782d103d21258db51055f35e5af21))
* Add output_gcs_uri to Imagen upscale_image ([7649467](https://github.com/googleapis/java-genai/commit/76494678d3937229778c5063b4f4ff340f977bba))
* add the response body for generateContent ([6e28ab4](https://github.com/googleapis/java-genai/commit/6e28ab4236565be61fb11e79ca9f2f31a2013598))
* add the response body for generateContent ([b2a5b3f](https://github.com/googleapis/java-genai/commit/b2a5b3f5a6ef7a8bb4d011980d90ffdc3c745603))
* Add VALIDATED mode into FunctionCallingConfigMode ([4bb8680](https://github.com/googleapis/java-genai/commit/4bb868046199d3249f75ede213ef7d77e0b7783f))
* Add VideoGenerationReferenceType enum for generate_videos ([df9d910](https://github.com/googleapis/java-genai/commit/df9d910537ec7de6188f777801b4d50e84cd91e7))
* Support GenerateVideosSource for Veo GenerateVideos ([c26af63](https://github.com/googleapis/java-genai/commit/c26af6396002cf21c0ed272290d44b09b6a41840))
* support tunings.cancel in the genai SDK for Python, Java, JS, and Go ([9982251](https://github.com/googleapis/java-genai/commit/9982251d2dd80d3151aefb4462d9e4864d8e064e))


### Documentation

* Refactor model IDs into a Constants class ([dacd787](https://github.com/googleapis/java-genai/commit/dacd7875d41f810e50f2655e5d0e62f031197e61))

## [1.14.0](https://github.com/googleapis/java-genai/compare/v1.13.0...v1.14.0) (2025-08-22)


### Features

* Add add_watermark field for recontext_image (Virtual Try-On, Product Recontext) ([5aacbc0](https://github.com/googleapis/java-genai/commit/5aacbc06435fb36fffde0c3641b3077493f13577))


### Bug Fixes

* Fix the bug that files.create doesn't return the upload URL correctly ([eb40c5f](https://github.com/googleapis/java-genai/commit/eb40c5f7f255b46a7a820da044e210127c7aac18))


### Documentation

* update TokensInfo docstring ([48eba7f](https://github.com/googleapis/java-genai/commit/48eba7fcb369537ca4266ec61107e016f7c242ed))

## [1.13.0](https://github.com/googleapis/java-genai/compare/v1.12.0...v1.13.0) (2025-08-18)


### Features

* expose JsonSerializable.stringToJsonNode to help user better use *JsonSchema fields. ([35d783b](https://github.com/googleapis/java-genai/commit/35d783b5d1655b6f0d52afefa633c608f39d4e01))
* Return response headers for all methods (except streaming methods) ([7e8b71b](https://github.com/googleapis/java-genai/commit/7e8b71b0769362a728e2bf9b93738563113a4edc))
* Support Imagen image segmentation on Vertex ([e2a561b](https://github.com/googleapis/java-genai/commit/e2a561b11b53f3a7cc30aacb4a0dcf6a26e01645))
* Support Veo 2 Reference Images to Video Generation on Vertex ([2f5580f](https://github.com/googleapis/java-genai/commit/2f5580fd1e78d6e8e4f371f291dacf98c7c617ef))

## [1.12.0](https://github.com/googleapis/java-genai/compare/v1.11.0...v1.12.0) (2025-08-13)


### Features

* enable continuous fine-tuning on a pre-tuned model in the SDK. ([e49d350](https://github.com/googleapis/java-genai/commit/e49d3509355f717d391a88b6ff1a6f4f6d83fddc))
* support document name in grounding metadata ([8273922](https://github.com/googleapis/java-genai/commit/8273922ebfbce4ffafa8993bcc6928b47b5ff821))
* Support exclude_domains in Google Search and Enterprise Web Search ([e975d28](https://github.com/googleapis/java-genai/commit/e975d284f78e0c9a3cd2199d304b4739bad36fe1))

## [1.11.0](https://github.com/googleapis/java-genai/compare/v1.10.0...v1.11.0) (2025-08-06)


### Features

* Add image_size field for Gemini Developer API Imagen 4 generation ([c50c755](https://github.com/googleapis/java-genai/commit/c50c755c08efbed5a62e1006890b1d0bd9956702))
* enable responseId for Gemini Developer API ([4912ff4](https://github.com/googleapis/java-genai/commit/4912ff421d6d3bc40edd70a939f71f5f33f58597))
* support extraBody in HttpOptions class ([036bac8](https://github.com/googleapis/java-genai/commit/036bac89fda15022ec4d9c5c73ba81ad0a6cc9be))
* Support image recontext on Vertex ([e7de8c8](https://github.com/googleapis/java-genai/commit/e7de8c83bbd2e7e37c2198c3501e2d5bee58c0a2))
* Support new enum types for UrlRetrievalStatus ([cb27222](https://github.com/googleapis/java-genai/commit/cb27222a7f7cdf442a7d6b61496709f7cf084a91))
* support response headers in Go for all methods. ([222b41e](https://github.com/googleapis/java-genai/commit/222b41e196afc13775cc22292a58567d7b4859fa))


### Bug Fixes

* Remove duplicate JavaTimeModule in JsonSerializable ([a7dbd4c](https://github.com/googleapis/java-genai/commit/a7dbd4c527456f20aa5d154bde14f74f6e66d174))


### Documentation

* Add Imagen and Veo to README ([cc0a0aa](https://github.com/googleapis/java-genai/commit/cc0a0aa28cae618acf617ab92819df78d80afea5))
* Add latest models features in README ([a2eccaf](https://github.com/googleapis/java-genai/commit/a2eccafae5c6c9b82341a148b572bf9bc80f241b))
* mark Client as thread safe and Chat as not thread safe ([be3e50e](https://github.com/googleapis/java-genai/commit/be3e50e4217780329c0636fd7f8a1b743e7f9597))

## [1.10.0](https://github.com/googleapis/java-genai/compare/v1.9.0...v1.10.0) (2025-07-23)


### Features

* Add image_size field for Vertex Imagen 4 generation ([950c0c6](https://github.com/googleapis/java-genai/commit/950c0c657f786039e3a301bf1237a57ae324ff62))
* Support API keys for VertexAI mode for Java SDK ([826c0dc](https://github.com/googleapis/java-genai/commit/826c0dca02e06fcb6c7980259b23e955db176ec6))
* Support http headers in GenerateContentResponse ([5282774](https://github.com/googleapis/java-genai/commit/528277406279d772c01ac2a48544962408ac235b))


### Bug Fixes

* Defer loading ADC when credentials is provided explicitly in Live API ([a540614](https://github.com/googleapis/java-genai/commit/a5406140aeaf9774265a5bcce79dae0707ed9287))
* **live:** Enhance security by moving api key from query parameters to header ([e48c7f1](https://github.com/googleapis/java-genai/commit/e48c7f1e73dadf5c5198f9b58cea322deb7a4ed0))
* Pager throws an exception if list request returns nothing(correct behavior is returning a Pager without any items in it) ([0a2301b](https://github.com/googleapis/java-genai/commit/0a2301b19fcd3e4d3694d42780da8f5ffe5f9207))


### Documentation

* Update README with latest features in Client ([dcf70cc](https://github.com/googleapis/java-genai/commit/dcf70cc64a93355cdc6a2eedf172399dd332750e))

## [1.9.0](https://github.com/googleapis/java-genai/compare/v1.8.0...v1.9.0) (2025-07-16)


### Features

* Add `addWatermark` parameter to the edit image configuration. ([c4598da](https://github.com/googleapis/java-genai/commit/c4598da0903d5dacb0c7bb4462aec1226ba259bf))
* add Tuning support for Java ([0ab209d](https://github.com/googleapis/java-genai/commit/0ab209db99bf98b58f7273fb12843984c42cb910))


### Documentation

* Update generated video resolution config docstring ([9a2ced8](https://github.com/googleapis/java-genai/commit/9a2ced8ed3a1896b8170cc9ca117b61cb9eea705))

## [1.8.0](https://github.com/googleapis/java-genai/compare/v1.7.0...v1.8.0) (2025-07-09)


### Features

* Add new languages for Imagen 4 prompt language ([7e1e6d2](https://github.com/googleapis/java-genai/commit/7e1e6d2ead45c7a0737e4a010fce266fb436d2dd))

## [1.7.0](https://github.com/googleapis/java-genai/compare/v1.6.0...v1.7.0) (2025-07-01)


### Features

* Support Batches delete ([782465d](https://github.com/googleapis/java-genai/commit/782465d9c85c3637586fef490983771c4b4b5df0))
* Support different media input in Vertex Live API ([7f4c6bf](https://github.com/googleapis/java-genai/commit/7f4c6bf58804764d568bd3412086ead75a388df0))


### Bug Fixes

* Remove default timeout ([d1f6201](https://github.com/googleapis/java-genai/commit/d1f6201892de9f37b913044dd494c68b81bcc13a))

## [1.6.0](https://github.com/googleapis/java-genai/compare/v1.5.0...v1.6.0) (2025-06-25)


### Features

* Add compressionQuality enum for generate_videos ([b0e665b](https://github.com/googleapis/java-genai/commit/b0e665bf6ae09dc2146e49714a4855443a270776))
* Add enhance_input_image and image_preservation_factor fields for upscale_image ([94a329a](https://github.com/googleapis/java-genai/commit/94a329abcd3c668065abfae511b55766ed051668))
* allow users to access headers for generateContent method and generateContentStream ([0315357](https://github.com/googleapis/java-genai/commit/03153578ea64f0c34836ac62395aa867f44eac07))
* Batches support in Java ([5ce13e9](https://github.com/googleapis/java-genai/commit/5ce13e9c79c4791d405b1dfa71c1d9358dc5a08d))
* configure release-please to automatically update package version across all files during releases. ([9131ac2](https://github.com/googleapis/java-genai/commit/9131ac24fde477afb25deb516c7ace51530ed8d9))
* expose the responseJsonSchema in GenerateContentConfig ([9d9acdb](https://github.com/googleapis/java-genai/commit/9d9acdb494358155cbb3c2ce3acbe55209bbdb7e))
* support client.caches.update method ([345c2b9](https://github.com/googleapis/java-genai/commit/345c2b93789913d6d84cdde9c30f86ec4041bd24))


### Documentation

* add more comments to make it easier to follow live api code, and to explain the usage of new concepts like thenCompose. ([96c792c](https://github.com/googleapis/java-genai/commit/96c792c3aa84f632e0b46bb986de403ecbf4edc1))
* improve generate images documentation ([44c21dd](https://github.com/googleapis/java-genai/commit/44c21dd78e0d0be0e681e991b15d3dae3be360f2))
* Update description of thinking_budget. ([265f20a](https://github.com/googleapis/java-genai/commit/265f20addd9e9e76c249e6042d653c8cec9f27a4))

## [1.5.0](https://github.com/googleapis/java-genai/compare/v1.4.1...v1.5.0) (2025-06-19)


### Features

* Add a default 5 minutes timeout to the Java SDK. ([d80d23b](https://github.com/googleapis/java-genai/commit/d80d23b7a8cb440410f9dd9556ee1259352285ef))
* enable json schema for controlled output and function declaration. ([35d93d8](https://github.com/googleapis/java-genai/commit/35d93d84a349cf1032d2cc61bf4e3128386759d6))
* Parametrize model ID in Java genai samples and set hardcoded model IDs as default values if model ID not passed in by user. ([c1b0948](https://github.com/googleapis/java-genai/commit/c1b0948af51bc746cbf350e4b6a60b6199d3ffe2))
* resolve deep conditional nesting in Live API examples for better readability. ([aa71865](https://github.com/googleapis/java-genai/commit/aa718657f53f4bd9132a9b00816e7f5581108dec))


### Documentation

* fix small typo in comments ([a6bfe36](https://github.com/googleapis/java-genai/commit/a6bfe36843bed1575a03c86a092a17b35e48fd9f))

## [1.4.1](https://github.com/googleapis/java-genai/compare/v1.4.0...v1.4.1) (2025-06-12)


### Bug Fixes

* Add backwards compatibility for generateVideos in Java SDK ([504984a](https://github.com/googleapis/java-genai/commit/504984ad8bc30636e47a6838d89fe097766e9484))


### Miscellaneous Chores

* release 1.4.1 ([49a57d8](https://github.com/googleapis/java-genai/commit/49a57d878379729a14ccd4846af67c67bbaf2419))

## [1.4.0](https://github.com/googleapis/java-genai/compare/v1.3.0...v1.4.0) (2025-06-11)


### Features

* Add datastore_spec field for VertexAISearch ([8c8b47a](https://github.com/googleapis/java-genai/commit/8c8b47a79cbf9014325aeeab156f99db6181a265))
* Add support for Veo frame interpolation and video extension ([94f5e41](https://github.com/googleapis/java-genai/commit/94f5e41da83de8caa24c27b32d430f8ea5e6d0d8))
* RAG - Introducing context storing for Gemini Live API. ([d63d625](https://github.com/googleapis/java-genai/commit/d63d62583f554aca88062dfbf3c77cc65da812dd))
* Support maxConnections and maxConnectionsPerHost in Client instantiation ([7a4a8ac](https://github.com/googleapis/java-genai/commit/7a4a8ac093adba2f0fb7b2558b41274da450010c))
* Support passing builder classes to setter methods ([996c994](https://github.com/googleapis/java-genai/commit/996c994d6a2697b8b79dd48a7460eed49a408884))
* Support passing builder varargs to setter methods ([0b08524](https://github.com/googleapis/java-genai/commit/0b08524b002b656c9c8a7a7f667a9efabb69dcfb))
* Support varargs in Enum setter methods ([cd63fd0](https://github.com/googleapis/java-genai/commit/cd63fd0bdb124d8d2bebabc68f29a504ae1f3e7e))
* Support varargs in setter methods ([0b08524](https://github.com/googleapis/java-genai/commit/0b08524b002b656c9c8a7a7f667a9efabb69dcfb))


### Documentation

* Add javadoc for setter methods in types classes ([996c994](https://github.com/googleapis/java-genai/commit/996c994d6a2697b8b79dd48a7460eed49a408884))

## [1.3.0](https://github.com/googleapis/java-genai/compare/v1.2.0...v1.3.0) (2025-06-04)


### Features

* Add enhance_prompt field for Gemini Developer API generate_videos ([944af16](https://github.com/googleapis/java-genai/commit/944af164b380eaca7cf4d0be4a346807ad8d0ad9))
* Enable url_context for Vertex ([cc772d8](https://github.com/googleapis/java-genai/commit/cc772d83354507ee576b6d6fc44c7e435e6fc3f0))
* **java:** Support `GEMINI_API_KEY` as environment variable for setting API key. ([84e7588](https://github.com/googleapis/java-genai/commit/84e7588d1638770c7bf465aa25c5c7402404c918))


### Documentation

* Updating docs to include how to initialize client via environment variables ([7fc3e62](https://github.com/googleapis/java-genai/commit/7fc3e624cc46f897bee0d1fa12276e7dc0727c2f))

## [1.2.0](https://github.com/googleapis/java-genai/compare/v1.1.0...v1.2.0) (2025-05-30)


### Features

* Adding `thought_signature` field to the `Part` to store the signature for thoughts. ([dc9c8f7](https://github.com/googleapis/java-genai/commit/dc9c8f79bff4f449ccec61e078c0edae4f85f4cb))
* include UNEXPECTED_TOOL_CALL enum value to FinishReason for Vertex AI APIs. ([8186ff0](https://github.com/googleapis/java-genai/commit/8186ff0064b6f22e9f30e047ec0aebbb4804becc))


### Bug Fixes

* Rename LiveEphemeralParameters to LiveConnectConstraints. ([0655609](https://github.com/googleapis/java-genai/commit/0655609c854a5b147974016cee5f28d712b4a06f))

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
