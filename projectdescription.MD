# Project Description

Group : { Andrew Spano, Jimmy Nguyen, Zak Gudlin }

This project is a travel assistant application which can be used by a frequent flyer to book and manage their flights. 
Users will be able to book flights based on arrival time & destination, choose their seating (with additional costs),
setup their baggage plans (with additional costs), and manage their flights as well. Management features would 
include viewing the itinerary of their flight, making changes to their booked flight, and cancellation of 
said flight. Users would be presented with a list of hotels nearby their destination airport and be able to
book with said business based on available rooms (with rates). Users would be able to manage their hotel booking
as well. Car rentals nearby the destination airport would also be available to view as well as book/manage. 
All payments would be handled through the in-house system.

## System Requirements
> 1 is highest, 4 is lowest

Identifier | Priority | Requirement
---------- | ---------| -----------
REQ1       |    1a    | The system shall allow an administrator to create flights for the user to select from.
REQ2       |    1b    | The system shall allow an administrator to create planes that can be assigned to flights.
REQ3       |    1c    | The system shall allow users to book flights (view arrival and destination).  
REQ4       |    1d    | The system shall allow users to select a seat for their flight.
REQ5       |    1e    | The system shall allow users to setup their baggage plan for their flight.
REQ6       |    1f    | The system shall allow users to cancel/change their flight.
REQ7       |    1g    | The system shall allow users to view the itinerary of their flight.
REQ8       |    1h    | The system shall allow users to find their itinerary by flight number.
REQ9       |    2a    | The system should have a login feature where users are able to create/login to their user accounts.
REQ10      |    2b    | The system shall allow users to pay for the flight using our application.
REQ11      |    2c    | The system shall allow the user to logout of the application.
REQ12      |    3a    | The system shall allow an administrator to add hotels to a list for the user to view and select from.
REQ13      |    3b    | The system shall present the user with a list of hotels by the destination airport.
REQ14      |    3c    | The system shall present the prices of the specific hotel (rooms and rates) to the user.
REQ15      |    3d    | The system shall allow users to book a hotel room for a specific amount of days.
REQ16      |    3e    | The system shall allow users to pay for their hotel booking with our application.
REQ17      |    4a    | The system shall allow an administrator to add cars to a list for the user to view and select from.
REQ18      |    4b    | The system shall allow users to see a list of car rental services by their airport destination.
REQ19      |    4c    | The system shall display various types of vehicles to rent (with rates).
REQ20      |    4d    | The system shall allow users to book their car rental.
REQ21      |    4e    | The system shall allow users to pay for car rentals using our application.

<br/><br/>

## User Stories
> Person = 1 pts,
> 1 Hour = 2 pts,
> Meeting = 3 pts

Identifier | User Story | Size
---------- | ---------- | ----
ST-1       | As an administrator of the system, I will be able to create flights for a user to select from.   | 3 pt(s)
ST-2       | As an administrator of the system, I will be able to create planes to be assigned to specific flights. | 3 pt(s)
ST-3       | As a user, I will be able to book flights that I am interested in based on arrival/destination. | 3 pt(s)
ST-4       | As a user, I will be able to choose seating for my booked flight. | 3 pt(s)
ST-5       | As a user, I will be able to choose my baggage plan for my booked flight. | 3 pt(s)
ST-6       | As a user, I will be able to cancel my booked flight. | 3 pt(s)
ST-7       | As a user, I will be able to change my booked flight. | 3 pt(s)
ST-8       | As a user, I will be able to find and view the itinerary of my booked flight. | 4 pt(s)
ST-9       | As a prospective client, I will be able to create a user account to access the services. | 4 pt(s)
ST-10      | As a user, I will be able to pay for my booked flight. | 3 pt(s)
ST-11	   | As a user, I should be able to log out of the system. | 2 pt(s)
ST-12      | As an administrator of the system, I will be able to create and add hotels to a list for the user to view and select from. | 3 pt(s)
ST-13      | As a user, I will be able to view hotels nearby my airport destination. | 6 pt(s)
ST-14      | As a user, I will be able to view rooms from a specified hotel. | 4 pt(s)
ST-15      | As a user, I will be able to select a room from a specified hotel to check-in. | 4 pt(s)
ST-16      | As a user, I will be able to pay for the booked hotel room. | 3 pt(s)
ST-17      | As an administrator of the system, I will be able to create and add cars to a list for the user to view and select from. | 3 pt(s)
ST-18      | As a user, I will be able to view car rental businesses nearby my airport destination. | 3 pt(s)
ST-19      | As a user, I will be able to view the vehicles available for rental based on rate. | 4 pt(s)
ST-20      | As a user, I will be able to rent a vehicle based on type and make. | 6 pt(s)
ST-21      | As a user, I will be able to pay for the vehicle rental. | 3 pt(s)

