package game;

public class King extends Piece{

    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = getLocation().getBoard().getSquareAt(to);
        if(targetLocation == null){
            return false;
        }
        // get the distances for row, column and diagonal distance
        int rowDistance = Math.abs(getLocation().getRowDistance(targetLocation));
        int columnDistance = Math.abs(getLocation().getColumnDistance(targetLocation));
        int diagonalDistance = Math.abs(getLocation().getDiagonalDistance(targetLocation));
        if (getLocation().isAtSameRow(targetLocation) || getLocation().isAtSameColumn(targetLocation) || getLocation().isAtSameDiagonal(targetLocation)) {
            if(rowDistance == 1 || columnDistance == 1 || diagonalDistance == 1) {
                if (targetLocation.isEmpty()) {
                    return true;
                } else if (!targetLocation.isEmpty()) {
                    if (getColor() == ChessBoard.WHITE) {
                        validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                                ChessBoard.BLACK;
                    } else if (getColor() == ChessBoard.BLACK) {
                        validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                                ChessBoard.WHITE;
                    }
                }
            }
        }
        return validMove;
    }

    @Override
    public void move(String to) {
        Square targetLocation = getLocation().getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        //clear previous location
        getLocation().clear();
        //update current location
        setLocation(targetLocation);
        getLocation().getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        return getColor() == ChessBoard.WHITE ? "K" : "k";
    }
}
