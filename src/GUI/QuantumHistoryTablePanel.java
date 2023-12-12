package GUI;

import Process.AGProcess;
import Schedulers.AGScheduling;
import Schedulers.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuantumHistoryTablePanel extends JPanel {

    private ArrayList<String> columnNames;
    private ArrayList<ArrayList<String>> quantumTable;

    private Scheduler scheduler;
    private final int cellWidth = 80;
    private final int cellHeight = 30;
    private final int xOffset = 100;
    private final int yOffset = 50;

    private int preferredWidth;
    private int preferredHeight;

    public QuantumHistoryTablePanel(Scheduler scheduler) {
        if(!(scheduler instanceof AGScheduling))
            return;
        this.scheduler = scheduler;
        initData();
        calculateDimension();
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }

    private void calculateDimension(){
        preferredWidth = cellWidth * columnNames.size() + xOffset * 2;
        preferredHeight = cellHeight * (quantumTable.size() + 1) + yOffset * 2;
    }

    private void initData(){
        ArrayList<AGProcess> AGProcesses =  ((AGScheduling) scheduler).getAGProcesses();

        columnNames = new ArrayList<>();
        columnNames.add("Iteration");

        int quantumTableSize = AGProcesses.get(0).getQuantumUpdates().size();
        quantumTable = new ArrayList<>();
        for(int i = 0; i < quantumTableSize; i++){
            quantumTable.add(new ArrayList<>());
            quantumTable.get(i).add("Iteration " + (i + 1));
        }

        for(AGProcess p : AGProcesses){
            columnNames.add(p.getName());
            int iteration = 1;
            for(Integer q : p.getQuantumUpdates()){
                quantumTable.get(iteration - 1).add(Integer.toString(q));
                iteration++;
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set column names appearance
        g.setColor(Color.BLACK);
        g.fillRect(xOffset, yOffset - cellHeight, columnNames.size() * cellWidth, cellHeight);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD));
        for (int i = 0; i < columnNames.size(); i++) {
            g.drawString(columnNames.get(i), xOffset + i * cellWidth + cellWidth / 2 - g.getFontMetrics().stringWidth(columnNames.get(i)) / 2,
                    yOffset - 10);
        }

        // Draw data
        g.setColor(Color.BLACK);
        for (int i = 0; i < quantumTable.size(); i++) {
            for (int j = 0; j < quantumTable.get(i).size(); j++) {
                String value = quantumTable.get(i).get(j);
                g.drawRect(xOffset + j * cellWidth, yOffset + i * cellHeight, cellWidth, cellHeight);
                g.drawString(value, xOffset + j * cellWidth + cellWidth / 2 - g.getFontMetrics().stringWidth(value) / 2,
                        yOffset + i * cellHeight + cellHeight / 2 + g.getFontMetrics().getHeight() / 4);
            }
        }
    }
}
