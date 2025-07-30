package com.leveraon.csfoundmental.datastructure.lists;

import com.leveraon.csfoundmental.datastructure.tree.Node;

public class DoublyLinkedList<E> {

	// instance variables of the DoublyLinkedList
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	/** Constructs a new empty list. */
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setRight(trailer);
	}

	/** Returns the number of elements in the linked list. */
	public int size() {
		return size;
	}

	/** Tests whether the linked list is empty. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Returns (but does not remove) the first element of the list. */
	public E first() {
		if (isEmpty())
			return null;
		return header.getRight().getElement(); // first element is beyond header
	}

	/** Returns (but does not remove) the last element of the list. */
	public E last() {
		if (isEmpty())
			return null;
		return trailer.getLeft().getElement(); // last element is before trailer
	}

	// public update methods
	/** Adds element e to the front of the list. */
	public void addFirst(E e) {
		addBetween(e, header, header.getRight());
	}

	/** Adds element e to the end of the list. */
	public void addLast(E e) {
		addBetween(e, trailer.getLeft(), trailer);
	}

	/** Removes and returns the first element of the list. */
	public E removeFirst() {
		if (isEmpty())
			return null; // nothing to remove
		return remove(header.getRight()); // first element is beyond header
	}

	/** Removes and returns the last element of the list. */
	public E removeLast() {
		if (isEmpty())
			return null;
		return remove(trailer.getLeft());
	}

	// private update methods
	/** Adds element e to the linked list in between the given nodes. */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		// create and link a new node
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setRight(newest);
		successor.setLeft(newest);
		size++;
	}

	/** Removes the given node from the list and returns its element. */
	private E remove(Node<E> node) {
		Node<E> predecessor = node.getLeft();
		Node<E> successor = node.getRight();
		predecessor.setRight(successor);
		successor.setLeft(predecessor);
		size--;
		return node.getElement();
	}
}
