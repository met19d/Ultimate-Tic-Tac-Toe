package screens.game;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameLogic.GameState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitlePanel extends JPanel {
    public JButton menu = new JButton();
    public JButton restart = new JButton();
    public JLabel turnLabel = new JLabel();
    private GameState gameState;

    public TitlePanel(GameState gameState) {
        this.gameState = gameState;

        turnLabel.setBackground(Color.BLACK);
        turnLabel.setText("Turn: X");
        turnLabel.setForeground(Color.BLUE);
        turnLabel.setFont(new Font("BOLD", Font.BOLD, 50));
        turnLabel.setOpaque(false);
        turnLabel.setHorizontalAlignment(JLabel.CENTER);

        menu.setText("Menu");
        menu.setFont(new Font("BOLD", Font.BOLD, 15));
        menu.setEnabled(true);

        restart.setText("Restart");
        restart.setFont(new Font("BOLD", Font.BOLD, 15));
        restart.setEnabled(true);

        setLayout(new BorderLayout());
        add(turnLabel);
        add(menu, BorderLayout.WEST);
        add(restart, BorderLayout.EAST);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState.winner.equals("")) {
            if (gameState.player1Turn) {
                turnLabel.setText("Turn: X");
                turnLabel.setForeground(Color.BLUE);
            } else {
                turnLabel.setText("Turn: O");
                turnLabel.setForeground(Color.RED);
            }
        } else if (gameState.winner.equals("draw")) {
            turnLabel.setText("Draw!");
            turnLabel.setForeground(Color.BLACK);
        } else if (gameState.winner.equals("X")) {
            turnLabel.setText("X Wins!");
            turnLabel.setForeground(Color.BLUE);
        } else if (gameState.winner.equals("O")) {
            turnLabel.setText("O Win!");
            turnLabel.setForeground(Color.RED);
        }
    }

}
