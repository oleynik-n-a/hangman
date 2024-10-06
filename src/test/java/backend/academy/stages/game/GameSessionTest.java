package backend.academy.stages.game;

import backend.academy.data.WordInfo;
import backend.academy.stages.difficulty.GameDifficultyOption;
import backend.academy.stages.category.WordCategoryOption;
import org.junit.jupiter.api.Test;

public class GameSessionTest {
    private static GameSession gameSession;

    @Test
    void testExecuteGameStep() {
        // Test correct execution
        gameSession = new GameSession(new WordInfo("test", WordCategoryOption.RANDOM, ""), GameDifficultyOption.HARD, 3);
        GameStepOption gameStepOption = gameSession.executeGameStep('t');
        assert(gameStepOption == GameStepOption.GAMING_STEP);
        gameStepOption = gameSession.executeGameStep('e');
        assert(gameStepOption == GameStepOption.GAMING_STEP);
        gameStepOption = gameSession.executeGameStep('s');
        assert(gameStepOption == GameStepOption.RESULTING_STEP);

        // Test correct execution with 1-letter word
        gameSession = new GameSession(new WordInfo("t", WordCategoryOption.RANDOM, ""), GameDifficultyOption.HARD, 3);
        gameStepOption = gameSession.executeGameStep('t');
        assert(gameStepOption == GameStepOption.RESULTING_STEP);

        // Test incorrect execution
        gameSession = new GameSession(new WordInfo("test", WordCategoryOption.RANDOM, ""), GameDifficultyOption.HARD, 3);
        gameStepOption = gameSession.executeGameStep('x');
        assert(gameStepOption == GameStepOption.GAMING_STEP);
        gameStepOption = gameSession.executeGameStep('x');
        assert(gameStepOption == GameStepOption.GAMING_STEP);
        gameStepOption = gameSession.executeGameStep('x');
        assert(gameStepOption == GameStepOption.RESULTING_STEP);

        // Test incorrect execution with 1-letter word
        gameSession = new GameSession(new WordInfo("test", WordCategoryOption.RANDOM, ""), GameDifficultyOption.CUSTOM, 1);
        gameStepOption = gameSession.executeGameStep('x');
        assert(gameStepOption == GameStepOption.RESULTING_STEP);
    }

    @Test
    void testGetGameResults() {
        // Test win result
        gameSession = new GameSession(new WordInfo("test", WordCategoryOption.RANDOM, ""), GameDifficultyOption.HARD, 3);
        gameSession.executeGameStep('t');
        gameSession.executeGameStep('e');
        gameSession.executeGameStep('s');
        assert(gameSession.getGameResults() == GameResultOption.WIN);

        // Test win result with 1-letter word
        gameSession = new GameSession(new WordInfo("t", WordCategoryOption.RANDOM, ""), GameDifficultyOption.HARD, 3);
        gameSession.executeGameStep('t');
        assert(gameSession.getGameResults() == GameResultOption.WIN);

        // Test loss result
        gameSession = new GameSession(new WordInfo("test", WordCategoryOption.RANDOM, ""), GameDifficultyOption.HARD, 3);
        gameSession.executeGameStep('x');
        gameSession.executeGameStep('x');
        gameSession.executeGameStep('x');
        assert(gameSession.getGameResults() == GameResultOption.LOSS);

        // Test loss result with 1-letter word
        gameSession = new GameSession(new WordInfo("test", WordCategoryOption.RANDOM, ""), GameDifficultyOption.CUSTOM, 1);
        gameSession.executeGameStep('x');
        assert(gameSession.getGameResults() == GameResultOption.LOSS);
    }
}
