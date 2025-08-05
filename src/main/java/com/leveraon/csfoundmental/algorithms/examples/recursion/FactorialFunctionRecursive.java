package com.leveraon.csfoundmental.algorithms.examples.recursion;

import java.math.BigInteger;

public class FactorialFunctionRecursive {
	/**
	 * Calculates the factorial of a non-negative integer using an iterative
	 * approach. This method uses 'long' to handle larger numbers, but will still
	 * overflow for n > 20.
	 *
	 * @param n The non-negative integer for which to calculate the factorial.
	 * @return The factorial of n.
	 * @throws IllegalArgumentException if n is negative or if the result overflows
	 *                                  'long'.
	 */
	public static long iterativeFactorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + n);
		}
		if (n == 0) {
			return 1; // 0! = 1
		}
		// Warn about potential overflow for long
		if (n > 20) {
			// A more graceful approach might return BigInteger or handle it differently,
			// but for a 'long' method, throwing is appropriate.
			throw new IllegalArgumentException(
					"Factorial of " + n + " will overflow 'long'. Use bigIntegerFactorial for n > 20.");
		}

		long result = 1;
		for (int i = 1; i <= n; i++) {
			// Optional: Add an overflow check before multiplication if desired,
			// though for n=21 it will overflow on the last multiplication.
			// if (Long.MAX_VALUE / i < result) { // Check if result * i would overflow
			// throw new ArithmeticException("Factorial result overflowed long at n=" + n);
			// }
			result *= i;
		}
		return result;
	}

	/**
	 * Calculates the factorial of a non-negative integer using a recursive
	 * approach. This method uses 'long' and has the same overflow limitations as
	 * iterativeFactorial.
	 *
	 * @param n The non-negative integer for which to calculate the factorial.
	 * @return The factorial of n.
	 * @throws IllegalArgumentException if n is negative or if the result overflows
	 *                                  'long'.
	 */
	public static long recursiveFactorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + n);
		}
		// Warn about potential overflow for long at the start
		if (n > 20) {
			throw new IllegalArgumentException(
					"Factorial of " + n + " will overflow 'long'. Use bigIntegerFactorial for n > 20.");
		}
		if (n == 0) {
			return 1; // Base case: 0! = 1
		}
		// Recursive step: n! = n * (n-1)!
		return n * recursiveFactorial(n - 1);
	}

	/**
	 * Calculates the factorial of a non-negative integer using BigInteger. This
	 * method can handle very large numbers without overflow (limited only by
	 * memory).
	 *
	 * @param n The non-negative integer for which to calculate the factorial.
	 * @return The factorial of n as a BigInteger.
	 * @throws IllegalArgumentException if n is negative.
	 */
	public static BigInteger bigIntegerFactorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + n);
		}
		if (n == 0) {
			return BigInteger.ONE; // 0! = 1
		}

		BigInteger result = BigInteger.ONE;
		for (int i = 1; i <= n; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

	public static void main(String[] args) {
		// --- Test Iterative Factorial (long) ---
		System.out.println("--- Iterative Factorial (long) ---");
		try {
			System.out.println("Factorial of 0: " + iterativeFactorial(0)); // Expected: 1
			System.out.println("Factorial of 5: " + iterativeFactorial(5)); // Expected: 120
			System.out.println("Factorial of 12: " + iterativeFactorial(12)); // Expected: 479001600
			System.out.println("Factorial of 20: " + iterativeFactorial(20)); // Expected: 2432902008176640000
			// This will throw an IllegalArgumentException
			// System.out.println("Factorial of 21 (should overflow long): " +
			// iterativeFactorial(21));
			// This will throw an IllegalArgumentException
			// System.out.println("Factorial of -5 (should throw error): " +
			// iterativeFactorial(-5));
		} catch (IllegalArgumentException e) {
			System.err.println("Error for iterativeFactorial: " + e.getMessage());
		}
		System.out.println();

		// --- Test Recursive Factorial (long) ---
		System.out.println("--- Recursive Factorial (long) ---");
		try {
			System.out.println("Factorial of 0: " + recursiveFactorial(0)); // Expected: 1
			System.out.println("Factorial of 5: " + recursiveFactorial(5)); // Expected: 120
			System.out.println("Factorial of 12: " + recursiveFactorial(12)); // Expected: 479001600
			System.out.println("Factorial of 20: " + recursiveFactorial(20)); // Expected: 2432902008176640000
			// This will throw an IllegalArgumentException
			// System.out.println("Factorial of 21 (should overflow long): " +
			// recursiveFactorial(21));
			// This will throw an IllegalArgumentException
			// System.out.println("Factorial of -5 (should throw error): " +
			// recursiveFactorial(-5));
		} catch (IllegalArgumentException e) {
			System.err.println("Error for recursiveFactorial: " + e.getMessage());
		}
		System.out.println();

		// --- Test BigInteger Factorial ---
		System.out.println("--- BigInteger Factorial ---");
		try {
			System.out.println("Factorial of 0: " + bigIntegerFactorial(0)); // Expected: 1
			System.out.println("Factorial of 5: " + bigIntegerFactorial(5)); // Expected: 120
			System.out.println("Factorial of 20: " + bigIntegerFactorial(20)); // Expected: 2432902008176640000
			System.out.println("Factorial of 21: " + bigIntegerFactorial(21)); // Expected: 51090942171709440000
			System.out.println("Factorial of 50: " + bigIntegerFactorial(50)); // A very large number
			System.out.println("Factorial of 100: " + bigIntegerFactorial(100)); // An even larger number
			// This will throw an IllegalArgumentException
			// System.out.println("Factorial of -5 (should throw error): " +
			// bigIntegerFactorial(-5));
		} catch (IllegalArgumentException e) {
			System.err.println("Error for bigIntegerFactorial: " + e.getMessage());
		}
	}
}
