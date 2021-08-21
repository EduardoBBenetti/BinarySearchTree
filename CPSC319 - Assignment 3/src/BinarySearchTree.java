/**
 * 
 * @author Eduardo Barros Benetti
 * @date 30th,  March, 2020
 * @version 1.0
 *
 *
 *This class is used to organize the data into a binary search tree of Strings 
 *
 */
public class BinarySearchTree {

	/**
	 * This is the root of the search tree (first Node)
	 */
	private Node root;
	
	/**
	 * This is the highest frequency of a certain word
	 */
	int highestFreq;
	
	/**
	 * Default constructor of the tree, simply setting the root to null 
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * This method coordinates the addition of each String being received in the tree
	 * @param data: the word to be added to the tree, at a specific spot
	 */
	public void insert (String data) {
		root = insertRec(root, data);
	}
	
	/**
	 * Helper method, responsible for actually adding each String into the BST. The method does that 
	 * recursively, where the base case is when the node is null.  
	 * @param cur: this is the current node
	 * @param st: this is the target String, to be added to the BST
	 * @return the node, where the String was added
	 */
	public Node insertRec(Node cur, String st) {
		if( cur == null ) {                             // base case (the end of the tree)
			cur = new Node(st);							// create new node		
			return cur;
		}
		if( st.compareTo(cur.getWord()) < 0 ) {         // if less, set left
			cur.setLeft(insertRec(cur.getLeft(), st));
		}	
		else if( st.compareTo(cur.getWord()) > 0 ) {    // if bigger, set right
			cur.setRight(insertRec(cur.getRight(), st));
		}
		else {
			cur.increaseFreq();							// if the same, increase frequency of word 
		} 
		return cur;
	}

	/**
	 * This method is going through the entire tree and counting the amount of data in the tree,
	 * This is the main method of this function, calling the recursive version, where the counting
	 * actually happens
	 * @return the number of words in the BST
	 */
	public int getNumberOfWords() {
		return getNumberOfWordsRec(root);
	}
	/**
	 * The recursive definition of going one node ate a time and adding up the Nodes present in the tree
	 * Where the base case is when the node is null, returning 0 to be added
	 * @param root: current node
	 * @return the sum of the words, up to that certain node
	 */
	public int getNumberOfWordsRec(Node root) {
		
		if(root != null && root.getFrequency()>this.highestFreq) {   // This if statement is mostly to setup 
			highestFreq = root.getFrequency(); 						 // the highest frequency of the tree.
		}															 // Since it goes through the entire tree
																	 // Through one if statement you can easily
		int sum;													 // get the highest frequency to be used on 
		if(root == null) {	 //base case							 // getHighestFreq() method afterwards
			return 0;
		}
		sum = getNumberOfWordsRec(root.getLeft()) + 1;   
		sum += getNumberOfWordsRec(root.getRight());
		return sum;
	}
	
	/**
	 * So as the previous method, this is the main one, where it calls the other method, where the 
	 * counting of unique words actually occur
	 * @return number of unique words in the tree
	 */
	public int getNumOfUniqueWords() {
		return getNumOfUniqueWordsRec(root);
	}
	
	/**
	 * This is the recursive definition of the counting of number of unique words in the tree, 
	 * The base case is where the root is null, meaning the end of the tree.
	 * Besides that, this method is used to get the highest frequency of the BST, since it goes through all
	 * nodes.
	 * @param root: is the current node
	 * @return the total number of unique words in the BST
	 */
	public int getNumOfUniqueWordsRec(Node root) {
		int sum = 0;
		if(root == null) {                                    // base case
			return 0;
		}
		sum = getNumOfUniqueWordsRec(root.getLeft());		  // doing the same operations on the left
		if(root.getFrequency() == 1) {						  // checking uniqueness
			sum++;
		}
		sum = sum + getNumOfUniqueWordsRec(root.getRight()); // same operations on the right
		return sum;
	}
	
	/**
	 * Calculates the max depth of the tree, once again this is the method that calls the recursive 
	 * definition in order to be actually calculated
	 * @return the maximum depth of the tree
	 */
	public int getMaxDepth() {
		return getMaxDepthRec(root);
	}
	
