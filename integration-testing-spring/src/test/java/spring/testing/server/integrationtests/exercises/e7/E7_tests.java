package spring.testing.server.integrationtests.exercises.e7;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import spring.testing.client.ClientApp;
import spring.testing.client.RateNotFoundException;
import spring.testing.client.configuration.ClientConfiguration;

@SpringBootTest
@ContextConfiguration(classes = { ClientConfiguration.class })
public class E7_tests {

	@Autowired
	RestTemplate template;
	@Autowired
	ClientApp client;
	MockRestServiceServer mockServer;

	@BeforeEach
	public void setup() {
		mockServer = MockRestServiceServer.createServer(template);
	}

	@Test
	public void whenBaseRateIsUpdated_GettingANonExistingRate_Throws() {
		mockServer.expect(requestTo("/rates/add")).andRespond(withSuccess());

		mockServer.expect(requestTo("/rates/updatebase")).andRespond(withSuccess());

		mockServer.expect(requestTo("/rates/currency/?name=ILS")).andRespond(withStatus(HttpStatus.NOT_FOUND));

		client.addRate("ILS=2.5");
		client.updateBaseRate("USD");
		assertThrows(RateNotFoundException.class, () -> {
			client.getRateByName("ILS");
		});
	}

	@Test
	public void whenBaseRateIsUpdated_GettingTheFormerBaseRate_Throws() {
		mockServer.expect(requestTo("/rates/add")).andRespond(withSuccess());

		mockServer.expect(requestTo("/rates/updatebase")).andRespond(withSuccess());

		mockServer.expect(requestTo("/rates/currency/?name=EUR")).andRespond(withStatus(HttpStatus.NOT_FOUND));

		client.addRate("ILS=2.5");
		client.updateBaseRate("USD");
		assertThrows(RateNotFoundException.class, () -> {
			client.getRateByName("EUR");
		});
	}
}
