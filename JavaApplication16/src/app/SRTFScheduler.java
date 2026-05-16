package app;



import java.util.ArrayList;
import java.util.List;

public class SRTFScheduler {

    public static List<String> gantt = new ArrayList<>();

    public static void schedule(List<Process> processes) {

        gantt.clear();

        int time = 0, completed = 0;

        while (completed < processes.size()) {

            Process shortest = null;

            for (Process p : processes) {
                if (p.arrival <= time && p.remaining > 0) {
                    if (shortest == null || p.remaining < shortest.remaining) {
                        shortest = p;
                    }
                }
            }

            if (shortest == null) {
                gantt.add("Idle");
                time++;
                continue;
            }

            if (shortest.firstResponseTime == -1)
                shortest.firstResponseTime = time;

            shortest.remaining--;
            gantt.add(shortest.id);
            time++;

            if (shortest.remaining == 0) {
                shortest.completion = time;
                completed++;
            }
        }
    }
}
