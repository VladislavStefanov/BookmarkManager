package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.state.GuestBookmarkManagerState;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.BookmarkRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkManagerTest {

    private static final String COLLECTION_NAME = "my collection";

    private static final String URL = "google.com";

    private static final String TITLE = "title";

    private static final List<String> RAW_TAGS = Arrays.asList("tag");

    private static final List<String> CHROME_URLS = Arrays.asList("yay");

    private static final String PASSWORD = "1234";

    private static final String USERNAME = "vladi";

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private GuestBookmarkManagerState bookmarkManager;

    @Test
    public void testRegisterAlreadyLoggedIn() {
        assertEquals(Response.ALREADY_LOGGED_IN.getMessage(),
                bookmarkManager.login(USERNAME, PASSWORD));
    }

    @Test
    public void testLoginAlreadyLoggedIn() {

        assertEquals(Response.ALREADY_LOGGED_IN.getMessage(),
                bookmarkManager.register(USERNAME, PASSWORD));
    }

    @Test
    public void testCreateCollection() {
        Mockito.when(bookmarkRepository.createCollection(COLLECTION_NAME))
                .thenReturn(Response.COLLECTION_CREATED.getMessage());

        assertEquals(Response.COLLECTION_CREATED.getMessage(),
                bookmarkManager.createCollection(COLLECTION_NAME));
    }

    @Test
    public void testAdd() {
        Mockito.when(bookmarkRepository.addBookmarkToDefaultCollection(URL))
                .thenReturn(Response.URL_ADDED.getMessage());

        assertEquals(Response.URL_ADDED.getMessage(), bookmarkManager.add(URL));
    }

    @Test
    public void testAddToCollection() {
        Mockito.when(bookmarkRepository.addBookmarkToCollection(COLLECTION_NAME,
                URL)).thenReturn(Response.URL_ADDED.getMessage());

        assertEquals(Response.URL_ADDED.getMessage(),
                bookmarkManager.addToCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testRemoveFromCollection() {
        Mockito.when(bookmarkRepository
                .removeBookmarkFromCollection(COLLECTION_NAME, URL))
                .thenReturn(Response.URL_REMOVED.getMessage());

        assertEquals(Response.URL_REMOVED.getMessage(),
                bookmarkManager.removeFromCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testGetAllBookmarks() {
        Mockito.when(bookmarkRepository.getAllBookmarks()).thenReturn(URL);

        assertEquals(URL, bookmarkManager.getAllBookmarks());
    }

    @Test
    public void testGetBookmarksFromCollection() {
        Mockito.when(bookmarkRepository
                .getAllBookmarksFromCollection(COLLECTION_NAME))
                .thenReturn(URL);

        assertEquals(URL,
                bookmarkManager.getBookmarksFromCollection(COLLECTION_NAME));
    }

    @Test
    public void testSearchBookmarksByTitle() {
        Mockito.when(bookmarkRepository.searchBookmarksByTitle(TITLE))
                .thenReturn(URL);

        assertEquals(URL, bookmarkManager.searchBookmarksByTitle(TITLE));
    }

    @Test
    public void testSearchBookmarksByTags() {
        Mockito.when(bookmarkRepository.searchBookmarksByTags(RAW_TAGS))
                .thenReturn(URL);

        assertEquals(URL, bookmarkManager.searchBookmarksByTags(RAW_TAGS));
    }

    @Test
    public void testImportFromChrome() {
        Mockito.when(bookmarkRepository
                .addAllBookmarksToChromeCollection(CHROME_URLS))
                .thenReturn(Response.IMPORTED_FROM_CHROME.getMessage());

        assertEquals(Response.IMPORTED_FROM_CHROME.getMessage(),
                bookmarkManager.importFromChrome(CHROME_URLS));
    }

    @Test
    public void testPersistBookmarks() {
        Mockito.doNothing().when(bookmarkRepository).persistBookmarks();

        assertEquals(Response.CLOSED.getMessage(),
                bookmarkManager.persistBookmarks());
    }

}
