package game;

public class Queen extends Piece{
    public Queen(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = getLocation().getBoard().getSquareAt(to);
        if(targetLocation == null){
            return false;
        }
        if (getLocation().isAtSameRow(targetLocation) || getLocation().isAtSameColumn(targetLocation) || getLocation().isAtSameDiagonal(targetLocation)) {
            Square[] between = getLocation().getBoard().getSquaresBetween(getLocation(),
                    targetLocation);
            //check every square between location and target location with a for loop
            for (int i = 0; i < between.length-1; i++) {
                //if there is a piece return false
                if(!between[i].isEmpty()){
                    validMove = false;
                    break;
                }
                validMove = true;
            }

            if(between.length == 1){
                validMove = true;
            }
            if(targetLocation.isEmpty() && validMove){
                return true;
            }
            else if(!targetLocation.isEmpty()){
                if(!validMove){
                    return false;
                }
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
        return getColor() == ChessBoard.WHITE ? "Q" : "q";
    }
}
