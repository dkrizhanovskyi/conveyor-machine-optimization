package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit test for ProductionData class.
 */
class ProductionDataTest {

    /**
     * Tests the creation of a ProductionData object to ensure all fields are correctly set.
     */
    @Test
    void testProductionDataCreation() {
        Map<String, Double> resources = Map.of("Resource1", 2.0, "Resource2", 1.0);
        ProductionData data = new ProductionData("Op1", 5000, resources, 0.2, 1.0);

        assertEquals("Op1", data.getOperation());
        assertEquals(5000, data.getTime());
        assertEquals(resources, data.getResources());
        assertEquals(0.2, data.getMaintenanceProbability());
        assertEquals(1.0, data.getPriority());
    }
}
