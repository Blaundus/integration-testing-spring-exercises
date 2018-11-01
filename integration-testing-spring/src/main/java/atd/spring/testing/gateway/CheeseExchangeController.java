package atd.spring.testing.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import atd.spring.testing.bills.Bill;
import atd.spring.testing.bills.BillTextFileLoader;
import atd.spring.testing.bills.Money;
import atd.spring.testing.compliance.CompliantRuledBillTextFileLoader;
import atd.spring.testing.compliance.TrafficRegulator;
import atd.spring.testing.compliance.TrafficRegulatorLogger;
import atd.spring.testing.exchange.CheeseExchange;
import atd.spring.testing.exchange.Rate;
import atd.spring.testing.exchange.RateLoader;
import atd.spring.testing.persistence.jdbc.RateRepository;
import atd.spring.testing.rules.CompositeLineItemRule;

@Controller
public class CheeseExchangeController {

	@Autowired CompositeLineItemRule ruleManager;
	@Autowired RateLoader rateLoader;
	@Autowired RateRepository rateRepository;
 	@Autowired CheeseExchange exchange;
	@Autowired StatusMonitor monitor;
	@Autowired TrafficRegulator trafficRegulator;
	
	
	@RequestMapping(method = RequestMethod.GET, value ="rates/currency")
	public String getRateByCurrency(
			@RequestParam(value="currency") String currency) {
		
		return rateRepository.findByCurrency(currency).toString();
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "rates/add")
	public void addRates(@RequestBody List<String> rates) {
		boolean isFirstTime = true;
		if (monitor.isInitialized()) {
			isFirstTime = false;
		}
		else
		{
			monitor.start();
		}
		
		if (monitor.isOk()) {
			if (isFirstTime)
				rateLoader.setBaseRate("EUR");
			rateLoader.add(rates);
		}
	}
	

	
	@RequestMapping(method = RequestMethod.POST, value = "bills/calculate")
	public Money calculateBill(
			@RequestBody List<String> items, 
			@RequestBody String currency) {
		BillTextFileLoader billLoader = new CompliantRuledBillTextFileLoader(ruleManager, trafficRegulator);
		Bill bill = billLoader.createFromLineItems(items);
		Money result = bill.getTotal(currency);
		
		return result;
	}
	
	public void Reset() {
		this.monitor.shutdown();
	}
}
