public class ProtocolDeleteServiceState extends ProtocolState {

	private enum STATE { enterServiceId, deleteService };
	private STATE state; 
	
	ProtocolDeleteServiceState(ProtocolContext context) {
		super(context);
		state =  STATE.enterServiceId;
	}

	@Override
	public String processInput(String in) {
		
		switch (state){
		case enterServiceId: 
			state = STATE.deleteService;
			return new String("\nEnter id of service: ");
		case deleteService:
			context.setState(new ProtocolMenuState(context));
			int id = -1;
			try {
				id = Integer.parseInt(in);
			} catch (NumberFormatException e){
				e.printStackTrace();
				return new String("Error - Please type number!");
			}
			
			if (context.getUser().deleteService( id )){
				return new String("Service deleted");
			} else {
				return new String("Please type correct id.");
			}
		default:
			context.setState(new ProtocolMenuState(context));
			return new String("Can not delete service. Try again!");		
		}
	}

}
