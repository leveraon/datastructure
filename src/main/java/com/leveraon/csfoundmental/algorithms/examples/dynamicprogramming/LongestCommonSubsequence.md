The Longest Common Subsequence (LCS) problem is a classic computer science problem, typically solved using dynamic programming.

## Understanding the Longest Common Subsequence (LCS) Problem

**Definition:** Given two sequences (strings in this case), find the length of the longest subsequence present in both of them. A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

**Example:**
*   String 1: `AGGTAB`
*   String 2: `GXTXAYB`
*   LCS: `GTAB` (length 4)

## Dynamic Programming Approach

Let `text1` be of length `m` and `text2` be of length `n`. We use a 2D array, `dp[m+1][n+1]`, where `dp[i][j]` stores the length of the LCS of `text1[0...i-1]` and `text2[0...j-1]`.

### Steps:

1.  **Initialization:**
    *   `dp[0][j] = 0` for all `j` from `0` to `n`. (LCS of an empty string with any other string is 0).
    *   `dp[i][0] = 0` for all `i` from `0` to `m`. (LCS of any string with an empty string is 0).

2.  **Filling the DP Table (Recurrence Relation):**
    For `i` from `1` to `m` and `j` from `1` to `n`:
    *   **If `text1.charAt(i-1) == text2.charAt(j-1)` (characters match):**
        This means we found a common character. So, the LCS length is 1 plus the LCS length of the preceding substrings (`text1[0...i-2]` and `text2[0...j-2]`).
        `dp[i][j] = 1 + dp[i-1][j-1]`
    *   **If `text1.charAt(i-1) != text2.charAt(j-1)` (characters don't match):**
        In this case, the current characters don't contribute to the common subsequence. We must take the maximum of two possibilities:
        1.  The LCS of `text1[0...i-2]` and `text2[0...j-1]` (effectively ignoring `text1[i-1]`). This is `dp[i-1][j]`.
        2.  The LCS of `text1[0...i-1]` and `text2[0...j-2]` (effectively ignoring `text2[j-1]`). This is `dp[i][j-1]`.
        `dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])`

3.  **Result:**
    The final answer (length of the LCS of `text1` and `text2`) will be stored in `dp[m][n]`.

### Reconstructing the LCS String

After filling the `dp` table, you can reconstruct one of the actual LCS strings by backtracking from `dp[m][n]`.

1.  Start at `i = m` and `j = n`.
2.  While `i > 0` and `j > 0`:
    *   **If `text1.charAt(i-1) == text2.charAt(j-1)`:**
        This character is part of the LCS. Add `text1.charAt(i-1)` to your result (e.g., a `StringBuilder`) and move diagonally up-left (`i--`, `j--`).
    *   **Else (characters don't match):**
        Compare `dp[i-1][j]` and `dp[i][j-1]`.
        *   If `dp[i-1][j] > dp[i][j-1]`: Move up (`i--`). This indicates the LCS came from ignoring `text1[i-1]`.
        *   Else: Move left (`j--`). This indicates the LCS came from ignoring `text2[j-1]`.
3.  Since you build the string in reverse, reverse it at the end.

### Time and Space Complexity

*   **Time Complexity:** O(m*n) for filling the DP table and O(m+n) for reconstructing the string. Overall, O(m*n).
*   **Space Complexity:** O(m*n) for the DP table.

---

## Java Implementation

```java
import java.util.Arrays;

public class LongestCommonSubsequence {

    /**
     * Computes the DP table for the Longest Common Subsequence (LCS) of two strings.
     * The DP table stores the lengths of LCS for all prefixes.
     *
     * @param text1 The first string.
     * @param text2 The second string.
     * @return A 2D array representing the DP table. dp[i][j] stores the LCS length
     *         of text1[0...i-1] and text2[0...j-1].
     */
    public int[][] calculateLCS_DPTable(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // dp[i][j] stores the length of LCS of text1[0...i-1] and text2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp table
        // i iterates through text1 (from 1 to m, corresponding to charAt(i-1))
        // j iterates through text2 (from 1 to n, corresponding to charAt(j-1))
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // Base cases: LCS with an empty string (i=0 or j=0) is 0
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                // If characters match, extend the LCS from the diagonal element
                else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                // If characters don't match, take the maximum from
                // 1. Ignoring the current character of text1 (dp[i-1][j])
                // 2. Ignoring the current character of text2 (dp[i][j-1])
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    /**
     * Computes only the length of the Longest Common Subsequence (LCS) of two strings.
     *
     * @param text1 The first string.
     * @param text2 The second string.
     * @return The length of the LCS.
     */
    public int lcsLength(String text1, String text2) {
        int[][] dp = calculateLCS_DPTable(text1, text2);
        return dp[text1.length()][text2.length()];
    }

    /**
     * Reconstructs one of the Longest Common Subsequences (LCS) string itself.
     * This method requires the DP table that was previously computed using calculateLCS_DPTable.
     *
     * @param text1 The first string.
     * @param text2 The second string.
     * @param dp    The 2D array containing LCS lengths, computed by calculateLCS_DPTable method.
     * @return The LCS string.
     */
    public String reconstructLCS(String text1, String text2, int[][] dp) {
        int i = text1.length();
        int j = text2.length();
        StringBuilder lcs = new StringBuilder();

        // Backtrack from the bottom-right corner of the DP table
        while (i > 0 && j > 0) {
            // If characters at current position match, this character is part of LCS
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1)); // Add to LCS
                i--; // Move diagonally up-left
                j--;
            }
            // If characters don't match, check which path led to the current dp value
            // and move in that direction (i.e., 'ignore' the character that didn't help)
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--; // Move up, indicating text1[i-1] was not part of LCS
            } else {
                j--; // Move left, indicating text2[j-1] was not part of LCS
            }
        }

        // The string was built in reverse order, so reverse it back
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        LongestCommonSubsequence solver = new LongestCommonSubsequence();

        // --- Example 1 ---
        String s1 = "ABCBDAB";
        String s2 = "BDCABA";

        System.out.println("--- Example 1 ---");
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);

        // Step 1: Calculate the DP table
        int[][] dpTable1 = solver.calculateLCS_DPTable(s1, s2);

        // Step 2: Get LCS length from the bottom-right cell of the DP table
        int lcsLen1 = dpTable1[s1.length()][s2.length()];
        System.out.println("Length of LCS: " + lcsLen1); // Expected: 4

        // Step 3: Reconstruct the LCS string using the DP table
        String lcsStr1 = solver.reconstructLCS(s1, s2, dpTable1);
        System.out.println("LCS String: " + lcsStr1); // Possible outputs: "BCBA" or "BDAB"

        System.out.println("\n--- Example 2 ---");
        String s3 = "AGGTAB";
        String s4 = "GXTXAYB";

        System.out.println("String 1: " + s3);
        System.out.println("String 2: " + s4);

        int[][] dpTable2 = solver.calculateLCS_DPTable(s3, s4);
        int lcsLen2 = dpTable2[s3.length()][s4.length()];
        System.out.println("Length of LCS: " + lcsLen2); // Expected: 4

        String lcsStr2 = solver.reconstructLCS(s3, s4, dpTable2);
        System.out.println("LCS String: " + lcsStr2); // Expected: "GTAB"

        System.out.println("\n--- Example 3 (No common subsequence) ---");
        String s5 = "ABC";
        String s6 = "DEF";
        System.out.println("String 1: " + s5);
        System.out.println("String 2: " + s6);
        int[][] dpTable3 = solver.calculateLCS_DPTable(s5, s6);
        int lcsLen3 = dpTable3[s5.length()][s6.length()];
        System.out.println("Length of LCS: " + lcsLen3); // Expected: 0
        String lcsStr3 = solver.reconstructLCS(s5, s6, dpTable3);
        System.out.println("LCS String: '" + lcsStr3 + "'"); // Expected: '' (empty string)
    }
}
```