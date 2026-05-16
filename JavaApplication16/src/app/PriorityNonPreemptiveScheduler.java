/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.ArrayList;
import java.util.List;

public class PriorityNonPreemptiveScheduler {

    public static List<String> gantt = new ArrayList<>();

    public static void schedule(List<Process> processes) {

        gantt.clear();

        int time = 0;
        int completed = 0;

        while (completed < processes.size()) {

            Process best = null;

            for (Process p : processes) {

                if (p.arrival <= time && p.remaining > 0) {

                    if (best == null
                            || p.priority < best.priority
                            || (p.priority == best.priority
                            && p.arrival < best.arrival)) {

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

            while (best.remaining > 0) {

                best.remaining--;

                gantt.add(best.id);

                time++;
            }

            best.completion = time;

            completed++;
        }
    }
}