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
public class AddCommandExecutorTest {
    private static final String WRONG_ARGUMENTS = "aa aa";

    private static final String URL = "abcd";

    @Mock
    private BookmarkManagerFacade bookmarkManagerFacade;

    @Test
    public void testExecute() {
        Mockito.when(bookmarkManagerFacade.add(URL))
                .thenReturn(Response.URL_ADDED.getMessage());

        CommandExecutor commandExecutor = new AddCommandExecutor();
        String response = commandExecutor.execute(bookmarkManagerFacade, URL);
        assertEquals(Response.URL_ADDED.getMessage(), response);
    }

    @Test
    public void testExecuteWrongArguments() {
        CommandExecutor commandExecutor = new AddCommandExecutor();
        String response = commandExecutor.execute(bookmarkManagerFacade,
                WRONG_ARGUMENTS);
        assertEquals(Response.WRONG_ARGUMENTS.getMessage(), response);
    }

}
