package atd.spring.testing.gateway;

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
import atd.spring.server.bills.BillTextFileLoader;
import atd.spring.server.bills.Money;
import atd.spring.testing.compliance.CompliantRuledBillTextFileLoader;
import atd.spring.testing.compliance.TrafficRegulator;
import atd.spring.testing.compliance.TrafficRegulatorLogger;
import atd.spring.testing.exchange.CheeseExchange;
import atd.spring.testing.exchange.Rate;
import atd.spring.testing.exchange.RateLoader;
import atd.spring.testing.exchange.Rates;
import atd.spring.testing.persistence.jdbc.RateRepository;
import atd.spring.testing.rules.CompositeLineItemRule;

@RestController()
public class CheeseExchangeController {

	@Autowired CompositeLineItemRule ruleManager;
	@Autowired RateLoader rateLoader;
	@Autowired RateRepository rateRepository;
 	@Autowired CheeseExchange exchange;
	@Autowired StatusMonitor monitor;
	@Autowired TrafficRegulator trafficRegulator;
	private boolean isFirstTime;
	
	
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
			rateLoader.add(rates);
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
			rateLoader.add(rates.getRates());
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
			rateLoader.add(rates.getRates());
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
