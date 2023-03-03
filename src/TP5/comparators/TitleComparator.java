package TP5.comparators;

import TP5.library.Document;
import java.util.Comparator;

public class TitleComparator implements Comparator<Document> {

    public int compare(Document a, Document b) {
        return a.getTitle().compareToIgnoreCase(b.getTitle());
    }
}
