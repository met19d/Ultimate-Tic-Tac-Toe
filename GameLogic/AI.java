package GameLogic;

import java.util.ArrayList;
import javax.swing.JButton;
import java.util.Random;

public class AI {
    String playingIcon;
    String opponentString;
    UltimateTicTacToePanel fullGame;
    Random random = new Random();

    private int maxDepth;

    AI(String player, UltimateTicTacToePanel game) {
        this.fullGame = game;
        playingIcon = player;
        opponentString = player.equals("X") ? "O" : "X";
    }

    public void GetNextMove(int maxDepth) {
        this.maxDepth = maxDepth;
        ArrayList<JButton> bestButtons = new ArrayList<>();
        ArrayList<TicTacToePanel> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fullGame.localGameBoards[i][j].getActive()) {
                    activeBoards.add(fullGame.localGameBoards[i][j]);
                }
            }
        }

        int bestScore = -2;
        for (TicTacToePanel game : activeBoards) {
            int bestBoardMove = -2;
            ArrayList<JButton> availableButtons = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available
                    if (game.getState(i, j).equals("")) {
                        int score = testMove(game, playingIcon, i, j, 0, false);

                        if (score > bestBoardMove) {
                            bestBoardMove = score;
                            availableButtons.clear();
                            availableButtons.add(game.buttons[i][j]);
                        } else if (score == bestBoardMove) {
                            availableButtons.add(game.buttons[i][j]);
                        }
                    }
                }
            }
            if (bestBoardMove > bestScore) {
                bestScore = bestBoardMove;
                bestButtons.clear();
                bestButtons.addAll(availableButtons);
            } else if (bestBoardMove == bestScore) {
                bestButtons.addAll(availableButtons);
            }
        }
        bestButtons.get(random.nextInt(bestButtons.size())).doClick();
    }

    public void GetNextRandomMove() {
        ArrayList<JButton> availableButtons = new ArrayList<>();
        ArrayList<TicTacToePanel> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fullGame.localGameBoards[i][j].getActive()) {
                    activeBoards.add(fullGame.localGameBoards[i][j]);
                }
            }
        }

        for (TicTacToePanel game : activeBoards) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.getState(i, j).equals("")) {
                        availableButtons.add(game.buttons[i][j]);
                    }
                }
            }
        }
        availableButtons.get(random.nextInt(availableButtons.size())).doClick();
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

    private int testMove(TicTacToe game, String player, int i, int j, int depth,
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
        return score;
    }

    public int minimax(int depth, boolean isMaximizing) {
        String check = fullGame.checkWinner();
        if (!check.equals("")) {
            return getScoreFromWinner(check);
        }

        ArrayList<TicTacToe> activeBoards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fullGame.localGameBoards[i][j].getActive()) {
                    activeBoards.add(fullGame.localGameBoards[i][j]);
                }
            }
        }

        if (depth > maxDepth) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = -2;
            for (TicTacToe game : activeBoards) {
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
            for (TicTacToe game : activeBoards) {
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

}
