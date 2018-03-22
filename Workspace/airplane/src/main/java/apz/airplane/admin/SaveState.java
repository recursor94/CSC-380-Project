package apz.airplane.admin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Flight;

public class SaveState {

//	public SaveState(ArrayList<Airplane> plane) throws IOException {
//		FileOutputStream fileOut = new FileOutputStream("planeobject.apz");
//		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//		objectOut.writeObject(plane);
//		objectOut.close();
//		System.out.println("The Object was succesfully written to a file");
//	}

	public static void savePlane(ArrayList<Airplane> plane) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream("planeobject.apz");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(plane);
			objectOut.close();
			System.out.println("The Object was succesfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
