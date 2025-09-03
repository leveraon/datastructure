package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.leveraon.csfoundmental.datastructure.graph.GraphNode;

public class CloneGraph {

	private Map<GraphNode, GraphNode> visited = new HashMap<>();

	GraphNode deepClone(GraphNode node) {

		if (node == null)
			return null;

		if (visited.containsKey(node))
			return visited.get(node);

		GraphNode graph = new GraphNode(node.getVal(), new ArrayList<GraphNode>());

		visited.put(node, graph);

		for (GraphNode neighbor : node.getNeighbours()) {
			node.getNeighbours().add(deepClone(neighbor));
		}

		return graph;
	}

}
