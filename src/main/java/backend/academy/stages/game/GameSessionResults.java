package backend.academy.stages.game;

import backend.academy.stages.GameStage;

public class GameSessionResults implements GameStage {
    public GameSessionResults(GameResultOption gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public String getPrintableView() {
        return "\n\nGame result: " + gameResult.name() + "!\n\n"
            + "Start new game? ('y' for restart, else for quit)\n"
            + " ->";
    }

    private final GameResultOption gameResult;
}
