package com.example;

import java.util.Map;

/**
 * Class representing production operation data.
 */
public class ProductionData {
    private String operation;
    private double time;
    private Map<String, Double> resources;
    private double maintenanceProbability;
    private double priority;

    /**
     * Creates a new ProductionData object.
     *
     * @param operation Name of the operation
     * @param time Time required to complete the operation
     * @param resources Resources required for the operation
     * @param maintenanceProbability Probability of maintenance required
     * @param priority Priority of the operation
     */
    public ProductionData(String operation, double time, Map<String, Double> resources, double maintenanceProbability, double priority) {
        this.operation = operation;
        this.time = time;
        this.resources = resources;
        this.maintenanceProbability = maintenanceProbability;
        this.priority = priority;
    }

    /**
     * Returns the name of the operation.
     *
     * @return Operation name
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Returns the time required to complete the operation.
     *
     * @return Time required for the operation
     */
    public double getTime() {
        return time;
    }

    /**
     * Returns the resources required for the operation.
     *
     * @return Resources
     */
    public Map<String, Double> getResources() {
        return resources;
    }

    /**
     * Returns the probability of maintenance required.
     *
     * @return Maintenance probability
     */
    public double getMaintenanceProbability() {
        return maintenanceProbability;
    }

    /**
     * Returns the priority of the operation.
     *
     * @return Priority
     */
    public double getPriority() {
        return priority;
    }
}
