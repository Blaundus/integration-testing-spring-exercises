package spring.testing.server.integrationtests.profiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.AppConfiguration;
import spring.testing.server.configuration.HackerConfiguration;
import spring.testing.server.configuration.JDBC_DataConfiguration;
import spring.testing.server.controllers.RatesController;

@SpringBootTest
@ContextConfiguration(classes= {HackerConfiguration.class, 
		AppConfiguration.class,
		JDBC_DataConfiguration.class})
@ActiveProfiles("hacker")
public class HackingTests {
	
	@Autowired RatesController controller;
	
	@Test
	public void CannotAddRates_withFaultyMonitor() {
		List<String> rates = List.of("ILS=2.5", "USD=3.8");
		controller.addRates(rates);
		assertEquals("Error", controller.getAllRates().getBody());
	}
}
