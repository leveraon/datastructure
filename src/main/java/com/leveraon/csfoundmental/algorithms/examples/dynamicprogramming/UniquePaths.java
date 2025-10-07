package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a maze (grid), find the number of unique paths from a start cell (e.g.,
 * top-left) to an end cell (e.g., bottom-right), only allowing movement right
 * and down, and avoiding wall
 */
public class UniquePaths {
	/**
	 * Finds number of unique paths in a maze from top-left to bottom-right
	 * 
	 * @param maze 2D array where 0 represents path and 1 represents wall
	 * @return number of unique paths, or 0 if no path exists
	 */
	public int findUniquePaths(int[][] maze) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return 0;
		}

		int m = maze.length;
		int n = maze[0].length;

		// If start or end is blocked, no path exists
		if (maze[0][0] == 1 || maze[m - 1][n - 1] == 1) {
			return 0;
		}

		// dp[i][j] represents number of unique paths to reach cell (i,j)
		int[][] dp = new int[m][n];

		// Initialize first row
		dp[0][0] = 1;
		for (int j = 1; j < n; j++) {
			dp[0][j] = (maze[0][j] == 0) ? dp[0][j - 1] : 0;
		}

		// Initialize first column
		for (int i = 1; i < m; i++) {
			dp[i][0] = (maze[i][0] == 0) ? dp[i - 1][0] : 0;
		}

		System.out.println("=======================Before==========================");
		Arrays.stream(dp).forEach(ar -> System.out.println(Arrays.toString(ar)));
		System.out.println("======================Progress=========================");

		// Fill dp table
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (maze[i][j] == 0) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
				// If cell is wall (maze[i][j] == 1), dp[i][j] remains 0
			}
			System.out.println("DP[" + i + "]=>" + Arrays.toString(dp[i]));
		}
		System.out.println("=======================After==========================");
		Arrays.stream(dp).forEach(ar -> System.out.println(Arrays.toString(ar)));
		System.out.println("======================================================");
		return dp[m - 1][n - 1];
	}

	public static void main(String[] args) {
		UniquePaths solution = new UniquePaths();

		int[][] maze = { { 0, 0, 0, 0, 1, 0 }, { 1, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0 },
				{ 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0 } };
		System.out.println("=======================MAZE==========================");
		Arrays.stream(maze).forEach(ar -> System.out.println(Arrays.toString(ar)));

		System.out.println("Number of unique paths: " + solution.findUniquePaths(maze));
	}
}
