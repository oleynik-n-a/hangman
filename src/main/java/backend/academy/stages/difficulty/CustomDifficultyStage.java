package backend.academy.stages.difficulty;

import backend.academy.game_states.GameDifficultyOptions;
import lombok.Getter;

@Getter
public class CustomDifficultyStage extends GameDifficultyStage {
    private final GameDifficultyOptions gameDifficulty = GameDifficultyOptions.CUSTOM;
    private int livesAmount;

    @Override
    public void submitDifficulty(int input) {
        livesAmount = input;
    }

    @Override
    public int getLivesAmount() {
        return livesAmount;
    }

    @Override
    public String getPrintableView() {
        return super.getPrintableView()
            + "4\n\n"
            + "Input lives amount:\n"
            + "  ->";
    }
}
