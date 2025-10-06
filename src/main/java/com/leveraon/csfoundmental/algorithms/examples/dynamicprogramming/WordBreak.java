package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {

	// Memoization map: Stores List<String> for each starting index
	private Map<Integer, List<String>> memo;
	// For O(1) average time dictionary lookup
	private Set<String> wordSet;

	/**
	 * Finds all possible sentences by breaking the string `s` into words from
	 * `wordDict`.
	 *
	 * @param s        The input string.
	 * @param wordDict The dictionary of valid words.
	 * @return A list of all possible sentences.
	 */
	public List<String> wordBreak(String s, List<String> wordDict) {
		memo = new HashMap<>();
		wordSet = new HashSet<>(wordDict); // Convert list to set for faster lookups
		return backtrack(s, 0);
	}

	/**
	 * Recursive helper function with memoization.
	 *
	 * @param s     The original input string.
	 * @param start The starting index of the current substring to break.
	 * @return A list of all possible ways to break the substring s[start...] into
	 *         valid words.
	 */
	private List<String> backtrack(String s, int start) {
		// Base case: If we reached the end of the string, it's a valid break.
		if (start == s.length()) {
			// Return a list containing an empty string to signify a complete path.
			// This allows the calling function to concatenate actual words without issues.
			List<String> result = new ArrayList<>();
			result.add("");
			return result;
		}

		// Check if this subproblem has already been solved.
		if (memo.containsKey(start)) {
			return memo.get(start);
		}

		List<String> currentResult = new ArrayList<>();

		// Iterate through all possible end points for the current word.
		// `end` goes from `start + 1` to `s.length()`.
		for (int end = start + 1; end <= s.length(); end++) {
			String word = s.substring(start, end);

			// If the current word is in the dictionary:
			if (wordSet.contains(word)) {
				// Recursively find solutions for the rest of the string.
				List<String> sentencesFromSuffix = backtrack(s, end);

				// Combine the current word with each sentence found from the suffix.
				for (String suffixSentence : sentencesFromSuffix) {
					// If suffixSentence is empty (means end of string was reached),
					// just add the word. Otherwise, add word + " " + suffixSentence.
					if (suffixSentence.isEmpty()) {
						currentResult.add(word);
					} else {
						currentResult.add(word + " " + suffixSentence);
					}
				}
			}
		}

		// Store the result for this subproblem before returning.
		memo.put(start, currentResult);
		return currentResult;
	}

	// Wrong
//	static String wordBreak(String word, String[] dictionary) {
//		StringBuilder sentence = new StringBuilder();
//		Map<String, Integer> wordMap = new HashMap<>();
//
//		for (String w : dictionary) {
//			int wLen = w.length();
//
//			int matchLen = word.length() - wLen;
//			if (matchLen < 0)
//				continue;
//
//			for (int i = 0; i <= matchLen; i++) {
//
//				int k = 0;
//				while (k < wLen && word.charAt(i + k) == w.charAt(k)) {
//					k++;
//				}
//
//				if (wLen == k) {
//					wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
//				}
//			}
//		}
//
//		System.out.println("Possible words are " + wordMap);
//
//		return sentence.toString();
//	}

	public static void main(String[] args) {
		String test = "ifyouregoodwiththisapproachthenillpushthosechangestoserverdoesthatsoundgoodtoyou";
		String[] dictionary = { "good", "approach", "server", "nice", "job" };

		WordBreak wb = new WordBreak();

//		System.out.println("Break word wrong " + wordBreak(test, dictionary));
//		List<String> dict = Arrays.asList("good", "approach", "server", "nice", "job");
//		System.out.println("s: \"" + test + "\", wordDict: " + dict);
//		System.out.println("Break word " + wb.wordBreak(test, dict));

        // Test Case 1
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        List<String> result1 = wb.wordBreak(s1, wordDict1);
        // Expected: ["leet code"]
        System.out.println("s: \"" + s1 + "\", wordDict: " + wordDict1);
        System.out.println("Result: " + result1);

        // Test Case 2
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        List<String> result2 = wb.wordBreak(s2, wordDict2);
        // Expected: ["apple pen apple", "applepen apple"] (order might vary)
        System.out.println("\ns: \"" + s2 + "\", wordDict: " + wordDict2);
        System.out.println("Result: " + result2);

        // Test Case 3
        String s3 = "catsanddog";
        List<String> wordDict3 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> result3 = wb.wordBreak(s3, wordDict3);
        // Expected: ["cats and dog", "cat sand dog"] (order might vary)
        System.out.println("\ns: \"" + s3 + "\", wordDict: " + wordDict3);
        System.out.println("Result: " + result3);

        // Test Case 4: No wbution
        String s4 = "pineapplepenapple";
        List<String> wordDict4 = Arrays.asList("apple", "pen", "pine", "pineapple");
        List<String> result4 = wb.wordBreak(s4, wordDict4);
        // Expected: ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
        System.out.println("\ns: \"" + s4 + "\", wordDict: " + wordDict4);
        System.out.println("Result: " + result4);

        // Test Case 5: Complex, large number of words
        String s5 = "aaaaaaa";
        List<String> wordDict5 = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa");
        List<String> result5 = wb.wordBreak(s5, wordDict5);
        System.out.println("\ns: \"" + s5 + "\", wordDict: " + wordDict5);
        System.out.println("Result (first few only due to length): " + result5.subList(0, Math.min(5, result5.size())));
        System.out.println("Total solutions for s5: " + result5.size()); // Should be 64
	}
}
