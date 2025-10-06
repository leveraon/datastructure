package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.leveraon.csfoundmental.datastructure.graph.GraphNode;

public class CloneGraph {

	private Map<GraphNode, GraphNode> visited = new HashMap<>();

	GraphNode deepClone(GraphNode node) {

		// Base case: If the input node is null, return null (empty graph).
		if (node == null) {
			return null;
		}

		// If the node has already been visited (and thus cloned), return its clone from
		// the map.
		// This handles cycles and prevents re-cloning.
		if (visited.containsKey(node)) {
			return visited.get(node);
		}

		// Create a new clone for the current node.
		// The neighbors list is initialized as empty and will be populated recursively.
		GraphNode graph = new GraphNode(node.getVal(), new ArrayList<GraphNode>());

		// Store the mapping from original node to its clone immediately.
		// This is important BEFORE processing neighbors to handle self-loops or
		// backward references in cycles correctly.
		visited.put(node, graph);

		// Recursively clone the neighbors and build the cloneNode's neighbors list.
		for (GraphNode neighbor : node.getNeighbours()) {
			node.getNeighbours().add(deepClone(neighbor));
		}

		return graph;
	}

}
