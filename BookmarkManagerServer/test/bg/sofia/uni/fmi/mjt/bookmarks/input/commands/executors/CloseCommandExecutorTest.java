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
public class CloseCommandExecutorTest {

    private static final String WRONG_ARGUMENTS = "a";

    @Mock
    private BookmarkManagerFacade bookmarkManagerFacade;

    @Test
    public void testExecute() {
        Mockito.when(bookmarkManagerFacade.persistBookmarks())
                .thenReturn(Response.CLOSED.getMessage());

        CommandExecutor commandExecutor = new CloseCommandExecutor();
        String response = commandExecutor.execute(bookmarkManagerFacade,
                new String());
        assertEquals(Response.CLOSED.getMessage(), response);
    }

    @Test
    public void testExecuteWrongArguments() {
        CommandExecutor commandExecutor = new CloseCommandExecutor();
        String response = commandExecutor.execute(bookmarkManagerFacade,
                WRONG_ARGUMENTS);
        assertEquals(Response.WRONG_ARGUMENTS.getMessage(), response);
    }

}
