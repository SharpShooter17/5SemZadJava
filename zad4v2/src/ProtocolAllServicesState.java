
public class ProtocolAllServicesState extends ProtocolState {

	ProtocolAllServicesState(ProtocolContext context) {
		super(context);
	}

	@Override
	public String processInput(String in) {
		context.setState(new ProtocolMenuState(context));
		return new String( "Services:\n" + context.getUser().getAllServices());
	}

}
