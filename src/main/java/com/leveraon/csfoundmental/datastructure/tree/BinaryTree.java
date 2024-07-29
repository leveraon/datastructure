package com.leveraon.csfoundmental.datastructure.tree;

import com.leveraon.csfoundmental.datastructure.lists.Node;

public interface BinaryTree<E> extends Tree<E> {
    /** Returns the Node of p's left child (or null if no child exists). */
    Node<E> left(Node<E> p) throws IllegalArgumentException;

    /** Returns the Node of p's right child (or null if no child exists). */
    Node<E> right(Node<E> p) throws IllegalArgumentException;

    /** Returns the Node of p's sibling (or null if no sibling exists). */
    Node<E> sibling(Node<E> p) throws IllegalArgumentException;
}
