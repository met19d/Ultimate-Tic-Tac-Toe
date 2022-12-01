import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.*;

public class GameBoard extends JPanel implements ActionListener {
    public String[][] board = {
            { "", "", "" },
            { "", "", "" },
            { "", "", "" }
    };
    public String winner = "";
    public boolean frameDone = false;

    private List<JButton> buttonArray = new ArrayList<JButton>();
    private JButton[][] buttons = new JButton[3][3];
    private GameState gameState;
    private Boolean isActive = true;

    private static Color activeColor = new Color(100, 0, 255);
    private static Color nonActiveColor = Color.GRAY;
    private static Color crossColor = Color.BLUE;
    private static Color circleColor = Color.RED;

    public GameBoard(GameState gameState) {
        setLayout(new GridLayout(3, 3));
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setBackground(Color.BLACK);
        this.gameState = gameState;
    }

    public void ready() {
        for (int i = 0; i < buttonArray.size(); i++) {
            buttons[i / 3][i % 3] = buttonArray.get(i);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (JButton button : buttonArray) {
            if (button.getText().equals(""))
                button.setEnabled(enabled);
        }
        repaint();
    }

    public void add(JButton comp) {
        super.add(comp);
        buttonArray.add(comp);
        comp.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        JButton clicked = (JButton) event.getSource();
        int row = (int) clicked.getClientProperty("row");
        int col = (int) clicked.getClientProperty("column");

        if (!boardFinished() && clicked.getText() == "" && isActive) {

            if (gameState.player1_turn) {
                clicked.setForeground(crossColor);
                clicked.setText("X");
                board[row][col] = "X";
            } else {
                clicked.setForeground(circleColor);
                clicked.setText("O");
                board[row][col] = "O";
            }
            gameState.player1_turn = !gameState.player1_turn;
            gameState.last_move = new Coordinates(row, col);

            winner = checkWinner();
            repaint();
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (boardFinished()) {
            setBackground(nonActiveColor);
            SwingUtilities.invokeLater(() -> {
                if (winner.equals("X"))
                    drawCross(5, crossColor);
                else if (winner.equals("O"))
                    drawCircle(5, circleColor);
            });
        } else if (isActive) {
            setBackground(activeColor);
        } else {
            setBackground(Color.BLACK);
        }
    }

    private void drawCross(int thickness, Color c) {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(c);
        g2d.drawLine(0, 0, getWidth(), getHeight());
        g2d.drawLine(getWidth(), 0, 0, getHeight());
    }

    private void drawCircle(int thickness, Color c) {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawOval(0, 0, getWidth(), getHeight());
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        if (isActive) {
            for (JButton button : buttonArray) {
                button.setBackground(Color.white);
            }
        } else {
            for (JButton button : buttonArray) {
                button.setBackground(Color.gray);
            }
        }
    }

    public boolean boardFinished() {
        return (checkWinner() != null);
    }

    private boolean equals3(String a, String b, String c) {
        return (a.equals(b) && b.equals(c) && !a.equals(""));
    }

    private String checkWinner() {
        String winner = null;
        ArrayList<Integer> available = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    available.add(1);
                }
            }
        }

        // horizontal
        for (int i = 0; i < 3; i++) {
            if (equals3(board[i][0], board[i][1], board[i][2])) {
                winner = board[i][0];
            }
        }

        // Vertical
        for (int i = 0; i < 3; i++) {
            if (equals3(board[0][i], board[1][i], board[2][i])) {
                winner = board[0][i];
            }
        }

        // Diagonal
        if (equals3(board[0][0], board[1][1], board[2][2])) {
            winner = board[0][0];
        }
        if (equals3(board[2][0], board[1][1], board[0][2])) {
            winner = board[2][0];
        }

        if (winner == null && available.size() == 0) {
            return "draw";
        } else {
            return winner;
        }
    }

}
