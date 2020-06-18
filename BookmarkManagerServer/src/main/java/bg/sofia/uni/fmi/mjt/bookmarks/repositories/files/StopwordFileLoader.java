package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordFileLoader {

    private static final String STOPWORDS_LOCATION = "stopwords.txt";
    private final InputStream inputStream;

    public StopwordFileLoader() {
        this(StopwordFileLoader.class.getResourceAsStream(STOPWORDS_LOCATION));
    }

    public StopwordFileLoader(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Set<Tag> loadStopwords() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream))) {
            return reader.lines().map(Tag::newTag).collect(Collectors.toSet());
        } catch (IOException | NullPointerException e1) {
            return new HashSet<>();
        }
    }
}
