import javax.swing.*;

public class Game {
    private Board board;
    private int missCounter;
    private int strikeCounter;
    private int totalMisses;
    private int totalHits;
    private JButton[][] buttons;

    public Game(JButton[][] buttons) {
        board = new Board(buttons);
        this.buttons = buttons;
        missCounter = 0;
        strikeCounter = 0;
        totalMisses = 0;
        totalHits = 0;
    }

    public Board getBoard() {
        return board;
    }

    public String fire(int row, int col) {
        if (board.hasShip(row, col)) {
            board.markHit(row, col);
            missCounter = 0;
            totalHits++;
            Ship sunkShip = checkSunkShip(row, col);
            if (sunkShip != null) {
                if (totalHits == 17) {
                    JOptionPane.showMessageDialog(null, "You sank the final ship! You win!");
                    int response = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
                    if (response == JOptionPane.YES_OPTION) {
                        missCounter = 0;
                        strikeCounter = 0;
                        totalMisses = 0;
                        totalHits = 0;
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                buttons[i][j].setText("");
                                buttons[i][j].setBackground(null);

                                buttons[i][j].setEnabled(true);
                            }
                        }

                    } else {
                        System.exit(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, sunkShip.getName() + " is sunk!");
                }
            }
            return "HIT";
        } else {
            board.markMiss(row, col);
            missCounter++;
            totalMisses++;
            if (missCounter >= 5) {
                strikeCounter++;
                missCounter = 0;
                if (strikeCounter >= 3) {
                    JOptionPane.showMessageDialog(null, "You lost! You hit 3 strikes!");
                    int response = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
                    if (response == JOptionPane.YES_OPTION) {
                       missCounter = 0;
                       strikeCounter = 0;
                       totalMisses = 0;
                       totalHits = 0;
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                buttons[i][j].setText("");
                                buttons[i][j].setBackground(null);
                                buttons[i][j].setEnabled(true);


                            }
                        }

                    } else {
                        System.exit(0);
                    }
                }
            }
            return "MISS";
        }
    }

    private Ship checkSunkShip(int row, int col) {
        for (Ship ship : board.getShips()) {
            if (ship.isAtLocation(row, col) && ship.isSunk()) return ship;
        }
        return null;
    }

    public int getMissCounter() {
        return missCounter;
    }

    public int getStrikeCounter() {
        return strikeCounter;
    }

    public int getTotalMisses() {
        return totalMisses;
    }

    public int getTotalHits() {
        return totalHits;
    }
}