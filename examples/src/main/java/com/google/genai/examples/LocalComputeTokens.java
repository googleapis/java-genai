package com.google.genai.examples;

import com.google.genai.LocalTokenizer;

public class LocalComputeTokens {
    public static void main(String[] args) {
        LocalTokenizer tokenizer = new LocalTokenizer("gemini-1.5-flash");
        System.out.println("Compute tokens for 'Hello world': " + tokenizer.computeTokens("Hello world").toJson());
    }
}
