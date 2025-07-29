/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

import java.util.Arrays;

/**
 * 
 */
public class InsertionSort {
	/**
	 * Sorts an array of integers using the Insertion Sort algorithm. The sorting is
	 * done in-place.
	 *
	 * @param arr The array of integers to be sorted.
	 */
	public static void sort(int[] arr) {
		// Handle edge cases: null array or array with 0 or 1 element
		// These are already "sorted" or don't need sorting.
		if (arr == null || arr.length <= 1) {
			return;
		}

		int n = arr.length;

		// Start from the second element (index 1) because the first element (index 0)
		// is considered the initial "sorted" part of the array.
		for (int i = 1; i < n; i++) {
			// 'key' is the element we are currently trying to insert into the
			// sorted subarray (elements from arr[0] to arr[i-1]).
			int key = arr[i];

			// 'j' is the index of the last element in the currently sorted subarray.
			// We compare 'key' with elements to its left, moving backwards.
			int j = i - 1;

			// Move elements of arr[0...i-1], that are greater than key,
			// to one position ahead of their current position.
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j]; // Shift element to the right
				j--; // Move to the next element on the left
			}

			// At this point, arr[j] is either smaller than or equal to 'key',
			// or we have reached the beginning of the array (j = -1).
			// Insert the 'key' into its correct position.
			arr[j + 1] = key;
		}
	}

	/**
	 * Main method to test the Insertion Sort implementation.
	 */
	public static void main(String[] args) {
		// Test Case 1: General unsorted array
		int[] arr1 = { 12, 11, 13, 5, 6 };
		System.out.println("Original Array 1: " + Arrays.toString(arr1));
		InsertionSort.sort(arr1);
		System.out.println("Sorted Array 1:   " + Arrays.toString(arr1)); // Expected: [5, 6, 11, 12, 13]
		System.out.println("--------------------");

		// Test Case 2: Already sorted array
		int[] arr2 = { 1, 2, 3, 4, 5 };
		System.out.println("Original Array 2: " + Arrays.toString(arr2));
		InsertionSort.sort(arr2);
		System.out.println("Sorted Array 2:   " + Arrays.toString(arr2)); // Expected: [1, 2, 3, 4, 5]
		System.out.println("--------------------");

		// Test Case 3: Reverse sorted array
		int[] arr3 = { 5, 4, 3, 2, 1 };
		System.out.println("Original Array 3: " + Arrays.toString(arr3));
		InsertionSort.sort(arr3);
		System.out.println("Sorted Array 3:   " + Arrays.toString(arr3)); // Expected: [1, 2, 3, 4, 5]
		System.out.println("--------------------");

		// Test Case 4: Array with duplicate elements
		int[] arr4 = { 8, 4, 2, 8, 1, 4 };
		System.out.println("Original Array 4: " + Arrays.toString(arr4));
		InsertionSort.sort(arr4);
		System.out.println("Sorted Array 4:   " + Arrays.toString(arr4)); // Expected: [1, 2, 4, 4, 8, 8]
		System.out.println("--------------------");

		// Test Case 5: Empty array
		int[] arr5 = {};
		System.out.println("Original Array 5: " + Arrays.toString(arr5));
		InsertionSort.sort(arr5);
		System.out.println("Sorted Array 5:   " + Arrays.toString(arr5)); // Expected: []
		System.out.println("--------------------");

		// Test Case 6: Single element array
		int[] arr6 = { 7 };
		System.out.println("Original Array 6: " + Arrays.toString(arr6));
		InsertionSort.sort(arr6);
		System.out.println("Sorted Array 6:   " + Arrays.toString(arr6)); // Expected: [7]
		System.out.println("--------------------");

		// Test Case 7: Array with negative numbers
		int[] arr7 = { -5, 0, -2, 3, -1 };
		System.out.println("Original Array 7: " + Arrays.toString(arr7));
		InsertionSort.sort(arr7);
		System.out.println("Sorted Array 7:   " + Arrays.toString(arr7)); // Expected: [-5, -2, -1, 0, 3]
		System.out.println("--------------------");
	}
}
