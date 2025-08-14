package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

import java.util.Arrays;

/**
 * Given two integer arrays a[] and b[] containing two integers each
 * representing the numerator and denominator of a fraction respectively. The
 * task is to find the sum of the two fractions and return the numerator and
 * denominator of the result.<br>
 * <br>
 * Input: a = [1, 2] , b = [3, 2] <br>
 * Output: [2, 1] <br>
 * Explanation: 1/2 + 3/2 = 2/1<br>
 * <br>
 * Input: a = [1, 3] , b = [3, 9] <br>
 * Output: [2, 3] <br>
 * Explanation: 1/3 + 3/9 = 2/3 <br>
 * <br>
 * Input: a = [1, 5] , b = [3, 15] <br>
 * Output: [2, 5] <br>
 * Explanation: 1/5 + 3/15 = 2/5 <br>
 */
public class AddTowFactions {

	static int[] addFactions(int[] first, int[] second) {

		int maxDenominator = Math.max(first[1], second[1]);
		int minDenominator = Math.min(first[1], second[1]);

		// calculate least common multiple
		int multiplier = 1;
		int lcm = 1;
		while (multiplier <= first[1] * second[1]) {
			if (maxDenominator * multiplier % minDenominator == 0) {
				lcm = maxDenominator * multiplier;
				break;
			}
			multiplier++;
		}

		//
		int numeratorSum = (first[0] * (lcm / first[1]) + second[0] * (lcm / second[1]));
		int[] result = { numeratorSum, lcm };
		System.out.println("Raw result is " + Arrays.toString(result));
		int divider = Math.min(result[0], result[1]);
		while (divider > 0) {
			if (result[0] % divider == 0 && result[1] % divider == 0) {
				result[0] = result[0] / divider;
				result[1] = result[1] / divider;
			}
			divider--;
		}

		return result;
	}

	public static void main(String[] args) {
		int[] first = { 3, 5 }, second = { 32, 5 };
//		int[] first = { 3, 50 }, second = { 7, 210 };

		System.out.println("Result is " + Arrays.toString(addFactions(first, second)));
	}

}
