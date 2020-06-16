package bg.sofia.uni.fmi.mjt.bookmarks;

import static bg.sofia.uni.fmi.mjt.bookmarks.input.commands.Command.QUIT_APPLICATION;

import java.util.Scanner;

public class ConsoleCommandScanner implements Runnable {
    private final Thread serverThread;

    public ConsoleCommandScanner(final Thread serverThread) {
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (QUIT_APPLICATION.getName().equals(scanner.next())) {
                    serverThread.interrupt();
                    break;
                }
            }
        }
    }
}
