package GUI;


import Process.Process;
import Schedulers.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

import static java.lang.Integer.max;

public class ProcessTimelineGraph extends JPanel {

    private static final int RECTANGLE_HEIGHT = 30; // Height for each process rectangle
    private static final int RECTANGLE_GAP = 10; // Gap between each process rectangle
    private static final int TIME_SCALE_FACTOR = 10; // Factor to scale time axis

    private Scheduler scheduler;

    private ArrayList<Process> processes;
    private int preferredWidth;
    private int preferredHeight;


    public ProcessTimelineGraph(Scheduler scheduler){
        this.scheduler = scheduler;
        initProcesses();
        calculatePreferredSize();
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }

    public void initProcesses(){
        processes = this.scheduler.getProcesses();
        processes.sort(Comparator.comparingInt(Process::getIndex));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawProcessTimeline(g);
    }

    private void calculatePreferredSize() {
        preferredWidth = getMaxEndTime() * TIME_SCALE_FACTOR + 180;
        preferredHeight = processes.size() * (RECTANGLE_HEIGHT + RECTANGLE_GAP) + 80;
    }

    private void drawProcessTimeline(Graphics g) {
        int yAxis = 50; // Starting point for Y-axis
        int timeScale = 10; // Scale for time axis

        // Draw Y-axis labels (process names)
        for (int i = 0; i < processes.size(); i++) {
            g.setColor(Color.BLACK);
            g.drawString("Process " + i, 10, yAxis + i * (RECTANGLE_HEIGHT + RECTANGLE_GAP) + RECTANGLE_HEIGHT / 2);
        }

        // Draw X-axis (time)
        for (int time = 0; time <= getMaxEndTime(); time += timeScale) {
            g.setColor(Color.BLACK);
            g.drawLine(getXCoordinate(time), 30, getXCoordinate(time), getHeight() - 10);
            g.drawString(String.valueOf(time), getXCoordinate(time) - 5, 25);
        }

        // Draw rectangles for active times
        int level = 0;
        for(Process p : processes){
            Color color = Color.decode(p.getColor());
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 150)); // Semi-transparent color

            for(Vector<Integer> startEnd : p.getStartEndTime()){
                int startX = getXCoordinate(startEnd.get(0));
                int endX = getXCoordinate(startEnd.get(1));
                int startY = yAxis + level * (RECTANGLE_HEIGHT + RECTANGLE_GAP);
                g.fillRect(startX, startY, endX - startX, RECTANGLE_HEIGHT);
            }
            level++;
        }
    }

    private int getXCoordinate(int time) {
        return time * TIME_SCALE_FACTOR + 100;
    }

    private int getMaxEndTime() {
        int mx = Integer.MIN_VALUE;
        for(Process p : processes){
            for(Vector<Integer> starEnd : p.getStartEndTime()){
                mx = max(starEnd.get(1), mx);
            }
        }
        return mx;
    }


}
