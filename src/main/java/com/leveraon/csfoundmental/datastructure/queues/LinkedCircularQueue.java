package com.leveraon.csfoundmental.datastructure.queues;

import com.leveraon.csfoundmental.datastructure.tree.Node;

public class LinkedCircularQueue<E> implements CircularQueue<E> {
    // instance variables of the CircularlyLinkedList
    private Node<E> tail = null; // we store tail (but not head)
    private int size = 0; // number of nodes in the list

    public LinkedCircularQueue() {
    } // constructs an initially empty list

    // access methods
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() { // returns (but does not remove) the first element
        if (isEmpty())
            return null;
        return tail.getRight().getElement(); // the head is *after* the tail
    }

    // update methods
    public void rotate() { // rotate the first element to the back of the list
        if (tail != null) // if empty, do nothing
            tail = tail.getRight(); // the old head becomes the new tail
    }

    public void enqueue(E element) {
        addLast(element);
    }

    public E dequeue() {
        return removeFirst();
    }

    private void addFirst(E e) { // adds element e to the front of the list
        if (size == 0) {
            tail = new Node<>(e, null, null);
            tail.setRight(tail); // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, null, tail.getRight());
            tail.setRight(newest);
        }
        size++;
    }

    private void addLast(E e) { // adds element e to the end of the list
        addFirst(e); // insert new element at front of list
        tail = tail.getRight(); // now new element becomes the tail
    }

    private E removeFirst() { // removes and returns the first element
        if (isEmpty())
            return null; // nothing to remove
        Node<E> head = tail.getRight();
        if (head == tail)
            tail = null; // must be the only node left
        else
            tail.setRight(head.getRight()); // removes ”head” from the list
        size--;
        return head.getElement();
    }
}
