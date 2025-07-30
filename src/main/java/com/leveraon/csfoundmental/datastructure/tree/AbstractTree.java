package com.leveraon.csfoundmental.datastructure.tree;

/**
 * An abstract base class providing some functionality of the Tree interface.
 */
public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Node<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Node<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Node<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}