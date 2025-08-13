package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * <h2>Given a number n, find the sum of its digits.</h2><br>
 * Input: n = 687 <br>
 * Output: 21<br>
 * Explanation: The sum of its digits are: 6 + 8 + 7 = 21<br>
 * 
 * Input: n = 12<br>
 * Output: 3<br>
 * Explanation: The sum of its digits are: 1 + 2 = 3<br>
 */
public class SumOfDigits {

	/**
	 * Digit Extraction - O(log10n) Time and O(1) Space
	 * 
	 * @param n
	 * @return
	 */
	static int sumOfDigits(int n) {
		int sum = 0;
		while (n != 0) {
			// Extract the last digit
			int last = n % 10;

			// Add last digit to sum
			sum += last;

			// Remove the last digit
			n = n / 10;
		}
		return sum;
	}

	/**
	 * Using Recursion - O(log10n) Time and O(log10n) Space
	 * 
	 * @param n
	 * @return
	 */
	static int sumOfDigitsRecursive(int n) {

		// Base Case
		if (n == 0)
			return 0;

		// Recursive Case
		return (n % 10) + sumOfDigits(n / 10);
	}

	public static void main(String[] args) {
		int n = 12345;
		System.out.println(sumOfDigits(n));
		System.out.println(sumOfDigitsRecursive(n));
	}
}
