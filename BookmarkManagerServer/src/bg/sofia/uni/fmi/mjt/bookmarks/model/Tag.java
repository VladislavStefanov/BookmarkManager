package bg.sofia.uni.fmi.mjt.bookmarks.model;

import java.util.Objects;

import opennlp.tools.stemmer.snowball.SnowballStemmer;
import opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM;

public class Tag {

    private static final String PUNCTUATION_REGEX = "[^a-zA-z]+";
    private String word;

    public static Tag newTag(final String word) {
        Tag tag = new Tag(word);
        tag.cleanUp();
        tag.stem();
        return tag;
    }

    private Tag(final String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }

    private void cleanUp() {
        word = word.toLowerCase().replaceAll(PUNCTUATION_REGEX, new String());
    }

    private void stem() {
        SnowballStemmer stemmer = new SnowballStemmer(ALGORITHM.ENGLISH);
        word = stemmer.stem(word).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
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
        Tag other = (Tag) obj;
        return Objects.equals(word, other.word);
    }
}
