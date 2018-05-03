package apz.airplane.user.gui.booking;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.Flight;

public class BookingTableData {

	private Airport departureAirport, destinationAirport;
	private LocalDate arriveDate, departureDate;
	//private Time arrivalTime, departureTime;
	private String arrivalTime, departureTime;
	private int flightNumber;
	//private int planeNum;
	private String airline;
	private LocalDate bookDate;
	private Flight flight;
	private String tripCost;
	private Booking bookingRef;
	
	public BookingTableData(Booking booking) {
		setFlight(booking.getFlight());
		setDepartureAirport(getFlight().getDepartureAirport());
		setDestinationAirport(getFlight().getDestinationAirport());
		setArriveDate(getFlight().getArriveDate());
		setDepartureDate(getFlight().getDepartureDate());
		setArrivalTime(getFlight().getArrivalTime().getTimeString());
		setDepartureTime(getFlight().getArrivalTime().getTimeString());
		setFlightNumber(getFlight().getFlightNum());
//		setPlaneNum(flight.getPlane().getPlaneNum());
		setAirline(getFlight().getPlane().getAirline());	
		setBookDate(booking.getBookDate());
		setTripCost(booking.getTripCost());
		setBookingRef(booking);
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public LocalDate getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(LocalDate arriveDate) {
		this.arriveDate = arriveDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNum) {
		this.flightNumber = flightNum;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}

	public String getTripCost() {
		return tripCost;
	}

	public void setTripCost(double tripCost) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		this.tripCost = formatter.format(tripCost);
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Booking getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(Booking bookingRef) {
		this.bookingRef = bookingRef;
	}
}
