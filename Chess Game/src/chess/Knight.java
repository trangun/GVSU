package chess;

public class Knight extends ChessPiece{
    Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(super.isValidMove(move, board)) {
            if ((Math.abs(move.toRow - move.fromRow) == 2
                    && Math.abs(move.toColumn - move.fromColumn) == 1)
                    || (Math.abs(move.toRow - move.fromRow) == 1
                    && Math.abs(move.toColumn - move.fromColumn) == 2)) {
                return true;
            }
        }
        return false;
    }
}
