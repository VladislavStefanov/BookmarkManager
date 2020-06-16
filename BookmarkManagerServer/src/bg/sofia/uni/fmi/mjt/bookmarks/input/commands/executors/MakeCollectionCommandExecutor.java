package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

public class MakeCollectionCommandExecutor implements CommandExecutor {

    @Override
    public String execute(final BookmarkManagerFacade bookmarkManagerFacade,
            final String argumentsString) {
        if (!argumentsString.isEmpty()) {
            return bookmarkManagerFacade.createCollection(argumentsString);
        }

        return Response.WRONG_ARGUMENTS.getMessage();
    }

}
