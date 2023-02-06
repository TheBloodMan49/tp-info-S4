/**
 * TP5 : Collections
 */
package TP5.library;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * JUnit tests for the Library class
 * @author Manuel Bouillon
 * @version 2015.02.27
 */
public class LibraryTest {

	/**
	 * Test method for {@link library.Library#Library()}.
	 */
	@Test
	public void testLibrary() {
		Library library = new Library();
		assertNotNull(library);
	}

	/**
	 * Test method for {@link library.Library#Library(java.util.Collection)}.
	 */
	@Test
	public void testLibraryCollectionOfDocument() {
		Collection<Document> c = new LinkedList<Document>();
		List<String> l = new ArrayList<String>();
		l.add("Test");
		c.add(new Document(1, "Un", l));
		c.add(new Document(2, "Deux", l));
		c.add(new Document(3, "Trois", l));
		Library library = new Library(c);
		assertTrue(library.size() == 3);
	}

	/**
	 * Test method for {@link library.Library#toString()}.
	 */
	@Test
	public void testToString() {
		Collection<Document> c = new LinkedList<Document>();
		List<String> l = new ArrayList<String>();
		l.add("Test");
		c.add(new Document(1, "Un", l));
		c.add(new Document(2, "Deux", l));
		c.add(new Document(3, "Trois", l));
		Library library = new Library(c);
		assertFalse(library.toString().equals(""));
	}

	/**
	 * Test method for {@link library.Library#add(library.Document)}.
	 */
	@Test
	public void testAdd() {
		List<String> l = new ArrayList<String>();
		l.add("Test");
		Library library = new Library();
		library.add(new Document(1, "Un", l));
		library.add(new Document(2, "Deux", l));
		library.add(new Document(3, "Trois", l));
		assertTrue(library.size() == 3);
	}

	/**
	 * Test method for {@link library.Library#borrow1(int)}.
	 */
	@Test
	public void testBorrow() {
		List<String> l = new ArrayList<String>();
		l.add("Test");
		Library library = new Library();
		library.add(new Document(1, "Un", l));
		library.add(new Document(2, "Deux", l));
		library.add(new Document(3, "Trois", l));
		Document doc = library.borrow(2);
		assertTrue(doc.getBarCode() == 2);
		assertTrue(doc.getTitle().equals("Deux"));
		assertFalse(doc.isAvailable());
		assertNotNull(library.borrow(2));
		assertNull(library.borrow(-3));
	}

	/**
	 * Test method for {@link library.Library#borrow1(int)} efficiency.
	 */
	@Test
	public void testBorrowEfficiency() {
		Library library = new Library(Document.loadFromFile("relire-full.csv"));
		long begin = System.currentTimeMillis();
		library.borrow(123456789);
		library.borrow(234567891);
		library.borrow(345678912);
		library.borrow(456789123);
		library.borrow(567891234);
		library.borrow(678912345);
		library.borrow(789123456);
		library.borrow(891234567);
		library.borrow(912345678);
		library.borrow(123456789);
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin)) / 1000f;
		System.out.println("Time : " + time);
		assertTrue(time < 0.01);
	}

	/**
	 * Test method for {@link library.Library#takeBack1(int)}.
	 */
	@Test
	public void testTakeBack() {
		List<String> l = new ArrayList<String>();
		l.add("Test");
		Library library = new Library();
		library.add(new Document(1, "Un", l));
		library.add(new Document(2, "Deux", l));
		library.add(new Document(3, "Trois", l));
		Document doc = library.borrow(2);
		library.takeBack(2);
		assertTrue(doc.isAvailable());
	}

}
