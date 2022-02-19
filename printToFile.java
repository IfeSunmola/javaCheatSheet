

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class printToFile {
	public static void main (String [] args) throws FileNotFoundException {
		String fileLocation = "src/assignment4/A4Q1input.txt";
		PrintStream out = new PrintStream(new FileOutputStream("src/assignment4/output2.txt"));
		System.setOut(out);
		// do stuff

		String goodOutput = dummy("src/assignment4/goodOutput.txt");
		String newOutput = dummy("src/assignment4/output2.txt");

		System.setOut(System.out);
		System.err.println(goodOutput.equals(newOutput));
	}

	private static String dummy (String file) {
		Scanner sc;
		String output = "";
		try {
			sc = new Scanner(new File(file));
			while (sc.hasNextLine()) {
				String temp = sc.nextLine();
				output += temp;
				if (sc.hasNextLine()) {
				}

			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return output;
	}
}
