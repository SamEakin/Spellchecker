import java.util.*;
import java.io.*;

public class SpellCheck extends BuildDict{

	public static TreeMap<String, Integer> dictionary = new TreeMap<String, Integer>();

	public static void main(String[] args){

		// Read words from myDict.dat into dictionary.
		readDictFromFile();

		// Create table &
		// Hash each word inside as long as load factor acceptable
			
		int size = 1000;
		WordList table = readDictionaryIntoTable(size);

		//table.find("apple");
		//table.find("biography");
		//table.find("boigraphy");

		System.out.println("Number of words = "+table.getWordCount());
	}

	public static void printKeys(){
		for(String word : dictionary.keySet())
			System.out.println(word);
	}

	public static WordList readDictionaryIntoTable(int size){

		// Construct table
		WordList table = new WordList(size);
		int wordCount = 0;

		// @TODO: WHY ISNT THIS WORKING
		// hash word and add to table
		for(String word : dictionary.keySet()){
			//System.out.println(word);
			if(word.length() > 1
			table.insertInTable(word);
			wordCount+=1;

			// if load factor is > 1.5 then double table size and rehash all words
			//if(table.checkLoadFactor(wordCount) == false){
				//System.out.println("Resizing table..."+wordCount);
				//return readDictionaryIntoTable(size*2);
			//}
		}
		System.out.println("TABLE IS COMPLETED!");
		return table;
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


