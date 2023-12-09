
package GUI;

import Schedulers.*;

import javax.swing.*;
import java.awt.*;

public class OutputScreen extends javax.swing.JFrame {

    private static Scheduler scheduler;
    private static String scheduleInstance = "NA";
    public OutputScreen(Scheduler scheduler) {
        OutputScreen.scheduler = scheduler;
        initInstance();
        initComponents();
        setVisible(true);
    }

    private void initInstance(){
        if(scheduler instanceof AGScheduling){
            scheduleInstance = "AG Scheduler";
        }
        if(scheduler instanceof PriorityScheduling){
            scheduleInstance = "Priority Scheduler";
        }
        if(scheduler instanceof SJF){
            scheduleInstance = "SJF Scheduler";
        }
        if(scheduler instanceof SRTF){
            scheduleInstance = "SRTF Scheduler";
        }
    }

    private void initComponents() {
        timelinePanel = new ProcessTimelineGraph(scheduler);
        tablePanel = new ProcessTablePanel(scheduler);

        graphScrollPane = new javax.swing.JScrollPane();
        tableScrollPane = new javax.swing.JScrollPane();

        GraphTitle = new javax.swing.JLabel();
        TableTitle = new javax.swing.JLabel();
        BottomPanel = new javax.swing.JPanel();
        StatsTitle = new javax.swing.JLabel();
        scheduleLabel = new javax.swing.JLabel();
        awtLabel = new javax.swing.JLabel();
        atatLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        timelinePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(timelinePanel);
        timelinePanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 683, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 432, Short.MAX_VALUE)
        );

        graphScrollPane.setViewportView(timelinePanel);

        graphScrollPane.setPreferredSize(new Dimension(200, 200));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 620, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 432, Short.MAX_VALUE)
        );

        tableScrollPane.setViewportView(tablePanel);
        tableScrollPane.setPreferredSize(new Dimension(620, 432));

        GraphTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        GraphTitle.setText("CPU Scheduling Graph");

        TableTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        TableTitle.setText("Processes Information");

        StatsTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        StatsTitle.setText("Statistics");

        scheduleLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));
        scheduleLabel.setText("Schedule Name: " + scheduleInstance);

        awtLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));
        awtLabel.setText("AWT: " + scheduler.getAverageWaitingTime());

        atatLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));
        atatLabel.setText("ATAT: " + scheduler.getAverageTurnAroundTime());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(BottomPanel);
        BottomPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(StatsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(awtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(atatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scheduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(StatsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scheduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(awtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(atatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(graphScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(GraphTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(TableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(tableScrollPane))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(GraphTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(graphScrollPane)
                                        .addComponent(tableScrollPane))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    // Variables declaration
    private javax.swing.JLabel GraphTitle;
    private javax.swing.JLabel TableTitle;
    private javax.swing.JLabel StatsTitle;
    private javax.swing.JLabel scheduleLabel;
    private javax.swing.JLabel awtLabel;
    private javax.swing.JLabel atatLabel;
    private javax.swing.JPanel timelinePanel;
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JScrollPane graphScrollPane;
    // End of variables declaration
}
