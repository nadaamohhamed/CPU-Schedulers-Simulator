package Schedulers;

import Process.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AGScheduling extends Scheduler {

    public AGScheduling(int numOfProcesses, ArrayList<Process> processes) {
        super(numOfProcesses, processes, "AG Schedule");
    }
    public int calculateAGFactor(Process process){
        int AGFactor = process.getArrivalTime() + process.getBurstTime();

        if(process.getRandomFunction() > 10)
            AGFactor += 10;
        else if(process.getRandomFunction() < 10)
            AGFactor += process.getRandomFunction();
        else
            AGFactor += process.getPriority();

        return AGFactor;
    }
    public int calculateMeanQuantum(){
        double sum = 0;
        for(int i = 0;i < numOfProcesses; i++){
            sum += processes.get(i).getQuantum();
        }
        return (int) Math.ceil(sum / numOfProcesses);
    }
    @Override
    public void simulate() {
        Queue<Process> readyQueue = new LinkedList<>();

    }
}
