package spring.testing.client.integrationtests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import spring.testing.client.ClientApp;
import spring.testing.client.configuration.ClientConfiguration;

@SpringBootTest
@ContextConfiguration(classes= {ClientConfiguration.class})
public class ClientTests {
	
	@Autowired ClientApp	client;
	@Autowired RestTemplate restTemplate;
	MockRestServiceServer mockServer;
	
	
	@BeforeEach
	public void setup() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.reset();
	}
	
	@AfterEach 
	public void tearDown() {
		mockServer.verify();
	}

	@Test
	public void get_ServerCalledCorrectly() {
		mockServer
				.expect(once(), requestTo("/rates/currency/?name=EUR"))
				.andRespond(
						withSuccess("EUR = 1.000000", MediaType.TEXT_PLAIN));
		client.getRateByName("EUR");
	}
	
	@Test 
	public void add_ThrowsOnError() {
		mockServer.expect(once(), requestTo("/rates/add"))
			.andRespond(withBadRequest());
		
		assertThrows(RuntimeException.class, 
				() -> {client.addRate("Invalid");}
				);
	}
}
