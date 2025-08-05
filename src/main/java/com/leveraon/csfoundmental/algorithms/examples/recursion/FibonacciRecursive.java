package com.leveraon.csfoundmental.algorithms.examples.recursion;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FibonacciRecursive {

	private static Double[] memo;

	/**
	 * Solution 1
	 * 
	 * @param n
	 * @return
	 */
	public static Double fibonacciMemoized(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Fibonacci index cannot be negative.");
		}
		// Initialize the memoization array for the current call
		memo = new Double[n + 1];
		return fibonacciMemoizedHelper(n);
	}

	private static Double fibonacciMemoizedHelper(int n) {
		if (n == 0) {
			return Double.valueOf(0);
		}
		if (n == 1) {
			return Double.valueOf(1);
		}

		// If the value is already computed, return it from memo
		if (memo[n] != null) {
			return memo[n];
		}

		// Compute the value, store it in memo, and then return
		memo[n] = fibonacciMemoizedHelper(n - 1) + fibonacciMemoizedHelper(n - 2);
		return memo[n];
	}

	/**
	 * Solution 2
	 * 
	 * @param n
	 * @return
	 */
	public static Double[] fibonacci(Double n) {
		log.info("Current n : " + n);
		if (n <= 1) {
			Double[] result = { n, Double.valueOf(0) };

			return result;
		} else {
			Double[] next = fibonacci(n - 1);
			log.info("next: {}", Arrays.toString(next));
			Double[] answer = { next[1] + next[0], next[0] };
			log.info("answer: {}", Arrays.toString(answer));
			return answer;
		}
	}

	public static void main(String[] args) {
		Double fibonacci = Double.valueOf(50);
		fibonacci(fibonacci);

		System.out.println("F(50) = " + fibonacciMemoized(50));
	}

}
