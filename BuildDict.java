import java.util.*;

	public class BuildDict {

	public static void main(String[] args) {

		// Test String input
		String testString = "These are some words. to,,! put? into the dictionary   are    are Are";
		String[] words = split(testString);

		// Create the Dictionary 
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		
		//Check if each word exists already (frequency >  0) & add it if (frequency = 0) 
		for (String key: words) {
			Integer frequency = dictionary.get(key);
			if (frequency == null) {
				frequency = 1;
			} else {
				frequency++;
			}
			dictionary.put(key, frequency);
		}

		System.out.println(dictionary);

	}

	static String[] split(String input) {
		// delete all non-spaces, non-letters @TODO what to do with numbers??
		input = input.replaceAll("[^\\sA-Za-z0-9]","");
		input = input.toLowerCase();
		print(input);
		String[] words = new String(input).split(" ");
		return words;
	}

	static void traverse(String[] array) {
		for (String word: array) {
			print(word);
		}
	}

	static void print(String input) {
		System.out.println(input);
	}
}
