package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit test for DataCollector class.
 */
class DataCollectorTest {

    /**
     * Tests the collectData method to ensure it correctly collects and stores production data.
     */
    @Test
    void testCollectData() {
        DataCollector collector = new DataCollector();
        ProductionData data = new ProductionData("Op1", 5000, Map.of("Resource1", 2.0), 0.2, 1.0);

        // Collect the production data
        collector.collectData(data);

        // Assert that the data has been collected
        assertEquals(1, collector.getMachineData().size());
        assertEquals(data, collector.getMachineData().get(0));
    }
}
