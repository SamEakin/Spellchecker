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

	public static String[] generateSuggestions(String input, WordList table){

		System.out.println("Invalid word: "+input);

		int numberOfSuggestions = 0;
		String[] suggestions = new String[7];
		suggestions[5] = "No change.";
		suggestions[6] = "Enter your own choice.";

		char[] word = input.toCharArray();
		// iterate through each letter in the word
		for(int i = 0; i < word.length; i++){
			// try each letter of the alphabet to see if that makes a word
			char[] permutedWord = input.toCharArray();
			for(int j = 0; j < 26; j++) {
				permutedWord[i] = ((char)('a'+j));
				String testWord = String.valueOf(permutedWord);
				if(checkWord(testWord, table)) {
					if(numberOfSuggestions == 5) {return suggestions;}
					else{
						suggestions[numberOfSuggestions] = testWord;
						numberOfSuggestions++;
					}
				}
			}
		}
		return suggestions;
	}

	// Display suggestions for user and get their input
	public static int chooseSuggestion(String originalWord, String[] suggestions){
		int i = 0;
		for(String suggestion: suggestions){
			if(suggestion != null)
				System.out.println("["+i+"] "+suggestion);
			i++;
		}
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your choice: ");
		int choice = scan.nextInt();
		return choice;
	}
	
	public static void readFile(String[] files, WordList table) {
		int extraCredit = extraCreditToggle();
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
				String correctedWord = "";
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
						// Depending on if extra credit is toggled
						// Ask user what they want to do with the word
						if(extraCredit == 2){
							String[] suggestions = generateSuggestions(word, table);

							int choice = -1;

							choice = chooseSuggestion(word, suggestions);
							if(choice == 6){
								correctedWord = getUserInputWord();
							}
							if(choice > -1 && choice < 5){
								correctedWord = suggestions[choice];
							}
							if(choice == 5){
								correctedWord = word;
							}
						}
						else{
							// Make the change to the file according to user's choice.
							correctedWord = flag(word);
						}
						revisedText = revisedText.replace(word, correctedWord);
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

	public static int extraCreditToggle(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 1 for regular version of spellchecker.");
		System.out.println("Enter 2 for extra credit version of spellchecker.");
		int choice = 1;
		choice = scan.nextInt();
		return choice;
	}

	public static String getUserInputWord(){
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("Enter a word: ");
		String word = "";
		word = scan.next();
		return word;
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


