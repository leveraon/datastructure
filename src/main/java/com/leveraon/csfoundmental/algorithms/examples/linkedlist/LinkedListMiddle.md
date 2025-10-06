Finding the middle of a linked list is a classic problem that can be solved efficiently using the **"Fast and Slow Pointer"** (also known as Tortoise and Hare) approach.

### The Fast and Slow Pointer Approach

**Idea:**
1.  Initialize two pointers, `slow` and `fast`, both starting at the `head` of the linked list.
2.  In each step, move `slow` by one node and `fast` by two nodes.
3.  When `fast` reaches the end of the list (or `null`), `slow` will be at the middle of the list.

**Why it works:**
If `slow` moves `x` steps, `fast` moves `2x` steps. When `fast` reaches the end of a list of length `N`, `fast` will have moved approximately `N` steps. At this point, `slow` will have moved approximately `N/2` steps, placing it exactly at the middle.

---

### Java Implementation

First, let's define a simple `ListNode` class for our linked list:

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
```

Now, the `findMiddle` method:

```java
public class LinkedListMiddle {

    // Method to find the middle of a linked list
    public ListNode findMiddle(ListNode head) {
        // Handle edge cases: empty list or single-node list
        if (head == null) {
            return null;
        }
        if (head.next == null) { // Only one node
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Loop until fast reaches the end of the list
        // The condition `fast != null` ensures fast itself is not null
        // The condition `fast.next != null` ensures fast can move two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow by one step
            fast = fast.next.next;    // Move fast by two steps
        }

        // When the loop terminates, slow will be at the middle node
        return slow;
    }

    // --- Helper method for testing ---
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedListMiddle solver = new LinkedListMiddle();

        // Test Case 1: Odd number of nodes
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        System.out.print("List 1: ");
        solver.printList(head1);
        ListNode middle1 = solver.findMiddle(head1);
        System.out.println("Middle of List 1: " + (middle1 != null ? middle1.val : "null")); // Expected: 3

        System.out.println("--------------------");

        // Test Case 2: Even number of nodes
        ListNode head2 = new ListNode(10);
        head2.next = new ListNode(20);
        head2.next.next = new ListNode(30);
        head2.next.next.next = new ListNode(40);
        System.out.print("List 2: ");
        solver.printList(head2);
        ListNode middle2 = solver.findMiddle(head2);
        System.out.println("Middle of List 2: " + (middle2 != null ? middle2.val : "null")); // Expected: 30 (second middle)

        System.out.println("--------------------");

        // Test Case 3: Single node
        ListNode head3 = new ListNode(100);
        System.out.print("List 3: ");
        solver.printList(head3);
        ListNode middle3 = solver.findMiddle(head3);
        System.out.println("Middle of List 3: " + (middle3 != null ? middle3.val : "null")); // Expected: 100

        System.out.println("--------------------");

        // Test Case 4: Two nodes
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);
        System.out.print("List 4: ");
        solver.printList(head4);
        ListNode middle4 = solver.findMiddle(head4);
        System.out.println("Middle of List 4: " + (middle4 != null ? middle4.val : "null")); // Expected: 2 (second middle)

        System.out.println("--------------------");

        // Test Case 5: Empty list
        ListNode head5 = null;
        System.out.print("List 5: ");
        solver.printList(head5);
        ListNode middle5 = solver.findMiddle(head5);
        System.out.println("Middle of List 5: " + (middle5 != null ? middle5.val : "null")); // Expected: null
    }
}
```

---

### Complexity Analysis:

*   **Time Complexity: O(N)**
    The `fast` pointer traverses the entire list once. The `slow` pointer traverses half the list. Therefore, the total time taken is proportional to the number of nodes `N` in the linked list.
*   **Space Complexity: O(1)**
    We only use a few constant extra variables (`slow`, `fast`, `head`). We don't use any data structures that grow with the input size.

### Important Note on "Middle" for Even Length Lists:

*   For a list like `1 -> 2 -> 3 -> 4`, there are two "middle" nodes: `2` and `3`.
*   The implementation above (where `slow` moves after `fast` has processed `fast.next`) will return the **second middle** (e.g., `3` for `1 -> 2 -> 3 -> 4`).
*   If you needed the **first middle** (e.g., `2` for `1 -> 2 -> 3 -> 4`), you would typically initialize `fast` to `head.next` instead of `head`, or adjust the loop condition slightly. However, returning the second middle is the more common interpretation for this problem in many contexts (e.g., LeetCode's "Middle of the Linked List").