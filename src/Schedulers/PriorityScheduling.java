package Schedulers;
import Process.Process;
import java.util.*;

public class PriorityScheduling extends Scheduler {
    int[] originalPriority = new int[numOfProcesses];
    public PriorityScheduling(int numOfProcesses, ArrayList<Process> processes) {
        super(numOfProcesses, processes, "Priority Schedule");

        for (Process p : processes)
            originalPriority[p.getIndex()] = p.getPriority();
    }

    @Override
    public void simulate() {
        // Sort the processes by arrival time
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        // Create a priority queue to store the ready processes
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                if (p1.getPriority() == p2.getPriority()) {
                    // if priority is equal, compare arrival time
                    return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
                }
                return Integer.compare(p1.getPriority(), p2.getPriority());
            }
        });
        // Create a list to store the finished processes
        ArrayList<Process> finishedList = new ArrayList<>();
        // Initialize the current time and the current process index
        int currentTime = 0;
        int currentProcess = 0;
        // Loop until all processes are finished
        while (currentProcess < numOfProcesses) {
            // If there are any processes that have arrived, add them to the ready queue
            while (!processes.isEmpty() && currentTime >= processes.get(0).getArrivalTime()) {
                readyQueue.add(processes.get(0));
                processes.remove(0);
            }
            // If the ready queue is not empty, execute the process with the highest priority
            if (!readyQueue.isEmpty()) {
                // Get the process from the queue
                Process p = readyQueue.poll();
                // Set the start time and the end time of the process
                p.getStartEndTime().add(new Vector<>());
                p.getStartEndTime().lastElement().add(currentTime);
                p.getStartEndTime().lastElement().add(currentTime + p.getBurstTime());
                // Update the current time and the remaining time of the process
                currentTime += p.getBurstTime();
                p.setRemainingTime(0);
                // Set the turn around time and the waiting time of the process
                p.setTurnAroundTime(currentTime - p.getArrivalTime());
                p.setWaitingTime(p.getTurnAroundTime() - p.getBurstTime());
                // Add the process to the finished list
                finishedList.add(p);
                // Increment the current process index
                currentProcess++;
            } else {
                // If the ready queue is empty, increment the current time
                currentTime++;
            }

            for (Process p : readyQueue) {
                int waitingTime = currentTime - p.getArrivalTime();
                // decrease priority for every 10 units of waiting time to be higher priority (aging)
                int priorityIncrease = waitingTime / 10;
                p.setPriority(Math.max(0, p.getPriority() - priorityIncrease));
            }
        }
        // Set the processes to the finished list
        setProcesses(finishedList);

        // restore original priorities for gui processes information & add the TAT & WT
        for (Process p : processes) {
            p.setPriority(originalPriority[p.getIndex()]);
            averageWaitingTime += p.getWaitingTime();
            averageTurnAroundTime += p.getTurnAroundTime();
        }
        averageWaitingTime /=(float) numOfProcesses;
        averageTurnAroundTime /= (float)numOfProcesses;
    }
}
