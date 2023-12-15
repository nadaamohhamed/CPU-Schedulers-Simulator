import GUI.OutputScreen;
import Process.Process;
import Schedulers.*;
import Utilities.ProcessDataGenerator;
import Utilities.ProcessDataReader;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numOfProcesses, RRQuantum, contextSwitching;
        boolean readingFromFile = true;
        Scanner scan = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();
        System.out.println("Welcome to CPU Schedulers Simulator!");
        System.out.println("-------------------------------------------------------------");

        while (true) {
            // uncomment the following line if you want to enter the inputs manually from the console
             // readingFromFile = false;
            if(readingFromFile){
                ProcessDataGenerator.generateAndWriteProcessData("process_data.txt");
                ProcessDataReader.readProcessesFromFile("process_data.txt");
                numOfProcesses = ProcessDataReader.numOfProcesses;
                RRQuantum = ProcessDataReader.quantumValue;
                contextSwitching = ProcessDataReader.contextSwitchingTime;
                processes = ProcessDataReader.processes;
                System.out.println("-------------------------------------------------------------");
            }else {
                System.out.println("What is the number of processes?");
                System.out.print(">> ");
                numOfProcesses = scan.nextInt();

                System.out.println("What is the Round-Robin time quantum?");
                System.out.print(">> ");
                RRQuantum = scan.nextInt();

                System.out.println("What is the context switching time?");
                System.out.print(">> ");
                contextSwitching = scan.nextInt();

                // generate random PIDs for processes
                int IDs = (int) (Math.random() * (5000 - 1) + 1);

                for (int i = 0; i < numOfProcesses; i++) {
                    System.out.println("-------------------------------------------------------------");
                    int arrivalTime, burstTime, priority;
                    System.out.println("Process " + (i + 1) + " name:");
                    System.out.print(">> ");
                    String name = scan.next();
                    System.out.println("Process " + (i + 1) + " color (HEXA-#rrggbb):");
                    System.out.print(">> ");
                    String color = scan.next();
                    System.out.println("Process " + (i + 1) + " arrival time:");
                    System.out.print(">> ");
                    arrivalTime = scan.nextInt();
                    System.out.println("Process " + (i + 1) + " burst time:");
                    System.out.print(">> ");
                    burstTime = scan.nextInt();
                    System.out.println("Process " + (i + 1) + " priority number:");
                    System.out.print(">> ");
                    priority = scan.nextInt();
                    Process process = new Process(name, color, arrivalTime, burstTime, priority, IDs++, i);
                    processes.add(process);
                }
            }
            boolean showOptions = true;
            String options = """ 
                    \nPlease choose one of the following options:
                    1. SJF - Non-preemptive
                    2. SRTF - Preemptive
                    3. Priority Scheduling - Non-preemptive
                    4. AG Scheduling - Preemptive
                    5. Return and add new inputs
                    6. Exit
                    """;
            while (showOptions) {
                ArrayList<Process> currProcesses = new ArrayList<>();
                // deep copy processes
                for (Process p : processes)
                    currProcesses.add(p.copy());

                System.out.print(options);
                System.out.print(">> ");
                String choice = scan.next();
                Scheduler scheduler = null;
                switch (choice) {
                    case "1" -> scheduler = new SJF(numOfProcesses, currProcesses, contextSwitching);
                    case "2" -> scheduler = new SRTF(numOfProcesses, currProcesses);
                    case "3" -> scheduler = new PriorityScheduling(numOfProcesses, currProcesses);
                    case "4" -> scheduler = new AGScheduling(numOfProcesses, currProcesses, RRQuantum);
                    case "5" -> showOptions = false;
                    case "6" -> {
                        System.out.println("Thank you for using our simulator!");
                        System.exit(0);
                    }
                    default -> System.out.println("Incorrect number, please try again & choose a number between [1-5]");
                }
                if (scheduler != null) {
                    // simulate and output processes information
                    scheduler.simulate();
                    scheduler.printProcesses();
                    // GUI screen for the process execution order
                    new OutputScreen(scheduler);
                }
            }
        }
    }
}