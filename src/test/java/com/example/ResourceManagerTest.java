package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit test for ResourceManager class.
 */
class ResourceManagerTest {

    /**
     * Tests the allocateResources method to ensure resources are correctly allocated.
     */
    @Test
    void testAllocateResources() {
        ResourceManager resourceManager = new ResourceManager(Map.of("Resource1", 10.0, "Resource2", 5.0));
        Map<String, Double> requiredResources = Map.of("Resource1", 2.0, "Resource2", 1.0);

        // Test successful resource allocation
        assertTrue(resourceManager.allocateResources("Op1", requiredResources));

        // Test unsuccessful resource allocation due to insufficient resources
        assertFalse(resourceManager.allocateResources("Op2", Map.of("Resource1", 11.0, "Resource2", 1.0)));
    }
}
