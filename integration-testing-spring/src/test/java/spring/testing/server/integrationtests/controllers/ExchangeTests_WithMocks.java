package spring.testing.server.integrationtests.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import spring.testing.server.configuration.ExchangeControllerConfiguration_WithMocks;
import spring.testing.server.persistence.jdbc.RateRepository;

@SpringBootTest
@ContextConfiguration(classes= {ExchangeControllerConfiguration_WithMocks.class})
public class ExchangeTests_WithMocks {
	
	@MockBean RateRepository repository;
	@Autowired WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.build();
	}

	@Test
	public void whenNoRatesAvailable_thenGetReturnsAnErrorCode() throws Exception {
		this.mockMvc
		.perform(get("/rates/currency/?name=EUR"))
    	.andExpect(status().isNotFound());
	}


}
