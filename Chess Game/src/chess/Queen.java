package chess;

public class Queen extends ChessPiece {
    protected Queen(Player player) {
        super(player);
    }

    @Override
    public String type() {
        return "Queen";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Rook straight = new Rook(player());
        Bishop diagonal = new Bishop(player());
        return super.isValidMove(move, board)
                && (straight.isValidMove(move, board)
                || diagonal.isValidMove(move, board));
    }
}
