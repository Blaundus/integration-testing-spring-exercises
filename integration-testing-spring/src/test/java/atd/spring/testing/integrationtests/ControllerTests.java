package atd.spring.testing.integrationtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import atd.spring.testing.configuration.ControllerConfiguration;
import atd.spring.testing.gateway.ExchangeController;
import atd.spring.testing.persistence.RateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ControllerConfiguration.class})
@Sql(scripts = "classpath:CreateSchema.sql", 
	executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", 
	executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ControllerTests {
	
	@Autowired ExchangeController controller;
	@Autowired RateRepository repository;
	
	@Test
	public void canRunControllerTests() {
		assertNotNull(controller);
		assertNotNull(repository);
	}
	
	
	@Test
	public void ratesAreAdded_withBaseRate_EUR_1() {
		List<String> rates = List.of("ILS=2.5", "USD=3.8");
		controller.addRates(rates);
		
		assertEquals("Rate: EUR = 1.000000", controller.getRateByCurrency("EUR"));
		assertEquals("Rate: ILS = 2.500000", controller.getRateByCurrency("ILS"));
		assertEquals("Rate: USD = 3.800000", controller.getRateByCurrency("USD"));
	}
}
