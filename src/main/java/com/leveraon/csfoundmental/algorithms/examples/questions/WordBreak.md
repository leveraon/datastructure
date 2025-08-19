### 2. **Word Break II**

*   **Problem:** Given a string `s` and a dictionary of strings `wordDict`, add spaces in `s` to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
*   **DSA Focus:** Strings, Dynamic Programming (Memoization/Backtracking), Hash Sets.
*   **Senior-Level Discussion Points:**
    *   Difference between "Word Break I" (can it be broken?) and "Word Break II" (find all ways).
    *   Backtracking approach: How to explore all possibilities.
    *   Optimization using Memoization (DP): Store results for subproblems to avoid redundant calculations. Explain *why* memoization is crucial here (to prune the search space when a sub-string cannot be broken).
    *   Time and Space Complexity of the recursive approach with/without memoization.
    *   Handling large dictionaries (use a `HashSet` for O(1) average lookup).

---

### Solution: Word Break II

**Problem:** Given a string `s` and a dictionary of strings `wordDict`, add spaces in `s` to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

**Approach:**
This problem is a classic example of **Backtracking with Memoization (Dynamic Programming)**.

1.  **Backtracking:** We recursively try to break the string `s` at every possible valid word prefix.
    *   Start from the beginning of `s`.
    *   For each possible prefix `s.substring(0, i)`:
        *   If it's in `wordDict`:
            *   Consider this prefix as a word.
            *   Recursively call the function for the remaining suffix `s.substring(i)`.
            *   Combine the current word with the results from the recursive call.
2.  **Memoization:** The key to efficiency is avoiding redundant computations for the same subproblems. We use a `HashMap` to store the results (list of sentences) for each substring (key: start index or substring itself).
    *   Before computing results for a substring, check if it's already in the memoization map.
    *   After computing, store the results in the map.
3.  **Dictionary Lookup:** Use a `HashSet` for `wordDict` for O(1) average time lookup.

**Time Complexity:** O(N^3 * W + N^2) roughly, where N is the length of `s`, and W is the average length of a word in `wordDict`.
*   The outer loop runs `N` times.
*   The inner loop (for substring `i`) also runs `N` times.
*   String operations (substring) take O(length).
*   List concatenation in backtracking takes time proportional to the number of results, which can be exponential in worst case without memoization, but memoization significantly prunes this.
*   With memoization, each state `(i)` is computed once. In the worst case, a state `i` might generate `O(N)` words (up to `N` choices for `j`), and each recursive call could potentially concatenate `O(N)` results, leading to `O(N^2)` string operations for results generation within each call. However, the number of actual recursive calls for a unique `substring` is `N`. So, `N` states, each involving up to `N` checks and concatenations. The concatenation of results dominates. `O(N^3)` is a commonly cited upper bound for the core logic, times the string operations, and `W` for hash set lookup.
**Space Complexity:** O(N * (N + L))
*   O(N) for recursion stack depth.
*   O(N^2) for memoization map (storing results for each substring). In the worst case, each entry could store a list of sentences, and the total length of strings stored could be large. The `L` factor comes from the average length of words/sentences stored.

**Java Code:**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

class Solution {

    // Memoization map: Stores List<String> for each starting index
    private Map<Integer, List<String>> memo;
    // For O(1) average time dictionary lookup
    private Set<String> wordSet;

    /**
     * Finds all possible sentences by breaking the string `s` into words from `wordDict`.
     *
     * @param s The input string.
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
     * @param s The original input string.
     * @param start The starting index of the current substring to break.
     * @return A list of all possible ways to break the substring s[start...] into valid words.
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

    // --- Example Usage ---
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test Case 1
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        List<String> result1 = sol.wordBreak(s1, wordDict1);
        // Expected: ["leet code"]
        System.out.println("s: \"" + s1 + "\", wordDict: " + wordDict1);
        System.out.println("Result: " + result1);

        // Test Case 2
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        List<String> result2 = sol.wordBreak(s2, wordDict2);
        // Expected: ["apple pen apple", "applepen apple"] (order might vary)
        System.out.println("\ns: \"" + s2 + "\", wordDict: " + wordDict2);
        System.out.println("Result: " + result2);

        // Test Case 3
        String s3 = "catsanddog";
        List<String> wordDict3 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> result3 = sol.wordBreak(s3, wordDict3);
        // Expected: ["cats and dog", "cat sand dog"] (order might vary)
        System.out.println("\ns: \"" + s3 + "\", wordDict: " + wordDict3);
        System.out.println("Result: " + result3);

        // Test Case 4: No solution
        String s4 = "pineapplepenapple";
        List<String> wordDict4 = Arrays.asList("apple", "pen", "pine", "pineapple");
        List<String> result4 = sol.wordBreak(s4, wordDict4);
        // Expected: ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
        System.out.println("\ns: \"" + s4 + "\", wordDict: " + wordDict4);
        System.out.println("Result: " + result4);

        // Test Case 5: Complex, large number of words
        String s5 = "aaaaaaa";
        List<String> wordDict5 = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa");
        List<String> result5 = sol.wordBreak(s5, wordDict5);
        System.out.println("\ns: \"" + s5 + "\", wordDict: " + wordDict5);
        System.out.println("Result (first few only due to length): " + result5.subList(0, Math.min(5, result5.size())));
        System.out.println("Total solutions for s5: " + result5.size()); // Should be 64
    }
}
```

**Senior-Level Considerations:**

*   **Word Break I vs. II:** Distinguish between the two problems. Word Break I (can it be broken?) is a simpler DP problem (boolean array), while Word Break II requires storing and building all possible sentences.
*   **Why Memoization?** Explain that without memoization, the same subproblems would be re-computed many times, leading to exponential time complexity. Memoization prunes the search tree significantly by storing results for already solved subproblems.
*   **Base Case Handling:** Pay attention to the `if (start == s.length())` base case returning `[""]`. This empty string acts as a placeholder that, when concatenated with a word, correctly forms the final valid sentence.
*   **String Operations:** `substring()` in Java creates new string objects. For very long strings and many partitions, this could incur overhead. However, for typical interview constraints, it's acceptable. Discuss if efficiency for string operations were critical, one might pass start/end indices or use a `StringBuilder`.
*   **Time/Space Nuances:** The exact complexity can be tricky to pin down and depends on the number of solutions. The provided `O(N^3 * W)` is a reasonable worst-case upper bound considering string manipulations. The `O(N * (N + L))` space complexity highlights that the map stores lists of strings, whose total length can add up.

---