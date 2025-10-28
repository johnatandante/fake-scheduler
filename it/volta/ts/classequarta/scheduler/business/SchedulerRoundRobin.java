package it.volta.ts.classequarta.scheduler.business;

import java.util.LinkedList;
import java.util.Queue;

import it.volta.ts.classequarta.scheduler.bean.Processo;

public class SchedulerRoundRobin extends SchedulerBase {

    public static final int TimeSlice = 250; // Time slice in seconds

    private Queue<Processo> processList;

    public SchedulerRoundRobin() {
        processList = new LinkedList<>();

    }

    // Scheduler implementation
    @Override
    public void run() {
        System.out.println("Scheduler Round Robin started.");

        while (true) {
            
            if (processList.isEmpty()) {
                break;
            }

            Processo processo = processList.remove();
                System.out.println("Process\t" + processo.getNome() + "\trunning  \tat time\t" + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
            try {
                processExecutionOrder += processo.getNome() + " ";
                processo.runFor(TimeSlice);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (processo.isCompleted()) {
                System.out.println("Process\t" + processo.getNome() + "\tcompleted\tat time\t" + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                this.processTerminationOrder += processo.getNome() + " ";
                processo.setCompletionTime(System.currentTimeMillis());
            } else {
                processList.add(processo);
            }

        }
        System.out.println("All processes completed.");
    }

    @Override
    public void addProcess(Processo processo) {
        this.processList.add(processo);
        processInfoList.add(processo);
        processo.setStartTime(System.currentTimeMillis());
    }

}
