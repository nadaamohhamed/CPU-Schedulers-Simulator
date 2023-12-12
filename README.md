# CPU Schedulers Simulator
## Description
This repository contains Java implementation of a program that simulates the following CPU scheduling algorithms:
### Non-preemptive:
- SJF (Shortest Job First)
- Priority Scheduling
### Preemptive:
- SRTF (Shortest Remaining Time First)
- AG Scheduling (A newly proposed algorithm in which a new factor is suggested to attach with each submitted process called AG-Factor)
## Features
The user can choose the scheduling algorithm he wants to simulate and the number of processes, processes data to be scheduled or our simulator
will generate random processes with random data. For each simulation our program will output the following:
  - Processes execution order (CPU Scheduling Graph)
  - Waiting Time for each process
  - Turnaround Time for each process
  - Average Waiting Time
  - Average Turnaround Time
  - History update of quantum time for each process (AG Scheduling)
  - Processes Information (Name, PID, Color...etc.)
## Preview
- SJF Scheduling simulation output (Non-preemptive)
<img src="/img/SJF.png" alt="Alt text" title="SJF-Scheduling">

- Priority Scheduling simulation output (Non-preemptive)
<img src="/img/Priority.png" alt="Alt text" title="Prioity-Scheduling">

- SRTF Scheduling simulation output (Preemptive)
<img src="/img/SRTF.png" alt="Alt text" title="SRTF-Scheduling">

- AG Scheduling simulation output (Preemptive)
<img src="/img/AG.png" alt="Alt text" title="AG-Scheduling">


