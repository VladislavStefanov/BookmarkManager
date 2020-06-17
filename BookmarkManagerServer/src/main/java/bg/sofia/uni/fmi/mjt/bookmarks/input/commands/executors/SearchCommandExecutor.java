package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRONG_ARGUMENTS;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.InputHandler.WHITESPACES_REGEX;

import java.util.ArrayList;
import java.util.List;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

public class SearchCommandExecutor implements CommandExecutor {

    private static final int MIN_ARGUMENTS_COUNT = 1;
    private static final String TITLE_SEARCH_SPECIFIER = "-title";
    private static final String TAGS_SEARCH_SPECIFIER = "-tags";

    @Override
    public String execute(final BookmarkManager bookmarkManager,
            final String argumentsString) {
        String[] argumentTokens = argumentsString.split(WHITESPACES_REGEX);
        int argumentTokensIterator = 0;
        String searchTypeSpecifier = argumentTokens[argumentTokensIterator++];

        if (argumentTokens.length > MIN_ARGUMENTS_COUNT) {
            if (TAGS_SEARCH_SPECIFIER.equalsIgnoreCase(searchTypeSpecifier)) {
                List<String> tags = new ArrayList<>();
                while (argumentTokensIterator < argumentTokens.length) {
                    tags.add(argumentTokens[argumentTokensIterator++]);
                }

                return bookmarkManager.searchBookmarksByTags(tags);
            }

            if (TITLE_SEARCH_SPECIFIER.equalsIgnoreCase(searchTypeSpecifier)) {
                String title = argumentsString
                        .replace(TITLE_SEARCH_SPECIFIER, new String()).trim();

                return bookmarkManager.searchBookmarksByTitle(title);
            }
        }

        return WRONG_ARGUMENTS.getMessage();
    }

}
