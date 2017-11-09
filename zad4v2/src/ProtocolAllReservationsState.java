import java.util.List;

public class ProtocolAllReservationsState extends ProtocolState {

	ProtocolAllReservationsState(ProtocolContext context) {
		super(context);
	}

	@Override
	public String processInput(String in) {
		List<List<Service>> list = context.getUser().getListOfServices();
		String result = new String("\n");
		
		for (List<Service> list2 : list) {
			for (Service service : list2) {
				result += service.getTimeReservations() + "\n";
			}
		}
		context.setState(new ProtocolMenuState(context));
		return result;
	}

}
