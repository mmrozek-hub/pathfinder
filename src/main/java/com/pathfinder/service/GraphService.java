package com.pathfinder.service;

import org.jgrapht.graph.DefaultEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.model.Graph;
import com.pathfinder.repository.GraphRepository;
import com.pathfinder.utils.GraphParser;
import com.pathfinder.utils.GraphSolver;

@RestController
public class GraphService {

	@Autowired
	private GraphRepository graphRepository;
	
	@Autowired
	private GraphSolver graphSolver;

	@GetMapping("/graphService/solve/{id}")
	public String findShortestPath(@PathVariable("id") Long graphId, @RequestParam("begin") String begin, @RequestParam("end") String end) throws Exception {

		Graph graph = graphRepository.findById(graphId)
				.orElseThrow(() -> new Exception(String.format("Graph with id %d not found!", graphId)));

		org.jgrapht.Graph<String,DefaultEdge> graphJGrapht = GraphParser.parseModelGraphToJGrapht(graph);
		return graphSolver.findShortestPath(graphJGrapht, begin, end);

	}

}
