import GUI.OutputScreen;
import Process.Process;
import Schedulers.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numOfProcesses, RRQuantum, contextSwitching;
        System.out.println("Welcome to CPU Schedulers Simulator!");

        while (true) {
            System.out.println("-------------------------------------------");
            Scanner scan = new Scanner(System.in);
            System.out.println("What is the number of processes?");
            System.out.print(">> ");
            numOfProcesses = scan.nextInt();

            System.out.println("What is the Round-Robin time quantum?");
            System.out.print(">> ");
            RRQuantum = scan.nextInt();

            System.out.println("What is the context switching time?");
            System.out.print(">> ");
            contextSwitching = scan.nextInt();

            int IDs = (int)(Math.random() * (5000 - 1) + 1);

            ArrayList<Process> processes = new ArrayList<>();
            for(int i = 0;i<numOfProcesses;i++){
                System.out.println("-------------------------------------------");
                int arrivalTime, burstTime, priority;
                System.out.println("Process " + (i+1) + " name:");
                System.out.print(">> ");
                String name = scan.next();
                System.out.println("Process " + (i+1) + " color (HEXA-#rrggbb):");
                System.out.print(">> ");
                String color = scan.next();
                System.out.println("Process " + (i+1) + " arrival time:");
                System.out.print(">> ");
                arrivalTime = scan.nextInt();
                System.out.println("Process " + (i+1) + " burst time:");
                System.out.print(">> ");
                burstTime = scan.nextInt();
                System.out.println("Process " + (i+1) + " priority number:");
                System.out.print(">> ");
                priority = scan.nextInt();
                Process process = new Process(name, color, arrivalTime, burstTime, priority, RRQuantum, IDs++, i);
                processes.add(process);
            }
            boolean showOptions = true;
            String options = """ 
                    \nPlease choose one of the following options:
                    1. SJF - Non-preemptive
                    2. SRTF - Preemptive
                    3. Priority Scheduling - Non-preemptive
                    4. AG Scheduling
                    5. Return and add new inputs
                    6. Exit
                    """;
            while (showOptions) {
                ArrayList<Process> currProcesses = new ArrayList<>();
                // deep copy processes
                for(int i = 0;i<numOfProcesses;i++){
                    Process process = processes.get(i);
                    currProcesses.add(new Process(
                            process.getName(), process.getColor(), process.getArrivalTime(),
                            process.getBurstTime(), process.getPriority(), process.getQuantum(), process.getPID(), process.getIndex()
                    ));
                }
                System.out.print(options);
                System.out.print(">> ");
                String choice = scan.next();
                Scheduler scheduler = null;
                switch (choice) {
                    case "1" -> scheduler = new SJF(numOfProcesses, currProcesses, contextSwitching);
                    case "2" -> scheduler = new SRTF(numOfProcesses, currProcesses);
                    case "3" -> scheduler = new PriorityScheduling(numOfProcesses, currProcesses);
                    case "4" -> scheduler = new AGScheduling(numOfProcesses, currProcesses);
                    case "5" -> showOptions = false;
                    case "6" -> {
                        System.out.println("Thank you for using our simulator!");
                        System.exit(0);
                    }
                    default -> System.out.println("Incorrect number, please try again & choose a number between [1-5]");
                }
                if (scheduler != null) {
                    scheduler.simulate();
                    // output form for the selected scheduler
                    new OutputScreen(scheduler);
                }
            }
        }
    }
}