package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Bookmark;
import bg.sofia.uni.fmi.mjt.bookmarks.model.BookmarkCollection;

public class FileEditorTest {

    private static final String IO_EXCEPTION_OCCURRED_WHILE_DELETING_FILE = "IOException occurred while deleting file.";
    private static Map<String, BookmarkCollection> bookmarks = new HashMap<>();

    private static Path bookmarksPath;
    private static Path bookmarksWrongPath;
    private static Path bookmarksPersistPath;

    @BeforeClass
    public static void setUpBeforeAllTests() {
        try {
            bookmarksPath = Paths.get(StopwordFileLoaderTest.class
                    .getResource("testBookmarks.txt").toURI());
            bookmarksPersistPath = Paths
                    .get(StopwordFileLoaderTest.class.getProtectionDomain()
                            .getCodeSource().getLocation().toURI())
                    .resolve("testBookmarksPersist.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        bookmarksWrongPath = Paths.get("testBookmarksWrong.txt");

        BookmarkCollection collection1 = new BookmarkCollection();
        collection1.add(new Bookmark("youtube.com"));
        collection1.add(new Bookmark("google.com"));

        bookmarks.put("myCollection1", collection1);

        BookmarkCollection collection2 = new BookmarkCollection();
        collection2.add(new Bookmark("google.com"));

        bookmarks.put("myCollection2", collection2);
    }

    @Test
    public void testLoad() {
        FileEditor<BookmarkCollection> fileEditor = new FileEditor<BookmarkCollection>(
                bookmarksPath, BookmarkCollection.class);

        Map<String, BookmarkCollection> loadedBookmarks = fileEditor.load();

        assertEquals(bookmarks, loadedBookmarks);
    }

    @Test
    public void testLoadWtihWrongFile() {
        FileEditor<BookmarkCollection> fileEditor = new FileEditor<BookmarkCollection>(
                bookmarksWrongPath, BookmarkCollection.class);

        Map<String, BookmarkCollection> loadedBookmarks = fileEditor.load();

        assertTrue(loadedBookmarks.isEmpty());
    }

    @Test
    public void testPersist() {
        FileEditor<BookmarkCollection> fileEditor = new FileEditor<BookmarkCollection>(
                bookmarksPersistPath, BookmarkCollection.class);

        fileEditor.persist(bookmarks);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, BookmarkCollection> loadedBookmarks = mapper.readValue(
                    Files.newBufferedReader(bookmarksPersistPath),
                    new TypeReference<Map<String, BookmarkCollection>>() {
                    });

            assertEquals(bookmarks, loadedBookmarks);
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
