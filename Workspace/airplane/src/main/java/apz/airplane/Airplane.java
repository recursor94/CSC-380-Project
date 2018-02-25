package apz.airplane;

public class Airplane {
	
	private int planeNum;
	private String airline;
	private Seating seatArray;
	
	public Airplane(int planeNum, String airline, int capacity) {
		this.planeNum = planeNum;
		this.airline = airline;
		seatArray = new Seating(capacity);
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

	public Seating getSeatArray() {
		return seatArray;
	}

	public void setSeatArray(Seating seatArray) {
		this.seatArray = seatArray;
	}

	@Override
	public String toString() {
		return "Airplane [planeNum=" + planeNum + ", airline=" + airline + ", seatArray=" + seatArray + "]";
	}
	
}
