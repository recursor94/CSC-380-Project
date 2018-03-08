#Use Cases


Actor | Actor Goal | Use Case Name
----- | -----------|--------------
User  | Create an account for service use |  




**Use Case UC-1:** | Booking
-------------------|---------
**Related Requirements:** | REQ1, REQ11, REQ15
**Initiating Actor:** | A User
**Actor's Goal** | To successfully book a flight, hotel, and/or car
**Participating Actors:** | Flight, Airplane, LocalDateTime, User, Hotel, Car, Booking, UserController
**Preconditions:** | There must be a user, a flight, and an airplane
**Postconditions:** | The user should have a booked trip saved in a trip list

**Flow of Events for Main Success Scenario** |- | -
---------------------------------------------| |
**<-** | 1. | The user is presented with flight options to choose from
**->** | 2. | The user is able to select a flight
**<-** | 3. | The user is notified that their flights has been successfully booked
 
