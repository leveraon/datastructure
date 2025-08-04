/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.tree;

import com.leveraon.csfoundmental.datastructure.tree.Node;

/**
 * 
 */
public class CalculateTreeHeight {
	/**
	 * Calculates the height of a binary tree. The height is the number of edges on
	 * the longest path from the root to a leaf. An empty tree has a height of -1. A
	 * tree with a single node has a height of 0.
	 *
	 * @param root The root node of the binary tree.
	 * @return The height of the binary tree.
	 */
	public int height(Node<Integer> root) {
		// Base case: If the tree is empty (null node), its height is -1.
		if (root == null) {
			return -1;
		}

		// Recursively calculate the height of the left subtree
		int leftSubtreeHeight = height(root.getLeft());

		// Recursively calculate the height of the right subtree
		int rightSubtreeHeight = height(root.getRight());

		// The height of the current node's tree is 1 (for the edge from the current
		// node)
		// plus the maximum of the heights of its left and right subtrees.
		return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
	}

	// --- Example Usage (Main method) ---
	public static void main(String[] args) {
		CalculateTreeHeight sol = new CalculateTreeHeight();

		// Example 1: Empty tree
		// Height should be -1
		System.out.println("Height of empty tree: " + sol.height(null)); // Output: -1

		// Example 2: Single node tree
		// 10
		// Height should be 0
		Node<Integer> root2 = new Node<>(10, null, null);
		System.out.println("Height of single node tree: " + sol.height(root2)); // Output: 0

		// Example 3: Simple tree
		// 10
		// / \
		// 20 30
		// Height should be 1
		Node<Integer> root3 = new Node<Integer>(10, null, null);
		root3.setLeft(new Node<Integer>(20, null, null));
		root3.setRight(new Node<Integer>(30, null, null));
		System.out.println("Height of simple tree: " + sol.height(root3)); // Output: 1

		// Example 4: Deeper left subtree
		// 10
		// / \
		// 20 30
		// /
		// 40
		// Height should be 2
		Node<Integer> root4 = new Node<Integer>(10, null, null);
		root4.setLeft(new Node<Integer>(20, null, null));
		root4.setRight(new Node<Integer>(30, null, null));
		root4.getLeft().setLeft(new Node<Integer>(40, null, null));
		System.out.println("Height of deeper left subtree: " + sol.height(root4)); // Output: 2

		// Example 5: Deeper right subtree
		// 10
		// / \
		// 20 30
		// \
		// 40
		// \
		// 50
		// Height should be 3
		Node<Integer> root5 = new Node<Integer>(10, null, null);
		root5.setLeft(new Node<Integer>(20, null, null));
		root5.setRight(new Node<Integer>(30, null, null));
		root5.getRight().setRight(new Node<Integer>(40, null, null));
		root5.getRight().getRight().setRight(new Node<Integer>(50, null, null));
		System.out.println("Height of deeper right subtree: " + sol.height(root5)); // Output: 3
	}
}
