/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
public class ReverseIterative {
	/** Reverses the contents of the given array. */
	public static void reverseIterative(int[] data) {
		int low = 0, high = data.length - 1;
		while (low < high) { // swap data[low] and data[high]
			int temp = data[low];
			data[low++] = data[high]; // post-increment of low
			data[high--] = temp; // post-decrement of high
		}
	}

	public static void main(String[] args) {
		int[] array = RandomIntArrayGenerator.generateRandomIntArray(10, 0, 200);
		log.info("Array before reverse {}", array);
		reverseIterative(array);
		log.info("Array after reverse {}", array);
	}

}
