package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.InputProcessor.WHITESPACES_REGEX;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

public class AddToCommandExecutor implements CommandExecutor {

    private static final int MIN_ARGUMENTS_COUNT = 2;

    @Override
    public String execute(final BookmarkManagerFacade bookmarkManagerFacade,
            final String argumentsString) {
        String[] argumentTokens = argumentsString.split(WHITESPACES_REGEX);
        if (argumentTokens.length >= MIN_ARGUMENTS_COUNT) {
            String bookmarkUrl = argumentTokens[argumentTokens.length - 1];
            int linkPosition = argumentsString.lastIndexOf(bookmarkUrl);
            String collection = argumentsString.substring(0, linkPosition)
                    .trim();

            return bookmarkManagerFacade.addToCollection(collection,
                    bookmarkUrl);
        }

        return WRONG_ARGUMENTS.getMessage();
    }

}
