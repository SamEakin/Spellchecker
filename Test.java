import java.io.IOException;
import java.util.Scanner;
public class Test {

	public static void main(String[] args) throws IOException {
		
		String files = getInput();
		System.out.println(files);

	}

	static String getInput(){
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter the names of .txt files to read into the dictionary: ");
		String input = scan.nextLine();
		String[] files = new String(input).split(" ");

		/* check validity of input
		 * should be formatted:
		 * file1.txt file.txt file3.txt
		 * no other characters
		 */



		if (checkInput(input)) {
			return input;
		} else {
			return "bad input";
		}

	}

	static boolean checkInput(String input) {
	// @TODO
	// This needs to make sure the file exists

		/*
		for (Character c: input) {
			System.out.println(c);
		}
		*/

		return true;
	}
}
