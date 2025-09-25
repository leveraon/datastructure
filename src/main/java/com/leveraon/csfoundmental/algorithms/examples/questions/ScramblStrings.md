The Scramble String problem asks us to determine if a string `s2` can be formed by recursively splitting another string `s1` into two non-empty substrings and swapping them (or not). This problem has a recursive structure and can be efficiently solved using dynamic programming with memoization.

Let's break down the approach:

**1. Problem Definition and Recursive Structure**

Given two strings `s1` and `s2` of the same length `n`:
- **Base Case 1:** If `s1` and `s2` are identical, then `s2` is a scramble of `s1`. (This includes the single-character case).
- **Base Case 2:** If their lengths are 1, they are scrambled if their characters are the same.
- **Recursive Step:** For strings of length `len > 1`:
    - `s1` can be split into two non-empty substrings at any point `k` (where `1 <= k < len`). Let these be `s1_left` (length `k`) and `s1_right` (length `len - k`).
    - `s2` must also be split into two substrings of corresponding lengths. There are two possibilities for how `s2`'s parts relate to `s1`'s parts:
        - **Possibility A (No Swap):** `s2` is split into `s2_left` (length `k`) and `s2_right` (length `len - k`). In this case, `s2` is a scramble of `s1` if `s2_left` is a scramble of `s1_left` AND `s2_right` is a scramble of `s1_right`.
        - **Possibility B (Swap):** `s2` is split into `s2_right_swapped` (length `len - k`) and `s2_left_swapped` (length `k`). In this case, `s2` is a scramble of `s1` if `s2_right_swapped` is a scramble of `s1_right` AND `s2_left_swapped` is a scramble of `s1_left`.

If either Possibility A or Possibility B holds for any split point `k`, then `s2` is a scramble of `s1`.

**2. Memoization (Dynamic Programming)**

A purely recursive solution would recompute the same subproblems many times. We use memoization to store the results of subproblems.
A subproblem is defined by whether `s1[i...i+len-1]` is a scramble of `s2[j...j+len-1]`.
We can use a 3D array `memo[i][j][len]` to store the results:
- `i`: starting index of the substring in `s1`.
- `j`: starting index of the substring in `s2`.
- `len`: length of the substrings.
`memo[i][j][len]` will store `true`, `false`, or `null` (if not yet computed).

**3. Optimization: Character Count Check**

Before making recursive calls, it's a crucial optimization to check if the current substrings `s1[i...i+len-1]` and `s2[j...j+len-1]` have the exact same character counts. If they don't, they cannot possibly be scrambled versions of each other, and we can immediately return `false` (and memoize it). This prunes a large portion of the search space.

**4. Algorithm Steps**

1. **Initialization:**
   - Handle edge cases: if lengths are different or strings are null, return `false`.
   - If `s1` and `s2` are identical, return `true`.
   - Initialize the `memo` table with `null` values. Store `s1` and `s2` as global variables (or pass them around) to avoid repeated substring creations, which can be inefficient.

2. **`solve(s1_idx, s2_idx, len)` function:**
   - **Memoization Check:** If `memo[s1_idx][s2_idx][len]` is not `null`, return its stored value.
   - **Base Case (Length 1):** If `len == 1`, return `s1.charAt(s1_idx) == s2.charAt(s2_idx)`. Store this result in `memo`.
   - **Character Count Check:** Call `areCharCountsSame(s1_idx, s2_idx, len)`. If it returns `false`, store `false` in `memo` and return `false`.
   - **Iterate Split Points (`k`):** Loop `k` from `1` to `len - 1` (this ensures `s1_left` has length `k` and `s1_right` has length `len - k`, both non-empty).
     - **Possibility A (No Swap):**
       - Recursively check `solve(s1_idx, s2_idx, k)` (s1_left vs s2_left)
       - AND `solve(s1_idx + k, s2_idx + k, len - k)` (s1_right vs s2_right)
       - If both are `true`, store `true` in `memo` and return `true`.
     - **Possibility B (Swap):**
       - Recursively check `solve(s1_idx, s2_idx + (len - k), k)` (s1_left vs s2_left_swapped)
       - AND `solve(s1_idx + k, s2_idx, len - k)` (s1_right vs s2_right_swapped)
       - If both are `true`, store `true` in `memo` and return `true`.
   - **No Match:** If the loop finishes without finding any successful split, store `false` in `memo` and return `false`.

3. **`areCharCountsSame(s1_idx, s2_idx, len)` function:**
   - Use an array of size 26 (for lowercase English letters) to count character frequencies.
   - Iterate `i` from `0` to `len - 1`: increment count for `s1.charAt(s1_idx + i)` and decrement count for `s2.charAt(s2_idx + i)`.
   - After iterating, if all counts in the array are zero, return `true`; otherwise, return `false`.

**Complexity Analysis:**

- **Time Complexity:** `O(N^4)`
    - There are `N * N * N` possible states for `(s1_idx, s2_idx, len)`.
    - For each state, we iterate `N` times (for `k`).
    - Inside the loop, the `areCharCountsSame` check takes `O(N)` time. The recursive calls are effectively `O(1)` amortized because of memoization.
    - Thus, `N^3 * N = N^4`. For `N=30`, `30^4 = 810,000`, which is acceptable.
