package game;

public class Knight extends Piece{

    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {

        // for knight piece squares between is useless because it just jumps over other pieces
        // so only condition to move it or not is if the given target location is at a LShape movement away
        // or if it is empty or not.

        boolean validMove = false;
        Square targetLocation = getLocation().getBoard().getSquareAt(to);
        if(targetLocation == null){
            return false;
        }
        // check if the targeted location is L Shaped movement away
        if (getLocation().isAtSameLMove(targetLocation)) {
            if(targetLocation.isEmpty()){
                return true;
            }
            else if(!targetLocation.isEmpty()){
                if (getColor() == ChessBoard.WHITE) {
                    validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                            ChessBoard.BLACK;
                } else if (getColor() == ChessBoard.BLACK) {
                    validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                            ChessBoard.WHITE;
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
        return getColor() == ChessBoard.WHITE ? "N" : "n";
    }
}
