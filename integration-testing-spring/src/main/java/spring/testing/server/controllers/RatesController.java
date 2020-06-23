package spring.testing.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.testing.server.compliance.logging.Registrar;
import spring.testing.server.exchange.Monitor;
import spring.testing.server.exchange.ProductExchange;
import spring.testing.server.exchange.RateParser;
import spring.testing.server.exchange.Rates;
import spring.testing.server.persistence.jdbc.RateRepository;
import spring.testing.server.rules.CompositeLineItemRule;

@RestController()
public class RatesController {

	@Autowired CompositeLineItemRule ruleManager;
	@Autowired RateParser rateLoader;
	@Autowired RateRepository rateRepository;
 	@Autowired ProductExchange exchange;
	@Autowired Monitor monitor;
	@Autowired Registrar trafficRegulator;
	private boolean isFirstTime = true;
	
	@GetMapping(value ="/rates/currency")
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

	@GetMapping(value ="/rates/all")
	public ResponseEntity<String> getAllRates() {
		if (monitor.isOk())
			return new ResponseEntity<String> ("Not implemented yet",HttpStatus.NOT_IMPLEMENTED);
		else
			return new ResponseEntity("Error", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@PostMapping(value = "/rates/addmany")
	public void addRates(@RequestParam List<String> rates) {
		if (monitor.isInitialized()) {
			isFirstTime = false;
		}
		else
		{
			monitor.startMonitoring();
		}
		
		if (monitor.isOk()) {
			if (isFirstTime)
				rateLoader.setBaseRate("EUR");
			rateLoader.parse(rates);
		}
	}

	
	@PostMapping(value = "/rates/add") 
	public ResponseEntity<?> addRate(@RequestBody String rate) {
		
		Rates rates = new Rates();
		rates.add(rate);
		isFirstTime = true;
		if (monitor.isInitialized()) {
			isFirstTime = false;
		}
		else
		{
			monitor.startMonitoring();
		}
		
		if (monitor.isOk()) {
			if (isFirstTime)
				rateLoader.setBaseRate("EUR");
			rateLoader.parse(rates.getRates());
		}
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	public void Reset() {
		this.monitor.stopMonitoring();
		isFirstTime = true;
	}

	public String getCurrencies() {
		StringBuilder result = new StringBuilder();
		List<String> currencies = rateRepository.getAllCurrenciesButBase();
		if (currencies == null )
			return "No Rates Exist";
		
		currencies.forEach(currency -> {
			if (currency != "EUR") {
				result.append(currency);
				result.append(",");
			}
		});
		return result.toString();	
	}
	
	@PostMapping(value = "/rates/updatebase")
	public void updateBaseRate(@RequestBody String newRate) {
		String nakedRate = removeQuotes(newRate);
		
		if (!isFirstTime) {
			rateRepository.deleteAllRates();
		}
		rateLoader.setBaseRate(nakedRate);
			
	}
	private String removeQuotes(String rate) {
		return rate.substring(1, rate.length()-1);
	}
}
