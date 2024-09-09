package backend.academy.stages.category;

import backend.academy.adapters.IPrintable;
import backend.academy.adapters.IRandom;
import backend.academy.game_states.WordCategoryOption;
import org.apache.commons.lang3.NotImplementedException;

public class WordCategoryStage implements IPrintable, IRandom {
    private WordCategoryOption wordCategory;

    @SuppressWarnings("magicnumber")
    public void submitCategory(int input) {
        switch (input) {
            case 1:
                wordCategory = WordCategoryOption.ANIMALS;
                break;
            case 2:
                wordCategory = WordCategoryOption.FRUITS;
                break;
            case 3:
                wordCategory = WordCategoryOption.HOUSEHOLD_APPLIANCES;
                break;
            case 4:
                wordCategory = WordCategoryOption.SPORTS;
                break;
            default:
                wordCategory = WordCategoryOption.RANDOM;
                submitCategory(IRandom.generateRandomInteger(1, WordCategoryOption.values().length - 1));
                break;
        }
    }

    public String generateWord() {
        throw new NotImplementedException();
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
}
