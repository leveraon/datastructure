### 6. **Serialize and Deserialize Binary Tree**

*   **Problem:** Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
*   **DSA Focus:** Trees, Recursion (DFS), BFS (Level Order Traversal), String manipulation.
*   **Senior-Level Discussion Points:**
    *   Choosing a traversal strategy (Pre-order, In-order, Post-order, Level-order). Why pre-order is often preferred for serialization *if you also store null nodes*.
    *   How to handle `null` nodes in the serialization string (e.g., using a special marker like "#").
    *   Delimiters between node values.
    *   Recursive (DFS) serialization and deserialization logic.
    *   Iterative (BFS) serialization and deserialization (less common but possible).
    *   Edge cases: empty tree, single node tree, skewed tree.
    *   Time and Space complexity of both processes.
    *   What happens if you don't store nulls explicitly (requires both pre-order and in-order to reconstruct).

---
### Solution: Serialize and Deserialize Binary Tree

**Problem:** Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

**Approach:**
A robust and commonly used approach for serializing a binary tree involves using a **Pre-order Traversal (DFS)** and explicitly marking `null` nodes. This allows for unambiguous reconstruction of the tree structure during deserialization.

**Serialization Process:**

1.  **Traversal Type:** We'll use Depth-First Search (DFS) with a pre-order traversal (Root -> Left -> Right).
2.  **Node Representation:**
    *   For a non-null node, append its integer value to a `StringBuilder`.
    *   For a null node, append a special marker (e.g., `"N"` or `"#"`).
3.  **Delimiter:** Use a consistent delimiter (e.g., a comma `,`) to separate node values or null markers in the string.
4.  **Building the String:** Recursively traverse the tree. At each step, append the current node's value (or "N" for null) and the delimiter.

**Example Serialization:**
Tree:
```
    1
   / \
  2   3
     / \
    4   5
```
Serialized String: `"1,2,N,N,3,4,N,N,5,N,N,"`

**Deserialization Process:**

1.  **Parse String:** The input string is split by the delimiter into an array of strings.
2.  **Data Structure for Consumption:** Convert this array into a `Queue` (like a `LinkedList`) or use an index/iterator. A `Queue` is convenient as `poll()` naturally processes elements in the order they were serialized.
3.  **Reconstruction:** Recursively build the tree:
    *   Dequeue the next string from the queue.
    *   If it's the `null` marker, return `null`.
    *   Otherwise, parse the string to an integer, create a new `TreeNode` with this value.
    *   Recursively call the deserialization function for the left child.
    *   Recursively call the deserialization function for the right child.
    *   Attach the returned left and right subtrees to the current node.
    *   Return the current node.

**Time Complexity:**
*   **Serialization:** O(N), where N is the number of nodes in the tree. Each node is visited exactly once. Appending to `StringBuilder` is amortized O(1).
*   **Deserialization:** O(N). Each element in the parsed string (which corresponds to a node or null pointer) is processed exactly once.

**Space Complexity:**
*   **Serialization:** O(N) for the `StringBuilder` to store the output string, and O(H) for the recursion stack, where H is the height of the tree (worst case O(N) for skewed tree).
*   **Deserialization:** O(N) for the `Queue` (to store all string tokens) and O(H) for the recursion stack (worst case O(N) for skewed tree).

---

**Java Code:**

