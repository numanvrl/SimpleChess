package game;

public class ChessBoard{

    public static final int WHITE = 0;
    public static final int BLACK = 1;
    private boolean isGameStarted;
    private Square[][] table;
    private int turn;


    public ChessBoard() {
        isGameStarted = false;
        turn = 0;

        //assign every square objects to null when ChessBoard object initiated

        table = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                table[row][col] = new Square(row, col, null, this);
            }
        }

    }

    public void drawTable(){
        System.out.println("     A   B   C   D   E   F   G   H");
        System.out.println("   ---------------------------------");
        int count = 8;
        for(int i = 7; i>=0; i--){
            System.out.print(count + "  | ");
            for(int j = 0; j<8; j++){
                if(table[i][j].isEmpty()){
                    System.out.print("  | ");
                }
                else{
                    if(table[i][j].getPiece() == null){
                        System.out.print("  | ");
                    }
                    else {
                        System.out.print(table[i][j].getPiece() + " | ");
                    }
                }
            }
            System.out.print(" "+count);
            count--;
            System.out.println();
            System.out.println("   ---------------------------------");
        }
        System.out.println("     A   B   C   D   E   F   G   H");
        System.out.println();
    }

    public void initialize(){

        //Create the squares for every piece in the game and put them on the correct places on the table

        for(int i = 7; i >= 0; i--){
            Square whitePawnSquare = new Square(1, i, null, this);
            Square blackPawnSquare = new Square(6, i, null, this);
            table[1][i] = whitePawnSquare;
            table[6][i] = blackPawnSquare;
            table[1][i].setPiece(new Pawn(WHITE, whitePawnSquare, true));
            table[6][i].setPiece(new Pawn(BLACK, blackPawnSquare, true));
        }

        Square c1 = new Square(0, 2, null, this);
        Square f1 = new Square(0, 5, null, this);
        Square c8 = new Square(7, 2, null, this);
        Square f8 = new Square(7, 5, null, this);

        table[0][2] = c1;
        table[0][5] = f1;
        table[7][2] = c8;
        table[7][5] = f8;

        table[0][2].setPiece(new Bishop(WHITE,c1));
        table[0][5].setPiece(new Bishop(WHITE,f1));
        table[7][2].setPiece(new Bishop(BLACK,c8));
        table[7][5].setPiece(new Bishop(BLACK,f8));

        Square a1 = new Square(0, 0, null, this);
        Square h1 = new Square(0, 7, null, this);
        Square a8 = new Square(7, 0, null, this);
        Square h8 = new Square(7, 7, null, this);

        table[0][0] = a1;
        table[0][7] = h1;
        table[7][0] = a8;
        table[7][7] = h8;

        table[0][0].setPiece(new Rook(WHITE,a1));
        table[0][7].setPiece(new Rook(WHITE,h1));
        table[7][0].setPiece(new Rook(BLACK,a8));
        table[7][7].setPiece(new Rook(BLACK,h8));

        Square b1 = new Square(0, 1, null, this);
        Square g1 = new Square(0, 6, null, this);
        Square b8 = new Square(7, 1, null, this);
        Square g8 = new Square(7, 6, null, this);

        table[0][1] = b1;
        table[0][6] = g1;
        table[7][1] = b8;
        table[7][6] = g8;

        table[0][1].setPiece(new Knight(WHITE,b1));
        table[0][6].setPiece(new Knight(WHITE,g1));
        table[7][1].setPiece(new Knight(BLACK,b8));
        table[7][6].setPiece(new Knight(BLACK,g8));

        Square d1 = new Square(0, 3, null, this);
        Square d8 = new Square(7, 3, null, this);

        table[0][3] = d1;
        table[7][3] = d8;

        table[0][3].setPiece(new Queen(WHITE,d1));
        table[7][3].setPiece(new Queen(BLACK,d8));

        Square e1 = new Square(0, 4, null, this);
        Square e8 = new Square(7, 4, null, this);

        table[0][4] = e1;
        table[7][4] = e8;

        table[0][4].setPiece(new King(WHITE,e1));
        table[7][4].setPiece(new King(BLACK,e8));

    }

    public Square getSquareAt(String to) {

        // returns the square at the given coordinates, changes the column characters to an integer with a helper function called columnCharToIndex.
        // returns an error message if given entry is wrong or out of bounds of table.

        try {
            char columnChar = to.charAt(0);
            int columnIndex = columnCharToIndex(columnChar);
            int rowIndex = Integer.parseInt(String.valueOf(to.charAt(1))) - 1;
            return table[rowIndex][columnIndex];
        }
        catch (Exception e){
            System.out.println("Invalid Entry");
            return null;
        }
    }

    public int columnCharToIndex(char s){

        // assigns the column names to an int

        int result;
        switch(s){
            case 'a','A':
                result = 0;
                break;
            case 'b','B':
                result = 1;
                break;
            case 'c','C':
                result = 2;
                break;
            case 'd','D':
                result = 3;
                break;
            case 'e','E':
                result = 4;
                break;
            case 'f','F':
                result = 5;
                break;
            case 'g','G':
                result = 6;
                break;
            case 'h','H':
                result = 7;
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }

    public Square[] getSquaresBetween(Square location, Square targetLocation) {

        // get the squares between two square objects horizontally, vertically or diagonally,
        // returns a square array that excludes the first location and includes targetLocation.

        Square[] squaresBetween;
        Square nextSquare = table[location.getRow()][location.getColumn()]; // assign the first square to a variable, but it's not included at the array
        int rowDistance = location.getRowDistance(targetLocation);
        int columnDistance = location.getColumnDistance(targetLocation);
        squaresBetween = new Square[Math.abs(rowDistance) + Math.abs(columnDistance)]; // get the numbers of squares between two target

        // if the target is on diagonal, call getDiagonalDistance method to find # of squares between
        if(location.isAtSameDiagonal(targetLocation)){
            squaresBetween = new Square[location.getDiagonalDistance(targetLocation)];
        }
        int numberOfSquaresBetween = squaresBetween.length;

        // get the next square according to, if target is in same row, same column or same diagonal, otherwise assign null

        for (int i = 0; i < numberOfSquaresBetween; i++) {
            if(location.isAtSameColumn(targetLocation)) {
                nextSquare = table[nextSquare.getRow() + (rowDistance / numberOfSquaresBetween)][nextSquare.getColumn()];
            }
            else if(location.isAtSameRow(targetLocation)){
                nextSquare = table[nextSquare.getRow()][nextSquare.getColumn() + (columnDistance / numberOfSquaresBetween)];
            }
            else if(location.isAtSameDiagonal(targetLocation)){
                nextSquare = table[nextSquare.getRow() + (rowDistance / Math.abs(rowDistance))]
                        [nextSquare.getColumn() + (columnDistance / Math.abs(columnDistance))];
            }
            else{
                nextSquare = null;
            }
            squaresBetween[i] = nextSquare;

        }
        return squaresBetween;
    }

    public boolean isGameEnded() {

        // count every piece's color on the board and assign the number to a value.

        int blackPiece = 0;
        int whitePiece = 0;
        for(int i=0;i<8;i++){
            for(int j=0; j<8; j++){
                Square currentSquare = table[i][j];
                if(currentSquare.isEmpty()){
                    continue;
                }
                else if(currentSquare.getPiece().getColor() == BLACK){
                    blackPiece++;
                }
                else if(currentSquare.getPiece().getColor() == WHITE ){
                    whitePiece++;
                }
            }
        }

        // if one of the counter values is equal to 0 the game is ended, return true.
        // else, game is continuing, return false.

        if(blackPiece == 0 || whitePiece == 0){
            return true;
        }
        return false;
    }

    public boolean isWhitePlaying() {
        if(turn == WHITE) {
            return true;
        }
        return false;
    }

    public Piece getPieceAt(String from) {

        // return the piece at the targeted square. If square is empty return null.

        Square square = getSquareAt(from);
        if(square == null){
            return null;
        }
        return square.getPiece();
    }

    public void nextPlayer() {

        // simple converter for if the turn was on White change it to Black and vice versa

        if(turn == WHITE){
            turn = BLACK;
        }
        else{
            turn = WHITE;
        }
    }

    @Override
    public String toString() {

        // if game is just starting initialize every piece then draw the table accordingly

        if(!isGameStarted) {
            initialize();
            isGameStarted = true;
        }
        drawTable();
        return "";
    }
}
