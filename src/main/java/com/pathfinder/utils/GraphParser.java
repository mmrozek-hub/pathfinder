package com.pathfinder.utils;

import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphParser {
	
	public static Graph<String, DefaultEdge> parseModelGraphToJGrapht(com.pathfinder.model.Graph mGraph) {
		Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
		// create vertexes
		Stream.of(mGraph.getVertexes().split(",")).forEach(s -> g.addVertex(s.trim()));
		// create edges
		Stream.of(mGraph.getEdges().split(",")).forEach(s -> g.addEdge(s.split("-")[0].trim(), s.split("-")[1].trim()));

		return g;
	}
}
