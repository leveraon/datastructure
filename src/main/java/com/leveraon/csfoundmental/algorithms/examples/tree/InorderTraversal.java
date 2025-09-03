/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.tree;

import java.util.ArrayList;
import java.util.List;

import com.leveraon.csfoundmental.datastructure.stacks.ArrayStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;
import com.leveraon.csfoundmental.datastructure.tree.TreeNode;

/**
 * 
 */
public class InorderTraversal {
	/**
	 * Performs an inorder traversal of a binary tree using recursion.
	 *
	 * @param root The root of the binary tree.
	 * @return A list of integers representing the inorder traversal.
	 */
	public List<Integer> inorderTraversal(TreeNode<Integer> root) {
		List<Integer> result = new ArrayList<>();
		inorderHelper(root, result); // Call the recursive helper
		return result;
	}

	/**
	 * Recursive helper function for inorder traversal.
	 *
	 * @param node   The current node being visited.
	 * @param result The list to accumulate the traversal results.
	 */
	private void inorderHelper(TreeNode<Integer> node, List<Integer> result) {
		// Base case: If the node is null, there's nothing to do.
		if (node == null) {
			return;
		}

		// 1. Traverse the left subtree
		inorderHelper(node.getLeft(), result);

		// 2. Visit the current node
		result.add(node.getElement());

		// 3. Traverse the right subtree
		inorderHelper(node.getRight(), result);
	}

	/**
	 * Performs an inorder traversal of a binary tree iteratively using a stack.
	 *
	 * @param root The root of the binary tree.
	 * @return A list of integers representing the inorder traversal.
	 */
	public List<Integer> inorderTraversalIterative(TreeNode<Integer> root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Stack<TreeNode<Integer>> stack = new ArrayStack<>();
		TreeNode<Integer> currentNode = root; // Start from the root

		// Loop continues as long as there are nodes to process or nodes in the stack
		while (currentNode != null || !stack.isEmpty()) {
			// Phase 1: Go as far left as possible, pushing nodes onto the stack
			while (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.getLeft();
			}

			// Phase 2: current is now null. We've reached the leftmost point
			// The top of the stack is the node to visit (the "root" of the
			// subtree whose left part has been fully processed).
			currentNode = stack.pop(); // Get the node from the stack
			result.add(currentNode.getElement()); // Visit the node

			// Phase 3: Move to the right child and repeat the process
			// (i.e., go left from the right child, push, pop, etc.)
			currentNode = currentNode.getRight();
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
		TreeNode<Integer> root = new TreeNode<>(1, null, null);
		root.setLeft(new TreeNode<>(2, null, null));
		root.setRight(new TreeNode<>(3, null, null));
		root.getLeft().setLeft(new TreeNode<>(4, null, null));
		root.getLeft().setRight(new TreeNode<>(5, null, null));

		InorderTraversal solver = new InorderTraversal();

		// Test Recursive Approach
		List<Integer> recursiveResult = solver.inorderTraversal(root);
		System.out.println("Inorder Traversal (Recursive): " + recursiveResult); // Expected: [1, 2, 4, 5, 3]

		// Test with an empty tree
		List<Integer> emptyRecursiveResult = solver.inorderTraversal(null);
		System.out.println("Inorder Traversal (Empty Tree - Recursive): " + emptyRecursiveResult); // Expected: []

		System.out.println("\n--- Iterative Traversal ---");
		List<Integer> iterativeResult = solver.inorderTraversalIterative(root);
		System.out.println("Result: " + iterativeResult); // Expected: [1, 2, 4, 5, 3]

		List<Integer> emptyIterativeResult = solver.inorderTraversalIterative(null);
		System.out.println("Empty Tree Result: " + emptyIterativeResult); // Expected: []
	}
}
