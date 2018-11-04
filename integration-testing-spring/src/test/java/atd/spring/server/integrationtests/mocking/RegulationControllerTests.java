package atd.spring.server.integrationtests.mocking;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.compliance.ComplianceMonitor;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.configuration.RegulationControllerConfiguration;
import atd.spring.server.gateway.ComplianceController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { RegulationControllerConfiguration.class })
public class RegulationControllerTests{

	@Autowired
	ComplianceController controller;
	@MockBean
	Registrar mockRegulator;


	@Test
	public void whenMonitorIsOn_RulesAreApplied() {
		ComplianceMonitor.getRegulator().StartMonitoring();
		controller.applyComplianceRules();
		verify(mockRegulator).start();
	}

	@Test
	public void whenMonitorIsOff_RulesAreNotApplied() {
		ComplianceMonitor.getRegulator().StopMonitoring();
		controller.applyComplianceRules();
		verify(mockRegulator, never()).start();
	}

}
