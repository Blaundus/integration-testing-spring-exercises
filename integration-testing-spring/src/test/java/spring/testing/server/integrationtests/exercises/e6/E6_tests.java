package spring.testing.server.integrationtests.exercises.e6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.testing.server.configuration.ExchangeControllerConfiguration;
import spring.testing.server.gateway.CheeseExchangeController;
import spring.testing.server.persistence.jdbc.RateRepository;

@SpringBootTest
@ContextConfiguration(classes= {ExchangeControllerConfiguration.class })
@Sql(scripts = "classpath:CreateSchema.sql", 
executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", 
executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class E6_tests {
	
	@Autowired CheeseExchangeController controller;
	@Autowired RateRepository rateRepository;
	@Autowired WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.build();
		controller.Reset();
	}
	
	
	@Test
	public void whenChangingBaseRate_andNoRateAdded_RateIsAddedAsBase() throws Exception {
		mockMvc.perform(post("/rates/updatebase")
				.content(asJsonString("USD"))
				.contentType("application/json"))
	    		.andExpect(status().isOk());

	    MvcResult result =  mockMvc.perform(get("/rates/currency/?name=USD"))
		    	.andExpect(status().isOk())
		    	.andReturn();
	    assertEquals("USD = 1.000000",result.getResponse().getContentAsString());

		mockMvc.perform(get("/rates/currency/?name=ILS"))
	    	.andExpect(status().isNotFound());
}
	
	@Test
	public void whenChangingBaseRate_andRateWasAdded_OnlyTheBaseRateExists() throws Exception {
		mockMvc.perform(
				post("/rates/add")
				.content(asJsonString("ILS=2.5"))
				.contentType("application/json"))
	    		.andExpect(status().isOk());

		mockMvc.perform(post("/rates/updatebase")
				.content(asJsonString("USD"))
				.contentType("application/json"))
	    		.andExpect(status().isOk());

	    MvcResult result =  mockMvc.perform(get("/rates/currency/?name=USD"))
		    	.andExpect(status().isOk())
		    	.andReturn();
		assertEquals("USD = 1.000000",result.getResponse().getContentAsString());

	}
	
	private String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
