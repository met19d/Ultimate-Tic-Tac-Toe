import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class TitlePanel extends JPanel {
    private JLabel turnLabel = new JLabel();
    private JButton menu = new JButton();

    public TitlePanel() {
        turnLabel.setBackground(Color.BLACK);
        turnLabel.setText("Turn: X");
        turnLabel.setForeground(Color.BLUE);
        turnLabel.setFont(new Font("BOLD", Font.BOLD, 50));
        turnLabel.setOpaque(true);
        turnLabel.setHorizontalAlignment(JLabel.CENTER);

        menu.setText("Menu");
        menu.setFont(new Font("BOLD", Font.BOLD, 15));
        menu.setEnabled(true);

        setLayout(new BorderLayout());
        add(turnLabel);
        add(menu, BorderLayout.WEST);
        
    }

    public void setTurn(boolean p1_turn) {
        if (p1_turn) {
            turnLabel.setText("Turn: X");
            turnLabel.setForeground(Color.BLUE);
        } else {
            turnLabel.setText("Turn: O");
            turnLabel.setForeground(Color.RED);
        }
    }

}
