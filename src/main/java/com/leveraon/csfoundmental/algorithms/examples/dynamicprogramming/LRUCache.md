### 3. **Design an LRU Cache**

*   **Problem:** Design a data structure that follows the constraints of a Least Recently Used (LRU) cache. Implement the `LRUCache` class:
    *   `LRUCache(int capacity)` initializes the LRU cache with the given positive `capacity`.
    *   `int get(int key)`: Return the value of the `key` if the key exists, otherwise return -1.
    *   `void put(int key, int value)`: Update the value of the `key` if the `key` exists. Otherwise, add the `key-value` pair to the cache. If the number of keys exceeds the `capacity` from this operation, evict the least recently used key.
    *   `get` and `put` operations should both run in O(1) average time complexity.
*   **DSA Focus:** Hash Maps (Dictionaries), Doubly Linked Lists.
*   **Senior-Level Discussion Points:**
    *   Why a simple array/list isn't sufficient (O(N) for operations).
    *   Why a `HashMap` is needed for O(1) `get` and `put` key lookups.
    *   Why a `Doubly Linked List` is needed to maintain order of usage and allow O(1) removal/addition from either end.
    *   How to link the `HashMap` and `Doubly Linked List` (e.g., hash map stores `key -> Node` pointers).
    *   Detailed explanation of `get` and `put` logic, including moving elements to the front (most recently used) and handling eviction.
    *   Edge cases: cache capacity 1, adding duplicates, adding when full.

---
### Solution: Design an LRU Cache

**Problem:** Design a data structure that follows the constraints of a Least Recently Used (LRU) cache. Implement the `LRUCache` class:
*   `LRUCache(int capacity)`: Initializes the LRU cache with the given positive `capacity`.
*   `int get(int key)`: Return the value of the `key` if the `key` exists, otherwise return -1.
*   `void put(int key, int value)`: Update the value of the `key` if the `key` exists. Otherwise, add the `key-value` pair to the cache. If the number of keys exceeds the `capacity` from this operation, evict the least recently used key.
*   `get` and `put` operations should both run in O(1) average time complexity.

**Approach:**
To achieve O(1) time complexity for both `get` and `put` operations, an LRU cache is typically implemented using a combination of two data structures:

1.  **`HashMap<Integer, Node>`:** To provide O(1) average time lookup for keys and quick access to the corresponding node in the linked list. The map stores `key -> Node` where `Node` is an object representing a key-value pair.
2.  **`Doubly Linked List`:** To maintain the order of usage.
    *   The **most recently used (MRU)** items are at one end (e.g., head).
    *   The **least recently used (LRU)** items are at the other end (e.g., tail).
    *   A doubly linked list allows for O(1) removal of an arbitrary node and O(1) insertion at either end.

**Detailed Operations:**

*   **`Node` Class:** Define a private inner `Node` class to hold `key`, `value`, `prev`, and `next` pointers. This is crucial because `HashMap` stores `key -> Node` references, allowing us to directly access and manipulate nodes in the linked list.
*   **`get(key)`:**
    1.  Check if `key` exists in the `HashMap`. If not, return -1.
    2.  If `key` exists, retrieve its `Node`.
    3.  Since this item was just accessed, it becomes the MRU. Move this `Node` to the head of the doubly linked list.
    4.  Return the `value` from the `Node`.
*   **`put(key, value)`:**
    1.  Check if `key` already exists in the `HashMap`:
        *   If yes: Update the `value` of the existing `Node`. Move this `Node` to the head of the doubly linked list.
        *   If no:
            1.  Create a new `Node` with the given `key` and `value`.
            2.  Add this new `Node` to the head of the doubly linked list.
            3.  Add the `key -> Node` mapping to the `HashMap`.
            4.  **Check Capacity:** If the cache size now exceeds its `capacity`:
                *   Remove the `tail` node from the doubly linked list (this is the LRU item).
                *   Remove the corresponding entry from the `HashMap`.

**Helper Methods for Doubly Linked List:**
To keep the `get` and `put` methods clean, it's good practice to encapsulate common linked list operations:
*   `addNode(Node node)`: Adds a node right after the `head` (making it MRU).
*   `removeNode(Node node)`: Removes a given node from the list.
*   `moveToHead(Node node)`: Combines `removeNode` and `addNode` to move an existing node to the head.
*   `popTail()`: Removes and returns the `tail` node (LRU).

**Time Complexity:** O(1) for both `get` and `put` operations on average, due to `HashMap` lookups and `Doubly LinkedList` manipulations.
**Space Complexity:** O(Capacity), as we store at most `capacity` key-value pairs in both the `HashMap` and the `Doubly LinkedList`.

**Java Code:**

