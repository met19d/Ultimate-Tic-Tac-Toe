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
        int bestScore = -2;

        ArrayList<GameBoard> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fullGame.localGameBoards[i][j].getActive()) {
                    activeBoards.add(fullGame.localGameBoards[i][j]);
                }
            }
        }
        JButton bestButton = null;

        for (GameBoard game : activeBoards) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available
                    if (game.getState(i, j).equals("")) {
                        game.playMove(i, j, playingIcon);
                        int score = minimax(fullGame, 0, false);
                        game.playMove(i, j, "");
                        if (score > bestScore) {
                            bestScore = score;
                            bestButton = game.buttons[i][j];
                        }
                    }
                }
            }
        }

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
        if (winner == "draw")
            return 0;
        else if (winner == playingIcon)
            return 1;
        return -1;
    }

    public int minimax(GameBoardPanel board, int depth, boolean isMaximizing) {
        String check = board.checkWinner();
        if (!check.equals("")) {
            return getScoreFromWinner(check);
        }

        ArrayList<BaseGameBoard> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.localGameBoards[i][j].getActive()) {
                    activeBoards.add(board.localGameBoards[i][j]);
                }
            }
        }

        if (depth > 2) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = -2;
            for (BaseGameBoard game : activeBoards) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (game.getState(i, j).equals("")) {
                            Coordinates lastMove = board.gameState.lastMove;

                            game.playMove(i, j, playingIcon);
                            board.gameState.lastMove = new Coordinates(i, j);
                            board.setActiveBasedOnLastMove();

                            int score = minimax(board, depth + 1, false);

                            game.playMove(i, j, "");
                            board.gameState.lastMove = lastMove;
                            board.setActiveBasedOnLastMove();

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
                            Coordinates lastMove = board.gameState.lastMove;

                            game.playMove(i, j, opponentString);
                            board.gameState.lastMove = new Coordinates(i, j);
                            board.setActiveBasedOnLastMove();

                            int score = minimax(board, depth + 1, true);

                            game.playMove(i, j, "");
                            board.gameState.lastMove = lastMove;
                            board.setActiveBasedOnLastMove();

                            bestScore = min(score, bestScore);
                        }
                    }
                }
            }
            return bestScore;
        }
    }

}
