package lk.ijse.connect4game.service;

public interface Board {
    public static int NUM_OF_ROWS = 5;
    public static int NUM_OF_COLS = 6;
    Piece[][] getPieces();
    public BoardUI getBoardUI();

    public int findNextAvailableSpot(int col);

    public boolean isLegalMove(int col);

    public boolean existLegalMoves();

    public void updateMove(int col, Piece move);
    void updateMove(int col,int row,Piece move);
    public Winner findWinner();
}
