package bg.sofia.uni.fmi.mjt.bookmarks.repositories;

import java.util.Map;

import bg.sofia.uni.fmi.mjt.bookmarks.repositories.files.UserFileEditor;

public class UserRepository {
    private static UserRepository instance = null;

    private Map<String, String> passwordsByUsernames;
    private final UserFileEditor userFileEditor = UserFileEditor.getInstance();

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
            instance.loadUsers();
        }

        return instance;
    }

    private UserRepository() {

    }

    private void loadUsers() {
        passwordsByUsernames = userFileEditor.load();
    }

    public void persistUsers() {
        userFileEditor.persist(passwordsByUsernames);
    }

    public boolean isRegistered(final String username, final String password) {
        return password.equals(passwordsByUsernames.get(username));
    }

    public boolean register(final String username, final String password) {
        if (!passwordsByUsernames.containsKey(username)) {
            passwordsByUsernames.put(username, password);
            return true;
        }

        return false;
    }

}
