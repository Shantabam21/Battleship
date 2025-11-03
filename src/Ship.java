public class Ship {
    private int length;
    // if true = horizontal, otherwise vertical
    private boolean placement;

    public Ship(int length, boolean placement) {
        this.length = length;
        this.placement = placement;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean isPlacement() {
        return placement;
    }
    public void setPlacement(boolean placement) {
        this.placement = placement;
    }
}
