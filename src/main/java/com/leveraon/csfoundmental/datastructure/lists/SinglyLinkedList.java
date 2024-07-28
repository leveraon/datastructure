package com.leveraon.csfoundmental.datastructure.lists;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SinglyLinkedList<E> {
	@Data
	@AllArgsConstructor
	private static class Node<E> {
		private E element;
		private Node<E> next;
	}

	// instance variables of the SinglyLinkedList
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public SinglyLinkedList() {
	} // access methods

	// head node of the list (or null if empty) // last node of the list (or
	// null if empty) // number of nodes in the list
	// constructs an initially empty list
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		return head.getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	// update methods
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if (size == 0)
			tail = head;
		size++;
	}

	public void addLast(E e) {

		// returns (but does not remove) the first element
		// returns (but does not remove) the last element
		// adds element e to the front of the list // create and link a new node
		// special case: new node becomes tail also
		// adds element e to the end of the list

		Node<E> newest = new Node<>(e, null); // node will eventually be the
												// tail
		if (isEmpty())
			head = newest;
		else
			tail.setNext(newest);
		tail = newest;
		size++;
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if (size == 0)
			tail = null;
		return answer;// special case: previously empty list
		// new node after existing tail // new node becomes the tail
		// removes and returns the first element // nothing to remove
		// will become null if list had only one node // special case as list is
		// now empty
	}
}
