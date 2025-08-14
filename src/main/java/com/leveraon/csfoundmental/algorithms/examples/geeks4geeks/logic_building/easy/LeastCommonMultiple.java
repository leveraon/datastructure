package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * Given two positive integers a and b. Find the Least Common Multiple (LCM) of
 * a and b.<br>
 * LCM of two numbers is the smallest number which can be divided by both
 * numbers.
 * 
 * Input : a = 10, b = 5<br>
 * Output : 10<br>
 * Explanation : 10 is the smallest number divisible by both 10 and 5<br>
 * 
 * Input : a = 5, b = 11<br>
 * Output : 55<br>
 * Explanation : 55 is the smallest number divisible by both 5 and 11<br>
 */
public class LeastCommonMultiple {

	static int findLCM(int first, int second) {

		int lcm = Math.max(first, second);

		int multiplier = 1;
		while (lcm <= first * second) {
			if (first * multiplier % second == 0)
				return first * multiplier;

			multiplier++;
		}

		return lcm;
	}

	public static void main(String[] args) {
//		int first = 8, second = 32;
//		int first = 5, second = 13;
//		int first = 16, second = 36;
		int first = 8, second = 36;

		System.out.println("LCM of " + first + " and " + second + " is: " + findLCM(first, second));
	}
}
