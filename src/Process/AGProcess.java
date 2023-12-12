package Process;

import java.util.Vector;

public class AGProcess extends Process{
    private int AGFactor;
    private boolean Preemptive;
    private Vector<Integer> quantumUpdates = new Vector<>();
    private int randomFunction;
    private int quantum;
    public AGProcess(String name, String color, int arrivalTime, int burstTime, int priority, int quantum, int id, int index) {
        super(name, color, arrivalTime, burstTime, priority, id, index);
        this.randomFunction = (int) (Math.random() * 20);
        this.quantum = quantum;
        this.Preemptive = false;
        quantumUpdates.add(quantum);
    }
    public AGProcess(Process process, int quantum){
        super(process.getName(), process.getColor(), process.getArrivalTime(), process.getBurstTime(), process.getPriority(), process.getPID(), process.getIndex());
        this.randomFunction = (int) (Math.random() * 20);
        this.quantum = quantum;
        this.Preemptive = false;
        quantumUpdates.add(quantum);
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

    public Vector<Integer> getQuantumUpdates() {
        return quantumUpdates;
    }

    public void setQuantumUpdates(Vector<Integer> quantumUpdates) {
        this.quantumUpdates = quantumUpdates;
    }

    public int getRandomFunction() {
        return randomFunction;
    }

    public void setRandomFunction(int randomFunction) {
        this.randomFunction = randomFunction;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
}