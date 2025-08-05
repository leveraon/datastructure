package com.leveraon.csfoundmental.datastructure.queues;

import java.util.EmptyStackException;

import com.leveraon.csfoundmental.datastructure.stacks.LinkedStack;

public class QueueWithTwoStacks<T>  {
	// Stack to handle incoming elements (for push operations)
	private LinkedStack<T> inputStack = new LinkedStack<>();
	// Stack to handle outgoing elements (for pop and peek operations)
	private LinkedStack<T> outputStack = new LinkedStack<>();

	/**
	 * Pushes element x to the back of the queue (enqueue). Time Complexity: O(1)
	 *
	 * @param x The element to be pushed.
	 */
	public void push(T x) {
		inputStack.push(x);
	}

	/**
	 * Removes the element from the front of the queue and returns it (dequeue). If
	 * the queue is empty, throws an EmptyStackException. Time Complexity: Amortized
	 * O(1). Worst case O(N) when transfer occurs.
	 *
	 * @return The element at the front of the queue.
	 * @throws EmptyStackException If the queue is empty.
	 */
	public T pop() {
		// Ensure outputStack is ready for popping
		transferElements();

		if (outputStack.isEmpty()) {
			// If outputStack is still empty after transfer, then inputStack must also be
			// empty
			// meaning the entire queue is empty.
			throw new EmptyStackException();
		}
		return outputStack.pop();
	}

	/**
	 * Returns the element at the front of the queue without removing it (peek). If
	 * the queue is empty, throws an EmptyStackException. Time Complexity: Amortized
	 * O(1). Worst case O(N) when transfer occurs.
	 *
	 * @return The element at the front of the queue.
	 * @throws EmptyStackException If the queue is empty.
	 */
	public T peek() {
		// Ensure outputStack is ready for peeking
		transferElements();

		if (outputStack.isEmpty()) {
			// If outputStack is still empty after transfer, then inputStack must also be
			// empty
			// meaning the entire queue is empty.
			throw new EmptyStackException();
		}
		return outputStack.top();
	}

	/**
	 * Returns whether the queue is empty. Time Complexity: O(1)
	 *
	 * @return true if the queue is empty, false otherwise.
	 */
	public boolean empty() {
		return inputStack.isEmpty() && outputStack.isEmpty();
	}

	/**
	 * Helper method to transfer elements from inputStack to outputStack. This is
	 * done only when outputStack is empty, to maintain FIFO order.
	 */
	private void transferElements() {
		// Only transfer if outputStack is empty.
		// If outputStack already has elements, they are in the correct FIFO order.
		if (outputStack.isEmpty()) {
			while (!inputStack.isEmpty()) {
				outputStack.push(inputStack.pop());
			}
		}
	}
}
