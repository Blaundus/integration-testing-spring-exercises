package atd.spring.server.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import atd.spring.server.compliance.ComplianceMonitor;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.compliance.logging.TrafficRegistrar;
import atd.spring.server.rules.CompositeLineItemRule;

@Controller
public class ComplianceController {
	@Autowired Registrar trafficRegistrar;

	@RequestMapping(method = RequestMethod.POST, value = "rules/apply")
	public void applyComplianceRules() {
		if (ComplianceMonitor.getRegulator().shouldMonitor()) {
			trafficRegistrar.start();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rules/log")
	public String getTrafficLog() {
		if (ComplianceMonitor.getRegulator().shouldMonitor()){
			return trafficRegistrar.getLog().getAllAsString();
		}
		else
			return "Monitoring is offline";
	}
	
}
