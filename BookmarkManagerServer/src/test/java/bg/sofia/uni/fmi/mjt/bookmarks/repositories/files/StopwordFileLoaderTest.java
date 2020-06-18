package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordFileLoaderTest {

    private static final Set<Tag> STOPWORDS = new HashSet<>(
            Arrays.asList(Tag.newTag("when"), Tag.newTag("then")));

    @Test
    public void testLoadStopwords() {
        InputStream inputStream = StopwordFileLoaderTest.class
                .getResourceAsStream("testStopwords.txt");
        StopwordFileLoader stopwordFileLoader = new StopwordFileLoader(
                inputStream);
        Set<Tag> loadedStopwords = stopwordFileLoader.loadStopwords();

        assertEquals(STOPWORDS, loadedStopwords);
    }

    @Test
    public void testLoadStopwordsWithWrongFile() {
        StopwordFileLoader stopwordFileLoader = new StopwordFileLoader(null);
        Set<Tag> loadedStopwords = stopwordFileLoader.loadStopwords();

        assertTrue(loadedStopwords.isEmpty());
    }

}
