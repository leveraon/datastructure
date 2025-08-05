/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.queue;

import java.util.EmptyStackException;

import com.leveraon.csfoundmental.datastructure.queues.QueueWithTwoStacks;

/**
 * 
 */
public class TwoStacksQueueExample<T> {

	// --- Example Usage ---
	public static void main(String[] args) {
		QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();

		System.out.println("Is queue empty? " + queue.empty()); // true

		queue.push(1);
		queue.push(2);
		queue.push(3);
		System.out.println("Pushed 1, 2, 3.");

		System.out.println("Is queue empty? " + queue.empty()); // false

		System.out.println("Peek: " + queue.peek()); // Expected: 1
		System.out.println("Pop: " + queue.pop()); // Expected: 1

		System.out.println("Peek: " + queue.peek()); // Expected: 2
		queue.push(4);
		System.out.println("Pushed 4.");

		System.out.println("Pop: " + queue.pop()); // Expected: 2
		System.out.println("Pop: " + queue.pop()); // Expected: 3
		System.out.println("Peek: " + queue.peek()); // Expected: 4
		System.out.println("Pop: " + queue.pop()); // Expected: 4

		System.out.println("Is queue empty? " + queue.empty()); // true

		try {
			queue.pop(); // Trying to pop from an empty queue
		} catch (EmptyStackException e) {
			System.out.println("Caught expected exception: " + e.getMessage());
		}

		try {
			queue.peek(); // Trying to peek from an empty queue
		} catch (EmptyStackException e) {
			System.out.println("Caught expected exception: " + e.getMessage());
		}
	}

}
