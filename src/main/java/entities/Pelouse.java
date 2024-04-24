package entities;

public class Pelouse {
    private int x;
    private int y;

    public Pelouse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= this.x && y >= 0 && y <= this.y;
    }
}
