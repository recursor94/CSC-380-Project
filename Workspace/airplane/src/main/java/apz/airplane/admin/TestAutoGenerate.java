package apz.airplane.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import apz.airplane.model.Airplane;
import apz.airplane.model.Airport;
import apz.airplane.model.Flight;
import apz.airplane.model.Province;
import apz.airplane.model.Time;

public class TestAutoGenerate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		autoGenerate();
	}
	
	private static void autoGenerate() {
		ArrayList<Airplane> planes = new ArrayList<>();
		ArrayList<String> airlines = new ArrayList<>();
		
		//Airlines
		airlines.add("Delta");
		airlines.add("Southwest");
		airlines.add("United");
		airlines.add("Jet Blue");
		airlines.add("American");
		Random rand = new Random();
		
		//Make Planes
		for(int i = 0; i < 12; i ++) {
			int airlineNum = rand.nextInt(5);
			planes.add(new Airplane(i + 1, airlines.get(airlineNum), 80));
		}
		System.out.println(planes);
		
		//Make Airports
		ArrayList<Airport> airports = new ArrayList<>();
		ArrayList<Flight> flights = new ArrayList<>();
		
		airports.add(new Airport("JFK", Province.strNY));
		airports.add(new Airport("SYR", Province.strNY));
		airports.add(new Airport("SFO", Province.strCA));
		airports.add(new Airport("LAX", Province.strCA));
		airports.add(new Airport("AUSTIN", Province.strTX));
		airports.add(new Airport("FT MYERS", Province.strFL));
		airports.add(new Airport("PHI", Province.strPA));
		
		//Make Flights
		for (int i = 0; i < 19; i ++) {
			LocalDate depart = LocalDate.of(2018, 5, i + 11);
			LocalDate arrive = LocalDate.of(2018, 5, i + 11);
			int airportNum = rand.nextInt(5);
			Time departTime = new Time("3:00 PM");
			Time arriveTime = new Time("6:30 PM");
			for (int j = 0; j < 12; j ++) {
				flights.add(new Flight(planes.get(j), airports.get(airportNum), airports.get(airportNum + 1), arrive, depart, arriveTime, departTime, j + 1));
			}
		}
	}

}
