package com.leveraon.csfoundmental.datastructure.queues;

/** Implementation of the queue ADT using a fixed-length array. */
public class ArrayQueue<E> implements Queue<E> {

    static int CAPACITY = 1000;
    // instance variables
    private E[] data; // generic array used for storage
    private int frontIndex = 0; // index of the front element
    private int size = 0; // current number of elements

    // constructors
    public ArrayQueue() {
        this(CAPACITY);
    } // constructs queue with default capacity

    public ArrayQueue(int capacity) { // constructs queue with given capacity
        data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
    }

    // methods
    /** Returns the number of elements in the queue. */
    public int size() {
        return size;
    }

    /** Tests whether the queue is empty. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Inserts an element at the rear of the queue. */
    public void enqueue(E e) throws IllegalStateException {
        if (size == data.length)
            throw new IllegalStateException("Queue is full");
        int avail = (frontIndex + size) % data.length; // use modular arithmetic
        data[avail] = e;
        size++;
    }

    /**
     * Returns, but does not remove, the first element of the queue (null if empty).
     */
    public E first() {
        if (isEmpty())
            return null;
        return data[frontIndex];
    }

    /** Removes and returns the first element of the queue (null if empty). */
    public E dequeue() {
        if (isEmpty())
            return null;
        E answer = data[frontIndex];
        data[frontIndex] = null; // dereference to help garbage collection
        frontIndex = (frontIndex + 1) % data.length;
        size--;
        return answer;
    }
}
