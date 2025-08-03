package com.leveraon.csfoundmental.algorithms.examples.sorting;

import java.util.Comparator;

import com.leveraon.csfoundmental.datastructure.queues.LinkedQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;

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
