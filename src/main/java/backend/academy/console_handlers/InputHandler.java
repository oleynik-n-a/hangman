package backend.academy.console_handlers;

import java.io.InputStream;
import java.util.Scanner;

public final class InputHandler {
    private static final InputHandler INSTANCE = new InputHandler();
    private static Scanner scanner;

    private InputHandler() {
    }

    public static InputHandler getInstance(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        return InputHandler.INSTANCE;
    }

    public int readInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    public char readLetter() {
        String input;
        while (true) {
            input = scanner.nextLine();
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
