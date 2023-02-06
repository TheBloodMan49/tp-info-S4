/**
 * TP5 : Collections
 */
package TP5.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Manuel Bouillon
 * @version 2015.02.27
 */
public class Document {

	/**
	 * Document bar code
	 */
	private int barCode;

	/**
	 * Document availability
	 */
	private boolean available;

	/**
	 * Document title
	 */
	private String title;

	/**
	 * Document authors
	 */
	private List<String> authors;

	/**
	 * Constructor
	 * @param barCode the bar code
	 * @param title the title
	 * @param authors the authors
	 */
	public Document(int barCode, String title, List<String> authors) {
		available = true;
		this.barCode = barCode;
		this.title = title;
		this.authors = authors;
	}

	/**
	 * Get the document availability
	 * @return true if available
	 */
	public boolean isAvailable(){
		return available;
	}

	/**
	 * Set document availability
	 * @param available the new availability
	 */
	public void setAvailable(boolean available){
		this.available = available;
	}

	/**
	 * Get the bar code
	 * @return the bar code
	 */
	public int getBarCode() {
		return barCode;
	}

	/**
	 * Get the title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the authors
	 * @return the authors
	 */
	public List<String> getAuthors() {
		return authors;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return title + " (" + barCode + ")";
	}

	/**
	 * Load a CSV file from the ReLire database
	 * @param file file path
	 * @return a set of documents
	 */
	public static Set<Document> loadFromFile(String file){
		System.out.print("Loading from file... ");
		Set<Document> set = new HashSet<Document>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while(br.ready()) { // As long as there is lines to read
				String line = br.readLine();
				String[] document = line.split(";");
				if(document.length > 12){
					try {
						String[] authors = document[1].split(",");
						List<String> authorsList = new ArrayList<String>();
						for(int i = 0; i < authors.length; i++){
							authorsList.add(authors[i]);
						}
						//System.out.print(document[12]);
						int barCode;
						try {
							barCode = Integer.parseInt(document[12].substring(1, document[12].length()-2));
						} catch (NumberFormatException e) {
							barCode = set.size();
						}
						set.add(new Document(barCode, document[0], authorsList));
					} catch (StringIndexOutOfBoundsException e) {
						// skipping incorrect entry
					}
				}
			}
			br.close();
			fr.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("done (" + set.size() + ")!");
		return set;
	}
}
