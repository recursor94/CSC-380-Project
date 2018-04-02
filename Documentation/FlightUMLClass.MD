Flight
--
-serialVersionUID: long <br/>
-plane: Airplane<br/>
-departureAirport: Airport<br/>
-destinationAirport: Airport<br/>
-arriveDate: LocalDate<br/>
-departureDate: LocalDate<br/>
-arrivalTime: Time<br/>
-departureTime: Time<br/>
-flightNum: int
--
+Flight(Airplane, Airport, Airport, LocalDate, LocalDate, Time, Time, int)<br/>
+getPlane(): Airplane<br/>
+setPlane(Airplane): void<br/>
+getDestinationAirport(): Airport<br/>
+setDestinationAirport(Airport): void<br/>
+getDepartureAirport(): Airport<br/>
+setDepartureAirport(Airport): void<br/>
+getArriveDate(): LocalDate<br/>
+setArriveDate(LocalDate): void<br/>
+getDepartureDate(): LocalDate<br/>
+setDepartureDate(LocalDate): void<br/>
+getArrivalTime(): Time<br/>
+setArrivalTime(Time): void<br/>
+getDepartureTime(): Time<br/>
+setDepartureTime(Time): void<br/>
+getFlightNum(): int<br/>
+setFlightNum(int): void<br/>
+toString(): String

