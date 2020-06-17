package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.input.InputProcessor.WHITESPACES_REGEX;

import java.util.Arrays;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public class ImportFromChromeCommandExecutor implements CommandExecutor {

    @Override
    public String execute(final BookmarkManager bookmarkManager,
            final String argumentsString) {
        return bookmarkManager.importFromChrome(
                Arrays.asList(argumentsString.split(WHITESPACES_REGEX)));
    }

}
