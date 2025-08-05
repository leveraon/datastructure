package com.leveraon.csfoundmental.algorithms.examples.recursion;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArraySumRecursive {

	public static int linearSum(int[] array, int n) {
		if (n == 0) {
			return 0;
		}

		return linearSum(array, n - 1) + array[n - 1];
	}

	/** Returns the sum of subarray data[low] through data[high] inclusive. */
	public static int binarySum(int[] data, int low, int high) {
		if (low > high) // zero elements in subarray
			return 0;
		else if (low == high) // one element in subarray
			return data[low];
		else {
			int mid = (low + high) / 2;
			return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
		}
	}

	public static void main(String[] args) {
		int[] testArray = RandomIntArrayGenerator.generateRandomIntArray(10, 0, 50);

		log.info("Array is {}", testArray);

		log.info("Linear Sum of arry {}", linearSum(testArray, testArray.length));

		log.info("Binary Sum of arry {}", binarySum(testArray, 0, testArray.length - 1));
	}

}
