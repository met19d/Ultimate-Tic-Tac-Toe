import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import screens.game.GamePanel;
import screens.menu.MenuPanel;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

public class UltimateTicTacToe {
    public static JFrame frame;
    public static GamePanel gamePanel;
    public static MenuPanel mainMenu;

    public static void createGame() {
        frame = new JFrame("Ultimate Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel = new GamePanel((ActionEvent e) -> {
            frame.remove(gamePanel);
            frame.add(mainMenu);
            mainMenu.reset();
        });

        mainMenu = new MenuPanel(gamePanel, (ActionEvent e) -> {
            frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            frame.remove(mainMenu);
            frame.add(gamePanel);
            gamePanel.reset();
            mainMenu.opponentOption();
            SwingUtilities.invokeLater(() -> {
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            });
        });

        frame.add(mainMenu);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createGame();
        });

    }
}
