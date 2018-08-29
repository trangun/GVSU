package chess;

/**********************************************************************
 * This class extends the IChessPiece Interface to creates the 
 * ChessPiece class which the chess pieces implement.  
 * 
 * @author Michelle Vu, Trang Nguyen, Habiba Salem
 * @version 03/30/2018
 *********************************************************************/
public abstract class ChessPiece implements IChessPiece {
	private Player owner;

	/******************************************************************
	 * Constructor for the ChessPiece class that takes in Player as a
	 * parameter type and sets it to owner.
	 * 
	 * @param player, assigned to owner.
	 *****************************************************************/
	ChessPiece(Player player) {
		this.owner = player;
	}

	/******************************************************************
	 * An abstract method for the type in the format of a String.
	 *****************************************************************/
	public abstract String type();

	/******************************************************************
	 * Returns current player
	 * 
	 * @return owner. 
	 *****************************************************************/
	public Player player() {
		return owner;
	}

	/******************************************************************
	 * A method checks to see if the move is valid for any piece, 
	 * making sure that a piece can't be put on itself, can't go over 
	 * the board size, jump over another piece.
	 * 
	 * @param move, an object describing the move to be made.
	 * @param board, the board in which this piece lies. 
	 * @return true if the proposed move is valid, otherwise, return 
	 * false. 
	 *****************************************************************/
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (move.fromRow > 7 || move.toRow > 7 || move.fromColumn > 7
				|| move.toColumn > 7) {
			return false;
		} else if (move.fromRow < 0 || move.toRow < 0 ||
				move.fromColumn < 0 || move.toColumn < 0) {
			return false;
		} else if (board[move.fromRow][move.fromColumn] == null) {
			return false;
		} else if (move.fromRow != move.toRow || move.fromColumn 
				!= move.toColumn) {
			if (board[move.fromRow][move.fromColumn] != null
					&& board[move.fromRow][move.fromColumn].player()
					== owner) {
				return board[move.toRow][move.toColumn] == null ||
						board[move.toRow][move.toColumn].player() 
						!= owner;
			}
		}
		return false;
	}
}
