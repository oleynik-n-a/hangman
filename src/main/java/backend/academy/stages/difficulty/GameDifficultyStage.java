package backend.academy.stages.difficulty;

import backend.academy.stages.GameStage;
import java.security.SecureRandom;
import java.util.Map;
import lombok.Getter;

@Getter
public class GameDifficultyStage implements GameStage {
    public static final int MAX_LIVE_AMOUNT = 20;

    public void submitDifficulty(int input) {
        gameDifficulty = GAME_DIFFICULTY_OPTION_MAP.get(input);
    }

    public int getLivesAmount() {
        if (gameDifficulty == GameDifficultyOption.RANDOM) {
            SecureRandom random = new SecureRandom();
            return random.nextInt(1, MAX_LIVE_AMOUNT);
        }
        return LIVES_AMOUNT_MAP.get(gameDifficulty);
    }

    @Override
    @SuppressWarnings("multiplestringliterals")
    public String getPrintableView() {
        return "\nChoose game difficulty:\n"
            + "  1. " + GameDifficultyOption.EASY.name() + " (7 lives).\n"
            + "  2. " + GameDifficultyOption.MEDIUM.name() + " (5 lives).\n"
            + "  3. " + GameDifficultyOption.HARD.name() + " (3 lives).\n"
            + "  4. " + GameDifficultyOption.CUSTOM.name() + " (choose from 1 to " + MAX_LIVE_AMOUNT + " lives).\n"
            + "  5. Skip (generates random lives amount from 1 to " + MAX_LIVE_AMOUNT + ").\n\n"
            + "Input here:\n"
            + "  ->";
    }

    protected GameDifficultyOption gameDifficulty;
    private static final Map<Integer, GameDifficultyOption> GAME_DIFFICULTY_OPTION_MAP = Map.of(
        1, GameDifficultyOption.EASY,
        2, GameDifficultyOption.MEDIUM,
        3, GameDifficultyOption.HARD,
        4, GameDifficultyOption.CUSTOM,
        5, GameDifficultyOption.RANDOM
    );
    private static final Map<GameDifficultyOption, Integer> LIVES_AMOUNT_MAP = Map.of(
        GameDifficultyOption.EASY, 7,
        GameDifficultyOption.MEDIUM, 5,
        GameDifficultyOption.HARD, 3
    );
}
