package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordFileLoaderTest {

    private static final Set<Tag> STOPWORDS = new HashSet<>(
            Arrays.asList(Tag.newTag("when"), Tag.newTag("then")));

    private static final String STOPWORDS_PATH = "test/bg/sofia/uni/fmi/mjt/bookmarks/repositories/files/testStopwords.txt";
    private static final String STOPWORDS_WRONG_PATH = "test/bg/sofia/uni/fmi/mjt/bookmarks/repositories/files/testStopwordsWrong.txt";

    @Test
    public void testLoadStopwords() {
        StopwordFileLoader stopwordFileLoader = new StopwordFileLoader(
                STOPWORDS_PATH);
        Set<Tag> loadedStopwords = stopwordFileLoader.loadStopwords();

        assertEquals(STOPWORDS, loadedStopwords);
    }

    @Test
    public void testLoadStopwordsWithWrongFile() {
        StopwordFileLoader stopwordFileLoader = new StopwordFileLoader(
                STOPWORDS_WRONG_PATH);
        Set<Tag> loadedStopwords = stopwordFileLoader.loadStopwords();

        assertTrue(loadedStopwords.isEmpty());
    }

}
