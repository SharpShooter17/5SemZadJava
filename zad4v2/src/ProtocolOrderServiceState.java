import java.time.LocalDateTime;

public class ProtocolOrderServiceState extends ProtocolState {

	private enum STATE { enterIdService, enterStartTime, enterEndTime, createReservation };
	private STATE state;
	private LocalDateTime start;
	private LocalDateTime end;
	private Service service;
	
	ProtocolOrderServiceState(ProtocolContext context) {
		super(context);
		state = STATE.enterIdService;
	}
	
	@Override
	public String processInput(String in) {
		switch (state){
		case enterIdService:
			state = STATE.enterStartTime;
			return new String("Enter Id Service");
		case enterStartTime:
			try {
				int id = Integer.parseInt(in);
				service = context.getUser().getService(id);
			} catch(NumberFormatException e){
				e.printStackTrace();
				context.setState(new ProtocolMenuState(context));
				return new String("Error - Please type number!");
			} catch (ExceptionNoServiceFound e) {
				context.setState(new ProtocolMenuState(context));
				return new String("Error - Service not found!");
			}
			state = STATE.enterEndTime;
			return new String("Enter start time ("+ DateTime.DATE_PATTERN +")");
		case enterEndTime:
			if (!DateTime.validDate(in)){
				context.setState(new ProtocolMenuState(context));
				return new String("Error - Date is not valid!");
			}
			start = DateTime.parse(in);
			state = STATE.createReservation;
			return new String("Enter end time ("+ DateTime.DATE_PATTERN +")");
		case createReservation:
			context.setState(new ProtocolMenuState(context));
			if (!DateTime.validDate(in)){
				return new String("Error - Date is not valid!");
			}
			end = DateTime.parse(in);

			if (service.addReservation(start, end, context.getUser().getUserId())){
				return new String("Service reserved");
			} else {
				return new String("Error - Other Client has reservation in this same time.");
			}
		default:
			context.setState(new ProtocolMenuState(context));
			return new String("Error!");
		}
	}
}
