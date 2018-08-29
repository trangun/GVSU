package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI extends JFrame {

    JButton startGame, cancel, gamePlayer, gameComp;
    ImageIcon backgroundImage;
    JFrame gameFrame;

    public ChessGUI() {
        startGame = new JButton("START");
        cancel = new JButton("CANCEL");
        gamePlayer = new JButton("Player");
        gameComp = new JButton("Computer");

        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());


        // change size and color of the buttons
        startGame.setPreferredSize(new Dimension(160,100));
        startGame.setBackground(Color.ORANGE);
        cancel.setPreferredSize(new Dimension(160,100));
        cancel.setBackground(Color.ORANGE);
        gamePlayer.setPreferredSize(new Dimension(160,100));
        gamePlayer.setBackground(Color.ORANGE);
        gameComp.setPreferredSize(new Dimension(160,100));
        gameComp.setBackground(Color.ORANGE);

        // add background image to front frame
        backgroundImage = new ImageIcon("background.jpg");
        JLabel background = new JLabel(backgroundImage);
        frame.setContentPane(background);

        background.setLayout(new GridBagLayout());
        GridBagConstraints gui = new GridBagConstraints();
        gui.gridx = 2;
        gui.gridy = 10;

        // Add button to front frame
        background.add(startGame);
        background.add(cancel);
        background.add(gamePlayer);
        background.add(gameComp);

        ChessGUI.startListener listener =
                new ChessGUI.startListener();
        startGame.addActionListener(listener);
        cancel.addActionListener(listener);
        gamePlayer.addActionListener(listener);
        gameComp.addActionListener(listener);

        frame.setSize(800, 600);
        gamePlayer.setVisible(false);
        gameComp.setVisible(false);
        frame.setVisible(true);
    }

    public void gameFrame(boolean person) {
        if (gameFrame!=null) {
            gameFrame.dispose();
        }

        gameFrame = new JFrame ("Chess Game");

        JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitItem;
        JMenuItem gameItem;

        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        gameItem = new JMenuItem("New game");

        gameFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

        fileMenu.add(gameItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();

        gameFrame.setJMenuBar(menus);
        menus.add(fileMenu);
        ChessPanel panel;

        panel = new ChessPanel(quitItem,
                gameItem, gameFrame, !person);

        gameFrame.getContentPane().add(panel);
        gameFrame.setSize(800,600);
    }

    private class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JComponent comp = (JComponent) e.getSource();

            if (comp == startGame) {
                startGame.setVisible(false);
                gamePlayer.setVisible(true);
                gameComp.setVisible(true);
            }
            else if (comp == cancel)
                System.exit(0);

            if (comp == gamePlayer) {
                gameFrame(true);

            } else if (comp == gameComp) {
                gameFrame(false);

            }
        }
    }
    public static void main(String[] args) {
        new ChessGUI();
    }

}