<br/><br/>

## Use Cases


**Use Case UC-1:** | Booking
-------------------|---------
**Related Requirements:** | REQ3, REQ4, REQ5, REQ15, REQ20  
**Initiating Actor:** | A User
**Actor's Goal:** | To successfully book a flight, hotel, and/or car
**Participating Actors:** | Flight, Airplane, LocalDateTime, User, Hotel, RentalCar, Booking, UserController
**Preconditions:** | There must be a user, a flight, and an airplane
**Postconditions:** | The user should have a booked trip saved in a trip list with automatic seating assigned

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with flight options to choose from
**->** | 2. | The user is able to select a flight
**<-** | 3. | The user is presented with baggage options to choose from
**->** | 4. | The user is able to select a baggage plan
**<-** | 5. | The user is notified that their flight has been successfully booked



<br/><br/>

 
**Use Case UC-2:** | Payment
-------------------|---------
**Related Requirements:** | REQ10, REQ16, REQ21
**Initiating Actor:** | A User
**Actor's Goal:** | To make payment for trips/hotel/rental car
**Participating Actors:** | UserController, User, Payment
**Preconditions:** | To have a user who has booked a flight (hotel/car rental optional)
**Postconditions:** | User has added payment details to pay for services available

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with input fields for payment details
**->** | 2. | The user inputs required information
**<-** | 3. | The user is notified that their payment method is valid and has been confirmed



<br/><br/>


**Use Case UC-3:** | Create User
-------------------|---------
**Related Requirements:** | REQ9
**Initiating Actor:** | A User
**Actor's Goal:** | To successfully create a user account
**Participating Actors:** | UserController, User, SessionCache, LoginDisplay
**Preconditions:** | Application is running
**Postconditions:** | The user has created an account which they can use to login

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with proper fields to enter their details
**->** | 2. | The user inputs their user name, password, and confirms their details
**<-** | 3. | (If a user name exists, user must choose different username)
**<-** | 4. | (If a password is invalid, user must choose different password)
**->** | 5. | The user inputs a valid user name and password and confirms details
**<-** | 6. | The user is notified of a successful account creation and an account is generated with the input information


<br/><br/>


**Use Case UC-4:** | Login/Logout
-------------------|---------
**Related Requirements:** | REQ9, REQ11
**Initiating Actor:** | A User
**Actor's Goal:** | To login to the APZ application
**Participating Actors:** | UserController, User, SessionCache, LoginDisplay
**Preconditions:** | Application is running and has loaded a list of saved created users
**Postconditions:** | User has successfully logged into the application and can access internal services

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with an input for user, password, and an action button
**->** | 2. | The user inputs their details in proper fields and action with the button
**<-** | 3. | The user is notified of their successful action and brought to the main application page


<br/><br/>


**Use Case UC-5:** | Cancel Trip
-------------------|---------
**Related Requirements:** | REQ3, REQ4, REQ6
**Initiating Actor:** | A User
**Actor's Goal:** | To cancel a booked trip
**Participating Actors:** | UserController, User, Booking
**Preconditions:** | To have a user who has booked a trip (flight)
**Postconditions:** | The user is notified that their trip has been cancelled

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with options to cancel a trip based on a generated list
**->** | 2. | The user selects one of the trips to cancel and confirms their cancellation
**<-** | 3. | The user is notified that their trip is cancelled and it is removed from their list of trips 

<br/><br/>



