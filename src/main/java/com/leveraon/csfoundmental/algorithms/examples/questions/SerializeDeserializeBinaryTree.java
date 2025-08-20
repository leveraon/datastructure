package com.leveraon.csfoundmental.algorithms.examples.questions;

import com.leveraon.csfoundmental.datastructure.queues.LinkedQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;
import com.leveraon.csfoundmental.datastructure.stacks.ArrayStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;
import com.leveraon.csfoundmental.datastructure.tree.Node;

public class SerializeDeserializeBinaryTree<T> {

	String serializeBinaryTree(Node<T> root) {
		StringBuilder serializedTree = new StringBuilder();

		// use bfs to serialize binary tree
		Queue<Node<T>> queue = new LinkedQueue<>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node<T> current = queue.first();
			serializedTree.append(current.getElement());

			if (current.getLeft() != null) {
				queue.enqueue(current.getLeft());
			}
			if (current.getRight() != null) {
				queue.enqueue(current.getRight());
			}
			serializedTree.append("-");
		}

		return serializedTree.toString();
	}

	Node<String> deserializeBinaryTree(String serializedTree) {
		if (serializedTree == null) {
			return null;
		}
		String[] treeNodes = serializedTree.split("-");

		Node<String> root = null;
		Node<String> current = null;
		Node<String> last = null;
		Stack<Node<String>> stack = new ArrayStack<>();
		for (String element : treeNodes) {
			if (root == null) {
				root = new Node<>(element, null, null);
				stack.push(root);
			} else {
				last = stack.pop();
				current = new Node<>(element, null, null);
				if (last.getLeft() == null) {
					last.setLeft(current);
					stack.push(last);
					continue;
				}
				if (last.getRight() == null) {
					last.setRight(current);
					stack.push(last);
					continue;
				}

				last = last.getLeft();
				last.setLeft(current);

				stack.push(last);

			}
		}

		return root;
	}
}
