package Project2;

import javax.swing.*;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConnectFourPanel extends JPanel {

    private int bSize = 10;
    private JLabel[][] board;
    private JButton[] selection;
    private String strBdSize;
    private Toolkit toolkit;
    private Timer timer;
    private JMenuItem gameItem, quitItem;
    private ImageIcon iconBlank, iconPlayer1, iconPlayer2;
    private ConnectFourGame game;
    private boolean isAIMode;
    private boolean thinking = false;

    public ConnectFourPanel(JMenuItem pquitItem, JMenuItem pgameItem,
                            JFrame gameFrame, boolean isAIMode) {

        gameItem = pgameItem;
        quitItem = pquitItem;
        this.isAIMode = isAIMode;

        try {
            strBdSize = JOptionPane.showInputDialog(null,
                    "Enter board size", bSize);
            if (strBdSize != null) {
                bSize = Integer.parseInt(strBdSize);
                gameFrame.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid number");
        }

        if (bSize < 3 || bSize > 30) {
            JOptionPane.showMessageDialog(null, "Invalid Number");
            bSize = 10;
        }

        iconBlank = new ImageIcon("blank.png");
        iconPlayer1 = new ImageIcon("player1.png");
        iconPlayer2 = new ImageIcon("player2.png");

        game = new ConnectFourGame(bSize);
        setLayout(new GridLayout(bSize + 1, bSize));// room for top row

        ConnectFourPanel.ButtonListener listener
                = new ConnectFourPanel.ButtonListener();


        quitItem.addActionListener(listener);
        gameItem.addActionListener(listener);

        selection = new JButton[bSize];
        for (int col = 0; col < selection.length; col++) {
            selection[col] = new JButton("Select");
            selection[col].addActionListener(listener);
            add(selection[col]);
        }

        board = new JLabel[bSize][bSize];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new JLabel("", iconBlank,
                        SwingConstants.CENTER);
                add(board[row][col]);
            }
        }
    }

    private void timer() {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new MyTask(), 250);
    }

    public class MyTask extends TimerTask {
        public void run() {

            if (game.getPlayer() == 1) {
                int col = game.AIPlay(2);
                int row = game.selectCol(col);
                setIcon(1, row, col);
                thinking = false;
                checkGameContext();
            }
            if (game.getPlayer() == 0)
                return;

        }
    }

    // Check the game context to see if it wins or not or
    // if it's the AI turn to set Icon
    private void checkGameContext() {
        if (game.isWinner()) {
            if (game.getPlayer() == 1) {
                JOptionPane.showMessageDialog(null, "   Wins!",
                        "WINNER", JOptionPane.INFORMATION_MESSAGE,
                        iconPlayer2);
            } else {
                JOptionPane.showMessageDialog(null, "   Wins!",
                        "WINNER", JOptionPane.INFORMATION_MESSAGE,
                        iconPlayer1);
            }
            resetGame();
        } else
            game.switchPlayer();
    }

    private void resetGame() {
        game.reset();
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[r].length; c++)
                board[r][c].setIcon(iconBlank);

        game.setPlayer(0);
    }


    private void setIcon(int player, int row, int col) {
        if (game.getBoard()[row][col] == 0)
            board[row][col].setIcon(iconPlayer1);
        else
            board[row][col].setIcon(iconPlayer2);
    }

    //*****************************************************************
    //  Represents a listener for button push (action) events.
    //*****************************************************************
    private class ButtonListener implements ActionListener {
        //--------------------------------------------------------------
        //  Updates the counter and label when the button is pushed.
        //--------------------------------------------------------------
        public void actionPerformed(ActionEvent event) {
            JComponent component = (JComponent) event.getSource();

            if (!isAIMode) {
                for (int col = 0; col < bSize; col++) {
                    if (selection[col] == component) {
                        int row = game.selectCol(col);
                        if (row == -1) {
                            JOptionPane.showMessageDialog(null,
                                    "Col is full!");
                            game.switchPlayer();
                        } else
                            setIcon(game.getPlayer(), row, col);

                        checkGameContext();

                    }
                }
            }
            if (isAIMode) {
                if (game.getPlayer() == 0 && thinking == false) {
                    for (int col = 0; col < bSize; col++) {
                        if (selection[col] == component) {
                            int row = game.selectCol(col);
                            if (row == -1) {
                                JOptionPane.showMessageDialog(null,
                                        "Col is full!");
                                game.switchPlayer();
                            } else
                                setIcon(game.getPlayer(), row, col);
                            checkGameContext();
                        }

                    }
                }


                if (game.getPlayer() == 1)
                    timer();

                if (component == gameItem)
                    resetGame();
                else if (component == quitItem)
                    System.exit(0);
            }
        }
    }
}



