package atd.spring.server.integrationtests.exercises.e3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.compliance.ComplianceMonitor;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.configuration.ComplianceControllerConfiguration;
import atd.spring.server.gateway.ComplianceController;
import atd.spring.server.integrationtests.exercises.e2.E2_configuration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ComplianceControllerConfiguration.class })
@ActiveProfiles("dev")
public class E3_tests {

	@Autowired ComplianceController controller;
	
	@MockBean(answer = Answers.RETURNS_DEEP_STUBS) 
	Registrar mockRegistrar;
	
	@Before
	public void setup() {
		ComplianceMonitor.getRegulator().StartMonitoring();
	}
	
	@After
	public void teardown() {
		ComplianceMonitor.getRegulator().StopMonitoring();
	}
	
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
