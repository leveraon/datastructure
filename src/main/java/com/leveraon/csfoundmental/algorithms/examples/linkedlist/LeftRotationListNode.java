package com.leveraon.csfoundmental.algorithms.examples.linkedlist;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;
import com.leveraon.csfoundmental.datastructure.tree.Node;

public class LeftRotationListNode {
	public Node<Integer> rotateLeft(Node<Integer> head, int k) {
		// 1. Handle Edge Cases
		if (head == null || head.getRight() == null || k == 0) {
			return head;
		}

		// 2. Find the length of the list and its last node (oldTail)
		int length = 1;
		Node<Integer> current = head;
		while (current.getRight() != null) {
			current = current.getRight();
			length++;
		}
		Node<Integer> oldTail = current; // 'current' is now the last node

		// 3. Normalize k: k rotations are equivalent to k % length rotations
		k = k % length;

		// If k is 0 after modulo, no actual rotation is needed
		if (k == 0) {
			return head;
		}

		// 4. Connect the oldTail to the head, making the list circular
		oldTail.setRight(head);

		// 5. Find the new tail:
		// The new tail will be the node that was originally at (k)-th position
		// from the
		// head.
		// We need to traverse (k-1) steps from the original head to reach it.
		// After k rotations, the node at index k (0-indexed) or k+1 (1-indexed)
		// becomes
		// the new head.
		// The node *before* that will be the new tail.
		// So, we need to find the node that's 'k' steps away from the original
		// head.
		Node<Integer> newTail = head;
		for (int i = 0; i < k - 1; i++) {
			newTail = newTail.getRight();
		}
		// At this point, newTail is the k-th node from the original head.

		// 6. The new head is the node right after the new tail.
		Node<Integer> newHead = newTail.getRight();

		// 7. Break the cycle: The new tail should now point to null.
		newTail.setRight(null);

		// 8. Return the new head.
		return newHead;
	}

	// Method to create a linked list from an array
	public Node<Integer> createList(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		Node<Integer> head = new Node<Integer>(arr[0], null, null);
		Node<Integer> current = head;
		for (int i = 1; i < arr.length; i++) {
			current.setRight(new Node<Integer>(arr[i], null, null));
			current = current.getRight();
		}
		return head;
	}

	// Method to print a linked list
	public void printList(Node<Integer> head) {
		Node<Integer> current = head;
		while (current != null) {
			System.out.print(current.getElement() + (current.getRight() != null ? " -> " : ""));
			current = current.getRight();
		}
		System.out.println();
	}

	// Main method for demonstration
	public static void main(String[] args) {
		LeftRotationListNode solver = new LeftRotationListNode();

		System.out.println("--- Test Case 1: Basic Rotation ---");
		int[] arr1 = RandomIntArrayGenerator.generateRandomIntArray(5, 0, 50);
		Node<Integer> head1 = solver.createList(arr1);
		System.out.print("Original List: ");
		solver.printList(head1);
		Node<Integer> rotatedHead1 = solver.rotateLeft(head1, 2);
		System.out.print("Rotated (k=2): ");
		solver.printList(rotatedHead1); // Expected: 3 -> 4 -> 5 -> 1 -> 2

		System.out.println("\n--- Test Case 2: k = 0 ---");
		int[] arr2 = { 10, 20, 30 };
		Node<Integer> head2 = solver.createList(arr2);
		System.out.print("Original List: ");
		solver.printList(head2);
		Node<Integer> rotatedHead2 = solver.rotateLeft(head2, 0);
		System.out.print("Rotated (k=0): ");
		solver.printList(rotatedHead2); // Expected: 10 -> 20 -> 30

		System.out.println("\n--- Test Case 3: k >= length (k = 7, length = 5) ---");
		int[] arr3 = RandomIntArrayGenerator.generateRandomIntArray(5, 0, 50);
		Node<Integer> head3 = solver.createList(arr3);
		System.out.print("Original List: ");
		solver.printList(head3);
		Node<Integer> rotatedHead3 = solver.rotateLeft(head3, 7); // 7 % 5 = 2
																	// rotations
		System.out.print("Rotated (k=7): ");
		solver.printList(rotatedHead3); // Expected: 3 -> 4 -> 5 -> 1 -> 2

		System.out.println("\n--- Test Case 4: Single Node<Integer> List ---");
		int[] arr4 = { 99 };
		Node<Integer> head4 = solver.createList(arr4);
		System.out.print("Original List: ");
		solver.printList(head4);
		Node<Integer> rotatedHead4 = solver.rotateLeft(head4, 5);
		System.out.print("Rotated (k=5): ");
		solver.printList(rotatedHead4); // Expected: 99

		System.out.println("\n--- Test Case 5: Empty List ---");
		Node<Integer> head5 = null;
		System.out.print("Original List: ");
		solver.printList(head5);
		Node<Integer> rotatedHead5 = solver.rotateLeft(head5, 3);
		System.out.print("Rotated (k=3): ");
		solver.printList(rotatedHead5); // Expected: (empty line)

		System.out.println("\n--- Test Case 6: Rotate by full length (k = length) ---");
		int[] arr6 = RandomIntArrayGenerator.generateRandomIntArray(5, 0, 50);
		Node<Integer> head6 = solver.createList(arr6);
		System.out.print("Original List: ");
		solver.printList(head6);
		Node<Integer> rotatedHead6 = solver.rotateLeft(head6, 3);
		System.out.print("Rotated (k=3): ");
		solver.printList(rotatedHead6); // Expected: A -> B -> C (no change)
	}

}
