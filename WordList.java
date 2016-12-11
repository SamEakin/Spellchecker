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
	public double currentLoadFactor; // N/n <= 1.5
	public double desiredLoadFactor; 

	// Constructor
	WordList(int size){
		tableSize = size;
		theArray = new Bucket[size];
		numberOfWords = 0;
		currentLoadFactor = (double)numberOfWords/(double)tableSize;
		desiredLoadFactor = 1.5;

		// fill empty list
		for(int i=0; i < tableSize; i++){
			theArray[i] = new Bucket();
		}
	}

	public int numberWords(){
		return numberOfWords;
	}

	public void updateLoadFactor(){
		currentLoadFactor = (double)numberOfWords / (double)tableSize;
	}

	public int stringHashFunction(String word){
		
		int hashKeyValue = (word.hashCode() & 0x7fffffff) % tableSize;
		return hashKeyValue;
	}

	// Insert a word into the table
	public void insertInTable(String word){
		int hashKey = stringHashFunction(word);
		Word newWord = new Word(word, hashKey);
		theArray[hashKey].insertInBucket(newWord);
	}

	// Find a word in the table
	public boolean find(String searchWord){
		int hashKey = stringHashFunction(searchWord);
		return theArray[hashKey].find(hashKey, searchWord);
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
	public boolean find(int hashKey, String searchWord){
		Word current = firstWord;
		while(current != null && current.key <= hashKey){
			if(current.word.equals(searchWord)){
				//current.printWord();
				return true;
		}
			current = current.next;
		}
		//System.out.println("Word not found.");
		return false;
	}

	public void printList(){
		Word current = firstWord;
		while(current != null){
			System.out.println(current);
			current = current.next;
		}
	}

}//end








