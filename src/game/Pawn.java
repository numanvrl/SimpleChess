package game;

public class Pawn extends Piece {
    boolean initialLocation;

    public Pawn(int color, Square location, boolean initialLocation) {
        super(color, location);
        this.initialLocation = initialLocation;
    }
    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = getLocation().getBoard().getSquareAt(to);
        if(targetLocation == null){
            return false;
        }
        int rowDistance = targetLocation.getRowDistance(getLocation());
        if (this.getLocation().isAtSameColumn(targetLocation)) {
            if (getColor() == ChessBoard.WHITE && rowDistance < 0 && rowDistance >= -2) {
                if (rowDistance == -2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = getLocation().getBoard().getSquaresBetween(getLocation(),
                                targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                        initialLocation = false;
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
                return validMove;
            } else if (getColor() == ChessBoard.BLACK && rowDistance > 0 && rowDistance <= 2) {
                if (rowDistance == 2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between =getLocation().getBoard().getSquaresBetween(getLocation(),
                                targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                        initialLocation = false;
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
            }
        } else if (this.getLocation().isNeighborColumn(targetLocation)) {
            if (getColor() == ChessBoard.WHITE && rowDistance == -1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                        ChessBoard.BLACK;
            } else if (getColor() == ChessBoard.BLACK && rowDistance == 1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                        ChessBoard.WHITE;
            }
        }
        return validMove;
    }
    @Override
    public void move(String to) {
        Square targetLocation = getLocation().getBoard().getSquareAt(to);
        //promoteToQueen
        if (targetLocation.isAtLastRow(getColor())) {
            targetLocation.putNewQueen(getColor());
        } else {
            targetLocation.setPiece(this);
        }
        //clear previous location
        getLocation().clear();
        //update current location
        setLocation(targetLocation);
        getLocation().getBoard().nextPlayer();
    }
    @Override
    public String toString() {
        return getColor() == ChessBoard.WHITE ? "P" : "p";
    }
}