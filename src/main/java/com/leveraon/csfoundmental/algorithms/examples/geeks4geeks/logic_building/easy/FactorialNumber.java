package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * Given the number n (n >=0), find its factorial. Factorial of n is defined as
 * 1 x 2 x ... x n. For n = 0, factorial is 1.
 */
public class FactorialNumber {

	static int factorial(int number) {
		int result = 1;

		if (number == 0)
			return 0;

		if (number == 1)
			return 1;

		while (number > 0) {
			result *= number;
			number--;
		}

		return result;
	}

	public static void main(String[] args) {
		int test = 5;

		System.out.println(test + " factorial number is " + factorial(test));
	}
}
