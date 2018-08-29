package chess;
/**********************************************************************
 * This class extends the chessPiece class to creates the king 
 * piece in the game of chess 
 * 
 * @author Michelle Vu
 * @author Trang Nguyen
 * @author Habiba Salem
 * @version 03/30/2018
 *********************************************************************/
public class King extends ChessPiece {

	/******************************************************************
	 * Constructs the piece and takes in Player as a parameter type.
	 * 
	 * @param player assigned to the piece.
	 *****************************************************************/
	King (Player player) {
		super(player);
	}

	/******************************************************************
	 * A method of type string that returns the piece type.
	 * 
	 * @return King, the type of the piece.
	 *****************************************************************/
	public String type() {
		return "King";
	}

	/******************************************************************
	 * A method checks to see if the move is valid based on the 
	 * superclasses' isValidMove method and the piece specific move. 
	 * 
	 * @param move, an object describing the move to be made.
	 * @param board, the board in which this piece lies. 
	 * @return true if the proposed move is valid, otherwise, return 
	 * false. 
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
        if(super.isValidMove(move, board)) {
            return (Math.abs(move.toRow - move.fromRow) == 1
                    && move.fromColumn == move.toColumn)
                    || (Math.abs(move.toColumn - move.fromColumn) == 1
                    && move.fromRow == move.toRow)
                    || (Math.abs(move.toRow - move.fromRow) == 1
                    && Math.abs(move.toColumn - move.fromColumn) == 1);
        }
        return false;
    }
}

