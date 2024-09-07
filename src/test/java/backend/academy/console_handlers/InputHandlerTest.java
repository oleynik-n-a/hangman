package backend.academy.console_handlers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {
    private static InputHandler inputHandler;
    private static PrintHandler printHandler;
    private static InputStream sysInBackup;

    @BeforeAll
    static void setUp() {
        sysInBackup = System.in;
    }

    @AfterAll
    static void tearDown() {
        System.setIn(sysInBackup);
    }

    @Test
    void testReadInteger() {
        // TestInteger
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("50".getBytes()));
        assertEquals(50, inputHandler.readInteger(printHandler));

        // TestSomeString
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("Test\n50".getBytes()));
        assertEquals(50, inputHandler.readInteger(printHandler));

        // TestEmptyString
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("\n5".getBytes()));
        assertEquals(5, inputHandler.readInteger(printHandler));
    }

    @Test
    void testReadLetter() {
        // TestBigLetter
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("A".getBytes()));
        assertEquals('A', inputHandler.readLetter(printHandler));

        // TestSmallLetter
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("a".getBytes()));
        assertEquals('A', inputHandler.readLetter(printHandler));

        // TestRandomSymbol
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("5\nA".getBytes()));
        assertEquals('A', inputHandler.readLetter(printHandler));

        // TestSomeString
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("Test\nA".getBytes()));
        assertEquals('A', inputHandler.readLetter(printHandler));

        // TestEmptyString
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("\nA".getBytes()));
        assertEquals('A', inputHandler.readLetter(printHandler));
    }
}
