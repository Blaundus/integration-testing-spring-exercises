package spring.testing.server.integrationtests.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import spring.testing.server.configuration.ExchangeControllerConfiguration_WithMocks;
import spring.testing.server.exceptions.UnknownCurrencyException;
import spring.testing.server.persistence.jdbc.RateRepository;


@SpringBootTest
@ContextConfiguration(classes= {ExchangeControllerConfiguration_WithMocks.class})
public class ExchangeTests_WithMocks {
	
	@MockBean RateRepository mockRepository;
	@Autowired WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.build();
	}

	@Test
	public void whenNoRatesAvailable_thenGetReturnsAnErrorCode() throws Exception {
		when(mockRepository.findByCurrency(anyString()))
			.thenThrow(new UnknownCurrencyException(null));
		this.mockMvc
		.perform(get("/rates/currency/?name=EUR"))
    	.andExpect(status().isNotFound());
	}


}
