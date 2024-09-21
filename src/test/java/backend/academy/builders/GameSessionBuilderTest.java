package backend.academy.builders;

import backend.academy.data.WordInfo;
import backend.academy.game_states.GameDifficultyOption;
import backend.academy.game_states.WordCategoryOption;
import backend.academy.stages.game.GameSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GameSessionBuilderTest {
    private static GameSessionBuilder gameSessionBuilder;
    private static int testLives;
    private static GameDifficultyOption testDifficulty;
    private static WordInfo testWordInfo;

    @BeforeAll
    static void setUp() {
        gameSessionBuilder = new GameSessionBuilder();
        testLives = 1;
        testDifficulty = GameDifficultyOption.EASY;
        testWordInfo = new WordInfo("TestAnimal", WordCategoryOption.ANIMALS, "HintForTestAnimal");
    }

    @Test
    void testBuild() {
        // Test standard build
        GameSession gameSession1 = new GameSession(testWordInfo, testDifficulty, testLives);
        gameSessionBuilder.lives(testLives);
        gameSessionBuilder.difficulty(testDifficulty);
        gameSessionBuilder.wordInfo(testWordInfo);
        GameSession gameSession2 = gameSessionBuilder.build();
        assert(gameSession1.getPrintableView().equals(gameSession2.getPrintableView()));
    }
}
