package atd.spring.server.gateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.BillParser;
import atd.spring.server.bills.Money;
import atd.spring.server.compliance.ComplianceRuledBasedBillParser;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.compliance.logging.TrafficRegistrar;
import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.exchange.Rate;
import atd.spring.server.exchange.RateParser;
import atd.spring.server.exchange.Rates;
import atd.spring.server.persistence.jdbc.RateRepository;
import atd.spring.server.rules.CompositeLineItemRule;

@RestController()
public class CheeseExchangeController {

	@Autowired CompositeLineItemRule ruleManager;
	@Autowired RateParser rateLoader;
	@Autowired RateRepository rateRepository;
 	@Autowired CheeseExchange exchange;
	@Autowired StatusMonitor monitor;
	@Autowired Registrar trafficRegulator;
	private boolean isFirstTime;
	
//	@GetMapping(value = "/rates/all")
//	public ResponseEntity<Rates> getAllRates() {
//		Rates = rateRepository.
//	}
	
	@RequestMapping(method = RequestMethod.GET, 
					value ="/rates/currency")
	public ResponseEntity<String> getRateByCurrency(
			@RequestParam(value="name") String currency) {
		try {
			String result =rateRepository.findByCurrency(currency).toString();
			return new ResponseEntity<>(result, HttpStatus.OK); 
		}
		catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "rates/add1")
	public void addRates_direct(@RequestParam List<String> rates) {
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
			rateLoader.parse(rates);
		}
		
	}
	

	@GetMapping(value ="/rates/currency1")
	public ResponseEntity<String> getRateByCurrency_Rest(
			@RequestParam(value="currency") String currency) {
		String resultBody =rateRepository.findByCurrency(currency).toString();
		ResponseEntity<String> response = new ResponseEntity(resultBody, HttpStatus.OK); 
		return response; 
	}

	@PostMapping(value = "/rates/add") 
	public ResponseEntity<?> addRate_Rest(@RequestBody String rate) {

		Rates rates = new Rates();
		rates.add(rate);
		isFirstTime = true;
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
			rateLoader.parse(rates.getRates());
		}
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
		
		@PostMapping(value = "/rates/addmany",
			headers = "Accept=application/json") 
	public ResponseEntity<?> addRates_Rest(
			@RequestBody Rates rates) {
		
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
			rateLoader.parse(rates.getRates());
		}
		
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "bills/calculate")
	public Money calculateBill(
			@RequestBody List<String> items, 
			@RequestBody String currency) {
		BillParser parser = new ComplianceRuledBasedBillParser(ruleManager, trafficRegulator);
		Bill bill = parser.createFromLineItems(items);
		Money result = bill.getTotal(currency);
		
		return result;
	}
	
	public void Reset() {
		this.monitor.shutdown();
	}
}
