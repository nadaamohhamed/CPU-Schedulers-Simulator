package Schedulers;

import Process.Process;

import java.util.ArrayList;
import java.util.Comparator;
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
        processes.sort(Comparator.comparingInt(Process::getArrivalTime)
                .thenComparingInt(Process::getBurstTime));

        int counter = 0, totalTime = 0, j = 0;
        while(j < numOfProcesses){
            int start, end;
            Vector<Integer> v = new Vector<>();
            // if arrived, take it
            if(counter <= processes.get(j).getArrivalTime()) {
                int currBurstTime = processes.get(j).getBurstTime(), currWaitingTime;
                totalTime += currBurstTime + contextSwitching;
                int currTurnAroundTime = totalTime - processes.get(j).getArrivalTime();

                if (j == 0)
                    currWaitingTime = contextSwitching;
                else
                    currWaitingTime = currTurnAroundTime - currBurstTime;

                start = processes.get(j).getArrivalTime() + currWaitingTime;
                end = start + currBurstTime;
                v.add(start);
                v.add(end);
//                System.out.println(start + " " + end);
                processes.get(j).getStartEndTime().add(v);

                processes.get(j).setTurnAroundTime(currTurnAroundTime);
                processes.get(j).setWaitingTime(currWaitingTime);

                averageWaitingTime += currWaitingTime;
                averageTurnAroundTime += currTurnAroundTime;
                j++;
            }
            counter++;
        }

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
