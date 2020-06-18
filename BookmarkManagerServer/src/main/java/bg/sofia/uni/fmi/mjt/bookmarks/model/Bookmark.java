package bg.sofia.uni.fmi.mjt.bookmarks.model;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Bookmark {

    private static final int TIMEOUT = 20000;
    private final String url;

    public String getUrl() {
        return url;
    }

    public Bookmark(final String url) {
        this.url = url;
    }

    public Bookmark() {
        url = null;
    }

    public boolean containsTitle(final String title) {
        try {
            Document htmlDocument = Jsoup.parse(new URL(url), TIMEOUT);
            String linkTitle = htmlDocument.title().trim();
            return linkTitle.toLowerCase().contains(title.toLowerCase());
        } catch (IOException e) {
            return false;
        }
    }

    public boolean containsTags(final Set<Tag> tags) {

        try {
            Document htmlDocument = Jsoup.parse(new URL(url), TIMEOUT);
            String text = htmlDocument.text();
            StringTokenizer tokenizer = new StringTokenizer(text);

            Set<Tag> tagsCopy = new HashSet<>(tags);
            while (tokenizer.hasMoreTokens() && !tagsCopy.isEmpty()) {
                tagsCopy.remove(Tag.newTag(tokenizer.nextToken()));
            }

            return tagsCopy.isEmpty();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Bookmark other = (Bookmark) obj;
        return Objects.equals(url, other.url);
    }

    @Override
    public String toString() {
        return url;
    }

}
