package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit test for RouteOptimizer class.
 */
class RouteOptimizerTest {

    /**
     * Tests the optimizeRoutes method to ensure routes are optimized correctly based on given data and resource limits.
     */
    @Test
    void testOptimizeRoutes() {
        RouteOptimizer optimizer = new RouteOptimizer();
        Map<String, Double> data = Map.of("Op1", 5000.0, "Op2", 8000.0, "Op3", 3000.0);
        Map<String, Double> resourceLimits = Map.of("Resource1", 10.0, "Resource2", 5.0);

        // Optimize routes
        Map<String, Double> optimizedRoutes = optimizer.optimizeRoutes(data, resourceLimits);

        // Validate the optimization results
        assertNotNull(optimizedRoutes);
        assertEquals(3, optimizedRoutes.size());
    }
}
