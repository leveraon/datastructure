package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



//Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class SerializeDeserializeBinaryTree<T> {

//	String serializeBinaryTree(Node<T> root) {
//		StringBuilder serializedTree = new StringBuilder();
//
//		// use bfs to serialize binary tree
//		Queue<Node<T>> queue = new LinkedQueue<>();
//		queue.enqueue(root);
//		while (!queue.isEmpty()) {
//			Node<T> current = queue.first();
//			serializedTree.append(current.getElement());
//
//			if (current.getLeft() != null) {
//				queue.enqueue(current.getLeft());
//			}
//			if (current.getRight() != null) {
//				queue.enqueue(current.getRight());
//			}
//			serializedTree.append("-");
//		}
//
//		return serializedTree.toString();
//	}
//
//	Node<String> deserializeBinaryTree(String serializedTree) {
//		if (serializedTree == null) {
//			return null;
//		}
//		String[] treeNodes = serializedTree.split("-");
//
//		Node<String> root = null;
//		Node<String> current = null;
//		Node<String> last = null;
//		Stack<Node<String>> stack = new ArrayStack<>();
//		for (String element : treeNodes) {
//			if (root == null) {
//				root = new Node<>(element, null, null);
//				stack.push(root);
//			} else {
//				last = stack.pop();
//				current = new Node<>(element, null, null);
//				if (last.getLeft() == null) {
//					last.setLeft(current);
//					stack.push(last);
//					continue;
//				}
//				if (last.getRight() == null) {
//					last.setRight(current);
//					stack.push(last);
//					continue;
//				}
//
//				last = last.getLeft();
//				last.setLeft(current);
//
//				stack.push(last);
//
//			}
//		}
//
//		return root;
//	}
	
	
	// Delimiter to separate node values in the serialized string
    private static final String DELIMITER = ",";
    // Marker for null nodes
    private static final String NULL_NODE_MARKER = "N";

    /**
     * Encodes a tree to a single string.
     * Uses Pre-order DFS traversal.
     *
     * @param root The root of the binary tree.
     * @return A string representation of the tree.
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        return sb.toString();
    }

    private void dfsSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_NODE_MARKER).append(DELIMITER);
            return;
        }
        sb.append(node.val).append(DELIMITER);
        dfsSerialize(node.left, sb);
        dfsSerialize(node.right, sb);
    }

    /**
     * Decodes your encoded data to tree.
     * Uses Pre-order DFS traversal for reconstruction.
     *
     * @param data The serialized string of the tree.
     * @return The root of the reconstructed binary tree.
     */
    public TreeNode deserialize(String data) {
        // Handle empty or invalid input string if necessary, though problem usually implies valid format.
        if (data == null || data.isEmpty()) {
            return null;
        }

        // Split the string by the delimiter.
        // The last element might be an empty string if data ends with DELIMITER,
        // so we filter it out or use substring/index carefully.
        // Using `split` without a limit can sometimes produce an empty string at the end.
        // Example: "1,N,N," -> ["1", "N", "N", ""]
        // Using a negative limit preserves trailing empty strings if any, which is generally not an issue for `N`.
        String[] nodesArray = data.split(DELIMITER, -1); 
        
        // Use a Queue to process nodes in order
        Queue<String> nodesQueue = new LinkedList<>(Arrays.asList(nodesArray));
        
        return dfsDeserialize(nodesQueue);
    }

    private TreeNode dfsDeserialize(Queue<String> nodesQueue) {
        // If the queue is empty, it means we've consumed all valid tokens unexpectedly
        // or there was a malformed string. Based on serialization, this shouldn't happen
        // unless the initial string was empty or incorrectly formed.
        if (nodesQueue.isEmpty()) {
            return null; 
        }

        String val = nodesQueue.poll();

        if (val.equals(NULL_NODE_MARKER)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = dfsDeserialize(nodesQueue);
        node.right = dfsDeserialize(nodesQueue);

        return node;
    }

}