First, define the `TreeNode` class (if not provided):

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
```

Now, the `Codec` class for serialization and deserialization:

```java
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Delimiter to separate node values in the serialized string
    private static final String DELIMITER = ",";
    // Marker for null nodes
    private static final String NULL_NODE_MARKER = "N";

    /**
     * Encodes a tree to a single string.
     * Uses Pre-order DFS traversal.
     *
     * @param root The root of the binary tree.
     * @return A string representation of the tree.
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        return sb.toString();
    }

    private void dfsSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_NODE_MARKER).append(DELIMITER);
            return;
        }
        sb.append(node.val).append(DELIMITER);
        dfsSerialize(node.left, sb);
        dfsSerialize(node.right, sb);
    }

    /**
     * Decodes your encoded data to tree.
     * Uses Pre-order DFS traversal for reconstruction.
     *
     * @param data The serialized string of the tree.
     * @return The root of the reconstructed binary tree.
     */
    public TreeNode deserialize(String data) {
        // Handle empty or invalid input string if necessary, though problem usually implies valid format.
        if (data == null || data.isEmpty()) {
            return null;
        }

        // Split the string by the delimiter.
        // The last element might be an empty string if data ends with DELIMITER,
        // so we filter it out or use substring/index carefully.
        // Using `split` without a limit can sometimes produce an empty string at the end.
        // Example: "1,N,N," -> ["1", "N", "N", ""]
        // Using a negative limit preserves trailing empty strings if any, which is generally not an issue for `N`.
        String[] nodesArray = data.split(DELIMITER, -1); 
        
        // Use a Queue to process nodes in order
        Queue<String> nodesQueue = new LinkedList<>(Arrays.asList(nodesArray));
        
        return dfsDeserialize(nodesQueue);
    }

    private TreeNode dfsDeserialize(Queue<String> nodesQueue) {
        // If the queue is empty, it means we've consumed all valid tokens unexpectedly
        // or there was a malformed string. Based on serialization, this shouldn't happen
        // unless the initial string was empty or incorrectly formed.
        if (nodesQueue.isEmpty()) {
            return null; 
        }

        String val = nodesQueue.poll();

        if (val.equals(NULL_NODE_MARKER)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = dfsDeserialize(nodesQueue);
        node.right = dfsDeserialize(nodesQueue);

        return node;
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();

        // Helper to print tree for verification (simple in-order for quick check)
        // For actual verification, one would compare serialized strings or perform deep equality check.
        Runnable printTreeInOrder = (treeNode) -> {
            System.out.print("Tree (In-Order): ");
            printInOrder(treeNode);
            System.out.println();
        };

        // Test Case 1: Standard tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        String serialized1 = ser.serialize(root1);
        System.out.println("Original Tree 1:");
        printTreeInOrder.run(root1);
        System.out.println("Serialized: " + serialized1);
        TreeNode deserialized1 = deser.deserialize(serialized1);
        System.out.println("Deserialized Tree 1:");
        printTreeInOrder.run(deserialized1);
        System.out.println("---");


        // Test Case 2: Empty tree
        TreeNode root2 = null;
        String serialized2 = ser.serialize(root2);
        System.out.println("Original Tree 2 (Empty):");
        printTreeInOrder.run(root2);
        System.out.println("Serialized: " + serialized2); // Expected: "N,"
        TreeNode deserialized2 = deser.deserialize(serialized2);
        System.out.println("Deserialized Tree 2:");
        printTreeInOrder.run(deserialized2); // Expected: empty line or "null" indicator
        System.out.println("---");

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(42);
        String serialized3 = ser.serialize(root3);
        System.out.println("Original Tree 3 (Single Node):");
        printTreeInOrder.run(root3);
        System.out.println("Serialized: " + serialized3); // Expected: "42,N,N,"
        TreeNode deserialized3 = deser.deserialize(serialized3);
        System.out.println("Deserialized Tree 3:");
        printTreeInOrder.run(deserialized3);
        System.out.println("---");

        // Test Case 4: Skewed tree (left)
        TreeNode root4 = new TreeNode(10);
        root4.left = new TreeNode(20);
        root4.left.left = new TreeNode(30);
        String serialized4 = ser.serialize(root4);
        System.out.println("Original Tree 4 (Left Skewed):");
        printTreeInOrder.run(root4);
        System.out.println("Serialized: " + serialized4); // Expected: "10,20,30,N,N,N,N,"
        TreeNode deserialized4 = deser.deserialize(serialized4);
        System.out.println("Deserialized Tree 4:");
        printTreeInOrder.run(deserialized4);
        System.out.println("---");
    }

    // Simple in-order traversal for verification (for main method's output)
    private static void printInOrder(TreeNode node) {
        if (node == null) {
            System.out.print("N "); // Indicate nulls explicitly during print for clarity
            return;
        }
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }
}
```

---

**Senior-Level Considerations and Discussion Points:**

1.  **Choice of Traversal:**
    *   **Pre-order (DFS):** As implemented. It's often the most straightforward because the root is always the first element encountered, followed by its children in a predictable order (left then right). The null markers are crucial for reconstructing the exact structure.
    *   **Level-order (BFS):** Also a good choice. Serialize by adding nodes to a queue level by level, explicitly adding `null` for missing children. Deserialization involves processing elements from the string/queue and linking them level by level. This can sometimes be more complex to implement recursively.
    *   **In-order/Post-order alone:** Neither in-order nor post-order traversal alone is sufficient to uniquely reconstruct a binary tree without `null` markers because they don't capture parent-child relationships unambiguously (e.g., you can't tell if a node is a left or right child without context). If `null` markers are explicitly added (making them full traversals), they would work, but pre-order is generally simpler. If you *don't* explicitly mark nulls, you'd typically need *two* traversals (e.g., pre-order and in-order) to reconstruct.

2.  **Importance of Null Markers:**
    *   Crucial for preserving the exact tree structure. Without them, you couldn't distinguish between a tree `1 -> 2` (1 has left child 2, right is null) and `1 -> 2` (1 has right child 2, left is null).
    *   They define the "boundaries" of subtrees.

3.  **Delimiter Choice:**
    *   A comma (`,`) is common. Ensure the delimiter character does not appear within valid node values. If node values could contain commas, a more robust delimiter (e.g., `,"`, or fixed-width values) or a different serialization format (e.g., JSON, XML) would be needed.

4.  **Handling Edge Cases:**
    *   **Empty Tree:** Serializes to `"N,"` (or similar). Deserializes back to `null`.
    *   **Single Node Tree:** Serializes to `"val,N,N,"`. Deserializes correctly.
    *   **Skewed Trees:** Handled correctly because `null` children are explicitly marked, preserving the depth and structure.

5.  **Performance Considerations:**
    *   **`StringBuilder` vs. String Concatenation:** Using `StringBuilder` for serialization is efficient for building the string, avoiding repeated object creation of `String` during concatenation.
    *   **`String.split()`:** While convenient, `split()` creates a new array. For very large trees, this could be a memory concern. An alternative for deserialization could be to use a `StringTokenizer` or manually parse the string using `indexOf()` to avoid creating the full array upfront, though this adds complexity.
    *   **`Integer.parseInt()`:** This can throw `NumberFormatException` if a non-numeric string is encountered (e.g., if a value accidentally used the `NULL_NODE_MARKER` or if the input string is corrupted). In a production system, robust error handling would be necessary.

6.  **Extensibility:**
    *   How would this change if node values could be strings, or doubles? The `Integer.parseInt()` would need to be `Double.parseDouble()` or just taken as `String` and handled accordingly.
    *   What if the tree nodes had more properties (e.g., parent pointers, colors for Red-Black trees)? Serialization would need to include these properties, and deserialization would need to reconstruct them, which might require a second pass or more complex recursive arguments.

This solution provides a solid foundation for the problem, covering the core requirements and demonstrating good practices for a senior-level candidate.