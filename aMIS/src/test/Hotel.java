/**
 * 
 * @author Andrew Spano
 * Created 2/9/2018
 * @version 0.0.0.1
 */
public class Hotel {
	
	//private Room[] rooms; //rooms will have cost associated with them
	//I'm not making it an arraylist because it shouldn't be possible to add rooms after hotel is created
	
/* Just an FYI, when you think about data types you should use what will be maintainable and expandable in the future.
	This means having an array as opposed to an arraylist is very restricting as a hotel can expand and have more or 
	less rooms. Having an array also makes adding/removing more of a hassle as we are looking to use the functions
	of an array list but with a different data structure. 
*/ 

	private String location;
	//private ArrayList<Review> reviews; //reviews users have posted--will include score and customer comment.

	public Hotel(int numOfRooms) {
		//rooms = new Room[numOfRooms];
	}


	/**
	 * Calculate the average of all reviews for the hotel
	 * @return
	 */
	public double getAverageReviewScore() {
		return 0;
	}
}
