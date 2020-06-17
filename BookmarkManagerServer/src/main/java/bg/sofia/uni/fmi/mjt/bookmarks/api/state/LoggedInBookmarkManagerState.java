package bg.sofia.uni.fmi.mjt.bookmarks.api.state;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.ALREADY_LOGGED_IN;

import java.util.List;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.BookmarkRepository;

public class LoggedInBookmarkManagerState extends BookmarkManagerState {
    private BookmarkRepository bookmarkRepository;

    public LoggedInBookmarkManagerState(final BookmarkManager bookmarkManagerWrapper,
            final String username) {
        super(bookmarkManagerWrapper);
        bookmarkRepository = BookmarkRepository.newInstance(username);
    }

    @Override
    public String register(final String username, final String password) {
        return ALREADY_LOGGED_IN.getMessage();
    }

    @Override
    public String login(final String username, final String password) {
        return ALREADY_LOGGED_IN.getMessage();
    }

    @Override
    public String createCollection(final String collectionName) {
        return bookmarkRepository.createCollection(collectionName);
    }

    @Override
    public String add(final String bookmarkUrl) {
        return bookmarkRepository.addBookmarkToDefaultCollection(bookmarkUrl);
    }

    @Override
    public String addToCollection(final String collectionName,
            final String bookmarkUrl) {
        return bookmarkRepository.addBookmarkToCollection(collectionName,
                bookmarkUrl);
    }

    @Override
    public String removeFromCollection(final String collectionName,
            final String url) {
        return bookmarkRepository.removeBookmarkFromCollection(collectionName,
                url);
    }

    @Override
    public String getAllBookmarks() {
        return bookmarkRepository.getAllBookmarks();
    }

    @Override
    public String getBookmarksFromCollection(final String collectionName) {
        return bookmarkRepository.getAllBookmarksFromCollection(collectionName);
    }

    @Override
    public String searchBookmarksByTitle(final String title) {
        return bookmarkRepository.searchBookmarksByTitle(title);
    }

    @Override
    public String searchBookmarksByTags(final List<String> tags) {
        return bookmarkRepository.searchBookmarksByTags(tags);
    }

    @Override
    public String importFromChrome(final List<String> chromeUrls) {
        return bookmarkRepository.addAllBookmarksToChromeCollection(chromeUrls);
    }

    @Override
    public String persistBookmarks() {
        bookmarkRepository.persistBookmarks();
        return super.persistBookmarks();
    }

}
