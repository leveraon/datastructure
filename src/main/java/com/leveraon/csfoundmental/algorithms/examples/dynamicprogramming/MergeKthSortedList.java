package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.PriorityQueue;

import com.leveraon.csfoundmental.datastructure.tree.Node;

public class MergeKthSortedList {

	public Node<Integer> mergeKLists(Node<Integer>[] lists) {
		// Handle edge cases: no lists or only empty lists
		if (lists == null || lists.length == 0) {
			return null;
		}

		// Min-heap to store the current nodes from each list, ordered by their value.
		// We provide a custom Comparator to sort Node<Integer>s by their 'val'.
		// The lambda (a, b) -> a.val - b.val creates a comparator that orders nodes by
		// ascending 'val'.
		PriorityQueue<Node<Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getElement() - b.getElement());

		// Add the head of each non-null list to the min-heap.
		for (Node<Integer> list : lists) {
			if (list != null) {
				minHeap.offer(list); // Add the first node of each list
			}
		}

		// Dummy head for the merged linked list.
		// This simplifies adding nodes without worrying about the first node being
		// null.
		Node<Integer> dummyHead = new Node<>(0, null, null);
		Node<Integer> current = dummyHead; // Pointer to the last node in the merged list

		// While the heap is not empty, extract the smallest node.
		while (!minHeap.isEmpty()) {
			// Get the smallest node from the heap (this will be the next node in the merged
			// list).
			Node<Integer> smallestNode = minHeap.poll();

			// Append this node to our merged list.
			current.setNext(smallestNode);
			current = current.getNext();

			// If the extracted node has a next element, add that next element to the heap.
			// This ensures we always have the next smallest candidates from all active
			// lists.
			if (smallestNode.getNext() != null) {
				minHeap.offer(smallestNode.getNext());
			}
		}

		// The merged list starts from dummyHead.next.
		return dummyHead.getNext();
	}
	

    // --- Helper methods for testing ---
    public static Node<Integer> createList(int... values) {
        if (values.length == 0) return null;
        Node<Integer> dummy = new Node<Integer>(0, null, null);
        Node<Integer> current = dummy;
        for (int val : values) {
            current.setNext(new Node<Integer>(val, null, null));
            current = current.getNext();
        }
        return dummy.getNext();
    }

    public static void printList(Node<Integer> head) {
        if (head == null) {
            System.out.print("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        Node<Integer> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append("->");
            }
            current = current.getNext();
        }
        sb.append("]");
        System.out.print(sb.toString());
    }

    public static void main(String[] args) {
    	MergeKthSortedList solver = new MergeKthSortedList();

        // Example 1: lists = [[1,4,5],[1,3,4],[2,6]]
        Node<Integer>[] lists1 = new Node[3];
        lists1[0] = createList(1, 4, 5);
        lists1[1] = createList(1, 3, 4);
        lists1[2] = createList(2, 6);
        System.out.println("Input lists:");
        for (Node<Integer> list : lists1) { printList(list); System.out.print(" "); }
        System.out.print("\nMerged Output: ");
        printList(solver.mergeKLists(lists1)); // Expected: [1->1->2->3->4->4->5->6]
        System.out.println("\n---");

        // Example 2: lists = []
        Node<Integer>[] lists2 = new Node[0];
        System.out.println("Input lists: []");
        System.out.print("Merged Output: ");
        printList(solver.mergeKLists(lists2)); // Expected: []
        System.out.println("\n---");

        // Example 3: lists = [[]]
        Node<Integer>[] lists3 = new Node[1];
        lists3[0] = null; // Represents an empty list
        System.out.println("Input lists: [[]]");
        System.out.print("Merged Output: ");
        printList(solver.mergeKLists(lists3)); // Expected: []
        System.out.println("\n---");

        // Additional: lists with mixed empty/non-empty
        Node<Integer>[] lists4 = new Node[3];
        lists4[0] = createList(10, 20);
        lists4[1] = null; // Empty list
        lists4[2] = createList(5, 15);
        System.out.println("Input lists:");
        printList(lists4[0]); System.out.print(" "); printList(lists4[1]); System.out.print(" "); printList(lists4[2]);
        System.out.print("\nMerged Output: ");
        printList(solver.mergeKLists(lists4)); // Expected: [5->10->15->20]
        System.out.println("\n---");

        // Additional: single list
        Node<Integer>[] lists5 = new Node[1];
        lists5[0] = createList(1,2,3);
        System.out.println("Input lists:");
        printList(lists5[0]);
        System.out.print("\nMerged Output: ");
        printList(solver.mergeKLists(lists5)); // Expected: [1->2->3]
        System.out.println("\n---");
    }

}