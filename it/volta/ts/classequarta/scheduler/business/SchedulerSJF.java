package it.volta.ts.classequarta.scheduler.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import it.volta.ts.classequarta.scheduler.bean.Processo;


public class SchedulerSJF extends SchedulerBase {

    public static final int TimeSlice = 250; // Time slice in seconds

    private ArrayList<Processo> processList;

    public SchedulerSJF() {
        processList = new ArrayList<>();

    }
    @Override
    public void addProcess(Processo processo) {
        processList.add(processo);
        processInfoList.add(processo);
        processo.setStartTime(System.currentTimeMillis());
    }

    // Scheduler implementation
    @Override
    public void run() {
        System.out.println("Scheduler SJF started.");

        processList.sort( (item1, item2) -> {
            return item1.getDurata() > item2.getDurata() ? 1 : -1;
        } );

        for (int i = 0; i < processList.size(); i++) {
            Processo processo = processList.get(i);
            System.out.println("Process\t" + processo.getNome() + "\trunning   \tat time\t" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            try {
                // Simula il tempo di esecuzione
                processo.runFor(processo.getDurata());
                processo.setCompletionTime(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Process\t" + processo.getNome() +  "\tcompleted \tat time\t" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
        }
        System.out.println("All processes completed.");

    }

}
