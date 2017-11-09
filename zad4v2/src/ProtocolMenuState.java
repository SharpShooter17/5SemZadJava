import java.util.Vector;

public class ProtocolMenuState extends ProtocolState {
	private static Vector<String> menu = new Vector<String>();
	private static String menuStr;
	
	static { 
		menu.add("Menu");
		menu.add("New service");
		menu.add("Delete service");
		menu.add("Order service");
		menu.add("Cancel reservation");
		menu.add("All services");
		menu.add("Available services");
		menu.add("My services");
		menu.add("My reservations");
		menu.add("All reservations");
		menu.add("Close");
		
		menuStr = new String();
		menuStr = "\n";
		for (int i = 0; i < menu.size(); i++){
			menuStr += "[" + i + "]\t" + menu.get(i) + "\n"; 
		}
	};
       
	public ProtocolMenuState(ProtocolContext context) {
		super(context);
	}
	
	@Override
	public String processInput(String in) {
		
		try {
			int num = Integer.parseInt(in);
			if ( num < menu.size() && num >= 0 ){
				in = menu.get(num);
			}
		} catch (NumberFormatException e) {
			//Nothing to do
		}
		
		in = in.toLowerCase();
		
		if (in.equals("menu")){
			return menuStr;
		} else if (in.equals("new service")){
			context.setState(new ProtocolNewServiceState(context));
		} else if (in.equals("delete service")){
			context.setState(new ProtocolDeleteServiceState(context));
		} else if (in.equals("my services")){
			context.setState(new ProtocolMyServicesState(context));
		} else if (in.equals("all services")){
			context.setState(new ProtocolAllServicesState(context));
		} else if (in.equals("order service")){
			context.setState(new ProtocolOrderServiceState(context));
		} else if (in.equals("cancel reservation")){
			context.setState(new ProtocolCancelReservationState(context));
		} else if (in.equals("my reservations")){
			context.setState(new ProtocolMyReservationState(context));
		} else if (in.equals("all reservations")){
			context.setState(new ProtocolAllReservationsState(context));
		} else if (in.equals("close")){
			context.setState(null);
			return null;
		} else if (in.equals("available services")) {
			context.setState(new ProtocolAvailableServicesState(context));
		} else {
			return new String("Error - Command not found!");
		}
		
		return context.getProtocolState().processInput(in);
	}

}
