import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 *
 * Word List (Hash Table)
 * Holds Buckets of Words
 *
 */
public class WordList{
	public Bucket[] theArray;
	public int tableSize; // N

	public int numberOfWords; // n
	public double loadFactor; // N/n <= 1.5

	// Constructor
	WordList(int size){
		tableSize = size;
		theArray = new Bucket[size];
		loadFactor = 1.5;

		// fill empty list
		for(int i=0; i < tableSize; i++){
			theArray[i] = new Bucket();
		}
	}

	public boolean checkLoadFactor(int wordCount){
		double tableLoad = (double)tableSize / (double)wordCount;
		System.out.println("Load factor = "+tableLoad);
		if (tableLoad > loadFactor) {
			return false;	
		}
		return true;
	}

	public int getWordCount(){
		int count = 0;
		for(int i = 0; i < tableSize; i++){
			int wordsInCurrentBucket = theArray[i].numberOfWords;
			count += wordsInCurrentBucket;
		}
		return count;
	}

	public int stringHashFunction(String word){
		int hashKeyValue = word.hashCode(); //% tableSize;
		System.out.println(word+" --> "+word.hashCode());
		//System.out.println(word+" --> "+hashKeyValue);
		return hashKeyValue;
	}


	// Insert a word into the table
	public void insertInTable(String word){
		System.out.println("inserting -> "+word);
		int hashKey = stringHashFunction(word);
		Word newWord = new Word(word, hashKey);
		
		theArray[hashKey].insertInBucket(newWord);
	}

	// Find a word in the table
	public Word find(String searchWord){
		int hashKey = stringHashFunction(searchWord);
		Word theWord = theArray[hashKey].find(hashKey, searchWord);
		return theWord;
	}

	public void displayArray(){
		for(int i=0; i < tableSize; i++){
			System.out.println("Array index = "+i);
			theArray[i].printList();
		}
	}
}

/* Word class 
 * Fields: hash key, String word, Word link
 */
class Word{
	public String word;
	public int key;
	public Word next;

	public Word(String word, int hashKey){
		this.word = word;
		this.key = hashKey;
	}

	public String toString(){
		return word;
	}

	public void printWord(){
		System.out.println(word);
	}
}

// Container class to put nodes inside.
class Bucket{

	public int numberOfWords = 0;
	public Word firstWord = null;

	// Insert a word into table
	public void insertInBucket(Word newWord){
		Word previous = null;
		Word current = firstWord;


		while(current != null && newWord.key > current.key){
			previous = current;
			current = current.next;
		}

		if(previous == null)
			firstWord = newWord;
		else
			previous.next = newWord;

		newWord.next = current;
		numberOfWords++;
	}

	// Find a word in the table
	public Word find(int hashKey, String searchWord){
		Word current = firstWord;
		while(current != null && current.key <= hashKey){
			if(current.word.equals(searchWord)){
				current.printWord();
				return current;
		}
			current = current.next;
		}
		System.out.println("Word not found.");
		return null;
	}

	public void printList(){
		Word current = firstWord;
		while(current != null){
			System.out.println(current);
			current = current.next;
		}
	}

}//end








