package com.leveraon.csfoundmental.algorithms.examples.sorting;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Best-Case Time Complexity: O(n log n)
 * 
 * Average-Case Time Complexity: O(n log n)
 * 
 * Worst-Case Time Complexity: O(n^2)
 * 
 * Space Complexity: O(log n) (due to recursion stack, average case) or O(n)
 * (worst case for recursion stack, if not optimized with tail call optimization
 * or iterative approach).
 * 
 * @author leveraon
 *
 *         Created on: Aug 3, 2025
 */

@Slf4j
public class QuickSort {
	 /**
     * Main method to perform QuickSort on an array.
     * It's a wrapper to start the recursive process.
     *
     * @param arr The array to be sorted.
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return; // Nothing to sort
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursive helper method for QuickSort.
     *
     * @param arr   The array to be sorted.
     * @param low   The starting index of the sub-array.
     * @param high  The ending index of the sub-array.
     */
    private void quickSort(int[] arr, int low, int high) {
        // Base case: If the sub-array has 0 or 1 elements, it's already sorted.
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at correct position
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);  // Sort left sub-array
            quickSort(arr, pi + 1, high); // Sort right sub-array
        }
    }

    /**
     * This function takes last element as pivot, places the pivot element at its correct
     * position in sorted array, and places all smaller (than pivot) to left of pivot
     * and all greater elements to right of pivot.
     * (Lomuto Partition Scheme)
     *
     * @param arr   The array being partitioned.
     * @param low   The starting index of the sub-array.
     * @param high  The ending index of the sub-array (where the pivot is initially).
     * @return The final index of the pivot element.
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose the last element as the pivot
        int i = (low - 1);     // Index of smaller element, indicating the rightmost boundary of elements known to be smaller than pivot

        // Iterate through the sub-array from low to high-1
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++; // Increment index of smaller element
                swap(arr, i, j); // Swap current element with the element at i
            }
        }

        // After the loop, all elements from arr[low] to arr[i] are less than or equal to the pivot.
        // Elements from arr[i+1] to arr[high-1] are greater than the pivot.
        // Now, place the pivot (arr[high]) at its correct sorted position (arr[i+1]).
        swap(arr, i + 1, high);

        return i + 1; // Return the final index of the pivot
    }

    /**
     * A utility function to swap two elements in an array.
     *
     * @param arr The array.
     * @param i   Index of the first element.
     * @param j   Index of the second element.
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


 // Main method for testing
    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();

        int[] arr1 = {10, 7, 8, 9, 1, 5};
        System.out.println("Original array 1: " + Arrays.toString(arr1));
        sorter.sort(arr1);
        System.out.println("Sorted array 1:   " + Arrays.toString(arr1)); // Expected: [1, 5, 7, 8, 9, 10]
        System.out.println("---");

        int[] arr2 = {4, 2, 8, 1, 3, 7, 5, 6};
        System.out.println("Original array 2: " + Arrays.toString(arr2));
        sorter.sort(arr2);
        System.out.println("Sorted array 2:   " + Arrays.toString(arr2)); // Expected: [1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println("---");

        int[] arr3 = {5, 4, 3, 2, 1}; // Reverse sorted
        System.out.println("Original array 3: " + Arrays.toString(arr3));
        sorter.sort(arr3);
        System.out.println("Sorted array 3:   " + Arrays.toString(arr3)); // Expected: [1, 2, 3, 4, 5]
        System.out.println("---");

        int[] arr4 = {1, 2, 3, 4, 5}; // Already sorted
        System.out.println("Original array 4: " + Arrays.toString(arr4));
        sorter.sort(arr4);
        System.out.println("Sorted array 4:   " + Arrays.toString(arr4)); // Expected: [1, 2, 3, 4, 5]
        System.out.println("---");

        int[] arr5 = {}; // Empty array
        System.out.println("Original array 5: " + Arrays.toString(arr5));
        sorter.sort(arr5);
        System.out.println("Sorted array 5:   " + Arrays.toString(arr5)); // Expected: []
        System.out.println("---");

        int[] arr6 = {7}; // Single element array
        System.out.println("Original array 6: " + Arrays.toString(arr6));
        sorter.sort(arr6);
        System.out.println("Sorted array 6:   " + Arrays.toString(arr6)); // Expected: [7]
        System.out.println("---");

        int[] arr7 = {3, 1, 4, 1, 5, 9, 2, 6}; // Array with duplicates
        System.out.println("Original array 7: " + Arrays.toString(arr7));
        sorter.sort(arr7);
        System.out.println("Sorted array 7:   " + Arrays.toString(arr7)); // Expected: [1, 1, 2, 3, 4, 5, 6, 9]
        System.out.println("---");
    }
}
