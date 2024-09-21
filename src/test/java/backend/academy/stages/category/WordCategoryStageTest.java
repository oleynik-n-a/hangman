package backend.academy.stages.category;

import backend.academy.Main;
import backend.academy.game_states.WordCategoryOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WordCategoryStageTest {
    private static WordCategoryStage wordCategoryStage;

    @BeforeAll
    static void setUp() {
        wordCategoryStage = new WordCategoryStage();
    }

    @Test
    void testSubmitCategory() {
        // Test input 1
        wordCategoryStage.submitCategory(1);
        assert(wordCategoryStage.generateWord(Main.DICTIONARY).wordCategory() == WordCategoryOption.ANIMALS);

        // Test input 2
        wordCategoryStage.submitCategory(2);
        assert(wordCategoryStage.generateWord(Main.DICTIONARY).wordCategory() == WordCategoryOption.FRUITS);

        // Test input 3
        wordCategoryStage.submitCategory(3);
        assert(wordCategoryStage.generateWord(Main.DICTIONARY).wordCategory() == WordCategoryOption.HOUSEHOLD_APPLIANCES);

        // Test input 4
        wordCategoryStage.submitCategory(4);
        assert(wordCategoryStage.generateWord(Main.DICTIONARY).wordCategory() == WordCategoryOption.SPORTS);

        // Test input 5
        wordCategoryStage.submitCategory(5);
        assert(wordCategoryStage.generateWord(Main.DICTIONARY).wordCategory() != WordCategoryOption.RANDOM);
    }
}
