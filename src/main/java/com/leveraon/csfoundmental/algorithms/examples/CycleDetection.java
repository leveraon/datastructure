/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

import com.leveraon.csfoundmental.datastructure.tree.Node;

/**
 * 
 */
public class CycleDetection {
	 /**
     * Detects if a cycle exists in a linked list using Floyd's Tortoise and Hare algorithm.
     *
     * @param head The head of the linked list.
     * @return true if a cycle exists, false otherwise.
     */
    public boolean hasCycle(Node head) {
        // Edge cases: empty list or single node list cannot have a cycle
        if (head == null || head.getRight() == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;

        // Loop as long as fast and fast.next are not null
        // This ensures fast doesn't go out of bounds on a non-cyclic list
        while (fast != null && fast.getRight() != null) {
            slow = slow.getRight();         // Move slow by one step
            fast = fast.getRight().getRight();    // Move fast by two steps

            // If they meet, a cycle is detected
            if (slow == fast) {
                return true;
            }
        }

        // If fast reaches null, it means there's no cycle
        return false;
    }
    
    /**
     * Finds the starting node of a cycle in a linked list using Floyd's Tortoise and Hare algorithm.
     * If no cycle exists, returns null.
     *
     * @param head The head of the linked list.
     * @return The starting node of the cycle, or null if no cycle.
     */
    public Node detectCycle(Node head) {
        // Edge cases: empty list or single node list cannot have a cycle
        if (head == null || head.getRight() == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        boolean cycleFound = false; // Flag to indicate if a cycle was found

        // Phase 1: Detect the cycle
        while (fast != null && fast.getRight() != null) {
            slow = slow.getRight();
            fast = fast.getRight().getRight();

            if (slow == fast) {
                cycleFound = true;
                break; // Cycle detected, break out of the first loop
            }
        }

        // If no cycle was found, return null
        if (!cycleFound) {
            return null;
        }

        // Phase 2: Find the starting node of the cycle
        // Reset slow to head, fast remains at meeting point
        slow = head;
        while (slow != fast) {
            slow = slow.getRight();
            fast = fast.getRight();
        }

        // Both pointers now point to the start of the cycle
        return slow;
    }
    
    
 // --- Main method for testing ---
//    public static void main(String[] args) {
//        CycleDetection detector = new CycleDetection();
//
//        // --- Test Case 1: No Cycle ---
//        Node head1 = new Node(1);
//        head1.next = new Node(2);
//        head1.next.next = new Node(3);
//        head1.next.next.next = new Node(4);
//
//        System.out.println("--- Test Case 1: No Cycle ---");
//        System.out.println("Has Cycle: " + detector.hasCycle(head1)); // Expected: false
//        Node cycleStart1 = detector.detectCycle(head1);
//        System.out.println("Cycle Start: " + (cycleStart1 == null ? "null" : cycleStart1.val)); // Expected: null
//        System.out.println("-----------------------------\n");
//
//
//        // --- Test Case 2: Cycle (1 -> 2 -> 3 -> 4 -> 2) ---
//        Node head2 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        Node node4 = new Node(4);
//
//        head2.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node2; // Creates a cycle: 4 points back to 2
//
//        System.out.println("--- Test Case 2: Cycle (4 -> 2) ---");
//        System.out.println("Has Cycle: " + detector.hasCycle(head2)); // Expected: true
//        Node cycleStart2 = detector.detectCycle(head2);
//        System.out.println("Cycle Start: " + (cycleStart2 == null ? "null" : cycleStart2.val)); // Expected: 2
//        System.out.println("-----------------------------\n");
//
//
//        // --- Test Case 3: Cycle at the head (1 -> 2 -> 1) ---
//        Node head3 = new Node(1);
//        Node node_2_3 = new Node(2);
//        head3.next = node_2_3;
//        node_2_3.next = head3; // Creates a cycle: 2 points back to 1
//
//        System.out.println("--- Test Case 3: Cycle at head (2 -> 1) ---");
//        System.out.println("Has Cycle: " + detector.hasCycle(head3)); // Expected: true
//        Node cycleStart3 = detector.detectCycle(head3);
//        System.out.println("Cycle Start: " + (cycleStart3 == null ? "null" : cycleStart3.val)); // Expected: 1
//        System.out.println("-----------------------------\n");
//
//        // --- Test Case 4: Single Node, No Cycle ---
//        Node head4 = new Node(10);
//        System.out.println("--- Test Case 4: Single Node, No Cycle ---");
//        System.out.println("Has Cycle: " + detector.hasCycle(head4)); // Expected: false
//        Node cycleStart4 = detector.detectCycle(head4);
//        System.out.println("Cycle Start: " + (cycleStart4 == null ? "null" : cycleStart4.val)); // Expected: null
//        System.out.println("-----------------------------\n");
//
//        // --- Test Case 5: Empty List ---
//        Node head5 = null;
//        System.out.println("--- Test Case 5: Empty List ---");
//        System.out.println("Has Cycle: " + detector.hasCycle(head5)); // Expected: false
//        Node cycleStart5 = detector.detectCycle(head5);
//        System.out.println("Cycle Start: " + (cycleStart5 == null ? "null" : cycleStart5.val)); // Expected: null
//        System.out.println("-----------------------------\n");
//    }
}
