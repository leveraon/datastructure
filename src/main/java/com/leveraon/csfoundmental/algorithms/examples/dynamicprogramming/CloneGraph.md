### **Problem 8: Clone Graph**

**Problem Statement:**
Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a `val` (int) and a list (`List[Node]`) of its neighbors.

**Graph Definition (for test cases):**
The graph is represented in the test case as an adjacency list. For example, `[[2,4],[1,3],[2,4],[1,3]]` means there are 4 nodes.
*   Node 1 is connected to Node 2 and Node 4.
*   Node 2 is connected to Node 1 and Node 3.
*   Node 3 is connected to Node 2 and Node 4.
*   Node 4 is connected to Node 1 and Node 3.
(Note: Node values are 1-indexed in problem examples for convenience, but internally can be 0-indexed or arbitrary).

**Example 1:**
Input: `adjList = [[2,4],[1,3],[2,4],[1,3]]`
Output: `[[2,4],[1,3],[2,4],[1,3]]`
Explanation: The graph is 4 nodes with connections as described above. We need to return a clone of it.

**Example 2:**
Input: `adjList = [[]]`
Output: `[[]]`
Explanation: Single node with no neighbors.

**Example 3:**
Input: `adjList = []`
Output: `[]`
Explanation: Empty graph (no nodes). This implies the initial `node` reference is null.

---

**Understanding & Clarification:**

*   **Graph Type:** Connected, undirected. Does it contain cycles? (Yes, typically, and must be handled).
*   **Node Structure:** `val` (int) and `List<Node> neighbors`.
*   **Deep Copy:** Crucial. This means creating *new* `Node` objects for all original nodes and correctly setting up their neighbor relationships between these new nodes. Not just copying values.
*   **Disconnected components:** Problem states "connected graph," so starting from any node allows reaching all others. If it *were* disconnected, one would need to iterate through all possible starting nodes (e.g., from a list of all nodes if available) and apply the clone logic to each unvisited component.
*   **Input `node`:** Given a reference to *a* node. Can it be `null`? (Yes, handle by returning `null`). The `val` can be arbitrary (not necessarily 1-indexed from examples).

---

**Approach:**

The core challenge in cloning a graph is to handle cycles and ensure that each original node maps to exactly one new cloned node. If we simply traverse and create new nodes without tracking, we might create duplicate nodes for the same original node or get stuck in an infinite loop due to cycles.

This problem is best solved using a traversal algorithm (BFS or DFS) combined with a `HashMap` to keep track of already cloned nodes.

