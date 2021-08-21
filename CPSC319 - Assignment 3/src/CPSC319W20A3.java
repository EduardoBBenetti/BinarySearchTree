/**
 * 
 * @author Eduardo Barros Benetti
 * @version 1.0
 * @date March, 30th, 2020
 * 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This is the main class, where the inputs from the users are used and inputed into other 
 * methods and running the program
 * 
 */
public class CPSC319W20A3 {
	
	private String fileName; 
		
	/**
	 * Main method is responsible to create the binary search tree and input data into the tree, along
	 * with calling methods to read input into a String
	 * @param args: argument from the program
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		CPSC319W20A3 data = new CPSC319W20A3();
		BinarySearchTree BST = new BinarySearchTree();
		data.readInput(BST);                            // reading string and adding to the BST
		data.userOptions(BST);							// running the program and giving display options for user
	}
	
	/**
	 * Where it receives the inputs form the user to choose between searching data, printing data or quitting
	 * the program. It runs until the user inputs 3, which quits
	 * @param BST: the actual binary
	 */
	public void userOptions(BinarySearchTree BST) {
		Scanner scan = new Scanner(System.in);
		int answer;
		while(true) {
			System.out.print("\nEnter one of the following options: \n"
					+ "1 - Search data for a specific word\n"
					+ "2 - Print data (tranversal methods)\n"
					+ "3 - Quit program\n");
			if(!scan.hasNextInt()) {
				System.out.println("Invalid input...\nTry again");
			}
			else {
				answer = scan.nextInt();
				switch(answer) {
				case 1:
					this.searchWord(BST);                              // search for specific word
					break;
				case 2:
					this.printMethods(BST);							   // printing the entire BST in a format  
					break;
				case 3:													
					System.out.println("Thank for using the program"); // quitting the program
					return;
				}
			}
		}	
	}

	/**
	 * Reads the inputs from the user, and call the search method in the BinarySearchTree to look in the 
	 * tree the existence of the target String, or it prints that the word was not found
	 * @param BST: where the data is stored in a tree organization
	 */
	private void searchWord(BinarySearchTree BST) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the word you are looking for in "+this.getFileName()+"	 ? ");
		String word = scan.next();
		try {
			int f = BST.search(BST.getRoot(),word);             // look the target word
			String target = "Found! It appears "+f+" times in the input text file"; // printing info when the target is present
			System.out.println(target);
		}catch(NullPointerException e) {
			System.out.println("Word not found!");				// error when the word is not found
		}
	}
	
	/**
	 * This is a helper method, to separate from the main method, the printing one. Directed entirely on
	 * printing the BST in 3 possible methods: in order, pre order and post order (each one calling its 
	 * respective method in the BinarySearchTree)
	 * @param BST: where the data is stored in a tree organization
	 */
	private void printMethods(BinarySearchTree BST) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST ORDER) for "+this.getFileName()+" ?");
		int option = scan.nextInt();
		switch(option) {
			case 1:
				System.out.print("IN-ORDER output: ");
				BST.inOrder(BST.getRoot());                     // IN ORDER 
				System.out.println();
				break;
			case 2:
				System.out.print("PRE-ORDER output: ");
				BST.preOrder(BST.getRoot());					// PRE ORDER
				System.out.println();
				break;
			case 3:
				System.out.print("POST-ORDER output: ");
				BST.postOrder(BST.getRoot());					// POST ORDER
				System.out.println();
				break;
		}
	}
	
	/**
	 * Receives an array of Strings, which is checked if it is not a null (or a space without Strings, such
	 * as an empty line) and add it to the BinarySearchTree, or the BST 
	 * @param words: source of Strings, which will be added to the BST, through the insert method in the 
	 * 	BinarySearchTree class
	 * @param BST: the destiny of the Strings in the array
	 */
	public void createBST(String [] words, BinarySearchTree BST) {
		for(String st: words) {
			if(st != null)
				BST.insert(st);
		}
	}

	/**
	 * This method is responsible of reading the input file name which is given by the user, along with
	 * reading the file line by line, then transformed into a array of Strings, which is the argument of
	 * a different method, which insert each one in the BST
	 * @param BST: binary search tree, where the String sources are being placed 
	 * @throws IOException
	 */
	public void  readInput(BinarySearchTree BST) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the input filename: ");
		String filename = scan.nextLine();	
		setFileName(filename);
		FileInputStream fstream = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String s = "";
		String  []  words = null;
		String line = br.readLine();
		System.out.println();
		while(line != null) {
			words = line.replaceAll("[^0-9a-zA-Z ]", " ").toLowerCase().split("\\s+");
			this.createBST(words, BST);
		    line = br.readLine();
		}
		System.out.println("Total number of words in "+this.getFileName()+" = "+BST.getNumberOfWords()
			+ "\nNumber of unique words in the "+getFileName()+ " = "+BST.getNumOfUniqueWords()
			+ "\nThe word(s) which occur(s) most often and the number of time that it/they occur(s): \n"
			+ BST.getHighestFreq()
			+ "The maximum height of the tree = "+BST.getMaxDepth());
		
		fstream.close();
		System.out.println();
	}
	
	/**
	 * Getters and setters for the filename, which is the source file of the tree
	 */
	public void setFileName(String name) {
		this.fileName = name;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
}
