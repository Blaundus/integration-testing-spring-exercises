package atd.spring.testing.integrationtests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.configuration.ControllerConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ControllerConfiguration.class})
public class ControllerTests {

	@Test
	public void canRunControllerTests() {
		assertTrue(true);
	}
}
