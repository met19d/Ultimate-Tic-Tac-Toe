import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameBoardPanel extends BaseGameBoard {

    public GameBoard[][] localGameBoards = new GameBoard[3][3];
    public GameState gameState;
    private JButton[][] gameBoardButtons = new JButton[9][9];
    private AI aiPlayer;
    private GamePanel fullPanel;

    public GameBoardPanel(GameState gameState, GamePanel fullPanel) {
        this.gameState = gameState;
        this.fullPanel = fullPanel;
        aiPlayer = new AI("O", this);

        setBackground(Color.ORANGE);

        setLayout(new GridLayout(3, 3, 3, 3));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        for (int i = 0; i < localGameBoards.length; i++) {
            for (int j = 0; j < localGameBoards[i].length; j++) {
                localGameBoards[i][j] = new GameBoard(gameState, i, j);
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

        for (GameBoard[] boards : localGameBoards) {
            for (GameBoard gameBoard : boards) {
                gameBoard.ready();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (boardFinished()) {
            // Currently does not display
            // But it does pick up who the winner is
            // going to put check somewhere else
            drawWinner();
            SwingUtilities.invokeLater(() -> {
                drawWinner();
            });
        }

    }

    public void setActiveBasedOnLastMove() {
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

    private class GameStateHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setActiveBasedOnLastMove();

            // if (!gameState.player1Turn)
            // aiPlayer.GetNextMove(100);

            fullPanel.actionPerformed();
            repaint();
        }
    }

}
