package bg.sofia.uni.fmi.mjt.bookmarks.api;

import java.util.List;

import bg.sofia.uni.fmi.mjt.bookmarks.api.state.BookmarkManagerState;
import bg.sofia.uni.fmi.mjt.bookmarks.api.state.GuestBookmarkManagerState;

public class BookmarkManager {

    private BookmarkManagerState bookmarkManagerState = new GuestBookmarkManagerState(
            this);

    public String register(final String username, final String password) {
        return bookmarkManagerState.register(username, password);
    }

    // TODO encrypt password maybe??
    // TODO multiple clients login to same account
    public String login(final String username, final String password) {
        return bookmarkManagerState.login(username, password);
    }

    public String createCollection(final String collectionName) {
        return bookmarkManagerState.createCollection(collectionName);
    }

    public String addToDefaultCollection(final String bookmarkUrl) {
        return bookmarkManagerState.addToDefaultCollection(bookmarkUrl);
    }

    public String addToCollection(final String collectionName,
            final String bookmarkUrl) {
        return bookmarkManagerState.addToCollection(collectionName,
                bookmarkUrl);
    }

    public String removeFromCollection(final String collectionName,
            final String url) {
        return bookmarkManagerState.removeFromCollection(collectionName, url);
    }

    public String getAllBookmarks() {
        return bookmarkManagerState.getAllBookmarks();
    }

    public String getBookmarksFromCollection(final String collectionName) {
        return bookmarkManagerState.getBookmarksFromCollection(collectionName);
    }

    public String searchBookmarksByTitle(final String title) {
        return bookmarkManagerState.searchBookmarksByTitle(title);
    }

    public String searchBookmarksByTags(final List<String> tags) {
        return bookmarkManagerState.searchBookmarksByTags(tags);
    }

    public String importFromChrome(final List<String> chromeUrls) {
        return bookmarkManagerState.importFromChrome(chromeUrls);
    }

    public String persistBookmarks() {
        return bookmarkManagerState.persistBookmarks();
    }

    public void persistUsers() {
        bookmarkManagerState.persistUsers();
    }

    public void setState(final BookmarkManagerState bookmarkManagerState) {
        this.bookmarkManagerState = bookmarkManagerState;
    }
}
