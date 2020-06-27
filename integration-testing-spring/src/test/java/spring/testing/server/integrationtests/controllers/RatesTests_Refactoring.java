package spring.testing.server.integrationtests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
import spring.testing.server.helpers.JsonHelper;
import spring.testing.server.persistence.jdbc.RateRepository;

@SpringBootTest
@ContextConfiguration(classes= {RatesControllerConfiguration.class})
@Sql(scripts = "classpath:CreateSchema.sql", 
	executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", 
	executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureMockMvc
public class RatesTests_Refactoring {
	
	@Autowired RatesController controller;
	@Autowired MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		controller.Reset();
	}
	

	@Test
	public void ratesAreAddedOneByOne_withBaseRate() throws Exception {
		
		addRate("ILS=2.5");
	    mockMvc.perform(
	    		post("/rates/add")
	    		.content(JsonHelper.asJsonString("USD=3.8"))
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

	@Test
	public void ratesAreAddedOneByOne_withBaseRate_refactored() throws Exception {
		
		addRate("ILS=2.5");
		addRate("USD=3.8");

		assertEquals("EUR = 1.000000",getRateFor("EUR"));
	    assertEquals("ILS = 2.500000",getRateFor("ILS"));
	    assertEquals("USD = 3.800000",getRateFor("USD"));
	}


	private String getRateFor(String currency) throws Exception, UnsupportedEncodingException {
		String url = "/rates/currency/?name=" + currency;
		MvcResult result =  mockMvc.perform(
	    		get(url))
	    	.andExpect(status().isOk())
	    	.andReturn();
	    return result.getResponse().getContentAsString();
	}


	private void addRate(String rate) throws Exception {
		mockMvc.perform(
				post("/rates/add")
				.content(JsonHelper.asJsonString(rate))
				.contentType("application/json"))
	    		.andExpect(status().isOk());
	}

}
