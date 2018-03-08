# Use Cases


**Use Case UC-1:** | Booking
-------------------|---------
**Related Requirements:** | REQ3, REQ4, REQ5, REQ15, 
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



<br/><br/><br/><br/><br/><br/>

 
**Use Case UC-2:** | Payment
-------------------|---------
**Related Requirements:** | REQ10, REQ16, REQ18, REQ21
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



<br/><br/><br/><br/><br/><br/>


**Use Case UC-3:** | Create User
-------------------|---------
**Related Requirements:** | REQ9
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


<br/><br/><br/><br/><br/><br/>


**Use Case UC-4:** | Login/Logout
-------------------|---------
**Related Requirements:** | REQ9, REQ11
**Initiating Actor:** | A User
**Actor's Goal** | To login to the apz application
**Participating Actors:** | UserController, User, SessionCache, LoginDisplay
**Preconditions:** | Application is running and has loaded list of saved created users
**Postconditions:** | User has successfully logged into the application and can access internal services

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with an input for user, password, and an action button
**->** | 2. | The user inputs their details in proper fields and action with the button
**<-** | 3. | The user is notified of their successful action and brought to the main application page


<br/><br/><br/><br/><br/><br/>


**Use Case UC-5:** | Cancel Trip
-------------------|---------
**Related Requirements:** | REQ3, REQ4, REQ6
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

<br/><br/><br/><br/><br/><br/>



**Use Case UC-6:** | View/Find Itinerary
-------------------|---------
**Related Requirements:** | REQ7, REQ8
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



<br/><br/><br/><br/><br/><br/>


**Use Case UC-7:** | Display Hotels
-------------------|---------
**Related Requirements:** | REQ13, REQ14
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



<br/><br/><br/><br/><br/><br/>


**Use Case UC-8:** | Display Car Rental
-------------------|---------
**Related Requirements:** | REQ18, REQ19, REQ20
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


<br/><br/><br/><br/><br/><br/>


**Use Case UC-9:** | Administrative Create
-------------------|---------
**Related Requirements:** | REQ1, REQ2, REQ12, REQ17
**Initiating Actor:** | An Admin
**Actor's Goal** | To create flights, car rentals, and hotel renting
**Participating Actors:** | Flight, Airplane, Seating, RentalCar, Hotel
**Preconditions:** | To have a the application running
**Postconditions:** | To generate a flight/car/hotel in the area

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The admin inputs details a flight to be created (or car/hotel rental)
**->** | 2. | The admin confirms the details entered
**<-** | 3. | A flight/car/hotel rental is generated and is available for service



