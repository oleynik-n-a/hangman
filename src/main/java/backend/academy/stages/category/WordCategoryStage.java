package backend.academy.stages.category;

import backend.academy.adapters.IRandom;
import backend.academy.adapters.IPrintable;
import backend.academy.game_states.WordCategoryOptions;
import org.apache.commons.lang3.NotImplementedException;

public class WordCategoryStage implements IPrintable, IRandom {
    private WordCategoryOptions wordCategory;

    public void submitCategory(int input) {
        switch (input) {
            case 1:
                wordCategory = WordCategoryOptions.ANIMALS;
                break;
            case 2:
                wordCategory = WordCategoryOptions.FRUITS;
                break;
            case 3:
                wordCategory = WordCategoryOptions.HOUSEHOLD_APPLIANCES;
                break;
            case 4:
                wordCategory = WordCategoryOptions.SPORTS;
                break;
            case 5:
                submitCategory(IRandom.generateRandomInteger(1, 4));
                break;
        }
    }

    public String generateWord() {
        throw new NotImplementedException();
    }

    @Override
    public String getPrintableView() {
        return "Choose word category:\n" +
            "  1. " + WordCategoryOptions.ANIMALS.name() + ".\n" +
            "  2. " + WordCategoryOptions.FRUITS.name() + ".\n" +
            "  3. " + WordCategoryOptions.HOUSEHOLD_APPLIANCES.name() + ".\n" +
            "  4. " + WordCategoryOptions.SPORTS.name() + ".\n" +
            "  5. Skip.\n\n" +
            "Input here:\n" +
            "  ->";
    }
}
