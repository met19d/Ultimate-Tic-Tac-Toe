import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class UltimateTicTacToe {
    public static void createGame() {
        JFrame frame = new JFrame("Ultimate Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createGame();
        });

    }

}
