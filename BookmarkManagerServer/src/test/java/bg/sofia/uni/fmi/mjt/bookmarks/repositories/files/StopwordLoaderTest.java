package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordLoaderTest {

    private static final Set<Tag> STOPWORDS = new HashSet<>(
            Arrays.asList(Tag.newTag("when"), Tag.newTag("then")));

    @Test
    public void testLoadStopwords() {
        InputStream inputStream = StopwordLoaderTest.class
                .getResourceAsStream("testStopwords.txt");
        StopwordLoader stopwordLoader = new StopwordLoader(
                inputStream);
        Set<Tag> loadedStopwords = stopwordLoader.loadStopwords();

        assertEquals(STOPWORDS, loadedStopwords);
    }

    @Test
    public void testLoadStopwordsWithWrongFile() {
        StopwordLoader stopwordLoader = new StopwordLoader(null);
        Set<Tag> loadedStopwords = stopwordLoader.loadStopwords();

        assertTrue(loadedStopwords.isEmpty());
    }

}
