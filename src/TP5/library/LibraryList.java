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
public class LibraryList {

    /**
     * The document collection
     */
    private List<Document> documents;

    /**
     * Constructor
     */
    public LibraryList(){
        this.documents = new ArrayList<Document>();
    }

    /**
     * Constructor
     */
    public LibraryList(Collection<Document> c){
        this();
        this.documents.addAll(c);
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
        for (Document doc : this.documents) {
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
        this.documents.add(doc);
        doc.setAvailable(true);
    }

    /**
     * Borrow a document from the library
     * @param barCode the bar code of the document to borrow
     * @return the borrowed document
     * @version 1
     */
    public Document borrow(int barCode){
        for (Document doc : this.documents) {
            if (doc.getBarCode() == barCode && doc.isAvailable()) {
                doc.setAvailable(false);
                return doc;
            }
        }
        return null;
    }

    /**
     * Return a document to the library
     * @param barCode the bar code of the document to return
     * @version 1
     */
    public void takeBack(int barCode){
        for (Document doc : this.documents) {
            if (doc.getBarCode() == barCode) {
                doc.setAvailable(true);
            }
        }
    }
}
