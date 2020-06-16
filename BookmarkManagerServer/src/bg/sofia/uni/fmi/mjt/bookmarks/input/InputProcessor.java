package bg.sofia.uni.fmi.mjt.bookmarks.input;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_COMMAND;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.Command;

//TODO maybe add producer-consumer

public class InputProcessor {

    public static final String WHITESPACES_REGEX = "\\s+";

    private static final int MESSAGE_TOKENS_COUNT = 2;
    private BookmarkManagerFacade bookmarkManagerFacade = new BookmarkManagerFacade();

    public String process(final String message) {
        String[] messageTokens = message.trim().split(WHITESPACES_REGEX,
                MESSAGE_TOKENS_COUNT);
        int messageTokensIterator = 0;
        Command command = Command
                .fromString(messageTokens[messageTokensIterator++]);
        if (command != null) {
            String argumentsString = (messageTokens.length == 1) ? new String()
                    : messageTokens[messageTokensIterator++];
            return command.execute(bookmarkManagerFacade, argumentsString);
        }

        return WRONG_COMMAND.getMessage();
    }
}
