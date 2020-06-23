package spring.testing.server.integrationtests.refactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.messages.CommSystem;
import spring.testing.server.messages.Messenger5;

@SpringBootTest
@ContextConfiguration(classes = {Messenger5Configuration.class})
class MessengerSpringTest {
	
	@Autowired 
	Messenger5 messenger;
	
	@Test
	void test5() {
		assertEquals(CommSystem.CONFIRMED, messenger.sendMessage());
	}
}
