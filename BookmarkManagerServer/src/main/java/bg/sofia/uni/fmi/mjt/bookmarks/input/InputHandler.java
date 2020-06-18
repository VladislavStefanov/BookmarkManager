package bg.sofia.uni.fmi.mjt.bookmarks.input;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_COMMAND;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.Command;

//TODO maybe add producer-consumer

public class InputHandler {

    public static final String WHITESPACES_REGEX = "\\s+";

    private static final int MESSAGE_TOKENS_COUNT = 2;
    private BookmarkManager bookmarkManager = new BookmarkManager();

    public String handle(final String message) {
        String[] messageTokens = message.trim().split(WHITESPACES_REGEX,
                MESSAGE_TOKENS_COUNT);
        int messageTokensIterator = 0;
        Command command = Command
                .fromString(messageTokens[messageTokensIterator++]);
        if (command != null) {
            String argumentsString = (messageTokens.length == 1) ? new String()
                    : messageTokens[messageTokensIterator++];
            return command.execute(bookmarkManager, argumentsString);
        }

        return WRONG_COMMAND.getMessage();
    }
}
