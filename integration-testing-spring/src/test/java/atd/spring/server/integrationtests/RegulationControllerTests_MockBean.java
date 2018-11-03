package atd.spring.server.integrationtests;

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

import atd.spring.server.compliance.RegulationMonitor;
import atd.spring.server.compliance.TrafficRegulator;
import atd.spring.server.configuration.RegulationControllerConfiguration;
import atd.spring.server.gateway.RegulationController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { RegulationControllerConfiguration.class })
public class RegulationControllerTests_MockBean {

	@Autowired
	RegulationController controller;
	@MockBean
	TrafficRegulator mockRegulator;


	@Test
	public void whenMonitorIsOn_RulesAreApplied() {
		RegulationMonitor.getRegulator().StartMonitoring();
		controller.applyRules();
		verify(mockRegulator).apply();
	}

	@Test
	public void whenMonitorIsOff_RulesAreNotApplied() {
		RegulationMonitor.getRegulator().StopMonitoring();
		controller.applyRules();
		verify(mockRegulator, never()).apply();
	}

}
