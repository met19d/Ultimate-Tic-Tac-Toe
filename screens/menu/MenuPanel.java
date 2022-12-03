package screens.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import GameLogic.UltimateTicTacToePanel.OpponentType;
import screens.game.GamePanel;

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

        humanVHuman.setText("Play Game (PVP)");
        humanVHuman.setFont(new Font("BOLD", Font.BOLD, 40));
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
        setLayout(new GridLayout(3, 5, 20, 100));

        for (int i = 0; i < 11; i++) {
            add(new JLabel());
        }

        JLabel orText = new JLabel("Or ...");
        orText.setFont(new Font("BOLD", Font.BOLD, 40));
        orText.setHorizontalAlignment(SwingConstants.CENTER);
        orText.setForeground(Color.WHITE);
        add(orText, BorderLayout.PAGE_END, 2);

        easyAI.setText("Easy AI");
        easyAI.addActionListener(swapPanelLogic);
        easyAI.addActionListener((ActionEvent e) -> {
            selectedOpponent = OpponentType.easy;
        });
        add(easyAI, 6);

        advanceAI.setText("Advanced AI");
        advanceAI.addActionListener(swapPanelLogic);
        advanceAI.addActionListener((ActionEvent e) -> {
            selectedOpponent = OpponentType.advanced;
        });
        advanceAI.setHorizontalAlignment(SwingConstants.CENTER);
        add(advanceAI, 8);

        JButton rules = new JButton("Rules ?");
        rules.addActionListener((ActionEvent e) -> {

            JOptionPane.showMessageDialog(null,
                    "Each small 3 × 3 tic-tac-toe board is referred to as a local board, and the larger 3 × 3 board is referred to as the global board.\n\n"
                            +
                            "The game starts with X playing wherever they want in any of the 81 empty spots. \nThis move 'sends' their opponent to its relative location. \nFor example, if X played in the top right square of their local board, then O needs to play next in the local board at the top right of the global board. \nO can then play in any one of the nine available spots in that local board, each move sending X to a different local board.\n\n"
                            +
                            "If a move is played so that it is to win a local board by the rules of normal tic-tac-toe, then the entire local board is marked as a victory for the player in the global board. \nOnce a local board is won by a player or it is filled completely, no more moves may be played in that board. \nIf a player is sent to such a board, then that player may play in any other board. \nGame play ends when either a player wins the global board or there are no legal moves remaining, in which case the game is a draw.",
                    "Ultimate Tic Tac Toe Rules", JOptionPane.PLAIN_MESSAGE);
        });
        add(rules, -1);

    }
}