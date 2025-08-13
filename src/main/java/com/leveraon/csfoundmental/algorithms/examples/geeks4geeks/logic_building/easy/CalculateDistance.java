package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

/**
 * You are given two coordinates (x1, y1) and (x2, y2) of a two-dimensional
 * graph. Find the distance between them.<br>
 * Input : x1, y1 = (3, 4 x2, y2 = (7, 7))<br>
 * Output : 5<br>
 * 
 * Input : x1, y1 = (3, 4) x2, y2 = (4, 3)<br>
 * Output : 1.41421)<br>
 */
public class CalculateDistance {

	static double calculateDistance(int[] c1, int[] c2) {
		double distance = 0;

		distance = Math.sqrt(Math.pow(c2[0] - c1[0], 2) + Math.pow(c2[1] - c1[1], 2));

		return distance;
	}

	public static void main(String[] args) {
		int[] c1 = new int[] { 1, 3 };
		int[] c2 = new int[] { 122, 3333 };

		System.out.println("Calculate Distance: " + calculateDistance(c1, c2));
	}
}
