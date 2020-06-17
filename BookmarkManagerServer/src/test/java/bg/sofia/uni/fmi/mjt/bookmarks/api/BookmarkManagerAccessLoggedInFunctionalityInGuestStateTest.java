package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.state.GuestBookmarkManagerState;

public class BookmarkManagerAccessLoggedInFunctionalityInGuestStateTest {

    private static final String COLLECTION_NAME = "my collection";
    private static final String URL = "google.com";
    private static final String TITLE = "title";
    private static final List<String> RAW_TAGS = Arrays.asList("tag");
    private static final List<String> CHROME_URLS = Arrays.asList("yay");

    @Test
    public void testCreateCollectionNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.createCollection(COLLECTION_NAME));
    }

    @Test
    public void testAddNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.add(URL));
    }

    @Test
    public void testAddToCollectionNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.addToCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testRemoveFromCollectionNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.removeFromCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testGetAllBookmarksNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.getAllBookmarks());
    }

    @Test
    public void testGetBookmarksFromCollectionNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.getBookmarksFromCollection(COLLECTION_NAME));
    }

    @Test
    public void testSearchByTitleNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.searchBookmarksByTitle(TITLE));
    }

    @Test
    public void testSearchByTagsNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.searchBookmarksByTags(RAW_TAGS));
    }

    @Test
    public void testImportFromChromeNotLoggedIn() {
        GuestBookmarkManagerState bookmarkManager = new GuestBookmarkManagerState(
                null);
        assertEquals(Response.NOT_LOGGED_IN.getMessage(),
                bookmarkManager.importFromChrome(CHROME_URLS));
    }
}
