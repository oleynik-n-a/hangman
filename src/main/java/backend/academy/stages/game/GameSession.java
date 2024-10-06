package backend.academy.stages.game;

import backend.academy.stages.GameStage;
import backend.academy.data.WordInfo;
import backend.academy.stages.difficulty.GameDifficultyOption;
import lombok.Builder;
import java.util.HashSet;
import java.util.Set;

public class GameSession implements GameStage {
    private final WordInfo wordInfo;
    private final GameDifficultyOption difficulty;
    private final int lives;
    private final Set<Character> correctAnswer = new HashSet<>();
    private int mistakes = 0;
    private final Set<Character> answer = new HashSet<>();

    @Builder
    public GameSession(WordInfo wordInfo, GameDifficultyOption difficulty, int lives) {
        this.wordInfo = wordInfo;
        this.difficulty = difficulty;
        this.lives = lives;
        for (int i = 0; i < wordInfo.word().length(); i++) {
            this.correctAnswer.add(wordInfo.word().charAt(i));
        }
    }

    public GameStepOption executeGameStep(char input) {
        if (correctAnswer.contains(input)) {
            answer.add(input);
        } else {
            ++mistakes;
        }
        if (correctAnswer.size() == answer.size() || mistakes == lives) {
            return GameStepOption.RESULTING_STEP;
        }
        return GameStepOption.GAMING_STEP;
    }

    public GameResultOption getGameResults() {
        if (correctAnswer.size() == answer.size()) {
            return GameResultOption.WIN;
        }
        return GameResultOption.LOSS;
    }

    @Override
    public String getPrintableView() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nHangman\n")
            .append("Word category: ").append(wordInfo.wordCategory()).append('\n')
            .append("Difficulty: ").append(difficulty.toString()).append("\n\n");

        if (mistakes > lives / 2) {
            stringBuilder.append("Hint: ").append(wordInfo.hint()).append(".\n\n");
        }

        stringBuilder.append("Word: ");
        for (int i = 0; i < wordInfo.word().length(); i++) {
            stringBuilder.append(answer.contains(wordInfo.word().charAt(i)) ? wordInfo.word().charAt(i) : '_');
        }
        stringBuilder.append("\n\n")
            .append("*--------------------*\n")
            .append("|                    |\n".repeat(mistakes))
            .append("|                     \n".repeat(lives - mistakes))
            .append("|                    O\n")
            .append("|                   /|\\\n")
            .append("|                    A\n")
            .append("H                    ").append(mistakes == lives ? ' ' : 'H').append("\n\n");

        stringBuilder.append("Input here:\n").append(" ->");

        return stringBuilder.toString();
    }
}
