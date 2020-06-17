package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordFileLoaderTest {

    private static final Set<Tag> STOPWORDS = new HashSet<>(
            Arrays.asList(Tag.newTag("when"), Tag.newTag("then")));

    private static Path stopwordsPath;
    private static Path stopwordsWrongPath;

    @BeforeClass
    public static void setUpBeforeAllTests() {
        try {
            stopwordsPath = Paths.get(StopwordFileLoaderTest.class
                    .getResource("testStopwords.txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        stopwordsWrongPath = Paths.get("testStopwordsWrong.txt");
    }

    @Test
    public void testLoadStopwords() {
        this.getClass().getResource("").getPath().toString();
        StopwordFileLoader stopwordFileLoader = new StopwordFileLoader(
                stopwordsPath);
        Set<Tag> loadedStopwords = stopwordFileLoader.loadStopwords();

        assertEquals(STOPWORDS, loadedStopwords);
    }

    @Test
    public void testLoadStopwordsWithWrongFile() {
        StopwordFileLoader stopwordFileLoader = new StopwordFileLoader(
                stopwordsWrongPath);
        Set<Tag> loadedStopwords = stopwordFileLoader.loadStopwords();

        assertTrue(loadedStopwords.isEmpty());
    }

}
