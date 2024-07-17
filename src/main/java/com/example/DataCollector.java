package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DataCollector is responsible for collecting and storing production data.
 * It uses a synchronized list to ensure thread safety when accessing the data.
 */
class DataCollector {
    // Synchronized list to store production data
    private List<ProductionData> machineDataList = Collections.synchronizedList(new ArrayList<>());

    /**
     * Collects and adds production data to the list.
     *
     * @param data Production data to be collected
     */
    public void collectData(ProductionData data) {
        machineDataList.add(data);
    }

    /**
     * Returns a copy of the collected production data.
     *
     * @return List of collected production data
     */
    public List<ProductionData> getMachineData() {
        return new ArrayList<>(machineDataList);
    }
}
