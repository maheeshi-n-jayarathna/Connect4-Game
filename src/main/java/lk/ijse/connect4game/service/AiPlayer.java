package lk.ijse.connect4game.service;

public class AiPlayer extends Player {
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }
    @Override
    public void movePiece(int col) {

//        do {
//            col = (int) (Math.random() * 6);
//        } while (!board.isLegalMove(col));
//        board.updateMove(col, Piece.GREEN);

        Piece[][] tempBoard=board.getPieces();
        int max=Integer.MIN_VALUE;
        int row=0;
        for (int c=0;c<6;c++){
            for (int r=0;r<5;r++){
                if (tempBoard[c][r]==Piece.EMPTY){
                    tempBoard[c][r]=Piece.GREEN;
                    int returnValue=minimax(tempBoard,0,false);
                    tempBoard[c][r]=Piece.EMPTY;
                    if (max<returnValue){
                        max=returnValue;
                        col=c;
                        row=r;
                    }
                }
            }
        }

        board.updateMove(col,row,Piece.GREEN);
        board.getBoardUI().update(col, false);
        if (board.findWinner() == null) {
            if (!board.existLegalMoves()) {
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        } else {
            board.getBoardUI().notifyWinner(board.findWinner());
        }
    }

    public int minimax(Piece[][] tempBoard, int depth, boolean maximisingPLayer) {
        String winner=getWinner(tempBoard);
        if (winner != null || depth==4){
            if (winner==null){
                return 0;
            }if (winner.equals("Player")){
                return -10;
            }if (winner.equals("Ai")){
                return 10;
            }if (winner.equals("Tie")){
                return 0;
            }
        }
        if(maximisingPLayer){
            int max=Integer.MIN_VALUE;
            for(int c=0;c<6;c++){
                for(int r=0;r<5;r++){
                    if (tempBoard[c][r]==Piece.EMPTY){
                        tempBoard[c][r]=Piece.GREEN;
                        int score=minimax(tempBoard,depth+1,false);
                        tempBoard[c][r]=Piece.EMPTY;
                        max=Math.max(score,max);
                    }
                }
            }
            return max;
        }else{
            int min=Integer.MAX_VALUE;
            for(int c=0;c<6;c++){
                for(int r=0;r<5;r++){
                    if (tempBoard[c][r]==Piece.EMPTY){
                        tempBoard[c][r]=Piece.BLUE;
                        int score=minimax(tempBoard,depth+1,true);
                        tempBoard[c][r]=Piece.EMPTY;
                        min=Math.min(score,min);
                    }
                }
            }
            return min;
        }
    }
    public String getWinner(Piece[][] tempBoard){
        String winner=null;
        for (int r=0;r<5;r++){
            for (int c=0;c<3;c++){
                if(tempBoard[c][r]==Piece.BLUE && tempBoard[c+1][r]==Piece.BLUE && tempBoard[c+2][r]==Piece.BLUE && tempBoard[c+3][r]==Piece.BLUE){
                    winner="Player";
                }else if(tempBoard[c][r]==Piece.GREEN && tempBoard[c+1][r]==Piece.GREEN && tempBoard[c+2][r]==Piece.GREEN && tempBoard[c+3][r]==Piece.GREEN){
                    winner="Ai";
                }
            }
        }
        for (int c=0;c<6;c++){
            for (int r=0;r<2;r++){
                if(tempBoard[c][r]==Piece.BLUE && tempBoard[c][r+1]==Piece.BLUE && tempBoard[c][r+2]==Piece.BLUE && tempBoard[c][r+3]==Piece.BLUE){
                    winner="Player";
                }else if(tempBoard[c][r]==Piece.GREEN && tempBoard[c][r+1]==Piece.GREEN && tempBoard[c][r+2]==Piece.GREEN && tempBoard[c][r+3]==Piece.GREEN){
                    winner="Ai";
                }
            }
        }
        int emptySpot=0;
        for (int c=0;c<6;c++) {
            for (int r = 0; r < 5; r++) {
                if (tempBoard[c][r]==Piece.EMPTY){
                    emptySpot++;
                }
            }
        }
        if (winner==null && emptySpot==0){
            return "Tie";
        }else{
            return winner;
        }
    }
}
