package GameLogic;

import javax.swing.*;
import java.util.Random;
import screens.game.GamePanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UltimateTicTacToePanel extends TicTacToe {

    public TicTacToePanel[][] localGameBoards = new TicTacToePanel[3][3];
    public GameState gameState;
    public JButton[][] gameBoardButtons = new JButton[9][9];
    private AI aiPlayer;
    private GamePanel fullPanel;

    public enum OpponentType {
        advanced,
        easy,
        human
    };

    public OpponentType opponentType;

    public UltimateTicTacToePanel(GameState gameState, GamePanel fullPanel) {
        this.gameState = gameState;
        this.fullPanel = fullPanel;
        String[] options = { "X", "O" };
        Random r = new Random();
        aiPlayer = new AI(options[r.nextInt(options.length)], this);
        SwingUtilities.invokeLater(() -> {
            aiMove();
        });

        setBackground(Color.ORANGE);

        setLayout(new GridLayout(3, 3, 3, 3));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        for (int i = 0; i < localGameBoards.length; i++) {
            for (int j = 0; j < localGameBoards[i].length; j++) {
                localGameBoards[i][j] = new TicTacToePanel(gameState, i, j);
                add(localGameBoards[i][j]);
            }
        }
        for (int i = 0; i < gameBoardButtons.length; i++) {
            int panelI = i / 3;
            for (int j = 0; j < gameBoardButtons[i].length; j++) {
                int panelJ = j / 3;
                gameBoardButtons[i][j] = new JButton("");
                gameBoardButtons[i][j].setBackground(Color.white);
                gameBoardButtons[i][j].putClientProperty("row", i % 3);
                gameBoardButtons[i][j].putClientProperty("column", j % 3);
                gameBoardButtons[i][j].setFont(new Font("BOLD", Font.BOLD, 35));
                gameBoardButtons[i][j].addActionListener(new GameStateHandler());
                localGameBoards[panelI][panelJ].add(gameBoardButtons[i][j]);
            }
        }
        for (TicTacToePanel[] boards : localGameBoards) {
            for (TicTacToePanel gameBoard : boards) {
                gameBoard.ready();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (boardFinished()) {
            winner = checkWinner();
            gameState.winner = winner;
            drawWinner(10);
            SwingUtilities.invokeLater(() -> {
                drawWinner(10);
            });
            fullPanel.checkForWinner();
        }
    }

    public void setActiveBasedOnLastMove() {
        if (gameState.lastMove == null)
            return;
        if (localGameBoards[gameState.lastMove.x][gameState.lastMove.y].boardFinished()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    localGameBoards[i][j].setActive(true);
                }
                localGameBoards[gameState.lastMove.x][gameState.lastMove.y].setActive(false);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    localGameBoards[i][j].setActive(false);
                }
            }
            localGameBoards[gameState.lastMove.x][gameState.lastMove.y].setActive(true);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (localGameBoards[i][j].boardFinished())
                    localGameBoards[i][j].setActive(false);
                board[i][j] = localGameBoards[i][j].winner;
            }
        }

    }

    private void aiMove() {
        if ((aiPlayer.playingIcon.equals("O") && !gameState.player1Turn)
                || (aiPlayer.playingIcon.equals("X") && gameState.player1Turn)) {
            if (opponentType == OpponentType.advanced) {
                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                if (gameState.lastMove != null
                        && localGameBoards[gameState.lastMove.x][gameState.lastMove.y].boardFinished())
                    aiPlayer.GetNextMove(2);
                else
                    aiPlayer.GetNextMove(3);
                setCursor(defaultCursor);
            } else if (opponentType == OpponentType.easy)
                aiPlayer.GetNextRandomMove();
        }
    }

    private class GameStateHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setActiveBasedOnLastMove();
            aiMove();
            repaint();
            fullPanel.actionPerformed();
        }
    }

}
