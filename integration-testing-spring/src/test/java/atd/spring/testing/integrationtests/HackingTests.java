package atd.spring.testing.integrationtests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.configuration.ControllerConfiguration;
import atd.spring.testing.configuration.HackerConfiguration;
import atd.spring.testing.configuration.MinimalConfiguration;
import atd.spring.testing.gateway.ExchangeController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {HackerConfiguration.class, MinimalConfiguration.class})
@ActiveProfiles("hacker")
public class HackingTests {
	
	@Autowired ExchangeController controller;
	
	@Test
	public void CannotAddRates_withFaultyMonitor() {
		List<String> rates = List.of("ILS=2.5", "USD=3.8");
		controller.addRates(rates);
		assertEquals("Error", controller.getRates());
	}
}
