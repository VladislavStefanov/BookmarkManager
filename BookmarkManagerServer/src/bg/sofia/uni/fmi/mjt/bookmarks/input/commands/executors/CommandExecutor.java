package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

public interface CommandExecutor {

    String execute(final BookmarkManagerFacade bookmarkManagerFacade, final String argumentsString);
}
