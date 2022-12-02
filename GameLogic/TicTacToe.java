package GameLogic;

import javax.swing.*;

import java.awt.Color;
import java.awt.*;

public class TicTacToe extends JPanel {
    public String[][] board = {
            { "", "", "" },
            { "", "", "" },
            { "", "", "" }
    };
    public String winner = "";
    public Coordinates globalLocation;

    public final static Color activeColor = new Color(100, 0, 255);
    public final static Color nonActiveColor = Color.GRAY;
    public final static Color crossColor = Color.BLUE;
    public final static Color circleColor = Color.RED;

    public final static Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    public String getState(int row, int column) {
        return board[row][column];
    }

    public void playMove(int row, int column, String play) {
        board[row][column] = play;
    }

    public void drawWinner() {
        if (winner.equals("X"))
            drawCross(5, crossColor);
        else if (winner.equals("O"))
            drawCircle(5, circleColor);
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

    private boolean equals3(String a, String b, String c) {
        return (a.equals(b) && b.equals(c) && !a.equals(""));
    }

    public boolean boardFinished() {
        return (!checkWinner().equals(""));
    }

    public String checkWinner() {
        String winner = "";
        boolean validMovesLeft = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    validMovesLeft = true;
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

        if (winner.equals("") && !validMovesLeft) {
            return "draw";
        } else {
            return winner;
        }
    }
}
