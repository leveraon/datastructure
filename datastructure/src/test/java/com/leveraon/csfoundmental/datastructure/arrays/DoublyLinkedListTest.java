package com.leveraon.csfoundmental.datastructure.arrays;

import java.util.Random;

import org.junit.Test;

import com.leveraon.csfoundmental.datastructure.TestBase;
import com.leveraon.csfoundmental.datastructure.lists.DoublyLinkedList;

public class DoublyLinkedListTest extends TestBase {

	@Test
	public void testCreateDoublyLinkedList() {
		Integer count = new Integer(10);
		DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();
		Random rand = new Random(100);
		for (int i = 1; i < count; i++) {
			int nextInt = rand.nextInt(i + 1);
			System.out.println(nextInt + " is being added to Doubly linked list.");
			linkedList.addFirst(nextInt);
		}

		System.out.println("Doubly Linked List first node is: " + linkedList.first());
		System.out.println("Doubly Linked List last node is: " + linkedList.last());

		System.out.println("Node to be removed is: " + linkedList.removeFirst());
		System.out.println("Doubly Linked List first node after removed is: " + linkedList.first());
	}
}
