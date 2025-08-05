/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.iteration;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
public class BinarySearchIterative {
	/** Returns true if the target value is found in the data array. */
	public static boolean binarySearchIterative(int[] data, int target) {
		int low = 0;
		int high = data.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (target == data[mid]) // found a match
				return true;
			else if (target < data[mid])
				high = mid - 1; // only consider values left of mid
			else
				low = mid + 1; // only consider values right of mid
		}
		return false; // loop ended without success
	}
	
	
	public static void main(String[] args) {
		int[] array = RandomIntArrayGenerator.generateRandomIntArray(10, 0, 100);

		int target = 88, target2 = 82;

		log.info("Searching for {}, is exist: {}", target, binarySearchIterative(array, target));
		log.info("Searching for {}, is exist: {}", target2, binarySearchIterative(array, target2));
	}
}
