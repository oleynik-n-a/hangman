package backend.academy.stages.difficulty;

import backend.academy.adapters.IPrintable;
import backend.academy.adapters.IRandom;
import backend.academy.game_states.GameDifficultyOptions;
import lombok.Getter;

@Getter
public class GameDifficultyStage implements IPrintable, IRandom {
    private GameDifficultyOptions gameDifficulty;
    public static final int MAX_DIFFICULTY = 20;

    @SuppressWarnings("magicnumber")
    public void submitDifficulty(int input) {
        gameDifficulty = switch (input) {
            case 1 -> GameDifficultyOptions.EASY;
            case 2 -> GameDifficultyOptions.MEDIUM;
            case 3 -> GameDifficultyOptions.HARD;
            case 4 -> GameDifficultyOptions.CUSTOM;
            default -> GameDifficultyOptions.RANDOM;
        };
    }

    @SuppressWarnings("magicnumber")
    public int getLivesAmount() {
        return switch (gameDifficulty) {
            case EASY -> 7;
            case MEDIUM -> 5;
            case HARD -> 3;
            case RANDOM -> IRandom.generateRandomInteger(1, MAX_DIFFICULTY);
            default -> 0;
        };
    }

    @Override
    @SuppressWarnings("multiplestringliterals")
    public String getPrintableView() {
        return "\nChoose game difficulty:\n"
            + "  1. " + GameDifficultyOptions.EASY.name() + " (7 lives).\n"
            + "  2. " + GameDifficultyOptions.MEDIUM.name() + " (5 lives).\n"
            + "  3. " + GameDifficultyOptions.HARD.name() + " (3 lives).\n"
            + "  4. " + GameDifficultyOptions.CUSTOM.name() + " (choose from 1 to " + MAX_DIFFICULTY + " lives).\n"
            + "  5. Skip (generates random lives amount from 1 to " + MAX_DIFFICULTY + ").\n\n"
            + "Input here:\n"
            + "  ->";
    }
}
