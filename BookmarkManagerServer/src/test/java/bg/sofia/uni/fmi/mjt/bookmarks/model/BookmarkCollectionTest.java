package bg.sofia.uni.fmi.mjt.bookmarks.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkCollectionTest {

    private static final String DELIMITER = "\n";
    private static final String URL2 = "google.com";
    private static final String URL1 = "youtube.com";

    @Test
    public void testToString() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL1));
        bookmarkCollection.add(new Bookmark(URL2));

        assertEquals(URL1 + DELIMITER + URL2 + DELIMITER,
                bookmarkCollection.toString());
    }

    @Test
    public void testEquals() {

        BookmarkCollection bookmarkCollection1 = new BookmarkCollection();
        bookmarkCollection1.add(new Bookmark(URL1));
        bookmarkCollection1.add(new Bookmark(URL2));

        BookmarkCollection bookmarkCollection2 = new BookmarkCollection();
        bookmarkCollection2.add(new Bookmark(URL2));
        bookmarkCollection2.add(new Bookmark(URL1));

        assertTrue(bookmarkCollection1.equals(bookmarkCollection2));
        assertTrue(bookmarkCollection2.equals(bookmarkCollection1));
        assertTrue(bookmarkCollection1.equals(bookmarkCollection1));

        assertFalse(bookmarkCollection1.equals(null));
        assertFalse(bookmarkCollection1.equals(URL1));

        assertEquals(bookmarkCollection1.hashCode(),
                bookmarkCollection2.hashCode());
    }

}
