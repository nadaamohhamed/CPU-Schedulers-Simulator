package Process;

public class Process {
    private int arrivalTime;
    private int burstTime;
    private int turnAroundTime;
    private int remainingTime;
    private int waitingTime;
    private int randomFunction;
    private int priority;
    private int quantum;
    private int PID;
    private String name;
    private String color;

    public Process(String name, String color, int arrivalTime, int burstTime, int priority, int quantum, int id) {
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority; // edit - convert to priority?
        this.quantum = quantum; // edit - convert to AG?
        this.turnAroundTime = 0;
        this.remainingTime = 0;
        this.waitingTime = 0;
        this.randomFunction = (int)(Math.random() * 20);
        this.PID = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getRandomFunction() {
        return randomFunction;
    }

    public void setRandomFunction(int randomFunction) {
        this.randomFunction = randomFunction;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
