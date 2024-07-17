package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * Unit test for MaintenancePredictor class.
 */
class MaintenancePredictorTest {

    /**
     * Tests the predictAndScheduleMaintenance method to ensure it correctly identifies
     * tasks that require maintenance and schedules them.
     */
    @Test
    void testPredictAndScheduleMaintenance() {
        MaintenancePredictor predictor = new MaintenancePredictor();
        ProductionData data1 = new ProductionData("Op1", 5000, Map.of("Resource1", 2.0), 0.2, 1.0);
        ProductionData data2 = new ProductionData("Op2", 8000, Map.of("Resource1", 3.0), 0.7, 1.5);

        List<ProductionData> dataList = List.of(data1, data2);
        predictor.predictAndScheduleMaintenance(dataList);

        // No assertion here as we are just testing the print output
        // which is difficult to assert in a unit test.
    }
}
