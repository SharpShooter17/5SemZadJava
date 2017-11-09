public class ProtocolContext {
	ProtocolState state;
	private User user;
	
	public ProtocolContext(User user){
		state = new ProtocolMenuState(this);
		this.user = user;
	}
	
	public void setState(ProtocolState state){
		this.state = state;
	}
	
	public ProtocolState getProtocolState(){
		return state;
	}

	public User getUser() {
		return user;
	}
}
