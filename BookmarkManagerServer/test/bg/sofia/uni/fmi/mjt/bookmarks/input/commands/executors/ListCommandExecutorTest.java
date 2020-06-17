package bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManager;

@RunWith(MockitoJUnitRunner.class)
public class ListCommandExecutorTest {

    private static final String COLLECTION_NAME = "my collection";

    private static final String RESPONSE = "youtube.com, google.com";

    @Mock
    private BookmarkManager bookmarkManager;

    @Test
    public void testExecute() {
        Mockito.when(bookmarkManager
                .getBookmarksFromCollection(COLLECTION_NAME))
                .thenReturn(RESPONSE);

        CommandExecutor commandExecutor = new ListCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                COLLECTION_NAME);
        assertEquals(RESPONSE, response);
    }

}
