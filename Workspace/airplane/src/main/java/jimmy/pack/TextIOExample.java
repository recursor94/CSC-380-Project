package jimmy.pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextIOExample {

	public static void main(String[] args) throws IOException {
		write();
		read();
	}

	private static void read() throws FileNotFoundException {
		File file = new File("text.txt");

		String line = "";
		Scanner input = new Scanner(file);

		while (input.hasNextLine()) {
			line += input.nextLine();
		}

		System.out.println(line);
	}

	public static void write() throws IOException {
		File file = new File("text.txt");
		
		FileWriter fw = new FileWriter(file);
		
		PrintWriter pw = new PrintWriter(fw);

		pw.write("HELLO there");

		pw.close();
		System.out.println("FILE WRITTEN");
	}

}
