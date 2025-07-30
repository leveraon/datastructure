package com.leveraon.csfoundmental.algorithms.examples.tree;

import java.util.ArrayList;
import java.util.List;

import com.leveraon.csfoundmental.datastructure.stacks.ArrayStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;
import com.leveraon.csfoundmental.datastructure.tree.Node;

public class PostorderTraversal {
	/**
	 * Recursive Post-order Traversal Time Complexity: O(N) - Each node is visited
	 * once. Space Complexity: O(H) - Where H is the height of the tree. In worst
	 * case (skewed tree), H = N, so O(N). In best case (balanced tree), H = logN,
	 * so O(logN).
	 */
	public List<Integer> postorderTraversalRecursive(Node<Integer> root) {
		List<Integer> result = new ArrayList<>();
		postorderRecursiveHelper(root, result);
		return result;
	}

	private void postorderRecursiveHelper(Node<Integer> node, List<Integer> result) {
		if (node == null) {
			return;
		}

		// 1. Traverse Left Subtree
		postorderRecursiveHelper(node.getLeft(), result);

		// 2. Traverse Right Subtree
		postorderRecursiveHelper(node.getRight(), result);

		// 3. Visit Root Node
		result.add(node.getElement());
	}

	/**
	 * Iterative Post-order Traversal using One Stack This is more complex as it
	 * requires checking if right child has been visited. Time Complexity: O(N) -
	 * Each node is visited a constant number of times. Space Complexity: O(H) -
	 * Where H is the height of the tree. In worst case (skewed tree), H = N, so
	 * O(N). In best case (balanced tree), H = logN, so O(logN).
	 */
	public List<Integer> postorderTraversalIterative(Node<Integer> root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Stack<Node<Integer>> stack = new ArrayStack<>();
		Node<Integer> current = root;
		Node<Integer> lastVisitedNode = null; // Tracks the last node added to the result list

		while (current != null || !stack.isEmpty()) {
			// Traverse left as far as possible, pushing nodes onto the stack
			if (current != null) {
				stack.push(current);
				current = current.getLeft();
			} else {
				// We've gone as far left as possible. Now, peek at the top of the stack.
				Node<Integer> peekNode = stack.top();

				// If the peekNode has a right child AND that right child hasn't been visited
				// yet:
				// Move to the right child to process its subtree first.
				if (peekNode.getRight() != null && peekNode.getRight() != lastVisitedNode) {
					current = peekNode.getRight(); // Set current to the right child
				} else {
					// Otherwise (if no right child, or right child was already visited),
					// it means both left and right subtrees of peekNode are processed.
					// So, we can visit the peekNode itself.
					result.add(peekNode.getElement());
					lastVisitedNode = stack.pop(); // Pop and update lastVisitedNode
					current = null; // Set current to null to force picking from stack in next iteration
									// (important so we don't try to go left again from the popped node's parent)
				}
			}
		}

		return result;
	}

	// --- Main method for testing ---
	public static void main(String[] args) {
		// Construct a sample tree:
		// 1
		// / \
		// 2 3
		// / \
		// 4 5
		Node<Integer> root = new Node<>(1, null, null);
		root.setLeft(new Node<>(2, null, null));
		root.setRight(new Node<>(3, null, null));
		root.getLeft().setLeft(new Node<>(4, null, null));
		root.getLeft().setRight(new Node<>(5, null, null));

		PostorderTraversal solver = new PostorderTraversal();

		// Test Recursive Approach
		List<Integer> recursiveResult = solver.postorderTraversalRecursive(root);
		System.out.println("Postorder Traversal (Recursive): " + recursiveResult); // Expected: [1, 2, 4, 5, 3]

		// Test with an empty tree
		List<Integer> emptyRecursiveResult = solver.postorderTraversalRecursive(null);
		System.out.println("Postorder Traversal (Empty Tree - Recursive): " + emptyRecursiveResult); // Expected: []

		System.out.println("\n--- Iterative Traversal ---");
		List<Integer> iterativeResult = solver.postorderTraversalIterative(root);
		System.out.println("Result: " + iterativeResult); // Expected: [1, 2, 4, 5, 3]

		List<Integer> emptyIterativeResult = solver.postorderTraversalIterative(null);
		System.out.println("Empty Tree Result: " + emptyIterativeResult); // Expected: []
	}

}
