import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameBoardPanel extends BaseGameBoard {

    public GameBoard[][] localGameBoards = new GameBoard[3][3];
    public GameState gameState;
    private JButton[][] gameBoardButtons = new JButton[9][9];
    private AI aiPlayer;

    public GameBoardPanel() {
        aiPlayer = new AI("O", this);
        gameState = new GameState();
        setBackground(Color.BLACK);

        setLayout(new GridLayout(3, 3, 3, 3));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        for (int i = 0; i < localGameBoards.length; i++) {
            for (int j = 0; j < localGameBoards[i].length; j++) {
                localGameBoards[i][j] = new GameBoard(gameState, aiPlayer);
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
    }

    private class GameStateHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setActiveBasedOnLastMove();
            // Could be optimized but this is fine
            for (int i = 0; i < localGameBoards.length; i++) {
                for (int j = 0; j < localGameBoards[i].length; j++) {
                    board[i][j] = localGameBoards[i][j].winner;
                }
            }
            if (!gameState.player1Turn)
                aiPlayer.GetNextMove(100);

            repaint();
        }
    }

}
