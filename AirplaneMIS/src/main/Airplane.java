package main;

public class Airplane {
	
	private String name;
	private Seating seats;
	
	public Airplane(String name, int capacity) {
		
		this.name = name;
		this.seats = new Seating(capacity);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Seating getSeats() {
		return seats;
	}

	public void setSeats(Seating seats) {
		this.seats = seats;
	}
	

}
