package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.Arrays;

public class MaxSumSubarrayK {
	/**
	 * Finds the maximum sum of any contiguous subarray of size K in the given
	 * array. Uses the sliding window technique for O(N) time complexity.
	 *
	 * @param arr The input array of integers.
	 * @param k   The desired size of the subarray.
	 * @return The maximum sum of a subarray of size K.
	 * @throws IllegalArgumentException if the input array is invalid or k is out of
	 *                                  bounds.
	 */
	public static int findMaxSumSubarray(int[] arr, int k) {
		// --- 1. Handle Edge Cases and Invalid Inputs ---
		if (arr == null || arr.length == 0 || k <= 0) {
			throw new IllegalArgumentException("Array cannot be null or empty, and k must be a positive integer.");
		}
		if (k > arr.length) {
			throw new IllegalArgumentException("k cannot be greater than the length of the array.");
		}

		// --- 2. Initialize Variables ---
		int windowSum = 0; // Stores the sum of the current window
		int maxSum = Integer.MIN_VALUE; // Stores the maximum sum found so far, initialized to smallest possible integer

		// --- 3. Calculate Sum of the First Window (0 to k-1) ---
		for (int i = 0; i < k; i++) {
			windowSum += arr[i];
		}
		// The sum of the first window is our initial maximum
		maxSum = windowSum;

		// --- 4. Slide the Window ---
		// 'windowEnd' iterates from k to arr.length - 1
		// 'windowStart' can be inferred as (windowEnd - k + 1)
		for (int windowEnd = k; windowEnd < arr.length; windowEnd++) {
			// Add the new element entering the window from the right
			windowSum += arr[windowEnd];

			// Subtract the element leaving the window from the left
			// The element to remove is at index (windowEnd - k)
			windowSum -= arr[windowEnd - k];

			// Update maxSum if the current window's sum is greater
			maxSum = Math.max(maxSum, windowSum);
		}

		return maxSum;
	}

	// --- Main method for testing ---
	public static void main(String[] args) {
		System.out.println("--- Test Cases ---");

		// Test Case 1: Basic example
		int[] arr1 = { 2, 1, 5, 1, 3, 2 };
		int k1 = 3;
		// Expected output: (5+1+3) = 9
		System.out.println(
				"Array: " + Arrays.toString(arr1) + ", k: " + k1 + " -> Max Sum: " + findMaxSumSubarray(arr1, k1)); // Expected:
																													// 9

		// Test Case 2: Different array and k
		int[] arr2 = { 2, 3, 4, 1, 5 };
		int k2 = 2;
		// Expected output: (3+4) = 7
		System.out.println(
				"Array: " + Arrays.toString(arr2) + ", k: " + k2 + " -> Max Sum: " + findMaxSumSubarray(arr2, k2)); // Expected:
																													// 7

		// Test Case 3: Single element window
		int[] arr3 = { 10, -5, 20, -100, 30 };
		int k3 = 1;
		// Expected output: 30
		System.out.println(
				"Array: " + Arrays.toString(arr3) + ", k: " + k3 + " -> Max Sum: " + findMaxSumSubarray(arr3, k3)); // Expected:
																													// 30

		// Test Case 4: Array with negative numbers
		int[] arr4 = { -2, -1, -5, -1, -3, -2 };
		int k4 = 3;
		// Expected output: (-1 + -5 + -1) = -7 (max of [-8, -7, -7, -6])
		System.out.println(
				"Array: " + Arrays.toString(arr4) + ", k: " + k4 + " -> Max Sum: " + findMaxSumSubarray(arr4, k4)); // Expected:
																													// -7

		// Test Case 5: k equals array length
		int[] arr5 = { 1, 2, 3, 4, 5 };
		int k5 = 5;
		// Expected output: 15
		System.out.println(
				"Array: " + Arrays.toString(arr5) + ", k: " + k5 + " -> Max Sum: " + findMaxSumSubarray(arr5, k5)); // Expected:
																													// 15

		// Test Case 6: Edge case - k too large
		int[] arr6 = { 1, 2, 3 };
		int k6 = 4;
		try {
			System.out.println(
					"Array: " + Arrays.toString(arr6) + ", k: " + k6 + " -> Max Sum: " + findMaxSumSubarray(arr6, k6));
		} catch (IllegalArgumentException e) {
			System.out.println("Caught expected exception: " + e.getMessage());
		}

		// Test Case 7: Edge case - empty array
		int[] arr7 = {};
		int k7 = 1;
		try {
			System.out.println(
					"Array: " + Arrays.toString(arr7) + ", k: " + k7 + " -> Max Sum: " + findMaxSumSubarray(arr7, k7));
		} catch (IllegalArgumentException e) {
			System.out.println("Caught expected exception: " + e.getMessage());
		}

		// Test Case 8: Edge case - k is zero or negative
		int[] arr8 = { 1, 2, 3 };
		int k8 = 0;
		try {
			System.out.println(
					"Array: " + Arrays.toString(arr8) + ", k: " + k8 + " -> Max Sum: " + findMaxSumSubarray(arr8, k8));
		} catch (IllegalArgumentException e) {
			System.out.println("Caught expected exception: " + e.getMessage());
		}
	}
}
