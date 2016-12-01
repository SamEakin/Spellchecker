import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GetInputFiles {

	static String getInput(){
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter the names of .txt files to read into the dictionary: ");
		String input = scan.nextLine();
		String[] files = new String(input).split(" ");

		printInput(files);

		if (checkInput(files)) {
			return input;
		} else {
			return getInput();
		}

	}

	static void printInput(String[] input) {
		for (String file: input) {
			System.out.println(file);
		}
	}

	static boolean checkInput(String[] files) {
	// @TODO: This needs to make sure the file exists
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
}
