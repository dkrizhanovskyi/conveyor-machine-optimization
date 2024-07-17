package com.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TaskScheduler is responsible for scheduling and executing tasks.
 * It uses a fixed thread pool executor to handle concurrent task execution.
 */
class TaskScheduler {
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * Schedules the given list of tasks for execution.
     * Each task is submitted to the executor for concurrent execution.
     *
     * @param data List of production data representing the tasks to be scheduled
     */
    public void scheduleTasks(List<ProductionData> data) {
        for (ProductionData task : data) {
            executor.submit(() -> executeTask(task));
        }
    }

    /**
     * Executes a single task.
     * This method is called by the executor for each task.
     *
     * @param task The production data representing the task to be executed
     */
    private void executeTask(ProductionData task) {
        System.out.println("Executing task: " + task.getOperation());
        try {
            Thread.sleep((long) task.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
