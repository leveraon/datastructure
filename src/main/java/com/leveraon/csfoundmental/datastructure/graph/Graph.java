package com.leveraon.csfoundmental.datastructure.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * A generic Graph data structure implemented using an Adjacency List. This
 * implementation supports undirected graphs. For a directed graph, simply
 * remove the line that adds the reverse edge in addEdge method. For a weighted
 * graph, modify the adjacency list to store pairs (neighbor, weight) or a
 * custom Edge object.
 *
 * @param <V> The type of the vertex (e.g., Integer, String, custom objects).
 */
public class Graph<V> {

	// Adjacency list representation:
	// A map where each key is a vertex, and its value is a list of its adjacent
	// vertices.
	private Map<V, List<V>> adjList;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		adjList = new HashMap<>();
	}

	/**
	 * Adds a new vertex to the graph. If the vertex already exists, nothing
	 * happens.
	 *
	 * @param vertex The vertex to be added.
	 */
	public void addVertex(V vertex) {
		adjList.putIfAbsent(vertex, new ArrayList<>());
		System.out.println("Added vertex: " + vertex);
	}

	/**
	 * Adds an edge between two vertices. If either vertex does not exist, it will
	 * be added. This method assumes an UNDIRECTED graph, so it adds edges in both
	 * directions. For a directed graph, remove the second add operation.
	 *
	 * @param source      The source vertex.
	 * @param destination The destination vertex.
	 */
	public void addEdge(V source, V destination) {
		// Ensure both vertices exist in the graph
		addVertex(source);
		addVertex(destination);

		// Add edge from source to destination
		adjList.get(source).add(destination);
		// Add edge from destination to source (for undirected graph)
		adjList.get(destination).add(source);
		System.out.println("Added edge: " + source + " -- " + destination);
	}

	/**
	 * Checks if a vertex exists in the graph.
	 *
	 * @param vertex The vertex to check.
	 * @return true if the vertex exists, false otherwise.
	 */
	public boolean hasVertex(V vertex) {
		return adjList.containsKey(vertex);
	}

	/**
	 * Checks if an edge exists between two vertices.
	 *
	 * @param source      The source vertex.
	 * @param destination The destination vertex.
	 * @return true if an edge exists, false otherwise.
	 */
	public boolean hasEdge(V source, V destination) {
		if (!hasVertex(source)) {
			return false;
		}
		return adjList.get(source).contains(destination);
	}

	/**
	 * Returns the list of neighbors for a given vertex.
	 *
	 * @param vertex The vertex whose neighbors are requested.
	 * @return A list of adjacent vertices, or an empty list if the vertex doesn't
	 *         exist.
	 */
	public List<V> getNeighbors(V vertex) {
		return adjList.getOrDefault(vertex, Collections.emptyList());
	}

	/**
	 * Prints the graph's adjacency list representation.
	 */
	public void printGraph() {
		System.out.println("\n--- Graph Adjacency List ---");
		if (adjList.isEmpty()) {
			System.out.println("Graph is empty.");
			return;
		}
		for (Map.Entry<V, List<V>> entry : adjList.entrySet()) {
			System.out.print(entry.getKey() + " -> ");
			System.out.println(entry.getValue());
		}
		System.out.println("----------------------------\n");
	}

	// --- Graph Traversal Algorithms ---

	/**
	 * Performs a Breadth-First Search (BFS) starting from a given vertex. Prints
	 * the vertices in BFS order.
	 *
	 * @param startVertex The vertex to start the BFS from.
	 */
	public void bfs(V startVertex) {
		if (!hasVertex(startVertex)) {
			System.out.println("BFS: Start vertex " + startVertex + " not found.");
			return;
		}

		System.out.println("\n--- BFS Traversal (starting from " + startVertex + ") ---");
		Queue<V> queue = new LinkedList<>();
		Set<V> visited = new HashSet<>(); // To keep track of visited vertices

		queue.offer(startVertex); // Add the starting vertex to the queue
		visited.add(startVertex); // Mark it as visited

		while (!queue.isEmpty()) {
			V current = queue.poll(); // Get the next vertex from the queue
			System.out.print(current + " "); // Process the current vertex

			// Add all unvisited neighbors to the queue
			for (V neighbor : adjList.get(current)) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.offer(neighbor);
				}
			}
		}
		System.out.println("\n-----------------------------------------\n");
	}

	/**
	 * Performs a Depth-First Search (DFS) starting from a given vertex (recursive
	 * approach). Prints the vertices in DFS order.
	 *
	 * @param startVertex The vertex to start the DFS from.
	 */
	public void dfs(V startVertex) {
		if (!hasVertex(startVertex)) {
			System.out.println("DFS: Start vertex " + startVertex + " not found.");
			return;
		}

		System.out.println("\n--- DFS Traversal (starting from " + startVertex + ") ---");
		Set<V> visited = new HashSet<>(); // To keep track of visited vertices
		dfsRecursiveHelper(startVertex, visited);
		System.out.println("\n-----------------------------------------\n");
	}

	private void dfsRecursiveHelper(V currentVertex, Set<V> visited) {
		visited.add(currentVertex); // Mark current vertex as visited
		System.out.print(currentVertex + " "); // Process the current vertex

		// Recursively visit all unvisited neighbors
		for (V neighbor : adjList.get(currentVertex)) {
			if (!visited.contains(neighbor)) {
				dfsRecursiveHelper(neighbor, visited);
			}
		}
	}

}
