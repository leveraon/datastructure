package com.leveraon.csfoundmental.algorithms.examples.questions;

public class BurstBalloons {
	public int maxCoins(int[] nums) {
		int n = nums.length;

		// Create a new array with padded 1s at both ends.
		// This simplifies handling boundary conditions: if we burst a balloon at the
		// original
		// array's edge, its virtual neighbor (value 1) is used for multiplication.
		// paddedNums will have length n + 2.
		int[] paddedNums = new int[n + 2];
		paddedNums[0] = 1; // Left virtual balloon
		paddedNums[n + 1] = 1; // Right virtual balloon
		for (int i = 0; i < n; i++) {
			paddedNums[i + 1] = nums[i]; // Copy original balloons into the padded array
		}

		// dp[i][j] will store the maximum coins obtained by bursting all balloons
		// strictly between index i and index j (exclusive of i and j) in the paddedNums
		// array.
		// The indices i and j refer to the paddedNums array.
		// Java initializes int arrays to 0 by default, which is suitable here.
		int[][] dp = new int[n + 2][n + 2];

		// Iterate over the length of the interval.
		// 'len' represents the number of elements *between* i and j.
		// A length of 2 means (i, i+2), implying one balloon (paddedNums[i+1]).
		// A length of n+1 means (0, n+1), implying all 'n' original balloons.
		for (int len = 2; len <= n + 1; len++) {
			// Iterate over the starting index 'i' of the interval (i, j).
			// 'i' can go from 0 up to (total_padded_length - 1) - len.
			// For example, if paddedNums.length is N, max j is N-1.
			// j = i + len. So, i + len <= N-1 => i <= N - 1 - len.
			// Here, N = n + 2, so i <= (n + 2) - 1 - len = n + 1 - len.
			for (int i = 0; i <= n + 1 - len; i++) {
				int j = i + len; // Calculate the end index 'j' for the current interval (i, j)

				// Iterate over all possible balloons 'k' that could be the *last* balloon to
				// burst
				// within the open interval (i, j).
				// 'k' must be strictly between 'i' and 'j'.
				for (int k = i + 1; k < j; k++) {
					// If paddedNums[k] is the last balloon to be burst in (i, j):
					// 1. We earn coins from bursting paddedNums[k]: paddedNums[i] * paddedNums[k] *
					// paddedNums[j].
					// At this moment, paddedNums[i] and paddedNums[j] are the immediate neighbors
					// of paddedNums[k].
					// 2. We add the maximum coins from bursting balloons in the sub-interval (i,
					// k): dp[i][k].
					// 3. We add the maximum coins from bursting balloons in the sub-interval (k,
					// j): dp[k][j].
					// These two subproblems (i, k) and (k, j) are independent because k is burst
					// last.
					// All balloons in (i, k) are burst (leaving i and k adjacent).
					// All balloons in (k, j) are burst (leaving k and j adjacent).
					// Then, k is burst using i and j as its final neighbors.
					dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + paddedNums[i] * paddedNums[k] * paddedNums[j]);
				}
			}
		}

		// The final result is the maximum coins for bursting all balloons between index
		// 0 and n+1
		// in the paddedNums array, which corresponds to bursting all the original 'n'
		// balloons.
		return dp[0][n + 1];
	}
}
