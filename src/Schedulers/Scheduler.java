package Schedulers;

import Process.Process;

import java.util.ArrayList;

public abstract class Scheduler {
    protected int numOfProcesses;
    protected String name;
    protected float averageWaitingTime;
    protected float averageTurnAroundTime;

    protected ArrayList<Process> processes;

    public Scheduler(int numOfProcesses, ArrayList<Process> processes, String name) {
        this.numOfProcesses = numOfProcesses;
        this.processes = processes;
        this.name = name;
        this.averageWaitingTime = 0;
        this.averageTurnAroundTime = 0;
    }
    public void printProcesses(){
        System.out.println("-------------------------------------------------------------");
        for(int i = 0; i < numOfProcesses; i++){
            System.out.println("Process " +  processes.get(i).getPID() + " - " + processes.get(i).getName());
            System.out.println("    Waiting time: " + processes.get(i).getWaitingTime());
            System.out.println("    Turnaround time: " + processes.get(i).getTurnAroundTime());
            System.out.println("-------------------------------------------------------------");
        }
        System.out.println("Average waiting time: " + averageWaitingTime);
        System.out.println("Average turnaround time: " + averageTurnAroundTime);
        System.out.println("-------------------------------------------------------------");

    }

    public abstract void simulate();


    public float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(float averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public float getAverageTurnAroundTime() {
        return averageTurnAroundTime;
    }

    public void setAverageTurnAroundTime(float averageTurnAroundTime) {
        this.averageTurnAroundTime = averageTurnAroundTime;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}