**Use Case UC-6:** | View/Find Itinerary
-------------------|---------
**Related Requirements:** | REQ7, REQ8
**Initiating Actor:** | A User
**Actor's Goal:** | To view details of a booked trip
**Participating Actors:** | UserController, User, Booking, Flight, Airplane, Seating, Payment
**Preconditions:** | To have a user who has booked a trip and has made payment
**Postconditions:** | User is presented with a page with details of their scheduled trip

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is presented with a list of their trips to view
**->** | 2. | The user selects a trip from the list to generate an itinerary
**<-** | 3. | An itinerary is presented to the user to view



<br/><br/>


**Use Case UC-7:** | Display Hotels
-------------------|---------
**Related Requirements:** | REQ13, REQ14
**Initiating Actor:** | A User
**Actor's Goal:** | To view a list of hotels near the flight's destination
**Participating Actors:** | UserController, User, Hotel
**Preconditions:** | To have a user who has booked a trip who selects a trip as their destination target (main trip to search for nearby hotels)
**Postconditions:** | User is presented with a list of hotels to select from (to book)

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is given a list of hotels in the area nearby the destination of their trip
**->** | 2. | The user selects a hotel from the list of hotels generated
**<-** | 3. | The selected hotel information (rates, details, etc.) is displayed to the user



<br/><br/>


**Use Case UC-8:** | Display Car Rental
-------------------|---------
**Related Requirements:** | REQ18, REQ19, REQ20
**Initiating Actor:** | A User
**Actor's Goal:** | To view a list of rental car services nearby the flight's destination
**Participating Actors:** | UserController, User, RentalCar
**Preconditions:** | To have a user who has booked a trip who selects a trip as their destination target (main trip to search for a nearby car rental business)
**Postconditions:** | User is presented with a list of car rentals to select from (to book)

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**<-** | 1. | The user is given a list of car rentals in the area nearby the destination of their trip
**->** | 2. | The user selects a car rental business from the list
**<-** | 3. | The selected car rental service information (cars, rates, information) is displayed to the user


<br/><br/>


**Use Case UC-9:** | Administrative Create
-------------------|---------
**Related Requirements:** | REQ1, REQ2, REQ12, REQ17
**Initiating Actor:** | An Admin
**Actor's Goal:** | To create flights, car rentals, and hotel renting
**Participating Actors:** | Flight, Airplane, Seating, RentalCar, Hotel
**Preconditions:** | To have the application running
**Postconditions:** | To generate a flight/car/hotel in the area

**Flow of Events for Main Success Scenario** | X | X
---------------------------------------------|---|----
**->** | 1. | The admin inputs details for a flight to be created (or car/hotel rental)
**->** | 2. | The admin confirms the details entered
**<-** | 3. | A flight/car/hotel rental is generated and is available for service

<br/><br/>

## Traceability matrix

Requirements|UC-1|UC-2|UC-3|UC-4|UC-5|UC-6|UC-7|UC-8|UC-9
------------|----|----|----|----|----|----|----|----|----
REQ1		|    |	  |    |	|	 |	  |	   |    |  X
REQ2		|    |	  |	   |	|    |    |    |    |  X
REQ3		|  X |    |    |    |  X |    |    |    | 
REQ4		|  X |    |    |    |  X |    |    |    |
REQ5		|  X |    |    |    |    |    |    |    |
REQ6		|    |	  |	   |	|  X |    |	   |    |
REQ7		|	 |    |    |    |    |  X |    |    |
REQ8		|    |    |    |    |    |  X |    |    |
REQ9		|    |    |  X |  X |    |    |    |    |
REQ10		|    |  X |    |    |    |    |    |    |
REQ11		|    |    |    |  X |    |    |    |    | 
REQ12		|	 |    |    |    |    |    |    |    |  X
REQ13		|	 |    |    |    |    |    |  X |    |
REQ14		|	 |    |    |    |    |    |  X |    | 
REQ15		|  X |    |    |    |    |    |    |	|
REQ16		|  	 |  X |    |    |    |    |    |    |
REQ17		|	 |    |    |    |    |    |    |    |  X 
REQ18		|	 |    |    |    |    |    |    |  X |
REQ19		|	 |    |    |    |    |    |    |  X |
REQ20		|  X |    |    |    |    |    |    |  X |
REQ21		|	 |	X |	   |    |    |    |    |    |
