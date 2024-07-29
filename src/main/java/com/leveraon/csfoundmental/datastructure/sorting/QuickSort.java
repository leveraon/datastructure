package com.leveraon.csfoundmental.datastructure.sorting;

import java.util.Comparator;

import com.leveraon.csfoundmental.datastructure.queues.LinkedQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;

public class QuickSort {
    /**
     * Quick-sort contents
     * of a queue.
     */

    public static <K> void quickSort(Queue<K> S, Comparator<K> comp) {
        int n = S.size();
        if (n < 2)
            return; // queue is trivially sorted
        // divide
        K pivot = S.first(); // using first as arbitrary pivot
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        while (!S.isEmpty()) { // divide original into L, E, and G
            K element = S.dequeue();
            int c = comp.compare(element, pivot);
            if (c < 0) // element is less than pivot
                L.enqueue(element);
            else if (c == 0) // element is equal to pivot
                E.enqueue(element);
            else // element is greater than pivot
                G.enqueue(element);
        } // conquer
        quickSort(L, comp); // sort elements less than pivot
        quickSort(G, comp); // sort elements greater than pivot
        // concatenate results
        while (!L.isEmpty())
            S.enqueue(L.dequeue());
        while (!E.isEmpty())
            S.enqueue(E.dequeue());
        while (!G.isEmpty())
            S.enqueue(G.dequeue());
    }
}
