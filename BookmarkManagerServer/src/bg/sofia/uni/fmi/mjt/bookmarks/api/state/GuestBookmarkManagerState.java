package bg.sofia.uni.fmi.mjt.bookmarks.api.state;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.INVALID_CREDENTIALS;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.LOGGED_IN;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.NOT_LOGGED_IN;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.REGISTERED;

import java.util.List;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public class GuestBookmarkManagerState extends BookmarkManagerState {

    public GuestBookmarkManagerState(final BookmarkManager bookmarkManagerWrapper) {
        super(bookmarkManagerWrapper);
    }

    @Override
    public String register(final String username, final String password) {
        if (userRepository.register(username, password)) {
            return REGISTERED.getMessage();
        }
        return Response.USER_WITH_USERNAME_ALREADY_EXISTS.getMessage();
    }

    @Override
    public String login(final String username, final String password) {
        if (userRepository.isRegistered(username, password)) {
            bookmarkManagerWrapper.setState(new LoggedInBookmarkManagerState(
                    bookmarkManagerWrapper, username));
            return LOGGED_IN.getMessage();
        }
        return INVALID_CREDENTIALS.getMessage();
    }

    @Override
    public String createCollection(final String collectionName) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String add(final String bookmarkUrl) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String addToCollection(final String collectionName,
            final String bookmarkUrl) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String removeFromCollection(final String collectionName,
            final String url) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String getAllBookmarks() {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String getBookmarksFromCollection(final String collectionName) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String searchBookmarksByTitle(final String title) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String searchBookmarksByTags(final List<String> tags) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String importFromChrome(final List<String> chromeUrls) {
        return NOT_LOGGED_IN.getMessage();
    }

    @Override
    public String persistBookmarks() {
        return NOT_LOGGED_IN.getMessage();
    }

}
