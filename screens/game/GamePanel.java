package screens.game;

import javax.swing.JPanel;

import GameLogic.GameState;
import GameLogic.UltimateTicTacToePanel;

import java.awt.BorderLayout;

public class GamePanel extends JPanel {
    private UltimateTicTacToePanel gameBoardPanel;
    private TitlePanel titlePanel;
    private GameState masterGame = new GameState();

    public GamePanel() {
        gameBoardPanel = new UltimateTicTacToePanel(masterGame, this);
        titlePanel = new TitlePanel(masterGame);

        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(gameBoardPanel);

    }

    public void actionPerformed() {
        titlePanel.repaint();
    }

}
