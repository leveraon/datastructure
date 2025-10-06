package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ClosestPointsToOrigin {

	/**
	 * Finds the K closest points to the origin (0,0) using a Max-Heap.
	 *
	 * @param points An array of points, where each point is [x, y].
	 * @param k      The number of closest points to return.
	 * @return A 2D array containing the k closest points.
	 */
	public int[][] kClosest(int[][] points, int k) {
		// Handle edge cases:
		if (points == null || points.length == 0 || k <= 0) {
			return new int[0][0];
		}
		if (k >= points.length) {
			return points; // All points are k closest if k is greater than or equal to total points
		}

		// Use a Max-Heap (PriorityQueue in Java is a Min-Heap by default,
		// so we use a custom comparator for a Max-Heap).
		// The comparator should order elements such that the "largest" (farthest)
		// point in terms of distance is at the top (root) of the heap.
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> {
			// Calculate squared Euclidean distance to avoid Math.sqrt()
			// and potential floating point issues.
			// distance^2 = x^2 + y^2
			long dist1 = (long) p1[0] * p1[0] + (long) p1[1] * p1[1];
			long dist2 = (long) p2[0] * p2[0] + (long) p2[1] * p2[1];
			return Long.compare(dist2, dist1); // For max-heap, compare b,a (or a-b for min-heap)
		});

		// Iterate through all points
		for (int[] point : points) {
			maxHeap.offer(point); // Add the current point to the heap

			// If the heap size exceeds k, remove the farthest point (root of max-heap)
			if (maxHeap.size() > k) {
				maxHeap.poll();
			}
		}

		// The heap now contains the k closest points.
		// Convert the heap contents to a 2D array.
		int[][] result = new int[k][2];
		for (int i = 0; i < k; i++) {
			result[i] = maxHeap.poll();
		}

		return result;
	}

	// --- Example Usage ---
	public static void main(String[] args) {
		ClosestPointsToOrigin sol = new ClosestPointsToOrigin();

		// Test Case 1
		int[][] points1 = { { 1, 3 }, { -2, 2 } };
		int k1 = 1;
		int[][] result1 = sol.kClosest(points1, k1);
		System.out.println("Points: " + Arrays.deepToString(points1) + ", k: " + k1);
		System.out.println("Result: " + Arrays.deepToString(result1)); // Expected: [[-2, 2]] (distance sqrt(8) vs
																		// sqrt(10))

		// Test Case 2
		int[][] points2 = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
		int k2 = 2;
		int[][] result2 = sol.kClosest(points2, k2);
		System.out.println("\nPoints: " + Arrays.deepToString(points2) + ", k: " + k2);
		System.out.println("Result: " + Arrays.deepToString(result2)); // Expected: [[3, 3], [-2, 4]] (distances
																		// sqrt(18), sqrt(26), sqrt(20))

		// Test Case 3: k equals points.length
		int[][] points3 = { { 1, 1 }, { 2, 2 } };
		int k3 = 2;
		int[][] result3 = sol.kClosest(points3, k3);
		System.out.println("\nPoints: " + Arrays.deepToString(points3) + ", k: " + k3);
		System.out.println("Result: " + Arrays.deepToString(result3)); // Expected: [[1,1], [2,2]] (order might vary
																		// based on heap implementation, but content is
																		// same)

		// Test Case 4: Empty points array
		int[][] points4 = {};
		int k4 = 1;
		int[][] result4 = sol.kClosest(points4, k4);
		System.out.println("\nPoints: " + Arrays.deepToString(points4) + ", k: " + k4);
		System.out.println("Result: " + Arrays.deepToString(result4)); // Expected: [[]]
	}
}
