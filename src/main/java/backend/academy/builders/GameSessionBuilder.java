package backend.academy.builders;

import backend.academy.game_states.GameDifficultyOptions;
import backend.academy.stages.game.GameSession;
import lombok.Setter;

@Setter
public class GameSessionBuilder implements IBuilder<GameSession> {
    private String word;
    private GameDifficultyOptions difficulty;
    private int lives;

    @Override
    public GameSession build() {
        return new GameSession(word, difficulty, lives);
    }
}
