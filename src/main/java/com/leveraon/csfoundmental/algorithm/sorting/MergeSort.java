package com.leveraon.csfoundmental.algorithm.sorting;

import java.util.Arrays;
import java.util.Comparator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MergeSort {
    /** Merge contents of arrays S1 and S2 into properly sized array S. */
    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i + j] = S1[i++]; // copy ith element of S1 and increment i
            else
                S[i + j] = S2[j++]; // copy jth element of S2 and increment j
        }
    }

    /** Merge-sort contents of array S. */
    public static <K> void mergeSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        if (n < 2)
            return; // array is trivially sorted
        // divide
        int mid = n / 2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid); // copy of first half
        K[] S2 = Arrays.copyOfRange(S, mid, n); // copy of second half
        // conquer (with recursion)
        mergeSort(S1, comp); // sort copy of first half
        mergeSort(S2, comp); // sort copy of second half
        // merge results
        merge(S1, S2, S, comp); // merge sorted halves back into original
    }

    public static class IntComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 - o2 > 0) ? 1 : (o1 - o2 == 0) ? 0 : -1;
        }

    }

    public static void main(String[] args) {
        Integer[] array = { 1, 2, 3, 4, 55, 88, 32, 23, 24, 13, 39, 9, 29, 74, 28 };
        log.info("Array before sorting ");
        for (Integer integer : array) {
            System.out.print(integer + ",");
        }
        System.out.println("\n");
        Comparator<Integer> comp = new IntComparator();
        mergeSort(array, comp);

        log.info("Array after sorting ");
        for (Integer integer : array) {
            System.out.print(integer + ",");
        }
    }

}
