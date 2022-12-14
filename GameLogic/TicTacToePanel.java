package GameLogic;

import javax.swing.*;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.*;
import java.awt.dnd.DragSource;

public class TicTacToePanel extends TicTacToe implements ActionListener {
    public boolean frameDone = false;
    public JButton[][] buttons = new JButton[3][3];
    private List<JButton> buttonArray = new ArrayList<JButton>();
    private GameState gameState;
    private Boolean isActive = true;

    public TicTacToePanel(GameState gameState, int i, int j) {
        globalLocation = new Coordinates(i, j);
        setLayout(new GridLayout(3, 3));
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setBackground(Color.BLACK);
        this.gameState = gameState;
    }

    public void ready() {
        for (int i = 0; i < buttonArray.size(); i++) {
            buttons[i / 3][i % 3] = buttonArray.get(i);
        }
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

        if (!boardFinished() && clicked.getText() == "" && isActive) {
            if (gameState.player1Turn) {
                clicked.setForeground(crossColor);
                clicked.setText("X");
                board[row][col] = "X";
            } else {
                clicked.setForeground(circleColor);
                clicked.setText("O");
                board[row][col] = "O";
            }
            gameState.player1Turn = !gameState.player1Turn;
            gameState.lastMove = new Coordinates(row, col);
            clicked.setCursor(DragSource.DefaultMoveNoDrop);

            winner = checkWinner();
            repaint();
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (boardFinished()) {
            setBackground(nonActiveColor);
            drawWinner(5);
            SwingUtilities.invokeLater(() -> {
                drawWinner(5);
            });
        } else if (isActive) {
            setBackground(activeColor);
        } else {
            setBackground(Color.BLACK);
        }
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        if (isActive) {
            for (JButton button : buttonArray) {
                if (button.getText().equals("")) {
                    button.setCursor(defaultCursor);
                }
            }
        } else {
            for (JButton button : buttonArray) {
                button.setCursor(DragSource.DefaultMoveNoDrop);
            }
        }
    }

    public boolean getActive() {
        return isActive;
    }

}
