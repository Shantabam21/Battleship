import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleShipGUI extends JFrame{
    JPanel mainPanel;
    JPanel gridPanel;
    JPanel quitButtonPnl;
    Board board = new Board();
    JButton[][] buttons = new JButton[10][10];
    Game game = new Game();


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

        }
    }
    public static void main(String[] args) {
        new BattleShipGUI();
    }
}
