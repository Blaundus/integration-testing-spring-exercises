package spring.testing.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ClientApp {
	
	@Autowired RestTemplate template;
	
	public String getRateByName(String name) {
		try {
			ResponseEntity<String> result =  
				template.getForEntity("/rates/currency/?name=" + name, String.class);
			return result.getBody();
		}
		catch (HttpClientErrorException e) {
			throwIfNotFound(e);
		}
		return "";
		
	}

	public void addRate(String newRate) {
		ResponseEntity result = 
				template.postForEntity("/rates/add", newRate, String.class);
	}

	
	private void throwIfNotFound(HttpClientErrorException e) {
		if (e.getStatusCode()== HttpStatus.NOT_FOUND)
			throw new RateNotFoundException();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// e7
	public void updateBaseRate(String newRate) {
		ResponseEntity result = 
				template.postForEntity("/rates/updatebase", newRate, String.class);
	}
}
