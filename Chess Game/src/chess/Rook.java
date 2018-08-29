package chess;

public class Rook extends ChessPiece {
	
    Rook(Player player) {
        super(player);
     
    }

    public String type() {
        return "Rook";
    }
   

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if (super.isValidMove(move, board)) {
            if (Math.abs(move.fromRow - move.toRow) > 0
                    && move.fromColumn == move.toColumn) {
                for (int row = move.fromRow - 1; row > move.toRow; row--) {
                    if (board[row][move.fromColumn] != null) {
                        return false;
                    } 
                }
                for (int row = move.fromRow + 1; row < move.toRow; row++) {
                    if (board[row][move.fromColumn] != null) {
                        return false;
                    }
                }
                return true;
            } else if (Math.abs(move.fromColumn - move.toColumn) > 0
                    && move.fromRow == move.toRow) {
                for (int col = move.fromColumn + 1; col < move.toColumn; col++) {
                    if (board[move.fromRow][col] != null) {
                        return false;
                    }
                }
                for (int col = (move.fromColumn - 1); col > move.toColumn; col--) {
                    if (board[move.fromRow][col] != null) {
                        return false;
                    }
                }
              
                return true;
            }
        }
        return false;
    }
}
