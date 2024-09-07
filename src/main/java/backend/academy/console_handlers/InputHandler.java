package backend.academy.console_handlers;

import java.io.InputStream;
import java.util.Scanner;

public final class InputHandler {
    private static final InputHandler INSTANCE = new InputHandler();
    private static Scanner SCANNER;

    private InputHandler() {
    }

    public static InputHandler getInstance(InputStream inputStream) {
        SCANNER = new Scanner(inputStream);
        return InputHandler.INSTANCE;
    }

    public int readInteger() {
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    public char readLetter() {
        String input;
        while (true) {
            input = SCANNER.nextLine();
            if (input.length() != 1) {
                continue;
            }
            if (!Character.isLetter(input.charAt(0))) {
                continue;
            }
            return Character.toUpperCase(input.charAt(0));
        }
    }
}
