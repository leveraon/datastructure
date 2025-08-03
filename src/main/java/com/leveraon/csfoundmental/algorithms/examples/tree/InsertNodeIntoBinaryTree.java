/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.tree;

import com.leveraon.csfoundmental.datastructure.queues.LinkedQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;
import com.leveraon.csfoundmental.datastructure.tree.Node;

/**
 * 
 */
public class InsertNodeIntoBinaryTree {
	/**
	 * Inserts a new node with the given value into the binary tree following a
	 * level-order (breadth-first) insertion strategy.
	 *
	 * @param root The root of the binary tree.
	 * @param val  The value to insert.
	 * @return The root of the modified binary tree.
	 */
	public Node<Integer> insertIntoNode(Node<Integer> root, int val) {
		// Edge case: If the tree is empty, the new node becomes the root.
		if (root == null) {
			return new Node<Integer>(val, null, null);
		}

		Queue<Node<Integer>> queue = new LinkedQueue<>();
		queue.enqueue(root); // Start BFS from the root

		// Perform BFS to find the first available spot
		while (!queue.isEmpty()) {
			Node<Integer> current = queue.dequeue();

			// Try to insert into the left child
			if (current.getLeft() == null) {
				current.setLeft(new Node<>(val, null, null));
				return root; // Insertion complete, return the modified root
			} else {
				// Left child exists, add it to the queue for further exploration
				queue.enqueue(current.getLeft());
			}

			// Try to insert into the right child
			if (current.getRight() == null) {
				current.setRight(new Node<>(val, null, null));
				return root; // Insertion complete, return the modified root
			} else {
				// Right child exists, add it to the queue
				queue.enqueue(current.getRight());
			}
		}

		// This line should theoretically not be reached if the tree is not full
		// and we're always guaranteed to find a spot. It's here for completeness
		// or in very specific edge cases where the problem might imply a 'full' tree.
		return root;
	}

	// --- Helper methods for demonstration (optional) ---

	// Method to build a sample tree from an array (level-order)
	public Node<Integer> buildTree(Integer[] values) {
		if (values == null || values.length == 0 || values[0] == null) {
			return null;
		}

		Node<Integer> root = new Node<Integer>(values[0], null, null);
		Queue<Node<Integer>> queue = new LinkedQueue<>();
		queue.enqueue(root);
		int i = 1;

		while (!queue.isEmpty() && i < values.length) {
			Node<Integer> current = queue.dequeue();

			// Left child
			if (values[i] != null) {
				current.setLeft(new Node<Integer>(values[i], null, null));
				;
				queue.enqueue(current.getLeft());
			}
			i++;

			// Right child
			if (i < values.length && values[i] != null) {
				current.setRight(new Node<Integer>(values[i], null, null));
				queue.enqueue(current.getRight());
			}
			i++;
		}
		return root;
	}

	// Method to print tree level by level (for verification)
	public void printTreeLevelOrder(Node<Integer> root) {
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}

		Queue<Node<Integer>> queue = new LinkedQueue<>();
		queue.enqueue(root);
		int level = 0;

		System.out.println("Tree Level-Order Traversal:");
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			System.out.print("Level " + level + ": ");
			for (int i = 0; i < levelSize; i++) {
				Node<Integer> current = queue.dequeue();
				if (current != null) {
					System.out.print(current.getElement() + " ");
					queue.enqueue(current.getLeft());
					queue.enqueue(current.getRight());
				} else {
					System.out.print("null "); // Represent null nodes for clarity
				}
			}
			System.out.println(); // New line for new level
			level++;
		}
	}

	// Main method for testing
	public static void main(String[] args) {
		InsertNodeIntoBinaryTree solution = new InsertNodeIntoBinaryTree();

		// Test Case 1: Empty tree
		Node<Integer> root1 = null;
		System.out.println("--- Test Case 1: Inserting into an empty tree ---");
		root1 = solution.insertIntoNode(root1, 10);
		solution.printTreeLevelOrder(root1); // Expected: Level 0: 10
		System.out.println("\n");

		// Test Case 2: Insert into a non-empty tree
		// Initial tree: [1, 2, 3]
		// 1
		// / \
		// 2 3
		Integer[] initialValues2 = { 1, 2, 3 };
		Node<Integer> root2 = solution.buildTree(initialValues2);
		System.out.println("--- Test Case 2: Inserting 4 into [1, 2, 3] ---");
		System.out.println("Before insertion:");
		solution.printTreeLevelOrder(root2);
		root2 = solution.insertIntoNode(root2, 4);
		System.out.println("After inserting 4:");
		solution.printTreeLevelOrder(root2);
		// Expected:
		// Before: Level 0: 1, Level 1: 2 3
		// After: Level 0: 1, Level 1: 2 3, Level 2: 4 null null null
		System.out.println("\n");

		// Test Case 3: Insert into a slightly larger tree, filling right child
		// Initial tree: [1, 2, 3, 4, 5, 6]
		// 1
		// / \
		// 2 3
		// / \ /
		// 4 5 6
		Integer[] initialValues3 = { 1, 2, 3, 4, 5, 6 };
		Node<Integer> root3 = solution.buildTree(initialValues3);
		System.out.println("--- Test Case 3: Inserting 7 into [1, 2, 3, 4, 5, 6] ---");
		System.out.println("Before insertion:");
		solution.printTreeLevelOrder(root3);
		root3 = solution.insertIntoNode(root3, 7);
		System.out.println("After inserting 7:");
		solution.printTreeLevelOrder(root3);
		// Expected:
		// Before: Level 0: 1, Level 1: 2 3, Level 2: 4 5 6 null
		// After: Level 0: 1, Level 1: 2 3, Level 2: 4 5 6 7
		System.out.println("\n");
	}

}
