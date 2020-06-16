package bg.sofia.uni.fmi.mjt.bookmarks.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookmarkTest {

    private static final String URL = "https://en.wikipedia.org/wiki/String_theory";
    private static final String URL2 = "https://en.wikipedia.org/wiki/Music";
    private static final String URL_WRONG = "https://en.wikipedia.org/wiki/String_theoryssssssss";
    private static final String TITLE = "String theory - Wikipedia";
    private static final Set<Tag> TAGS = new HashSet<Tag>(
            Arrays.asList(Tag.newTag("string"), Tag.newTag("theory"),
                    Tag.newTag("particle")));

    @Test
    public void testContainsTitle() {
        Bookmark bookmark = new Bookmark(URL);
        assertTrue(bookmark.containsTitle(TITLE));
    }

    @Test
    public void testContainsTags() {
        Bookmark bookmark = new Bookmark(URL);
        assertTrue(bookmark.containsTags(TAGS));
    }

    @Test
    public void testDoesNotContainsTitle() {
        Bookmark bookmark = new Bookmark(URL2);
        assertFalse(bookmark.containsTitle(TITLE));
    }

    @Test
    public void testDoesNotContainsTags() {
        Bookmark bookmark = new Bookmark(URL2);
        assertFalse(bookmark.containsTags(TAGS));
    }

    @Test
    public void testWrongUrlContainsTitle() {
        Bookmark bookmark = new Bookmark(URL_WRONG);
        assertFalse(bookmark.containsTitle(TITLE));
    }

    @Test
    public void testWrongUrlContainsTags() {
        Bookmark bookmark = new Bookmark(URL_WRONG);
        assertFalse(bookmark.containsTags(TAGS));
    }

}
