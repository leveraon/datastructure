package com.leveraon.csfoundmental.algorithms.examples.sorting;

import java.util.Arrays;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MergeSort {

	/**
	 * Public method to sort an array using Merge Sort. This method acts as a
	 * wrapper to call the recursive mergeSort method.
	 *
	 * @param arr
	 *            The array to be sorted.
	 */
	public void sort(int[] arr) {
		// Handle edge cases: null array or array with 0 or 1 element is already
		// sorted.
		if (arr == null || arr.length <= 1) {
			return;
		}
		// Call the recursive mergeSort method with initial left and right
		// bounds.
		mergeSortRecursive(arr, 0, arr.length - 1);
	}

	/**
	 * Recursive method to divide the array into halves and sort them.
	 *
	 * @param arr
	 *            The array to be sorted.
	 * @param left
	 *            The starting index of the sub-array.
	 * @param right
	 *            The ending index of the sub-array.
	 */
	private void mergeSortRecursive(int[] arr, int left, int right) {
		// Base case: If left >= right, it means the sub-array has 0 or 1
		// element,
		// which is considered sorted.
		if (left >= right) {
			return;
		}

		// Find the middle point to divide the array into two halves.
		// Using left + (right - left) / 2 prevents potential integer overflow
		// compared to (left + right) / 2 if left and right are very large.
		int mid = left + (right - left) / 2;

		// Recursively sort the first half.
		mergeSortRecursive(arr, left, mid);

		// Recursively sort the second half.
		mergeSortRecursive(arr, mid + 1, right);

		// Merge the two sorted halves.
		merge(arr, left, mid, right);
	}

	/**
	 * Merges two sorted sub-arrays into a single sorted array. This is the core
	 * operation of Merge Sort.
	 *
	 * @param arr
	 *            The original array containing the two sorted sub-arrays.
	 * @param left
	 *            The starting index of the first sub-array.
	 * @param mid
	 *            The ending index of the first sub-array (and mid + 1 is start
	 *            of second).
	 * @param right
	 *            The ending index of the second sub-array.
	 */
	private void merge(int[] arr, int left, int mid, int right) {
		// Calculate the sizes of the two sub-arrays to be merged.
		int n1 = mid - left + 1; // Size of the left sub-array (arr[left...mid])
		int n2 = right - mid; // Size of the right sub-array
								// (arr[mid+1...right])

		// Create temporary arrays to hold the elements of the two sub-arrays.
		int[] L = new int[n1];
		int[] R = new int[n2];

		// Copy data from the original array to the temporary left array.
		for (int i = 0; i < n1; i++) {
			L[i] = arr[left + i];
		}
		// Copy data from the original array to the temporary right array.
		for (int j = 0; j < n2; j++) {
			R[j] = arr[mid + 1 + j];
		}

		// Initialize pointers for iterating through L, R, and the original
		// array.
		int i = 0; // Initial index of first sub-array (L)
		int j = 0; // Initial index of second sub-array (R)
		int k = left; // Initial index of merged sub-array in arr

		// Merge the temporary arrays back into the original array
		// arr[left...right].
		// Compare elements from L and R and place the smaller one into arr.
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++; // Move to the next position in the original array
		}

		// Copy any remaining elements of L[] if any are left (e.g., if R[] was
		// exhausted).
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		// Copy any remaining elements of R[] if any are left (e.g., if L[] was
		// exhausted).
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	public static void main(String[] args) {
		MergeSort mergeSort = new MergeSort();
		log.info("Merge sort using array");
		int[] array = RandomIntArrayGenerator.generateRandomIntArray(10, 3, 100);
		System.out.println("Before -> " + Arrays.toString(array));
		mergeSort.sort(array);
		System.out.println("After -> " + Arrays.toString(array));
	}

}
