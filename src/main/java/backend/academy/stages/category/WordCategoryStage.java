package backend.academy.stages.category;

import backend.academy.data.WordInfo;
import backend.academy.stages.GameStage;
import java.security.SecureRandom;
import java.util.Map;

public class WordCategoryStage implements GameStage {
    public void submitCategory(int input) {
        wordCategory = WORD_CATEGORY_OPTION_MAP.get(input);
        if (wordCategory == WordCategoryOption.RANDOM) {
            SecureRandom random = new SecureRandom();
            submitCategory(random.nextInt(1, WordCategoryOption.values().length - 1));
        }
    }

    public WordInfo generateWord(Map<WordCategoryOption, WordInfo[]> dictionary) {
        SecureRandom random = new SecureRandom();
        return dictionary.get(wordCategory)[random.nextInt(0, dictionary.get(wordCategory).length)];
    }

    @Override
    @SuppressWarnings("multiplestringliterals")
    public String getPrintableView() {
        return "Choose word category:\n"
            + "  1. " + WordCategoryOption.ANIMALS.name() + ".\n"
            + "  2. " + WordCategoryOption.FRUITS.name() + ".\n"
            + "  3. " + WordCategoryOption.HOUSEHOLD_APPLIANCES.name() + ".\n"
            + "  4. " + WordCategoryOption.SPORTS.name() + ".\n"
            + "  5. Skip (random category)\n\n"
            + "Input here:\n"
            + "  ->";
    }

    private WordCategoryOption wordCategory;
    private static final Map<Integer, WordCategoryOption> WORD_CATEGORY_OPTION_MAP = Map.of(
        1, WordCategoryOption.ANIMALS,
        2, WordCategoryOption.FRUITS,
        3, WordCategoryOption.HOUSEHOLD_APPLIANCES,
        4, WordCategoryOption.SPORTS,
        5, WordCategoryOption.RANDOM
    );
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
