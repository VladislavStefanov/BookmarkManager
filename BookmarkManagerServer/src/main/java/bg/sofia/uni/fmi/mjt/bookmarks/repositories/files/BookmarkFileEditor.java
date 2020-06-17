package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import java.nio.file.Paths;

import bg.sofia.uni.fmi.mjt.bookmarks.model.BookmarkCollection;

public class BookmarkFileEditor extends FileEditor<BookmarkCollection> {

    private static final String WILDCARD = "?";
    private static final String BOOKMARKS_LOCATION = "resources/bookmarks/"
            + WILDCARD + ".txt";

    public BookmarkFileEditor(final String username) {
        super(Paths.get(BOOKMARKS_LOCATION.replace(WILDCARD, username)),
                BookmarkCollection.class);
    }

}
