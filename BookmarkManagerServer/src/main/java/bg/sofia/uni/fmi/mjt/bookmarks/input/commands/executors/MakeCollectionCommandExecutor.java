package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public class MakeCollectionCommandExecutor implements CommandExecutor {

    @Override
    public String execute(final BookmarkManager bookmarkManager,
            final String argumentsString) {
        if (!argumentsString.isEmpty()) {
            return bookmarkManager.createCollection(argumentsString);
        }

        return Response.WRONG_ARGUMENTS.getMessage();
    }

}
