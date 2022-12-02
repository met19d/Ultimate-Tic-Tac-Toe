package screens.game;

import javax.swing.JPanel;
import javax.swing.*;

import GameLogic.GameState;
import GameLogic.UltimateTicTacToePanel;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Component;

public class GamePanel extends JPanel {
    private UltimateTicTacToePanel gameBoardPanel;
    private TitlePanel titlePanel;
    private GameState masterGame = new GameState();
    private ActionListener swapActionListener;

    public GamePanel(ActionListener swapPanelLogic) {
        swapActionListener = swapPanelLogic;
        setLayout(new BorderLayout());
        reset();
    }

    public void reset() {
        removeAll();

        masterGame = new GameState();
        gameBoardPanel = new UltimateTicTacToePanel(masterGame, this);
        titlePanel = new TitlePanel(masterGame);

        titlePanel.menu.addActionListener(swapActionListener);

        add(titlePanel, BorderLayout.NORTH);
        add(gameBoardPanel);

        revalidate();
        repaint();
    }

    public void checkForWinner() {
        if (!masterGame.winner.equals("")) {
            gameBoardPanel.setEnabled(false);
            for (Component c : gameBoardPanel.getComponents()) {
                c.setEnabled(false);
            }
            for (JButton[] buttons : gameBoardPanel.gameBoardButtons) {
                for (JButton button : buttons) {
                    button.setEnabled(false);
                }
            }
        }
    }

    public void actionPerformed() {
        titlePanel.repaint();
    }
}
