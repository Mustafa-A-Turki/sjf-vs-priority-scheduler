package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainFrame extends JFrame {

    DefaultTableModel inputModel;

    DefaultTableModel srtfModel;
    DefaultTableModel priorityModel;
    DefaultTableModel sjfNonModel;
    DefaultTableModel priorityNonModel;

    JTable inputTable;

    int idCounter = 1;

    public MainFrame() {

        setTitle("CPU Scheduling Simulator");

        setSize(1400, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inputModel = new DefaultTableModel(
                new String[]{"ID","AT","BT","Priority"}, 0);

        inputTable = new JTable(inputModel);

        srtfModel = createModel();

        priorityModel = createModel();

        sjfNonModel = createModel();

        priorityNonModel = createModel();

        JTable srtfTable = new JTable(srtfModel);

        JTable priorityTable = new JTable(priorityModel);

        JTable sjfNonTable = new JTable(sjfNonModel);

        JTable priorityNonTable = new JTable(priorityNonModel);

        JButton add = new JButton("Add");

        JButton delete = new JButton("Delete");

        JButton deleteAll = new JButton("Delete All");

        JButton run = new JButton("Run");

        JButton scenarioA =
                new JButton("Scenario A");

        JButton scenarioB =
                new JButton("Scenario B");

        JButton scenarioC =
                new JButton("Scenario C");

        JButton scenarioD =
                new JButton("Scenario D");

        add.addActionListener(e ->
                inputModel.addRow(
                        new Object[]{
                                "P" + (idCounter++),
                                0,
                                1,
                                1
                        })
        );

        delete.addActionListener(e -> {

            int row = inputTable.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select row first!"
                );

                return;
            }

            inputModel.removeRow(row);
        });

        deleteAll.addActionListener(e -> {

            if (inputModel.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No data!"
                );

                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Delete all processes?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                inputModel.setRowCount(0);

                srtfModel.setRowCount(0);

                priorityModel.setRowCount(0);

                sjfNonModel.setRowCount(0);

                priorityNonModel.setRowCount(0);

                idCounter = 1;
            }
        });

        run.addActionListener(e -> runSimulation());

        scenarioA.addActionListener(
                e -> loadScenarioA()
        );

        scenarioB.addActionListener(
                e -> loadScenarioB()
        );

        scenarioC.addActionListener(
                e -> loadScenarioC()
        );

        scenarioD.addActionListener(
                e -> loadScenarioD()
        );

        JPanel top = new JPanel();

        top.add(add);

        top.add(delete);

        top.add(deleteAll);

        top.add(run);

        top.add(scenarioA);

        top.add(scenarioB);

        top.add(scenarioC);

        top.add(scenarioD);

        JPanel resultPanel =
                new JPanel(new GridLayout(2,2,10,10));

        resultPanel.add(
                createPanel(
                        "SRTF (Preemptive SJF)",
                        srtfTable
                )
        );

        resultPanel.add(
                createPanel(
                        "Priority Preemptive",
                        priorityTable
                )
        );

        resultPanel.add(
                createPanel(
                        "SJF Non Preemptive",
                        sjfNonTable
                )
        );

        resultPanel.add(
                createPanel(
                        "Priority Non Preemptive",
                        priorityNonTable
                )
        );

        add(top, BorderLayout.NORTH);

        add(new JScrollPane(inputTable), BorderLayout.WEST);

        add(resultPanel, BorderLayout.CENTER);
    }

    DefaultTableModel createModel() {

        return new DefaultTableModel(
                new String[]{
                        "ID","AT","BT","PR",
                        "CT","TAT","WT","RT"
                }, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };
    }

    JPanel createPanel(String title, JTable table) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label =
                new JLabel(title, SwingConstants.CENTER);

        label.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        panel.add(label, BorderLayout.NORTH);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    void loadScenarioA() {

        inputModel.setRowCount(0);

        inputModel.addRow(
                new Object[]{"P1", 0, 7, 2});

        inputModel.addRow(
                new Object[]{"P2", 2, 4, 1});

        inputModel.addRow(
                new Object[]{"P3", 4, 1, 3});

        inputModel.addRow(
                new Object[]{"P4", 5, 8, 2});

        idCounter = 5;
    }

    void loadScenarioB() {

        inputModel.setRowCount(0);

        inputModel.addRow(
                new Object[]{"P1", 0, 2, 5});

        inputModel.addRow(
                new Object[]{"P2", 0, 10, 1});

        inputModel.addRow(
                new Object[]{"P3", 1, 4, 3});

        inputModel.addRow(
                new Object[]{"P4", 2, 5, 2});

        idCounter = 5;
    }

    void loadScenarioC() {

        inputModel.setRowCount(0);

        inputModel.addRow(
                new Object[]{"P1", 0, 20, 5});

        inputModel.addRow(
                new Object[]{"P2", 1, 2, 1});

        inputModel.addRow(
                new Object[]{"P3", 2, 2, 1});

        inputModel.addRow(
                new Object[]{"P4", 3,1, 1});

        inputModel.addRow(
                new Object[]{"P5", 4, 2, 1});

        inputModel.addRow(
                new Object[]{"P6", 5, 1, 1});

        idCounter = 7;
    }

    void loadScenarioD() {

        inputModel.setRowCount(0);

        inputModel.addRow(
                new Object[]{"P1", 0, 5, 1});

        inputModel.addRow(
                new Object[]{"P1", 2, 3, 2});

        inputModel.addRow(
                new Object[]{"P3", -1, 4, 1});

        inputModel.addRow(
                new Object[]{"P4", 3, 0, 2});

        inputModel.addRow(
                new Object[]{"P5", 4, 2, -1});

        idCounter = 6;
    }

    void runSimulation() {

        if (inputModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No processes found!"
            );

            return;
        }

        List<Process> original = new ArrayList<>();

        Set<String> ids = new HashSet<>();

        for (int i = 0; i < inputModel.getRowCount(); i++) {

            String id =
                    inputModel
                            .getValueAt(i,0)
                            .toString()
                            .trim();

            if (id.isEmpty() || ids.contains(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid or duplicate ID!"
                );

                return;
            }

            ids.add(id);

            try {

                int at = Integer.parseInt(
                        inputModel
                                .getValueAt(i,1)
                                .toString());

                int bt = Integer.parseInt(
                        inputModel
                                .getValueAt(i,2)
                                .toString());

                int pr = Integer.parseInt(
                        inputModel
                                .getValueAt(i,3)
                                .toString());

                if (at < 0 || bt <= 0 || pr < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid values!"
                    );

                    return;
                }

                original.add(
                        new Process(id, at, bt, pr)
                );

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Numbers only!"
                );

                return;
            }
        }

        List<Process> srtfList =
                cloneList(original);

        List<Process> priorityList =
                cloneList(original);

        List<Process> sjfNonList =
                cloneList(original);

        List<Process> priorityNonList =
                cloneList(original);

        SRTFScheduler.schedule(srtfList);

        PriorityScheduler.schedule(priorityList);

        SJFNonPreemptiveScheduler.schedule(sjfNonList);

        PriorityNonPreemptiveScheduler.schedule(
                priorityNonList
        );

        srtfModel.setRowCount(0);

        priorityModel.setRowCount(0);

        sjfNonModel.setRowCount(0);

        priorityNonModel.setRowCount(0);

        fillTable(srtfModel, srtfList);

        fillTable(priorityModel, priorityList);

        fillTable(sjfNonModel, sjfNonList);

        fillTable(priorityNonModel, priorityNonList);

        double[] srtfAvg =
                MetricsCalculator.calculateAverages(
                        srtfList
                );

        double[] priorityAvg =
                MetricsCalculator.calculateAverages(
                        priorityList
                );

        double[] sjfNonAvg =
                MetricsCalculator.calculateAverages(
                        sjfNonList
                );

        double[] priorityNonAvg =
                MetricsCalculator.calculateAverages(
                        priorityNonList
                );

        new ComparisonFrame(
                srtfAvg,
                priorityAvg,
                sjfNonAvg,
                priorityNonAvg
        ).setVisible(true);

        createGantt(
                "SRTF Gantt",
                SRTFScheduler.gantt
        );

        createGantt(
                "Priority Preemptive Gantt",
                PriorityScheduler.gantt
        );

        createGantt(
                "SJF Non Preemptive Gantt",
                SJFNonPreemptiveScheduler.gantt
        );

        createGantt(
                "Priority Non Preemptive Gantt",
                PriorityNonPreemptiveScheduler.gantt
        );
    }

    void fillTable(
            DefaultTableModel model,
            List<Process> list
    ) {

        for (Object[] row :
                MetricsCalculator.calculate(list)) {

            model.addRow(row);
        }
    }

    void createGantt(
            String title,
            java.util.List<String> gantt
    ) {

        JFrame frame = new JFrame(title);

        GanttPanel panel = new GanttPanel(gantt);

        JScrollPane scrollPane = new JScrollPane(
                panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        frame.add(scrollPane);

        frame.setSize(1200, 300);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    List<Process> cloneList(List<Process> list) {

        List<Process> copy = new ArrayList<>();

        for (Process p : list) {

            copy.add(p.copy());
        }

        return copy;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new MainFrame().setVisible(true));
    }
}