	/**
	 * This method is the recursive definition to calculate the max depth of the tree, going through all
	 * the possibilities of the tree and checking the highest one. Where the base case is the end of the tree
	 * or where the node is null
	 * @param root: current node of being checked
	 * @return the max depth of the tree
	 */
	public int getMaxDepthRec(Node root) {
		if(root == null) {
			return 0;
		}
		int maxLeft = getMaxDepthRec(root.getLeft());            // getting max from the left
		int maxRight = getMaxDepthRec(root.getRight());			 // getting max from the right
		if(maxLeft > maxRight) {							     // which one if the max of the max
			return maxLeft + 1;
		} 
		else {
			return maxRight + 1;
		}
	}
	
	/**
	 * A helper method, responsible of getting the users input to check which method of printing 
	 * he/ she prefers
	 * @param printOption a number representing a respective method of printing
	 */
	public void printData(int printOption) {
		switch(printOption) {
			case 1:
				System.out.println("IN-ORDER output: ");
				inOrder(root);
				System.out.println();
				break;
			case 2:
				System.out.println("PRE-ORDER output: ");
				preOrder(root);
				System.out.println();
				break;				
			case 3:
				System.out.println("POST-ORDER output: ");
				postOrder(root);
				System.out.println();
				break;
			default:
				System.out.println("Invalid printing option!");
				break;
		}
	}
	
	/**
	 * In order printing, a recursive definition
	 * @param root: the beginning of the BST
	 */
	public void inOrder(Node root) {		
		if(root == null) {
			return;
		}
		inOrder(root.getLeft());  						// LEFT --> CURRENT --> RIGHT
		System.out.print(root.getWord() + " ");
		inOrder(root.getRight());
	}
	
	/**
	 * Pre order printing, a recursive definition
	 * @param root: the beginning of the BST
	 */
	public void preOrder(Node root) {
		if(root == null) {
			return;
		}

		System.out.print(root.getWord() + " ");			// CURRENT --> LEFT --> RIGHT
		preOrder(root.getLeft());
		preOrder(root.getRight());
		
	}
	
	/**
	 * Post order printing, a recursive definition
	 * @param root: the beginning of the BST
	 */
	public void postOrder(Node root) {
		if(root == null) {
			return;
		}
		postOrder(root.getLeft());						// LEFT --> RIGHT --> CURRENT
		postOrder(root.getRight());
		System.out.print(root.getWord() + " ");

	}
	
	/**
	 * This is the search method, responsible of finding a certain target String in the tree. 
	 * This method is all recursive	
	 * @param root: the beginning of the tree
	 * @param key: the order being searched
	 * @return the frequency in which the target String is in the input file (used like that in the method 
	 * that calls)
	 */
	public int search(Node root, String key)
	{	   
	    if (root==null || root.getWord().equals(key)) // found the key, return the frequency
	        return root.getFrequency(); 
	  
	    if (root.getWord().compareTo(key) > 0) 		// recurse left
	        return search(root.left, key); 
	  
	    return search(root.right, key); 			// recurse right
	}
	
	
	/**
	 * Getter of the root
	 * @return the root of the tree
	 */
	public Node getRoot() {
		return root;
	}
	
	/**
	 * Finds all the word(s) with the highest frequency in the tree. This is also the main method, 
	 * which calls the helper function to actually go through the tree and check node by node
	 * @return String with all the information of the word(s) with the highest frequency
	 */
	public String getHighestFreq() {
		return getHighestFrequency(root);
	}
	
	/**
	 * Responsible of getting the words with the highest frequency, established by a member variable.
	 * This method is also recursive, by searching for words on both "sides" of the tree
	 * @param root: the root of the 
	 * @return combination of the information of all the word(s) with the highest frequency
	 */
	public String getHighestFrequency(Node root) {
		
		if(root == null) {												// base case, or the end of the tree
			return "";
		}	
		StringBuilder st = new StringBuilder();
		if(root.getFrequency() == this.highestFreq) {					// comparing each node to the member
			st.append(root.getWord() + " = "+highestFreq+" times\n");	// variable of the BST, which is the 
		}																// highest frequency in the tree
		st.append(getHighestFrequency(root.getLeft()));					// recurse left
		st.append(getHighestFrequency(root.getRight()));				// recurse right
		return st.toString();
	}

	
	
}











