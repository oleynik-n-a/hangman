package backend.academy.stages.difficulty;

import lombok.Getter;

@Getter
public class CustomDifficultyStage extends GameDifficultyStage {
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

    private int livesAmount;
}
