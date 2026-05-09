package app;

import java.util.ArrayList;
import java.util.List;

public class PriorityScheduler {

    public static List<String> gantt = new ArrayList<>();

    public static void schedule(List<Process> processes) {

        gantt.clear();

        int time = 0, completed = 0;

        while (completed < processes.size()) {

            Process best = null;

            for (Process p : processes) {
                if (p.arrival <= time && p.remaining > 0) {
                    if (best == null || p.priority < best.priority) {
                        best = p;
                    }
                }
            }

            if (best == null) {
                gantt.add("Idle");
                time++;
                continue;
            }

            if (best.firstResponseTime == -1)
                best.firstResponseTime = time;

            best.remaining--;
            gantt.add(best.id);
            time++;

            if (best.remaining == 0) {
                best.completion = time;
                completed++;
            }
        }
    }
}