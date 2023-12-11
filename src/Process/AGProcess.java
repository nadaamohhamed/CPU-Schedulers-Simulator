package Process;

import java.util.Vector;

public class AGProcess extends Process{

    public Vector<Integer> quantumUpdates = new Vector<>();
    private int AGFactor;
    private boolean Preemptive = false;
    public AGProcess(String name, String color, int arrivalTime, int burstTime, int priority, int quantum, int id, int index) {
        super(name, color, arrivalTime, burstTime, priority, quantum, id, index);
        quantumUpdates.add(quantum);
    }
    public AGProcess(Process process){
        super(process.getName(), process.getColor(), process.getArrivalTime(), process.getBurstTime(), process.getPriority(), process.getQuantum(), process.getPID(), process.getIndex());
        quantumUpdates.add(process.getQuantum());
    }
    public boolean isPreemptive() {
        return Preemptive;
    }
    public void setPreemptive(boolean Preemptive) {
        this.Preemptive = Preemptive;
    }
    public int getAGFactor() {
        return AGFactor;
    }

    public void setAGFactor(int AGFactor) {
        this.AGFactor = AGFactor;
    }
}