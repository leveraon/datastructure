package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.Arrays;

public class LongestCommonSubsequence {

	/**
	 * Computes the DP table for the Longest Common Subsequence (LCS) of two
	 * strings. The DP table stores the lengths of LCS for all prefixes.
	 *
	 * @param text1 The first string.
	 * @param text2 The second string.
	 * @return A 2D array representing the DP table. dp[i][j] stores the LCS length
	 *         of text1[0...i-1] and text2[0...j-1].
	 */
	public int[][] calculateLCS(String text1, String text2) {
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
		for (int[] matrix : dp) {
			System.out.println(Arrays.toString(matrix));
		}
		return dp;
	}

	/**
	 * Computes only the length of the Longest Common Subsequence (LCS) of two
	 * strings.
	 *
	 * @param text1 The first string.
	 * @param text2 The second string.
	 * @return The length of the LCS.
	 */
	public int lcsLength(String text1, String text2) {
		int[][] dp = calculateLCS(text1, text2);
		return dp[text1.length()][text2.length()];
	}

	/**
	 * Reconstructs one of the Longest Common Subsequences (LCS) string itself. This
	 * method requires the DP table that was previously computed using
	 * calculateLCS_DPTable.
	 *
	 * @param text1 The first string.
	 * @param text2 The second string.
	 * @param dp    The 2D array containing LCS lengths, computed by
	 *              calculateLCS_DPTable method.
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
		int[][] dpTable1 = solver.calculateLCS(s1, s2);

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

		int[][] dpTable2 = solver.calculateLCS(s3, s4);
		int lcsLen2 = dpTable2[s3.length()][s4.length()];
		System.out.println("Length of LCS: " + lcsLen2); // Expected: 4

		String lcsStr2 = solver.reconstructLCS(s3, s4, dpTable2);
		System.out.println("LCS String: " + lcsStr2); // Expected: "GTAB"

		System.out.println("\n--- Example 3 (No common subsequence) ---");
		String s5 = "ABC";
		String s6 = "DEF";
		System.out.println("String 1: " + s5);
		System.out.println("String 2: " + s6);
		int[][] dpTable3 = solver.calculateLCS(s5, s6);
		int lcsLen3 = dpTable3[s5.length()][s6.length()];
		System.out.println("Length of LCS: " + lcsLen3); // Expected: 0
		String lcsStr3 = solver.reconstructLCS(s5, s6, dpTable3);
		System.out.println("LCS String: '" + lcsStr3 + "'"); // Expected: '' (empty string)
	}
}