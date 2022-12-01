import java.util.ArrayList;
import javax.swing.JButton;

public class AI {
    String playingIcon;
    String opponentString;
    GameBoardPanel fullGame;

    AI(String player, GameBoardPanel game) {
        this.fullGame = game;
        playingIcon = player;
        opponentString = player.equals("X") ? "O" : "X";
    }

    public void GetNextMove(int maxDepth) {
        ArrayList<GameBoard> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fullGame.localGameBoards[i][j].getActive()) {
                    activeBoards.add(fullGame.localGameBoards[i][j]);
                }
            }
        }
        JButton bestButton = null;

        int bestScore = -2;
        for (GameBoard game : activeBoards) {
            int bestBoardMove = -2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available
                    if (game.getState(i, j).equals("")) {
                        int score = testMove(game, playingIcon, i, j, 0, false);

                        if (score >= bestBoardMove) {
                            bestBoardMove = score;
                            bestButton = game.buttons[i][j];
                        }
                    }
                }
            }
            // if (bestBoardMove >)
        }
        // System.out.println(activeBoards.size());
        System.out.println(bestScore);

        if (bestButton != null) {
            bestButton.doClick();
        }
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int min(int a, int b) {
        return (a < b) ? a : b;
    }

    private int getScoreFromWinner(String winner) {
        if (winner.equals("draw"))
            return 0;
        else if (winner.equals(playingIcon))
            return 1;
        return -1;
    }

    private int testMove(BaseGameBoard game, String player, int i, int j, int depth,
            boolean isMaximizing) {
        Coordinates lastMove = fullGame.gameState.lastMove;

        game.playMove(i, j, player);
        fullGame.gameState.lastMove = new Coordinates(i, j);
        fullGame.setActiveBasedOnLastMove();
        game.winner = game.checkWinner();

        int score = minimax(depth + 1, isMaximizing);

        game.playMove(i, j, "");
        fullGame.gameState.lastMove = lastMove;
        game.winner = "";
        fullGame.setActiveBasedOnLastMove();
        if (score != 0)
            System.out.println(score);
        return score;
    }

    public int minimax(int depth, boolean isMaximizing) {
        String check = fullGame.checkWinner();
        if (!check.equals("")) {
            // System.out.println(check);
            // System.out.println(isMaximizing);
            // System.out.println(getScoreFromWinner(check));
            return getScoreFromWinner(check);
        }

        ArrayList<BaseGameBoard> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fullGame.localGameBoards[i][j].getActive()) {
                    activeBoards.add(fullGame.localGameBoards[i][j]);
                }
            }
        }

        if (depth > 3) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = -2;
            for (BaseGameBoard game : activeBoards) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (game.getState(i, j).equals("")) {
                            int score = testMove(game, playingIcon, i, j, depth, false);
                            if (score != 0) {
                                return score;
                            }
                            bestScore = max(score, bestScore);
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 2;
            for (BaseGameBoard game : activeBoards) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (game.getState(i, j).equals("")) {
                            int score = testMove(game, opponentString, i, j, depth, true);
                            if (score != 0) {
                                return score;
                            }
                            bestScore = min(score, bestScore);
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public int randomMove() {
        String check = fullGame.checkWinner();
        if (!check.equals("")) {
            return getScoreFromWinner(check);
        }

        ArrayList<BaseGameBoard> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fullGame.localGameBoards[i][j].getActive()) {
                    activeBoards.add(fullGame.localGameBoards[i][j]);
                }
            }
        }
        return 0;
    }

}
