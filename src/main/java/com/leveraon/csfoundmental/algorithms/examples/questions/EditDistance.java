package com.leveraon.csfoundmental.algorithms.examples.questions;

public class EditDistance {

	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		// dp[i][j] will store the minimum operations required to transform
		// word1[0...i-1] into word2[0...j-1]
		int[][] dp = new int[m + 1][n + 1];

		// Base cases:
		// If word1 is empty, we need to insert all characters of word2.
		// dp[0][j] = j operations (j insertions).
		for (int j = 0; j <= n; j++) {
			dp[0][j] = j;
		}

		// If word2 is empty, we need to delete all characters of word1.
		// dp[i][0] = i operations (i deletions).
		for (int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}

		// Fill the DP table using the recurrence relation
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// If the current characters match, no operation is needed for them.
				// The cost is carried over from the previous subproblem (i-1, j-1).
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// If characters don't match, we consider three operations:
					// 1. Insert: Cost is 1 + dp[i][j-1] (transform word1[0...i-1] to
					// word2[0...j-2], then insert word2[j-1])
					// 2. Delete: Cost is 1 + dp[i-1][j] (transform word1[0...i-2] to
					// word2[0...j-1], then delete word1[i-1])
					// 3. Replace: Cost is 1 + dp[i-1][j-1] (transform word1[0...i-2] to
					// word2[0...j-2], then replace word1[i-1] with word2[j-1])
					dp[i][j] = 1 + Math.min(dp[i][j - 1], // Cost of insertion
							Math.min(dp[i - 1][j], // Cost of deletion
									dp[i - 1][j - 1])); // Cost of replacement
				}
			}
		}

		// The final result is stored at dp[m][n], representing transformation
		// of entire word1 to entire word2.
		return dp[m][n];
	}
}
