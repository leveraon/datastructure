package com.leveraon.csfoundmental.algorithms.recursion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecursionBinarySearch {

	public static boolean binarySearch(int[] data, int target, int low, int high) {
		if (low > high) {
			return false;
		}

		else {
			int mid = (low + high) / 2;
			if (target == data[mid]) {
				return true;
			} else if (target < data[mid]) {
				return binarySearch(data, target, low, mid - 1);
			} else {
				return binarySearch(data, target, mid + 1, high);
			}
		}

	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 4, 5, 8, 10, 12, 39, 49, 59, 67, 68, 70, 82, 99, 100 };

		int low = 0, high = 15, target = 88, target2 = 82;

		log.info("Searching for {}, is exist: {}", target, binarySearch(array, target, low, high));
		log.info("Searching for {}, is exist: {}", target2, binarySearch(array, target2, low, high));
	}
}
