package game;
public class Square {
    private int column;
    private int row;
    private Piece piece;
    private ChessBoard board;


    public Square(int row, int column, Piece piece, ChessBoard board) {
        this.column = column;
        this.row = row;
        this.piece = piece;
        this.board = board;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isEmpty() {
        if (piece == null) {
            return true;
        }
        return false;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public int getRowDistance(Square location) {
        return location.row - row;
    }

    public int getColumnDistance(Square location) {
        return location.column - column;
    }

    public int getDiagonalDistance(Square location){
        return ((Math.abs(getRowDistance(location)) + Math.abs(getColumnDistance(location))) / 2);
    }

    public boolean isAtSameColumn(Square targetLocation) {
        if (column == targetLocation.column) {
            return true;
        }
        return false;
    }

    public boolean isAtSameRow(Square targetLocation) {
        if(row == targetLocation.row){
            return true;
        }
        return false;
    }

    public boolean isAtSameDiagonal(Square targetLocation){
        // to find if the target location is on the same diagonal with our location we can use this equation:
        // row + column == targetRow + targetColumn || row - column == targetRow - targetColumn

        if(getRow() + getColumn() == targetLocation.getRow() + targetLocation.getColumn() ||
           Math.abs(getRow() - getColumn()) == Math.abs(targetLocation.getRow() - targetLocation.getColumn())){
            return true;
        }
        return false;
    }

    public boolean isAtSameLMove(Square targetLocation){
        // to find if our knight can hop onto the target location, first we must check if the row and column is different from the target location
        // because after the hope it must change. After that we must check whether the traveled squares are equal to 3
        if(getRow() != targetLocation.getRow() && getColumn() != targetLocation.getColumn()){
            if(Math.abs(getRowDistance(targetLocation)) + Math.abs(getColumnDistance(targetLocation)) == 3){
                return true;
            }
        }
        return false;
    }

    public boolean isNeighborColumn(Square targetLocation) {
        if ((getColumnDistance(targetLocation) == 1) || getColumnDistance(targetLocation) == -1) {
            return true;
        }
        return false;
    }

    public boolean isAtLastRow(int color) {
        if (color == getBoard().WHITE && row == 7) {
            return true;
        } else if (color == getBoard().BLACK && row == 0) {
            return true;
        }
        return false;
    }

    public void clear() {
        setPiece(null);
    }

    public void putNewQueen(int color) {
        if (color == getBoard().WHITE) {
            piece = new Queen(color, getPiece().getLocation());
        }
        else if(color == getBoard().BLACK){
            piece = new Queen(color, getPiece().getLocation());
        }
    }


}
