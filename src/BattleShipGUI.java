import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleShipGUI extends JFrame{
    JPanel mainPanel;
    JPanel gridPanel;
    JPanel quitButtonPnl;

    JButton[][] buttons = new JButton[10][10];
    Game game = new Game(buttons);


    JButton quitButton = new JButton("Quit");

    public BattleShipGUI() {



        setTitle("Battle Ship");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createGridPanel();
        createQuitButtonPnl();

        add(mainPanel);
        setVisible(true);
    }

    public void createGridPanel() {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(10,10));
        gridPanel.setPreferredSize(new Dimension(300,300));
        tileListener listener = new tileListener();
        for( int row = 0; row < 10; row++)
            for(int col= 0; col < 10; col++)
            {
                buttons[row][col] = new JButton();
                buttons[row][col].addActionListener(listener);
                gridPanel.add(buttons[row][col]);

            }
        mainPanel.add(gridPanel, BorderLayout.CENTER);
    }

    public void createQuitButtonPnl() {
        quitButtonPnl = new JPanel();
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        quitButtonPnl.add(quitButton);
        mainPanel.add(quitButtonPnl, BorderLayout.SOUTH);
    }

    class tileListener implements ActionListener {
        public void  actionPerformed(ActionEvent e) {
            JButton tile = (JButton) e.getSource();
            int row = -1, col = -1;
            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    if (buttons[r][c] == tile) {
                        row = r;
                        col = c;
                        break;
                    }
                }
            }
            if (row != -1 && col != -1) {
                String result = game.fire(row, col);
                if (result.equals("HIT")) {
                    tile.setText("X");
                    tile.setBackground(Color.RED);
                } else {
                    tile.setText("M");
                    tile.setBackground(Color.YELLOW);
                }
                tile.setEnabled(false);

            }

        }
    }
    public static void main(String[] args) {
        new BattleShipGUI();
    }
}
