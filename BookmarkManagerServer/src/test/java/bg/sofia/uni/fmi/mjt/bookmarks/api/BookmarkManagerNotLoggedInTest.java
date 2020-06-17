package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;

public class BookmarkManagerNotLoggedInTest {

    private static final String COLLECTION_NAME = "my collection";
    private static final String URL = "google.com";
    private static final String TITLE = "title";
    private static final List<String> RAW_TAGS = Arrays.asList("tag");
    private static final List<String> CHROME_URLS = Arrays.asList("yay");

    @Test
    public void testCreateCollectionNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.createCollection(COLLECTION_NAME));
    }

    @Test
    public void testAddNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.add(URL));
    }

    @Test
    public void testAddToCollectionNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.addToCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testRemoveFromCollectionNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(), bookmarkManager
                .removeFromCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testGetAllBookmarksNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.getAllBookmarks());
    }

    @Test
    public void testGetBookmarksFromCollectionNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(), bookmarkManager
                .getBookmarksFromCollection(COLLECTION_NAME));
    }

    @Test
    public void testSearchByTitleNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.searchBookmarksByTitle(TITLE));
    }

    @Test
    public void testSearchByTagsNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.searchBookmarksByTags(RAW_TAGS));
    }

    @Test
    public void testImportFromChromeNotLoggedIn() {
        BookmarkManager bookmarkManager = new BookmarkManager();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.importFromChrome(CHROME_URLS));
    }
}
