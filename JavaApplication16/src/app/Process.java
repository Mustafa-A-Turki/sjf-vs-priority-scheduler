package app;

public class Process {
    public String id;
    public int arrival, burst, priority;

    public int remaining;

    public int completion;
    public int firstResponseTime = -1;

    public Process(String id, int arrival, int burst, int priority) {
        this.id = id;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.remaining = burst;
    }

    // مهم للـ clone
    public Process copy() {
        return new Process(id, arrival, burst, priority);
    }
}