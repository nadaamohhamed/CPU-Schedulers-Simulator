package Schedulers;

import Process.Process;

import java.util.ArrayList;
public class SRTF extends Scheduler {
    public SRTF(int numOfProcesses, ArrayList<Process> processes) {
        super(numOfProcesses, processes, "SRTF Schedule");
    }
    @Override
    public void simulate() {

    }
}
