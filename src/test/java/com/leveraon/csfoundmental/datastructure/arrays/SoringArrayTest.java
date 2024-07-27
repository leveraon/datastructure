package com.leveraon.csfoundmental.datastructure.arrays;

import org.junit.jupiter.api.Test;

import com.leveraon.csfoundmental.datastructure.TestBase;

public class SoringArrayTest extends TestBase {

	@Test
	public void testInsertAndSorting() {
		char[] array = { 'A', 'B', 'D', 'F', 'C', 'H', 'G', 'E' };
		System.out.println("Before sorting:");
		printArray(array);

		SortingArray.insertionSort(array);
		System.out.println("After sorting:");
		printArray(array);
	}

	public static void printArray(char[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i != array.length - 1) {
				System.out.print(" ,");
			}
		}
		System.out.print("\n");
	}

}
