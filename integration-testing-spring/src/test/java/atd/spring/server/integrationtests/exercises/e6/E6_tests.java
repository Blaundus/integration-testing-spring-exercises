package atd.spring.server.integrationtests.exercises.e6;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import atd.spring.server.configuration.CheeseControllerConfiguration;
import atd.spring.server.configuration.ExchangeControllerConfiguration;
import atd.spring.server.gateway.CheeseExchangeController;
import atd.spring.server.persistence.jdbc.RateRepository;

@RunWith(SpringRunner.class)
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
	
	@Before
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
