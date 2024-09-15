package backend.academy.stream_handlers;

import backend.academy.adapters.Printable;
import java.io.PrintStream;

public final class PrintHandler {
    private static final PrintHandler INSTANCE = new PrintHandler();
    private static PrintStream out;
    private String lastPrinted = "";

    private PrintHandler() {
    }

    public static PrintHandler getInstance(PrintStream printStream) {
        out = printStream;
        return PrintHandler.INSTANCE;
    }

    public void updateView() {
        out.print(lastPrinted);
    }

    public <T extends Printable> void printView(T printable) {
        lastPrinted = printable.getPrintableView();
        updateView();
    }
}
