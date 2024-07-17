package com.example;

import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.*;
import org.apache.commons.math3.linear.*;

import java.util.*;

/**
 * RouteOptimizer is responsible for optimizing the routes of production operations
 * based on the given data and resource limits.
 */
public class RouteOptimizer {

    /**
     * Optimizes the routes of production operations based on the given data and resource limits.
     *
     * @param data The data representing the time required for each operation
     * @param resourceLimits The limits of the resources available for the operations
     * @return A map of optimized routes with their respective optimized values
     */
    public Map<String, Double> optimizeRoutes(Map<String, Double> data, Map<String, Double> resourceLimits) {
        List<LinearConstraint> constraints = new ArrayList<>();
        Map<String, Double> operationVars = new HashMap<>();
        String[] operations = data.keySet().toArray(new String[0]);

        // Create constraints based on resource limits
        for (String resource : resourceLimits.keySet()) {
            double[] coefficients = new double[data.size()];
            for (int i = 0; i < data.size(); i++) {
                coefficients[i] = data.get(operations[i]);
            }
            constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, resourceLimits.get(resource)));
        }

        // Set the objective function coefficients based on the data
        double[] objectiveCoefficients = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            objectiveCoefficients[i] = data.get(operations[i]);
        }

        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(objectiveCoefficients, 0);

        // Solve the optimization problem using SimplexSolver
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(100), objectiveFunction, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true));

        // Extract the optimized values for each operation
        for (int i = 0; i < solution.getPoint().length; i++) {
            operationVars.put(operations[i], solution.getPoint()[i]);
        }

        return operationVars;
    }

    /**
     * Optimizes the routes of production operations with consideration of task priorities.
     *
     * @param data The data representing the time required for each operation
     * @param resourceLimits The limits of the resources available for the operations
     * @param priorities The priorities of the operations
     * @return A map of optimized routes with their respective optimized values considering priorities
     */
    public Map<String, Double> optimizeRoutesWithPriority(Map<String, Double> data, Map<String, Double> resourceLimits, Map<String, Double> priorities) {
        List<LinearConstraint> constraints = new ArrayList<>();
        Map<String, Double> operationVars = new HashMap<>();
        String[] operations = data.keySet().toArray(new String[0]);

        // Create constraints based on resource limits and priorities
        for (String resource : resourceLimits.keySet()) {
            double[] coefficients = new double[data.size()];
            for (int i = 0; i < data.size(); i++) {
                coefficients[i] = data.get(operations[i]) * priorities.getOrDefault(operations[i], 1.0);
            }
            constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, resourceLimits.get(resource)));
        }

        // Set the objective function coefficients based on the data and priorities
        double[] objectiveCoefficients = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            objectiveCoefficients[i] = data.get(operations[i]) * priorities.getOrDefault(operations[i], 1.0);
        }

        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(objectiveCoefficients, 0);

        // Solve the optimization problem using SimplexSolver
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(100), objectiveFunction, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true));

        // Extract the optimized values for each operation
        for (int i = 0; i < solution.getPoint().length; i++) {
            operationVars.put(operations[i], solution.getPoint()[i]);
        }

        return operationVars;
    }
}
