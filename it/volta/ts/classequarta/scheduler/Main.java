package it.volta.ts.classequarta.scheduler;

import it.volta.ts.classequarta.scheduler.bean.Processo;
import it.volta.ts.classequarta.scheduler.business.SchedulerRoundRobin;
import it.volta.ts.classequarta.scheduler.business.SchedulerBase;
import it.volta.ts.classequarta.scheduler.business.SchedulerSJF;

public class Main {

    public static void main(String[] args) {
        // Entry point of the application
        System.out.println("Scheduler ... ");

        SchedulerBase scheduler = new SchedulerSJF();
        scheduler.addProcess(new Processo("SJF1", 4000));
        scheduler.addProcess(new Processo("SJF2", 3000));
        scheduler.addProcess(new Processo("SJF3", 5000));
        scheduler.addProcess(new Processo("SJF4", 2000));

        SchedulerBase scheduler2 = new SchedulerRoundRobin();
        scheduler2.addProcess(new Processo("RoundR1", 4000));
        scheduler2.addProcess(new Processo("RoundR2", 3000));
        scheduler2.addProcess(new Processo("RoundR3", 5000));
        scheduler2.addProcess(new Processo("RoundR4", 2000));

        Thread t1 = new Thread(scheduler);
        Thread t2 = new Thread(scheduler2);

        try {
            
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println("Schedulers ... ended.");
            scheduler.printStats();
            scheduler2.printStats();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }
}