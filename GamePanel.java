import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GamePanel extends JPanel {
    private GameBoardPanel gameBoardPanel;
    private TitlePanel titlePanel;
    private GameState masterGame = new GameState();

    GamePanel() {
        gameBoardPanel = new GameBoardPanel(masterGame, this);
        titlePanel = new TitlePanel(masterGame);

        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(gameBoardPanel);

    }

    public void actionPerformed() {
        titlePanel.repaint();
    }

}
