package app;

import javax.swing.*;
import java.awt.*;

public class AverageFrame extends JFrame {

    JLabel srtfWT = new JLabel();
    JLabel srtfTAT = new JLabel();
    JLabel srtfRT = new JLabel();

    JLabel priorityWT = new JLabel();
    JLabel priorityTAT = new JLabel();
    JLabel priorityRT = new JLabel();

    public AverageFrame() {

        setTitle("Average Metrics");
        setSize(400, 300);
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel srtfPanel = new JPanel(new GridLayout(3, 1));
        srtfPanel.setBorder(BorderFactory.createTitledBorder("SRTF Averages"));

        srtfPanel.add(srtfWT);
        srtfPanel.add(srtfTAT);
        srtfPanel.add(srtfRT);

        JPanel priorityPanel = new JPanel(new GridLayout(3, 1));
        priorityPanel.setBorder(BorderFactory.createTitledBorder("Priority Averages"));

        priorityPanel.add(priorityWT);
        priorityPanel.add(priorityTAT);
        priorityPanel.add(priorityRT);

        add(srtfPanel);
        add(priorityPanel);
    }

    public void setSRTF(double wt, double tat, double rt) {
        srtfWT.setText("Average WT: " + wt);
        srtfTAT.setText("Average TAT: " + tat);
        srtfRT.setText("Average RT: " + rt);
    }

    public void setPriority(double wt, double tat, double rt) {
        priorityWT.setText("Average WT: " + wt);
        priorityTAT.setText("Average TAT: " + tat);
        priorityRT.setText("Average RT: " + rt);
    }
}