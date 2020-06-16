package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import bg.sofia.uni.fmi.mjt.bookmarks.model.Tag;

public class StopwordFileLoader {

    private static final String STOPWORDS_LOCATION = "resources/stopwords.txt";

    private final Path stopwordsPath;

    public StopwordFileLoader() {
        this(STOPWORDS_LOCATION);
    }

    public StopwordFileLoader(final String stopwordsLocation) {
        stopwordsPath = Paths.get(stopwordsLocation);
    }

    public Set<Tag> loadStopwords() {
        try (BufferedReader reader = Files.newBufferedReader(stopwordsPath)) {
            return reader.lines().map(Tag::newTag).collect(Collectors.toSet());
        } catch (IOException e) {
            return new HashSet<>();
        }
    }
}
