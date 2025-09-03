package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree<T> {

//	// Delimiter to separate node values in the serialized string
//	private static final String DELIMITER = ",";
//	// Marker for null nodes
//	private static final String NULL_NODE_MARKER = "N";
//
//	/**
//	 * Encodes a tree to a single string. Uses Pre-order DFS traversal.
//	 *
//	 * @param root The root of the binary tree.
//	 * @return A string representation of the tree.
//	 */
//	public String serialize(Node root) {
//		StringBuilder sb = new StringBuilder();
//		dfsSerialize(root, sb);
//		return sb.toString();
//	}
//
//	private void dfsSerialize(Node node, StringBuilder sb) {
//		if (node == null) {
//			sb.append(NULL_NODE_MARKER).append(DELIMITER);
//			return;
//		}
//		sb.append(node.val).append(DELIMITER);
//		dfsSerialize(node, sb);
//		dfsSerialize(node.right, sb);
//	}
//
//	/**
//	 * Decodes your encoded data to tree. Uses Pre-order DFS traversal for
//	 * reconstruction.
//	 *
//	 * @param data The serialized string of the tree.
//	 * @return The root of the reconstructed binary tree.
//	 */
//	public Node deserialize(String data) {
//		// Handle empty or invalid input string if necessary, though problem usually
//		// implies valid format.
//		if (data == null || data.isEmpty()) {
//			return null;
//		}
//
//		// Split the string by the delimiter.
//		// The last element might be an empty string if data ends with DELIMITER,
//		// so we filter it out or use substring/index carefully.
//		// Using `split` without a limit can sometimes produce an empty string at the
//		// end.
//		// Example: "1,N,N," -> ["1", "N", "N", ""]
//		// Using a negative limit preserves trailing empty strings if any, which is
//		// generally not an issue for `N`.
//		String[] nodesArray = data.split(DELIMITER, -1);
//
//		// Use a Queue to process nodes in order
//		Queue<String> nodesQueue = new LinkedList<>(Arrays.asList(nodesArray));
//
//		return dfsDeserialize(nodesQueue);
//	}
//
//	private Node dfsDeserialize(Queue<String> nodesQueue) {
//		// If the queue is empty, it means we've consumed all valid tokens unexpectedly
//		// or there was a malformed string. Based on serialization, this shouldn't
//		// happen
//		// unless the initial string was empty or incorrectly formed.
//		if (nodesQueue.isEmpty()) {
//			return null;
//		}
//
//		String val = nodesQueue.poll();
//
//		if (val.equals(NULL_NODE_MARKER)) {
//			return null;
//		}
//
//		Node node = new Node(Integer.parseInt(val));
//		node.left = dfsDeserialize(nodesQueue);
//		node.right = dfsDeserialize(nodesQueue);
//
//		return node;
//	}

}
