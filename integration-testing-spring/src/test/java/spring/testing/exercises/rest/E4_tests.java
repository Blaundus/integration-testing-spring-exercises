package spring.testing.exercises.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.testing.server.configuration.RatesControllerConfiguration_WithMocks;
import spring.testing.server.exchange.Monitor;
import spring.testing.server.helpers.JsonHelper;
import spring.testing.server.persistence.jdbc.RateRepository;

@SpringBootTest
@ContextConfiguration(classes= {RatesControllerConfiguration_WithMocks.class })
@AutoConfigureMockMvc
public class E4_tests {

	@MockBean RateRepository mockRepository;
	@MockBean Monitor mockMonitor;
	@Autowired MockMvc mockMvc;
	
	@Test
	public void getAllRates_whenMonitored_returnsNotImplemented() throws Exception {
		when(mockMonitor.isOk()).thenReturn(true);
		
		MvcResult result = 
				mockMvc.perform(get("/rates/all"))
				.andExpect(status().isNotImplemented())
				.andReturn();
		
		assertEquals("Not implemented yet" , 
				result.getResponse().getContentAsString());
		
	}

	@Test
	public void addNewRate() throws Exception {
		mockMvc.perform(post("/rates/add")
			.content(JsonHelper.asJsonString("ILS=2.5"))
			.contentType("application/json"))
			.andExpect(status().isOk());
		
	}
	
	
}
