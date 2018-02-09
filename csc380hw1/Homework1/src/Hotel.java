/**
 * 
 * @author Andrew Spano
 * Created 2/9/2018
 * @version 0.0.0.1
 */
public class Hotel {
	private Room[] rooms; //rooms will have cost associated with them
	//I'm not making it an arraylist because it shouldn't be possible to add rooms after hotel is created
	private String location;
	private ArrayList<Review> reviews; //reviews users have posted--will include score and customer comment.

	/**
	 * Calculate the average of all reviews for the hotel
	 * @return
	 */
	public double getAverageReviewScore() {
		return 0;
	}
}
