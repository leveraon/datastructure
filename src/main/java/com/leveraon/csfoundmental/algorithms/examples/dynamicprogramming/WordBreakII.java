package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.*;

public class WordBreakII {

    /**
     * Main function to be called by the user.
     * It sets up the dictionary for fast lookups and initializes the memoization
     * cache.
     *
     * @param s        The input string to be segmented.
     * @param wordDict A list of dictionary words.
     * @return A list of all possible sentences.
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        // Use a HashSet for O(1) average time complexity for word lookups.
        Set<String> dict = new HashSet<>(wordDict);
        // Memoization cache: key is the starting index of the substring,
        // value is the list of sentences that can be formed from that substring.
        Map<Integer, List<String>> memo = new HashMap<>();

        return dfs(s, dict, 0, memo);
    }

    /**
     * A recursive helper function with memoization (Dynamic Programming).
     * It finds all possible sentences for the substring s.substring(start).
     *
     * @param s     The original input string.
     * @param dict  The dictionary of words.
     * @param start The starting index of the substring to process.
     * @param memo  The memoization cache.
     * @return A list of valid sentences for s.substring(start).
     */
    private List<String> dfs(String s, Set<String> dict, int start, Map<Integer, List<String>> memo) {
        // If we've already computed the result for this starting index, return it.
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        // This list will store all valid sentences for the current subproblem
        // s.substring(start).
        List<String> results = new LinkedList<>();

        // Base case: If we've reached the end of the string, it means the previous
        // steps formed a valid sentence. We return a list with an empty string,
        // which acts as a signal to the calling function to form a complete sentence.
        if (start == s.length()) {
            results.add("");
            return results;
        }

        // Iterate through all possible end points for the current word.
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);

            // If the substring is a valid word in our dictionary...
            if (dict.contains(word)) {
                // ...recursively call the function for the rest of the string.
                List<String> sentencesFromSuffix = dfs(s, dict, end, memo);

                // For each valid sentence formed from the suffix...
                for (String suffixSentence : sentencesFromSuffix) {
                    // ...construct the new sentence.
                    // If the suffixSentence is empty, it means we've reached the end of the string.
                    // In this case, just add the current word. Otherwise, add a space.
                    String newSentence = word + (suffixSentence.isEmpty() ? "" : " ") + suffixSentence;
                    results.add(newSentence);
                }
            }
        }

        // Cache the computed results for the current starting index before returning.
        memo.put(start, results);
        return results;
    }

    public static void main(String[] args) {
        WordBreakII solver = new WordBreakII();

        // Example 1
        String s1 = "catsanddog";
        List<String> wordDict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println("Input: s = \"" + s1 + "\", wordDict = " + wordDict1);
        System.out.println("Output: " + solver.wordBreak(s1, wordDict1));
        // Expected: [cats and dog, cat sand dog] or [cat sand dog, cats and dog]

        // Example 2
        String s2 = "pineapplepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println("\nInput: s = \"" + s2 + "\", wordDict = " + wordDict2);
        System.out.println("Output: " + solver.wordBreak(s2, wordDict2));
        // Expected: [pine apple pen apple, pineapple pen apple, pine applepen apple]
    }
}
