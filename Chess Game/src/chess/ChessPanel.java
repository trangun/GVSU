package chess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessPanel extends JPanel {

	private JButton[][] board;
	private ChessModel model;
	private JMenuItem gameItem, quitItem;
	private boolean isAIMode;
	private static final Color DARK_BROWN = new Color(89,60,7);
	private static final Color GOLD = new Color(218,198,134);
	private static final Color LIGHT_GREEN = new Color(134,169,17);
	private ImageIcon bKing, bQueen, bKnight, bRook, bBishop, bPawn,
	wKing, wQueen, wKnight, wRook, wBishop, wPawn;
	private Move move;
	private int fromRow, fromCol;
	private boolean firstClick = true;
	private boolean gameOver = false;

	public ChessPanel(JMenuItem pquitItem, JMenuItem pgameItem,
			JFrame gameFrame, boolean isAIMode) {
		gameItem = pgameItem;
		quitItem = pquitItem;
		this.isAIMode = isAIMode;

		ButtonListener listener = new ButtonListener();
		quitItem.addActionListener(listener);
		gameItem.addActionListener(listener);

		model = new ChessModel();
		board = new JButton[8][8];
		setLayout(new GridLayout(8, 8));

		// icon for chess pieces
		bKing = new ImageIcon("bKing.png");
		bQueen = new ImageIcon("bQueen.png");
		bKnight = new ImageIcon("bKnight.png");
		bRook = new ImageIcon("bRook.png");
		bBishop = new ImageIcon("bBishop.png");
		bPawn = new ImageIcon("bPawn.png");
		wKing = new ImageIcon("wKing.png");
		wQueen = new ImageIcon("wQueen.png");
		wKnight = new ImageIcon("wKnight.png");
		wRook = new ImageIcon("wRook.png");
		wBishop = new ImageIcon("wBishop.png");
		wPawn = new ImageIcon("wPawn.png");


		// Creates the background for board
		for (int row = 0; row < model.numRows(); row++) {
			for (int col = 0; col < model.numColumns(); col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(new Dimension(100, 100));
				board[row][col].addActionListener(listener);
				add(board[row][col]);

			}
		}
		colorBoard();
		displayBoard();
		gameFrame.setVisible(true);
	}

	public void colorBoard() {
		for (int row = 0; row < model.numRows(); row++) {
			for (int col = 0; col < model.numColumns(); col++) {
				if ((row + col) % 2 != 0)
					board[row][col].setBackground(DARK_BROWN);
				else
					board[row][col].setBackground(GOLD);
			}
		}
	}

	private void switchPlayer() {
		model.setPlayer(model.currentPlayer().next());
	}

	// method that updates the board
	private void displayBoard() {
		for (int row = 0; row < model.numRows(); row++) {
			for (int col = 0; col < model.numColumns(); col++) {
				if (model.pieceAt(row, col) != null) {
					if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (model.pieceAt(row, col).type().equals("Pawn"))
							board[row][col].setIcon(bPawn);
						if (model.pieceAt(row, col).type().equals("Rook"))
							board[row][col].setIcon(bRook);
						if (model.pieceAt(row, col).type().equals("Knight"))
							board[row][col].setIcon(bKnight);
						if (model.pieceAt(row, col).type().equals("Bishop"))
							board[row][col].setIcon(bBishop);
						if (model.pieceAt(row, col).type().equals("King"))
							board[row][col].setIcon(bKing);
						if (model.pieceAt(row, col).type().equals("Queen"))
							board[row][col].setIcon(bQueen);
					} else {
						if (model.pieceAt(row, col).type().equals("Pawn"))
							board[row][col].setIcon(wPawn);
						if (model.pieceAt(row, col).type().equals("Rook"))
							board[row][col].setIcon(wRook);
						if (model.pieceAt(row, col).type().equals("Knight"))
							board[row][col].setIcon(wKnight);
						if (model.pieceAt(row, col).type().equals("Bishop"))
							board[row][col].setIcon(wBishop);
						if (model.pieceAt(row, col).type().equals("King"))
							board[row][col].setIcon(wKing);
						if (model.pieceAt(row, col).type().equals("Queen"))
							board[row][col].setIcon(wQueen);
					}
				} else {
					board[row][col].setIcon(null);
				}
			}
		}
	}

	// add other helper methods as needed
	private void resetGame() {

		model.reset();
		gameOver = false;
		displayBoard();

	}

	private void playerTurn() {
		if (!model.isValidMove(move)) {
			if(model.vMove) {
				if (model.isCastling()) {
				JOptionPane.showMessageDialog(null,
						"Castling");
				} else {
					JOptionPane.showMessageDialog(null,
							"Invalid Move"); 
				}
			}
			if(!model.vMove) {
				JOptionPane.showMessageDialog(null,
						"Invalid Move : Get out of check");
				model.setvMove(true);
			}
		}

	}

	private void checkGameContext () {
		if(model.inCheck(model.currentPlayer())){
			if(model.currentPlayer()==Player.BLACK) {
				JOptionPane.showMessageDialog(null,"Black is in Check");
			}
			if(model.currentPlayer()==Player.WHITE){
				JOptionPane.showMessageDialog(null,"White is in Check");
			}
		}
		if(model.inCheck(model.currentPlayer().next())){
			if(model.currentPlayer().next()==Player.BLACK) {
				JOptionPane.showMessageDialog(null,"Black is in Check");
			}
			if(model.currentPlayer().next()==Player.WHITE){
				JOptionPane.showMessageDialog(null,"White is in Check");
			}
		}

		if(model.isComplete()) {
			gameOver = true;
			if (model.currentPlayer() == Player.BLACK) {
				JOptionPane.showMessageDialog(null, "White Wins!");
			} else {
				JOptionPane.showMessageDialog(null, "Black Wins!");
			}
			int message = JOptionPane.showConfirmDialog(null,
					"GAME OVER!!!" + "\nDo you want to play again?"
							+ "\nIf not, click No to exit.",
							"GAME OVER",
							JOptionPane.YES_NO_OPTION);

			if (message == JOptionPane.YES_OPTION) {
				resetGame();
			} else {
				System.exit(0);
			}
		}
	}
	public void aiMove() {
		if (model.currentPlayer() == Player.WHITE) {
			model.setAiMode(isAIMode);
			model.setAiPromote(true);
			move = model.aiMove();
			if (move != null) {
				model.move(move);
				model.promotion(move);
			}

			//checkGameContext();
			//  displayBoard();
			switchPlayer();

			//System.out.print(model.currentPlayer());
			displayBoard();
			checkGameContext();
			model.setAiPromote(false);
		}
	}

	// inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JComponent component = (JComponent) event.getSource();

			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (component == board[row][col]) {
						if (firstClick) {
							fromRow = row;
							fromCol = col;
							firstClick = false;
							board[row][col].setBackground(LIGHT_GREEN);
						} else {
							move = new Move(fromRow, fromCol, row, col);
							model.castling(move);
							displayBoard();
							playerTurn();

							if (model.isValidMove(move)) {

								model.move(move);
								displayBoard();
								switchPlayer();
								checkGameContext();
								model.promotion(move);
								displayBoard();
								colorBoard();
							} else {
								colorBoard();
							}
							firstClick = true;
						}
					}
				}
			}

			if (isAIMode) {
				aiMove();
			}

			if (component == gameItem)
				resetGame();
			else if (component == quitItem)
				System.exit(0);
		}
	}
}
