import java.util.*;
import java.io.File;
import java.io.IOException;
//import java.util.Scanner;

public class GetInputFiles {

	// Takes user input filenames and returns filenames as an array.
	static String[] getInput(){
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter the names of .txt files to read into the dictionary: ");

		String input = scan.nextLine();
		String[] files = new String(input).split(" ");

		if (checkInput(files)) {
			return files;
		} else {
			return getInput();
		}
	}

	// Makes sure each user entry exists within the current directory as a text file.
	static boolean checkInput(String[] files) {
		for (String fileName: files) {
			File f = new File(fileName);

			if (f.exists()) {
				System.out.println(fileName+" exists.");
			} else {
				System.out.println(fileName+" does not exist.");
				return false;
			}

		}
		return true;
	}

	// For Testing Purposes Only
	static void printInput(String[] input) {
		for (String file: input) {
			System.out.println(file);
		}
	}
}


