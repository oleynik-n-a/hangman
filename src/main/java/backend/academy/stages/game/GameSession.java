package backend.academy.stages.game;

import backend.academy.adapters.Printable;
import backend.academy.data.WordInfo;
import backend.academy.game_states.GameDifficultyOption;
import backend.academy.game_states.GameResultOption;
import backend.academy.game_states.GameStepOption;
import java.util.HashSet;
import java.util.Set;

public class GameSession implements Printable {
    private final WordInfo wordInfo;
    private final GameDifficultyOption difficulty;
    private final int lives;
    private final Set<Character> correctAnswer;

    private int livesLeft;
    private final Set<Character> answer;

    public GameSession(WordInfo wordInfo, GameDifficultyOption difficulty, int lives) {
        this.wordInfo = wordInfo;
        this.difficulty = difficulty;
        this.lives = lives;
        this.correctAnswer = new HashSet<>();
        for (int i = 0; i < wordInfo.word().length(); i++) {
            this.correctAnswer.add(wordInfo.word().charAt(i));
        }

        this.livesLeft = lives;
        this.answer = new HashSet<>();
    }

    public GameStepOption executeGameStep(char input) {
        if (correctAnswer.contains(input)) {
            answer.add(input);
        } else {
            --livesLeft;
        }
        if (correctAnswer.size() == answer.size() || livesLeft == 0) {
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
    @SuppressWarnings("multiplestringliterals")
    public String getPrintableView() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nHangman\n")
            .append("Word category: ").append(wordInfo.wordCategory()).append('\n')
            .append("Difficulty: ").append(difficulty.toString()).append("\n\n");

        if (livesLeft < lives / 2) {
            stringBuilder.append("Hint: ").append(wordInfo.hint()).append(".\n\n");
        }

        stringBuilder.append("Word: ");
        for (int i = 0; i < wordInfo.word().length(); i++) {
            stringBuilder.append(answer.contains(wordInfo.word().charAt(i)) ? wordInfo.word().charAt(i) : '_');
        }
        stringBuilder.append("\n\n")
            .append("*--------------------*\n")
            .append("|                    |\n".repeat(lives - livesLeft))
            .append("|                     \n".repeat(livesLeft))
            .append("|                    O\n")
            .append("|                   /|\\\n")
            .append("|                    A\n")
            .append("H                    ").append(livesLeft == 0 ? ' ' : 'H').append("\n\n");

        stringBuilder.append("Input here:\n").append(" ->");

        return stringBuilder.toString();
    }
}
