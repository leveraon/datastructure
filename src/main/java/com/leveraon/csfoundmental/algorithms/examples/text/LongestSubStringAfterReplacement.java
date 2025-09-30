package com.leveraon.csfoundmental.algorithms.examples.text;

import java.util.Arrays;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Given a binary array nums and an integer k, return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's.
 * </p>
 * <br/>
 * <p>
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 <br/>
 * Output: 6 <br/>
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1] <br/>
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * 
 * </p>
 */
@Slf4j
public class LongestSubStringAfterReplacement {
	public int longestOnes(int[] A, int K) {
		int max = 0;
		int zeroCount = 0; // zero count in current window
		int i = 0; // slow pointer
		for (int j = 0; j < A.length; ++j) {
			if (A[j] == 0) { // move forward j, if current is 0, increase the zeroCount
				zeroCount++;
			}

			// when current window has more than K, the window is not valid any more
			// we need to loop the slow pointer until the current window is valid
			while (zeroCount > K) {
				if (A[i] == 0) {
					zeroCount--;
				}
				i++;
			}
			max = Math.max(max, j - i + 1); // everytime we get here, the current window is valid
		}
		return max;
	}

	public static void main(String[] args) {
		LongestSubStringAfterReplacement demo = new LongestSubStringAfterReplacement();

		int[] test = RandomIntArrayGenerator.generateRandomIntArray(10, 0, 1);

		int result = demo.longestOnes(test, 2);

		log.info("original array {}", Arrays.toString(test));
		log.info("result {}", result);
	}
}
