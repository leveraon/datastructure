package com.leveraon.csfoundmental.algorithms.recursion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecursionRuler {

	public static void drawRuler(int nInches, int majorLenghth) {

		drawLine(majorLenghth, 0);
		for (int j = 1; j <= nInches; j++) {
			drawInterval(majorLenghth - 1);
			drawLine(majorLenghth, j);
		}

	}

	private static void drawInterval(int centalLength) {
		if (centalLength >= 1) {
			drawInterval(centalLength - 1);
			drawLine(centalLength);
			drawInterval(centalLength - 1);
		}
	}

	private static void drawLine(int tickLength, int tickLabel) {
		for (int j = 0; j < tickLength; j++)
			System.out.print("-");
		if (tickLabel >= 0)
			System.out.print(" " + tickLabel);
		System.out.print("\n");
	}

	private static void drawLine(int tickLength) {
		drawLine(tickLength, -1);
	}

	public static void main(String[] args) {
		int inches = 5, majorLength = 4;

		log.info("Drawing Ruler...\n ");
		drawRuler(inches, majorLength);
	}
}
