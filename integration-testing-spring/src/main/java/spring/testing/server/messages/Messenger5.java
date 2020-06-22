package spring.testing.server.messages;

import org.springframework.beans.factory.annotation.Autowired;

public class Messenger5 {
	@Autowired
	private CommSystem commSystem;
	
	public String sendMessage() {
		if (getCommSystem().send() == null)
			return CommSystem.CONFIRMED;
		return CommSystem.CONFIRMED;
	}

	
	private CommSystem getCommSystem() {
		if (commSystem == null){
			commSystem = new CommSystem();
		}
		return commSystem;
	}

}
