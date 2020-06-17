package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandExecutorTest {

    private static final String PASSWORD = "zxcv";

    private static final String USERNAME = "vladi";

    private static final String SPACE = " ";

    private static final String WRONG_ARGUMENTS = "aaa";

    @Mock
    private BookmarkManager bookmarkManager;

    @Test
    public void testExecute() {
        Mockito.when(bookmarkManager.login(USERNAME, PASSWORD))
                .thenReturn(Response.LOGGED_IN.getMessage());

        CommandExecutor commandExecutor = new LoginCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                USERNAME + SPACE + PASSWORD);
        assertEquals(Response.LOGGED_IN.getMessage(), response);
    }

    @Test
    public void testExecuteWrongArguments() {
        CommandExecutor commandExecutor = new LoginCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                WRONG_ARGUMENTS);
        assertEquals(Response.WRONG_ARGUMENTS.getMessage(), response);
    }

}
