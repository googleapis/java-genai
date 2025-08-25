package com.google.genai.examples;

import com.google.genai.LocalTokenizer;

public class LocalCountTokens {
    public static void main(String[] args) {
        LocalTokenizer tokenizer = new LocalTokenizer("gemini-1.5-flash");
        System.out.println("Count for 'Hello world': " + tokenizer.countTokens("Hello world").totalTokens());
    }
}
