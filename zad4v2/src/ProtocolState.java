public abstract class ProtocolState {
	ProtocolContext context;
	
	ProtocolState(ProtocolContext context){
		this.context = context; 
	}
	
	public abstract String processInput(String in);
}
