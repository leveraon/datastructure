package com.leveraon.csfoundmental.algorithms.sorting;

import java.util.Comparator;

import com.leveraon.csfoundmental.algorithms.sorting.MergeSort.IntComparator;
import com.leveraon.csfoundmental.datastructure.queues.LinkedQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public static void main(String[] args) {
        Integer[] array = { 1, 2, 3, 4, 55, 88, 32, 23, 24, 13, 39, 9, 29, 74, 28 };
        Queue<Integer> linkedQueue = new LinkedQueue<>();
        log.info("Array before sorting ");
        for (Integer integer : array) {
            System.out.print(integer + ",");
            linkedQueue.enqueue(integer);
        }
        System.out.println("\n");
        Comparator<Integer> comp = new IntComparator();
        quickSort(linkedQueue, comp);

        log.info("Array after sorting ");
        while (!linkedQueue.isEmpty()) {
            System.out.print(linkedQueue.dequeue() + ",");
        }
    }
}
