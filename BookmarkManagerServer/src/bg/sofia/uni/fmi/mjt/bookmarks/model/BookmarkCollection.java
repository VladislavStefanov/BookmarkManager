package bg.sofia.uni.fmi.mjt.bookmarks.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class BookmarkCollection {
    private static final String DELIMITER = "\n";

    private final Set<Bookmark> bookmarks = new LinkedHashSet<>();

    public BookmarkCollection() {

    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public boolean add(final Bookmark bookmark) {
        return bookmarks.add(bookmark);
    }

    public void addAllFromCollection(
            final BookmarkCollection bookmarkCollection) {
        bookmarks.addAll(bookmarkCollection.getBookmarks());
    }

    public void addAll(final Set<Bookmark> bookmarks) {
        this.bookmarks.addAll(bookmarks);
    }

    public boolean remove(final Bookmark bookmark) {
        return bookmarks.remove(bookmark);
    }

    public boolean isEmpty() {
        return bookmarks.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder urlsStringBuilder = new StringBuilder();

        bookmarks.forEach(bookmark -> urlsStringBuilder
                .append(bookmark.toString()).append(DELIMITER));

        return urlsStringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookmarks);
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
        BookmarkCollection other = (BookmarkCollection) obj;
        return Objects.equals(bookmarks, other.bookmarks);
    }
}
