package spring.testing.server.messages;

public class Messenger4 {
	public CommSystem commSystem;

	public String sendMessage() {
		if (getCommSystem().send() == null)
			return CommSystem.CONFIRMED;
		return CommSystem.CONFIRMED;
	}
	
	private CommSystem getCommSystem() {
		if (commSystem == null){
			commSystem= new CommSystem();
		}
		return commSystem;
	}


}
