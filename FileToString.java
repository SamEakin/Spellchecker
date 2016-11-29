/* FileToString.java
 * Read input files(.txt) into a string for the dictionary
 */
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class FileToString {
	
	public static void main(String[] args) throws IOException {
		
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader("war.txt")));

			while (s.hasNext()) {
				System.out.println("Adding "+s.next()+" to dictionary.");
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	/*
	public static void main(String[] args) throws Exception {
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));

	}
	
	public void getInputFiles() {
		Scanner scan = new Scanner(System.in);
		String inp = "";
		String fileName = "/Users/seakin/Desktop/Project/"+inputFileName

		System.out.print("Enter the names of .txt files to read into the dictionary: ");
		
		
	}
*/
}
