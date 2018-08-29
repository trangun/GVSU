package chess;

/**********************************************************************
 * This class extends the chessPiece class to creates the pawn 
 * piece in the game of chess 
 * 
 * @author Michelle Vu
 * @author Trang Nguyen
 * @author Habiba Salem
 * 
 * @version 03/30/2018
 *********************************************************************/
public class Pawn extends ChessPiece {

	/******************************************************************
	 * Constructs the piece and takes in Player as a paramater type.
	 * 
	 * @param player assigned to the piece.
	 *****************************************************************/
	Pawn(Player player) {
		super(player);
	}

	/******************************************************************
	 * A method of type string that returns the piece type.
	 * 
	 * @return Pawn, the type of the piece.
	 *****************************************************************/
	public String type() {
		return "Pawn";
	}

	/******************************************************************
	 * A method checks to see if the move is valid based on the 
	 * superclasses' isValidMove method and the piece specific move.
	 * 
	 * @param move, an object describing the move to be made.
	 * @param board, the board in which this piece lies. 
	 * @return if the proposed move is valid, otherwise, return
	 * false. 
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			if (board[move.toRow][move.toColumn] == null) {
				if (super.player() == Player.BLACK) {
					return move.fromRow == 6
							&& (move.toRow == move.fromRow - 2
							|| move.toRow == move.fromRow - 1)
							&& move.toColumn == move.fromColumn
							|| move.toRow == move.fromRow - 1
							&& move.toColumn == move.fromColumn;
				} else {
					return move.fromRow == 1
							&& (move.toRow == move.fromRow + 2
							|| move.toRow == move.fromRow + 1)
							&& move.toColumn == move.fromColumn
							|| move.toRow == move.fromRow + 1
							&& move.toColumn == move.fromColumn;
				}
			} else {
				if(super.player()==Player.BLACK) {
					return (move.fromRow - 1 == move.toRow
							&& move.fromColumn - 1 == move.toColumn)
							|| move.fromRow - 1 == move.toRow
							&& move.fromColumn + 1 == move.toColumn;
				}else return (move.fromRow + 1 == move.toRow
						&& move.fromColumn + 1 == move.toColumn)
						|| move.fromRow + 1 == move.toRow
						&& move.fromColumn - 1 == move.toColumn;
			}
		}
		return false;
	}
}
