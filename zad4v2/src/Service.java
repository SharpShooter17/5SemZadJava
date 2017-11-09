import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {
	private static int counter = 0;
	private String name;
	private int idService;
	private List<ReservationService> reservations;
	
	Service(String name) {
		reservations = new ArrayList<ReservationService>();
		this.name = name;
		this.idService = counter++;
	}

	public String getName() {
		return name;
	}

	public int getIdService() {
		return idService;
	}
	
	public String getAvaivableTime(){
		String result = new String("[x - ");
		
		Collections.sort(reservations);
				
		for (ReservationService reservationService : reservations) {
			result += reservationService.getStartTime() + "]\n";
			result += "[" + reservationService.getEndTime() + " - ";
		}
		
		result += "x]";
		
		return result;
	}
	
	public String getTimeReservations(){
		String result = new String("\tName of service: " + name + ". Id: " + idService + ".\n\tReservations:\n");
		
		for (ReservationService reservationService : reservations) {
			result += "\t\t" + reservationService.getTimeReservation() + "\n";
		}
		
		return result;
	}
	
	public boolean hasReservation(int id){
		for (ReservationService reservationService : reservations) {
			if (reservationService.getIdReservation() == id){
				return true;
			}
		}
		return false;
	}
	
	public void removeReservation(int idReservation, int idUser) throws ExceptionNoReservation{
		for (ReservationService reservationService : reservations) {
			if (reservationService.getIdReservation() == idReservation){
				if (reservationService.getIdUser() == idUser){
					reservations.remove(reservationService);
				} else {
					throw new ExceptionNoReservation();
				}
				return;
			}
		}
		throw new ExceptionNoReservation();
	}
	
	public boolean addReservation(LocalDateTime start, LocalDateTime end, int idUser) {
		ReservationService r = new ReservationService(start, end, idUser);
		
		for (ReservationService reservationService : reservations) {
			if ( reservationService.isColision(r) ){
				return false;
			}
		}
		
		reservations.add(r);
		
		return true;
	}

}
