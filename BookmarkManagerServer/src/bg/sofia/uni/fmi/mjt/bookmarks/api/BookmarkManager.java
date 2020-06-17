package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.ALREADY_LOGGED_IN;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.CLOSED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.INVALID_CREDENTIALS;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.LOGGED_IN;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.NOT_LOGGED_IN;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.REGISTERED;

import java.util.List;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.BookmarkRepository;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.UserRepository;

public class BookmarkManager {

    private BookmarkRepository bookmarkRepository = null;
    private UserRepository userRepository = UserRepository.getInstance();

    public String register(final String username, final String password) {
        if (bookmarkRepository == null) {
            if (userRepository.register(username, password)) {
                return REGISTERED.getMessage();
            }
            return Response.USER_WITH_USERNAME_ALREADY_EXISTS.getMessage();
        }

        return ALREADY_LOGGED_IN.getMessage();
    }

    // TODO encrypt password maybe??
    // TODO multiple clients login to same account
    public String login(final String username, final String password) {
        if (bookmarkRepository == null) {
            if (userRepository.isRegistered(username, password)) {
                bookmarkRepository = BookmarkRepository.newInstance(username);
                return LOGGED_IN.getMessage();
            }
            return INVALID_CREDENTIALS.getMessage();
        }

        return ALREADY_LOGGED_IN.getMessage();
    }

    public String createCollection(final String collectionName) {
        if (bookmarkRepository != null) {
            return bookmarkRepository.createCollection(collectionName);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String add(final String bookmarkUrl) {
        if (bookmarkRepository != null) {
            return bookmarkRepository
                    .addBookmarkToDefaultCollection(bookmarkUrl);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String addToCollection(final String collectionName,
            final String bookmarkUrl) {
        if (bookmarkRepository != null) {
            return bookmarkRepository.addBookmarkToCollection(collectionName,
                    bookmarkUrl);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String removeFromCollection(final String collectionName,
            final String url) {
        if (bookmarkRepository != null) {
            return bookmarkRepository
                    .removeBookmarkFromCollection(collectionName, url);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String getAllBookmarks() {
        if (bookmarkRepository != null) {
            return bookmarkRepository.getAllBookmarks();
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String getBookmarksFromCollection(final String collectionName) {
        if (bookmarkRepository != null) {
            return bookmarkRepository
                    .getAllBookmarksFromCollection(collectionName);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String searchBookmarksByTitle(final String title) {
        if (bookmarkRepository != null) {
            return bookmarkRepository.searchBookmarksByTitle(title);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String searchBookmarksByTags(final List<String> tags) {
        if (bookmarkRepository != null) {
            return bookmarkRepository.searchBookmarksByTags(tags);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String importFromChrome(final List<String> chromeUrls) {
        if (bookmarkRepository != null) {
            return bookmarkRepository
                    .addAllBookmarksToChromeCollection(chromeUrls);
        }

        return NOT_LOGGED_IN.getMessage();
    }

    public String persistBookmarks() {
        if (bookmarkRepository != null) {
            bookmarkRepository.persistBookmarks();
        }

        return CLOSED.getMessage();
    }

    public void persistUsers() {
        userRepository.persistUsers();
    }
}
