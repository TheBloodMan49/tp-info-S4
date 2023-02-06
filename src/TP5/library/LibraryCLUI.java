/**
 * TP5 : Collections
 */
package TP5.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Comparator;

/**
 * Library Command Line User Interface
 * @author Manuel Bouillon
 * @version 2015.02.27
 */
public class LibraryCLUI {

	/**
	 * Programme principal
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<Document> c = Document.loadFromFile("relire-test.csv");
		Library library = new Library(c);
		LibraryCLUI clui = new LibraryCLUI(library);
		clui.run();
	}
	
	/**
	 * The library object the CLUI is associated to
	 */
	private Library library;
	
	/**
	 * True when the CLUI is running
	 */
	private boolean run = false;
	
	/**
	 * The number of actions in the menu
	 */
	private final int NB_ACTION = 6;
	
	/**
	 * Constructor
	 * @param library
	 */
	public LibraryCLUI(Library library){
		this.library = library;
		run = false;
	}
	
	/**
	 * Main method: launching the CLUI
	 */
	public void run(){
		run = true;
		while(run){
			System.out.println(getMenu());
			int action = getUserChoice();
			executeAction(action);
		}
	}
	
	/**
	 * The CLUI menu
	 * @return the menu
	 */
	private String getMenu(){
		String s = "\n****************\n";
		s += "* Library CLUI *\n";
		s += "1 - borrow document \n";
		s += "2 - return document \n";
		s += "3 - search by author \n";
		s += "4 - print type list \n";
		s += "5 - print content \n";
		s += "0 - quit \n";
		s += "****************\n";
		return s;
	}

	/**
	 * Read the user action number
	 * @return the action number (between 0 and NB_ACTION)
	 */
	private int getUserChoice(){
		boolean correct = false;
		int choice = 0;
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		do {
			try {
				String stringRes = br.readLine();
				choice = Integer.parseInt(stringRes);
				if(0 <= choice && choice < NB_ACTION){
					correct = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Incorrect choice");
			} catch (IOException e) {
				System.out.println("Error reading user choice.\n" + e.getMessage());
				e.printStackTrace();
			}
		} while (!correct);
		return choice;

	}

	/**
	 * Read user text entry
	 * @return
	 */
	private String getUserEntry(){
		String entry = "";
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		try {
			entry = br.readLine();
		} catch (IOException e) {
			System.out.println("Error reading user choice.\n" + e.getMessage());
			e.printStackTrace();
		}
		return entry;
	}

	/**
	 * Execute the method corresponding to the action number
	 * @param action the action number
	 */
	private void executeAction(int action) {
		switch (action) {
		case 0:
			run = false;
			break;
		case 1:
			borrow();
			break;
		case 2:
			takeBack();
			break;
		case 3:
			searchByAuthor();
			break;
		case 4:
			//printTypeList();
			break;
		case 5:
			printContent();
			printContentByTitle();
			break;
		default: //should never happen
			System.out.println("Incorrect action");
			break;
		}
	}

	/**
	 * Ask the user a document bar code
	 * and borrow the corresponding document
	 */
	private void borrow() {
		System.out.println("Enter bar code:");
		String entry = getUserEntry();
		try {
			int barCode = Integer.parseInt(entry);
			Document doc = library.borrow(barCode);
			System.out.println(doc);
		} catch (NumberFormatException e) {
			System.out.println("Incorrect bar code format");
		}
	}

	/**
	 * Ask the user a document bar code
	 * and return the corresponding document
	 */
	private void takeBack() {
		System.out.println("Enter bar code:");
		String entry = getUserEntry();
		try {
			int barCode = Integer.parseInt(entry);
			library.takeBack(barCode);
		} catch (NumberFormatException e) {
			System.out.println("Incorrect bar code format");
		}
	}

	/**
	 * Ask the user an author name
	 * and search the collection for documents
	 * associated to this author 
	 */
	private void searchByAuthor() {
		/*
		//TODO uncomment
		System.out.println("Enter author name:");
		String author = getUserEntry();
		Collection<Document> result = library.getDocumentByAuthor(author);
		System.out.println(" * Documents written by " + author + ":");
		for(Document doc : result){
			System.out.println(doc);
		}
		*/
	}

	/**
	 * Print the list of all the document types
	 * existing in the library
	 *
	private void printTypeList() {
		Collection<String> result = library.getDocumentTypes();
		System.out.println(" * Documents types:");
		for(String s : result){
			System.out.println(s);
		}
	}/

	/**
	 * Print library content
	 */
	private void printContent() {
		System.out.println(library);
	}

	/**
	 * Print library content ordered by title
	 */
	private void printContentByTitle() {
		/*
		//TODO uncomment
		Comparator<Document> c = new TitleComparator();
		System.out.println(library.toString(c));
		*/
	}
}
