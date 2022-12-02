import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import screens.game.GamePanel;
import screens.menu.MenuPanel;

public class UltimateTicTacToe {
    public static void createGame() {
        JFrame frame = new JFrame("Ultimate Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        GamePanel gamePanel = new GamePanel();
        MenuPanel mainMenu = new MenuPanel();
        // frame.add(gamePanel);
        frame.add(mainMenu);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createGame();
        });

    }

}
