package com.leveraon.csfoundmental.jdk.features;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SequencedMap;

public class SequencedCollectionsDemo {
	public static void main(String[] args) {
		// SequencedCollection for List
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.addFirst("Dates"); // New method from SequencedCollection
		list.addLast("Elderberry"); // New method

		System.out.println("Sequenced List: " + list);
		System.out.println("First element: " + list.getFirst());
		System.out.println("Last element: " + list.getLast());

		System.out.print("Reversed list: ");
		list.reversed().forEach(s -> System.out.print(s + " ")); // Direct iteration
		System.out.println();

		System.out.println("Removed first: " + list.removeFirst());
		System.out.println("Removed last: " + list.removeLast());
		System.out.println("List after removals: " + list);

		System.out.println("---");

		// SequencedMap for LinkedHashMap
		SequencedMap<String, Integer> map = new LinkedHashMap<>();
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);
		map.putFirst("Zero", 0); // New method
		map.putLast("Four", 4); // New method

		System.out.println("Sequenced Map: " + map);
		System.out.println("First entry: " + map.firstEntry());
		System.out.println("Last entry: " + map.lastEntry());

		System.out.print("Reversed map keys: ");
		map.reversed().keySet().forEach(k -> System.out.print(k + " "));
		System.out.println();

		System.out.println("Removed first entry: " + map.pollFirstEntry());
		System.out.println("Removed last entry: " + map.pollLastEntry());
		System.out.println("Map after removals: " + map);
	}
}
