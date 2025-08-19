package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.Arrays;

public class ClosestPointsToOrigin {

	static int[] closestPoint(int[][] pointArray, int k) {
		int[] result = {};
		if (pointArray == null || pointArray.length == 0 || k > pointArray.length) {
			return result;
		}

		for (int i = 1; i < pointArray.length; i++) {
			int[] currentPoint = pointArray[i];
			double currentDistance = distance(currentPoint);

			int j = i;
			while (j > 0 && distance(pointArray[j - 1]) > currentDistance) {
				pointArray[j] = pointArray[j - 1];
				j--;
			}
			pointArray[j] = currentPoint;
		}

		return pointArray[k - 1];
	}

	private static double distance(int[] point) {
		return Math.pow(point[0], 2) + Math.pow(point[1], 2);
	}

	public static void main(String[] args) {
		int[][] points = { { 1, 2 }, { 2, 3 }, { 2, 1 }, { 4, 5 }, { -1, -3 }, { -2, -1 } };

		int k = 4;
		System.out.println("'" + k + "'th closest point of array is " + Arrays.toString(closestPoint(points, k)));
	}
}
