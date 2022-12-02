package screens.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

    public MenuPanel(ActionListener swapPanelLogic) {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(2, 1));

        add(new PlayGamePanel(swapPanelLogic));
        add(new PlayAIPanel());
    }

    public void reset() {
        revalidate();
        repaint();
    }
}

class PlayGamePanel extends JPanel {
    private JButton humanVHuman = new JButton();
    private JLabel gameTitle = new JLabel();

    public PlayGamePanel(ActionListener swapPanelLogic) {
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        gameTitle.setFont(new Font("BOLD", Font.BOLD, 50));
        gameTitle.setText("Ultimate TIC-TAC-TOE");
        gameTitle.setForeground(Color.GREEN);

        humanVHuman.setText("Play Game");
        add(gameTitle, BorderLayout.CENTER);
        add(humanVHuman, BorderLayout.PAGE_END);

        humanVHuman.addActionListener(swapPanelLogic);
    }
}

class PlayAIPanel extends JPanel {
    private JButton humanVAI = new JButton();

    public PlayAIPanel() {
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        humanVAI.setText("Play against an AI");
        add(humanVAI, BorderLayout.PAGE_START);
    }
}