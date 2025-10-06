package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Interval {
	int start;
	int end;
}

public class MergeIntervals {

	List<Interval> mergeIntervals(List<Interval> intervals) {

		if (intervals == null || intervals.isEmpty())
			return Collections.emptyList();

		List<Interval> sortedIntervals = sortIntervals(intervals);

		Map<Integer, Integer> result = new HashMap<>();

		int start = 0;
		int end = 0;
		for (Interval interval : sortedIntervals) {
			int currentStart = interval.start;
			int currentEnd = interval.end;
			if (result.isEmpty()) {
				start = currentStart;
				end = currentEnd;
				result.put(currentStart, currentEnd);
			}

			if (currentStart >= start && currentStart <= end) {
				end = Math.max(currentEnd, end);
				result.put(currentStart, end);
			} else {
				start = currentStart;
				end = currentEnd;
				result.put(currentStart, currentEnd);
			}

		}
		List<Interval> finalResult = new ArrayList<>();
		result.forEach((key, value) -> {
			finalResult.add(Interval.builder().start(key).end(value).build());
		});

		return finalResult;
	}

	List<Interval> sortIntervals(List<Interval> intervals) {
		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);

			int j = i;
			while (j > 0 && intervals.get(j - 1).start > current.start) {
				intervals.set(j, intervals.get(j - 1));
				j--;
			}
			intervals.set(j, current);
		}

		return intervals;
	}

	/**
	 * Merges a collection of overlapping intervals.
	 *
	 * @param intervals A 2D array where each inner array represents an interval
	 *                  [start, end].
	 * @return A 2D array representing the merged, non-overlapping intervals.
	 */
	public int[][] merge(int[][] intervals) {
		// Handle edge cases: null or empty input
		if (intervals == null || intervals.length == 0) {
			return new int[0][]; // Return an empty 2D array
		}

		// 1. Sort the intervals based on their start times.
		// If start times are equal, the order doesn't strictly matter for correctness
		// but can be stable. Integer.compare is safer than a-b for very large numbers
		// to prevent potential overflow, although for typical interval problems it's
		// fine.
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

		// Using an ArrayList to dynamically store merged intervals
		List<int[]> merged = new ArrayList<>();

		// 2. Iterate through the sorted intervals and merge
		for (int[] interval : intervals) {
			// If the merged list is empty OR
			// the current interval's start is greater than the end of the last merged
			// interval,
			// it means there's no overlap. So, add the current interval as a new merged
			// interval.
			if (merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1]) {
				merged.add(interval);
			} else {
				// Otherwise, there is an overlap.
				// Merge the current interval with the last merged interval
				// by updating the end time of the last merged interval to the maximum of
				// its current end and the current interval's end.
				merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
			}
		}

		// Convert the List<int[]> to int[][] and return it.
		return merged.toArray(new int[merged.size()][]);
	}

	// Main method for testing
	public static void main(String[] args) {
		MergeIntervals solver = new MergeIntervals();

		// Test case 1
		int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
		System.out.println("Input: " + Arrays.deepToString(intervals1));
		int[][] result1 = solver.merge(intervals1);
		System.out.println("Output: " + Arrays.deepToString(result1)); // Expected: [[1, 6], [8, 10], [15, 18]]

		// Test case 2
		int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
		System.out.println("Input: " + Arrays.deepToString(intervals2));
		int[][] result2 = solver.merge(intervals2);
		System.out.println("Output: " + Arrays.deepToString(result2)); // Expected: [[1, 5]]

		// Test case 3
		int[][] intervals3 = { { 1, 4 }, { 0, 4 } };
		System.out.println("Input: " + Arrays.deepToString(intervals3));
		int[][] result3 = solver.merge(intervals3);
		System.out.println("Output: " + Arrays.deepToString(result3)); // Expected: [[0, 4]]

		// Test case 4 (no overlap)
		int[][] intervals4 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		System.out.println("Input: " + Arrays.deepToString(intervals4));
		int[][] result4 = solver.merge(intervals4);
		System.out.println("Output: " + Arrays.deepToString(result4)); // Expected: [[1, 2], [3, 4], [5, 6]]

		// Test case 5 (all overlap)
		int[][] intervals5 = { { 1, 10 }, { 2, 3 }, { 4, 5 }, { 6, 7 } };
		System.out.println("Input: " + Arrays.deepToString(intervals5));
		int[][] result5 = solver.merge(intervals5);
		System.out.println("Output: " + Arrays.deepToString(result5)); // Expected: [[1, 10]]

		// Test case 6 (empty input)
		int[][] intervals6 = {};
		System.out.println("Input: " + Arrays.deepToString(intervals6));
		int[][] result6 = solver.merge(intervals6);
		System.out.println("Output: " + Arrays.deepToString(result6)); // Expected: []

		// Test case 7 (single interval)
		int[][] intervals7 = { { 7, 8 } };
		System.out.println("Input: " + Arrays.deepToString(intervals7));
		int[][] result7 = solver.merge(intervals7);
		System.out.println("Output: " + Arrays.deepToString(result7)); // Expected: [[7, 8]]

		// Test case 8 (unordered, then merged)
		int[][] intervals8 = { { 4, 5 }, { 1, 4 }, { 0, 1 } };
		System.out.println("Input: " + Arrays.deepToString(intervals8));
		int[][] result8 = solver.merge(intervals8);
		System.out.println("Output: " + Arrays.deepToString(result8)); // Expected: [[0, 5]]
	}
}
