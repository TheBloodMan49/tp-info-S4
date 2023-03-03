/**
 * TP5 : Collections
 */
package TP5.library;

import java.util.*;

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
	private Map<Integer, Document> documents;

	/**
	 * Constructor
	 */
	public Library(){
		this.documents = new HashMap<Integer, Document>();
	}

	/**
	 * Constructor
	 */
	public Library(Collection<Document> c){
		this();
		for (Document doc : c) {
			this.documents.put(doc.getBarCode(), doc);
		}
	}
	
	/**
	 * Gives the size of the library
	 * @return the number of documents in the library
	 */
	public int size(){
		return this.documents.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String res = "[";
		for (Document doc : this.documents.values()) {
			res += doc.getTitle();
		}
		res += "]";
		return res;
	}

	/**
	 * Add a document to the library
	 * @param doc the document to add
	 */
	public void add(Document doc){
		this.documents.put(doc.getBarCode(), doc);
		doc.setAvailable(true);
	}

	/**
	 * Borrow a document from the library
	 * @param barCode the bar code of the document to borrow
	 * @return the borrowed document
	 * @version 1
	 */
	public Document borrow(int barCode){
		Document doc = this.documents.get(barCode);
		if (doc != null) doc.setAvailable(false);
		return doc;
	}

	/**
	 * Return a document to the library
	 * @param barCode the bar code of the document to return
	 * @version 1
	 */
	public void takeBack(int barCode){
		Document doc = this.documents.get(barCode);
		if (doc != null) doc.setAvailable(true);
	}

	public String toString(Comparator<Document> c) {
		ArrayList<Document> sortedDocuments = new ArrayList<>(this.documents.values());
		Collections.sort(sortedDocuments, c);
		String res = "[";
		for (Document doc : sortedDocuments) {
			res += doc.getTitle();
		}
		res += "]";
		return res;
	}

	public Collection<Document> getDocumentByAuthor(String author) {
		Collection<Document> res = new ArrayList<>();
		for (Document doc : this.documents.values()) {
			if (doc.getAuthors().contains(author)) {
				res.add(doc);
			}
		}
		return res;
	}
}
