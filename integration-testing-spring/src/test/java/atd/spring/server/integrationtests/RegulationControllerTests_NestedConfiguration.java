package atd.spring.server.integrationtests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.compliance.RegulationMonitor;
import atd.spring.server.compliance.TrafficRegulator;
import atd.spring.server.compliance.TrafficRegulatorLogger;
import atd.spring.server.configuration.RegulationControllerConfiguration;
import atd.spring.server.gateway.RegulationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegulationControllerTests_NestedConfiguration{
	@Autowired
	RegulationController controller;

	@Configuration
	@Import(RegulationControllerConfiguration.class)
	static class NestedConfiguration {
		
		@Bean
		public TrafficRegulator trafficRegulator() {
			return Mockito.mock(TrafficRegulator.class);
		}
	}

	
	@Value(value = "${monitoring.status}")
	String monitoringStatus;

	@Test
	public void whenSettingMonitoring_LogContentChange() {
		if (monitoringStatus == "on") {
			RegulationMonitor.getRegulator().StartMonitoring();
		} else
			RegulationMonitor.getRegulator().StopMonitoring();
		String log = controller.getLog();
		if (monitoringStatus == "on") {
			assertTrue(!log.contains("offline"));
		} else {
			assertTrue(log.contains("offline"));
		}
	}
}
