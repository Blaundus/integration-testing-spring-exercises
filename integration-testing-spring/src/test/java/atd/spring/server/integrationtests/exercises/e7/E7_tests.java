package atd.spring.server.integrationtests.exercises.e7;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import atd.spring.client.ClientApp;
import atd.spring.client.NonExistentRateException;
import atd.spring.client.RateNotFoundException;
import atd.spring.client.configuration.ClientConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ClientConfiguration.class})
public class E7_tests {
	
	@Autowired RestTemplate template;
	@Autowired ClientApp	client;
	MockRestServiceServer mockServer;

	@Before
	public void setup() {
		mockServer = MockRestServiceServer.createServer(template);	
	}
	
	@Test(expected = RateNotFoundException.class)
	public void whenBaseRateIsUpdated_GettingANonExistingRate_Throws() {
		mockServer
			.expect(requestTo("/rates/add"))
			.andRespond(withSuccess());

		mockServer
			.expect(requestTo("/rates/updatebase"))
			.andRespond(withSuccess());
		
		mockServer
			.expect(requestTo("/rates/currency/?name=ILS"))
			.andRespond(withStatus(HttpStatus.NOT_FOUND));

		client.addRate("ILS=2.5");
		client.updateBaseRate("USD");
		client.getRateByName("ILS");
	}

	@Test(expected = RateNotFoundException.class)
	public void whenBaseRateIsUpdated_GettingTheFormerBaseRate_Throws() {
		mockServer
		.expect(requestTo("/rates/add"))
		.andRespond(withSuccess());

	mockServer
		.expect(requestTo("/rates/updatebase"))
		.andRespond(withSuccess());
	
	mockServer
		.expect(requestTo("/rates/currency/?name=EUR"))
		.andRespond(withStatus(HttpStatus.NOT_FOUND));

		client.addRate("ILS=2.5");
		client.updateBaseRate("USD");
		client.getRateByName("EUR");
	}
}
