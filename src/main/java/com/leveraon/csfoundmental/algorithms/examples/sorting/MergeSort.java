package com.leveraon.csfoundmental.algorithms.examples.sorting;

import java.util.Arrays;
import java.util.Comparator;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MergeSort {

	public static void merge(int[] s1, int[] s2, int[] s, Comparator<Integer> comp) {
		int i = 0, j = 0;
		while (i + j < s.length) {
			if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0)) {
				s[i + j] = s1[i++];
			} else {
				s[i + j] = s2[j++];
			}
		}
	}

	public static void mergeSort(int[] s, Comparator<Integer> comp) {
		int length = s.length;

		if (length < 2) {
			return;
		}

		int mid = length / 2;
		// first half
		int[] s1 = Arrays.copyOfRange(s, 0, mid);
		// second half
		int[] s2 = Arrays.copyOfRange(s, mid, length);

		mergeSort(s1, comp);
		mergeSort(s2, comp);

		merge(s1, s2, s, comp);

	}

	public static void main(String[] args) {
		log.info("Merge sort using array");
		int[] array = RandomIntArrayGenerator.generateRandomIntArray(10, 3, 100);
		Comparator<Integer> comp = new Comparator<>() {
			public int compare(Integer o1, Integer o2) {
				return o1 - o2 >= 0 ? 1 : -1;
			}
		};
		System.out.println("Before -> " + Arrays.toString(array));
		mergeSort(array, comp);
		System.out.println("After -> " + Arrays.toString(array));
	}

}
