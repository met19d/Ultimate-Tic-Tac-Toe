import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;

public class GameBoardPanel extends JPanel {

    private GameState gameState;
    private JButton[][] gameBoardButtons = new JButton[9][9];
    private GameBoard[][] localGameBoards = new GameBoard[3][3];

    public GameBoardPanel() {
        gameState = new GameState();
        setBackground(Color.BLACK);

        setLayout(new GridLayout(3, 3, 3, 3));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        for (int i = 0; i < localGameBoards.length; i++) {
            for (int j = 0; j < localGameBoards[i].length; j++) {
                localGameBoards[i][j] = new GameBoard(gameState);
                add(localGameBoards[i][j]);
            }
        }
        for (int i = 0; i < gameBoardButtons.length; i++) {
            int panelI = i / 3;
            for (int j = 0; j < gameBoardButtons[i].length; j++) {
                int panelJ = j / 3;
                gameBoardButtons[i][j] = new JButton("");
                gameBoardButtons[i][j].putClientProperty("row", i % 3);
                gameBoardButtons[i][j].putClientProperty("column", j % 3);
                gameBoardButtons[i][j].setFont(new Font("BOLD", Font.BOLD, 35));
                // gameBoardButtons[i][j].addActionListener(new GameStateHandler());
                localGameBoards[panelI][panelJ].add(gameBoardButtons[i][j]);
            }
        }

        for (GameBoard[] boards : localGameBoards) {
            for (GameBoard gameBoard : boards) {
                gameBoard.ready();
            }
        }
    }

    // private class GameStateHandler implements ActionListener {

    // public void actionPerformed(ActionEvent event) {
    // // Do nothing for now
    // }
    // }

}
