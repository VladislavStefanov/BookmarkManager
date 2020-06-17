package bg.sofia.uni.fmi.mjt.bookmarks;

import bg.sofia.uni.fmi.mjt.bookmarks.web.BookmarkManagerServer;

public class BookmarkManagerApplication {
    public static void main(final String[] args) {
        Thread serverThread = new Thread(new BookmarkManagerServer());
        serverThread.start();
        Thread inputThread = new Thread(
                new ConsoleCommandScanner(serverThread));
        inputThread.setDaemon(true);
        inputThread.start();
    }
}
