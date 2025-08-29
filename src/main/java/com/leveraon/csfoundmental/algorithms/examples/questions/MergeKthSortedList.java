package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.PriorityQueue;

//Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

public class MergeKthSortedList {


	public ListNode mergeKLists(ListNode[] lists) {
		// Handle edge cases: no lists or only empty lists
		if (lists == null || lists.length == 0) {
			return null;
		}

		// Min-heap to store the current nodes from each list, ordered by their value.
		// We provide a custom Comparator to sort ListNodes by their 'val'.
		// The lambda (a, b) -> a.val - b.val creates a comparator that orders nodes by
		// ascending 'val'.
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

		// Add the head of each non-null list to the min-heap.
		for (ListNode list : lists) {
			if (list != null) {
				minHeap.offer(list); // Add the first node of each list
			}
		}

		// Dummy head for the merged linked list.
		// This simplifies adding nodes without worrying about the first node being
		// null.
		ListNode dummyHead = new ListNode(0);
		ListNode current = dummyHead; // Pointer to the last node in the merged list

		// While the heap is not empty, extract the smallest node.
		while (!minHeap.isEmpty()) {
			// Get the smallest node from the heap (this will be the next node in the merged
			// list).
			ListNode smallestNode = minHeap.poll();

			// Append this node to our merged list.
			current.next = smallestNode;
			current = current.next;

			// If the extracted node has a next element, add that next element to the heap.
			// This ensures we always have the next smallest candidates from all active
			// lists.
			if (smallestNode.next != null) {
				minHeap.offer(smallestNode.next);
			}
		}

		// The merged list starts from dummyHead.next.
		return dummyHead.next;
	}

}