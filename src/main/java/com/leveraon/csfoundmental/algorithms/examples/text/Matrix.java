package com.leveraon.csfoundmental.algorithms.examples.text;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

public class Matrix {
	public static int[][] matrixChain(int[] d) {
		int n = d.length - 1; // number of matrices
		int[][] N = new int[n][n]; // n-by-n matrix; initially zeros
		for (int b = 1; b < n; b++) {
			// number of products in subchain
			for (int i = 0; i < n - b; i++) { // start of subchain
				int j = i + b; // end of subchain
				N[i][j] = Integer.MAX_VALUE; // used as ’infinity’
				for (int k = i; k < j; k++)
					N[i][j] = Math.min(N[i][j], N[i][k] + N[k + 1][j] + d[i] * d[k + 1] * d[j + 1]);
				System.out.print("N[" + i + "]" + "[" + j + "]=" + N[i][j] + ",");
			}
			System.out.println();
		}
		return N;
	}

	public static void main(String[] args) {
		int[] random = RandomIntArrayGenerator.generateRandomIntArray(10, 1, 100);

		matrixChain(random);
	}

}
