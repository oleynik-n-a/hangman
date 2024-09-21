package backend.academy;

import backend.academy.builders.GameSessionBuilder;
import backend.academy.data.WordInfo;
import backend.academy.game_states.GameDifficultyOption;
import backend.academy.game_states.GameStepOption;
import backend.academy.game_states.WordCategoryOption;
import backend.academy.stages.category.WordCategoryStage;
import backend.academy.stages.difficulty.CustomDifficultyStage;
import backend.academy.stages.difficulty.GameDifficultyStage;
import backend.academy.stages.game.GameSession;
import backend.academy.stages.game.GameSessionResults;
import backend.academy.stream_handlers.InputHandler;
import backend.academy.stream_handlers.PrintHandler;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        // Initializing Singletons (singleton pattern) - output & input stream handlers.
        PrintHandler printHandler = PrintHandler.getInstance(System.out);
        InputHandler inputHandler = InputHandler.getInstance(System.in);

        // Initializing Builder class for future game session (builder pattern).
        final GameSessionBuilder gameSessionBuilder = new GameSessionBuilder();

        int input;

        // Game cycle to make possible restart the game.
        while (true) {
            // Stage to choose word category.
            WordCategoryStage wordCategoryStage = new WordCategoryStage();
            printHandler.printView(wordCategoryStage);
            input = inputHandler.readInteger(printHandler, 1, WordCategoryOption.values().length);
            wordCategoryStage.submitCategory(input);

            // Building game session after choosing stage.
            gameSessionBuilder.wordInfo(wordCategoryStage.generateWord(DICTIONARY));

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
                input = inputHandler.readInteger(printHandler, 1, GameDifficultyStage.MAX_DIFFICULTY);
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

    // Initialization of game words.
    public static final Map<WordCategoryOption, WordInfo[]> DICTIONARY = Map.of(
        WordCategoryOption.ANIMALS, new WordInfo[] {
            new WordInfo("WOLF", WordCategoryOption.ANIMALS, "Gray predator"),
            new WordInfo("BEAR", WordCategoryOption.ANIMALS, "Big predator loves fish"),
            new WordInfo("CHICKEN", WordCategoryOption.ANIMALS, "Small animal that produces eggs"),
            new WordInfo("FOX", WordCategoryOption.ANIMALS, "Canny animal steels chickens"),
        },
        WordCategoryOption.FRUITS, new WordInfo[] {
            new WordInfo("APPLE", WordCategoryOption.FRUITS, "Snow white's killer"),
            new WordInfo("MANGO", WordCategoryOption.FRUITS, "Indian's king of the fruits"),
            new WordInfo("PLUM", WordCategoryOption.FRUITS, "Purple like Thanos"),
        },
        WordCategoryOption.HOUSEHOLD_APPLIANCES, new WordInfo[] {
            new WordInfo("FRIDGE", WordCategoryOption.HOUSEHOLD_APPLIANCES, "Cold guy"),
            new WordInfo("IRON", WordCategoryOption.HOUSEHOLD_APPLIANCES, "The one you forgot"),
            new WordInfo("DISHWASHER", WordCategoryOption.HOUSEHOLD_APPLIANCES, "Helps you to clean up"),
        },
        WordCategoryOption.SPORTS, new WordInfo[] {
            new WordInfo("SWIMMING", WordCategoryOption.SPORTS, "Wet session"),
            new WordInfo("FOOTBALL", WordCategoryOption.SPORTS, "You will need just ball and yor legs"),
            new WordInfo("CHESS", WordCategoryOption.SPORTS, "Mind sport"),
        }
    );
}
