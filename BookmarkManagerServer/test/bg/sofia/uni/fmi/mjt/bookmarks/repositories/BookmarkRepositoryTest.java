package bg.sofia.uni.fmi.mjt.bookmarks.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.model.Bookmark;
import bg.sofia.uni.fmi.mjt.bookmarks.model.BookmarkCollection;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkRepositoryTest {

    private static final String WRONG_TAG = "wrongestTagEver";

    private static final String TITLE = "Google";

    private static final String NEW_LINE = "\n";

    private static final String URL = "https://www.google.com";

    private static final String URL2 = "youtube.com";

    private static final String URL3 = "https://en.wikipedia.org/wiki/String_theory";

    private static final List<String> RAW_TAGS = Arrays.asList("string",
            "theory", "particle");

    private static final String DEFAULT_COLLECTION_NAME = "default";

    private static final String USERNAME = "";

    private static final String COLLECTION_NAME = "my collection";

    @Mock
    private Map<String, BookmarkCollection> bookmarkCollections;

    @InjectMocks
    private BookmarkRepository bookmarkRepository = BookmarkRepository
            .newInstance(USERNAME);

    @Test
    public void testCreateCollection() {
        Mockito.when(bookmarkCollections.containsKey(COLLECTION_NAME))
                .thenReturn(false);

        assertEquals(Response.COLLECTION_CREATED.getMessage(),
                bookmarkRepository.createCollection(COLLECTION_NAME));
    }

    @Test
    public void testCreateCollectionNameTaken() {
        Mockito.when(bookmarkCollections.containsKey(COLLECTION_NAME))
                .thenReturn(true);

        assertEquals(Response.COLLECTION_ALREADY_EXISTS.getMessage(),
                bookmarkRepository.createCollection(COLLECTION_NAME));
    }

    @Test
    public void testAddBookmarkToDefaultCollection() {
        Mockito.when(bookmarkCollections.get(DEFAULT_COLLECTION_NAME))
                .thenReturn(new BookmarkCollection());

        assertEquals(Response.URL_ADDED.getMessage(),
                bookmarkRepository.addBookmarkToDefaultCollection(URL));
    }

    @Test
    public void testAddBookmarkToDefaultCollectionAlreadyAdded() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));
        Mockito.when(bookmarkCollections.get(DEFAULT_COLLECTION_NAME))
                .thenReturn(bookmarkCollection);

        assertEquals(Response.URL_ALREADY_ADDED.getMessage(),
                bookmarkRepository.addBookmarkToDefaultCollection(URL));
    }

    @Test
    public void testAddBookmarkToCollection() {
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME))
                .thenReturn(new BookmarkCollection());

        assertEquals(Response.URL_ADDED.getMessage(), bookmarkRepository
                .addBookmarkToCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testAddBookmarkToCollectionAlreadyAdded() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME))
                .thenReturn(bookmarkCollection);

        assertEquals(Response.URL_ALREADY_ADDED.getMessage(), bookmarkRepository
                .addBookmarkToCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testAddBookmarkToCollectionNotFound() {
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME)).thenReturn(null);

        assertEquals(Response.COLLECTION_NOT_FOUND.getMessage(),
                bookmarkRepository.addBookmarkToCollection(COLLECTION_NAME,
                        URL));
    }

    @Test
    public void testRemoveBookmarkToCollection() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME))
                .thenReturn(bookmarkCollection);

        assertEquals(Response.URL_REMOVED.getMessage(), bookmarkRepository
                .removeBookmarkFromCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testRemoveBookmarkToCollectionNotFoundUrl() {
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME))
                .thenReturn(new BookmarkCollection());

        assertEquals(Response.URL_NOT_FOUND.getMessage(), bookmarkRepository
                .removeBookmarkFromCollection(COLLECTION_NAME, URL));
    }

    @Test
    public void testRemoveBookmarkToCollectionNotFoundCollection() {
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME)).thenReturn(null);

        assertEquals(Response.COLLECTION_NOT_FOUND.getMessage(),
                bookmarkRepository.removeBookmarkFromCollection(COLLECTION_NAME,
                        URL));
    }

    @Test
    public void testGetAllBookmarks() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));

        BookmarkCollection bookmarkCollection2 = new BookmarkCollection();
        bookmarkCollection2.add(new Bookmark(URL));
        bookmarkCollection2.add(new Bookmark(URL2));

        Mockito.when(bookmarkCollections.values()).thenReturn(
                Arrays.asList(bookmarkCollection, bookmarkCollection2));

        assertEquals(URL + NEW_LINE + URL2 + NEW_LINE,
                bookmarkRepository.getAllBookmarks());
    }

    @Test
    public void testGetAllBookmarksFromCollection() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));
        bookmarkCollection.add(new Bookmark(URL2));
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME))
                .thenReturn(bookmarkCollection);

        assertEquals(URL + NEW_LINE + URL2 + NEW_LINE, bookmarkRepository
                .getAllBookmarksFromCollection(COLLECTION_NAME));
    }

    @Test
    public void testGetAllBookmarksFromCollectionNotFound() {
        Mockito.when(bookmarkCollections.get(COLLECTION_NAME)).thenReturn(null);

        assertEquals(Response.COLLECTION_NOT_FOUND.getMessage(),
                bookmarkRepository
                        .getAllBookmarksFromCollection(COLLECTION_NAME));
    }

    @Test
    public void testSearchBookmarksByTitle() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));

        BookmarkCollection bookmarkCollection2 = new BookmarkCollection();
        bookmarkCollection2.add(new Bookmark(URL));
        bookmarkCollection2.add(new Bookmark(URL2));

        Mockito.when(bookmarkCollections.values()).thenReturn(
                Arrays.asList(bookmarkCollection, bookmarkCollection2));

        assertEquals(URL + NEW_LINE,
                bookmarkRepository.searchBookmarksByTitle(TITLE));
    }

    @Test
    public void testSearchBookmarksByTitleNoBookmarks() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL2));

        Mockito.when(bookmarkCollections.values())
                .thenReturn(Arrays.asList(bookmarkCollection));

        assertEquals(Response.NO_BOOKMARKS.getMessage(),
                bookmarkRepository.searchBookmarksByTitle(TITLE));
    }

    @Test
    public void testSearchBookmarksByTags() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));

        BookmarkCollection bookmarkCollection2 = new BookmarkCollection();
        bookmarkCollection2.add(new Bookmark(URL2));
        bookmarkCollection2.add(new Bookmark(URL3));

        Mockito.when(bookmarkCollections.values()).thenReturn(
                Arrays.asList(bookmarkCollection, bookmarkCollection2));

        assertEquals(URL3 + NEW_LINE,
                bookmarkRepository.searchBookmarksByTags(RAW_TAGS));
    }

    @Test
    public void testSearchBookmarksByTagsNoBookmarks() {
        BookmarkCollection bookmarkCollection = new BookmarkCollection();
        bookmarkCollection.add(new Bookmark(URL));

        BookmarkCollection bookmarkCollection2 = new BookmarkCollection();
        bookmarkCollection2.add(new Bookmark(URL2));
        bookmarkCollection2.add(new Bookmark(URL3));

        Mockito.when(bookmarkCollections.values()).thenReturn(
                Arrays.asList(bookmarkCollection, bookmarkCollection2));

        assertEquals(Response.NO_BOOKMARKS.getMessage(), bookmarkRepository
                .searchBookmarksByTags(Arrays.asList(WRONG_TAG)));
    }

}
