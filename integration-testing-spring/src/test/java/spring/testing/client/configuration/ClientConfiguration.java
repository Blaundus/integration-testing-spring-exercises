package spring.testing.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import spring.testing.client.ClientApp;
@Configuration
public class ClientConfiguration {
	
	@Bean
	public ClientApp client() {
		return new ClientApp();
	}
	
	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}	
	
}
