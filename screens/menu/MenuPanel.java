package screens.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GameLogic.UltimateTicTacToePanel.OpponentType;
import screens.game.GamePanel;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {
    private GamePanel gamePanel;
    private PlayGamePanel playGamePanel;
    private PlayAIPanel playAIPanel;

    public MenuPanel(GamePanel gamePanel, ActionListener swapPanelLogic) {
        this.gamePanel = gamePanel;
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(2, 1));

        playGamePanel = new PlayGamePanel(swapPanelLogic);
        playAIPanel = new PlayAIPanel(swapPanelLogic);

        add(playGamePanel, BorderLayout.NORTH);
        add(playAIPanel, BorderLayout.CENTER);
    }

    public void reset() {
        playGamePanel.selectedOpponent = null;
        playAIPanel.selectedOpponent = null;
        revalidate();
        repaint();
    }

    public void opponentOption() {
        if (playGamePanel.selectedOpponent != null) {
            gamePanel.gameBoardPanel.opponentType = OpponentType.human;
        } else {
            gamePanel.gameBoardPanel.opponentType = playAIPanel.selectedOpponent;
        }
    }
}

class PlayGamePanel extends JPanel {
    public OpponentType selectedOpponent = null;
    private JButton humanVHuman = new JButton();
    private JLabel gameTitle = new JLabel();

    public PlayGamePanel(ActionListener swapPanelLogic) {
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        gameTitle.setFont(new Font("BOLD", Font.BOLD, 50));
        gameTitle.setText("Ultimate TIC-TAC-TOE");

        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setForeground(Color.GREEN);

        humanVHuman.setText("Play Game");
        add(gameTitle, BorderLayout.CENTER);
        add(humanVHuman, BorderLayout.PAGE_END);

        humanVHuman.addActionListener(swapPanelLogic);
        humanVHuman.addActionListener((ActionEvent e) -> {
            selectedOpponent = OpponentType.human;
        });
    }
}

class PlayAIPanel extends JPanel {
    public OpponentType selectedOpponent = null;
    private JButton easyAI = new JButton();
    private JButton advanceAI = new JButton();

    public PlayAIPanel(ActionListener swapPanelLogic) {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(2, 1, 5, 10));

        easyAI.setText("Easy AI");
        easyAI.addActionListener(swapPanelLogic);
        easyAI.addActionListener((ActionEvent e) -> {
            selectedOpponent = OpponentType.easy;
        });
        add(easyAI);

        advanceAI.setText("Advanced AI");
        advanceAI.addActionListener(swapPanelLogic);
        advanceAI.addActionListener((ActionEvent e) -> {
            selectedOpponent = OpponentType.advanced;
        });
        advanceAI.setHorizontalAlignment(SwingConstants.CENTER);
        add(advanceAI);

    }
}