package atd.spring.testing.integrationtests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.configuration.ControllerConfiguration;
import atd.spring.testing.gateway.ExchangeController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ControllerConfiguration.class})
public class ControllerTests {

	@Autowired
	ExchangeController controller;
	
	@Test
	public void canRunControllerTests() {
		assertTrue(true);
	}
	
	@Test
	public void addingRates() {
		List<String> rates = List.of("EUR=1", "USD=3.8");
		controller.addRates(rates);
			
		assertRatesWereAdded();
	}

	private void assertRatesWereAdded() {
		assertTrue(false);	
	}
}
