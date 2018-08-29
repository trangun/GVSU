import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TimerGUIPanel extends JPanel {
    private String[] name;
    private JButton[] buttons;
    private JLabel label, display;
    private JTextField[] timerField;
    private CountDownTimer cdTimer;
    private Timer javaTimer;
    private newTimerListener timer1;
    private TimerListener timer;
    private final int DELAY = 1000;

    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("Countdown Timer - TUN");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainPanel panel = new mainPanel();
        frame.pack();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setSize(680,450);
        frame.setVisible(true);
    }

    public TimerGUIPanel(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gui = new GridBagConstraints();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //create label
        cdTimer = new CountDownTimer(0,0,10);
        timer1 = new newTimerListener();
        timer = new TimerListener();
        javaTimer = new Timer(DELAY, timer1);


        //Create message
        gui.gridx = 0;
        gui.gridy = 0;
        gui.insets.bottom = 20;
        gui.insets.top = 20;
        add(new JLabel ("<html><span style='font-size:20px'>"+
                "Timer: \t\t"+"</span></html>"), label);

        //Create display label
        gui.gridx = 0;
        gui.gridy = 0;
        gui.insets.bottom = 20;
        gui.insets.top = 20;
        display = new JLabel(cdTimer.toString());
        display.setFont(new Font("Serif", Font.BOLD, 30));
        add(display);

        //hour, minute, second and its textfield
        name = new String[] {"Hour", "Minute", "Second"};
        timerField = new JTextField[3];
        for (int i = 0; i < timerField.length; i++) {
            timerField[i] = new JTextField(name[i]);
            gui.insets = new Insets(5, 30, 5, 5);
            gui.gridx = 0 ;
            gui.gridy = 2 + i;
            gui.gridheight = 1;
            gui.gridwidth = 1;
            gui.anchor = GridBagConstraints.LINE_START;
            add(new JLabel("<html><span style='font-size:14px'>" +
                    name[i] + "</span></html>"), gui);
            gui.insets = new Insets(5, 5, 5, 5);
            gui.gridx = 1;
            gui.gridy = 2 + i;
            timerField[i] = new JTextField(5);
            add(timerField[i], gui);
        }

        //create all of the buttons needed
        name = new String[] {"Start", "Stop ", " Add ", " Sub ",
                "Save", "Load", " Inc  ", " Dec ", "Reset"};
        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(name[i]);
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8) {
                gui.gridx = 0;
                gui.gridy = 9;
                gui.insets = new Insets(5,15,5,5);
                for (int j = i + 2; j < buttons.length; j += 2)
                    gui.gridy--;
            }
            else {
                gui.gridx = 1;
                gui.gridy = 8;
                for (int j = i + 2; j < buttons.length; j += 2)
                    gui.gridy--;
            }
            gui.anchor = GridBagConstraints.LINE_START;
            buttons[i].addActionListener(timer);
            add(buttons[i], gui);
        }
    }

    //helper method for decrement
    private void timerDec (){
        if (cdTimer.getHours() > 0 || cdTimer.getMinutes() > 0
                || cdTimer.getSeconds() > 0)
            cdTimer.dec();
    }

    //helper method for decrement
    private void timerInc () {
        if (cdTimer.getHours() > 0 || cdTimer.getMinutes() > 0
                || cdTimer.getSeconds() > 0)
            cdTimer.inc();
    }

    // helper method for sub button
    private int timerSub() {
        int timerConvertToSecs =  cdTimer.getHours()*3600 +
                cdTimer.getMinutes()*60 + cdTimer.getSeconds();
        try {
            int fieldsSeconds = Integer.parseInt(timerField[0].getText())*3600 +
                    Integer.parseInt(timerField[1].getText()) * 60
                    + Integer.parseInt(timerField[2].getText());
            if (timerConvertToSecs - fieldsSeconds < 0)
                throw new IllegalArgumentException();
            return fieldsSeconds;

        } catch (Exception e) {
            if(!CountDownTimer.isSuspend())
                JOptionPane.showMessageDialog(this, "Invalid numbers");
            return 0;
        }
    }

    // helper method for add button
    private int timerAdd() {
        int timerConvertToSecs =  cdTimer.getHours()*3600 +
                cdTimer.getMinutes()*60 + cdTimer.getSeconds();
        try {
            int fieldsSeconds = Integer.parseInt(timerField[0].getText())*3600 +
                    Integer.parseInt(timerField[1].getText()) * 60
                    + Integer.parseInt(timerField[2].getText());
            if (timerConvertToSecs + fieldsSeconds < 0)
                throw new IllegalArgumentException();
            return fieldsSeconds;

        } catch (Exception e) {
            if(!CountDownTimer.isSuspend())
                JOptionPane.showMessageDialog(this, "Invalid numbers");
            return 0;
        }
    }

    // load the desired file
    private void loadFile() {
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String file = fc.getSelectedFile().getName();
            cdTimer.load(file);
        }
    }

    // save file in desired direction
    private void saveFile () {
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String file = fc.getSelectedFile().getName();
            cdTimer.save(file);
        }
    }

    //for buttons
    private class TimerListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            //load the timer
            if(e.getSource() == buttons[5]) loadFile();

                //save the timer
            else if (e.getSource() == buttons[4]) saveFile();

                //start timer
            else if (e.getSource() == buttons[0])
                javaTimer.start();

                //stop the timer
            else if (e.getSource() == buttons[1])
                javaTimer.stop();

                //increment timer by 1 second
            else if (e.getSource() == buttons[6])
                timerInc();

                //decrement timer by 1 second
            else if (e.getSource() == buttons[7])
                timerDec();

                // add the timer
            else if (e.getSource() == buttons[2])
                cdTimer.add(timerAdd());

                // subtract the timer
            else if (e.getSource() == buttons[3])
                cdTimer.sub(timerSub());

                // reset timer
            else if (e.getSource() == buttons[8]) {
                cdTimer = new CountDownTimer(0, 0, 10);
                javaTimer.stop();
            }

            //decrement timer
            else
                cdTimer.dec();

            // update the label
            display.setText(cdTimer.toString());
        }
    }

    //for timer
    private class newTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!cdTimer.toString().equals("0:00:00"))
                cdTimer.dec();
            else
                javaTimer.stop();

            display.setText(cdTimer.toString());
        }
    }
}
