package com.leveraon.csfoundmental.algorithms.examples.iteration;

/**
 * 3. Iterative Approach (Bottom-Up Dynamic Programming) This is generally the
 * most efficient and preferred method for calculating Fibonacci numbers in an
 * imperative language like Java. It builds the sequence from the bottom up
 * (starting from F(0) and F(1)) without recursion.
 * 
 * Pros:
 * 
 * Most efficient: O(n) time complexity and O(1) space complexity (constant
 * space, as it only needs to store the last two numbers). No risk of
 * StackOverflowError. Can handle much larger n values (up to where long
 * overflows). Cons:
 * 
 * May be slightly less "elegant" than the direct recursive definition for some.
 */
public class FibonacciIterative {
	/**
	 * Calculates the Nth Fibonacci number using an iterative approach. This is
	 * generally the most efficient method for calculating a single Nth Fibonacci
	 * number.
	 *
	 * @param n The index of the Fibonacci number to calculate.
	 * @return The Nth Fibonacci number.
	 */
	public static long fibonacciIterative(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Fibonacci index cannot be negative.");
		}
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		long a = 0; // Represents F(i-2)
		long b = 1; // Represents F(i-1)
		long result = 0; // Represents F(i)

		for (int i = 2; i <= n; i++) {
			result = a + b; // Current Fibonacci number is the sum of the previous two
			a = b; // Update a to be the previous b
			b = result; // Update b to be the current result (which becomes the new "previous")
		}
		return result;
	}

	/**
	 * Prints the first N Fibonacci numbers.
	 *
	 * @param n The number of Fibonacci numbers to print.
	 */
	public static void printFibonacciSeries(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Number of Fibonacci terms cannot be negative.");
		}
		if (n == 0) {
			System.out.println("[]");
			return;
		}
		if (n == 1) {
			System.out.println("[0]");
			return;
		}

		long a = 0;
		long b = 1;
		System.out.print("[0, 1"); // Print initial two terms

		for (int i = 2; i < n; i++) {
			long next = a + b;
			System.out.print(", " + next);
			a = b;
			b = next;
		}
		System.out.println("]");
	}

	// --- Main method for demonstration ---
	public static void main(String[] args) {
		System.out.println("\n--- Iterative Approach ---");
		for (int i = 0; i <= 10; i++) {
			System.out.println("F(" + i + ") = " + fibonacciIterative(i));
		}
		System.out.println("F(90) = " + fibonacciIterative(90)); // long can hold up to F(92)

		System.out.println("\n--- Printing Fibonacci Series (Iterative) ---");
		System.out.print("First 10 Fibonacci numbers: ");
		printFibonacciSeries(10);

		System.out.print("First 1 Fibonacci numbers: ");
		printFibonacciSeries(1);
	}
}
