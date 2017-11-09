public class ProtocolNewServiceState extends ProtocolState {

	private enum STATE { enterName, createService };
	STATE state;
	
	ProtocolNewServiceState(ProtocolContext context) {
		super(context);
		state = STATE.enterName;
	}
	
	@Override
	public String processInput(String in) {
		switch(state){
		case enterName:
			state = STATE.createService;
			return new String("Enter name of service: ");
		case createService:
			context.getUser().newService(in);
			System.out.println("new service: " + in);
			context.setState(new ProtocolMenuState(context));
			return new String("Ok");
		default:
			context.setState(new ProtocolMenuState(context));
			return new String("Can not add new service. Try again!");
		}
	}
}
