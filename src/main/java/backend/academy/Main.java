package backend.academy;

import backend.academy.stages.difficulty.GameDifficultyOption;
import backend.academy.stages.game.GameStepOption;
import backend.academy.stages.category.WordCategoryOption;
import backend.academy.stages.category.WordCategoryStage;
import backend.academy.stages.difficulty.CustomDifficultyStage;
import backend.academy.stages.difficulty.GameDifficultyStage;
import backend.academy.stages.game.GameSession;
import backend.academy.stages.game.GameSessionResults;
import backend.academy.stream_handlers.InputHandler;
import backend.academy.stream_handlers.PrintHandler;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        // Initializing Singletons (singleton pattern) - output & input stream handlers.
        PrintHandler printHandler = PrintHandler.getInstance(System.out);
        InputHandler inputHandler = InputHandler.getInstance(System.in);

        // Initializing Builder class for future game session (builder pattern).
        final var gameSessionBuilder = GameSession.builder();

        int input;

        // Game cycle to make possible restart the game.
        while (true) {
            // Stage to choose word category.
            WordCategoryStage wordCategoryStage = new WordCategoryStage();
            printHandler.printView(wordCategoryStage);
            input = inputHandler.readInteger(printHandler, 1, WordCategoryOption.values().length);
            wordCategoryStage.submitCategory(input);

            // Building game session after choosing stage.
            gameSessionBuilder.wordInfo(wordCategoryStage.generateWord(WordCategoryStage.DICTIONARY));

            // Stage to choose game difficulty (lives amount).
            GameDifficultyStage gameDifficultyStage = new GameDifficultyStage();
            printHandler.printView(gameDifficultyStage);
            input = inputHandler.readInteger(printHandler, 1, GameDifficultyOption.values().length);
            gameDifficultyStage.submitDifficulty(input);

            // Building game session after choosing stage.
            gameSessionBuilder.difficulty(gameDifficultyStage.gameDifficulty());

            // Stage to choose game custom difficulty (custom lives amount).
            if (gameDifficultyStage.gameDifficulty() == GameDifficultyOption.CUSTOM) {
                gameDifficultyStage = new CustomDifficultyStage();
                printHandler.printView(gameDifficultyStage);
                input = inputHandler.readInteger(printHandler, 1, GameDifficultyStage.MAX_LIVE_AMOUNT);
                gameDifficultyStage.submitDifficulty(input);
            }

            // Building game session after choosing stage.
            gameSessionBuilder.lives(gameDifficultyStage.getLivesAmount());

            // Game session building done, starting game.
            GameSession gameSession = gameSessionBuilder.build();
            printHandler.printView(gameSession);
            GameStepOption gameStep = GameStepOption.GAMING_STEP;
            while (gameStep != GameStepOption.RESULTING_STEP) {
                char inputCharacter = inputHandler.readLetter(printHandler);
                gameStep = gameSession.executeGameStep(inputCharacter);
                printHandler.printView(gameSession);
            }

            // End of the game.
            GameSessionResults gameSessionResults = new GameSessionResults(gameSession.getGameResults());
            printHandler.printView(gameSessionResults);
            if (inputHandler.readLetter(printHandler) != 'Y') {
                return;
            }
        }
    }
}
