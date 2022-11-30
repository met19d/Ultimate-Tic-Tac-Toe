import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;


public class GameBoardFrame extends JFrame{

    private boolean player1_turn = true;
    private JButton[][] buttons;

    public GameBoardFrame()
    {
        for (int i = 0; i < 9; i++) {
            
        }
        setLayout(new GridLayout(9, 9, 2, 2));
        buttons = new JButton[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].putClientProperty("row", i);
                buttons[i][j].putClientProperty("column", j);
                buttons[i][j].setFont(new Font("BOLD", Font.BOLD, 35));
                buttons[i][j].addActionListener(new ButtonHandler());
                add(buttons[i][j]);
            }
        }
        setBackground(Color.BLACK);

    }
    private class ButtonHandler implements ActionListener 
    {
    
        public void actionPerformed( ActionEvent event )
        {
            JButton clicked = (JButton)event.getSource();
            int globalRow = (int)clicked.getClientProperty("row");
            int globalCol = (int)clicked.getClientProperty("column");
            int localRow = localize(globalRow);
            int localCol = localize(globalCol);
            
            if(clicked.getText() == ""){
                if(player1_turn) {
                    
                    buttons[globalRow][globalCol].setForeground(Color.blue);
                    buttons[globalRow][globalCol].setText("X");
                    player1_turn = false;
                }   
                else {
                    buttons[globalRow][globalCol].setForeground(Color.red);
                    buttons[globalRow][globalCol].setText("O");
                    player1_turn = true;
                }
            }
        }
 
    }

    //Returns coordinates in respect to the individual game board
    int localize(int x) {
        if(x > 2) {
            if(x > 5)
                return x - 6;
            else
                return x - 3;
        }
        return x;
    }
  }

