package lk.ijse.connect4game.service;

public class BoardImpl implements  Board {

    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardUI getBoardUI() {
        return boardUI;
    }
    @Override
    public Piece[][] getPieces() {
        return pieces;
    }

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        for (int c=0;c<NUM_OF_COLS;c++){
            for (int r=0;r<NUM_OF_ROWS;r++){
                pieces[c][r]=Piece.EMPTY;
            }
        }
    }
    @Override
    public int findNextAvailableSpot(int col){
        for(int i = 0; i<5; i++){
            if(pieces[col][i]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        if (findNextAvailableSpot(col) == -1){
            return false;
        }else{
            return true;}
    }

    @Override
    public boolean existLegalMoves() {
        for (int c=0;c<NUM_OF_COLS;c++){
            for (int r=0;r<NUM_OF_ROWS;r++){
                if(pieces[c][r]==Piece.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)]=move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {
        for (int r=0;r<NUM_OF_ROWS;r++){
            for (int c=0;c<3;c++){
                if(pieces[c][r]==Piece.BLUE && pieces[c+1][r]==Piece.BLUE && pieces[c+2][r]==Piece.BLUE && pieces[c+3][r]==Piece.BLUE){
                    return new Winner(Piece.BLUE,c,r,c+3,r);
                }else if(pieces[c][r]==Piece.GREEN && pieces[c+1][r]==Piece.GREEN && pieces[c+2][r]==Piece.GREEN && pieces[c+3][r]==Piece.GREEN){
                    return new Winner(Piece.GREEN,c,r,c+3,r);
                }
            }
        }
        for (int c=0;c<NUM_OF_COLS;c++){
            for (int r=0;r<2;r++){
                if(pieces[c][r]==Piece.BLUE && pieces[c][r+1]==Piece.BLUE && pieces[c][r+2]==Piece.BLUE && pieces[c][r+3]==Piece.BLUE){
                    return new Winner(Piece.BLUE,c,r,c,r+3);
                }else if(pieces[c][r]==Piece.GREEN && pieces[c][r+1]==Piece.GREEN && pieces[c][r+2]==Piece.GREEN && pieces[c][r+3]==Piece.GREEN){
                    return new Winner(Piece.GREEN,c,r,c,r+3);
                }
            }
        }
        return null;
    }
}
