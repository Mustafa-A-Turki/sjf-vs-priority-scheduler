package app;

import java.util.List;

public class MetricsCalculator {

    public static Object[][] calculate(List<Process> list) {

        Object[][] data = new Object[list.size()][8];

        for (int i = 0; i < list.size(); i++) {

            Process p = list.get(i);

            int ct = p.completion;
            int tat = ct - p.arrival;
            int wt = tat - p.burst;
            int rt = p.firstResponseTime - p.arrival;

            data[i][0] = p.id;
            data[i][1] = p.arrival;
            data[i][2] = p.burst;
            data[i][3] = p.priority;
            data[i][4] = ct;
            data[i][5] = tat;
            data[i][6] = wt;
            data[i][7] = rt;
        }

        return data;
    }

    public static double[] calculateAverages(List<Process> list) {

        double totalWT = 0;
        double totalTAT = 0;
        double totalRT = 0;

        for (Process p : list) {

            int ct = p.completion;
            int tat = ct - p.arrival;
            int wt = tat - p.burst;
            int rt = p.firstResponseTime - p.arrival;

            totalWT += wt;
            totalTAT += tat;
            totalRT += rt;
        }

        int n = list.size();

        double avgWT = totalWT / n;
        double avgTAT = totalTAT / n;
        double avgRT = totalRT / n;

        return new double[]{avgWT, avgTAT, avgRT};
    }
}