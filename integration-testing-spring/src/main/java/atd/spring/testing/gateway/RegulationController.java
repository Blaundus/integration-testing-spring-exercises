package atd.spring.testing.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import atd.spring.testing.compliance.RegulationMonitor;
import atd.spring.testing.compliance.TrafficRegulator;
import atd.spring.testing.compliance.TrafficRegulatorLogger;
import atd.spring.testing.rules.CompositeLineItemRule;

@Controller
public class RegulationController {
	@Autowired TrafficRegulator trafficRegulator;

	@RequestMapping(method = RequestMethod.POST, value = "rules/apply")
	public void applyRules() {
		if (RegulationMonitor.getRegulator().shouldMonitor()) {
			trafficRegulator.apply();
		}
	}
}
