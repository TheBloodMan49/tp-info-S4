/**
 * TP5 : Collections
 */
package TP5.library;

import java.util.Collection;
import java.util.List;

/**
 * Library consisting of a collection of documents
 * @author ? ?
 * @author ? ?
 * @version 2015.02.27
 */
public class Library {

	/**
	 * The document collection
	 */
	private List<Document> documents;

	/**
	 * Constructor
	 */
	public Library(){
		//TODO
	}

	/**
	 * Constructor
	 */
	public Library(Collection<Document> c){
		//TODO
	}
	
	/**
	 * Gives the size of the library
	 * @return the number of documents in the library
	 */
	public int size(){
		//TODO
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		//TODO
		return null;
	}

	/**
	 * Add a document to the library
	 * @param doc the document to add
	 */
	public void add(Document doc){
		//TODO
	}

	/**
	 * Borrow a document from the library
	 * @param barCode the bar code of the document to borrow
	 * @return the borrowed document
	 * @version 1
	 */
	public Document borrow(int barCode){
		//TODO
		return null;
	}

	/**
	 * Return a document to the library
	 * @param barCode the bar code of the document to return
	 * @version 1
	 */
	public void takeBack(int barCode){
		//TODO
	}
}
