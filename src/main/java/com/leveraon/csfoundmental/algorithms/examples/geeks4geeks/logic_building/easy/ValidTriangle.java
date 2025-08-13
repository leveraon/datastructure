package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * Given three sides, check whether triangle is valid or not. <br>
 * Input : a = 7, b = 10, c = 5 <br>
 * Output : Valid<br>
 * We can draw a triangle with the given three edge lengths.<br>
 * 
 * Input : a = 1, b = 10, c = 12 <br>
 * Output : Invalid <br>
 * We can not draw a triangle with the given three edge lengths.<br>
 */
public class ValidTriangle {

	static boolean isValidTriangle(int a, int b, int c) {
		int sum = a + b + c;
		System.out.println("Given:" + a + " " + b + " " + c);
		if (sum - a <= a || sum - b <= b || sum - c <= c)
			return false;

		return true;
	}

	public static void main(String[] args) {
		int a = 2, b = 3, c = 5, d = 4;
		System.out.println("Is valid triangle:" + isValidTriangle(a, b, c));
		System.out.println("Is valid triangle:" + isValidTriangle(a, b, d));
	}
}
