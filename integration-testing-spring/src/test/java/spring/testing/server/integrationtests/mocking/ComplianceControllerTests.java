package spring.testing.server.integrationtests.mocking;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.compliance.ComplianceMonitor;
import spring.testing.server.compliance.logging.Registrar;
import spring.testing.server.configuration.ComplianceControllerConfiguration;
import spring.testing.server.controllers.ComplianceController;

@SpringBootTest
@ContextConfiguration(classes = { ComplianceControllerConfiguration.class })
public class ComplianceControllerTests{

	@Autowired ComplianceController controller;
	@MockBean  Registrar mockRegistrar;


	@Test
	public void whenMonitorIsOn_RulesAreApplied() {
		ComplianceMonitor.instance().StartMonitoring();
		controller.applyComplianceRules();
		verify(mockRegistrar).start();
	}

	@Test
	public void whenMonitorIsOff_RulesAreNotApplied() {
		ComplianceMonitor.instance().StopMonitoring();
		controller.applyComplianceRules();
		verify(mockRegistrar, never()).start();
	}

}
