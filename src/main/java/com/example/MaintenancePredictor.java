package com.example;

import java.util.List;

/**
 * MaintenancePredictor is responsible for predicting and scheduling maintenance tasks
 * based on the probability of maintenance required.
 */
class MaintenancePredictor {

    /**
     * Predicts and schedules maintenance for the given production data.
     * If the maintenance probability of a task is greater than 0.5, it schedules maintenance for that task.
     *
     * @param data List of production data containing maintenance probability information
     */
    public void predictAndScheduleMaintenance(List<ProductionData> data) {
        for (ProductionData task : data) {
            if (task.getMaintenanceProbability() > 0.5) {
                System.out.println("Scheduling maintenance for: " + task.getOperation());
            }
        }
    }
}
