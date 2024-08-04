package com.leveraon.csfoundmental.algorithm.examples;

import lombok.extern.slf4j.Slf4j;

/**
 * A robot is located at the top-left corner of a m x n grid. It can only move
 * either down
 * or right at any point in time. The robot is trying to reach the bottom-right
 * corner of
 * the grid.
 * How many possible unique paths are there?
 */
@Slf4j
public class UniquePath {
    // DFS
    public static int uniquePathsDFS(int m, int n) {
        return dfs(0, 0, m, n);
    }

    public static int dfs(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (i < m - 1 && j < n - 1) {
            return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
        }
        if (i < m - 1) {
            return dfs(i + 1, j, m, n);
        }
        if (j < n - 1) {
            return dfs(i, j + 1, m, n);
        }
        return 0;
    }

    // Dynamic Programming
    public static int uniquePathsDP(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;
        int[][] dp = new int[m][n];
        // left column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // top row
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // fill up the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        log.info("Unique path with DFS:: {}", uniquePathsDFS(8, 10));
        log.info("Unique path with DP:: {}", uniquePathsDP(8, 10));
    }

}
