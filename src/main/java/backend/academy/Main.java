package backend.academy;

import backend.academy.stages.StagesRunner;
import backend.academy.stream_handlers.InputHandler;
import backend.academy.stream_handlers.PrintHandler;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        // Initializing Singletons (singleton pattern) - output & input stream handlers.
        PrintHandler printHandler = PrintHandler.getInstance(System.out);
        InputHandler inputHandler = InputHandler.getInstance(System.in);

        final var stagesRunner = new StagesRunner(printHandler, inputHandler);

        // Game cycle to make possible restart the game.
        while (true) {
            // Stage to choose word category.
            stagesRunner.runCategoryStages();

            // Stage to choose game difficulty (lives amount).
            stagesRunner.runDifficultyStages();

            // Game session building done, starting game.
            stagesRunner.runGameStages();

            // End of the game.
            if (inputHandler.readLetter(printHandler) != 'Y') {
                return;
            }
        }
    }
}
