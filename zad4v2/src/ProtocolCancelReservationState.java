
public class ProtocolCancelReservationState extends ProtocolState {

	ProtocolCancelReservationState(ProtocolContext context) {
		super(context);
		state = STATE.getId;
	}

	private enum STATE { getId, cancelReservation };
	STATE state;
	@Override
	public String processInput(String in) {
		switch (state) {
		case getId:
			state = STATE.cancelReservation;
			return new String("Type reservation ID");
		case cancelReservation:
			context.setState(new ProtocolMenuState(context));
			 
			try {
				int id = Integer.parseInt(in);
				context.getUser().cancelReservation(id);
			} catch (NumberFormatException e) {
				return new String("Error - Please type number!");
			} catch (ExceptionNoReservation e) {
				return new String("Error - Reservation ID does not exist!");
			}
			return new String("Reservation canceled");
		default:
			return new String("Error");
		}
	}
}
