package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordFileLoader {

    private static final String STOPWORDS_LOCATION = "stopwords.txt";

    private final Path stopwordsPath;

    public StopwordFileLoader() {
        Path defaultStopwordsPath;
        try {
            defaultStopwordsPath = Paths.get(StopwordFileLoader.class
                    .getResource(STOPWORDS_LOCATION).toURI());
        } catch (URISyntaxException e) {
            defaultStopwordsPath = Paths.get(STOPWORDS_LOCATION);
        }

        stopwordsPath = defaultStopwordsPath;
    }

    public StopwordFileLoader(final Path stopwordsLocation) {
        stopwordsPath = stopwordsLocation;
    }

    public Set<Tag> loadStopwords() {
        try (BufferedReader reader = Files.newBufferedReader(stopwordsPath)) {
            return reader.lines().map(Tag::newTag).collect(Collectors.toSet());
        } catch (IOException e) {
            return new HashSet<>();
        }
    }
}
