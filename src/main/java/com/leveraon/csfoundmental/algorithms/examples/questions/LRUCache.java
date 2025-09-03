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
		head.setNext(tail);
		tail.setPrev(head);
	}

	public void put(int i, T element) {
		if (!cache.containsKey(i)) {
			if (cache.size() >= capacity) {
				removeTail();
			}
			this.addNewNode(element);
		} else {
			Node<T> node = cache.get(i);
			this.moveToHead(node);
		}
	}

	public T get(int i) {
		Node<T> node = cache.get(i);
		if (node == null)
			return null;

		this.moveToHead(node);
		return node.getElement();
	}

	void addNewNode(T element) {
		Node<T> newNode = new Node<>(element, null, null);
		newNode.setNext(head);
		head.setPrev(newNode);
		head = newNode;
		capacity++;
	}

	void moveToHead(Node<T> node) {
		node.getPrev().setNext(node.getNext());
		node.setNext(head);
		head.setPrev(node);
		head = node;
	}

	void removeTail() {
		tail.setPrev(null);
		capacity--;
	}
}
