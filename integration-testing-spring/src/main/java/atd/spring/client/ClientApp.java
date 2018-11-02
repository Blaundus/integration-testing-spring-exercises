package atd.spring.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

public class ClientApp {
	
	@Autowired RestTemplate template;
	
	public String getRateByName(String name) {
		ResponseEntity<String> result =  template.getForEntity("/rates/currency/?name=EUR", String.class);
		return result.getBody();
	}
	
	public void addRate(String newRate) {
		ResponseEntity result = template.postForEntity("/rates/add", newRate, String.class);
		if (result.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException();
	}
}
