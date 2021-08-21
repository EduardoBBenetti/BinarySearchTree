/**
 * 
 * @author Eduardo Barros Benetti
 * @version 1.0
 * @date March, 30th, 2020
 * 
 */

public class Node {

	/**
	 * These are the member variables with the pointers to the left and right, 
	 * the frequency of each word, and the actual word being held
	 */
	Node right, left;
	int frequency;
	String word;
	
	/**
	 * Main constructor, by receiving a String, which is the data supposed to be created
	 * @param w the data to be added to the Node
	 */
	public Node(String w){
		right = null;
		left = null;
		word = w;
		frequency = 1;
	}
	
	/**
	 * This method is to increase the frequency of a specific word
	 */
	public void increaseFreq() {
		this.frequency += 1;
	}
	
	// All the other methods are GETTERS and SETTERS of the member variables of this class
	public int getFrequency() {
		return this.frequency;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public void setWord(String st) {
		word = st;
	}
	
	public Node getRight() {
		return this.right;
	}

	public Node getLeft() {
		return this.left;
	}
	
	public String getValRight() {
		return right.getWord();
	}
	
	public String getValLeft() {
		return left.getWord();
	}
	
	public void setLeft(Node n) {
		this.left = n;
	}
	
	public void setRight(Node n) {
		this.right = n;
	}
	
}
	