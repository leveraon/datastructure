package com.leveraon.csfoundmental.algorithms.examples.tree;

import java.util.ArrayList;
import java.util.List;

import com.leveraon.csfoundmental.datastructure.stacks.ArrayStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;
import com.leveraon.csfoundmental.datastructure.tree.TreeNode;

public class PreorderTraversal {
	/**
	 * Recursive approach for Preorder Traversal.
	 *
	 * Time Complexity: O(N), where N is the number of TreeNodes in the tree, as each
	 * TreeNode is visited exactly once. Space Complexity: O(H), where H is the height
	 * of the tree. This is due to the recursion stack. In the worst case (skewed
	 * tree), H can be N, so O(N). In the best case (balanced tree), H is logN, so
	 * O(logN).
	 */
	public List<Integer> preorderTraversalRecursive(TreeNode<Integer> root) {
		List<Integer> result = new ArrayList<>();
		// Call the helper function to perform the traversal
		preorderHelper(root, result);
		return result;
	}

	private void preorderHelper(TreeNode<Integer> TreeNode, List<Integer> result) {
		// Base case: if the TreeNode is null, there's nothing to do, so return.
		if (TreeNode == null) {
			return;
		}

		// 1. Visit the root (add its value to the result list)
		result.add(TreeNode.getElement());

		// 2. Traverse the left subtree recursively
		preorderHelper(TreeNode.getLeft(), result);

		// 3. Traverse the right subtree recursively
		preorderHelper(TreeNode.getRight(), result);
	}

	/**
	 * Iterative approach for Preorder Traversal using a Stack.
	 */
	public List<Integer> preorderTraversalIterative(TreeNode<Integer> root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Stack<TreeNode<Integer>> stack = new ArrayStack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode<Integer> currentTreeNode = stack.pop();
			result.add(currentTreeNode.getElement());

			// Push right child first, then left child, so left is processed before right
			// (LIFO)
			if (currentTreeNode.getRight() != null) {
				stack.push(currentTreeNode.getRight());
			}
			if (currentTreeNode.getLeft() != null) {
				stack.push(currentTreeNode.getLeft());
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
		TreeNode<Integer> root = new TreeNode<>(1, null, null);
		root.setLeft(new TreeNode<>(2, null, null));
		root.setRight(new TreeNode<>(3, null, null));
		root.getLeft().setLeft(new TreeNode<>(4, null, null));
		root.getLeft().setRight(new TreeNode<>(5, null, null));

		PreorderTraversal solver = new PreorderTraversal();

		// Test Recursive Approach
		List<Integer> recursiveResult = solver.preorderTraversalRecursive(root);
		System.out.println("Preorder Traversal (Recursive): " + recursiveResult); // Expected: [1, 2, 4, 5, 3]

		// Test with an empty tree
		List<Integer> emptyRecursiveResult = solver.preorderTraversalRecursive(null);
		System.out.println("Preorder Traversal (Empty Tree - Recursive): " + emptyRecursiveResult); // Expected: []

		System.out.println("\n--- Iterative Traversal ---");
		List<Integer> iterativeResult = solver.preorderTraversalIterative(root);
		System.out.println("Result: " + iterativeResult); // Expected: [1, 2, 4, 5, 3]

		List<Integer> emptyIterativeResult = solver.preorderTraversalIterative(null);
		System.out.println("Empty Tree Result: " + emptyIterativeResult); // Expected: []
	}

}
