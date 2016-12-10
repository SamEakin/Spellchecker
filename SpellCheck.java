import java.util.*;
import java.io.*;

public class SpellCheck extends BuildDict{

	public static TreeMap<String, Integer> dictionary = new TreeMap<String, Integer>();

	public static void main(String[] args){

		// Read words from myDict.dat into dictionary.
		readDictFromFile();

		// Create table
		WordList table = new WordList(100);

		// Hash each word & place all into table
		readDictionaryIntoTable(table);

		table.find("apple");
		table.find("biography");
		table.find("boigraphy");

		System.out.println("Number of words = "+table.getWordCount());
	}

	public static void readDictionaryIntoTable(WordList table){
		for(String word : dictionary.keySet()){
			// hash word and add to table
			table.insertInTable(word);
		}
	}

	// Read myDict.dat into dictionary
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
}


