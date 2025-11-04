public class Ship {
    private String name;
    private int size;
    private int startRow;
    private int startCol;
    private boolean horizontal;
    private int hits;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hits = 0;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
    public int getRow() {
        return startRow;
    }
    public int getCol() {
        return startCol;
    }


    public void placeShip(int row, int col, boolean horizontal) {
        this.startRow = row;
        this.startCol = col;
        this.horizontal = horizontal;
    }

    public boolean isAtLocation(int row, int col) {
        if (horizontal) {
            return row == startRow && col >= startCol && col < startCol + size;
        } else {
            return col == startCol && row >= startRow && row < startRow + size;
        }
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits >= size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}