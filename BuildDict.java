import java.util.*;
import java.io.*;

public class BuildDict {

	// create a static dictionary 
	public static TreeMap<String, Integer> dictionary = new TreeMap<String, Integer>();

	public static void main(String[] args) {

		// 1. Get an array of valid text file names from user.
		String[] files = GetInputFiles.getInput();

		// 2. Add words from file to dictionary.
		dictionary = createTree(files);

		// 3. Check size of dictionary
		System.out.println("Dictionary size = "+dictionary.size());

		// 4. Print first words from dictionary.
		printWordsFromDictionary(40);

		//5. Write dictionary to myDict.dat file.
		dictToFile();
		readDictFromFile();


	}

	public static void dictToFile(){
		// 5. Write dictionary to myDict.dat
		try{
			FileOutputStream fileOutStream = new FileOutputStream("myDict.dat");
			ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);

			objectOutStream.writeObject(dictionary);
			objectOutStream.flush();
			objectOutStream.close();
		}
		catch(IOException e){
			System.out.println("File Error!");
			e.printStackTrace();
		}
	}

	// Read myDict.dat 
	public static void readDictFromFile(){
		try{
			// Open myDict.dat and look at it's data.
			FileInputStream fileInStream = new FileInputStream("myDict.dat");
			ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);

			// Not sure how to fix this warning.
			dictionary = (TreeMap <String, Integer>) objectInStream.readObject();
		}
		catch(IOException e){
			System.out.println("File Error!");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}

	}

	public static void printWordsFromDictionary(int numberOfWords){
		// 4. Print first 40 words from dictionary.
		int numberOfKeys = 40;
		int currentKey = 0;
		for (String key: dictionary.keySet()){
			if (currentKey > numberOfKeys){
				break;
			}
			currentKey++;
			System.out.println(key);
		}
	}

	public static TreeMap<String, Integer> createTree(String[] files) /*throws IOException*/ {
		String word = "";
		for (String file: files) {
			Scanner s = null;
			try {
				s = new Scanner(new BufferedReader(new FileReader(file)));

				while (s.hasNext()) {
					word = split(s.next());
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

	// Delete all non-spaces, non-letters, & make all lowercase.
	public static String split(String input) {
		input = input.replaceAll("[^\\sA-Za-z]","");
		input = input.toLowerCase();
		return input;
	}
}
