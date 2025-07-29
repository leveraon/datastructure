/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

//Definition for a binary tree node.
class Node {
	int data;
	Node left;
	Node right;

	// Constructor to create a new node
	Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

/**
 * 
 */
public class HeightOfBinaryTree {
	/**
	 * Calculates the height of a binary tree. The height is the number of edges on
	 * the longest path from the root to a leaf. An empty tree has a height of -1. A
	 * tree with a single node has a height of 0.
	 *
	 * @param root The root node of the binary tree.
	 * @return The height of the binary tree.
	 */
	public int height(Node root) {
		// Base case: If the tree is empty (null node), its height is -1.
		if (root == null) {
			return -1;
		}

		// Recursively calculate the height of the left subtree
		int leftSubtreeHeight = height(root.left);

		// Recursively calculate the height of the right subtree
		int rightSubtreeHeight = height(root.right);

		// The height of the current node's tree is 1 (for the edge from the current
		// node)
		// plus the maximum of the heights of its left and right subtrees.
		return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
	}

	// --- Example Usage (Main method) ---
	public static void main(String[] args) {
		HeightOfBinaryTree sol = new HeightOfBinaryTree();

		// Example 1: Empty tree
		// Height should be -1
		System.out.println("Height of empty tree: " + sol.height(null)); // Output: -1

		// Example 2: Single node tree
		// 10
		// Height should be 0
		Node root2 = new Node(10);
		System.out.println("Height of single node tree: " + sol.height(root2)); // Output: 0

		// Example 3: Simple tree
		// 10
		// / \
		// 20 30
		// Height should be 1
		Node root3 = new Node(10);
		root3.left = new Node(20);
		root3.right = new Node(30);
		System.out.println("Height of simple tree: " + sol.height(root3)); // Output: 1

		// Example 4: Deeper left subtree
		// 10
		// / \
		// 20 30
		// /
		// 40
		// Height should be 2
		Node root4 = new Node(10);
		root4.left = new Node(20);
		root4.right = new Node(30);
		root4.left.left = new Node(40);
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
		Node root5 = new Node(10);
		root5.left = new Node(20);
		root5.right = new Node(30);
		root5.right.right = new Node(40);
		root5.right.right.right = new Node(50);
		System.out.println("Height of deeper right subtree: " + sol.height(root5)); // Output: 3
	}
}
