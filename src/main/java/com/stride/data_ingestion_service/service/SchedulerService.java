package com.stride.data_ingestion_service.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SchedulerService {

    private final Runnable task;
    private final long initialDelay;
    private final long delay;
    private final TimeUnit timeUnit;
    private final ScheduledExecutorService scheduler;


    @Autowired
    public SchedulerService(Runnable task) {
        this.task = task;
        this.initialDelay = 5;
        this.delay = 60;
        this.timeUnit = TimeUnit.SECONDS;

        this.scheduler = Executors.newScheduledThreadPool(3);
    }

    @PostConstruct
    public void start() {
        scheduler.scheduleAtFixedRate(task, initialDelay, delay, timeUnit);
    }

    @PreDestroy
    public void stop() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Scheduler timed out. Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            scheduler.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
