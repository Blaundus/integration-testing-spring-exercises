package spring.testing.server.integrationtests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.testing.server.configuration.RatesControllerConfiguration;

@SpringBootTest
@ContextConfiguration(classes = { RatesControllerConfiguration.class })
@Sql(scripts = "classpath:CreateSchema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureMockMvc
public class JDBC_ExchangeControllerTests {

	@Autowired MockMvc mockMvc;
	

	@Test
	public void ratesAreAdded_withBaseRate() throws Exception {

		List<String> rates = List.of("ILS=2.5", "USD=3.8");
		addRates(rates);

		assertEquals("EUR = 1.000000", getRateByCurrency("EUR"));
		assertEquals("ILS = 2.500000", getRateByCurrency("ILS"));
		assertEquals("USD = 3.800000", getRateByCurrency("USD"));
	}

	private void addRates(List<String> rates) throws Exception {
		rates.forEach((rate) ->{
			try {
				mockMvc.perform(post("/rates/add")
						.content(asJsonString(rate)))
						.andExpect(status().isOk());
			} catch (Exception e) {
			}
		});
	}

	private String getRateByCurrency(String currency) throws Exception {
		String url = "/rates/currency/?name=" + currency;
		MvcResult result = mockMvc
				.perform(get(url))
				.andExpect(status().isOk())
				.andReturn();
		
		return result.getResponse().getContentAsString();
	}

	private String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}	
}
