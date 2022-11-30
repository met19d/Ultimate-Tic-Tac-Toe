import javax.swing.JFrame;

public class GameBoard {
    public static void main(String[] args) {
        GameBoardFrame gameBoardFrame = new GameBoardFrame();
        gameBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoardFrame.setSize(600, 600);
        gameBoardFrame.setVisible(true);
    }
}
