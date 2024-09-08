package backend.academy.stream_handlers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {
    private static InputHandler inputHandler;
    private static PrintHandler printHandler;
    private static InputStream sysInBackup;

    @BeforeAll
    static void setUp() {
        sysInBackup = System.in;
        printHandler = PrintHandler.getInstance(System.out);
    }

    @AfterAll
    static void tearDown() {
        System.setIn(sysInBackup);
    }

    @Test
    void testReadInteger() {
        // TestInteger
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("50".getBytes()));
        assertEquals(50, inputHandler.readInteger(printHandler, 1, 100));

        // TestSomeString
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("Test\n50".getBytes()));
        assertEquals(50, inputHandler.readInteger(printHandler, 1, 100));

        // TestEmptyString
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("\n5".getBytes()));
        assertEquals(5, inputHandler.readInteger(printHandler, 1, 100));

        // TestUpperBound
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("50\n25".getBytes()));
        assertEquals(25, inputHandler.readInteger(printHandler, 1, 49));

        // TestLowerBound
        inputHandler = InputHandler.getInstance(new ByteArrayInputStream("5\n25".getBytes()));
        assertEquals(25, inputHandler.readInteger(printHandler, 10, 50));
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
