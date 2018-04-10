package apz.airplane.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.Flight;
import apz.airplane.UserController;

public class State {
	
	private static String ucFilePath = "userList.apz";
	private static String flightObject = "flightList.apz";
	
	public static void saveInformation(UserController uc) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(ucFilePath);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(uc);
			objectOut.close();
			System.out.println("The Object was successfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserController loadInformation() {
		UserController uc = new UserController();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(ucFilePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			uc = (UserController) obj;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return uc;
	}
	
	public static ArrayList<Flight> loadFlights() {
		if (new File(flightObject).exists()) {
			ArrayList<Flight> flightList = new ArrayList<>();
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(flightObject);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);

				Object obj = objectIn.readObject();
				System.out.println("The Object has been read from the file");
				objectIn.close();
				flightList = (ArrayList<Flight>) obj;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return flightList;
		}
		return new ArrayList<Flight>();

}
}