package com.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pathfinder.model.Graph;

public interface GraphRepository extends JpaRepository<Graph, Long> {

}