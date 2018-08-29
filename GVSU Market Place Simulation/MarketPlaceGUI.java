import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

/*************************************************************
 * GUI for a Market Place
 *
 * @author Scott Grissom & Trang Nguyen
 * @version October 7, 2017 & November 2017
 ************************************************************/
public class MarketPlaceGUI extends JFrame implements ActionListener {

    /** object of the MarketPlace class */
    private MarketPlace mp;

    /** JButtons */
    // Define buttons
    private JButton simulate;

    /** JCheckBox */
    private JCheckBox display;

    /** JTextFields */
    // Define text fields
    private JTextField cashiers;
    private JTextField avgArrivalTime;
    private JTextField avgServiceTime;

    /**
     * Results text area
     */
    JTextArea resultsArea;

    /**
     * menu items
     */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem clearItem;
    JMenuItem quitItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/
    public static void main(String args[]) {
        MarketPlaceGUI gui = new MarketPlaceGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Market Place");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/
    public MarketPlaceGUI() {

        // instantiate an object of type MarketPlace 
        mp = new MarketPlace();

        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area to span one column and 10 rows
        resultsArea = new JTextArea(20, 35);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 10;
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 20;
        add(scrollPane, loc);

        // create Results label
        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 20;
        add(new JLabel("Results"), loc);

        // create Parameters label
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 0;
        loc.gridwidth = 2;
        add(new JLabel("Parameters"), loc);

        //create labels, textfields and buttons 
        //cashiers and its textfield
        loc.insets = new Insets(5, 5, 5, 5);
        loc.gridx = 2;
        loc.gridy = 2;
        loc.gridheight = 1;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("       Cashiers:"), loc);
        loc.gridx = 3;
        loc.gridy = 2;
        cashiers = new JTextField(6);
        add(cashiers, loc);

        // Avg Arrival Time and its text field
        loc.insets = new Insets(5, 5, 5, 5);
        loc.gridx = 2;
        loc.gridy = 3;
        loc.gridheight = 1;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Avg Arrival Time:"), loc);
        loc.gridx = 3;
        loc.gridy = 3;
        avgArrivalTime = new JTextField(6);
        add(avgArrivalTime, loc);

        // Avg Service Time and its text field
        loc.insets = new Insets(5, 5, 5, 5);
        loc.gridx = 2;
        loc.gridy = 4;
        loc.gridheight = 1;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Avg Service Time:"), loc);
        loc.gridx = 3;
        loc.gridy = 4;
        avgServiceTime = new JTextField(6);
        add(avgServiceTime, loc);

        // Display checkbox
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 5;
        loc.gridwidth = 2;
        display = new JCheckBox("Display");
        display.setSelected(false);
        add(display, loc);

        // Simulate button
        loc = new GridBagConstraints();
        loc.insets = new Insets(5, 5, 5, 5);
        loc.gridx = 2;
        loc.gridy = 6;
        loc.gridwidth = 2;
        simulate = new JButton("Simulate");
        add(simulate, loc);

        // register listeners for the buttons
        simulate.addActionListener(this);

        // hide details of creating menus
        setupMenus();
    }

    /**
     * displays each record in the provided list
     * @param ArrayList <BabyName> list of babies
     */
    private boolean isValidNumber (String str) {
        boolean isValid = true;

        // see if str contains a valid double
        try {
            Double.parseDouble(str);
            // there must have been an error converting to double
        } catch (Exception e) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * display the most popular boy and girls names for the requested year
     */
    private void displaySimulate() {
        boolean isOk = true;

        String textService = avgServiceTime.getText();
        double s = 0.0;

        String textArrival = avgArrivalTime.getText();
        double a = 0.0;

        String textCashier = cashiers.getText();
        int c = 0;

        if (isValidNumber(textService)){
            s = Double.parseDouble(textService);
        } else { 
            isOk = false;
            JOptionPane.showMessageDialog(this, "Enter number of Service");
        }

        if (isValidNumber(textArrival)){
            a = Double.parseDouble(textArrival);
        } else {
            isOk = false;
            JOptionPane.showMessageDialog(this, "Enter number of Arrival");
        }

        try {
            c = Integer.parseInt(textCashier);
        } catch (Exception e) {
            isOk = false;
            JOptionPane.showMessageDialog(this, "Enter number of Cashier");
        }

        if (isOk) {
            mp.setParameters(c, s, a, display.isSelected());
            mp.startSimulation();
            resultsArea.append(mp.getReport());
        }

    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     *
     * @param e the event that was fired
     ****************************************************************/
    public void actionPerformed(ActionEvent e) {

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // Allow user to load baby names from a file    
        if (buttonPressed == quitItem) {
            System.exit(1);
        }  else if (buttonPressed == clearItem) {
            resultsArea.setText("");
        } else if (buttonPressed == simulate) {
            resultsArea.setText("");
            displaySimulate();
        } 
    }

    /*******************************************************
    Creates the menu items
     *******************************************************/
    private void setupMenus() {
        fileMenu = new JMenu("File");
        clearItem = new JMenuItem("Clear");
        quitItem = new JMenuItem("Quit");

        fileMenu.add(clearItem);
        fileMenu.add(quitItem);

        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // register the menu items with the action listener
        quitItem.addActionListener(this);
        clearItem.addActionListener(this);

    }
}