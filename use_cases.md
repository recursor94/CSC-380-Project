# Use Cases


**Use Case UC-1:** | Booking
-------------------|---------
**Related Requirements:** | REQ1, REQ2, REQ3, REQ11, REQ15
**Initiating Actor:** | A User
**Actor's Goal** | To successfully book a flight, hotel, and/or car
**Participating Actors:** | Flight, Airplane, LocalDateTime, User, Hotel, Car, Booking, UserController
**Preconditions:** | There must be a user, a flight, and an airplane
**Postconditions:** | The user should have a booked trip saved in a trip list with automatic seating assigned.

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with flight options to choose from
**->** | 2. | The user is able to select a flight
**<-** | 3. | The user is presented with baggage options to choose from
**->** | 4. | The user is able to select a baggage plan
**<-** | 5. | The user is notified that their flights has been successfully booked




 
**Use Case UC-2:** | Payment
-------------------|---------
**Related Requirements:** | REQ8, REQ12, REQ16
**Initiating Actor:** | A User
**Actor's Goal** | To make payment for trips/hotel
**Participating Actors:** | UserController, User, Payment
**Preconditions:** | To have a user who has booked a flight (hotel/car rental optional)
**Postconditions:** | User has added payment details to pay for services available

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with input for payment details
**->** | 2. | The user inputs required information
**<-** | 3. | The user is notified that their payment method is valid and has been confirmed





**Use Case UC-3:** | Create User
-------------------|---------
**Related Requirements:** | REQ7
**Initiating Actor:** | A User
**Actor's Goal** | To successfully create a use account
**Participating Actors:** | UserController, User, SessionCache, LoginDisplay
**Preconditions:** | Application is running
**Postconditions:** | The user has created an account which they can use to login

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented proper fields to enter their details
**->** | 2. | The user inputs their user, password, and confirms their details
**<-** | 3. | (If a username exists, user must choose different username)
**<-** | 4. | (If a password is invalid, user must choose different password)
**<-** | 5. | The user inputs a valid user name and password and confirms details
**->** | 6. | The user is notified of a successful account creation and an account is generated with the input information





**Use Case UC-4:** | Login
-------------------|---------
**Related Requirements:** | REQ7
**Initiating Actor:** | A User
**Actor's Goal** | To login to the apz application
**Participating Actors:** | UserController, User, SessionCache, LoginDisplay
**Preconditions:** | Application is running and has loaded list of saved created users
**Postconditions:** | User has successfully logged into the application and can access internal services

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with an input for user, password, and a login button
**->** | 2. | The user inputs their details in proper fields and logins with the button
**<-** | 3. | The user is notified of their successful login and brought to the main application page





**Use Case UC-5:** | Create Trip
-------------------|---------
**Related Requirements:** | REQ4
**Initiating Actor:** | A User
**Actor's Goal** | To cancel a booked trip
**Participating Actors:** | UserController, User, Booking
**Preconditions:** | To have a user who has booked a trip (flight)
**Postconditions:** | The user is notified that their trip has been cancelled

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with options to cancel a trip based on a generated list
**->** | 2. | The user selects one of the trips to cancel the trip and confirms their cancellation
**<-** | 3. | The user is notified that their trip is cancelled and is removed from their list of trips 





**Use Case UC-6:** | View/Find Itinerary
-------------------|---------
**Related Requirements:** | REQ5, REQ6
**Initiating Actor:** | A User
**Actor's Goal** | To view details of booked trip
**Participating Actors:** | UserController, User, Booking, Flight, Airplane, Seating, Payment
**Preconditions:** | To have a user who has booked a trip and to have made payment.
**Postconditions:** | User is presented with a page with details of their scheduled trip

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with a list of their trips to view
**->** | 2. | The user selects a trip from the list to generate an itinerary
**<-** | 3. | An itinerary is presented to the user to view






**Use Case UC-7:** | Display Hotels
-------------------|---------
**Related Requirements:** | REQ9, REQ10
**Initiating Actor:** | A User
**Actor's Goal** | To view a list of hotels near the flight's destination
**Participating Actors:** | UserController, User, Hotel
**Preconditions:** | To have a user who has booked a trip who selects a trip as their destination target (main trip to search for nearby hotels)
**Postconditions:** | User is presented with a list of hotels to select from (to book)

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is given a list of hotels in the area nearby the destination of their trip
**->** | 2. | The user selects a hotel from the list of hotels generated.
**<-** | 3. | The selected hotel information (rates, details, etc.) is displayed to the user






**Use Case UC-8:** | Display Car Rental
-------------------|---------
**Related Requirements:** | REQ13
**Initiating Actor:** | A User
**Actor's Goal** | To view a list of rental car services nearby the flight's destination
**Participating Actors:** | UserController, User, RentalCar
**Preconditions:** | To have a user who has booked a trip who selects a trip as their destination target (main trip to search for a nearby car rental business)
**Postconditions:** | User is presented with a list of car rentals to select from (to book)

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is given a list of car rentals in the area nearby the destination of their trip
**->** | 2. | The user selects a car rental business from the list.
**<-** | 3. | The selected car rental service information (cars, rates, information) is displayed to the user






**Use Case UC-9:** | Display Car Rental
-------------------|---------
**Related Requirements:** | REQ13, REQ14
**Initiating Actor:** | A User
**Actor's Goal** | To view a list of rental car services nearby the flight's destination
**Participating Actors:** | UserController, User, RentalCar
**Preconditions:** | To have a user who has booked a trip who selects a trip as their destination target (main trip to search for a nearby car rental business)
**Postconditions:** | User is presented with a list of car rentals to select from (to book)

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is given a list of car rentals in the area nearby the destination of their trip
**->** | 2. | The user selects a car rental business from the list.
**<-** | 3. | The selected car rental service information (cars, rates, information) is displayed to the user


