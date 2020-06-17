package bg.sofia.uni.fmi.mjt.bookmarks;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class ChromeBookmarkReader {

    private static final String URL_FIELD = "\"url\"";

    private static final String OPERATION_SYSTEM = System.getProperty("os.name")
            .toLowerCase();

    private static final String BOOKMARKS_FILE_NAME = "Bookmarks";

    private static final Path WINDOWS_BOOKMARKS_PATH = Paths.get(
            System.getProperty("user.home"),
            "AppData\\Local\\Google\\Chrome\\User Data\\Default",
            BOOKMARKS_FILE_NAME);
    private static final Path UNIX_BOOKMARKS_PATH = Paths
            .get("~/.config/google-chrome/Default/", BOOKMARKS_FILE_NAME);
    private static final Path MAC_BOOKMARKS_PATH = Paths.get(
            System.getProperty("user.home"), "Library/Application",
            "Support/Google/Chrome", BOOKMARKS_FILE_NAME);

    private final Path bookmarksPath;

    public ChromeBookmarkReader() throws UnsupportedOperatingSystemException {
        if (isWindows()) {
            bookmarksPath = WINDOWS_BOOKMARKS_PATH;
        } else if (isUnix()) {
            bookmarksPath = UNIX_BOOKMARKS_PATH;
        } else if (isMac()) {
            bookmarksPath = MAC_BOOKMARKS_PATH;
        } else {
            throw new UnsupportedOperatingSystemException();
        }
    }

    public String read() throws IOException {
        StringBuilder urls = new StringBuilder();
        BufferedReader reader = Files.newBufferedReader(bookmarksPath);
        reader.lines().map(String::trim)
                .filter(line -> line.startsWith(URL_FIELD)).map(line -> {
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    tokenizer.nextElement();
                    return tokenizer.nextToken();
                }).map(url -> url.substring(1, url.length() - 1))
                .forEach(url -> urls.append(url).append(" "));

        return urls.toString();
    }

    public static boolean isWindows() {
        return (OPERATION_SYSTEM.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OPERATION_SYSTEM.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OPERATION_SYSTEM.indexOf("nix") >= 0
                || OPERATION_SYSTEM.indexOf("nux") >= 0
                || OPERATION_SYSTEM.indexOf("aix") > 0);
    }

    public static void main(final String[] args)
            throws IOException, UnsupportedOperatingSystemException {
        System.out.println(new ChromeBookmarkReader().read());
    }
}
