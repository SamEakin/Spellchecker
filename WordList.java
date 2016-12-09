import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class WordList<Key, Value>{

    private int n;       // number of key-value pairs
    private int m;       // hash table size
    private Node[] st;   // array of linked-list symbol tables

    // a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    // create separate chaining hash table
    public WordList() {
        this(997);
    } 

    // create separate chaining hash table with m lists
    public WordList(int m) {
        this.m = m;
        st = new Node[m];
    } 

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    } 

    // return number of key-value pairs in symbol table
    public int size() {
        return n;
    } 

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    } 

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) 
				return (Value) x.val;
        }
        return null;
    }

    // insert key-value pair into the table
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        n++;
        st[i] = new Node(key, val, st[i]);
    }

    // delete key (and associated value) from the symbol table.
    public void delete(Key key) {
        throw new UnsupportedOperationException("delete not currently supported");
    }

    // return all keys as an Iterable
    public Iterable<Key> keys()  {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                queue.add((Key) x.key);
            }
        }
        return queue;
    }

	// @TODO: WRITE THESE METHOD FRIDAY
	public boolean search(String word){
		// This method returns true if myWord is found in the hash table; otherwise it returns false.
		boolean found = false;
		return found;
	}
	
	public void insert(String word){
		// This method inserts myWord into the hash table if it is not already in it. 
		// After myWord is inserted into the hash table, 
		// make sure that there are still sufficiently many empty slots in the table.
	}

	public int numberWords(){
		// This method returns the number of words in the hash table and it should run in constant time.
		int numberOfWords = 0;
		return numberOfWords;
	}
	
	public void sortedPrint(){
		// This method prints out all the words in the hash table in lexicographic order 
		// in a nice readable format. This will be used for testing only.
	}

	public void sortedPrint(String fileName){
		// This method is the same as the above method except that it writes into a file with the given fileName.
	}
}


