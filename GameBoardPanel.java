import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;

public class GameBoardPanel extends JPanel {

    private boolean player1_turn = true;
    private JButton[][] gameBoardButtons = new JButton[9][9];
    private JPanel[][] smallPanels = new JPanel[3][3];

    public GameBoardPanel() {
        setBackground(Color.BLACK);

        setLayout(new GridLayout(3, 3, 3, 3));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        for (int i = 0; i < smallPanels.length; i++) {
            for (int j = 0; j < smallPanels[i].length; j++) {
                smallPanels[i][j] = new JPanel(new GridLayout(3, 3));
                add(smallPanels[i][j]);
            }
        }
        for (int i = 0; i < gameBoardButtons.length; i++) {
            int panelI = i / 3;
            for (int j = 0; j < gameBoardButtons[i].length; j++) {
                int panelJ = j / 3;
                gameBoardButtons[i][j] = new JButton("");
                gameBoardButtons[i][j].putClientProperty("row", i);
                gameBoardButtons[i][j].putClientProperty("column", j);
                gameBoardButtons[i][j].setFont(new Font("BOLD", Font.BOLD, 35));
                gameBoardButtons[i][j].addActionListener(new ButtonHandler());
                smallPanels[panelI][panelJ].add(gameBoardButtons[i][j]);
            }
        }

    }

    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JButton clicked = (JButton) event.getSource();
            int globalRow = (int) clicked.getClientProperty("row");
            int globalCol = (int) clicked.getClientProperty("column");
            int localRow = globalRow / 3;
            int localCol = globalCol / 3;

            if (clicked.getText() == "") {
                if (player1_turn) {

                    gameBoardButtons[globalRow][globalCol].setForeground(Color.blue);
                    gameBoardButtons[globalRow][globalCol].setText("X");
                    player1_turn = false;
                } else {
                    gameBoardButtons[globalRow][globalCol].setForeground(Color.red);
                    gameBoardButtons[globalRow][globalCol].setText("O");
                    player1_turn = true;
                }
                smallPanels[localRow][localCol].setBackground(Color.BLUE);
            }
        }

    }

}
