package zak;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.UserController;

public class StateUtil {
	
	private static String ucFilePath = "userList.zak";
	
	public static void saveInformation(UserControllerZak uc) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(ucFilePath);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(uc);
			objectOut.close();
			System.out.println("The Object was succesfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserControllerZak loadInformation() {
		UserControllerZak uc = new UserControllerZak();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(ucFilePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			uc = (UserControllerZak) obj;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return uc;
	}

}
