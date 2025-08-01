package com.leveraon.csfoundmental.algorithms.examples.graph;

import com.leveraon.csfoundmental.datastructure.graph.Graph;

public class GraphTraversal {

	// --- Main Method for Demonstration ---
	public static void main(String[] args) {
		Graph<String> cityGraph = new Graph<>();

		// Add Vertices
		cityGraph.addVertex("New York");
		cityGraph.addVertex("London");
		cityGraph.addVertex("Paris");
		cityGraph.addVertex("Tokyo");
		cityGraph.addVertex("Rome");
		cityGraph.addVertex("Berlin");
		cityGraph.addVertex("Sydney"); // Add a disconnected vertex to show traversal limits

		// Add Edges (representing flights between cities)
		cityGraph.addEdge("New York", "London");
		cityGraph.addEdge("New York", "Paris");
		cityGraph.addEdge("London", "Paris");
		cityGraph.addEdge("London", "Rome");
		cityGraph.addEdge("Paris", "Berlin");
		cityGraph.addEdge("Berlin", "Rome");
		cityGraph.addEdge("Tokyo", "London"); // Add a link to an existing part of the graph

		// Print the graph structure
		cityGraph.printGraph();

		// Check for vertex and edge existence
		System.out.println("Does graph have 'New York'? " + cityGraph.hasVertex("New York")); // true
		System.out.println("Does graph have 'Moscow'? " + cityGraph.hasVertex("Moscow")); // false
		System.out.println("Does graph have edge 'London'--'Rome'? " + cityGraph.hasEdge("London", "Rome")); // true
		System.out.println("Does graph have edge 'New York'--'Tokyo'? " + cityGraph.hasEdge("New York", "Tokyo")); // false

		// Get neighbors
		System.out.println("\nNeighbors of London: " + cityGraph.getNeighbors("London"));
		System.out.println("Neighbors of Sydney: " + cityGraph.getNeighbors("Sydney"));

		// Perform BFS traversal
		cityGraph.bfs("New York");
		cityGraph.bfs("Sydney"); // BFS from a disconnected node

		// Perform DFS traversal
		cityGraph.dfs("New York");
		cityGraph.dfs("Sydney"); // DFS from a disconnected node

		// Example with Integer vertices
		Graph<Integer> intGraph = new Graph<>();
		intGraph.addEdge(0, 1);
		intGraph.addEdge(0, 2);
		intGraph.addEdge(1, 3);
		intGraph.addEdge(2, 4);
		intGraph.addEdge(3, 4);
		intGraph.addEdge(4, 5);
		intGraph.addVertex(6); // Isolated vertex
		intGraph.printGraph();
		intGraph.bfs(0);
		intGraph.dfs(0);
	}
}
