package spring.testing.server.integrationtests.refactoring;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.testing.server.messages.CommSystem;
import spring.testing.server.messages.Messenger5;

@Configuration
public class Messenger5Configuration {

	@Bean
	public Messenger5 messenger5() {
		return new Messenger5();
	}
	@Bean
	public CommSystem dependency1() {
		return mock(CommSystem.class);
	}
}
