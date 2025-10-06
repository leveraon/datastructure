
### **Problem 7: Merge K Sorted Lists**

**Problem Statement:**
You are given an array of `k` linked-lists `lists`, each sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

**Example 1:**
Input: `lists = [[1,4,5],[1,3,4],[2,6]]`
Output: `[1,1,2,3,4,4,5,6]`
Explanation: The linked-lists are:
`[1->4->5]`, `[1->3->4]`, `[2->6]`
merging them into one sorted list: `1->1->2->3->4->4->5->6`

**Example 2:**
Input: `lists = []`
Output: `[]`

**Example 3:**
Input: `lists = [[]]`
Output: `[]`

---

**Understanding & Clarification:**

*   **`lists` array:** Can it be `null` or empty? (Yes, handle by returning `null`). Can it contain `null` lists (empty linked lists)? (Yes, treat as empty and ignore).
*   **Linked Lists:** Are they singly or doubly linked? (Assume singly, as `next` is sufficient). What is the `ListNode` structure? (Typically `val`, `next`).
*   **Sorted order:** Ascending.
*   **Number of lists `k`:** Can be 0.
*   **Length of lists `n_i`:** Individual list lengths can vary. The total number of nodes across all lists, let's call it `N = sum(n_i)`. This `N` can be very large.

---

**Approach:**

Several approaches exist, with varying time/space complexities:

1.  **Brute Force (Collect all, Sort, Rebuild):**
    *   Traverse all `k` linked lists and collect all `N` node values into a single `ArrayList`.
    *   Sort the `ArrayList`.
    *   Build a new linked list from the sorted `ArrayList`.
    *   Time Complexity: O(N log N) (due to sorting).
    *   Space Complexity: O(N) (for the `ArrayList`).
    *   *Simple to implement, but generally not optimal for linked lists, especially if `N` is huge and doesn't fit in memory.*

2.  **Iterative Merging (Pairwise):**
    *   Repeatedly merge two lists at a time using a standard `mergeTwoLists` helper function.
    *   Start by merging `lists[0]` and `lists[1]`, then merge the result with `lists[2]`, and so on.
    *   Time Complexity: O(K * N), where `N` is the total number of nodes. In the worst case (e.g., `list[0]` is very long, and others are short), each merge operation will traverse almost the entire length of the intermediate list. For `K` lists, this can sum up to `N + 2N + 3N + ... + (K-1)N` which simplifies to `O(K * N)`.
    *   Space Complexity: O(1) extra space (for merging, excluding the output list).
    *   *Better than brute force, but still less efficient than optimal approaches for large `K`.*

3.  **Divide and Conquer:**
    *   This approach is similar to Merge Sort applied to lists.
    *   Divide the `k` lists into two halves. Recursively merge the first half, recursively merge the second half, and then merge the two resulting sorted lists.
    *   The base case is when there's only one list (return it) or no lists (return `null`).
    *   Time Complexity: O(N log K). There are `log K` levels of merging. At each level, the total number of nodes processed across all merges is `N`. For example, at the first level, `k/2` merges happen, each processing approximately `2N/K` nodes, summing to `N` work.
    *   Space Complexity: O(log K) for the recursion stack depth.
    *   *Optimal time complexity, good for balanced merges.*

4.  **Min-Heap (PriorityQueue):**
    *   This is typically the most efficient and practical approach, especially in interviews.
    *   Use a `Min-Heap` (PriorityQueue in Java) to keep track of the smallest node from *each* of the `k` lists.
    *   **Initialization:** Add the head node of each non-empty list into the min-heap. The heap will automatically order them by their `val`.
    *   **Extraction Loop:** While the heap is not empty:
        *   Extract the smallest node (`minNode`) from the heap.
        *   Append `minNode` to the merged linked list.
        *   If `minNode` has a `next` node, add that `next` node to the heap.
    *   Continue until the heap is empty.
    *   Time Complexity: O(N log K).
        *   Each of the `N` total nodes is added to the heap exactly once and removed from the heap exactly once.
        *   Heap operations (`offer`/`poll`) take O(log K) time because the heap size is at most `k` (it stores one node from each of the `k` lists).
    *   Space Complexity: O(K) for the heap (stores up to `k` nodes).
    *   *Optimal time, good space, very robust for varying `N` and `K`.*

---

**Java Code Solution (Min-Heap / PriorityQueue):**

