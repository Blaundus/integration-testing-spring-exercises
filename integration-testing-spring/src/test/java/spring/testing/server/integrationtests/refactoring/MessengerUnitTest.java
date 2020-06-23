package spring.testing.server.integrationtests.refactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import spring.testing.server.messages.CommSystem;
import spring.testing.server.messages.Messenger1;
import spring.testing.server.messages.Messenger2;
import spring.testing.server.messages.Messenger3;
import spring.testing.server.messages.Messenger4;

class MessengerUnitTest {

	@Disabled
	@Test
	void sendMessageThroughCommSystem1() {
		Messenger1 messenger = new Messenger1();
		assertEquals(CommSystem.CONFIRMED, messenger.sendMessage());
	}

	@Test
	void sendMessageThroughCommSystem2() {
		Messenger2 messenger = new Messenger2();
		CommSystem mockComm = mock(CommSystem.class);
		assertEquals(CommSystem.CONFIRMED, messenger.sendMessage(mockComm));
	}
	
	@Test
	void sendMessageThroughCommSystem3() {
		Messenger3 messenger = new Messenger3();
		messenger.commSystem = mock(CommSystem.class);
		assertEquals(CommSystem.CONFIRMED, messenger.sendMessage());
	}
	
	@Test
	void sendMessageThroughCommSystem4() {
		Messenger4 messenger = new Messenger4();
		messenger.commSystem = mock(CommSystem.class);
		assertEquals(CommSystem.CONFIRMED, messenger.sendMessage());
	}

}
