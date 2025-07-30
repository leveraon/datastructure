package com.leveraon.csfoundmental.algorithms.utils;

import java.util.Random;

public class RandomIntArrayGenerator {
	/**
	 * Generates an array of random integers.
	 *
	 * @param size The number of elements in the array.
	 * @param min  The minimum possible value for an element (inclusive).
	 * @param max  The maximum possible value for an element (inclusive).
	 * @return An array of random integers.
	 * @throws IllegalArgumentException if size is negative or min > max.
	 */
	public static int[] generateRandomIntArray(int size, int min, int max) {
		// --- Input Validation ---
		if (size < 0) {
			throw new IllegalArgumentException("Array size cannot be negative.");
		}
		if (min > max) {
			throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.");
		}

		// --- Initialization ---
		int[] randomArray = new int[size];
		Random random = new Random(); // Create a new Random object

		// --- Populate the array ---
		// The formula for a random integer within [min, max] (inclusive) is:
		// min + random.nextInt(max - min + 1)
		// random.nextInt(bound) generates a number from 0 (inclusive) to bound
		// (exclusive).
		// So, max - min + 1 gives us the total number of possible values in the range.
		// Adding 'min' shifts this range to start from 'min'.
		for (int i = 0; i < size; i++) {
			randomArray[i] = min + random.nextInt(max - min + 1);
		}

		return randomArray;
	}

}
