package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Bookmark;
import bg.sofia.uni.fmi.mjt.bookmarks.model.BookmarkCollection;

public class FileEditorTest {

    private static final String IO_EXCEPTION_OCCURRED_WHILE_DELETING_FILE = "IOException occurred while deleting file.";
    private static final String BOOKMARKS_PATH = "test/bg/sofia/uni/fmi/mjt/bookmarks/repositories/files/testBookmarks.txt";
    private static final String BOOKMARKS_WRONG_PATH = "test/bg/sofia/uni/fmi/mjt/bookmarks/repositories/files/testBookmarksWrong.txt";
    private static final String BOOKMARKS_PERSIST_PATH = "test/bg/sofia/uni/fmi/mjt/bookmarks/repositories/files/testBookmarksPersist.txt";
    private static final Map<String, BookmarkCollection> BOOKMARKS = new HashMap<>();

    static {
        BookmarkCollection collection1 = new BookmarkCollection();
        collection1.add(new Bookmark("youtube.com"));
        collection1.add(new Bookmark("google.com"));

        BOOKMARKS.put("myCollection1", collection1);

        BookmarkCollection collection2 = new BookmarkCollection();
        collection2.add(new Bookmark("google.com"));

        BOOKMARKS.put("myCollection2", collection2);
    }

    @Test
    public void testLoad() {
        FileEditor<BookmarkCollection> fileEditor = new FileEditor<BookmarkCollection>(
                BOOKMARKS_PATH, BookmarkCollection.class);

        Map<String, BookmarkCollection> loadedBookmarks = fileEditor.load();

        assertEquals(BOOKMARKS, loadedBookmarks);
    }

    @Test
    public void testLoadWtihWrongFile() {
        FileEditor<BookmarkCollection> fileEditor = new FileEditor<BookmarkCollection>(
                BOOKMARKS_WRONG_PATH, BookmarkCollection.class);

        Map<String, BookmarkCollection> loadedBookmarks = fileEditor.load();

        assertTrue(loadedBookmarks.isEmpty());
    }

    @Test
    public void testPersist() {
        FileEditor<BookmarkCollection> fileEditor = new FileEditor<BookmarkCollection>(
                BOOKMARKS_PERSIST_PATH, BookmarkCollection.class);

        fileEditor.persist(BOOKMARKS);

        Path bookmarksPersistPath = Paths.get(BOOKMARKS_PERSIST_PATH);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, BookmarkCollection> loadedBookmarks = mapper.readValue(
                    Files.newBufferedReader(bookmarksPersistPath),
                    new TypeReference<Map<String, BookmarkCollection>>() {
                    });

            assertEquals(BOOKMARKS, loadedBookmarks);
        } catch (IOException e1) {
            fail("File not persisted.");
        }

        try {
            Files.deleteIfExists(bookmarksPersistPath);
        } catch (IOException e) {
            fail(IO_EXCEPTION_OCCURRED_WHILE_DELETING_FILE);
        }
    }
}
