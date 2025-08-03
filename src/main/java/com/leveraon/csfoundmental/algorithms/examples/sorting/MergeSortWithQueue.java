package com.leveraon.csfoundmental.algorithms.examples.sorting;

import java.util.Comparator;

import com.leveraon.csfoundmental.datastructure.queues.LinkedQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;

/**
 * Merge Sort is a highly efficient, comparison-based sorting algorithm. Its
 * complexity is very consistent across different input scenarios.
 * 
 * Here's a breakdown:
 * 
 * ### Time Complexity
 * 
 * Merge Sort always divides the list into two halves, recursively sorts them,
 * and then merges them. This leads to a very predictable performance:
 * 
 * **Best-Case Time Complexity:** **O(n log n)** Even if the array is already
 * sorted, Merge Sort will still go through the entire process of dividing and
 * merging, resulting in O(n log n) operations. **Average-Case Time
 * Complexity:** **O(n log n)** On average, the performance remains consistent.
 * **Worst-Case Time Complexity:** **O(n log n)** Unlike Quick Sort, Merge
 * Sort's performance does not degrade to O(n^2) for specific input patterns
 * (like already sorted or reverse-sorted arrays). This makes it very reliable.
 ** 
 * How O(n log n) is derived:**
 * 
 * 1. **Divide:** Splitting the array into two halves takes constant time, O(1).
 * 2. **Conquer (Recursion):** You recursively sort two subproblems, each of
 * size `n/2`. This contributes `2 * T(n/2)`. 3. **Combine (Merge):** Merging
 * the two sorted halves takes linear time, O(n), because you have to examine
 * every element once to place it correctly into the merged array.
 * 
 * This leads to the recurrence relation: `T(n) = 2T(n/2) + O(n)` Solving this
 * recurrence relation (e.g., using the Master Theorem or expansion) yields
 * **O(n log n)**.
 * 
 * </br>
 * ### Space Complexity
 * 
 * **Space Complexity:** **O(n)** (Auxiliary Space) Merge Sort requires an
 * auxiliary (temporary) array of size `n` to perform the merging step. This is
 * because merging two sorted lists generally requires a separate space to store
 * the merged result before copying it back (or directly into) the original
 * array. The recursion stack depth is O(log n), but the dominant factor for
 * space complexity is the O(n) auxiliary array.
 * 
 * 
 * @author leveraon
 *
 *         Created on: Aug 3, 2025
 */

public class MergeSortWithQueue {

	Queue<Integer> merge(Queue<Integer> q1, Queue<Integer> q2, Queue<Integer> queue, Comparator<Integer> comp) {

		while (!q1.isEmpty() && !q2.isEmpty()) {
			if (comp.compare(q1.first(), q2.first()) < 0) {
				queue.enqueue(q1.dequeue());
			} else {
				queue.enqueue(q2.dequeue());
			}
		}

		while (!q1.isEmpty()) {
			queue.enqueue(q1.dequeue());
		}
		while (!q2.isEmpty()) {
			queue.enqueue(q2.dequeue());
		}

		return queue;
	}

	void mergeSort(Queue<Integer> queue, Comparator<Integer> comp) {

		int size = queue.size();
		if (size < 2)
			return;

		Queue<Integer> firstHalf = new LinkedQueue<>();
		Queue<Integer> secondHalf = new LinkedQueue<>();

		while (firstHalf.size() < size / 2) {
			firstHalf.enqueue(queue.dequeue());
		}

		while (!queue.isEmpty()) {
			secondHalf.enqueue(queue.dequeue());
		}

		mergeSort(firstHalf, comp);
		mergeSort(secondHalf, comp);
		merge(firstHalf, secondHalf, queue, comp);

	}
}
