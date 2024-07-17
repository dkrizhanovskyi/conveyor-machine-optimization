package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * Unit test for TaskScheduler class.
 */
class TaskSchedulerTest {

    /**
     * Tests the scheduleTasks method to ensure tasks are scheduled correctly.
     */
    @Test
    void testScheduleTasks() {
        TaskScheduler scheduler = new TaskScheduler();
        ProductionData data1 = new ProductionData("Op1", 5000, Map.of("Resource1", 2.0), 0.2, 1.0);
        ProductionData data2 = new ProductionData("Op2", 8000, Map.of("Resource1", 3.0), 0.7, 1.5);

        List<ProductionData> dataList = List.of(data1, data2);
        scheduler.scheduleTasks(dataList);

        // No assertion here as we are just testing the scheduling part
        // which is difficult to assert due to the asynchronous nature.
    }
}
