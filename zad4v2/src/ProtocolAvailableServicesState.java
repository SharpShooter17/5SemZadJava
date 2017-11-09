import java.util.List;

public class ProtocolAvailableServicesState extends ProtocolState {

	ProtocolAvailableServicesState(ProtocolContext context) {
		super(context);
	}

	@Override
	public String processInput(String in) {
		context.setState( new ProtocolMenuState(context) );
		String result = new String("Avaivable services:\n");
		
		for ( List<Service> list : context.getUser().getListOfServices() ){
			for (Service service : list) {
				result += service.getName() + ". Free time:" + "\n";
				result += service.getAvaivableTime() + "\n";
			}
		}	
		
		return result;
	}
}
