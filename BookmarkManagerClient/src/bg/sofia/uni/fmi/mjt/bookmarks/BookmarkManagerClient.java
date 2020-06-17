package bg.sofia.uni.fmi.mjt.bookmarks;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BookmarkManagerClient implements Runnable {
    public static final String CONNECTION_ABORTED_BY_HOST_MACHINE_EXCEPTION = "An established connection was aborted by the software in your host machine";
    public static final String CLOSED_BY_THE_REMOTE_HOST_EXCEPTION_MESSAGE = "closed by the remote host";

    private static final List<String> HELP_CONTENT = Arrays.asList(
            "register <username> <password>", "login <username> <password>",
            "make-collection <collection-name>",
            "add <link> - add bookmark to the default collection",
            "add-to <collection> <link>", "remove-from <collection> <link>",
            "list-all - prints all bookmarks",
            "list <collection> - prints all bookmarks in the collection",
            "search -tags <tag1> <tag2> <tag3> ... search bookmarks with common words",
            "search -title <title> - search bookmarks with title equal to <title>",
            "import-from-chrome - adds all bookmarks from Google Chrome",
            "close - closes the application");
    private static final String HELP_COMMAND = "help";

    private static final String CLOSE_THE_APPLICATION_WITH_THE_CLOSE_COMMAND = "Close the application only with the \"close\" command";
    private static final String TYPE_HELP_TO_SEE_ALL_COMMANDS = "Type \"help\" to see all commands";

    private static final String IMPORT_FROM_CHROME_COMMAND = "import-from-chrome";
    private static final String CLOSE_COMMAND = "close";

    private static final String WRONG_ARGUMENTS = "This command does not take arguments";
    private static final String COULD_NOT_IMPORT_CHROME_BOOKMARKS = "Could not import chrome bookmarks";

    private static final String WRITING_EXCEPTION_HAS_OCCURRED = "A problem when writing to server has occurred.";
    private static final String UNABALE_TO_INITIALIZE_CLIENT = "Unabale to initialize client.";

    private static final int HOST_PORT = 9745;
    private static final String HOST_NAME = "localhost";

    private final ChromeBookmarkReader chromeBookmarkReader;

    public BookmarkManagerClient() throws UnsupportedOperatingSystemException {
        chromeBookmarkReader = new ChromeBookmarkReader();
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in);
                SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress(HOST_NAME, HOST_PORT));

            Thread writerThread = new Thread(
                    new BookmarkManagerClientWriter(socketChannel));
            writerThread.setDaemon(true);
            writerThread.start();

            System.out.println(TYPE_HELP_TO_SEE_ALL_COMMANDS);
            System.out.println(CLOSE_THE_APPLICATION_WITH_THE_CLOSE_COMMAND);

            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }
                StringTokenizer tokenizer = new StringTokenizer(input);
                String command = tokenizer.nextToken();

                if (HELP_COMMAND.equalsIgnoreCase(command)) {
                    if (!tokenizer.hasMoreTokens()) {
                        HELP_CONTENT.forEach(System.out::println);
                        continue;
                    } else {
                        System.out.println(WRONG_ARGUMENTS);
                    }
                }

                if (IMPORT_FROM_CHROME_COMMAND.equalsIgnoreCase(command)) {
                    if (!tokenizer.hasMoreTokens()) {
                        try {
                            input += " " + chromeBookmarkReader.read();
                        } catch (IOException e) {
                            System.out
                                    .println(COULD_NOT_IMPORT_CHROME_BOOKMARKS);
                            continue;
                        }
                    } else {
                        System.out.println(WRONG_ARGUMENTS);
                    }
                }
                try {
                    writeToServer(socketChannel, input);
                } catch (ClosedChannelException e) {
                    break;
                }

                if (CLOSE_COMMAND.equalsIgnoreCase(input)) {
                    break;
                }

            }
        } catch (IOException e) {
            System.out.println(UNABALE_TO_INITIALIZE_CLIENT);
        }
    }

    private void writeToServer(final SocketChannel socketChannel,
            final String input) throws ClosedChannelException {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());
            socketChannel.write(buffer);
        } catch (IOException e) {
            if (e.getMessage()
                    .contains(CONNECTION_ABORTED_BY_HOST_MACHINE_EXCEPTION)
                    || e.getMessage().contains(
                            CLOSED_BY_THE_REMOTE_HOST_EXCEPTION_MESSAGE)) {
                throw new ClosedChannelException();
            }
            System.out.println(WRITING_EXCEPTION_HAS_OCCURRED);
        }
    }

    public static void main(final String[] args) {
        try {
            new Thread(new BookmarkManagerClient()).start();
        } catch (UnsupportedOperatingSystemException e) {
            System.out.println(e.getMessage());
        }
    }
}
