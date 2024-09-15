package backend.academy.builders;

import backend.academy.data.WordInfo;
import backend.academy.game_states.GameDifficultyOption;
import backend.academy.stages.game.GameSession;
import lombok.Setter;

@Setter
public class GameSessionBuilder implements IBuilder<GameSession> {
    private WordInfo wordInfo;
    private GameDifficultyOption difficulty;
    private int lives;

    @Override
    public GameSession build() {
        return new GameSession(wordInfo, difficulty, lives);
    }
}
