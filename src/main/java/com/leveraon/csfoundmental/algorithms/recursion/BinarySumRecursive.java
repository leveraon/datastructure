package com.leveraon.csfoundmental.algorithms.recursion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinarySumRecursive {
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
        int[] testArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        log.info("Array is {}", testArray);

        log.info("Binary Sum of arry {}", binarySum(testArray, 0, 9));
    }

}
