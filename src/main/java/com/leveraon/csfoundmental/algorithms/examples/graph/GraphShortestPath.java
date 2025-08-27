/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.leveraon.csfoundmental.datastructure.graph.Graph;

/**
 * 
 */
public class GraphShortestPath {
	/**
	 * Performs a Breadth-First Search from a start node to find a target node
	 * and returns the shortest path if found.
	 *
	 * @param graph
	 *            The graph to search within.
	 * @param startNode
	 *            The node to start the search from.
	 * @param targetNode
	 *            The node to search for.
	 * @return A list of integers representing the shortest path from startNode
	 *         to targetNode. Returns an empty list if no path exists or if
	 *         start/target nodes are not in the graph.
	 */
	public List<Integer> findShortestPath(Graph<Integer> graph, int startNode, int targetNode) {
		// --- 1. Basic Checks ---
		if (!graph.hasVertex(startNode) || !graph.hasVertex(targetNode)) {
			System.out.println("Error: Start or target node not in graph.");
			return Collections.emptyList();
		}

		if (startNode == targetNode) {
			return Collections.singletonList(startNode); // Path is just the
															// node itself
		}

		// --- 2. Data Structures for BFS ---
		Queue<Integer> queue = new LinkedList<>(); // Stores nodes to visit
													// (FIFO)
		Set<Integer> visited = new HashSet<>(); // Stores visited nodes to
												// prevent cycles and
												// re-processing
		Map<Integer, Integer> parentMap = new HashMap<>(); // Stores parent of
															// each node for
															// path
															// reconstruction

		// --- 3. Initialization ---
		queue.offer(startNode); // Add start node to the queue
		visited.add(startNode); // Mark start node as visited
		parentMap.put(startNode, -1); // Start node has no parent (-1 indicates
										// root/no parent)

		// --- 4. BFS Traversal Loop ---
		while (!queue.isEmpty()) {
			int currentNode = queue.poll(); // Get the next node from the queue

			// Check if we reached the target node
			if (currentNode == targetNode) {
				System.out.println("Target node " + targetNode + " found!");
				return reconstructPath(parentMap, startNode, targetNode);
			}

			// Explore neighbors
			for (int neighbor : graph.getNeighbors(currentNode)) {
				if (!visited.contains(neighbor)) { // If neighbor hasn't been
													// visited
					visited.add(neighbor); // Mark it as visited
					parentMap.put(neighbor, currentNode); // Set current node as
															// its parent
					queue.offer(neighbor); // Add it to the queue for future
											// processing
				}
			}
		}

		// --- 5. Target Not Found ---
		System.out.println("Target node " + targetNode + " not reachable from " + startNode);
		return Collections.emptyList(); // Path not found
	}

	/**
	 * Helper method to reconstruct the path from the parentMap.
	 */
	private List<Integer> reconstructPath(Map<Integer, Integer> parentMap, int startNode, int targetNode) {
		List<Integer> path = new LinkedList<>(); // Use LinkedList for efficient
													// add(0, element)
		Integer current = targetNode;

		while (current != null && current != -1) { // -1 is our sentinel for the
													// start node's parent
			path.add(0, current); // Add to the beginning to build path in
									// correct order
			current = parentMap.get(current);
		}

		// Check if the path actually starts with the startNode (i.e., it was
		// reachable)
		if (path.isEmpty() || path.get(0) != startNode) {
			return Collections.emptyList(); // Path reconstruction failed (e.g.,
											// target not reachable from start)
		}
		return path;
	}

	// --- Main method for demonstration ---
	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>();

		// Add nodes
		for (int i = 0; i <= 9; i++) {
			graph.addVertex(i);
		}

		// Add edges to create a sample graph
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 5);
		graph.addEdge(2, 6);
		graph.addEdge(3, 7);
		graph.addEdge(4, 8);
		graph.addEdge(5, 9);
		graph.addEdge(7, 8); // Cycle

		// Let's create a disconnected component too
		graph.addVertex(10);
		graph.addVertex(11);
		graph.addEdge(10, 11);

		GraphShortestPath bfs = new GraphShortestPath();

		System.out.println("--- Test Case 1: Find path from 0 to 8 ---");
		List<Integer> path1 = bfs.findShortestPath(graph, 0, 8);
		System.out.println("Path from 0 to 8: " + (path1.isEmpty() ? "No path found" : path1)); // Expected:
																								// [0,
																								// 1,
																								// 4,
																								// 8]

		System.out.println("\n--- Test Case 2: Find path from 0 to 9 ---");
		List<Integer> path2 = bfs.findShortestPath(graph, 0, 9);
		System.out.println("Path from 0 to 9: " + (path2.isEmpty() ? "No path found" : path2)); // Expected:
																								// [0,
																								// 2,
																								// 5,
																								// 9]

		System.out.println("\n--- Test Case 3: Start and target are same ---");
		List<Integer> path3 = bfs.findShortestPath(graph, 5, 5);
		System.out.println("Path from 5 to 5: " + (path3.isEmpty() ? "No path found" : path3)); // Expected:
																								// [5]

		System.out.println("\n--- Test Case 4: Target not reachable (disconnected component) ---");
		List<Integer> path4 = bfs.findShortestPath(graph, 0, 11);
		System.out.println("Path from 0 to 11: " + (path4.isEmpty() ? "No path found" : path4)); // Expected:
																									// No
																									// path
																									// found

		System.out.println("\n--- Test Case 5: Node not in graph ---");
		List<Integer> path5 = bfs.findShortestPath(graph, 0, 99);
		System.out.println("Path from 0 to 99: " + (path5.isEmpty() ? "No path found" : path5)); // Expected:
																									// Error
																									// message
																									// +
																									// No
																									// path
																									// found

		System.out.println("\n--- Test Case 6: Path in reverse direction ---");
		List<Integer> path6 = bfs.findShortestPath(graph, 8, 0);
		System.out.println("Path from 8 to 0: " + (path6.isEmpty() ? "No path found" : path6)); // Expected:
																								// [8,
																								// 4,
																								// 1,
																								// 0]
																								// or
																								// [8,
																								// 7,
																								// 3,
																								// 1,
																								// 0]
																								// (BFS
																								// gives
																								// *a*
																								// shortest,
																								// not
																								// necessarily
																								// *the
																								// only*
																								// shortest)
																								// Depending
																								// on
																								// adjacency
																								// list
																								// iteration
																								// order,
																								// it
																								// could
																								// be
																								// [8,4,1,0]
																								// or
																								// [8,7,3,1,0].
																								// Both
																								// are
																								// length
																								// 4.
	}
}
