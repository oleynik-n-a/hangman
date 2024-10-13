package backend.academy.stages;

import backend.academy.stages.category.WordCategoryOption;
import backend.academy.stages.category.WordCategoryStage;
import backend.academy.stages.difficulty.CustomDifficultyStage;
import backend.academy.stages.difficulty.GameDifficultyOption;
import backend.academy.stages.difficulty.GameDifficultyStage;
import backend.academy.stages.game.GameSession;
import backend.academy.stages.game.GameSessionResults;
import backend.academy.stages.game.GameStepOption;
import backend.academy.stream_handlers.InputHandler;
import backend.academy.stream_handlers.PrintHandler;

public class StagesRunner {
    public StagesRunner(PrintHandler printHandler, InputHandler inputHandler) {
        this.printHandler = printHandler;
        this.inputHandler = inputHandler;
    }

    public void runCategoryStages() {
        WordCategoryStage wordCategoryStage = new WordCategoryStage();
        printHandler.printView(wordCategoryStage);
        int input = inputHandler.readInteger(printHandler, 1, WordCategoryOption.values().length);
        wordCategoryStage.submitCategory(input);

        // Building game session after choosing stage.
        gameSessionBuilder.wordInfo(wordCategoryStage.generateWord(WordCategoryStage.DICTIONARY));
    }

    public void runDifficultyStages() {
        GameDifficultyStage gameDifficultyStage = new GameDifficultyStage();
        printHandler.printView(gameDifficultyStage);
        int input = inputHandler.readInteger(printHandler, 1, GameDifficultyOption.values().length);
        gameDifficultyStage.submitDifficulty(input);

        // Building game session after choosing stage.
        gameSessionBuilder.difficulty(gameDifficultyStage.gameDifficulty());

        if (gameDifficultyStage.gameDifficulty() == GameDifficultyOption.CUSTOM) {
            gameDifficultyStage = new CustomDifficultyStage();
            printHandler.printView(gameDifficultyStage);
            input = inputHandler.readInteger(printHandler, 1, GameDifficultyStage.MAX_LIVE_AMOUNT);
            gameDifficultyStage.submitDifficulty(input);
        }

        // Building game session after choosing stage.
        gameSessionBuilder.lives(gameDifficultyStage.getLivesAmount());
    }

    public void runGameStages() {
        GameSession gameSession = gameSessionBuilder.build();
        printHandler.printView(gameSession);
        GameStepOption gameStep = GameStepOption.GAMING_STEP;
        while (gameStep != GameStepOption.RESULTING_STEP) {
            char inputCharacter = inputHandler.readLetter(printHandler);
            gameStep = gameSession.executeGameStep(inputCharacter);
            printHandler.printView(gameSession);
        }

        GameSessionResults gameSessionResults = new GameSessionResults(gameSession.getGameResults());
        printHandler.printView(gameSessionResults);
    }

    private final PrintHandler printHandler;
    private final InputHandler inputHandler;
    private final GameSession.GameSessionBuilder gameSessionBuilder = GameSession.builder();
}
