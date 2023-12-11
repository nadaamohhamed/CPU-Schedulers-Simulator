
package GUI;

import Schedulers.*;

import javax.swing.*;
import java.awt.*;

public class OutputScreen extends JFrame {

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
        setTitle("CPU Schedulers Simulator");
        timelinePanel = new ProcessTimelineGraph(scheduler);
        processTablePanel = new ProcessTablePanel(scheduler);
        quantumTablePanel = new QuantumHistoryTablePanel(scheduler);
        bottomPanel = new javax.swing.JPanel();

        timelineScrollPane = new javax.swing.JScrollPane();
        processTableScrollPane = new javax.swing.JScrollPane();
        quantumTableScrollPane = new javax.swing.JScrollPane();

        timelineLabel = new javax.swing.JLabel();
        processTableLabel = new javax.swing.JLabel();
        scheduleLabel = new javax.swing.JLabel();
        awtLabel = new javax.swing.JLabel();
        atatLabel = new javax.swing.JLabel();
        quantumTableLabel = new javax.swing.JLabel();
        statLabel = new javax.swing.JLabel();

        timelineLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24)); // NOI18N
        timelineLabel.setText("CPU Scheduling Graph");

        processTableLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24)); // NOI18N
        processTableLabel.setText("Processes Information");

        scheduleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        scheduleLabel.setText("Schedule Name: " + scheduleInstance);

        awtLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        awtLabel.setText("AWT: " + scheduler.getAverageWaitingTime());

        atatLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        atatLabel.setText("ATAT: " + scheduler.getAverageTurnAroundTime());

        quantumTableLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24)); // NOI18N
        quantumTableLabel.setText("Quantum Update History");

        statLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24)); // NOI18N
        statLabel.setText("Statistics");

        if(!scheduleInstance.equals("AG Scheduler")){
            quantumTablePanel.setVisible(false);
            quantumTableScrollPane.setVisible(false);
            quantumTableLabel.setVisible(false);
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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

        processTableScrollPane.setViewportView(timelinePanel);

        processTableScrollPane.setPreferredSize(timelinePanel.getPreferredSize());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(processTablePanel);
        processTablePanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 620, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 432, Short.MAX_VALUE)
        );

        timelineScrollPane.setViewportView(processTablePanel);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(quantumTablePanel);
        quantumTablePanel.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 620, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 326, Short.MAX_VALUE)
        );
        quantumTableScrollPane.setViewportView(quantumTablePanel);
        quantumTableScrollPane.setPreferredSize(new Dimension(700, 326));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(atatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(awtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scheduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(320, 320, 320)
                                .addComponent(quantumTableScrollPane))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(quantumTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(scheduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(awtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(atatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(processTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(timelineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(statLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(timelineScrollPane)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(processTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(quantumTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(timelineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(processTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(processTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                                        .addComponent(timelineScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(quantumTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(statLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
    }

    // Variables declaration
    private javax.swing.JLabel timelineLabel;
    private javax.swing.JLabel processTableLabel;
    private javax.swing.JLabel statLabel;
    private javax.swing.JLabel scheduleLabel;
    private javax.swing.JLabel awtLabel;
    private javax.swing.JLabel atatLabel;
    private javax.swing.JLabel quantumTableLabel;
    private javax.swing.JPanel timelinePanel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel processTablePanel;
    private javax.swing.JPanel quantumTablePanel;
    private javax.swing.JScrollPane timelineScrollPane;
    private javax.swing.JScrollPane processTableScrollPane;
    private javax.swing.JScrollPane quantumTableScrollPane;
    // End of variables declaration
}
