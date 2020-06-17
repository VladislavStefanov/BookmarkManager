package bg.sofia.uni.fmi.mjt.bookmarks;

import static bg.sofia.uni.fmi.mjt.bookmarks.BookmarkManagerClient.CLOSED_BY_THE_REMOTE_HOST_EXCEPTION_MESSAGE;
import static bg.sofia.uni.fmi.mjt.bookmarks.BookmarkManagerClient.CONNECTION_ABORTED_BY_HOST_MACHINE_EXCEPTION;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.SocketChannel;

public class BookmarkManagerClientWriter implements Runnable {

    private static final String READING_EXCEPTION_HAS_OCCURRED = "A problem when reading from server has occured.";

    private static final int DEFAULT_BUFFER_CAPACITY = 1000;

    private final SocketChannel socketChannel;
    private final ByteBuffer buffer = ByteBuffer
            .allocate(DEFAULT_BUFFER_CAPACITY);

    public BookmarkManagerClientWriter(final SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {

        while (true) {
            try {
                buffer.clear();
                try {
                    socketChannel.read(buffer);
                    // TODO if server
                    // closes interrupt
                    // client thread (quit
                    // or terminate,
                    // scanner blocks)
                } catch (AsynchronousCloseException e) {
                    break;
                } catch (IOException e) {
                    if (e.getMessage().contains(
                            CONNECTION_ABORTED_BY_HOST_MACHINE_EXCEPTION)
                            || e.getMessage().contains(
                                    CLOSED_BY_THE_REMOTE_HOST_EXCEPTION_MESSAGE)) {
                        break;
                    }
                    throw e;
                }
                buffer.flip();
                String response = new String(buffer.array(), 0, buffer.limit());

                if (!response.isEmpty()) {
                    System.out.println(response);
                }
            } catch (IOException e) {
                System.out.println(READING_EXCEPTION_HAS_OCCURRED);
                e.printStackTrace();
            }
        }

    }
}
