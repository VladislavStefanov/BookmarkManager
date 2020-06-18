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
public class MakeCollectionCommandExecutorTest {

    private static final String COLLECTION_NAME = "my collection";

    @Mock
    private BookmarkManager bookmarkManager;

    @Test
    public void testExecute() {
        Mockito.when(bookmarkManager.createCollection(COLLECTION_NAME))
                .thenReturn(Response.COLLECTION_CREATED.getMessage());

        CommandExecutor commandExecutor = new MakeCollectionCommandExecutor();
        String response = commandExecutor.execute(bookmarkManager,
                COLLECTION_NAME);
        assertEquals(Response.COLLECTION_CREATED.getMessage(), response);
    }

}
