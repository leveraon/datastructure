/**
 * LEVERAON INC
 *
 * 2015 - 2025 ALL RIGHTS RESERVED
 */
package com.leveraon.csfoundmental.algorithms.examples.sorting;

import java.util.Arrays;
import java.util.Comparator;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;
import com.leveraon.csfoundmental.datastructure.queues.ArrayQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;

/**
 * @author leveraon
 *
 *         Created on: Aug 4, 2025
 */
public class QuickSortWithQueue {

	public static void quickSort(Queue<Integer> queue, Comparator<Integer> comp) {
		int size = queue.size();
		if (size < 2) {
			return;
		}

		int pivot = queue.first();

		Queue<Integer> L = new ArrayQueue<>();
		Queue<Integer> E = new ArrayQueue<>();
		Queue<Integer> G = new ArrayQueue<>();

		while (!queue.isEmpty()) {
			int current = queue.dequeue();
			int result = comp.compare(current, pivot);
			if (result > 0)
				G.enqueue(current);
			else if (result == 0)
				E.enqueue(current);
			else
				L.enqueue(current);
		}

		quickSort(L, comp);
		quickSort(G, comp);

		while (!L.isEmpty()) {
			queue.enqueue(L.dequeue());
		}
		while (!E.isEmpty()) {
			queue.enqueue(E.dequeue());
		}
		while (!G.isEmpty()) {
			queue.enqueue(G.dequeue());
		}

	}

	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayQueue<>();
		int[] array = RandomIntArrayGenerator.generateRandomIntArray(10, 0, 50);
		for (int i : array) {
			queue.enqueue(i);
		}

		System.out.println("Array before -> " + Arrays.toString(array));
		Comparator<Integer> comp = new Comparator<>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int result = o1 - o2;
				if (result > 0)
					return 1;
				if (result == 0)
					return 0;
				else
					return -1;

			}
		};
		quickSort(queue, comp);

		int[] newArray = new int[array.length];
		int i = 0;
		while (!queue.isEmpty()) {
			newArray[i] = queue.dequeue();
			i++;
		}
		System.out.println("Array after -> " + Arrays.toString(newArray));
	}
}
