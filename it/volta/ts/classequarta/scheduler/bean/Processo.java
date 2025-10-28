package it.volta.ts.classequarta.scheduler.bean;

public class Processo {
    
    private String nome;
    private int durata;
    private long startExecutionTime;
    private long endExecutionTime;

    public long getExecutionTime() {
        return endExecutionTime - startExecutionTime;
    }

    public String getNome() {
        return nome;
    }

    public int getDurata() {
        return durata;
    }
    
    public Processo(String nome, int durata) {
        this.nome = nome;
        this.durata = durata;
        startExecutionTime = 0;
    }

    public void runFor(int milliseconds) throws InterruptedException {
        // Simula il tempo di esecuzione
        int runningTime = Math.min(this.durata, milliseconds);
        Thread.sleep(runningTime);
        this.durata -= runningTime;

    }

    public boolean isCompleted() {
        return this.durata <= 0;
    }

    public void setCompletionTime(long timeMillis) {
        this.endExecutionTime = timeMillis;
    }

    public void setStartTime(long timeMillis) {
        this.startExecutionTime = timeMillis;
    }

}
