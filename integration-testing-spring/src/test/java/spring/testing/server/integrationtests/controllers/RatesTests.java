package spring.testing.server.integrationtests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.testing.server.configuration.RatesControllerConfiguration;
import spring.testing.server.controllers.RatesController;
import spring.testing.server.persistence.jdbc.RateRepository;

@SpringBootTest
@ContextConfiguration(classes= {RatesControllerConfiguration.class})
@Sql(scripts = "classpath:CreateSchema.sql", 
	executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", 
	executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RatesTests {
	
	@Autowired RatesController controller;
	@Autowired RateRepository repository;
	@Autowired WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.build();
		controller.Reset();
	}
		
	@Test
	public void canRunIntegrationTests() {
		assertNotNull(controller);
		assertNotNull(repository);

		ServletContext servletContext = wac.getServletContext();
		assertNotNull(servletContext);
		assertTrue(servletContext instanceof MockServletContext);
		assertNotNull(wac.getBean("ratesController"));
	}
	

	@Test
	public void whenNoRatesAvailable_thenGetReturnsAnErrorCode() throws Exception {
		this.mockMvc
		.perform(get("/rates/currency/?name=EUR"))
    	.andExpect(status().isNotFound());
	}
	
	@Test
	public void whenRateIsAdded_thenReturnsOk() throws Exception {
		String jsonRates = asJsonString("ILS=2.5");

	    this.mockMvc.perform(post("/rates/add")
	    					.content(jsonRates)
	    					.contentType("application/json")
	    					.characterEncoding("UTF-8"))
	    		.andExpect(status().isOk());
	}
	
	@Test
	public void ratesAreAddedOneByOne_withBaseRate() throws Exception {
		
		mockMvc.perform(
				post("/rates/add")
				.content(asJsonString("ILS=2.5"))
				.contentType("application/json"))
	    		.andExpect(status().isOk());
	    mockMvc.perform(
	    		post("/rates/add")
	    		.content(asJsonString("USD=3.8"))
				.contentType("application/json"))
				.andExpect(status().isOk());

	    MvcResult result =  mockMvc.perform(
	    		get("/rates/currency/?name=EUR"))
	    	.andExpect(status().isOk())
	    	.andReturn();
	    assertEquals("EUR = 1.000000",result.getResponse().getContentAsString());
    
	    result =  mockMvc.perform(get("/rates/currency/?name=ILS"))
		    .andExpect(status().isOk())
		    .andReturn();
	    assertEquals("ILS = 2.500000",result.getResponse().getContentAsString());
	    
	    result = mockMvc.perform(get("/rates/currency/?name=USD"))
	    	.andExpect(status().isOk())
	        .andReturn();
	    assertEquals("USD = 3.800000",result.getResponse().getContentAsString());
	}

	private String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
