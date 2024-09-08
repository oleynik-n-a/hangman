package backend.academy.stream_handlers;

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

    public int readInteger(PrintHandler printHandler, int min, int max) {
        int result;
        while (true) {
            try {
                result = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printHandler.updateView();
                continue;
            }
            if (result < min || result > max) {
                printHandler.updateView();
                continue;
            }
            return result;
        }
    }

    public char readLetter(PrintHandler printHandler) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.length() != 1) {
                printHandler.updateView();
                continue;
            }
            if (!Character.isLetter(input.charAt(0))) {
                printHandler.updateView();
                continue;
            }
            return Character.toUpperCase(input.charAt(0));
        }
    }
}
