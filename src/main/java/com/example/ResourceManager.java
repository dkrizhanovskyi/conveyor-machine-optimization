package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ResourceManager is responsible for managing the allocation and release of resources.
 * It uses a concurrent hash map to handle resources in a thread-safe manner.
 */
class ResourceManager {
    private Map<String, Double> currentResources;

    /**
     * Creates a new ResourceManager with the given initial resources.
     *
     * @param initialResources The initial resources available
     */
    public ResourceManager(Map<String, Double> initialResources) {
        this.currentResources = new ConcurrentHashMap<>(initialResources);
    }

    /**
     * Allocates resources required for an operation.
     *
     * @param operation The name of the operation
     * @param requiredResources The resources required for the operation
     * @return true if resources are successfully allocated, false otherwise
     */
    public synchronized boolean allocateResources(String operation, Map<String, Double> requiredResources) {
        // Check if all required resources are available
        for (Map.Entry<String, Double> entry : requiredResources.entrySet()) {
            String resource = entry.getKey();
            Double requiredAmount = entry.getValue();

            if (currentResources.getOrDefault(resource, 0.0) < requiredAmount) {
                return false;
            }
        }

        // Deduct the required resources from the current resources
        for (Map.Entry<String, Double> entry : requiredResources.entrySet()) {
            String resource = entry.getKey();
            Double requiredAmount = entry.getValue();
            currentResources.put(resource, currentResources.get(resource) - requiredAmount);
        }

        return true;
    }

    /**
     * Releases resources after an operation is completed.
     *
     * @param usedResources The resources used by the operation
     */
    public synchronized void releaseResources(Map<String, Double> usedResources) {
        // Add the used resources back to the current resources
        for (Map.Entry<String, Double> entry : usedResources.entrySet()) {
            String resource = entry.getKey();
            Double usedAmount = entry.getValue();
            currentResources.put(resource, currentResources.get(resource) + usedAmount);
        }
    }

    /**
     * Allocates resources required for an operation, considering resource priorities.
     *
     * @param operation The name of the operation
     * @param requiredResources The resources required for the operation
     * @param priorities The priorities of the resources
     * @return true if resources are successfully allocated, false otherwise
     */
    public synchronized boolean allocatePriorityResources(String operation, Map<String, Double> requiredResources, Map<String, Double> priorities) {
        // Check if all required resources are available considering their priorities
        for (Map.Entry<String, Double> entry : requiredResources.entrySet()) {
            String resource = entry.getKey();
            Double requiredAmount = entry.getValue() * priorities.getOrDefault(resource, 1.0);

            if (currentResources.getOrDefault(resource, 0.0) < requiredAmount) {
                return false;
            }
        }

        // Deduct the required resources from the current resources considering their priorities
        for (Map.Entry<String, Double> entry : requiredResources.entrySet()) {
            String resource = entry.getKey();
            Double requiredAmount = entry.getValue() * priorities.getOrDefault(resource, 1.0);
            currentResources.put(resource, currentResources.get(resource) - requiredAmount);
        }

        return true;
    }
}
