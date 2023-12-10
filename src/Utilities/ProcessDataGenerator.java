package Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ProcessDataGenerator {

    private static final int NUMBER_OF_PROCESSES = 10;

    public static void main(String[] args) {
        generateAndWriteProcessData("process_data.txt");
    }

    public static void generateAndWriteProcessData(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            int quantumValue = getRandomNumberInRange(0, 10);
            int contextSwitchingTime = getRandomNumberInRange(0, 0);


            writer.write(String.valueOf(NUMBER_OF_PROCESSES));
            writer.newLine();
            writer.write(String.valueOf(quantumValue));
            writer.newLine();
            writer.write(String.valueOf(contextSwitchingTime));
            writer.newLine();


            for (int i = 0; i < NUMBER_OF_PROCESSES; i++) {
                String processName = "Process " + i;
                String processColor = generateRandomColorHex();
                int arrivalTime = getRandomNumberInRange(0, 30);
                int burstTime = getRandomNumberInRange(5, 10);
                int priority = getRandomNumberInRange(0, 128);

                writer.write(processName);
                writer.newLine();
                writer.write(processColor);
                writer.newLine();
                writer.write(String.valueOf(arrivalTime));
                writer.newLine();
                writer.write(String.valueOf(burstTime));
                writer.newLine();
                writer.write(String.valueOf(priority));
                writer.newLine();
            }

            System.out.println("Process data has been generated and saved in " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private static String generateRandomColorHex() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02X%02X%02X", r, g, b);
    }
}
