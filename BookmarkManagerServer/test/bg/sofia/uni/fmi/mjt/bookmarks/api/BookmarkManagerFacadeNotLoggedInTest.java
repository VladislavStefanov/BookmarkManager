package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;

public class BookmarkManagerFacadeNotLoggedInTest {

    private static final String COLLECTION_NAME = "my collection";
    private static final String URL = "google.com";
    private static final String TITLE = "title";
    private static final List<String> RAW_TAGS = Arrays.asList("tag");
    private static final List<String> CHROME_URLS = Arrays.asList("yay");

    @Test
    public void testCreateCollectionNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.createCollection(COLLECTION_NAME));
    }

    @Test
    public void testAddNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.add(URL));
    }

    @Test
    public void testAddToCollectionNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.addToCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testRemoveFromCollectionNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(), bookmarkManagerFacade
                .removeFromCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testGetAllBookmarksNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.getAllBookmarks());
    }

    @Test
    public void testGetBookmarksFromCollectionNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(), bookmarkManagerFacade
                .getBookmarksFromCollection(COLLECTION_NAME));
    }

    @Test
    public void testSearchByTitleNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.searchBookmarksByTitle(TITLE));
    }

    @Test
    public void testSearchByTagsNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.searchBookmarksByTags(RAW_TAGS));
    }

    @Test
    public void testImportFromChromeNotLoggedIn() {
        BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.importFromChrome(CHROME_URLS));
    }
}
