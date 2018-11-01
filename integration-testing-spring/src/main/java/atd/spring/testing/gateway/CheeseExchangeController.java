package atd.spring.testing.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class CheeseExchangeController {

	@Autowired CompositeLineItemRule ruleManager;
	@Autowired RateLoader rateLoader;
	@Autowired RateRepository rateRepository;
 	@Autowired CheeseExchange exchange;
	@Autowired StatusMonitor monitor;
	@Autowired TrafficRegulator trafficRegulator;
	
	
	@RequestMapping(method = RequestMethod.GET, value ="rates/currency")
	public ResponseEntity<String> getRateByCurrency(
			@RequestParam(value="currency") String currency) {
		try {
			String result =rateRepository.findByCurrency(currency).toString();
			return new ResponseEntity<>(result, HttpStatus.OK); 
		}
		catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "rates/add")
	public void addRates_direct(@RequestBody List<String> rates) {
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
	

	@RequestMapping(method = RequestMethod.GET, value ="rates/rest/currency")
	public ResponseEntity<String> getRateByCurrency_Rest(
			@RequestParam(value="currency") String currency) {
		String resultBody =rateRepository.findByCurrency(currency).toString();
		ResponseEntity<String> response = new ResponseEntity(resultBody, HttpStatus.OK); 
		return response; 
	}

	@RequestMapping(method = RequestMethod.POST, 
			value = "rates/rest/add",
			consumes = "application/json")
	public ResponseEntity addRates_Rest(@RequestBody List<String> rates) {
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
		
		return new ResponseEntity(HttpStatus.OK);
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
