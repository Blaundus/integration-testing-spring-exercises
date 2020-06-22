package spring.testing.server.messages;

public class Messenger3 {
	public CommSystem commSystem;

	public String sendMessage() {
		if (commSystem.send() == null)
			return CommSystem.CONFIRMED;
		return CommSystem.CONFIRMED;
	}

}
