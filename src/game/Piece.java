package game;

public abstract class Piece{
    private final int color;
    private Square location;


    public Piece(int color, Square location) {
        this.color = color;
        this.location = location;
    }

    public int getColor() {
        return this.color;
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

    public abstract boolean canMove(String to);

    public abstract void move(String to);

}
