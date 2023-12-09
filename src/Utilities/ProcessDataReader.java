package Utilities;

import Process.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProcessDataReader {

    public static int numOfProcesses;
    public static int quantumValue;
    public static int contextSwitchingTime;
    public static ArrayList<Process> processes;


    public static void readProcessesFromFile(String filename) {
        ArrayList<Process> readProcesses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            numOfProcesses = Integer.parseInt(reader.readLine());
            quantumValue = Integer.parseInt(reader.readLine());
            contextSwitchingTime = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numOfProcesses; i++) {
                String name = reader.readLine();
                String color = reader.readLine();
                int arrivalTime = Integer.parseInt(reader.readLine());
                int burstTime = Integer.parseInt(reader.readLine());
                int priority = Integer.parseInt(reader.readLine());
                int PID = (int) (Math.random() * (5000 - 1) + 1);

                // Create Process instance and add it to the list
                Process process = new Process(name, color, arrivalTime, burstTime, priority, quantumValue, PID, i);
                readProcesses.add(process);
            }
            processes = readProcesses;
            System.out.println("Process data has been read from " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
