package com.leveraon.csfoundmental.algorithms.examples.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Node {
	int val;
	List<Node> neighbours;
}

public class CloneGraph {

	private Map<Node, Node> visited = new HashMap<>();

	Node deepClone(Node node) {

		if (node == null)
			return null;

		if (visited.containsKey(node))
			return visited.get(node);

		Node graph = new Node(node.getVal(), new ArrayList<Node>());

		visited.put(node, graph);

		for (Node neighbor : node.getNeighbours()) {
			node.getNeighbours().add(deepClone(neighbor));
		}

		return graph;
	}

}
