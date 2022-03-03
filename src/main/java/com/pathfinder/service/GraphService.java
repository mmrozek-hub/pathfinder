package com.pathfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RestControllerConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.model.Graph;
import com.pathfinder.repository.GraphRepository;

@RestController
public class GraphService {

	@Autowired
	private GraphRepository graphRepository;

	@GetMapping("/graphService/solve/{id}")
	public String findShortestPath(@PathVariable("id") Long graphId) throws Exception {

		Graph graph = graphRepository.findById(graphId)
				.orElseThrow(() -> new Exception(String.format("Graph with id %d not found!", graphId)));

		
		
		return graph.toString();
	}

}