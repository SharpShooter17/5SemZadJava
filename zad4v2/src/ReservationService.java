import java.time.LocalDateTime;

public class ReservationService extends Reservation {
	private int idUser;
	private int idReservation;
	private static int counterReservation = 0;
	
	public ReservationService(LocalDateTime start, LocalDateTime end, int idUser) {
		super(start, end);
		this.idUser = idUser;
		this.idReservation = counterReservation++;
	}

	public int getIdUser() {
		return idUser;
	}
	
	public int getIdReservation(){
		return idReservation;
	}
	
	public String getTimeReservation() {
		return new String( "Id: " + idReservation + " TIME: " + DateTime.format(getStartTime().toLocalDateTime()) + " - " + DateTime.format(getEndTime().toLocalDateTime()));
	}
}
