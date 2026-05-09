/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.ArrayList;
import java.util.List;

public class SJFNonPreemptiveScheduler {

    public static List<String> gantt = new ArrayList<>();

    public static void schedule(List<Process> processes) {

        gantt.clear();

        int time = 0;
        int completed = 0;

        while (completed < processes.size()) {

            Process shortest = null;

            for (Process p : processes) {

                if (p.arrival <= time && p.remaining > 0) {

                    if (shortest == null
                            || p.burst < shortest.burst
                            || (p.burst == shortest.burst
                            && p.arrival < shortest.arrival)) {

                        shortest = p;
                    }
                }
            }

            // CPU Idle
            if (shortest == null) {

                gantt.add("Idle");
                time++;
                continue;
            }

            // Response Time
            if (shortest.firstResponseTime == -1)
                shortest.firstResponseTime = time;

            // Execute بالكامل
            while (shortest.remaining > 0) {

                shortest.remaining--;

                gantt.add(shortest.id);

                time++;
            }

            shortest.completion = time;

            completed++;
        }
    }
}