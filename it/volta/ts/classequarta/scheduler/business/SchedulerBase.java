package it.volta.ts.classequarta.scheduler.business;

import java.util.ArrayList;
import java.util.List;

import it.volta.ts.classequarta.scheduler.bean.Processo;

public abstract class SchedulerBase implements Runnable {
    
    protected final List<Processo> processInfoList = new ArrayList<>();

    protected String processExecutionOrder = "";
    protected String processTerminationOrder = "";

    public abstract void addProcess(Processo processo);
    
    public abstract void run();

    public String getProcessExecutionOrder() {
        return processExecutionOrder;
    }

    public String getProcessProcessTerminationOrder() {
        return processTerminationOrder;
    }

    public double getAverageExecutionTime() {
        
        double totalTime = processInfoList
            .stream()
            .map( item -> item.getExecutionTime() )
            .reduce((a, b) -> a + b )
            .get();
        return totalTime / (double)processInfoList.size();

    }

    public void printStats() {
        
        System.out.println("Execution order: " + this.processExecutionOrder);
        System.out.println("Termination order: " + this.processTerminationOrder);
        System.out.println("Average Execution Time: " + this.getAverageExecutionTime() + " ms");

    }

}
