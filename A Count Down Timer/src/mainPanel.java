import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainPanel extends JPanel {
    private TimerGUIPanel p1, p2, p3;
    private JCheckBox suspend;

    public mainPanel() {
        p1 = new TimerGUIPanel();
        p2 = new TimerGUIPanel();
        p3 = new TimerGUIPanel();
        add(p1);
        add(p2);
        add(p3);

        TimerListener timer = new TimerListener();
        suspend = new JCheckBox("Suspend");
        suspend.setSelected(false);
        suspend.addActionListener(timer);
        add(suspend);

    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (suspend.isSelected()){
                CountDownTimer.suspend(true);
            }
            else
                CountDownTimer.suspend(false);
        }
    }
}
