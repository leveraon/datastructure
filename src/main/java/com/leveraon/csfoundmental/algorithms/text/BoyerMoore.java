package com.leveraon.csfoundmental.algorithms.text;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoyerMoore {
    /**
     * Returns the lowest index at which substring pattern begins in text (or else
     * -1).
     */
    public static int findBoyerMoore(char[] text, char[] pattern) {
        int textLen = text.length;
        int patternLen = pattern.length;
        if (patternLen == 0)
            return 0; // trivial search for empty string
        Map<Character, Integer> last = new HashMap<>(); // the 'last' map
        for (int i = 0; i < textLen; i++)
            last.put(text[i], -1); // set -1 as default for all text characters
        for (int k = 0; k < patternLen; k++)
            last.put(pattern[k], k); // rightmost occurrence in pattern is last
        // start with the end of the pattern aligned at index m-1 of the text
        int i = patternLen - 1; // an index into the text
        int k = patternLen - 1; // an index into the pattern
        while (i < textLen) {
            if (text[i] == pattern[k]) { // a matching character
                if (k == 0)
                    return i; // entire pattern has been found
                i--; // otherwise, examine previous
                k--; // characters of text/pattern
            } else {
                i += patternLen - Math.min(k, 1 + last.get(text[i])); // case analysis for jump step
                k = patternLen - 1; // restart at end of pattern
            }
        }
        return -1; // pattern was never found
    }

    public static void main(String[] args) {
        String sentence = "thereisawillthereisaway";
        String pattern = "the";

        log.info("String {} contains pattern '{}' at index: {}", sentence, pattern,
                findBoyerMoore(sentence.toCharArray(), pattern.toCharArray()));
    }
}
