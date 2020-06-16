package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.InputProcessor.WHITESPACES_REGEX;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

public class LoginCommandExecutor implements CommandExecutor {

    private static final int ARGUMENTS_COUNT = 2;

    @Override
    public String execute(final BookmarkManagerFacade bookmarkManagerFacade,
            final String argumentsString) {
        String[] argumentTokens = argumentsString.split(WHITESPACES_REGEX);
        if (argumentTokens.length == ARGUMENTS_COUNT) {
            int argumentsTokensIterator = 0;
            String username = argumentTokens[argumentsTokensIterator++];
            String password = argumentTokens[argumentsTokensIterator++];

            return bookmarkManagerFacade.login(username, password);
        }

        return WRONG_ARGUMENTS.getMessage();
    }

}
