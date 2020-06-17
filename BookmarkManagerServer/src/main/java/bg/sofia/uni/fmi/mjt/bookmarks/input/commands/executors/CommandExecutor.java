package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public interface CommandExecutor {

    String execute(final BookmarkManager bookmarkManager, final String argumentsString);
}
