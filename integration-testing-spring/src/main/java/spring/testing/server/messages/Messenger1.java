package spring.testing.server.messages;

public class Messenger1 {

	public String sendMessage() {
		CommSystem commSystem = new CommSystem();
		if (commSystem.send() == null)
			return CommSystem.CONFIRMED;
		return CommSystem.DENIED;
	}
}
