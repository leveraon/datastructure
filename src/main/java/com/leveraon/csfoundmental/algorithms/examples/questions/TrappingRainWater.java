package com.leveraon.csfoundmental.algorithms.examples.questions;

public class TrappingRainWater {
	/**
	 * Computes the total amount of water trapped after raining, given an elevation
	 * map. Uses the Two-Pointer approach for O(N) time and O(1) space complexity.
	 *
	 * @param height An array of non-negative integers representing the elevation
	 *               map.
	 * @return The total amount of trapped water.
	 */
	public int trap(int[] height) {
		if (height == null || height.length < 3) {
			return 0; // Not enough bars to trap water
		}

		int left = 0;
		int right = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;
		int totalWater = 0;

		while (left < right) {
			// Process the side with the smaller current height, as it is the limiting
			// factor.
			if (height[left] < height[right]) {
				// The water level at 'left' is determined by 'leftMax'.
				// Any water trapped here would be bounded by 'leftMax' on the left
				// and at least 'height[right]' (which is >= height[left]) on the right.
				if (height[left] >= leftMax) {
					leftMax = height[left]; // Update the left maximum wall
				} else {
					totalWater += leftMax - height[left]; // Trap water
				}
				left++; // Move left pointer inwards
			} else {
				// The water level at 'right' is determined by 'rightMax'.
				// Any water trapped here would be bounded by 'rightMax' on the right
				// and at least 'height[left]' (which is >= height[right]) on the left.
				if (height[right] >= rightMax) {
					rightMax = height[right]; // Update the right maximum wall
				} else {
					totalWater += rightMax - height[right]; // Trap water
				}
				right--; // Move right pointer inwards
			}
		}

		return totalWater;
	}
}
