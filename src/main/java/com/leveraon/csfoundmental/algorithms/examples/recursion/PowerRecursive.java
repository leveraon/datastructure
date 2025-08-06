package com.leveraon.csfoundmental.algorithms.examples.recursion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PowerRecursive {
	/**
	 * Computes the value of x raised to the nth power, for nonnegative integer
	 * n. <br>
	 * Time Complexity: O(n) <br>
	 * Space Complexity: O(n)
	 */
	public static double power(double x, int n) {
		if (n == 0)
			return 1;
		else
			return x * power(x, n - 1);
	}

	/**
	 * Computes the value of x raised to the nth power, for nonnegative integer
	 * n. <br>
	 * Time Complexity: O(log(n)) <br>
	 * Space Complexity: O(log(n))
	 */
	public static double powerEfficient(double x, int n) {
		if (n == 0)
			return 1;
		else {
			double partial = powerEfficient(x, n / 2); // rely on truncated
														// division of n
			double result = partial * partial;
			if (n % 2 == 1) // if n odd, include extra factor of x
				result *= x;
			return result;
		}
	}

	public static void main(String[] args) {
		int base = 2, power = 10;

		log.info("{} power of {} is: {}", base, power, power(base, power));
		log.info("{} power of {} use powerEfficient is: {}", base, power, powerEfficient(base, power));
	}
}
