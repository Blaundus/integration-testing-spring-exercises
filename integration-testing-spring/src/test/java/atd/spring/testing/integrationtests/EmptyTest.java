package atd.spring.testing.integrationtests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.configuration.MinimalConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {MinimalConfiguration.class})
public class EmptyTest {

	@Test
	public void canRunIntegrationTests() {
		assertTrue(true);
	}
}
