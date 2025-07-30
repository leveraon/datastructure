package com.leveraon.csfoundmental.algorithms.examples.recursion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EscalatorRecursive {

	public static void drawEscalator(int n) {

		if (n >= 0) {
			drawEscalator(n - 1);
			for (int i = 0; i < n; i++) {
				System.out.print("*");
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

	public static void main(String[] args) {
		int escalator = 10;
		drawEscalator(escalator);

		int stircase = 10;
		drawStairCase(stircase);
	}
}
