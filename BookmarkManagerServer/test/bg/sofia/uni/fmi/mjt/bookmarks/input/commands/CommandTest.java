package bg.sofia.uni.fmi.mjt.bookmarks.input.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandTest {

    @Test
    public void testFromString() {
        Command command = Command.fromString(Command.ADD_TO.getName());
        assertEquals(Command.ADD_TO, command);
    }

}
