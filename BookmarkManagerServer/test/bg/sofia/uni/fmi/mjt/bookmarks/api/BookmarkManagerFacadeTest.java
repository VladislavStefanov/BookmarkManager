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
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.BookmarkRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkManagerFacadeTest {

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
    private BookmarkManagerFacade bookmarkManagerFacade;

    @Test
    public void testRegisterAlreadyLoggedIn() {
        assertEquals(Response.ALREADY_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.login(USERNAME, PASSWORD));
    }

    @Test
    public void testLoginAlreadyLoggedIn() {

        assertEquals(Response.ALREADY_LOGGED_IN.getMessage(),
                bookmarkManagerFacade.register(USERNAME, PASSWORD));
    }

    @Test
    public void testCreateCollection() {
        Mockito.when(bookmarkRepository.createCollection(COLLECTION_NAME))
                .thenReturn(Response.COLLECTION_CREATED.getMessage());

        assertEquals(Response.COLLECTION_CREATED.getMessage(),
                bookmarkManagerFacade.createCollection(COLLECTION_NAME));
    }

    @Test
    public void testAdd() {
        Mockito.when(bookmarkRepository.addBookmarkToDefaultCollection(URL))
                .thenReturn(Response.URL_ADDED.getMessage());

        assertEquals(Response.URL_ADDED.getMessage(),
                bookmarkManagerFacade.add(URL));
    }

    @Test
    public void testAddToCollection() {
        Mockito.when(bookmarkRepository.addBookmarkToCollection(COLLECTION_NAME,
                URL)).thenReturn(Response.URL_ADDED.getMessage());

        assertEquals(Response.URL_ADDED.getMessage(),
                bookmarkManagerFacade.addToCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testRemoveFromCollection() {
        Mockito.when(bookmarkRepository
                .removeBookmarkFromCollection(COLLECTION_NAME, URL))
                .thenReturn(Response.URL_REMOVED.getMessage());

        assertEquals(Response.URL_REMOVED.getMessage(), bookmarkManagerFacade
                .removeFromCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testGetAllBookmarks() {
        Mockito.when(bookmarkRepository.getAllBookmarks()).thenReturn(URL);

        assertEquals(URL, bookmarkManagerFacade.getAllBookmarks());
    }

    @Test
    public void testGetBookmarksFromCollection() {
        Mockito.when(bookmarkRepository
                .getAllBookmarksFromCollection(COLLECTION_NAME))
                .thenReturn(URL);

        assertEquals(URL, bookmarkManagerFacade
                .getBookmarksFromCollection(COLLECTION_NAME));
    }

    @Test
    public void testSearchBookmarksByTitle() {
        Mockito.when(bookmarkRepository.searchBookmarksByTitle(TITLE))
                .thenReturn(URL);

        assertEquals(URL, bookmarkManagerFacade.searchBookmarksByTitle(TITLE));
    }

    @Test
    public void testSearchBookmarksByTags() {
        Mockito.when(bookmarkRepository.searchBookmarksByTags(RAW_TAGS))
                .thenReturn(URL);

        assertEquals(URL,
                bookmarkManagerFacade.searchBookmarksByTags(RAW_TAGS));
    }

    @Test
    public void testImportFromChrome() {
        Mockito.when(bookmarkRepository
                .addAllBookmarksToChromeCollection(CHROME_URLS))
                .thenReturn(Response.IMPORTED_FROM_CHROME.getMessage());

        assertEquals(Response.IMPORTED_FROM_CHROME.getMessage(),
                bookmarkManagerFacade.importFromChrome(CHROME_URLS));
    }

    @Test
    public void testPersistBookmarks() {
        Mockito.doNothing().when(bookmarkRepository).persistBookmarks();

        assertEquals(Response.CLOSED.getMessage(),
                bookmarkManagerFacade.persistBookmarks());
    }

}
