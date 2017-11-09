import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Reservation implements Comparable<Reservation>{
	
	private Timestamp startTime;
	private Timestamp endTime;
	
	@SuppressWarnings("deprecation")
	public Reservation(LocalDateTime start, LocalDateTime end){
		setStartTime(new Timestamp(start.getYear() - 1900, start.getMonthValue() - 1, start.getDayOfMonth(), start.getHour(), start.getMinute(), start.getSecond(), start.getNano()));
		setEndTime(new Timestamp(end.getYear() - 1900, end.getMonthValue() - 1, end.getDayOfMonth(), end.getHour(), end.getMinute(), end.getSecond(), end.getNano()));
		
		if (getStartTime().after(getEndTime())){
			Timestamp tmp = endTime;
			setEndTime(startTime);
			setStartTime(tmp);
		}
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	public boolean isColision(Reservation r){
		if ( (getEndTime().before(r.getEndTime()) == true) && (getStartTime().after(r.getStartTime()) == true) ){
			return true;
		} 
		if ( (getStartTime().before(r.getEndTime()) == true) && (getEndTime().after(r.getEndTime()) == true) ){
			return true;
		}
		if ((getStartTime().before(r.getStartTime()) == true) && (getEndTime().after(r.getStartTime()) == true)){
			return true;
		}
		if ( (r.getEndTime().before(getEndTime()) == true) && (r.getStartTime().after(getStartTime()) == true) ){
			return true;
		} 
		
		return false;
	}

	@Override
	public int compareTo(Reservation o) {
		if ( isColision(o) ){
			return 0;
		} else if (getStartTime().before(o.getStartTime())){
			return -1;
		} else {
			return 1;
		}
	}
}
