import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitlePanel extends JPanel {
    private JLabel turnLabel = new JLabel();
    private JButton menu = new JButton();
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

        setLayout(new BorderLayout());
        add(turnLabel);
        add(menu, BorderLayout.WEST);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState.player1Turn) {
            turnLabel.setText("Turn: X");
            turnLabel.setForeground(Color.BLUE);
        } else {
            turnLabel.setText("Turn: O");
            turnLabel.setForeground(Color.RED);
        }
    }

}
