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

	// Wrong
//	int[] mergeKthList(int[][] lists) {
//		int[] result = {};
//
//		if (lists == null)
//			return null;
//
//		int head = 0;
//		int tail = 0;
//
//		for (int[] list : lists) {
//			if (result.length == 0) {
//				result = Arrays.copyOf(list, list.length);
//				head = list[0];
//				tail = list[list.length - 1];
//			} else {
//				int currentHead = list[0];
//				int currentTail = list[1];
//
//				// list greatest element is less or equal to head, move list to front. update
//				// head
//				if (currentTail <= head) {
//					result = addBeforeOrAfter(list, result);
//					head = currentHead;
//					continue;
//				}
//
//				// list smallest element is greater or equal to tail, move list to end. update
//				// tail
//				if (currentHead >= tail) {
//					result = addBeforeOrAfter(result, list);
//					tail = currentTail;
//					continue;
//				}
//
//				// list head in between current head and tail range. insert
//				result = addInBetween(result, list);
//
//			}
//		}
//
//		return result;
//	}
//
//	int[] addInBetween(int[] source, int[] target) {
//		if (source == null)
//			return null;
//
//		if (target == null)
//			return source;
//
//		int[] result = {};
//		int j = 0;
//		for (int i = 0; i < source.length; i++) {
//			result[i] = source[i];
//			while (j < target.length) {
//				if (target[j] >= result[i]) {
//					result[i + j] = target[j];
//					j++;
//				} else {
//					int temp = result[i];
//					result[i] = target[j];
//					result[i + j] = temp;
//					i++;
//				}
//			}
//		}
//
//		return result;
//	}
//
//	int[] addBeforeOrAfter(int[] source, int[] target) {
//		if (source == null) {
//			return target;
//		}
//
//		if (target == null) {
//			return source;
//		}
//
//		int[] newArray = {};
//
//		int index = 0;
//		for (int i = 0; i < source.length; i++) {
//			newArray[i] = source[i];
//			index++;
//		}
//		for (int i = 0; i < target.length; i++) {
//			newArray[index] = target[i];
//			index++;
//		}
//
//		return newArray;
//	}

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