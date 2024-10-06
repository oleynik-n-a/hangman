package backend.academy.stages.difficulty;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomDifficultyStageTest {
    private static GameDifficultyStage customDifficultyStage;

    @BeforeAll
    static void setUp() {
        customDifficultyStage = new CustomDifficultyStage();
    }

    @Test
    void testSubmitDifficulty() {
        // Test input 1
        customDifficultyStage.submitDifficulty(1);
        assert(customDifficultyStage.gameDifficulty() == GameDifficultyOption.CUSTOM);
        assert(customDifficultyStage.getLivesAmount() == 1);

        // Test input 5
        customDifficultyStage.submitDifficulty(5);
        assert(customDifficultyStage.gameDifficulty() == GameDifficultyOption.CUSTOM);
        assert(customDifficultyStage.getLivesAmount() == 5);
    }
}
