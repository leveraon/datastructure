package main.java.com.leveraon.csfoundmental.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

import main.java.com.leveraon.csfoundmental.datastructure.queues.LinkedQueue;

public class TreeTraversal {

    /**
     * Returns an iterable collection of positions of the tree in breadth-first
     * order.
     */
    public Iterable<Position<E>> breadthfirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root()); // start with the root
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.dequeue(); // remove from front of the queue
                snapshot.add(p); // report this position
                for (Position<E> c : children(p))
                    fringe.enqueue(c); // add children to back of queue
            }
        }
        return snapshot;
    }
}
