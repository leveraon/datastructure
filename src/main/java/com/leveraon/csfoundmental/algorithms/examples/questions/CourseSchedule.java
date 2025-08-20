package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule {

//	Map<Integer, Map<Integer, Integer>> courseSchedule = new HashMap<>();

	// Wrong
//	int[][] courseSchedule(int[] courses, int[][] prerequesites) {
//		int[][] result = {};
//		if (prerequesites == null || prerequesites.length == 0) {
//			result[0] = courses;
//			return result;
//		}
//
//		for (int course : courses) {
//			Map<Integer, Integer> prerequesitesMap = new HashMap<>();
//			courseSchedule.put(course, prerequesitesMap);
//			for (int[] prerequesite : prerequesites) {
//
//				int cour = prerequesite[0];
//				int prer = prerequesite[1];
//
//				if (cour == course) {
//					prerequesitesMap.put(prer, cour);
//				}
//
//				// detect cycle
//				if (prerequesitesMap.containsKey(prer)) {
//					if (prerequesitesMap.get(prer) == cour) {
//						courseSchedule.remove(course);
//						break;
//					} else {
//						prerequesitesMap.put(prer, cour);
//					}
//
//				}
//
//			}
//		}
//
//		return result;
//	}

	/**
	 * Finds a valid course order to take all courses, respecting prerequisites.
	 * Uses Kahn's algorithm (BFS-based topological sort).
	 *
	 * @param numCourses    The total number of courses (0 to numCourses - 1).
	 * @param prerequisites A 2D array where prerequisites[i] = [ai, bi] means bi
	 *                      must be taken before ai.
	 * @return An array of course order, or an empty array if a cycle exists.
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		// 1. Build Graph (Adjacency List) and Calculate In-Degrees
		Map<Integer, List<Integer>> adj = new HashMap<>();
		int[] inDegree = new int[numCourses];

		for (int i = 0; i < numCourses; i++) {
			adj.put(i, new ArrayList<>()); // Initialize empty adjacency lists for all courses
		}

		for (int[] prerequisite : prerequisites) {
			int course = prerequisite[0]; // Course to take (ai)
			int preReq = prerequisite[1]; // Prerequisite course (bi)
			// Edge goes from preReq to course, because preReq must be taken before course
			adj.get(preReq).add(course);
			inDegree[course]++; // Increment in-degree of the dependent course
		}

		// 2. Initialize Queue with courses having 0 in-degree
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		// List to store the topological order
		List<Integer> topologicalOrder = new ArrayList<>();

		// 3. Process nodes using BFS (Kahn's Algorithm)
		while (!queue.isEmpty()) {
			int course = queue.poll();
			topologicalOrder.add(course); // Add course to the order

			// For each neighbor (course that depends on current course)
			for (int neighbor : adj.get(course)) {
				inDegree[neighbor]--; // Decrement in-degree
				if (inDegree[neighbor] == 0) {
					queue.offer(neighbor); // If in-degree becomes 0, add to queue
				}
			}
		}

		// 4. Check for Cycles
		if (topologicalOrder.size() == numCourses) {
			return topologicalOrder.stream().mapToInt(Integer::intValue).toArray();
		} else {
			// A cycle exists, impossible to finish all courses
			return new int[0];
		}
	}

}
