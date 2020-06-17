package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.InputHandler.WHITESPACES_REGEX;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public class RemoveFromCommandExecutor implements CommandExecutor {

    private static final int MIN_ARGUMENTS_COUNT = 1;

    @Override
    public String execute(final BookmarkManager bookmarkManager,
            final String argumentsString) {
        String[] argumentTokens = argumentsString.split(WHITESPACES_REGEX);

        if (argumentTokens.length > MIN_ARGUMENTS_COUNT) {
            String link = argumentTokens[argumentTokens.length - 1];
            int linkPosition = argumentsString.lastIndexOf(link);
            String collection = argumentsString.substring(0, linkPosition)
                    .trim();

            return bookmarkManager.removeFromCollection(collection, link);
        }

        return WRONG_ARGUMENTS.getMessage();
    }

}
