package atd.spring.testing.integrationtests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.configuration.ExchangeControllerConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ExchangeControllerConfiguration.class})
public class SnowExchangeApplicationTests {

	@Test
	public void canRunIntegrationTests() {
	}
}
