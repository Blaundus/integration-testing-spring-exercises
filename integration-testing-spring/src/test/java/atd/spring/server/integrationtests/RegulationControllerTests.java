package atd.spring.server.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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

import atd.spring.server.configuration.RegulationControllerConfiguration;
import atd.spring.testing.compliance.RegulationMonitor;
import atd.spring.testing.compliance.TrafficRegulator;
import atd.spring.testing.gateway.RegulationController;



@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {RegulationControllerConfiguration.class})
public class RegulationControllerTests {

	@Autowired 	RegulationController controller;
	@MockBean 	TrafficRegulator mockRegulator;
	
	@Value(value = "${monitoring.status}") 		
	String		monitoringStatus;
	
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

	@Test
	public void whenSettingMonitoring_LogContentChange() {
		if (monitoringStatus == "on") {
			RegulationMonitor.getRegulator().StartMonitoring();
		}
		else
			RegulationMonitor.getRegulator().StopMonitoring();
		String log = controller.getLog();
		if (monitoringStatus == "on") {
			assertTrue(!log.contains("offline"));
		}
		else {
			assertTrue(log.contains("offline"));
		}
			
			
			
	}
	
}
