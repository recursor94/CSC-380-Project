package main;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a username: ");
		String username = input.next();
		
		System.out.print("Enter a password: ");
		String password = input.next();
		
		User user = new User(username, password);
		System.out.println("User created! " + user);
	}

}
