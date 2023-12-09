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
        PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                return Integer.compare(p1.getBurstTime(), p2.getBurstTime());
            }
        });

        int totalTime = 0, j = 0;
        ArrayList<Process> dieList = new ArrayList<>();
        while(j < numOfProcesses) {
            if(!processes.isEmpty()) {
                // if arrived, take all the arrived processes
                while (totalTime >= processes.get(0).getArrivalTime()) {
                    readyQueue.add(processes.get(0));
                    processes.remove(0);
                    if (processes.isEmpty())
                        break;
                }
            }
            if (!readyQueue.isEmpty()) {
                Process currProcess = readyQueue.poll();
                j++;

                Vector<Integer> v = new Vector<>();
                int currBurstTime = currProcess.getBurstTime(), currWaitingTime, start, end;
                totalTime += contextSwitching + currBurstTime;
                int currTurnAroundTime = totalTime - currProcess.getArrivalTime();

                if (j == 1) // first process is added
                    currWaitingTime = contextSwitching;
                else
                    currWaitingTime = currTurnAroundTime - currBurstTime;

                start = currProcess.getArrivalTime() + currWaitingTime;
                end = start + currBurstTime;
                v.add(start);
                v.add(end);
//                System.out.println(start + " " + end);
                currProcess.getStartEndTime().add(v);

                currProcess.setTurnAroundTime(currTurnAroundTime);
                currProcess.setWaitingTime(currWaitingTime);

                averageWaitingTime += currWaitingTime;
                averageTurnAroundTime += currTurnAroundTime;

                dieList.add(currProcess);
            }
            else
                totalTime++;
        }
        processes = dieList;
        averageWaitingTime /= (float) numOfProcesses;
        averageTurnAroundTime /= (float) numOfProcesses;

        // test only, yet to be converted to gui
        System.out.println("-------------------------------------------");
        for(int i = 0; i < numOfProcesses; i++){
            System.out.println("Process " +  processes.get(i).getPID() + " - " + processes.get(i).getName());
            System.out.println("    Waiting time: " + processes.get(i).getWaitingTime());
            System.out.println("    Turnaround time: " + processes.get(i).getTurnAroundTime());
            System.out.println("-------------------------------------------");
        }
        System.out.println("Average waiting time: " + averageWaitingTime);
        System.out.println("Average turnaround time: " + averageTurnAroundTime);
    }
}
