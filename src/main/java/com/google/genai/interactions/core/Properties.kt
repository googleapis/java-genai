/*
* Copyright 2025 Google LLC
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

@file:JvmName("Properties")

package com.google.genai.interactions.core

import com.google.genai.interactions.client.GeminiNextGenApiClient

fun getOsArch(): String {
    val osArch = System.getProperty("os.arch")

    return when (osArch) {
        null -> "unknown"
        "i386",
        "x32",
        "x86" -> "x32"
        "amd64",
        "x86_64" -> "x64"
        "arm" -> "arm"
        "aarch64" -> "arm64"
        else -> "other:$osArch"
    }
}

fun getOsName(): String {
    val osName = System.getProperty("os.name")
    val vendorUrl = System.getProperty("java.vendor.url")

    return when {
        osName == null -> "Unknown"
        osName.startsWith("Linux") && vendorUrl == "http://www.android.com/" -> "Android"
        osName.startsWith("Linux") -> "Linux"
        osName.startsWith("Mac OS") -> "MacOS"
        osName.startsWith("Windows") -> "Windows"
        else -> "Other:$osName"
    }
}

fun getOsVersion(): String = System.getProperty("os.version", "unknown") ?: "unknown"

fun getPackageVersion(): String =
    GeminiNextGenApiClient::class.java.`package`?.implementationVersion ?: "unknown"

fun getJavaVersion(): String = System.getProperty("java.version", "unknown") ?: "unknown"
