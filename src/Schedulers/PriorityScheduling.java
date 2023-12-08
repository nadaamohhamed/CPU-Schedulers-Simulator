package Schedulers;

import Process.Process;

import java.util.ArrayList;

public class PriorityScheduling extends Scheduler {
    public PriorityScheduling(int numOfProcesses, ArrayList<Process> processes) {
        super(numOfProcesses, processes, "Priority Schedule");
    }
    @Override
    public void simulate() {

    }
}
