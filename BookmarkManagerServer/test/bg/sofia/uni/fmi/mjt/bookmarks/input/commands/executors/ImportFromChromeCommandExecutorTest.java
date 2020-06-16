package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;

@RunWith(MockitoJUnitRunner.class)
public class ImportFromChromeCommandExecutorTest {
    private static final String SPACE = " ";
    private static final String URL1 = "abcd";
    private static final String URL2 = "zxcv";

    @Mock
    private BookmarkManagerFacade bookmarkManagerFacade;

    @Test
    public void testExecute() {
        Mockito.when(bookmarkManagerFacade
                .importFromChrome(Arrays.asList(URL1, URL2)))
                .thenReturn(Response.IMPORTED_FROM_CHROME.getMessage());

        CommandExecutor commandExecutor = new ImportFromChromeCommandExecutor();
        String response = commandExecutor.execute(bookmarkManagerFacade,
                URL1 + SPACE + URL2);
        assertEquals(Response.IMPORTED_FROM_CHROME.getMessage(), response);
    }

}
