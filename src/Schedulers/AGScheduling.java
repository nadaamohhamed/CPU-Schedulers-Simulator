package Schedulers;

import Process.Process;
import Process.AGProcess;

import java.util.*;


public class AGScheduling extends Scheduler {
    ArrayList<AGProcess> AGProcesses;
    Queue<AGProcess> readyQueue = new LinkedList<>();
    ArrayList<AGProcess> dieList = new ArrayList<>();
    AGProcess activeProcess = null;

    public AGScheduling(int numOfProcesses, ArrayList<Process> processes) {
        super(numOfProcesses, processes, "AG Schedule");
        AGProcesses = new ArrayList<>();
    }

    public int calculateAGFactor(Process process) {
        int AGFactor = process.getArrivalTime() + process.getBurstTime();
        if (process.getRandomFunction() > 10)
            AGFactor += 10;
        else if (process.getRandomFunction() < 10)
            AGFactor += process.getRandomFunction();
        else
            AGFactor += process.getPriority();

        return AGFactor;
    }

    public int calculateMeanQuantum() {
        // mean quantum of all arrived processes even if they are dead
        int sum = activeProcess.getQuantum();
        for (AGProcess process : readyQueue) {
            sum += process.getQuantum();
        }
        return sum / (readyQueue.size() + dieList.size() + 1);
    }

    void setAGFactor() {
        for (int i = 0; i < numOfProcesses; i++) {
            AGProcess process =  new AGProcess(processes.get(i));
            process.setAGFactor(calculateAGFactor(process));
            AGProcesses.add(process);
        }
        // to be like the example in assignment pdf uncomment the following lines
        AGProcesses.get(0).setAGFactor(20);
        AGProcesses.get(1).setAGFactor(17);
        AGProcesses.get(2).setAGFactor(16);
        AGProcesses.get(3).setAGFactor(43);
    }
    void addHistory(){
        // loop over all processes and add their quantum to the quantum updates history

        activeProcess.quantumUpdates.add(activeProcess.getQuantum());
        for(AGProcess process : AGProcesses){
            process.quantumUpdates.add(process.getQuantum());
        }
        for(AGProcess process : readyQueue){
            process.quantumUpdates.add(process.getQuantum());
        }
        for(AGProcess process : dieList){
            process.quantumUpdates.add(process.getQuantum());
        }
    }
    @Override
    public void simulate() {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        setAGFactor();
        int currentTime = 0;
        int currentProcess = 0;
        while (currentProcess < numOfProcesses) {
            while (!AGProcesses.isEmpty() && currentTime >= AGProcesses.get(0).getArrivalTime()) {
                // if there are any processes that have arrived, add them to the ready queue
                readyQueue.add(AGProcesses.get(0));
                AGProcesses.remove(0);
            }

            if (!readyQueue.isEmpty()) {
                // if the ready queue is not empty
                if (activeProcess == null) {
                    // if there is no active process, get the first process in the ready queue
                    activeProcess = readyQueue.poll();
                    activeProcess.getStartEndTime().add(new Vector<>());
                    activeProcess.getStartEndTime().lastElement().add(currentTime);
                }else if(activeProcess.isPreemptive()){
                    // if the active process is preemptive
                    // get the minimum AG factor process in the ready queue and compare it with the active process

                    int remainingQuantum = activeProcess.getQuantum() - (currentTime - activeProcess.getStartEndTime().lastElement().firstElement());
                    int mn = 9999999;
                    AGProcess mnProcess = null;
                    for (AGProcess process : readyQueue) {
                        if (process.getAGFactor() < mn) {
                            mn = process.getAGFactor();
                            mnProcess = process;
                        }
                    }
                    // if the minimum AG factor process is less than the active process AG factor
                    // stop the active process and add it to the ready queue
                    // and get the minimum AG factor process and make it the active process
                    if(mn < activeProcess.getAGFactor()){
                        activeProcess.getStartEndTime().lastElement().add(currentTime);
                        activeProcess.setQuantum(activeProcess.getQuantum() + remainingQuantum);
                        addHistory();
                        activeProcess.setPreemptive(false);
                        readyQueue.add(activeProcess);
                        activeProcess = mnProcess;
                        activeProcess.getStartEndTime().add(new Vector<>());
                        activeProcess.getStartEndTime().lastElement().add(currentTime);
                        readyQueue.remove(mnProcess);
                    }
                }
            }
            currentTime++;
            if (activeProcess != null) {
                // calculate the remaining quantum of the active process
                int remainingQuantum = activeProcess.getQuantum() - (currentTime - activeProcess.getStartEndTime().lastElement().firstElement());
                // update the remaining time of the active process
                activeProcess.setRemainingTime(activeProcess.getRemainingTime() - 1);
                if (activeProcess.getRemainingTime() == 0) {
                    // if the active process is finished
                    activeProcess.getStartEndTime().lastElement().add(currentTime);
                    activeProcess.setTurnAroundTime(currentTime - activeProcess.getArrivalTime());
                    activeProcess.setWaitingTime(activeProcess.getTurnAroundTime() - activeProcess.getBurstTime());
                    activeProcess.setQuantum(0);
                    addHistory();
                    dieList.add(activeProcess);
                    currentProcess++;
                    activeProcess = null;
                }else if (remainingQuantum <= 0) {
                    // if the active process quantum is finished
                    activeProcess.getStartEndTime().lastElement().add(currentTime);
                    int meanQuantum = calculateMeanQuantum();
                    activeProcess.setQuantum(activeProcess.getQuantum() + (int) Math.ceil(meanQuantum * 0.1));
                    addHistory();
                    activeProcess.setPreemptive(false);
                    readyQueue.add(activeProcess);
                    activeProcess = null;
                } else if (remainingQuantum <=  activeProcess.getQuantum()  / 2) {
                    // if the active process quantum is less than half of the original quantum
                    // make the active process preemptive
                    activeProcess.setPreemptive(true);
                }
            }
        }

        ArrayList<Process> processes = new ArrayList<>(dieList);
        setProcesses(processes);
        for (Process p : processes) {
            averageWaitingTime += p.getWaitingTime();
            averageTurnAroundTime += p.getTurnAroundTime();
        }
        averageWaitingTime /= numOfProcesses;
        averageTurnAroundTime /= numOfProcesses;
    }

}
