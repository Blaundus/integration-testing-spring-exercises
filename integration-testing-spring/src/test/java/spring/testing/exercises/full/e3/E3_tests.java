package spring.testing.exercises.full.e3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.compliance.ComplianceMonitor;
import spring.testing.server.compliance.logging.Registrar;
import spring.testing.server.configuration.ComplianceControllerConfiguration;
import spring.testing.server.controllers.ComplianceController;

@SpringBootTest
@ContextConfiguration(classes= {ComplianceControllerConfiguration.class })
@ActiveProfiles("dev")
public class E3_tests {

	@Autowired ComplianceController controller;
	
	@MockBean(answer = Answers.RETURNS_DEEP_STUBS) 
	Registrar mockRegistrar;
	
	@BeforeEach
	public void setup() {
		ComplianceMonitor.instance().StartMonitoring();
	}
	
	@AfterEach
	public void teardown() {
		ComplianceMonitor.instance().StopMonitoring();
	}
	
	@Disabled
	@Test
	@Profile("test")
	public void whenLogIsTempered_getWarning() {
		assertEquals("data was tempered", controller.getTrafficLog());
	}

	@Test
	@Profile("dev")
	public void whenLogIsTempered_noWarning() {
		assertNull(controller.getTrafficLog());
	}
	
	
	
}
