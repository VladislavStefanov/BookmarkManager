package bg.sofia.uni.fmi.mjt.bookmarks.model;

import static org.junit.Assert.assertEquals;

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

}
