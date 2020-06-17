package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.InputHandler.WHITESPACES_REGEX;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public class AddCommandExecutor implements CommandExecutor {

    private static final String HAS_WHITESPACE_REGEX = ".*" + WHITESPACES_REGEX
            + ".*";

    @Override
    public String execute(final BookmarkManager bookmarkManager,
            final String argumentsString) {
        if (!argumentsString.matches(HAS_WHITESPACE_REGEX)) {
            return bookmarkManager.add(argumentsString);
        }

        return WRONG_ARGUMENTS.getMessage();
    }

}
