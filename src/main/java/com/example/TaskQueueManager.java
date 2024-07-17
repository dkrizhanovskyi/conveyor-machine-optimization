package com.example;

import java.util.PriorityQueue;

/**
 * TaskQueueManager is responsible for managing the task queue with priority.
 * Tasks are stored in a priority queue, where tasks with higher priority are processed first.
 */
public class TaskQueueManager {
    private PriorityQueue<ProductionData> taskQueue;

    /**
     * Creates a new TaskQueueManager with an empty priority queue.
     * Tasks are ordered by their priority in descending order.
     */
    public TaskQueueManager() {
        taskQueue = new PriorityQueue<>((task1, task2) -> Double.compare(task2.getPriority(), task1.getPriority()));
    }

    /**
     * Adds a new task to the queue.
     *
     * @param task The production data representing the task to be added
     */
    public void addTask(ProductionData task) {
        taskQueue.add(task);
    }

    /**
     * Retrieves and removes the next task from the queue.
     * The task with the highest priority is returned.
     *
     * @return The next task in the queue
     */
    public ProductionData getNextTask() {
        return taskQueue.poll();
    }

    /**
     * Checks if the task queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}
