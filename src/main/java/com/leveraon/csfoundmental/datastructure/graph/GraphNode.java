package com.leveraon.csfoundmental.datastructure.graph;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphNode {
	int val;
	List<GraphNode> neighbours;
}
