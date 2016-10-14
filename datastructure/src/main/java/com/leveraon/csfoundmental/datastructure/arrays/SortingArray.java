package com.leveraon.csfoundmental.datastructure.arrays;

public class SortingArray {
	/** Insertion-sort of an array of characters into nondecreasing order */
	public static void insertionSort(char[] data) {
		for (int k = 1; k < data.length; k++) { // begin with second character
			char cur = data[k]; // time to insert cur=data[k]
			int j = k; // find correct index j for cur
			while (j > 0 && data[j - 1] > cur) { // thus, data[j-1] must go
													// after cur
				System.out.println("data[j] is " + data[j] + " Cur:" + cur);
				data[j] = data[j - 1]; // slide data[j-1] rightward
				j--; // and consider previous j for cur
			}
			data[j] = cur; // this is the proper place for cur
		}
	}
}
