package spring.testing.server.messages;

public class Messenger2 {
	
	public String sendMessage(CommSystem commSystem ) {
		if (commSystem.send() == null)
			return CommSystem.CONFIRMED;
		return CommSystem.CONFIRMED;
	}
}
