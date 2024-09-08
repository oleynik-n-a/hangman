package backend.academy.stages.game;

import backend.academy.game_states.GameDifficultyOptions;
import backend.academy.adapters.IPrintable;

public class GameSession implements IPrintable {
    private final String word;
    private final GameDifficultyOptions difficulty;
    private final int lives;

    public GameSession(String word, GameDifficultyOptions difficulty, int lives) {
        this.word = word;
        this.difficulty = difficulty;
        this.lives = lives;
    }

    @Override
    public String getPrintableView() {
        return "";
    }
}
