package backend.academy.stages.difficulty;

import backend.academy.game_states.GameDifficultyOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameDifficultyStageTest {
    private static GameDifficultyStage gameDifficultyStage;

    @BeforeEach
    void setUp() {
        gameDifficultyStage = new GameDifficultyStage();
    }

    @Test
    void testSubmitCategory() {
        // Test input 1
        gameDifficultyStage.submitDifficulty(1);
        assert(gameDifficultyStage.gameDifficulty() == GameDifficultyOption.EASY);

        // Test input 2
        gameDifficultyStage.submitDifficulty(2);
        assert(gameDifficultyStage.gameDifficulty() == GameDifficultyOption.MEDIUM);

        // Test input 3
        gameDifficultyStage.submitDifficulty(3);
        assert(gameDifficultyStage.gameDifficulty() == GameDifficultyOption.HARD);

        // Test input 4
        gameDifficultyStage.submitDifficulty(4);
        assert(gameDifficultyStage.gameDifficulty() == GameDifficultyOption.CUSTOM);

        // Test input 5
        gameDifficultyStage.submitDifficulty(5);
        assert(gameDifficultyStage.gameDifficulty() == GameDifficultyOption.RANDOM);
    }

    @Test
    void testGetLivesAmount() {
        // Test hard difficulty lives amount
        gameDifficultyStage.submitDifficulty(1);
        assert(gameDifficultyStage.getLivesAmount() == 7);

        // Test medium difficulty lives amount
        gameDifficultyStage.submitDifficulty(2);
        assert(gameDifficultyStage.getLivesAmount() == 5);

        // Test easy difficulty lives amount
        gameDifficultyStage.submitDifficulty(3);
        assert(gameDifficultyStage.getLivesAmount() == 3);

        // Test custom difficulty lives amount (before CustomDifficultyStage object creation)
        gameDifficultyStage.submitDifficulty(4);
        assert(gameDifficultyStage.getLivesAmount() == 0);

        // Test random difficulty lives borders
        gameDifficultyStage.submitDifficulty(5);
        assert(gameDifficultyStage.getLivesAmount() >= 1 && gameDifficultyStage.getLivesAmount() <= GameDifficultyStage.MAX_DIFFICULTY);
    }
}
