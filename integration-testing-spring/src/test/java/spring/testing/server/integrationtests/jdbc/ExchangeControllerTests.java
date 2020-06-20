package spring.testing.server.integrationtests.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import spring.testing.server.configuration.ExchangeControllerConfiguration;
import spring.testing.server.controllers.ProductExchangeController;
import spring.testing.server.persistence.jdbc.RateRepository;

@SpringBootTest
@ContextConfiguration(classes = { ExchangeControllerConfiguration.class })
@Sql(scripts = "classpath:CreateSchema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ExchangeControllerTests {

	@Autowired
	ProductExchangeController controller;
	@Autowired
	RateRepository repository;

	@Test
	public void canRunIntegrationTests() {
		assertNotNull(controller);
		assertNotNull(repository);
	}

	@BeforeEach
	public void setup() {
		controller.Reset();
	}

	@Test
	public void ratesAreAdded_withBaseRate() {

		List<String> rates = List.of("ILS=2.5", "USD=3.8");
		controller.addRates(rates);

		assertEquals("EUR = 1.000000", controller.getRateByCurrency("EUR").getBody());
		assertEquals("ILS = 2.500000", controller.getRateByCurrency("ILS").getBody());
		assertEquals("USD = 3.800000", controller.getRateByCurrency("USD").getBody());
	}

}
