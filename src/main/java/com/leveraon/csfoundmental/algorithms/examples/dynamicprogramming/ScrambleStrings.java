package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

public class ScrambleStrings {
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

		// Initialize memoization table: len goes from 1 to n, so size n+1 for the last
		// dimension
		memo = new Boolean[n][n][n + 1];

		// Start the recursive solving process from the full strings
		return solve(0, 0, n);
	}

	/**
	 * Determines if the substring of s1 starting at s1_idx with length 'len' is a
	 * scramble of the substring of s2 starting at s2_idx with length 'len'.
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
			// Check if (s1_left is scramble of s2_left) AND (s1_right is scramble of
			// s2_right)
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
			// The segment corresponding to s1_left_part (length k) will be at s2_idx + (len
			// - k).
			// The segment corresponding to s1_right_part (length len - k) will be at
			// s2_idx.
			//
			// Check if (s1_left is scramble of s2's segment for s1_left_after_swap)
			// AND (s1_right is scramble of s2's segment for s1_right_after_swap)
			if (solve(s1_idx, s2_idx + (len - k), k) && solve(s1_idx + k, s2_idx, len - k)) {
				return memo[s1_idx][s2_idx][len] = true;
			}
		}

		// If no split and no swap combination resulted in true, then it's not a
		// scramble string.
		return memo[s1_idx][s2_idx][len] = false;
	}

	/**
	 * Helper function to check if two substrings (defined by their start indices
	 * and length) have the same character counts. This is a necessary condition for
	 * them to be scramble strings. Assumes lowercase English letters ('a' through
	 * 'z').
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
