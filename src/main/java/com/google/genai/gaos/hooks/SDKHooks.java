/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.gaos.hooks;

// This file is written once by speakeasy code generation and
// thereafter will not be overwritten by speakeasy updates. As a
// consequence any customization of this class will be preserved.

public final class SDKHooks {

    private SDKHooks() {
        // prevent instantiation
    }

    public static void initialize(com.google.genai.gaos.utils.Hooks hooks) {
        // register synchronous hooks here
        // hooks.registerBeforeRequest(...);
        // hooks.registerAfterSuccess(...);
        // hooks.registerAfterError(...);

        // for more information see
        // https://www.speakeasy.com/docs/additional-features/sdk-hooks
    }

    public static void initialize(com.google.genai.gaos.utils.AsyncHooks asyncHooks) {
        // register async hooks here
        // asyncHooks.registerBeforeRequest(...);
        // asyncHooks.registerAfterSuccess(...);
        // asyncHooks.registerAfterError(...);

        // NOTE: If you have existing synchronous hooks, you can adapt them using HookAdapters:
        // asyncHooks.registerAfterError(com.google.genai.gaos.utils.HookAdapters.adapt(mySyncHook));

        // PERFORMANCE TIP: For better performance, implement async hooks directly using
        // non-blocking I/O (NIO) APIs instead of adapting synchronous hooks, as adapters
        // offload execution to the ForkJoinPool which can introduce overhead.

        // for more information see
        // https://www.speakeasy.com/docs/additional-features/sdk-hooks
    }
}
