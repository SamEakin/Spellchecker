import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class Part1 {

	// Main
	public static void main(String[] args) {
		// 1. Get an array of valid text file names from user.
		String[] files = GetInputFiles.getInput();

		// 2. Create dictionary
		//HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		
		// 3. Parse each text file into a formatted string.
		checkFiles(files);
		//HashMap<String, Integer> dictionary = createDict(files);
		TreeMap<String, Integer> dictionary = createTree(files);
		System.out.println("DICTIONARY: -------------------------");
		//System.out.println(dictionary.entrySet());
		//System.out.println(dictionary.keySet());
		//System.out.println(dictionary.entrySet());
		int numberOfKeys = 100;
		int currentKey = 0;
		for (String key: dictionary.keySet()){
			if (currentKey > numberOfKeys){
				break;
			}
			currentKey++;
			System.out.println(key);
		}

		/*for (int wordCount = 0; wordCount < 200; wordCount++){
			int tempCount = 0;
			for (Integer count: dictionary.values()) {
				if (count == wordCount){
					tempCount++;
				}
			}

			System.out.println("Words repeated "+wordCount+" times:"+tempCount);
		}
		*/

		//String testFileString = FileToString.formatString(files);
		//System.out.println(testFileString);
	}

	public static TreeMap<String, Integer> createTree(String[] files) /*throws IOException*/ {
		TreeMap<String, Integer> dictionary = new TreeMap<String, Integer>();
		String word = "";
		for (String file: files) {
			Scanner s = null;
			try {
				s = new Scanner(new BufferedReader(new FileReader(file)));

				while (s.hasNext()) {
					word = split(s.next());
					//bigFuckingString += s.next();
					// @TODO: Call trim method & addToDictionary method
					//BuildDict.	
					Integer frequency = dictionary.get(word);
					if (frequency == null) {
						frequency = 1;
					} else {
						frequency++;
					}
					dictionary.put(word, frequency);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if (s != null) {
					s.close();
				}
			}
		}
		return dictionary;
	}

	public static HashMap<String, Integer> createDict(String[] files) /*throws IOException*/ {
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		String word = "";
		for (String file: files) {
			Scanner s = null;
			try {
				s = new Scanner(new BufferedReader(new FileReader(file)));

				while (s.hasNext()) {
					word = split(s.next());
					//bigFuckingString += s.next();
					// @TODO: Call trim method & addToDictionary method
					//BuildDict.	
					Integer frequency = dictionary.get(word);
					if (frequency == null) {
						frequency = 1;
					} else {
						frequency++;
					}
					dictionary.put(word, frequency);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if (s != null) {
					s.close();
				}
			}
		}
		return dictionary;
	}

	public static String split(String input) {
		// delete all non-spaces, non-letters @TODO what to do with numbers??
		input = input.replaceAll("[^\\sA-Za-z0-9]","");
		input = input.toLowerCase();
		System.out.println(input);
		return input;
	}

	public static void checkFiles(String[] files) {
		for (String file: files) {
			System.out.println(file);
		}
	}
	/*
	static String add(String word) {
		Integer frequency = dictionary.get(key);
		if (frequency == null) {
			frequency = 1;
		} else {
			frequency++;
		}
		dictionary.put(key, frequency);
	}*/


}


