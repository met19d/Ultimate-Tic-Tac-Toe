import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GamePanel extends JPanel{
    GameBoardPanel gameBoardPanel = new GameBoardPanel();
    TitlePanel titlePanel = new TitlePanel();
    GamePanel() {
        setLayout(new BorderLayout());    
        add(titlePanel, BorderLayout.NORTH);
        add(gameBoardPanel);

    }
}
