### The Interview Question: Word Break II

**Problem Statement:**

Given a non-empty string `s` and a dictionary `wordDict` containing a list of non-empty words, add spaces in `s` to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

You may assume the dictionary does not contain duplicate words.

**Example 1:**

*   **Input:** `s = "catsanddog"`, `wordDict = ["cat", "cats", "and", "sand", "dog"]`
*   **Output:** `["cats and dog", "cat sand dog"]`

**Example 2:**

*   **Input:** `s = "pineapplepenapple"`, `wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]`
*   **Output:** `["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]`

**Example 3:**

*   **Input:** `s = "catsandog"`, `wordDict = ["cats", "dog", "sand", "and", "cat"]`
*   **Output:** `[]` (No solution possible)

---

### Interviewer's Guide & What to Look For

This is a great senior-level question because:
1.  A naive brute-force solution is purely recursive and will time out, demonstrating the need for optimization.
2.  The optimal solution combines **Dynamic Programming (specifically, memoization)** with **Backtracking (or Depth-First Search)**. A candidate needs to see how these two concepts work together.
3.  The state stored in the DP table is more complex than a simple boolean or integer; it's a list of results for a subproblem.
4.  It allows for rich discussions on time/space complexity and potential trade-offs.

#### Step 1: Clarifying Questions & Initial Thoughts
A good candidate should start by asking clarifying questions:
*   Are the words in the dictionary and the input string all lowercase? (Assume yes)
*   What should be returned if the input string is empty? (The problem states non-empty, but it's a good check).
*   What are the constraints on the length of `s` and the size of `wordDict`? (This informs the feasibility of certain complexities).

The candidate should then identify that this is a "partitioning" problem. The core idea is to try every possible prefix of the string. If a prefix is a valid word, we then recursively solve the problem for the rest of the string.

#### Step 2: The Brute-Force Backtracking Approach
The candidate might first describe a pure backtracking/DFS solution.

*   **Function Signature:** `dfs(string s, Set<String> dict)`
*   **Logic:**
    *   Iterate from `i = 1` to `s.length()`.
    *   Consider `prefix = s.substring(0, i)`.
    *   If `prefix` is in the dictionary:
        *   Get the `suffix = s.substring(i)`.
        *   Recursively call `dfs(suffix, dict)`.
        *   For each result returned from the recursive call, prepend the `prefix` and a space.
    *   **Base Case:** If the input string `s` is itself in the dictionary, it's one valid solution.

The candidate should recognize this will be very slow because it will re-compute results for the same substrings over and over. For `s = "aaaa...b"` and `dict = ["a", "aa", ...]`, the subproblem for `s.substring(k)` will be solved many times. This is the perfect entry point to introduce DP.

#### Step 3: Introducing Dynamic Programming (Memoization)
The key insight is to cache the results of the subproblems. The subproblem is "find all sentences for the suffix of `s` starting at index `i`".

*   **DP State:** `memo[i]` will store the list of all valid sentences that can be formed from the substring `s.substring(i)`. A `Map<Integer, List<String>>` is a great data structure for this memoization cache.

The algorithm now becomes a memoized DFS:

1.  Create a function `dfs(startIndex)` that returns a list of valid sentences for `s.substring(startIndex)`.
2.  Inside `dfs(startIndex)`:
    *   Check if `memo` already contains the result for `startIndex`. If so, return the cached result immediately.
    *   Initialize an empty list, `resultsForStartIndex`.
    *   If `startIndex == s.length()`, we've successfully parsed the whole string. Add an empty string `""` to the results list. This is a crucial base case that allows the concatenation logic to work elegantly.
    *   Iterate `endIndex` from `startIndex + 1` to `s.length()`:
        *   Let `word = s.substring(startIndex, endIndex)`.
        *   If `word` is in the `wordDict`:
            *   Recursively call `dfs(endIndex)` to get the list of sentences for the rest of the string (`subSentences`).
            *   For each `subSentence` in `subSentences`:
                *   If `subSentence` is empty (meaning we've reached the end), form a new sentence `word`.
                *   Otherwise, form a new sentence `word + " " + subSentence`.
                *   Add this new sentence to `resultsForStartIndex`.
3.  Cache `resultsForStartIndex` in `memo[startIndex]` and return it.
4.  The initial call will be `dfs(0)`.

---

### Java Implementation Example

Here is a clean, well-commented Java implementation of the memoized DFS approach.

```java
import java.util.*;

public class WordBreakII {

    /**
     * Main function to be called by the user.
     * It sets up the dictionary for fast lookups and initializes the memoization cache.
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

        // This list will store all valid sentences for the current subproblem s.substring(start).
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
```

### Complexity Analysis

A senior candidate must be able to break this down correctly.

*   **Time Complexity:** `O(N^2 + 2^N * L)` in the worst-case, but it's nuanced.
    *   Let `N` be the length of the string `s` and `L` be the max length of a word.
    *   The `dfs` function is called for each index from `0` to `N-1`.
    *   Inside each `dfs(i)` call, the `for` loop runs `N-i` times. The `substring` operation takes `O(N)` time (in some Java versions, it's O(1), but it's safer to assume O(N)). So the DP part of filling the `memo` table is `O(N^2)`.
    *   However, the number of valid sentences can be exponential. Consider `s = "aaaaaaaa"` and `dict = ["a", "aa", "aaa", ...]`. The number of ways to partition the string can grow rapidly. In the worst case, we might have `2^(N-1)` solutions. For each solution, we do string concatenations.
    *   So, a more accurate (though complex) bound is related to the number of subproblems (`N`) times the work per subproblem, plus the time to generate the output strings. The dominant factor is often the size of the output, which can be exponential. `O(N^2 + |Output|)` is a reasonable way to express it.

*   **Space Complexity:** `O(N^2 + |Output|)`
    *   The recursion depth can go up to `N`, so the call stack takes `O(N)` space.
    *   The memoization map `memo` stores results for each index `i`. The list of strings at `memo[i]` can itself be large. In the worst case, the total number of characters stored across all lists in the memo table could be `O(N^2)`.
    *   The final `results` list can be very large, proportional to the size of the output.

### Follow-up Questions for a Senior Candidate

1.  **Optimization:** "Before running this expensive DFS, can you think of a quick way to check if a solution is even possible? What if the string `s` contains characters that don't appear in any dictionary word?"
    *   **Answer:** You can pre-process the string. First, run the simpler **Word Break I** algorithm, which is a boolean DP (`dp[i] = true` if `s.substring(0, i)` can be segmented). If `dp[s.length()]` is false, you can return an empty list immediately without starting the expensive sentence-building DFS. This is a great optimization.

2.  **System Design / Scalability:** "Imagine `wordDict` is the entire English dictionary (millions of words) and doesn't fit in memory. How would you adapt your solution?"
    *   **Answer:** This moves the problem from algorithmic to system design.
        *   The `wordDict` should be stored in a more efficient data structure like a **Trie**. A Trie allows you to check for valid word prefixes very efficiently. Instead of slicing a substring and checking for containment in a `HashSet`, you would traverse the Trie character by character.
        *   If it still doesn't fit, the Trie could be stored on disk or in a distributed cache (like Redis). The check `dict.contains(word)` would become an API call or a database lookup.

3.  **Alternative Approaches:** "Could you solve this iteratively instead of recursively?"
    *   **Answer:** Yes, it's possible using bottom-up DP. You would build the solution from the end of the string to the beginning. `dp[i]` would store the list of sentences for `s.substring(i)`. To compute `dp[i]`, you'd look at `dp[j]` for all `j > i` where `s.substring(i, j)` is a word. This is often less intuitive to write than the top-down memoized DFS but demonstrates a deeper understanding of DP.