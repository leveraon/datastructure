package com.leveraon.csfoundmental.algorithms.examples.linkedlist;

import com.leveraon.csfoundmental.datastructure.tree.Node;

public class LinkedListMiddle {
	// Method to find the middle of a linked list
	public Node<Integer> findMiddle(Node<Integer> head) {
		// Handle edge cases: empty list or single-Node<Integer> list
		if (head == null) {
			return null;
		}
		if (head.getNext() == null) { // Only one node
			return head;
		}

		Node<Integer> slow = head;
		Node<Integer> fast = head;

		// Loop until fast reaches the end of the list
		// The condition `fast != null` ensures fast itself is not null
		// The condition `fast.next != null` ensures fast can move two steps
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext(); // Move slow by one step
			fast = fast.getNext().getNext(); // Move fast by two steps
		}

		// When the loop terminates, slow will be at the middle node
		return slow;
	}

	// --- Helper method for testing ---
	public void printList(Node<Integer> head) {
		Node<Integer> current = head;
		while (current != null) {
			System.out.print(current.getElement() + " -> ");
			current = current.getNext();
		}
		System.out.println("null");
	}

	public static void main(String[] args) {
		LinkedListMiddle solver = new LinkedListMiddle();

		// Test Case 1: Odd number of nodes
		Node<Integer> head1 = new Node<Integer>(1, null, null);
		head1.setNext(new Node<Integer>(2, null, null));
		head1.getNext().setNext(new Node<Integer>(3, null, null));
		head1.getNext().getNext().setNext(new Node<Integer>(4, null, null));
		head1.getNext().getNext().getNext().setNext(new Node<Integer>(5, null, null));
		System.out.print("List 1: ");
		solver.printList(head1);
		Node<Integer> middle1 = solver.findMiddle(head1);
		System.out.println("Middle of List 1: " + (middle1 != null ? middle1.getElement() : "null")); // Expected: 3

		System.out.println("--------------------");

		// Test Case 2: Even number of nodes
		Node<Integer> head2 = new Node<Integer>(10, null, null);
		head2.setNext(new Node<Integer>(20, null, null));
		head2.getNext().setNext(new Node<Integer>(30, null, null));
		head2.getNext().getNext().setNext(new Node<Integer>(40, null, null));
		System.out.print("List 2: ");
		solver.printList(head2);
		Node<Integer> middle2 = solver.findMiddle(head2);
		System.out.println("Middle of List 2: " + (middle2 != null ? middle2.getElement() : "null")); // Expected: 30
																										// (second
		// middle)

		System.out.println("--------------------");

		// Test Case 3: Single node
		Node<Integer> head3 = new Node<Integer>(100, null, null);
		System.out.print("List 3: ");
		solver.printList(head3);
		Node<Integer> middle3 = solver.findMiddle(head3);
		System.out.println("Middle of List 3: " + (middle3 != null ? middle3.getElement() : "null")); // Expected: 100

		System.out.println("--------------------");

		// Test Case 4: Two nodes
		Node<Integer> head4 = new Node<Integer>(1, null, null);
		head4.setNext(new Node<Integer>(2, null, null));
		System.out.print("List 4: ");
		solver.printList(head4);
		Node<Integer> middle4 = solver.findMiddle(head4);
		System.out.println("Middle of List 4: " + (middle4 != null ? middle4.getElement() : "null")); // Expected: 2
																										// (second
		// middle)

		System.out.println("--------------------");

		// Test Case 5: Empty list
		Node<Integer> head5 = null;
		System.out.print("List 5: ");
		solver.printList(head5);
		Node<Integer> middle5 = solver.findMiddle(head5);
		System.out.println("Middle of List 5: " + (middle5 != null ? middle5.getElement() : "null")); // Expected: null
	}
}
