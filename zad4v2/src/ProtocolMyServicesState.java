
public class ProtocolMyServicesState extends ProtocolState {

	ProtocolMyServicesState(ProtocolContext context) {
		super(context);
	}

	@Override
	public String processInput(String in) {
		context.setState(new ProtocolMenuState(context));
		return new String( "Your's services:\n" + context.getUser().showMyServices() );
	}

}
