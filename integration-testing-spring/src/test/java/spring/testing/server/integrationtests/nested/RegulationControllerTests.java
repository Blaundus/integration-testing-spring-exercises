package spring.testing.server.integrationtests.nested;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.testing.server.compliance.ComplianceMonitor;
import spring.testing.server.compliance.logging.Registrar;
import spring.testing.server.configuration.ComplianceControllerConfiguration;
import spring.testing.server.gateway.ComplianceController;

@SpringBootTest
public class RegulationControllerTests{
	@Autowired
	ComplianceController controller;

	@Configuration
	@Import(ComplianceControllerConfiguration.class)	
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
			ComplianceMonitor.instance().StartMonitoring();
		} else
			ComplianceMonitor.instance().StopMonitoring();
		String log = controller.getTrafficLog();
		if (monitoringStatus == "on") {
			assertTrue(!log.contains("offline"));
		} else {
			assertTrue(log.contains("offline"));
		}
	}
}
