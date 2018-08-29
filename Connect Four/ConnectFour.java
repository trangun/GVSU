package Project2;

import Project2.threeD.ConnectFourPanel3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Michelle Vu & Trang Nguyen
 */
public class ConnectFour extends JFrame{
    JButton startGame, cancel, gamePlayer, gameComp, game3D;
    ImageIcon backgroundImage;
    JFrame gameFrame;
    JFrame gameFrame3D;

    public ConnectFour () {
        startGame = new JButton("START");
        cancel = new JButton("CANCEL");
        gamePlayer = new JButton("Player");
        gameComp = new JButton("Computer");
        game3D = new JButton("3D");

        JFrame frame = new JFrame ("Connect Four");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        // change size and color of the buttons
        startGame.setPreferredSize(new Dimension(160,100));
        startGame.setBackground(Color.PINK);
        cancel.setPreferredSize(new Dimension(160,100));
        cancel.setBackground(Color.PINK);
        gamePlayer.setPreferredSize(new Dimension(160,100));
        gamePlayer.setBackground(Color.PINK);
        gameComp.setPreferredSize(new Dimension(160,100));
        gameComp.setBackground(Color.PINK);
        game3D.setPreferredSize(new Dimension(160,100));
        game3D.setBackground(Color.PINK);

        // add background image to front frame
        backgroundImage = new ImageIcon("background.png");
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
        background.add(game3D);

        ConnectFour.startListener listener =
                new ConnectFour.startListener();
        startGame.addActionListener(listener);
        cancel.addActionListener(listener);
        gamePlayer.addActionListener(listener);
        gameComp.addActionListener(listener);
        game3D.addActionListener(listener);

        frame.setSize(800, 600);
        gamePlayer.setVisible(false);
        gameComp.setVisible(false);
        game3D.setVisible(false);
        frame.setVisible(true);

    }

    // A method that connects to the panel for player and AI
    // based on the assigned condition.
    public void gameFrame(boolean person) {
        if (gameFrame!=null) {
            gameFrame.dispose();
        }

        gameFrame = new JFrame ("Connect Four");

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
        ConnectFourPanel panel;

        panel = new ConnectFourPanel(quitItem,
                gameItem, gameFrame, !person);

        gameFrame.getContentPane().add(panel);
        gameFrame.setSize(800,600);
    }

    // Connect to panel for 3D game
    public void gameFrame3D() {
        if (gameFrame3D != null) {
            gameFrame3D.dispose();
        }
        JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitItem;
        JMenuItem gameItem;

        gameFrame3D = new JFrame ("Connect Four 3D");
        gameFrame3D.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        gameItem = new JMenuItem("New game");

        fileMenu.add(gameItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        gameFrame3D.setJMenuBar(menus);
        menus.add(fileMenu);

        ConnectFourPanel3D panel = new ConnectFourPanel3D(quitItem,
                gameItem, gameFrame3D);
        gameFrame3D.getContentPane().add(panel);

        gameFrame3D.setSize(800, 600);
    }

    private class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JComponent comp = (JComponent) e.getSource();

            if (comp == startGame) {
                startGame.setVisible(false);
                gamePlayer.setVisible(true);
                gameComp.setVisible(true);
                game3D.setVisible(true);
            }
            else if (comp == cancel)
                System.exit(0);

            if (comp == gamePlayer) {
                gameFrame(true);

            } else if (comp == gameComp) {
                gameFrame(false);

            } else if (comp == game3D) {
                gameFrame3D();
            }
        }
    }

    public static void main (String[] args)
    {
        new ConnectFour();
    }
}


