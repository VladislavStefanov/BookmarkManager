package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.api.state.BookmarkManagerState;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkManagerTest {

    private static final String ANSWER = "answer";

    private static final String PASSWORD = "1234";

    private static final String NAME = "vladi";

    private static final String ALTERNATIVE_NAME = "vladko";

    @Mock
    private BookmarkManagerState bookmarkManagerState;

    @InjectMocks
    private BookmarkManager bookmarkManager = new BookmarkManager();

    @Test
    public void testRegister() {
        Mockito.when(bookmarkManagerState.register(NAME, PASSWORD))
                .thenReturn(ANSWER);

        String answer = bookmarkManager.register(NAME, PASSWORD);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testLogin() {
        Mockito.when(bookmarkManagerState.login(NAME, PASSWORD))
                .thenReturn(ANSWER);

        String answer = bookmarkManager.login(NAME, PASSWORD);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testCreateCollection() {
        Mockito.when(bookmarkManagerState.createCollection(NAME))
                .thenReturn(ANSWER);

        String answer = bookmarkManager.createCollection(NAME);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testAdd() {
        Mockito.when(bookmarkManagerState.addToDefaultCollection(NAME)).thenReturn(ANSWER);

        String answer = bookmarkManager.addToDefaultCollection(NAME);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testAddToCollection() {
        Mockito.when(
                bookmarkManagerState.addToCollection(NAME, ALTERNATIVE_NAME))
                .thenReturn(ANSWER);

        String answer = bookmarkManager.addToCollection(NAME, ALTERNATIVE_NAME);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testRemoveFromCollection() {
        Mockito.when(bookmarkManagerState.removeFromCollection(NAME,
                ALTERNATIVE_NAME)).thenReturn(ANSWER);

        String answer = bookmarkManager.removeFromCollection(NAME,
                ALTERNATIVE_NAME);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testGetAllBookmarks() {
        Mockito.when(bookmarkManagerState.getAllBookmarks()).thenReturn(ANSWER);

        String answer = bookmarkManager.getAllBookmarks();
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testGetBookmarksFromCollection() {
        Mockito.when(bookmarkManagerState.getBookmarksFromCollection(NAME))
                .thenReturn(ANSWER);

        String answer = bookmarkManager.getBookmarksFromCollection(NAME);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testSearchBookmarksByTitle() {
        Mockito.when(bookmarkManagerState.searchBookmarksByTitle(NAME))
                .thenReturn(ANSWER);

        String answer = bookmarkManager.searchBookmarksByTitle(NAME);
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testSearchBookmarksByTags() {
        Mockito.when(bookmarkManagerState
                .searchBookmarksByTags(Collections.singletonList(NAME)))
                .thenReturn(ANSWER);

        String answer = bookmarkManager
                .searchBookmarksByTags(Collections.singletonList(NAME));
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testImportFromChrome() {
        Mockito.when(bookmarkManagerState
                .importFromChrome(Collections.singletonList(NAME)))
                .thenReturn(ANSWER);

        String answer = bookmarkManager
                .importFromChrome(Collections.singletonList(NAME));
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testPersistBookmarks() {
        Mockito.when(bookmarkManagerState.persistBookmarks())
                .thenReturn(ANSWER);

        String answer = bookmarkManager.persistBookmarks();
        assertEquals(ANSWER, answer);
    }

    @Test
    public void testPersistUsers() {
        bookmarkManager.persistUsers();
        Mockito.verify(bookmarkManagerState).persistUsers();
    }
}
