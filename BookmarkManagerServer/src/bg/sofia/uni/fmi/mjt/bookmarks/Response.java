package bg.sofia.uni.fmi.mjt.bookmarks;

public enum Response {
    COLLECTION_CREATED("Collection created."),
    COLLECTION_ALREADY_EXISTS("Collection with such name already exists."),
    COLLECTION_NOT_FOUND("Collection with such name does not exist."),
    URL_ADDED("URL added to collection."),
    URL_REMOVED("URL was removed from the collection."),
    URL_NOT_FOUND("The specified URL was not found."),
    URL_ALREADY_ADDED("This URL is already added to this collection."),
    NOT_LOGGED_IN("You need to be logged in to execute this command."),
    LOGGED_IN("Successfully logged in."),
    ALREADY_LOGGED_IN("Already logged in."),
    INVALID_CREDENTIALS("Wrong credentials."),
    REGISTERED("Successfully registered."),
    USER_WITH_USERNAME_ALREADY_EXISTS(
            "An user with such username already exists."),
    NO_BOOKMARKS("No bookmarks found."),
    IMPORTED_FROM_CHROME("Bookmarks successfully imported from chrome."),
    CLOSED("Closed."),

    SELECTOR_UNABLE_TO_SELECT_KEYS("Selector unable to select keys."),
    CHANNEL_IS_CLOSED("Channel is closed."),
    UNABLE_TO_INITIALIZE_APPLICATION("Unable to initialize application."),
    IO_EXCEPTION_HAS_OCCURRED("IOException has occurred."),
    READING_EXCEPTION_HAS_OCCURRED("An exception when reading has occurred."),
    WRITING_EXCEPTION_HAS_OCCURRED("An exception when writing has occurred."),
    WRONG_COMMAND("Such command does not exist."),
    WRONG_ARGUMENTS("Command with such arguments does not exist."),
    SAVING_PROBLEM("IOException has occurred when saving entity."),
    WRONG_FILE_LOCATION("Wrong file location."),
    CLOSING_CHANNEL_PROBLEM("An IOException occured when closing a channel.");

    private String message;

    Response(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
