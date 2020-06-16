package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

public class ListAllCommandExecutor implements CommandExecutor {

    @Override
    public String execute(final BookmarkManagerFacade bookmarkManagerFacade,
            final String argumentsString) {
        if (argumentsString.isEmpty()) {
            return bookmarkManagerFacade.getAllBookmarks();
        }

        return WRONG_ARGUMENTS.getMessage();
    }

}
