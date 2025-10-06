package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

public class LongestIncreasingPath {
	// Member variables to be used by the DFS helper method
	private int[][] matrix;
	private int m; // Number of rows
	private int n; // Number of columns
	private int[][] dp; // Memoization table: dp[r][c] stores the length of the longest increasing path
						// starting from (r, c)

	// Directions for moving (up, down, left, right)
	private final int[] dr = { -1, 1, 0, 0 }; // delta row (up, down, stay, stay)
	private final int[] dc = { 0, 0, -1, 1 }; // delta column (stay, stay, left, right)

	/**
	 * Finds the length of the longest increasing path in the given matrix.
	 *
	 * @param matrix The m x n integers matrix.
	 * @return The length of the longest increasing path.
	 */
	public int longestIncreasingPath(int[][] matrix) {
		// Handle edge cases for empty or invalid matrix
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		// Initialize member variables
		this.matrix = matrix;
		this.m = matrix.length;
		this.n = matrix[0].length;
		// The dp table is initialized to 0s by default in Java for int arrays,
		// which serves as a flag for uncomputed states.
		this.dp = new int[m][n];

		int maxOverallPath = 0;

		// Iterate through each cell in the matrix
		// We start a DFS from each cell to find the longest increasing path beginning
		// at that cell.
		// The overall longest path will be the maximum of all these individual paths.
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				maxOverallPath = Math.max(maxOverallPath, dfs(r, c));
			}
		}

		return maxOverallPath;
	}

	/**
	 * Depth-First Search (DFS) with memoization to find the longest increasing path
	 * starting from a given cell (r, c).
	 *
	 * @param r The current row.
	 * @param c The current column.
	 * @return The length of the longest increasing path starting from (r, c).
	 */
	private int dfs(int r, int c) {
		// If dp[r][c] is not 0, it means we have already computed the longest path
		// starting from (r, c). Return the memoized value to avoid redundant
		// computations.
		if (dp[r][c] != 0) {
			return dp[r][c];
		}

		// Initialize the path length for the current cell to 1.
		// The cell itself counts as a path of length 1.
		int currentMaxPath = 1;

		// Explore all four possible directions (up, down, left, right)
		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];

			// Check if the new coordinates are within the matrix boundaries
			if (newR >= 0 && newR < m && newC >= 0 && newC < n) {
				// Check if the neighbor's value is strictly greater than the current cell's
				// value.
				// This is the essential condition for an increasing path.
				if (matrix[newR][newC] > matrix[r][c]) {
					// Recursively call DFS for the valid neighbor.
					// The path length through this neighbor would be 1 (for the current cell)
					// plus the longest path starting from the neighbor.
					currentMaxPath = Math.max(currentMaxPath, 1 + dfs(newR, newC));
				}
			}
		}

		// Store the computed longest path for (r, c) in the dp table before returning.
		dp[r][c] = currentMaxPath;
		return currentMaxPath;
	}
}