```java
import java.util.HashMap;
import java.util.Map;

class LRUCache {

    // Node class for the Doubly Linked List
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> cache; // HashMap to store key -> Node mapping
    private int capacity;
    private int size; // Current size of the cache

    // Dummy head and tail nodes for the doubly linked list
    // This simplifies operations, avoiding null checks for head/tail.
    private Node head; // Points to the most recently used (MRU) node
    private Node tail; // Points to the least recently used (LRU) node

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();

        // Initialize dummy head and tail
        head = new Node(0, 0); // Dummy node
        tail = new Node(0, 0); // Dummy node
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Retrieves the value associated with a key.
     * Moves the accessed node to the head (MRU).
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1; // Key not found
        }

        // Move the accessed node to the head (most recently used)
        moveToHead(node);
        return node.value;
    }

    /**
     * Inserts or updates a key-value pair.
     * Handles capacity eviction if necessary.
     */
    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // Key already exists, update value and move to head
            node.value = value;
            moveToHead(node);
        } else {
            // Key does not exist, create new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode); // Add to map
            addNode(newNode); // Add to list (at head)
            size++;

            // Check capacity and evict LRU if needed
            if (size > capacity) {
                Node lruNode = popTail(); // Remove LRU node (from tail)
                cache.remove(lruNode.key); // Remove from map
                size--;
            }
        }
    }

    // --- Doubly Linked List Helper Methods ---

    // Add a new node right after the head (MRU position)
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Remove an existing node from the list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Move an existing node to the head (MRU position)
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    // Remove the node just before the tail (LRU position)
    private Node popTail() {
        Node res = tail.prev; // This is the actual LRU node
        removeNode(res);
        return res;
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        System.out.println("LRU Cache Test 1 (Capacity 2):");
        LRUCache lruCache1 = new LRUCache(2);
        lruCache1.put(1, 1); // cache = {1=1}
        lruCache1.put(2, 2); // cache = {1=1, 2=2}
        System.out.println("get(1): " + lruCache1.get(1)); // returns 1 (MRU: 1)
        lruCache1.put(3, 3); // LRU key 2 is evicted, cache = {1=1, 3=3} (MRU: 3)
        System.out.println("get(2): " + lruCache1.get(2)); // returns -1 (2 not found)
        lruCache1.put(4, 4); // LRU key 1 is evicted, cache = {3=3, 4=4} (MRU: 4)
        System.out.println("get(1): " + lruCache1.get(1)); // returns -1 (1 not found)
        System.out.println("get(3): " + lruCache1.get(3)); // returns 3 (MRU: 3)
        System.out.println("get(4): " + lruCache1.get(4)); // returns 4 (MRU: 4)

        System.out.println("\nLRU Cache Test 2 (Capacity 1):");
        LRUCache lruCache2 = new LRUCache(1);
        lruCache2.put(1, 10); // cache = {1=10}
        System.out.println("get(1): " + lruCache2.get(1)); // returns 10
        lruCache2.put(2, 20); // LRU key 1 is evicted, cache = {2=20}
        System.out.println("get(1): " + lruCache2.get(1)); // returns -1
        System.out.println("get(2): " + lruCache2.get(2)); // returns 20

        System.out.println("\nLRU Cache Test 3 (Capacity 0 - effectively empty):");
        LRUCache lruCache3 = new LRUCache(0); // Should handle capacity 0 gracefully (or throw IllegalArgumentException)
        lruCache3.put(1, 100); // Nothing added
        System.out.println("get(1): " + lruCache3.get(1)); // returns -1

        System.out.println("\nLRU Cache Test 4 (Capacity 3, duplicates and updates):");
        LRUCache lruCache4 = new LRUCache(3);
        lruCache4.put(1, 10); // {1=10}
        lruCache4.put(2, 20); // {1=10, 2=20}
        lruCache4.put(3, 30); // {1=10, 2=20, 3=30}
        System.out.println("get(2): " + lruCache4.get(2)); // {1=10, 3=30, 2=20} (2 is now MRU)
        lruCache4.put(4, 40); // {3=30, 2=20, 4=40} (1 evicted)
        System.out.println("get(1): " + lruCache4.get(1)); // -1
        System.out.println("get(3): " + lruCache4.get(3)); // {2=20, 4=40, 3=30} (3 is now MRU)
        lruCache4.put(2, 25); // {4=40, 3=30, 2=25} (2 updated, moved to MRU)
        System.out.println("get(2): " + lruCache4.get(2)); // 25
    }
}
```

**Senior-Level Considerations:**

*   **Choice of Data Structures:** Clearly articulate *why* a `HashMap` and a `Doubly Linked List` are chosen. `HashMap` for O(1) key lookup, `Doubly Linked List` for O(1) node removal and insertion anywhere in the list.
*   **Dummy Nodes:** Explain the benefit of using dummy `head` and `tail` nodes. They simplify the logic for `addNode`, `removeNode`, and `popTail` by eliminating special null checks for empty lists or single-node lists, making the code cleaner and less error-prone.
*   **`Node` Class Design:** Emphasize why the `Node` needs to store both `key` and `value`. The `HashMap` maps `key` to `Node`, so when evicting a node from the tail, you need its `key` to remove it from the `HashMap`.
*   **Edge Cases:** Discuss how `capacity = 1` or `capacity = 0` (though typically capacity is positive) are handled. The current code handles `capacity = 0` by effectively preventing any elements from being added.
*   **Thread Safety:** Mention that this implementation is *not* thread-safe. For a multi-threaded environment, you'd need to use `synchronized` blocks or `java.util.concurrent` constructs (like `ReentrantLock`) for thread safety. `LinkedHashMap` is also an alternative if concurrency is not a concern, as it has built-in LRU policy support.

---