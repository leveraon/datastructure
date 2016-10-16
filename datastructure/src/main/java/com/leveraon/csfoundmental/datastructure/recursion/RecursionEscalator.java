package com.leveraon.csfoundmental.datastructure.recursion;

public class RecursionEscalator {

	public static void drawEscalator(int n) {

		if (n >= 0) {
			drawEscalator(n - 1);
			for (int i = 0; i < n; i++) {
				System.out.print('*');
			}
			System.out.println('\n');
		}
	}

	public static void drawStairCase(int totalStairNum) {
		for (int j = 0; j < totalStairNum; j++) {
			for (int i = 1; i <= totalStairNum; i++) {
				System.out.print(i < totalStairNum - j ? " " : "*");
			}
			System.out.println("");
		}
	}
}
