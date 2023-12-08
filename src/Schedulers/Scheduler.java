package Schedulers;

import Process.Process;

import java.util.ArrayList;

public abstract class Scheduler {
    protected int numOfProcesses;
    protected String name;
    protected float averageWaitingTime;
    protected float averageTurnAroundTime;
    protected ArrayList<Process> processes;
    protected ArrayList<Process> outputProcesses; // edit ?

    public Scheduler(int numOfProcesses, ArrayList<Process> processes, String name) {
        this.numOfProcesses = numOfProcesses;
        this.processes = processes;
        this.name = name;
        this.averageWaitingTime = 0;
        this.averageTurnAroundTime = 0;
    }

    public abstract void simulate();

    public ArrayList<Process> getOutputProcesses() {
        return outputProcesses;
    }
}
