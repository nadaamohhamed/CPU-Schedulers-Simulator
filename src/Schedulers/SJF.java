package Schedulers;

import Process.Process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class SJF extends Scheduler {
    private int contextSwitching;

    public SJF(int numOfProcesses, ArrayList<Process> processes, int contextSwitching) {
        super(numOfProcesses, processes, "SJF Schedule");
        this.contextSwitching = contextSwitching;
    }

    @Override
    public void simulate() {
        // sort processes by their arrival time first and then burst time
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                if (p1.getBurstTime() == p2.getBurstTime()) {
                    return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
                }
                return Integer.compare(p1.getBurstTime(), p2.getBurstTime());
            }
        });

        int totalTime = 0, processesDone = 0;
        ArrayList<Process> dieList = new ArrayList<>();

        while(processesDone < numOfProcesses) {
                // if there are arrived processes, take them all to ready queue
            while (!processes.isEmpty() && totalTime >= processes.get(0).getArrivalTime()) {
                readyQueue.add(processes.get(0));
                processes.remove(0);
            }
            if (!readyQueue.isEmpty()) {
                // get the process with the smallest burst time from the ready queue
                Process runningProcess = readyQueue.poll();
                processesDone++;

                Vector<Integer> v = new Vector<>();
                int currBurstTime = runningProcess.getBurstTime(), currWaitingTime, start, end;
                totalTime += contextSwitching + currBurstTime;
                int currTurnAroundTime = totalTime - runningProcess.getArrivalTime();

                if (processesDone == 1) // first process
                    currWaitingTime = contextSwitching;
                else
                    currWaitingTime = currTurnAroundTime - currBurstTime;

                // set the start and end time for each process
                start = runningProcess.getArrivalTime() + currWaitingTime;
                end = start + currBurstTime;
                v.add(start);
                v.add(end);
                runningProcess.getStartEndTime().add(v);

                // update waiting, turnaround, remaining time for the running process
                runningProcess.setTurnAroundTime(currTurnAroundTime);
                runningProcess.setWaitingTime(currWaitingTime);
                runningProcess.setRemainingTime(0);

                // add the current process waiting, turnaround time to average
                averageWaitingTime += currWaitingTime;
                averageTurnAroundTime += currTurnAroundTime;

                // add the process to die list after finishing its job
                dieList.add(runningProcess);
            }
            else
                totalTime++;
        }
        processes.addAll(dieList);

        // divide sum by its number of processes to get the average
        averageWaitingTime /= (float) numOfProcesses;
        averageTurnAroundTime /= (float) numOfProcesses;
    }
}
