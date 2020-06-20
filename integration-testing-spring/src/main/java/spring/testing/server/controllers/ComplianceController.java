package spring.testing.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import spring.testing.server.compliance.ComplianceMonitor;
import spring.testing.server.compliance.logging.Registrar;

@Controller
public class ComplianceController {
	@Autowired Registrar trafficRegistrar;

	@PostMapping(value = "rules/apply")
	public void applyComplianceRules() {
		if (ComplianceMonitor.instance().shouldMonitor()) {
			trafficRegistrar.start();
		}
	}
	
	@GetMapping(value = "rules/log")
	public String getTrafficLog() {
		if (ComplianceMonitor.instance().shouldMonitor()){
			// e2 : add tempering check
			if (trafficRegistrar.isTempered())
				return "data was tempered";
			// e2
			return trafficRegistrar.getLog().getAllAsString();
		}
		else
			return "Monitoring is offline";
	}
	
}
