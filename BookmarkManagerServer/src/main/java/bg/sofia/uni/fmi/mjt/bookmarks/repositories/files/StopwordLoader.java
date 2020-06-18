package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordLoader {

    private static final String STOPWORDS_LOCATION = "stopwords.txt";
    private final InputStream inputStream;

    public StopwordLoader() {
        this(StopwordLoader.class.getResourceAsStream(STOPWORDS_LOCATION));
    }

    public StopwordLoader(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Set<Tag> loadStopwords() {
        if (inputStream == null) {
            return new HashSet<>();
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream))) {
            return reader.lines().map(Tag::newTag).collect(Collectors.toSet());
        } catch (IOException e) {
            return new HashSet<>();
        }
    }
}
