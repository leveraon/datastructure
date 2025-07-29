/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class LeftRotation {
	/*
	 * Complete the 'rotLeft' function below.
	 *
	 * The function is expected to return a List<Integer>. The function accepts
	 * following parameters: 1. List<Integer> a 2. int d
	 */
	public static List<Integer> rotLeft(List<Integer> a, int d) {
		int n = a.size();

		// Handle edge cases: empty list or no rotations needed
		if (n == 0 || d == 0) {
			return a;
		}

		// Calculate the effective number of rotations
		// If d is a multiple of n, the array returns to its original state.
		int effectiveRotations = d % n;

		// If effectiveRotations is 0, no change is needed
		if (effectiveRotations == 0) {
			return a;
		}

		// Create a new ArrayList to store the rotated elements
		List<Integer> rotatedList = new ArrayList<>(n);

		// 1. Add elements from 'effectiveRotations' to 'n-1' (these move to the front)
		for (int i = effectiveRotations; i < n; i++) {
			rotatedList.add(a.get(i));
		}

		// 2. Add elements from '0' to 'effectiveRotations-1' (these move to the back)
		for (int i = 0; i < effectiveRotations; i++) {
			rotatedList.add(a.get(i));
		}

		return rotatedList;
	}
	
	/*
    // Alternative concise solution using List.subList() and addAll() (Java 8+)
    // This achieves the same O(N) time and O(N) space complexity.
    public static List<Integer> rotLeftConcise(List<Integer> a, int d) {
        int n = a.size();
        if (n == 0 || d == 0) {
            return a;
        }

        int effectiveRotations = d % n;
        if (effectiveRotations == 0) {
            return a;
        }

        List<Integer> rotatedList = new ArrayList<>(n);

        // Elements from effectiveRotations to n-1
        rotatedList.addAll(a.subList(effectiveRotations, n));

        // Elements from 0 to effectiveRotations-1
        rotatedList.addAll(a.subList(0, effectiveRotations));

        return rotatedList;
    }
    */
	
	public static void main(String[] args) {
        List<Integer> originalList1 = List.of(1, 2, 3, 4, 5);
        int d1 = 2;
        List<Integer> rotatedList1 = LeftRotation.rotLeft(originalList1, d1);
        System.out.println("Original: " + originalList1 + ", Rotations: " + d1 + " -> Result: " + rotatedList1); // Expected: [3, 4, 5, 1, 2]

        List<Integer> originalList2 = List.of(10, 20, 30, 40);
        int d2 = 4;
        List<Integer> rotatedList2 = LeftRotation.rotLeft(originalList2, d2);
        System.out.println("Original: " + originalList2 + ", Rotations: " + d2 + " -> Result: " + rotatedList2); // Expected: [10, 20, 30, 40] (d=n, full cycle)

        List<Integer> originalList3 = List.of(1, 2);
        int d3 = 3;
        List<Integer> rotatedList3 = LeftRotation.rotLeft(originalList3, d3);
        System.out.println("Original: " + originalList3 + ", Rotations: " + d3 + " -> Result: " + rotatedList3); // Expected: [2, 1] (effective_d = 1)

        List<Integer> originalList4 = new ArrayList<>();
        int d4 = 5;
        List<Integer> rotatedList4 = LeftRotation.rotLeft(originalList4, d4);
        System.out.println("Original: " + originalList4 + ", Rotations: " + d4 + " -> Result: " + rotatedList4); // Expected: [] (empty list)
    }
}
