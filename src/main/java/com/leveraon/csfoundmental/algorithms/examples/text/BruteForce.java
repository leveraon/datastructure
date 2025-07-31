package com.leveraon.csfoundmental.algorithms.examples.text;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BruteForce {

    /**
     * Returns the lowest index at which substring pattern begins in text (or else
     * -1).
     */
    public static int findBrute(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        for (int i = 0; i <= n - m; i++) { // try every starting index within text
            int k = 0; // k is index into pattern
            while (k < m && text[i + k] == pattern[k]) // kth character of pattern matches
                k++;
            if (k == m) // if we reach the end of the pattern,
                return i; // substring text[i..i+m-1] is a match
        }
        return -1; // search failed
    }

    public static void main(String[] args) {
        String sentence = "thereisawillthereisaway";
        String pattern = "saw";

        log.info("String {} contains pattern '{}' at index: {}", sentence, pattern,
                findBrute(sentence.toCharArray(), pattern.toCharArray()));

    }

}
