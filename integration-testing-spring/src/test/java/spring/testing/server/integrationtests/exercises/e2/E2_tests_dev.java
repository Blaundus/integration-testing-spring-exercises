package spring.testing.server.integrationtests.exercises.e2;

import static org.mockito.Mockito.reset;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import spring.testing.server.compliance.ComplianceMonitor;
import spring.testing.server.compliance.logging.Registrar;
import spring.testing.server.gateway.ComplianceController;

@SpringBootTest
@ContextConfiguration(classes= {E2_configuration.class })
@ActiveProfiles("dev")
public class E2_tests_dev {

	@Autowired ComplianceController controller;
	@Autowired Registrar registrar;
	
	@BeforeEach
	public void setup() {
		ComplianceMonitor.getRegulator().StartMonitoring();
		reset(registrar);
	}
	
	@AfterEach
	public void teardown() {
		ComplianceMonitor.getRegulator().StopMonitoring();
	}
	
	@Test
	public void whenLogIsTempered_noWarning() {
		assertNull(controller.getTrafficLog());
	}

}
