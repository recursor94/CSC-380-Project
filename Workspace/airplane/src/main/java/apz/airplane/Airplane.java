package apz.airplane;

public class Airplane {
	
	private int planeNum;
	private String airline;
	private Seating seats;
	
	public Airplane(int planeNum, String airline, int capacity) {
		this.planeNum = planeNum;
		this.airline = airline;
		seats = new Seating(capacity);
	}

	public int getPlaneNum() {
		return planeNum;
	}

	public void setPlaneNum(int planeNum) {
		this.planeNum = planeNum;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Seating getSeats() {
		return seats;
	}

	public void setSeats(Seating seatArray) {
		this.seats = seatArray;
	}

	@Override
	public String toString() {
		return "Airplane [planeNum=" + planeNum + ", airline=" + airline + ", seatArray=" + seats + "]";
	}
	
}
