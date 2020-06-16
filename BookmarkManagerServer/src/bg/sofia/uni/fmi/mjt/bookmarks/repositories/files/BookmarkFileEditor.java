package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import bg.sofia.uni.fmi.mjt.bookmarks.model.BookmarkCollection;

public class BookmarkFileEditor extends FileEditor<BookmarkCollection> {

    private static final String BOOKMARKS_LOCATION = "resources/bookmarks/?.txt";
    private static final String WILDCARD = "?";

    public BookmarkFileEditor(final String username) {
        super(BOOKMARKS_LOCATION.replace(WILDCARD, username),
                BookmarkCollection.class);
    }

}
