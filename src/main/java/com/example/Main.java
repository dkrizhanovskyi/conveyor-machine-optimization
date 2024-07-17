package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class to execute the application.
 */
public class Main {

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Data representing the time taken for each operation
        Map<String, Double> data = new HashMap<>();
        data.put("Op1", 5000.0);
        data.put("Op2", 8000.0);
        data.put("Op3", 3000.0);

        // Resource limits available for the operations
        Map<String, Double> resourceLimits = new HashMap<>();
        resourceLimits.put("Resource1", 10.0);
        resourceLimits.put("Resource2", 5.0);

        // Creating an instance of RouteOptimizer
        RouteOptimizer optimizer = new RouteOptimizer();

        // Optimizing routes based on data and resource limits
        Map<String, Double> optimizedRoutes = optimizer.optimizeRoutes(data, resourceLimits);

        // Printing the optimized routes
        System.out.println("Optimized Routes: " + optimizedRoutes);
    }
}
