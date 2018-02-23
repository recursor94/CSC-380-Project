package main;

public class Airplane {
	
	private String name;
	private Seating seatArray;
	
	public Airplane(String name, int capacity) {
		
		this.name = name;
		this.seatArray = new Seating(capacity);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Seating getSeatArray() {
		return seatArray;
	}

	public void setSeatArray(Seating seatArray) {
		this.seatArray = seatArray;
	}

	@Override
	public String toString() {
		return "Airplane [name=" + name + ", seatArray=" + seatArray + "]";
	}

}
