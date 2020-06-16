package bg.sofia.uni.fmi.mjt.bookmarks.repositories;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.COLLECTION_ALREADY_EXISTS;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.COLLECTION_CREATED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.COLLECTION_NOT_FOUND;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.IMPORTED_FROM_CHROME;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.NO_BOOKMARKS;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.URL_ADDED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.URL_ALREADY_ADDED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.URL_NOT_FOUND;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.URL_REMOVED;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Bookmark;
import bg.sofia.uni.fmi.mjt.bookmarks.model.BookmarkCollection;
import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.files.BookmarkFileEditor;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.files.StopwordFileLoader;

public class BookmarkRepository {

    public static final Set<Tag> STOPWORDS = new StopwordFileLoader()
            .loadStopwords();

    private static final String DEFAULT_COLLECTION_NAME = "default";
    private static final String CHROME_COLLECTION_NAME = "chrome";

    private Map<String, BookmarkCollection> bookmarkCollections = null;
    private BookmarkFileEditor bookmarkFileEditor;

    public static BookmarkRepository newInstance(final String username) {
        BookmarkRepository bookmarkRepository = new BookmarkRepository(
                username);
        bookmarkRepository.loadBookmarks();

        return bookmarkRepository;
    }

    private void loadBookmarks() {
        bookmarkCollections = bookmarkFileEditor.load();
    }

    private BookmarkRepository(final String username) {
        bookmarkFileEditor = new BookmarkFileEditor(username);
    }

    public String createCollection(final String collectionName) {
        if (!bookmarkCollections.containsKey(collectionName)) {
            bookmarkCollections.put(collectionName, new BookmarkCollection());
            return COLLECTION_CREATED.getMessage();
        }

        return COLLECTION_ALREADY_EXISTS.getMessage();
    }

    public String addBookmarkToDefaultCollection(final String url) {
        Bookmark bookmark = new Bookmark(url);
        if (!bookmarkCollections.containsKey(DEFAULT_COLLECTION_NAME)) {
            bookmarkCollections.put(DEFAULT_COLLECTION_NAME,
                    new BookmarkCollection());
        }

        if (bookmarkCollections.get(DEFAULT_COLLECTION_NAME).add(bookmark)) {
            return URL_ADDED.getMessage();
        }

        return URL_ALREADY_ADDED.getMessage();
    }

    public String addBookmarkToCollection(final String collectionName,
            final String url) {
        Bookmark bookmark = new Bookmark(url);
        BookmarkCollection bookmarkCollection = bookmarkCollections
                .get(collectionName);
        if (bookmarkCollection != null) {
            if (bookmarkCollection.add(bookmark)) {
                return URL_ADDED.getMessage();
            }

            return URL_ALREADY_ADDED.getMessage();
        }

        return COLLECTION_NOT_FOUND.getMessage();
    }

    public String removeBookmarkFromCollection(final String collectionName,
            final String url) {
        Bookmark bookmark = new Bookmark(url);
        BookmarkCollection bookmarkCollection = bookmarkCollections
                .get(collectionName);
        if (bookmarkCollection != null) {
            if (bookmarkCollection.remove(bookmark)) {
                return URL_REMOVED.getMessage();
            }

            return URL_NOT_FOUND.getMessage();
        }

        return COLLECTION_NOT_FOUND.getMessage();
    }

    public String getAllBookmarks() {
        return getAllBookmarksCollection().toString();
    }

    public String getAllBookmarksFromCollection(final String collectionName) {
        BookmarkCollection bookmarkCollection = bookmarkCollections
                .get(collectionName);

        if (bookmarkCollection != null) {
            return bookmarkCollection.toString();
        }

        return COLLECTION_NOT_FOUND.getMessage();
    }

    public String searchBookmarksByTitle(final String title) {
        BookmarkCollection filteredBookmarkCollection = new BookmarkCollection();
        // @formatter:off
        filteredBookmarkCollection.addAll(getAllBookmarksCollection()
                    .getBookmarks()
                    .stream()
                    .filter(bookmark -> bookmark.containsTitle(title))
                    .collect(Collectors.toSet()));
        // @formatter:on

        if (!filteredBookmarkCollection.isEmpty()) {
            return filteredBookmarkCollection.toString();
        }

        return NO_BOOKMARKS.getMessage();

    }

    public String searchBookmarksByTags(final List<String> rawTags) {
        // @formatter:off
        Set<Tag> tags = rawTags.stream()
                .map(Tag::newTag)
                .filter(tag -> !tag.toString().isEmpty())
                .filter(tag -> !STOPWORDS.contains(tag))
                .collect(Collectors.toSet());

        BookmarkCollection foundBookmarks = new BookmarkCollection();
        foundBookmarks.addAll(
                getAllBookmarksCollection()
                .getBookmarks()
                .stream()
                .filter(bookmark -> bookmark.containsTags(tags))
                .collect(Collectors.toSet()));
        // @formatter:on

        if (!foundBookmarks.isEmpty()) {
            return foundBookmarks.toString();
        }

        return NO_BOOKMARKS.getMessage();
    }

    private BookmarkCollection getAllBookmarksCollection() {
        BookmarkCollection bookmarks = new BookmarkCollection();

        bookmarkCollections.values().forEach(bookmarkCollection -> bookmarks
                .addAllFromCollection(bookmarkCollection));

        return bookmarks;
    }

    public String addAllBookmarksToChromeCollection(
            final List<String> chromeUrls) {
        BookmarkCollection chromeCollection = new BookmarkCollection();
        // @formatter:off
        chromeCollection.addAll(chromeUrls
                .stream()
                .map(Bookmark::new)
                .collect(Collectors.toSet()));
        // @formatter:on
        bookmarkCollections.put(CHROME_COLLECTION_NAME, chromeCollection);

        return IMPORTED_FROM_CHROME.getMessage();
    }

    public void persistBookmarks() {
        bookmarkFileEditor.persist(bookmarkCollections);
    }

}