```java
import java.util.PriorityQueue;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // Handle edge cases: no lists or only empty lists
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap to store the current nodes from each list, ordered by their value.
        // We provide a custom Comparator to sort ListNodes by their 'val'.
        // The lambda (a, b) -> a.val - b.val creates a comparator that orders nodes by ascending 'val'.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-null list to the min-heap.
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list); // Add the first node of each list
            }
        }

        // Dummy head for the merged linked list.
        // This simplifies adding nodes without worrying about the first node being null.
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead; // Pointer to the last node in the merged list

        // While the heap is not empty, extract the smallest node.
        while (!minHeap.isEmpty()) {
            // Get the smallest node from the heap (this will be the next node in the merged list).
            ListNode smallestNode = minHeap.poll();

            // Append this node to our merged list.
            current.next = smallestNode;
            current = current.next;

            // If the extracted node has a next element, add that next element to the heap.
            // This ensures we always have the next smallest candidates from all active lists.
            if (smallestNode.next != null) {
                minHeap.offer(smallestNode.next);
            }
        }

        // The merged list starts from dummyHead.next.
        return dummyHead.next;
    }

    // --- Helper methods for testing ---
    public static ListNode createList(int... values) {
        if (values.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            System.out.print("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append("->");
            }
            current = current.next;
        }
        sb.append("]");
        System.out.print(sb.toString());
    }

    public static void main(String[] args) {
        MergeKSortedLists solver = new MergeKSortedLists();

        // Example 1: lists = [[1,4,5],[1,3,4],[2,6]]
        ListNode[] lists1 = new ListNode[3];
        lists1[0] = createList(1, 4, 5);
        lists1[1] = createList(1, 3, 4);
        lists1[2] = createList(2, 6);
        System.out.println("Input lists:");
        for (ListNode list : lists1) { printList(list); System.out.print(" "); }
        System.out.print("\nMerged Output: ");
        printList(solver.mergeKLists(lists1)); // Expected: [1->1->2->3->4->4->5->6]
        System.out.println("\n---");

        // Example 2: lists = []
        ListNode[] lists2 = new ListNode[0];
        System.out.println("Input lists: []");
        System.out.print("Merged Output: ");
        printList(solver.mergeKLists(lists2)); // Expected: []
        System.out.println("\n---");

        // Example 3: lists = [[]]
        ListNode[] lists3 = new ListNode[1];
        lists3[0] = null; // Represents an empty list
        System.out.println("Input lists: [[]]");
        System.out.print("Merged Output: ");
        printList(solver.mergeKLists(lists3)); // Expected: []
        System.out.println("\n---");

        // Additional: lists with mixed empty/non-empty
        ListNode[] lists4 = new ListNode[3];
        lists4[0] = createList(10, 20);
        lists4[1] = null; // Empty list
        lists4[2] = createList(5, 15);
        System.out.println("Input lists:");
        printList(lists4[0]); System.out.print(" "); printList(lists4[1]); System.out.print(" "); printList(lists4[2]);
        System.out.print("\nMerged Output: ");
        printList(solver.mergeKLists(lists4)); // Expected: [5->10->15->20]
        System.out.println("\n---");

        // Additional: single list
        ListNode[] lists5 = new ListNode[1];
        lists5[0] = createList(1,2,3);
        System.out.println("Input lists:");
        printList(lists5[0]);
        System.out.print("\nMerged Output: ");
        printList(solver.mergeKLists(lists5)); // Expected: [1->2->3]
        System.out.println("\n---");
    }
}
```

---

**Complexity Analysis (for Min-Heap solution):**

*   **Time Complexity:** O(N log K)
    *   `N` is the total number of nodes across all linked lists (`N = sum(length of each list)`).
    *   `K` is the number of linked lists.
    *   **Initialization:** Adding the initial `K` head nodes to the heap takes O(K log K) time.
    *   **Main Loop:** We process each of the `N` nodes exactly once. For each node, we `poll` it from the heap (O(log K)) and potentially `offer` its `next` node back to the heap (O(log K)).
    *   Therefore, the total time complexity is O(K log K + N log K). Since `N` will generally be much larger than `K` in a meaningful input (unless all lists are very short), the dominating term is O(N log K).

*   **Space Complexity:** O(K)
    *   The `PriorityQueue` stores at most `K` nodes (one current node from each active list).

---

**Edge Cases & Considerations:**

*   **`lists` is `null` or empty:** Handled at the beginning, returns `null`.
*   **`lists` contains `null` or empty lists:** Handled by `if (list != null)` before adding to the heap. These lists effectively contribute nothing to the merge.
*   **Single list in `lists`:** The heap will effectively process only that one list, correctly returning it.
*   **All lists are empty:** The heap will remain empty after initialization, resulting in an empty merged list (`dummyHead.next` is `null`).

---

**Senior Discussion Points:**

*   **Comparison of Approaches:**
    *   **Brute Force (Sort all nodes):** Simple, but destroys linked list structure and is inefficient (O(N log N) time, O(N) space) for large datasets. Not suitable for streaming or memory-constrained scenarios.
    *   **Iterative Merging (Pairwise):** Better than brute force, but can be very inefficient (O(K * N) time) if lists are merged serially and intermediate lists grow very long.
    *   **Divide and Conquer:** An excellent recursive approach achieving O(N log K) time. It mirrors Merge Sort. It's preferred if you want a purely functional/recursive solution without external data structures like heaps.
    *   **Min-Heap:** Generally the most practical and efficient approach for this problem. It's clean, robust, and achieves the optimal O(N log K) time with reasonable O(K) space. It's particularly good for scenarios where `K` is relatively small compared to `N`.
*   **Heap vs. Divide & Conquer:** Both achieve the same optimal time complexity. The heap solution is often more intuitive for "pick the smallest from many sources" type problems. Divide & Conquer is more about breaking down a large problem into smaller, identical subproblems. The heap solution is more iterative and avoids deep recursion stack for very large `K` if `N` is also huge.
*   **Custom Comparator:** Demonstrates familiarity with Java's `PriorityQueue` and ability to define custom sorting logic using lambda expressions or anonymous classes.
*   **Dummy Head Node:** Explain its purpose to simplify list construction, avoiding special handling for the first node. This is a common pattern in linked list problems.
*   **Follow-up: Merge Two Sorted Lists:** This is a fundamental building block often asked as a simpler precursor.
*   **Real-world Applications:** Merging sorted data streams from different sources (e.g., sensor data, log files), combining results from multiple sorted database queries, multi-way merge in external sorting algorithms.

