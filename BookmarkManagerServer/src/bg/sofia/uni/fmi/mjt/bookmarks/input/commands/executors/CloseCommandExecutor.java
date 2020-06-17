package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public class CloseCommandExecutor implements CommandExecutor {

    @Override
    public String execute(final BookmarkManager bookmarkManager,
            final String argumentsString) {
        if (argumentsString.isEmpty()) {
            return bookmarkManager.persistBookmarks();
        }

        return WRONG_ARGUMENTS.getMessage();
    }
}
