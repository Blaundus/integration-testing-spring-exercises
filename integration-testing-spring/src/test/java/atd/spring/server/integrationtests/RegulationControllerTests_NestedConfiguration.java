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

import atd.spring.server.compliance.ComplianceMonitor;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.compliance.logging.TrafficRegistrar;
import atd.spring.server.configuration.RegulationControllerConfiguration;
import atd.spring.server.gateway.ComplianceController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegulationControllerTests_NestedConfiguration{
	@Autowired
	ComplianceController controller;

	@Configuration
	@Import(RegulationControllerConfiguration.class)
	static class NestedConfiguration {
		
		@Bean
		public Registrar trafficRegulator() {
			return Mockito.mock(Registrar.class);
		}
	}

	
	@Value(value = "${monitoring.status}")
	String monitoringStatus;

	@Test
	public void whenSettingMonitoring_LogContentChange() {
		if (monitoringStatus == "on") {
			ComplianceMonitor.getRegulator().StartMonitoring();
		} else
			ComplianceMonitor.getRegulator().StopMonitoring();
		String log = controller.getTrafficLog();
		if (monitoringStatus == "on") {
			assertTrue(!log.contains("offline"));
		} else {
			assertTrue(log.contains("offline"));
		}
	}
}
