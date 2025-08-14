package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * A number is a perfect number if it is equal to the sum of its proper
 * divisors, that is, the sum of its positive divisors excluding the number
 * itself. Find whether a given positive integer n is perfect or not. <br>
 * 
 * Input: n = 15<br>
 * Output: false<br>
 * Explanation: Divisors of 15 are 1, 3 and 5. Sum of divisors is 9 which is not
 * equal to 15.<br>
 * <br>
 * 
 * Input: n = 6<br>
 * Output: true<br>
 * Explanation: Divisors of 6 are 1, 2 and 3. Sum of divisors is 6.<br>
 * 
 */
public class PerfectNumber {

	static boolean isPerfectNumber(int number) {

		int sum = 0;
		int divider = 1;
		for (int i = divider; i < number; i++) {
			if (number % i == 0) {
				sum = sum + i;
				System.out.println("current sum is " + sum + ", i is " + i);
			}
		}

		return sum == number;
	}

	public static void main(String[] args) {
//		int number = 15;
		int number = 6;

		System.out.println(number + " is perfect number? " + isPerfectNumber(number));
	}
}
