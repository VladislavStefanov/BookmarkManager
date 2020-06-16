package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

public class UserFileEditor extends FileEditor<String> {

    private static final String USERS_LOCATION = "resources/users.txt";

    private static UserFileEditor instance = null;

    public static UserFileEditor getInstance() {
        if (instance == null) {
            instance = new UserFileEditor();
        }

        return instance;
    }

    private UserFileEditor() {
        super(USERS_LOCATION, String.class);
    }

}
