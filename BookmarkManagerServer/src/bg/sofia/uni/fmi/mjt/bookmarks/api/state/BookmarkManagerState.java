package bg.sofia.uni.fmi.mjt.bookmarks.api.state;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.CLOSED;

import java.util.List;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.UserRepository;

public abstract class BookmarkManagerState {
    protected UserRepository userRepository = UserRepository.getInstance();
    protected BookmarkManager bookmarkManagerWrapper;

    protected BookmarkManagerState(
            final BookmarkManager bookmarkManagerWrapper) {
        this.bookmarkManagerWrapper = bookmarkManagerWrapper;
    }

    public abstract String register(final String username,
            final String password);

    public abstract String login(final String username, final String password);

    public abstract String createCollection(final String collectionName);

    public abstract String add(final String bookmarkUrl);

    public abstract String addToCollection(final String collectionName,
            final String bookmarkUrl);

    public abstract String removeFromCollection(final String collectionName,
            final String url);

    public abstract String getAllBookmarks();

    public abstract String getBookmarksFromCollection(
            final String collectionName);

    public abstract String searchBookmarksByTitle(final String title);

    public abstract String searchBookmarksByTags(final List<String> tags);

    public abstract String importFromChrome(final List<String> chromeUrls);

    public String persistBookmarks() {
        return CLOSED.getMessage();
    }

    public void persistUsers() {
        userRepository.persistUsers();
    }
}
