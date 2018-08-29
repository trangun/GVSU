package Project2.threeD;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ConnectFourPanel3D extends JPanel {
	public int bSize = 7;
    private String strBdSize;
	private JButton[][] board;
	private ConnectFourGame3D game;
	private JMenuItem gameItem, quitItem;

	public ConnectFourPanel3D(JMenuItem pquitItem,
                              JMenuItem pgameItem, JFrame gameFrame3D){
		gameItem = pgameItem;
		quitItem = pquitItem;
		game = new ConnectFourGame3D();

        try {
            strBdSize = JOptionPane.showInputDialog(null,
                    "Enter board size", bSize);
            if (strBdSize != null) {
                bSize = Integer.parseInt(strBdSize);
                gameFrame3D.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid number");
        }

        if (bSize < 3 || bSize > 7) {
            JOptionPane.showMessageDialog(null, "Invalid Number");
            bSize = 7;
        }

        setLayout(new GridLayout(bSize, bSize));

		ConnectFourPanel3D.ButtonListener listener =
                new ConnectFourPanel3D.ButtonListener();
        quitItem.addActionListener(listener);
        gameItem.addActionListener(listener);

        board = new JButton[bSize][bSize];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				//for (int dep = 0; dep < SIZE;dep++) {
				board[row][col] = new JButton("");
				board[row][col].addActionListener(listener);
				add(board[row][col]);
				//}
			}
		}
		resetGame();
	}
	private void resetGame() {
		game.reset();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				board[r][c].setText("");
			}
		}
	}
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent event) {
			JComponent component = (JComponent) event.getSource();
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[row].length; col++) {
					if(board[row][col]==component) {
						if(board[row][col].getText().length() < bSize) {
						board[row][col].setText("" +
								board[row][col].getText()
                                + game.getPlayer());
						game.setDepth(board[row][col].getText().length());
					//	game.setRow(row);
						//game.setColumn(col);
						game.setPiece(col,row);
						
						}
						if(game.isWinner()) {
							JOptionPane.showMessageDialog(null,
                                    "Player " + game.getPlayer()
                                            + " Wins!");
							resetGame();
						}else
							game.switchPlayer();
					}
				}
			}
			if (component == gameItem)
				resetGame();
			else if (component == quitItem)
				System.exit(0);


		}
	}
}
