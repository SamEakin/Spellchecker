import java.util.*;
import java.io.*;

public class SpellCheck extends BuildDict{

	public static TreeMap<String, Integer> dictionary = new TreeMap<String, Integer>();
	public static WordList<String, String> table = new WordList<String, String>();

	public static void main(String[] args){

		// 1. Create Empty Hash Table to put words inside.
		System.out.println(table.getSize());
		System.out.println(table.isEmpty());

		// 2. Read words from myDict.dat into dictionary.
		readDictFromFile();

		// 3. Hash each word
		readDictionary();
		
		// 4. Place all words from dictionary into table.
		

	}

	public static void readDictionary(){
		for(String word : dictionary.keySet()){
			// hash word and add to table
			String key = hash(word);
			System.out.println(key+" = "+word);
		}	
	}

	public static String hash(String word){
		if(word.length() <= 1){return word.substring(0);}
		String key = "";
		key = word.substring(0, 1);
		return key;
	}

	public static void add(){
		// @TODO: add each word to table based on hash key
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


