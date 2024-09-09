package backend.academy.stages.game;

import backend.academy.adapters.IPrintable;
import backend.academy.game_states.GameDifficultyOption;
import org.apache.commons.lang3.NotImplementedException;

public class GameSession implements IPrintable {
    private final String word;
    private final GameDifficultyOption difficulty;
    private final int lives;

    public GameSession(String word, GameDifficultyOption difficulty, int lives) {
        this.word = word;
        this.difficulty = difficulty;
        this.lives = lives;
    }

    @Override
    public String getPrintableView() {
        throw new NotImplementedException();
    }
}
