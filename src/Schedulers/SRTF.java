package Schedulers;

import Process.Process;

import java.util.*;


// turnaround time = termination time - arrival time
// waiting time = turnaround time - burst time

public class SRTF extends Scheduler {
    int[] originalPriority = new int[numOfProcesses];

    public SRTF(int numOfProcesses, ArrayList<Process> processes) {
        super(numOfProcesses, processes, "SRTF Schedule");
        // Store the original priorities to age them
        for (Process p : processes)
            originalPriority[p.getIndex()] = p.getPriority();
    }

    //increases priority by 1
    public void age(PriorityQueue<Process> readyQueue) {
        for (Process p : readyQueue) {
            if (p.getPriority() >= 1)
                p.setPriority(p.getPriority() - 1);
        }
    }

    @Override
    public void simulate() {
        // The ready queue and die list
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(new SRTFComparator());
        ArrayList<Process> dieList = new ArrayList<>();

        // sort processes by their arrival time
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        Process activeProcess = null;
        boolean lock = false;
        int time = 0, j = 0, done = 0;
        int start = 0, end;

        // while not all processes are finished
        while (done < numOfProcesses) {
            // if a process arrived add it the ready queue
            while (j < numOfProcesses && time >= processes.get(j).getArrivalTime()) {
                readyQueue.add(processes.get(j++));
            }
            // if there is a process in the ready queue take the one with
            // the highest priority and shortest time burst
            if (!readyQueue.isEmpty()) {
                Process currProcess = readyQueue.poll();
                // if the process with the highest prio and shortest time burst
                // running at this time is not equal to the same process running
                if (currProcess != activeProcess && activeProcess != null) {
                    lock = false;
                }
                if (!lock) {
                    start = time;
                    lock = true;
                }
                // set active process to current process
                activeProcess = currProcess;

                // decrement the remaining time by 1 time unit
                currProcess.setRemainingTime(currProcess.getRemainingTime() - 1);

                // age the processes by increasing the prio
                age(readyQueue);

                // if the process is finished add the final start, end times
                // set TAT and WT and add to die list
                if (currProcess.getRemainingTime() == 0) {
                    end = time + 1;
                    currProcess.getStartEndTime().add(new Vector<>(Arrays.asList(start, end)));
                    currProcess.setTurnAroundTime(end - currProcess.getArrivalTime());
                    currProcess.setWaitingTime(currProcess.getTurnAroundTime() - currProcess.getBurstTime());

                    dieList.add(currProcess);
                    done++;
                } else {
                    // Check if there will be any interruption in the next time unit
                    boolean interruption = false;
                    int x = j;
                    while (x < numOfProcesses && time + 1 >= processes.get(x).getArrivalTime()) {
                        if (processes.get(x).getPriority() < currProcess.getPriority())
                            interruption = true;
                        else if (processes.get(x).getPriority() == currProcess.getPriority()
                                && processes.get(x).getRemainingTime() < currProcess.getRemainingTime())
                            interruption = true;
                        x++;
                    }
                    if (!readyQueue.isEmpty()) {
                        if (readyQueue.peek().getPriority() < currProcess.getPriority())
                            interruption = true;
                        else if (readyQueue.peek().getPriority() == currProcess.getPriority()
                                && readyQueue.peek().getRemainingTime() < currProcess.getRemainingTime())
                            interruption = true;
                    }
                    // if it is going to be interrupted add the time interval it worked
                    if (interruption) {
                        end = time + 1;
                        currProcess.getStartEndTime().add(new Vector<>(Arrays.asList(start, end)));
                        start = time + 1;
                        lock = false;
                    }
                    // add it back to the ready queue
                    readyQueue.add(currProcess);
                }
            }
            time++;
        }
        // restore original priorities for gui processes information & add the TAT & WT
        for (Process p : dieList) {
            p.setPriority(originalPriority[p.getIndex()]);
            averageTurnAroundTime += p.getTurnAroundTime();
            averageWaitingTime += p.getWaitingTime();
        }
        processes.clear();
        processes.addAll(dieList);

        averageWaitingTime /= (float) numOfProcesses;
        averageTurnAroundTime /= (float) numOfProcesses;
    }
}

class SRTFComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        if (p1.getPriority() == p2.getPriority()) {
            if (p1.getRemainingTime() == p2.getRemainingTime()) {
                return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
            }
            return Integer.compare(p1.getRemainingTime(), p2.getRemainingTime());
        } else
            return Integer.compare(p1.getPriority(), p2.getPriority());
    }
}
