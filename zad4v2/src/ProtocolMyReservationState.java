
public class ProtocolMyReservationState extends ProtocolState{

	ProtocolMyReservationState(ProtocolContext context) {
		super(context);
	}

	@Override
	public String processInput(String in) {
		context.setState(new ProtocolMenuState(context));
		return context.getUser().getReservations();
	}
	
}
