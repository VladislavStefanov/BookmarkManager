package bg.sofia.uni.fmi.mjt.bookmarks.web;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.CHANNEL_IS_CLOSED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.CLOSED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.CLOSING_CHANNEL_PROBLEM;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.IO_EXCEPTION_HAS_OCCURRED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.READING_EXCEPTION_HAS_OCCURRED;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.SELECTOR_UNABLE_TO_SELECT_KEYS;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.UNABLE_TO_INITIALIZE_APPLICATION;
import static bg.sofia.uni.fmi.mjt.bookmarks.Response.WRITING_EXCEPTION_HAS_OCCURRED;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.commands.Command.CLOSE;
import static bg.sofia.uni.fmi.mjt.bookmarks.input.commands.Command.QUIT_APPLICATION;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import bg.sofia.uni.fmi.mjt.bookmarks.input.InputHandler;

public class BookmarkManagerServer implements Runnable {
    private static final String CLOSED_BY_THE_REMOTE_HOST_EXCEPTION_MESSAGE = "closed by the remote host";
    private static final String INITIAL_SERVER_MESSAGE = "Close the application only with the \"quit\" command.";
    private static final int HOST_PORT = 9745;
    private static final String HOST_NAME = "localhost";
    private static final int DEFAULT_BUFFER_CAPACITY = 1000;

    private final ByteBuffer buffer = ByteBuffer
            .allocate(DEFAULT_BUFFER_CAPACITY);
    private final Map<SocketChannel, InputHandler> inputHandlersByChannels = new HashMap<>();

    public void run() {
        try (Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel
                        .open()) {
            serverSocketChannel
                    .bind(new InetSocketAddress(HOST_NAME, HOST_PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println(INITIAL_SERVER_MESSAGE);

            while (!Thread.interrupted()) {
                try {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) {
                        continue;
                    }

                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectedKeys
                            .iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();

                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = registerChannel(
                                    selector, serverSocketChannel);
                            if (socketChannel != null) {
                                inputHandlersByChannels.put(socketChannel,
                                        new InputHandler());
                            }
                        } else if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key
                                    .channel();
                            try {
                                String message = readMessage(socketChannel);
                                String responseMessage = inputHandlersByChannels
                                        .get(socketChannel).process(message);
                                if (!CLOSED.getMessage()
                                        .equals(responseMessage)) {
                                    writeResponse(socketChannel,
                                            responseMessage);
                                } else {
                                    socketChannel.close();
                                    inputHandlersByChannels
                                            .remove(socketChannel);
                                }
                            } catch (ClosedChannelException e) {
                                socketChannel.close();
                                inputHandlersByChannels.remove(socketChannel)
                                        .process(CLOSE.getName());
                            }
                        }

                        keyIterator.remove();
                    }
                } catch (IOException e) {
                    System.out.println(
                            SELECTOR_UNABLE_TO_SELECT_KEYS.getMessage());
                    e.printStackTrace();
                }
            }

        } catch (ClosedChannelException e) {
            System.out.println(UNABLE_TO_INITIALIZE_APPLICATION.getMessage());
            System.out.println(CHANNEL_IS_CLOSED.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(UNABLE_TO_INITIALIZE_APPLICATION.getMessage());
            e.printStackTrace();
        }

        close();

    }

    private SocketChannel registerChannel(final Selector selector,
            final ServerSocketChannel serverSocketChannel) {
        SocketChannel clientSocketChannel = null;
        try {
            clientSocketChannel = serverSocketChannel.accept();
            clientSocketChannel.configureBlocking(false);
            clientSocketChannel.register(selector,
                    SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        } catch (ClosedChannelException e) {
            System.out.println(CHANNEL_IS_CLOSED.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION_HAS_OCCURRED.getMessage());
            e.printStackTrace();
        }

        return clientSocketChannel;
    }

    private String readMessage(final SocketChannel socketChannel)
            throws ClosedChannelException {
        StringBuilder messageBuilder = new StringBuilder();
        buffer.clear();
        try {
            while (socketChannel.read(buffer) > 0) {
                buffer.flip();
                messageBuilder
                        .append(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        } catch (IOException e) {
            if (e.getMessage()
                    .contains(CLOSED_BY_THE_REMOTE_HOST_EXCEPTION_MESSAGE)) {
                throw new ClosedChannelException();
            }
            System.out.println(READING_EXCEPTION_HAS_OCCURRED.getMessage());
            e.printStackTrace();
        }

        return messageBuilder.toString();
    }

    private void writeResponse(final SocketChannel socketChannel,
            final String message) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            socketChannel.write(buffer);
        } catch (IOException e) {
            System.out.println(WRITING_EXCEPTION_HAS_OCCURRED.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
        inputHandlersByChannels.keySet().stream().forEach(channel -> {
            try {
                channel.close();
            } catch (IOException e) {
                System.out.println(CLOSING_CHANNEL_PROBLEM.getMessage());
            }
            inputHandlersByChannels.get(channel).process(CLOSE.getName());
        });
        new InputHandler().process(QUIT_APPLICATION.getName());
    }
}
