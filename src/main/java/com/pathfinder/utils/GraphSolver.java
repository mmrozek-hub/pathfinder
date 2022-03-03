package com.pathfinder.utils;

import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Component;

@Component
public class GraphSolver {
	
	public String findShortestPath(Graph<String, DefaultEdge> g, String source, String sink) {
		 GraphPath<String, DefaultEdge> graphPath = DijkstraShortestPath.findPathBetween(g, source, sink);
		 return graphPath.getEdgeList().stream().map(e -> e.toString()).collect(Collectors.joining(" - "));
	}
}
