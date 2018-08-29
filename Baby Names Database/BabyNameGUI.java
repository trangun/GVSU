import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

/*************************************************************
 * GUI for a Baby Name Database
 *
 * @author Scott Grissom & Trang Nguyen
 * @version October 7, 2017
 ************************************************************/
public class BabyNameGUI extends JFrame implements ActionListener {

    /** object of the BabyNamesDatabase class */
    // FIX ME: Define a BabyNameDatabase variable
    private BabyNamesDatabase db;

    /** JButtons */
    // FIX ME: Define buttons
    private JButton findBabyByName;
    private JButton findBabyByYear;
    private JButton findMostPopular;
    private JButton findTopTen;

    /** JTextFields */
    // FIX ME: Define text fields
    private JTextField year;
    private JTextField name;

    /**
     * Results text area
     */
    JTextArea resultsArea;

    /**
     * menu items
     */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem openItem;
    JMenuItem countItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/
    public static void main(String args[]) {
        BabyNameGUI gui = new BabyNameGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Baby Names");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/
    public BabyNameGUI() {

        // FIX ME: instantiate an object of type BabyNameDatbase  
        db = new BabyNamesDatabase();

        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area to span one column and 10 rows
        resultsArea = new JTextArea(20, 20);
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

        // create Searches label
        loc = new GridBagConstraints();
        loc.gridx = 3;
        loc.gridy = 0;
        loc.gridwidth = 2;
        add(new JLabel("Searches"), loc);

        // FIX ME: create labels, textfields and buttons 
        //year and its textfield
        loc.insets = new Insets(5, 5, 5, 5);
        loc.gridx = 2;
        loc.gridy = 2;
        loc.gridheight = 1;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Year"), loc);
        loc.gridx = 3;
        loc.gridy = 2;
        year = new JTextField(4);
        add(year, loc);

        // By Year button
        loc.insets = new Insets(6, 3, 3, 3);
        loc.gridx = 3;
        loc.gridy = 3;
        loc.anchor = GridBagConstraints.LINE_START;
        findBabyByYear = new JButton("By Year");
        add(findBabyByYear, loc);

        // Most Popular button
        loc.gridx = 3;
        loc.gridy = 4;
        loc.anchor = GridBagConstraints.LINE_START;
        findMostPopular = new JButton("Most Popular");
        add(findMostPopular, loc);

        // Top Ten button
        loc.gridx = 3;
        loc.gridy = 5;
        loc.anchor = GridBagConstraints.LINE_START;
        findTopTen = new JButton("Top Ten");
        add(findTopTen, loc);

        //name and its textfield
        loc.insets = new Insets(40, 5, 5, 5);
        loc.gridx = 2;
        loc.gridy = 8;
        loc.gridheight = 1;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Name"), loc);
        loc.gridx = 3;
        loc.gridy = 8;
        name = new JTextField(10);
        add(name, loc);

        //By Name button
        loc.insets = new Insets(0, 5, 5, 5);
        loc.gridx = 3;
        loc.gridy = 9;
        loc.anchor = GridBagConstraints.LINE_START;
        findBabyByName = new JButton("By Name");
        add(findBabyByName, loc);

        // FIX ME: register listeners for the buttons
        findBabyByName.addActionListener(this);
        findBabyByYear.addActionListener(this);
        findMostPopular.addActionListener(this);
        findTopTen.addActionListener(this);

        // hide details of creating menus
        setupMenus();
    }

    /**
     * displays each record in the provided list
     * @param ArrayList <BabyName> list of babies
     */
    private void displayNames(ArrayList<BabyName> list) {
        DecimalFormat f1 = new DecimalFormat("#,###");
        for (BabyName b : list) {
            resultsArea.append("\n" + b.toString());
        }
        resultsArea.append("\n\nTotal: " + f1.format(list.size()));
    }

    /**
     * display the most popular boy and girls names for the requested year
     */
    private void displayMostPopular() {
        try {
            if (year.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Did you forget to enter year?");
            } else if (Integer.parseInt(year.getText()) < 1880 ||
            Integer.parseInt(year.getText()) > 2016) {
                JOptionPane.showMessageDialog(this, "We do not have information for this year!");
            } else {
                int yearInt = Integer.parseInt(year.getText());
                resultsArea.setText("Most popular Names in " + year.getText() + "\n\n"
                    + db.mostPopularBoy(yearInt) + "\n"
                    + db.mostPopularGirl(yearInt));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year has to be integer number!");
        } 
    }

    /**
     * display all names for the requested year
     */
    private void displayByYear() {
        try {
            if (year.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Did you forget to enter year?");
            } else if (Integer.parseInt(year.getText()) < 1880) {
                JOptionPane.showMessageDialog(this, "We do not have information for this year!");
            } else {
                displayNames(db.searchForYear(Integer.parseInt(year.getText())));
                resultsArea.append("\nAll Names in " + year.getText());

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Year has to be in integer number!");
        }
    }

    /**
     * display top names for the requested year
     */
    private void displayTopTen() {
        try {
            if (year.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Did you forget to enter year?");
            } else {
                resultsArea.setText("Top Ten Baby Names in " + year.getText() + "\n");
                displayNames(db.topTenNames(Integer.parseInt(year.getText())));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year has to be integer number !");
        } catch (IndexOutOfBoundsException e2) {
            JOptionPane.showMessageDialog(this, "We do not have information for this year!");
        }
    }

    /**
     * display all records for the requested name
     */
    private void displayByName() {
        if (name.getText().length() > 0) {
            displayNames(db.searchForName(name.getText()));
            resultsArea.append("\nAll years with " + name.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Did you forget to enter name?");
        }
    }

    /**
     * display the total number of records, total number of girls, and total number of boys
     */
    private void displayCounts() {
        DecimalFormat f2 = new DecimalFormat("#,###");
        resultsArea.setText("Total Counts" + "\n"
            + "\nTotal Girls: " + f2.format(db.countAllGirls()) +
            "\nTotal Boys: " + f2.format(db.countAllBoys()) +
            "\nTotal Names: " + f2.format(db.countAllNames()));
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
        if (buttonPressed == openItem) {
            openFile();
        } else if (buttonPressed == quitItem) {
            System.exit(1);
        } else if (db.countAllNames() == 0) {
            JOptionPane.showMessageDialog(this, "Did you forget to open the file?");
        } else if (buttonPressed == findTopTen) {
            displayTopTen();
        } else if (buttonPressed == findBabyByName) {
            displayByName();
        } else if (buttonPressed == findMostPopular) {
            displayMostPopular();
        } else if (buttonPressed == findBabyByYear) {
            displayByYear();
        } else if (buttonPressed == countItem) {
            displayCounts();
        }
    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/
    private void openFile() {

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            // FIX ME: change the following line as needed
            // to use your instance variable name
            //database.readBabyNameData(filename);    
            db.readBabyNameData(filename);
        }
    }

    /*******************************************************
    Creates the menu items
     *******************************************************/
    private void setupMenus() {
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        countItem = new JMenuItem("Counts");
        openItem = new JMenuItem("Open...");

        fileMenu.add(countItem);
        fileMenu.add(openItem);
        fileMenu.add(quitItem);

        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // FIX ME: register the menu items with the action listener
        quitItem.addActionListener(this);
        openItem.addActionListener(this);
        countItem.addActionListener(this);

    }
}