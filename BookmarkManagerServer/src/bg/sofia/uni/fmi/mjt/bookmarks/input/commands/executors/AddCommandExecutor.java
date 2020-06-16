package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.InputProcessor.WHITESPACES_REGEX;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

public class AddCommandExecutor implements CommandExecutor {

    private static final String HAS_WHITESPACE_REGEX = ".*" + WHITESPACES_REGEX
            + ".*";

    @Override
    public String execute(final BookmarkManagerFacade bookmarkManagerFacade,
            final String argumentsString) {
        if (!argumentsString.matches(HAS_WHITESPACE_REGEX)) {
            return bookmarkManagerFacade.add(argumentsString);
        }

        return WRONG_ARGUMENTS.getMessage();
    }

}
