package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.HashMap;
import java.util.Map;

import com.leveraon.csfoundmental.datastructure.tree.Node;

public class LRUCache<T> {

	Map<Integer, Node<T>> cache;
	int capacity;
	private Node<T> head; // Points to the most recently used (MRU) node
	private Node<T> tail; // Points to the least recently used (LRU) node

	LRUCache(int capacity) {
		this.capacity = capacity;
		cache = new HashMap<>();
		head = new Node<T>(null, null, null);
		tail = new Node<T>(null, null, null);
		head.setRight(tail);
		tail.setLeft(head);
	}

	public void put(int i, T element) {

	}

	public T get(int i) {
		Node<T> node = cache.get(i);
		if (node == null)
			return null;

		this.moveNodeToHead(node);
		return node.getElement();
	}

	void moveNodeToHead(Node<T> node) {

	}
}
