package chess;

public class Bishop extends ChessPiece {
    Bishop(Player player) {
        super(player);
    }

    public String type() {
        return "Bishop";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(super.isValidMove(move, board)) {
            if (Math.abs(move.fromRow - move.toRow)
                    == Math.abs(move.fromColumn
                    - move.toColumn)) {
                if (move.fromRow < move.toRow) {
                    if (move.fromColumn < move.toColumn) {
                        for (int row = move.fromRow + 1;
                             row < move.toRow; row++) {
                            for (int col = move.fromColumn + 1;
                                 col < move.toColumn; col++) {
                                if (board[row][col] != null
                                        && row - move.fromRow
                                        == col - move.fromColumn) {
                                    return false;
                                }
                            }
                        }
                    } else {
                        for (int row = move.fromRow + 1; row < move.toRow; row++)
                            for (int col = move.fromColumn - 1;
                                 col > move.toColumn; col--)
                                if (board[row][col] != null
                                        && row - move.fromRow
                                        == move.fromColumn - col) {
                                    return false;
                                }
                    }
                } else {
                    if (move.fromColumn < move.toColumn) {
                        for (int row = move.fromRow - 1;
                             row > move.toRow; row--) {
                            for (int col = move.fromColumn + 1;
                                 col < move.toColumn; col++) {
                                if (board[row][col] != null
                                        && move.fromRow - row
                                        == col - move.fromColumn) {
                                    return false;
                                }
                            }
                        }
                    } else {
                        for (int row = move.fromRow - 1; row > move.toRow; row--)
                            for (int col = move.fromColumn - 1;
                                 col > move.toColumn; col--)
                                if (board[row][col] != null
                                        &&  move.fromRow - row
                                        == move.fromColumn - col) {
                                    return false;
                                }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
