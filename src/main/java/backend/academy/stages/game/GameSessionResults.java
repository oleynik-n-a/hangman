package backend.academy.stages.game;

import backend.academy.adapters.Printable;
import backend.academy.game_states.GameResultOption;

public class GameSessionResults implements Printable {
    private final GameResultOption gameResult;

    public GameSessionResults(GameResultOption gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public String getPrintableView() {
        return "\n\nGame result: " + gameResult.name() + "!\n\n"
            + "Start new game? ('y' for restart, else for quit)\n"
            + " ->";
    }
}
