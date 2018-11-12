package atd.spring.server.integrationtests.exercises.e2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.compliance.ComplianceMonitor;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.gateway.ComplianceController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {E2_configuration.class })
@ActiveProfiles("test")
public class E2_tests_test {

	@Autowired ComplianceController controller;
	@Autowired Registrar registrar;
	
	@Before
	public void setup() {
		ComplianceMonitor.getRegulator().StartMonitoring();
	}
	
	@After
	public void teardown() {
		ComplianceMonitor.getRegulator().StopMonitoring();
	}
	
	@Test
	public void whenLogIsTempered_getWarning() {
		assertEquals("data was tempered", controller.getTrafficLog());
	}
	
}
