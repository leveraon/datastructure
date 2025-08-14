package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * Given two positive integers a and b, the task is to find the GCD of the two
 * numbers. <br>
 * 
 * Note: The GCD (Greatest Common Divisor) or HCF (Highest Common Factor) of two
 * numbers is the largest number that divides both of them.<br>
 * <br>
 * Input: a = 20, b = 28<br>
 * Output: 4<br>
 * Explanation: The factors of 20 are 1, 2, 4, 5, 10 and 20. The factors of 28
 * are 1, 2, 4, 7, 14 and 28. Among these factors, 1, 2 and 4 are the common
 * factors of both 20 and 28. The greatest among the common factors is 4. <br>
 * <br>
 * Input: a = 60, b = 36<br>
 * Output: 12<br>
 * Explanation: GCD of 60 and 36 is 12.<br>
 */
public class GreatestCommonFactor {

	static int findGCD(int first, int second) {

		if (first == second) {
			return first;
		}

		if (second % first == 0)
			return first;

		if (first % second == 0)
			return second;

		int factor = first;

		while (factor > 0) {
			if (first % factor == 0 && second % factor == 0) {
				return factor;
			}

			factor--;
		}

		return factor;
	}

	public static void main(String[] args) {
		int first = 18, second = 81;

		System.out.println("GCD of " + first + " and " + second + " is " + findGCD(first, second));
	}
}
