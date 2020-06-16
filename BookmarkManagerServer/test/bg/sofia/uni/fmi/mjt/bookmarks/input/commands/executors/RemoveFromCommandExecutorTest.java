package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

@RunWith(MockitoJUnitRunner.class)
public class RemoveFromCommandExecutorTest {

    private static final String SPACE = " ";

    private static final String COLLECTION_NAME = "mine";

    private static final String URL = "abcd";

    private static final String WRONG_ARGUMENTS = "aaa";

    @Mock
    private BookmarkManagerFacade bookmarkManagerFacade;

    @Test
    public void testExecute() {
        Mockito.when(bookmarkManagerFacade.removeFromCollection(COLLECTION_NAME,
                URL)).thenReturn(Response.URL_REMOVED.getMessage());
        CommandExecutor commandExecutor = new RemoveFromCommandExecutor();
        String response = commandExecutor.execute(bookmarkManagerFacade,
                COLLECTION_NAME + SPACE + URL);
        assertEquals(Response.URL_REMOVED.getMessage(), response);
    }

    @Test
    public void testExecuteWrongArguments() {
        CommandExecutor commandExecutor = new RemoveFromCommandExecutor();
        String response = commandExecutor.execute(bookmarkManagerFacade,
                WRONG_ARGUMENTS);
        assertEquals(Response.WRONG_ARGUMENTS.getMessage(), response);
    }
}
