package com.leveraon.csfoundmental.algorithms.examples.text;

import java.util.Arrays;

public class LongestCommonSubString {
	/**
	 * Returns table such that L[j][k] is length of LCS for X[0..j−1] and Y[0..k−1].
	 */
	public static int[][] longestCommonSubstring(char[] X, char[] Y) {
		int n = X.length;
		int m = Y.length;
		int[][] L = new int[n + 1][m + 1];
		for (int j = 0; j < n; j++)
			for (int k = 0; k < m; k++)
				if (X[j] == Y[k]) // align this match
					L[j + 1][k + 1] = L[j][k] + 1;
				else // choose to ignore one character
					L[j + 1][k + 1] = Math.max(L[j][k + 1], L[j + 1][k]);
		return L;
	}

	public static void main(String[] args) {
		String test1 = "helloworldexperience", test2 = "workingexperience";

		int[][] lcs = longestCommonSubstring(test1.toCharArray(), test2.toCharArray());
		for (int[] array : lcs) {
			System.out.println( Arrays.toString(array));
		}
	}

}
