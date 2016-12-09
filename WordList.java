import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Node class to store each hash key, word, and link to another word.
class Node<K,V>{

	K key; // Hash key
	V value; // Word
	
	Node<K,V> next; // Link to next word in chain

	// Constructor
	public Node(K key, V value){
		this.key = key;
		this.value = value;
	}
}

// Container class to put nodes inside.
public class WordList<K,V>{

	private ArrayList<Node<K,V>> wordBuckets;

	// capacity of array list
	private int numberOfBuckets;

	// array list size
	private int size;

	public WordList(){
		wordBuckets = new ArrayList<>();
		numberOfBuckets = 10;
		size = 0;

		// Create empty chains
		for(int i = 0; i < numberOfBuckets; i++){
			wordBuckets.add(null);
		}
	}

	public int getSize(){return size;}
	public boolean isEmpty(){return size == 0;}

}


