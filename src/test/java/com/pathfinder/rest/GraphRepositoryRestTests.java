package com.pathfinder.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.pathfinder.repositoryrest.GraphRepositoryRest;

@SpringBootTest
@AutoConfigureMockMvc
public class GraphRepositoryRestTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GraphRepositoryRest graphRepository;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		graphRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$._links.graphs").exists());
	}

	@Test
	public void test_whenCreate_thenShouldCreateEntity() throws Exception {

		// given - when - then
		mockMvc.perform(post("/graphs")
				.content("{\"name\": \"Vertex7\", \"id\":7}"))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString("graphs/")));
	}

	@Test
	public void test_whenFindByName_thenShouldFindEntity() throws Exception {

		// given
		MvcResult mvcResult = mockMvc.perform(post("/graphs").content("{\"name\": \"Vertex7\", \"id\":7}"))
				.andExpect(status().isCreated()).andReturn();
		
		// when - then
		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Vertex7"));
	}

	@Test
	public void test_whenFindById_thenShouldFindEntity() throws Exception {

		// given
		MvcResult mvcResult = mockMvc.perform(post("/graphs")
				.content("{\"name\": \"Vertex7\", \"id\":7}"))
				.andExpect(status().isCreated()).andReturn();

		// when - then
		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("Vertex7"));
	}
	
	
	@Test
	public void test_whenFindByName_thenShouldFindEntityhould() throws Exception {

		// given
		mockMvc.perform(post("/graphs").content("{\"name\": \"Vertex7\", \"id\":7}"))
			.andExpect(status().isCreated());

		// when - then
		mockMvc.perform(
				get("/graphs/search/findByName?name={name}", "Vertex7")).andExpect(
						status().isOk()).andExpect(
								jsonPath("$._embedded.graphs[0].name").value(
										"Vertex7"));
	}

}
