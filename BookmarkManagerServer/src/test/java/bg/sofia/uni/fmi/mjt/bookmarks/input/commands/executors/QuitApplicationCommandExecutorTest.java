package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

@RunWith(MockitoJUnitRunner.class)
public class QuitApplicationCommandExecutorTest {

    private static final String WRONG_ARGUMENTS = "a";

    @Mock
    private BookmarkManager bookmarkManager;

    @Test
    public void testExecute() {
        Mockito.doNothing().when(bookmarkManager).persistUsers();

        CommandExecutor commandExecutor = new QuitApplicationCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                new String());
        assertTrue(response.isEmpty());
    }

    @Test
    public void testExecuteWrongArguments() {
        CommandExecutor commandExecutor = new QuitApplicationCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                WRONG_ARGUMENTS);
        assertEquals(Response.WRONG_ARGUMENTS.getMessage(), response);
    }

}
