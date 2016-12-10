import java.util.*;
import java.io.*;

public class SpellCheck extends BuildDict{

	public static TreeMap<String, Integer> dictionary = new TreeMap<String, Integer>();

	public static void main(String[] args){

		// Read words from myDict.dat into dictionary.
		readDictFromFile();

		// Create table &
		// Hash each word inside as long as load factor acceptable
		int size = 100;
		WordList table = readDictionaryIntoTable(size);

		// Get an array of valid text file names from user.
		String[] files = GetInputFiles.getInput();
		readFile(files, table);
	}
	
	public static void readFile(String[] files, WordList table) {
		for (String file: files) {
			Scanner s = null;
			try {
				File inputFile = new File(file);
				File outputFile = new File("revised_"+file);
				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				//s = new Scanner(new BufferedReader(new FileReader(file)));
				//BufferedWriter bw = new BufferedWriter(fw);
				String originalText = "";
				String line = "";

				// Make a copy of the file to revise
				while((line = reader.readLine()) != null){
					originalText += line + System.getProperty("line.separator");
				}
				reader.close();

				// Reread file by individual words now
				String word = "";
				String revisedText = originalText;
				s = new Scanner(new BufferedReader(new FileReader(inputFile)));
				while (s.hasNext()){
					word = s.next();
					boolean isCorrect = checkWord(word, table);
					if(isCorrect){
						// word is spelled correctly.
					}
					else{
						// word is spelled incorrectly
						// =word= append to new file
						revisedText = revisedText.replace(word, flag(word));
					}
				}

				String outputFileName = getOutputFileName(file);
				FileWriter writer = new FileWriter(outputFileName);
				writer.write(revisedText);
				writer.close();
			
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
	}

	public static String getOutputFileName(String inputFileName){
		if(inputFileName.contains(".")){
			int n = inputFileName.indexOf(".");
			return (inputFileName.substring(0,n)+".out");

		}
		return (inputFileName+".out");
	}

	public static String flag(String word){
		return "="+word+"=";
	}

	// Delete all non-spaces, non-letters, & make all lowercase.
	public static boolean checkWord(String word, WordList table) {
		word = word.replaceAll("[^\\sA-Za-z]","");
		word = word.toLowerCase();
		if(word.length() >= 1){
			return table.find(word);
		}
		return true;
	}

	public static void printKeys(){
		for(String word : dictionary.keySet())
			System.out.println(word);
	}

	public static WordList readDictionaryIntoTable(int size){

		// Construct table
		WordList table = new WordList(size);

		// hash word and add to table
		for(String word : dictionary.keySet()){
			if(word.length() >= 1){
				table.insertInTable(word);
				table.numberOfWords++;
				table.updateLoadFactor();
			}
				
			// if load factor is > 1.5 then double table size and rehash all words
			if(table.currentLoadFactor > table.desiredLoadFactor){
				System.out.println("Resizing table..."+table.numberOfWords+" currently in table.");
				return readDictionaryIntoTable(table.tableSize*2);
			}
		}
		System.out.println("TABLE IS COMPLETED!");
		System.out.println("Number of words = "+table.numberOfWords);
		System.out.println("Table size = "+table.tableSize);
		System.out.println("Load = "+ (double)table.numberOfWords / (double)table.tableSize);
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


