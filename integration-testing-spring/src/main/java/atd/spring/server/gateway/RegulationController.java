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
public class RegulationController {
	@Autowired Registrar trafficRegulator;

	@RequestMapping(method = RequestMethod.POST, value = "rules/apply")
	public void applyRules() {
		if (ComplianceMonitor.getRegulator().shouldMonitor()) {
			trafficRegulator.startLog();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rules/log")
	public String getLog() {
		if (ComplianceMonitor.getRegulator().shouldMonitor()){
			return trafficRegulator.getLog().getAllAsString();
		}
		else
			return "Monitoring is offline";
	}
	
}
