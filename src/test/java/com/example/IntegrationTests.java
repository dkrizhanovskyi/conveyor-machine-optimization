package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

/**
 * Integration tests for end-to-end process validation.
 */
class IntegrationTests {

    private DataCollector dataCollector;
    private TaskScheduler taskScheduler;
    private RouteOptimizer routeOptimizer;
    private ResourceManager resourceManager;
    private MaintenancePredictor maintenancePredictor;
    private TaskQueueManager taskQueueManager;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        dataCollector = new DataCollector();
        taskScheduler = new TaskScheduler();
        routeOptimizer = new RouteOptimizer();
        resourceManager = new ResourceManager(Map.of("Resource1", 10.0, "Resource2", 5.0));
        maintenancePredictor = new MaintenancePredictor();
        taskQueueManager = new TaskQueueManager();
    }

    /**
     * Tests the end-to-end process with task priorities.
     */
    @Test
    void testEndToEndProcessWithPriority() {
        // Step 1: Collect data with priorities
        ProductionData data1 = new ProductionData("Op1", 5000, Map.of("Resource1", 2.0, "Resource2", 1.0), 0.2, 1.0);
        ProductionData data2 = new ProductionData("Op2", 8000, Map.of("Resource1", 3.0, "Resource2", 2.0), 0.7, 1.5);
        dataCollector.collectData(data1);
        dataCollector.collectData(data2);
        List<ProductionData> collectedData = dataCollector.getMachineData();
        assertEquals(2, collectedData.size());

        // Step 2: Add tasks to queue
        taskQueueManager.addTask(data1);
        taskQueueManager.addTask(data2);

        // Step 3: Schedule tasks from queue
        while (!taskQueueManager.isEmpty()) {
            ProductionData task = taskQueueManager.getNextTask();
            taskScheduler.scheduleTasks(List.of(task));
        }

        // Step 4: Optimize routes with priorities
        Map<String, Double> data = Map.of(
            data1.getOperation(), data1.getTime(),
            data2.getOperation(), data2.getTime()
        );
        Map<String, Double> resourceLimits = Map.of("Resource1", 10.0, "Resource2", 5.0);
        Map<String, Double> priorities = Map.of(
            data1.getOperation(), data1.getPriority(),
            data2.getOperation(), data2.getPriority()
        );
        Map<String, Double> optimizedRoutes = routeOptimizer.optimizeRoutesWithPriority(data, resourceLimits, priorities);
        assertNotNull(optimizedRoutes);
        assertEquals(2, optimizedRoutes.size());

        // Step 5: Allocate priority resources
        boolean resourcesAllocated1 = resourceManager.allocatePriorityResources(data1.getOperation(), data1.getResources(), priorities);
        boolean resourcesAllocated2 = resourceManager.allocatePriorityResources(data2.getOperation(), data2.getResources(), priorities);
        assertTrue(resourcesAllocated1);
        assertTrue(resourcesAllocated2);

        // Step 6: Predict and schedule maintenance
        maintenancePredictor.predictAndScheduleMaintenance(collectedData);
    }
}
