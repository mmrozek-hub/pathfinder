package com.pathfinder.rest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pathfinder.model.Graph;

@RepositoryRestResource(collectionResourceRel = "graph", path = "graph")
public interface GraphRepository extends PagingAndSortingRepository<Graph, Long> {

//	List<Graph> findById(@Param("id") String id);

}
