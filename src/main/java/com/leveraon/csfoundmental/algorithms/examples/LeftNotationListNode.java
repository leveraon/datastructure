package com.leveraon.csfoundmental.algorithms.examples;

import com.leveraon.csfoundmental.algorithms.utils.RandomIntArrayGenerator;

class ListNode {
	int val;
	ListNode next;

	// Constructors
	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

public class LeftNotationListNode {
	public ListNode rotateLeft(ListNode head, int k) {
		// 1. Handle Edge Cases
		if (head == null || head.next == null || k == 0) {
			return head;
		}

		// 2. Find the length of the list and its last node (oldTail)
		int length = 1;
		ListNode current = head;
		while (current.next != null) {
			current = current.next;
			length++;
		}
		ListNode oldTail = current; // 'current' is now the last node

		// 3. Normalize k: k rotations are equivalent to k % length rotations
		k = k % length;

		// If k is 0 after modulo, no actual rotation is needed
		if (k == 0) {
			return head;
		}

		// 4. Connect the oldTail to the head, making the list circular
		oldTail.next = head;

		// 5. Find the new tail:
		// The new tail will be the node that was originally at (k)-th position from the
		// head.
		// We need to traverse (k-1) steps from the original head to reach it.
		// After k rotations, the node at index k (0-indexed) or k+1 (1-indexed) becomes
		// the new head.
		// The node *before* that will be the new tail.
		// So, we need to find the node that's 'k' steps away from the original head.
		ListNode newTail = head;
		for (int i = 0; i < k - 1; i++) {
			newTail = newTail.next;
		}
		// At this point, newTail is the k-th node from the original head.

		// 6. The new head is the node right after the new tail.
		ListNode newHead = newTail.next;

		// 7. Break the cycle: The new tail should now point to null.
		newTail.next = null;

		// 8. Return the new head.
		return newHead;
	}

	// Method to create a linked list from an array
	public ListNode createList(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		ListNode head = new ListNode(arr[0]);
		ListNode current = head;
		for (int i = 1; i < arr.length; i++) {
			current.next = new ListNode(arr[i]);
			current = current.next;
		}
		return head;
	}

	// Method to print a linked list
	public void printList(ListNode head) {
		ListNode current = head;
		while (current != null) {
			System.out.print(current.val + (current.next != null ? " -> " : ""));
			current = current.next;
		}
		System.out.println();
	}

	// Main method for demonstration
	public static void main(String[] args) {
		LeftNotationListNode solver = new LeftNotationListNode();

		System.out.println("--- Test Case 1: Basic Rotation ---");
		int[] arr1 = RandomIntArrayGenerator.generateRandomIntArray(5, 0, 50);
		ListNode head1 = solver.createList(arr1);
		System.out.print("Original List: ");
		solver.printList(head1);
		ListNode rotatedHead1 = solver.rotateLeft(head1, 2);
		System.out.print("Rotated (k=2): ");
		solver.printList(rotatedHead1); // Expected: 3 -> 4 -> 5 -> 1 -> 2

		System.out.println("\n--- Test Case 2: k = 0 ---");
		int[] arr2 = { 10, 20, 30 };
		ListNode head2 = solver.createList(arr2);
		System.out.print("Original List: ");
		solver.printList(head2);
		ListNode rotatedHead2 = solver.rotateLeft(head2, 0);
		System.out.print("Rotated (k=0): ");
		solver.printList(rotatedHead2); // Expected: 10 -> 20 -> 30

		System.out.println("\n--- Test Case 3: k >= length (k = 7, length = 5) ---");
		int[] arr3 = RandomIntArrayGenerator.generateRandomIntArray(5, 0, 50);
		ListNode head3 = solver.createList(arr3);
		System.out.print("Original List: ");
		solver.printList(head3);
		ListNode rotatedHead3 = solver.rotateLeft(head3, 7); // 7 % 5 = 2 rotations
		System.out.print("Rotated (k=7): ");
		solver.printList(rotatedHead3); // Expected: 3 -> 4 -> 5 -> 1 -> 2

		System.out.println("\n--- Test Case 4: Single Node List ---");
		int[] arr4 = { 99 };
		ListNode head4 = solver.createList(arr4);
		System.out.print("Original List: ");
		solver.printList(head4);
		ListNode rotatedHead4 = solver.rotateLeft(head4, 5);
		System.out.print("Rotated (k=5): ");
		solver.printList(rotatedHead4); // Expected: 99

		System.out.println("\n--- Test Case 5: Empty List ---");
		ListNode head5 = null;
		System.out.print("Original List: ");
		solver.printList(head5);
		ListNode rotatedHead5 = solver.rotateLeft(head5, 3);
		System.out.print("Rotated (k=3): ");
		solver.printList(rotatedHead5); // Expected: (empty line)

		System.out.println("\n--- Test Case 6: Rotate by full length (k = length) ---");
		int[] arr6 = RandomIntArrayGenerator.generateRandomIntArray(5, 0, 50);
		ListNode head6 = solver.createList(arr6);
		System.out.print("Original List: ");
		solver.printList(head6);
		ListNode rotatedHead6 = solver.rotateLeft(head6, 3);
		System.out.print("Rotated (k=3): ");
		solver.printList(rotatedHead6); // Expected: A -> B -> C (no change)
	}

}
