package GUI;

import Process.Process;
import Schedulers.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
public class ProcessTablePanel extends JPanel {
    private Scheduler scheduler;
    private ArrayList<Process> processes;


    public ProcessTablePanel(Scheduler scheduler) {
        this.scheduler = scheduler;
        processes = scheduler.getProcesses();
        processes.sort(Comparator.comparingInt(Process::getIndex));
    }

    private void drawTable(Graphics g){
        int rowHeight = 30;
        int columnWidth = getWidth() / 5;

        g.setFont(new Font("Arial", Font.PLAIN, 14));

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), rowHeight);

        g.setColor(Color.WHITE);
        g.drawString("PROCESS", 10, 20);
        g.drawString("COLOR", columnWidth, 20);
        g.drawString("NAME", columnWidth * 2, 20);
        g.drawString("PID", columnWidth * 3, 20);
        g.drawString("PRIORITY", columnWidth * 4, 20);

        g.setColor(Color.BLACK);
        g.drawRect(0, rowHeight, getWidth(), getHeight() - rowHeight);

        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            g.drawString(String.valueOf(process.getIndex()), 10, rowHeight * (i + 2));
            g.setColor(Color.decode(process.getColor()));
            g.fillRect(columnWidth, rowHeight * (i + 1) + 20, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(process.getName(), columnWidth * 2, rowHeight * (i + 2));
            g.drawString(String.valueOf(process.getPID()), columnWidth * 3, rowHeight * (i + 2));
            g.drawString(String.valueOf(process.getPriority()), columnWidth * 4, rowHeight * (i + 2));
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTable(g);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 30 * processes.size() + 50);
    }

}