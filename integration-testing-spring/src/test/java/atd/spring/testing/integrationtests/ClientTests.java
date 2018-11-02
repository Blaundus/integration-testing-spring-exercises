package atd.spring.testing.integrationtests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import atd.spring.client.ClientApp;
import atd.spring.client.configuration.ClientConfiguration;
import atd.spring.testing.configuration.ExchangeControllerConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ClientConfiguration.class})
public class ClientTests {
	
	@Autowired RestTemplate template;
	@Autowired ClientApp	client;
	
	MockRestServiceServer mockServer;
	
	
	@Before
	public void setup() {
		mockServer = MockRestServiceServer.createServer(template);
	}
	
	@Test
	public void get() {
		mockServer.expect(once(), requestTo("/rates/currency/?name=EUR"))
				.andRespond(
						withSuccess("EUR = 1.000000", MediaType.TEXT_PLAIN));
		client.getRateByName("EUR");
		mockServer.verify();
	}
	
	@Test (expected = RuntimeException.class)
	public void addWithError() {
		mockServer.expect(once(), requestTo("/rates/add"))
			.andRespond(withBadRequest());
		client.addRate("Invalid");
	}

}
