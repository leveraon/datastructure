package com.leveraon.csfoundmental.algorithms.examples.sorting;

import java.util.Arrays;

public class SelectionSort {
	/**
	 * Sorts an array of integers using the Selection Sort algorithm.
	 *
	 * @param arr The array of integers to be sorted.
	 */
	public static void selectionSort(int[] arr) {
		// Handle edge cases: null or empty array
		if (arr == null || arr.length == 0) {
			return;
		}

		int n = arr.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in the unsorted array arr[i...n-1]
			int minIndex = i; // Assume current element is the minimum

			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j; // Update minIndex if a smaller element is found
				}
			}

			// Swap the found minimum element with the first element of the unsorted
			// subarray
			// Only perform the swap if the minimum element is not already in its correct
			// position
			if (minIndex != i) {
				int temp = arr[minIndex];
				arr[minIndex] = arr[i];
				arr[i] = temp;
			}

			// Optional: Print array state after each pass (for visualization)
			// System.out.println("Array after pass " + (i + 1) + ": " +
			// Arrays.toString(arr));
		}
	}

	/**
	 * Helper method to print an array.
	 *
	 * @param arr The array to print.
	 */
	public static void printArray(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		int[] arr1 = { 64, 25, 12, 22, 11 };
		System.out.println("Original array 1:");
		printArray(arr1);
		selectionSort(arr1);
		System.out.println("Sorted array 1:");
		printArray(arr1);

		System.out.println("\n---");

		int[] arr2 = { 5, 1, 4, 2, 8 };
		System.out.println("Original array 2:");
		printArray(arr2);
		selectionSort(arr2);
		System.out.println("Sorted array 2:");
		printArray(arr2);

		System.out.println("\n---");

		int[] arr3 = { 1 }; // Single element array
		System.out.println("Original array 3:");
		printArray(arr3);
		selectionSort(arr3);
		System.out.println("Sorted array 3:");
		printArray(arr3);

		System.out.println("\n---");

		int[] arr4 = {}; // Empty array
		System.out.println("Original array 4:");
		printArray(arr4);
		selectionSort(arr4);
		System.out.println("Sorted array 4:");
		printArray(arr4);
	}
}
