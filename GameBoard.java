import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameBoard extends JPanel implements ActionListener {
    public String[][] board = {
            { "", "", "" },
            { "", "", "" },
            { "", "", "" }
    };
    public String winner = "X";
    private List<JButton> buttonArray = new ArrayList<JButton>();
    private JButton[][] buttons = new JButton[3][3];
    private GameState gameState;

    public GameBoard(GameState gameState) {
        setLayout(new GridLayout(3, 3));
        this.gameState = gameState;
    }

    public void ready() {
        for (int i = 0; i < buttonArray.size(); i++) {
            buttons[i / 3][i % 3] = buttonArray.get(i);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (JButton button : buttonArray) {
            if (button.getText().equals(""))
                button.setEnabled(enabled);
        }
        repaint();
    }

    public void add(JButton comp) {
        super.add(comp);
        buttonArray.add(comp);
        comp.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        JButton clicked = (JButton) event.getSource();
        int row = (int) clicked.getClientProperty("row");
        int col = (int) clicked.getClientProperty("column");

        if (clicked.getText() == "") {
            if (gameState.player1_turn) {
                buttons[row][col].setForeground(Color.blue);
                buttons[row][col].setText("X");
                board[row][col] = "X";
            } else {
                buttons[row][col].setForeground(Color.red);
                buttons[row][col].setText("O");
                board[row][col] = "O";
            }
            buttons[row][col].setEnabled(false);
            this.setEnabled(false);
            setBackground(Color.BLUE);
        }
    }

}