- **Space Complexity:** `O(N^3)`
    - For the `memo` table.

**Java Implementation:**

```java
import java.util.Arrays; // Not strictly necessary, but good for demonstrating character counts

class Solution {
    private String s1_global; // Stores s1 to avoid passing it repeatedly
    private String s2_global; // Stores s2 to avoid passing it repeatedly
    
    // memo[s1_start_index][s2_start_index][length]
    // Values: null (not computed), true (is scramble), false (not scramble)
    private Boolean[][][] memo; 

    public boolean isScramble(String s1, String s2) {
        // 1. Initial checks for problem constraints
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true; // If strings are identical, they are scrambled
        }
        // If length is 0, they are considered scrambled (handled by s1.equals(s2))
        if (s1.length() == 0) {
            return true; 
        }

        s1_global = s1;
        s2_global = s2;
        int n = s1.length();
        
        // Initialize memoization table: len goes from 1 to n, so size n+1 for the last dimension
        memo = new Boolean[n][n][n + 1]; 

        // Start the recursive solving process from the full strings
        return solve(0, 0, n);
    }

    /**
     * Determines if the substring of s1 starting at s1_idx with length 'len'
     * is a scramble of the substring of s2 starting at s2_idx with length 'len'.
     *
     * @param s1_idx The starting index of the substring in s1.
     * @param s2_idx The starting index of the substring in s2.
     * @param len    The length of the substrings being compared.
     * @return true if they are scramble strings, false otherwise.
     */
    private boolean solve(int s1_idx, int s2_idx, int len) {
        // --- Memoization Check ---
        if (memo[s1_idx][s2_idx][len] != null) {
            return memo[s1_idx][s2_idx][len];
        }

        // --- Base Case: Single character string ---
        if (len == 1) {
            return memo[s1_idx][s2_idx][len] = (s1_global.charAt(s1_idx) == s2_global.charAt(s2_idx));
        }

        // --- Optimization: Character Count Check ---
        // If the character counts don't match between the two substrings,
        // they cannot possibly be scramble strings.
        if (!areCharCountsSame(s1_idx, s2_idx, len)) {
            return memo[s1_idx][s2_idx][len] = false;
        }

        // --- Recursive Step: Try all possible split points 'k' ---
        // 'k' represents the length of the left partition of s1
        // It ranges from 1 to len-1 to ensure both partitions are non-empty.
        for (int k = 1; k < len; k++) {
            // Case A: No swap of partitions
            // s1 -> (s1[s1_idx...s1_idx+k-1], s1[s1_idx+k...s1_idx+len-1])
            // s2 -> (s2[s2_idx...s2_idx+k-1], s2[s2_idx+k...s2_idx+len-1])
            // Check if (s1_left is scramble of s2_left) AND (s1_right is scramble of s2_right)
            if (solve(s1_idx, s2_idx, k) && solve(s1_idx + k, s2_idx + k, len - k)) {
                return memo[s1_idx][s2_idx][len] = true;
            }

            // Case B: Swap of partitions
            // s1 -> (s1[s1_idx...s1_idx+k-1], s1[s1_idx+k...s1_idx+len-1])
            // s2 -> (s2_right_part_of_s1_scrambled, s2_left_part_of_s1_scrambled)
            //
            // s1_left_part has length k, starts at s1_idx.
            // s1_right_part has length (len - k), starts at s1_idx + k.
            //
            // After swap, in s2:
            // The segment corresponding to s1_left_part (length k) will be at s2_idx + (len - k).
            // The segment corresponding to s1_right_part (length len - k) will be at s2_idx.
            //
            // Check if (s1_left is scramble of s2's segment for s1_left_after_swap) 
            // AND (s1_right is scramble of s2's segment for s1_right_after_swap)
            if (solve(s1_idx, s2_idx + (len - k), k) && solve(s1_idx + k, s2_idx, len - k)) {
                return memo[s1_idx][s2_idx][len] = true;
            }
        }

        // If no split and no swap combination resulted in true, then it's not a scramble string.
        return memo[s1_idx][s2_idx][len] = false;
    }

    /**
     * Helper function to check if two substrings (defined by their start indices and length)
     * have the same character counts. This is a necessary condition for them to be scramble strings.
     * Assumes lowercase English letters ('a' through 'z').
     */
    private boolean areCharCountsSame(int s1_idx, int s2_idx, int len) {
        int[] counts = new int[26]; 

        for (int i = 0; i < len; i++) {
            counts[s1_global.charAt(s1_idx + i) - 'a']++; // Increment for char in s1
            counts[s2_global.charAt(s2_idx + i) - 'a']--; // Decrement for char in s2
        }

        // If all counts are zero, it means character frequencies match
        for (int count : counts) {
            if (count != 0) {
                return false; // Mismatch found
            }
        }
        return true; // All character counts match
    }
}

```