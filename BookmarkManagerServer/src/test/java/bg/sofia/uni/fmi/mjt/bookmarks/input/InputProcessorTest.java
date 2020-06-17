package bg.sofia.uni.fmi.mjt.bookmarks.input;

import static bg.sofia.uni.fmi.mjt.bookmarks.input.commands.Command.LIST;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.commands.Command.LIST_ALL;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

@RunWith(MockitoJUnitRunner.class)
public class InputProcessorTest {

    private static final String DELIMITER = " ";

    private static final String COLLECTION_NAME = "default";

    private static final String WRONG_COMMAND = "wrong-command";

    private static final String BOOKMARKS = "bookmarks";

    @Mock
    private BookmarkManager bookmarkManager;

    @InjectMocks
    private InputProcessor inputProcessor;

    @Test
    public void testProcessWithoutArguments() {
        Mockito.when(bookmarkManager.getAllBookmarks())
                .thenReturn(BOOKMARKS);

        String response = inputProcessor.process(LIST_ALL.getName());
        assertEquals(BOOKMARKS, response);
    }

    @Test
    public void testProcessWithArguments() {
        Mockito.when(bookmarkManager
                .getBookmarksFromCollection(COLLECTION_NAME))
                .thenReturn(BOOKMARKS);

        String response = inputProcessor
                .process(LIST.getName() + DELIMITER + COLLECTION_NAME);
        assertEquals(BOOKMARKS, response);
    }

    @Test
    public void testProcessWrongCommand() {
        String response = inputProcessor.process(WRONG_COMMAND);
        assertEquals(Response.WRONG_COMMAND.getMessage(), response);
    }

}
