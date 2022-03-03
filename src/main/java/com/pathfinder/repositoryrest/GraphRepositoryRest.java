package com.pathfinder.repositoryrest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pathfinder.model.Graph;

@RepositoryRestResource(collectionResourceRel = "graphs", path = "graphs")
public interface GraphRepositoryRest extends PagingAndSortingRepository<Graph, Long> {

	
	List<Graph> findByName(@Param("name") String name );

}
