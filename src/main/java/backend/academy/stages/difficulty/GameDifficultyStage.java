package backend.academy.stages.difficulty;

import backend.academy.adapters.Printable;
import backend.academy.adapters.Random;
import backend.academy.game_states.GameDifficultyOption;
import lombok.Getter;

@Getter
public class GameDifficultyStage implements Printable, Random {
    private GameDifficultyOption gameDifficulty;
    public static final int MAX_DIFFICULTY = 20;

    @SuppressWarnings("magicnumber")
    public void submitDifficulty(int input) {
        gameDifficulty = switch (input) {
            case 1 -> GameDifficultyOption.EASY;
            case 2 -> GameDifficultyOption.MEDIUM;
            case 3 -> GameDifficultyOption.HARD;
            case 4 -> GameDifficultyOption.CUSTOM;
            default -> GameDifficultyOption.RANDOM;
        };
    }

    @SuppressWarnings("magicnumber")
    public int getLivesAmount() {
        return switch (gameDifficulty) {
            case EASY -> 7;
            case MEDIUM -> 5;
            case HARD -> 3;
            case RANDOM -> Random.generateRandomInteger(1, MAX_DIFFICULTY);
            default -> 0;
        };
    }

    @Override
    @SuppressWarnings("multiplestringliterals")
    public String getPrintableView() {
        return "\nChoose game difficulty:\n"
            + "  1. " + GameDifficultyOption.EASY.name() + " (7 lives).\n"
            + "  2. " + GameDifficultyOption.MEDIUM.name() + " (5 lives).\n"
            + "  3. " + GameDifficultyOption.HARD.name() + " (3 lives).\n"
            + "  4. " + GameDifficultyOption.CUSTOM.name() + " (choose from 1 to " + MAX_DIFFICULTY + " lives).\n"
            + "  5. Skip (generates random lives amount from 1 to " + MAX_DIFFICULTY + ").\n\n"
            + "Input here:\n"
            + "  ->";
    }
}
