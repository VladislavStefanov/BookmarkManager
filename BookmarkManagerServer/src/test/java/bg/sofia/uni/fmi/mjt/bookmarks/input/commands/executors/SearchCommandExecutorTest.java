package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

@RunWith(MockitoJUnitRunner.class)
public class SearchCommandExecutorTest {

    private static final String TAGS_SPECIFIER = "-tags ";

    private static final String TITLE_SPECIFIER = "-title ";

    private static final String RESPONSE = "youtube.com";

    private static final String WRONG_ARGUMENTS = "aaa";

    private static final String WRONG_ARGUMENTS2 = "-tags";

    private static final String SPACE = " ";

    private static final String TITLE = "youtube";

    private static final List<String> TAGS = Arrays.asList("video", "youtube");

    @Mock
    private BookmarkManager bookmarkManager;

    @Test
    public void testExecuteSearchByTiltle() {
        Mockito.when(bookmarkManager.searchBookmarksByTitle(TITLE))
                .thenReturn(RESPONSE);
        CommandExecutor commandExecutor = new SearchCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                TITLE_SPECIFIER + TITLE);
        assertEquals(RESPONSE, response);
    }

    @Test
    public void testExecuteSearchByTags() {
        Mockito.when(bookmarkManager.searchBookmarksByTags(TAGS))
                .thenReturn(RESPONSE);
        CommandExecutor commandExecutor = new SearchCommandExecutor();

        StringBuilder tagsStringBuilder = new StringBuilder(TAGS_SPECIFIER);
        TAGS.forEach(tag -> tagsStringBuilder.append(tag).append(SPACE));
        String response = commandExecutor.execute(bookmarkManager,
                tagsStringBuilder.toString());
        assertEquals(RESPONSE, response);
    }

    @Test
    public void testExecuteWrongArguments() {
        CommandExecutor commandExecutor = new SearchCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                WRONG_ARGUMENTS);
        assertEquals(Response.WRONG_ARGUMENTS.getMessage(), response);

        response = commandExecutor.execute(bookmarkManager,
                WRONG_ARGUMENTS2);
        assertEquals(Response.WRONG_ARGUMENTS.getMessage(), response);
    }

}
