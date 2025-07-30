/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

import com.leveraon.csfoundmental.datastructure.tree.Node;

/**
 * 
 */
public class InsertNodeInList {
	/**
	 * Inserts a new node with given data at a specific position in a linked list.
	 *
	 * @param head         The head of the linked list.
	 * @param dataToInsert The data value for the new node.
	 * @param position     The 0-indexed position where the new node should be
	 *                     inserted.
	 * @return The head of the modified linked list.
	 */
	public Node<Integer> insertNode(Node<Integer> head, int dataToInsert, int position) {
		// 1. Create the new node
		Node<Integer> newNode = new Node<>();
		newNode.setElement(dataToInsert);

		// 2. Handle Insertion at Head (position 0)
		if (position == 0) {
			newNode.setRight(head);// New node points to the old head
			return newNode; // New node becomes the new head
		}

		// 3. Handle Insertion in Middle or at End
		Node<Integer> current = head;
		int count = 0;

		// Traverse to the node *before* the desired insertion point.
		// For position 'k', we want to stop at node 'k-1'.
		while (current != null && count < position - 1) {
			current = current.getRight();
			count++;
		}

		// 4. Check for Invalid Position
		// If 'current' is null, it means:
		// a) The original list was empty and 'position' > 0.
		// b) 'position' was greater than the length of the list.
		if (current == null) {
			// Cannot insert at the specified position.
			System.err.println("Error: Position " + position + " is out of bounds for insertion.");
			return head; // Return the original list unchanged
			// Optionally, you could throw an IllegalArgumentException here:
			// throw new IllegalArgumentException("Position " + position + " is out of
			// bounds.");
		}

		// 5. Perform Insertion
		// 'current' is now the node at (position - 1).
		// Link the new node to the rest of the list
		newNode.setRight(current.getRight());
		// Link the node at (position - 1) to the new node
		current.setRight(newNode);

		return head; // The head of the list remains the same (unless position was 0)
	}

	// Utility method to print the linked list
	public static void printList(Node<Integer> head) {
		Node<Integer> current = head;
		while (current != null) {
			System.out.print(current.getElement() + " -> ");
			current = current.getRight();
		}
		System.out.println("null");
	}

	public static void main(String[] args) {
		InsertNodeInList sol = new InsertNodeInList();
		Node<Integer> head = null; // Start with an empty list

		System.out.println("--- Test Case 1: Insert into an empty list at position 0 ---");
		head = sol.insertNode(head, 10, 0); // List: 10 -> null
		printList(head); // Expected: 10 -> null

		System.out.println("\n--- Test Case 2: Insert at the beginning (position 0) ---");
		head = sol.insertNode(head, 5, 0); // List: 5 -> 10 -> null
		printList(head); // Expected: 5 -> 10 -> null

		System.out.println("\n--- Test Case 3: Insert at the end ---");
		// Current list: 5 -> 10 -> null
		head = sol.insertNode(head, 20, 2); // Insert 20 at position 2
		printList(head); // Expected: 5 -> 10 -> 20 -> null

		System.out.println("\n--- Test Case 4: Insert in the middle ---");
		// Current list: 5 -> 10 -> 20 -> null
		head = sol.insertNode(head, 15, 2); // Insert 15 at position 2
		printList(head); // Expected: 5 -> 10 -> 15 -> 20 -> null

		System.out.println("\n--- Test Case 5: Insert at a position exactly equal to list length (append) ---");
		// Current list: 5 -> 10 -> 15 -> 20 -> null (length 4)
		head = sol.insertNode(head, 25, 4); // Insert 25 at position 4
		printList(head); // Expected: 5 -> 10 -> 15 -> 20 -> 25 -> null

		System.out.println("\n--- Test Case 6: Invalid position (too large) ---");
		// Current list: 5 -> 10 -> 15 -> 20 -> 25 -> null (length 5)
		head = sol.insertNode(head, 99, 10); // Try to insert at position 10
		printList(head); // Expected: Error message, then 5 -> 10 -> 15 -> 20 -> 25 -> null (list
							// unchanged)

		System.out.println("\n--- Test Case 7: Invalid position (into empty list at non-zero pos) ---");
		Node<Integer> emptyList = null;
		emptyList = sol.insertNode(emptyList, 100, 5); // Try to insert into empty list at pos 5
		printList(emptyList); // Expected: Error message, then null (list unchanged)
	}
}