1.  **High-Level Idea:**
    *   Maintain a `Map<Node, Node>` (e.g., `HashMap<OriginalNode, ClonedNode>`). This map will store the one-to-one mapping from an original node to its newly created clone. This is essential to prevent re-cloning nodes and handle cycles.
    *   Use a graph traversal (BFS or DFS) to systematically visit each node in the original graph.
    *   When visiting an `originalNode`:
        *   **Check `visitedMap`:** If `originalNode` has already been cloned (i.e., it's a key in the map), retrieve its existing `clonedNode` from the map.
        *   **Clone New Node:** If `originalNode` has *not* been cloned yet, create a `newClonedNode` (with `originalNode.val`). Add the pair `(originalNode, newClonedNode)` to the `visitedMap`.
        *   **Process Neighbors:** Iterate through `originalNode`'s neighbors. For each `originalNeighbor`:
            *   Recursively (if DFS) or iteratively (if BFS) ensure `originalNeighbor` is cloned (if not already).
            *   Add the `clonedNeighbor` to `newClonedNode`'s neighbors list.

2.  **Breadth-First Search (BFS) Approach:**
    *   Initialize `Map<Node, Node> visitedMap = new HashMap<>();`
    *   Initialize `Queue<Node> queue = new LinkedList<>();`
    *   **Start:** If the `givenNode` (the input) is `null`, return `null`. Otherwise, create a clone of `givenNode` (`clonedGivenNode = new Node(givenNode.val)`), put `(givenNode, clonedGivenNode)` into `visitedMap`, and add `givenNode` to the `queue`. This `clonedGivenNode` will be the root of our new graph and our final return value.
    *   **Traversal Loop:** While the `queue` is not empty:
        *   `originalNode = queue.poll();`
        *   `clonedNode = visitedMap.get(originalNode);` (This `clonedNode` must exist because we added it when `originalNode` was first encountered/cloned).
        *   **Process Neighbors:** For each `originalNeighbor` in `originalNode.neighbors`:
            *   **Check if Neighbor Cloned:** If `originalNeighbor` is *not* in `visitedMap` (meaning it hasn't been visited/cloned yet):
                *   Create its clone: `clonedNeighbor = new Node(originalNeighbor.val);`
                *   Add to map: `visitedMap.put(originalNeighbor, clonedNeighbor);`
                *   Add to queue: `queue.offer(originalNeighbor);`
            *   **Get Cloned Neighbor:** Retrieve the `clonedNeighbor` (either newly created or already existing) from the map: `clonedNeighbor = visitedMap.get(originalNeighbor);`
            *   **Connect Clones:** Add `clonedNeighbor` to the `clonedNode`'s neighbors list: `clonedNode.neighbors.add(clonedNeighbor);`

3.  **Depth-First Search (DFS) Approach:**
    *   Initialize `Map<Node, Node> visitedMap = new HashMap<>();`
    *   **Start:** If the `givenNode` is `null`, return `null`. Otherwise, call a recursive helper function, say `dfsClone(givenNode, visitedMap)`.
    *   **`dfsClone(originalNode, visitedMap)` function:**
        *   **Base Case/Memoization:** If `originalNode` is already in `visitedMap`, return `visitedMap.get(originalNode)`. This handles cycles and prevents re-cloning.
        *   **Clone Current Node:** Create `clonedNode = new Node(originalNode.val);`
        *   **Add to Map:** Put `(originalNode, clonedNode)` into `visitedMap`. This is crucial *before* processing neighbors to correctly handle cycles (i.e., if a neighbor refers back to `originalNode`, we already have its clone in the map).
        *   **Process Neighbors:** For each `originalNeighbor` in `originalNode.neighbors`:
            *   Recursively call `dfsClone(originalNeighbor, visitedMap)` to get its `clonedNeighbor`.
            *   Add `clonedNeighbor` to `clonedNode.neighbors`.
        *   Return `clonedNode`.

Both BFS and DFS are valid. BFS might be slightly more intuitive for explicit queue management, while DFS is more concise due to recursion. For a senior engineer, either is fine, but they should be able to articulate the use of the map to prevent cycles and duplicate nodes.

---

**Java Code Solution (DFS Approach):**

(Node class provided by problem usually)
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    // Optional: toString for easy printing/debugging of cloned graph
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node{val=").append(val).append(", neighbors=[");
        for (int i = 0; i < neighbors.size(); i++) {
            sb.append(neighbors.get(i).val);
            if (i < neighbors.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}


public class CloneGraph {

    // Map to store original node to its cloned counterpart.
    // This is crucial to avoid infinite loops with cycles and to ensure each original node is cloned exactly once.
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        // Base case: If the input node is null, return null (empty graph).
        if (node == null) {
            return null;
        }

        // If the node has already been visited (and thus cloned), return its clone from the map.
        // This handles cycles and prevents re-cloning.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a new clone for the current node.
        // The neighbors list is initialized as empty and will be populated recursively.
        Node cloneNode = new Node(node.val);

        // Store the mapping from original node to its clone immediately.
        // This is important BEFORE processing neighbors to handle self-loops or backward references in cycles correctly.
        visited.put(node, cloneNode);

        // Recursively clone the neighbors and build the cloneNode's neighbors list.
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor)); // Recursive call to clone neighbors
        }

        return cloneNode;
    }

    // --- Helper for creating test graphs and verifying clones ---
    // Note: Creating general graphs for testing manually can be tedious.
    // The problem statement implies graph is given as adjacency list, but actual input is a Node reference.
    // For local testing, we might need a builder.
    // Example Node values are 1-indexed, so we'll adjust for array indices
    public Node buildGraph(int[][] adjList) {
        if (adjList == null || adjList.length == 0) {
            return null;
        }

        // Create all nodes first based on their values (1 to N)
        // Store them in an array/map for easy access by value
        Map<Integer, Node> nodesByVal = new HashMap<>();
        for (int i = 1; i <= adjList.length; i++) {
            nodesByVal.put(i, new Node(i));
        }

        // Connect neighbors
        for (int i = 0; i < adjList.length; i++) {
            Node originalNode = nodesByVal.get(i + 1); // Get node by 1-indexed value
            for (int neighborVal : adjList[i]) {
                originalNode.neighbors.add(nodesByVal.get(neighborVal));
            }
        }
        return nodesByVal.get(1); // Return Node 1 as the starting point
    }

    // Helper to print graph (simple DFS print to show values and neighbors)
    public void printGraph(Node startNode) {
        if (startNode == null) {
            System.out.println("[]");
            return;
        }

        Map<Node, Boolean> visitedPrint = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.offer(startNode);
        visitedPrint.put(startNode, true);

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(node.val).append("]: ");
            List<Integer> neighborVals = new ArrayList<>();
            for (Node neighbor : node.neighbors) {
                neighborVals.add(neighbor.val);
                if (!visitedPrint.containsKey(neighbor)) {
                    visitedPrint.put(neighbor, true);
                    queue.offer(neighbor);
                }
            }
            sb.append(neighborVals);
            result.add(sb.toString());
        }
        System.out.println(String.join(", ", result));
    }


    public static void main(String[] args) {
        CloneGraph solver = new CloneGraph();

        // Example 1: [[2,4],[1,3],[2,4],[1,3]]
        int[][] adjList1 = {{2,4},{1,3},{2,4},{1,3}};
        Node graph1 = solver.buildGraph(adjList1);
        System.out.println("Original Graph 1:");
        solver.printGraph(graph1);
        Node clonedGraph1 = solver.cloneGraph(graph1);
        System.out.println("Cloned Graph 1:");
        // Reset visited map for next run
        solver.visited = new HashMap<>();
        solver.printGraph(clonedGraph1); // Expected to match original structure
        System.out.println("---");


        // Example 2: [[]] (Single node 1 with no neighbors)
        int[][] adjList2 = {{}};
        Node graph2 = solver.buildGraph(adjList2);
        System.out.println("Original Graph 2:");
        solver.printGraph(graph2);
        Node clonedGraph2 = solver.cloneGraph(graph2);
        System.out.println("Cloned Graph 2:");
        solver.visited = new HashMap<>(); // Reset visited map
        solver.printGraph(clonedGraph2); // Expected: [[1]: []]
        System.out.println("---");

        // Example 3: [] (null input node)
        Node graph3 = null;
        System.out.println("Original Graph 3: null");
        Node clonedGraph3 = solver.cloneGraph(graph3);
        System.out.println("Cloned Graph 3: " + (clonedGraph3 == null ? "null" : "Not null (Error)")); // Expected: null
        System.out.println("---");

        // Example: Graph with 2 nodes and a cycle [[2],[1]]
        int[][] adjList4 = {{2},{1}};
        Node graph4 = solver.buildGraph(adjList4);
        System.out.println("Original Graph 4:");
        solver.printGraph(graph4);
        Node clonedGraph4 = solver.cloneGraph(graph4);
        System.out.println("Cloned Graph 4:");
        solver.visited = new HashMap<>(); // Reset visited map
        solver.printGraph(clonedGraph4); // Expected: [[1]: [2], [2]: [1]]
        System.out.println("---");
    }
}
```
*(Self-correction: The `visited` map needs to be reset for each `cloneGraph` call if the `CloneGraph` object is reused, or it can be passed as a parameter in a helper function.) I've added `solver.visited = new HashMap<>();` reset in `main` for demonstration flexibility.*

---

**Complexity Analysis (for DFS Approach):**

*   **Time Complexity:** O(V + E)
    *   `V` is the number of nodes (vertices).
    *   `E` is the number of edges.
    *   Each node is visited once during the DFS traversal.
    *   For each node, we iterate through its neighbors. In an undirected graph, each edge (u, v) will be traversed twice (once from u, once from v), but overall work is proportional to the number of edges.
    *   `HashMap` operations (`containsKey`, `put`, `get`) take O(1) on average.

*   **Space Complexity:** O(V)
    *   The `HashMap` (`visited`) stores `V` entries (one mapping for each original node to its clone).
    *   The recursion stack for DFS can go up to O(V) in the worst case (a long chain-like graph). A BFS approach would use a queue that stores up to O(V) nodes.

---

**Edge Cases & Considerations:**

*   **Null input node:** Handled by returning `null`.
*   **Single node graph (no neighbors):** Works correctly. The clone will have the same value and an empty neighbors list.
*   **Graph with only two nodes and a single edge:** Handled correctly.
*   **Graph with cycles:** The `visited` map is critical here. By storing the clone immediately after creation