package app;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ComparisonFrame extends JFrame {

    public ComparisonFrame(
            double[] srtfAvg,
            double[] priorityAvg,
            double[] sjfNonAvg,
            double[] priorityNonAvg) {

        setTitle("CPU Scheduling Comparison");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ================= TABLE =================
        String[] cols = {
                "Metric",
                "SRTF",
                "Priority P",
                "SJF NP",
                "Priority NP"
        };

        DefaultTableModel model =
                new DefaultTableModel(cols, 0);

        model.addRow(new Object[]{
                "Avg Waiting Time",
                srtfAvg[0],
                priorityAvg[0],
                sjfNonAvg[0],
                priorityNonAvg[0]
        });

        model.addRow(new Object[]{
                "Avg Turnaround Time",
                srtfAvg[1],
                priorityAvg[1],
                sjfNonAvg[1],
                priorityNonAvg[1]
        });

        model.addRow(new Object[]{
                "Avg Response Time",
                srtfAvg[2],
                priorityAvg[2],
                sjfNonAvg[2],
                priorityNonAvg[2]
        });

        JTable table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        // ================= FIND BEST =================
        double minWT = Math.min(
                Math.min(srtfAvg[0], priorityAvg[0]),
                Math.min(sjfNonAvg[0], priorityNonAvg[0])
        );

        // ================= HIGHLIGHT =================
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c =
                        super.getTableCellRendererComponent(
                                table,
                                value,
                                isSelected,
                                hasFocus,
                                row,
                                column);

                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);

                if (row == 0 && column > 0) {

                    double v =
                            Double.parseDouble(value.toString());

                    if (v == minWT) {
                        c.setBackground(
                                new Color(144,238,144));
                    }
                }

                return c;
            }
        };

        table.setDefaultRenderer(Object.class, renderer);

        // ================= ANALYSIS =================
        JTextArea analysis = new JTextArea();

        analysis.setEditable(false);
        analysis.setLineWrap(true);
        analysis.setWrapStyleWord(true);

        String winner;

        if (minWT == srtfAvg[0]) {
            winner = "SRTF";
        } else if (minWT == priorityAvg[0]) {
            winner = "Priority Preemptive";
        } else if (minWT == sjfNonAvg[0]) {
            winner = "SJF Non Preemptive";
        } else {
            winner = "Priority Non Preemptive";
        }

        StringBuilder text = new StringBuilder();

        text.append("===== ANALYSIS =====\n\n");

        text.append("Best Algorithm Based on ")
                .append("Average Waiting Time: ")
                .append(winner)
                .append("\n\n");

        text.append("Lower Waiting Time means ")
                .append("better CPU efficiency and ")
                .append("less process delay.\n\n");

        text.append("Preemptive algorithms usually ")
                .append("improve responsiveness.\n");

        text.append("Non-preemptive algorithms are ")
                .append("simpler and have lower overhead.");

        analysis.setText(text.toString());

        analysis.setFont(new Font("Arial", Font.PLAIN, 15));

        // ================= LAYOUT =================
        setLayout(new BorderLayout(10,10));

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(new JScrollPane(analysis), BorderLayout.SOUTH);
    }
}