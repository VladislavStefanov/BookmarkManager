package bg.sofia.uni.fmi.mjt.bookmarks.input.commands;

import java.util.Arrays;

import bg.sofia.uni.fmi.mjt.bookmarks.api.BookmarkManagerFacade;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.AddCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.AddToCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.CloseCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.CommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.ImportFromChromeCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.ListAllCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.ListCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.LoginCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.MakeCollectionCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.QuitApplicationCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.RegisterCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.RemoveFromCommandExecutor;
import bg.sofia.uni.fmi.mjt.bookmarks.input.commands.executors.SearchCommandExecutor;

public enum Command {
    REGISTER("register", new RegisterCommandExecutor()),
    LOGIN("login", new LoginCommandExecutor()),
    MAKE_COLLECTION("make-collection", new MakeCollectionCommandExecutor()),
    ADD("add", new AddCommandExecutor()),
    ADD_TO("add-to", new AddToCommandExecutor()),
    REMOVE_FROM("remove-from", new RemoveFromCommandExecutor()),
    LIST_ALL("list-all", new ListAllCommandExecutor()),
    LIST("list", new ListCommandExecutor()),
    SEARCH("search", new SearchCommandExecutor()),
    IMPORT_FROM_CHROME("import-from-chrome",
            new ImportFromChromeCommandExecutor()),
    CLOSE("close", new CloseCommandExecutor()),
    QUIT_APPLICATION("quit", new QuitApplicationCommandExecutor());

    private String name;
    private CommandExecutor commandExecutor;

    Command(final String name, final CommandExecutor commandExecutor) {
        this.name = name;
        this.commandExecutor = commandExecutor;
    }

    public String getName() {
        return name;
    }

    public String execute(final BookmarkManagerFacade bookmarkManager,
            final String argumentsString) {
        return commandExecutor.execute(bookmarkManager, argumentsString);
    }

    public static Command fromString(final String commandString) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(command -> command.getName()
                        .equalsIgnoreCase(commandString))
                .findFirst()
                .orElse(null);
        // @formatter:on
    }
}
