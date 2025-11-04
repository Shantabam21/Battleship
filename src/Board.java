import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int[][] grid;
    private ArrayList<Ship> ships;
    private Random rand;
    JButton[][] buttons;

    public Board(JButton[][] buttons) {
        grid = new int[10][10];
        ships = new ArrayList<>();
        rand = new Random();
        this.buttons = buttons;
        placeAllShips();

    }

    private void placeAllShips() {
        int[] shipSizes = {5, 4, 3, 3, 2};
        for (int i = 0; i < shipSizes.length; i++) {
            placeShipRandomly((new Ship("Ship" + (i + 1), shipSizes[i])));
        }
    }

    private void placeShipRandomly(Ship ship) {
        boolean placed = false;
        while (!placed) {
            boolean horizontal = rand.nextBoolean();
            int row = rand.nextInt(10);
            int col = rand.nextInt(10);

            if (canPlaceShip(ship.getSize(), row, col, horizontal)) {
                ship.placeShip(row, col, horizontal);
                markShipOnGrid(ship);
                ships.add(ship);
                placed = true;
            }
        }
    }

    private boolean canPlaceShip(int size, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + size > 10) return false;
            for (int c = col; c < col + size; c++)
                if (grid[row][c] != 0)
                    return false;
        } else {
            if (row + size > 10) return false;
            for (int r = row; r < row + size; r++)
                if (grid[r][col] != 0)
                    return false;
        }
        return true;
    }

    private void markShipOnGrid(Ship ship) {

        if (ship != null) {
            if (ship.isAtLocation(0,0)) {}
        }

        if (ship.isHorizontal()) {
            for (int c = ship.getCol(); c < ship.getCol() + ship.getSize(); c++) {
                grid[ship.getRow()][c] = 1;
            }
        } else {
            for (int r = ship.getRow(); r < ship.getRow() + ship.getSize(); r++) {
                grid[r][ship.getCol()] = 1;
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public boolean hasShip(int row, int col) {
        return grid[row][col] == 1;
    }

    public void markHit(int row, int col) {
        grid[row][col] = 2;
        // register hit on the ship
        for (Ship ship : ships) {
            if (ship.isAtLocation(row, col)) ship.hit();
        }
    }

    public void markMiss(int row, int col) {
        grid[row][col] = 3;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}