package atd.spring.testing.integrationtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.configuration.ControllerConfiguration;
import atd.spring.testing.gateway.ExchangeController;
import atd.spring.testing.persistence.RateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes= {ControllerConfiguration.class})
public class ControllerTests {
	/*
	@Autowired ExchangeController controller;
	@Autowired RateRepository repository;
	
	@Test
	public void canRunControllerTests() {
		assertTrue(true);
		
	}
	
	@Test
	public void addingRates() {
		List<String> rates = List.of("EUR=1", "USD=3.8");
		controller.addRates(rates);
		
		assertEquals("1", controller.getRateByCurrency("EUR"));
		assertEquals("3.8", controller.getRateByCurrency("USD"));
	}
*/
}
