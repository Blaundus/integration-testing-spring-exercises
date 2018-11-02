package atd.spring.server.integrationtests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.configuration.AppConfiguration;
import atd.spring.server.configuration.ExchangeControllerConfiguration;
import atd.spring.server.configuration.HackerConfiguration;
import atd.spring.server.configuration.JdbcDataConfiguration;
import atd.spring.server.gateway.CheeseExchangeController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {HackerConfiguration.class, 
		AppConfiguration.class,
		JdbcDataConfiguration.class})
@ActiveProfiles("hacker")
public class HackingTests {
	
	@Autowired CheeseExchangeController controller;
	
	@Before
	public void setup() {
		controller.Reset();
	}
	
	@Test
	public void CannotAddRates_withFaultyMonitor() {
		List<String> rates = List.of("ILS=2.5", "USD=3.8");
		controller.addRates_direct(rates);
//		assertEquals("Error", controller.getRates());
	}
}
