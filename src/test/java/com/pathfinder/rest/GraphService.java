package com.pathfinder.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.pathfinder.repositoryrest.GraphRepositoryRest;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/testdata.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class GraphService {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GraphRepositoryRest graphRepository;

	@AfterEach
	public void deleteAllBeforeTests() throws Exception {
		graphRepository.deleteAll();
	}
	
	@Test
	public void test_testData() throws Exception {

		mockMvc.perform(get("/graphs/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("graph1"));
	}

	@Test
	public void test_givenGraphWithVerticiesOneAfterAnother_whenFindingShortestPath_thenOkAndShortestPath0To7() throws Exception {

		mockMvc.perform(get("/graphService/solve/1?begin=1&end=7")).andExpect(status().isOk())
				.andExpect(content().string("(1 : 2) - (2 : 3) - (3 : 4) - (4 : 5) - (5 : 6) - (6 : 7)")); 

	}
	
	@Test
	public void test_givenGraphWithVerticiesShortpathFrom2To6_whenFindingShortestPath_thenOkAndShortestPathWithShortpath() throws Exception {

		mockMvc.perform(get("/graphService/solve/2?begin=0&end=7")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().string("(0 : 1) - (1 : 2) - (2 : 6) - (6 : 7)")); 

	}

	@Test
	public void test_givenGraphWithVerticiesCircle0To3AndThenFrom2To4_whenFindingShortestPath_thenOkAndShortestPathWithShortpath() throws Exception {

		mockMvc.perform(get("/graphService/solve/3?begin=0&end=7")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().string("(0 : 1) - (1 : 2) - (2 : 4) - (4 : 5) - (5 : 6) - (6 : 7)")); 
	}

	@Test
	public void test_givenGraphWithVerticiesCircle0To3AndThenFrom2To4AndStartingFromEnd_whenFindingShortestPath_thenOkAndShortestPath() throws Exception {

		mockMvc.perform(get("/graphService/solve/3?begin=7&end=0")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().string("(6 : 7) - (5 : 6) - (4 : 5) - (2 : 4) - (1 : 2) - (0 : 1)")); 
	}
	
	@Test
	public void test_givenGraphWithVerticiesCircle0To3AndThenFrom2To4And2To6_whenFindingShortestPath_thenOkAndShortestPathWithShorterShortpath() throws Exception {

		mockMvc.perform(get("/graphService/solve/4?begin=0&end=7")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().string("(0 : 1) - (1 : 2) - (2 : 6) - (6 : 7)")); 
	}

	

}
