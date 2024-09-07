package backend.academy.console_handlers;

import java.util.Scanner;

public final class InputHandler {
    private static final InputHandler INSTANCE = new InputHandler();
    private final Scanner scanner;

    private InputHandler() {
        scanner = new Scanner(System.in);
    }

    public static InputHandler getInstance() {
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
