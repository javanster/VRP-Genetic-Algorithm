package com.p2.readers_writers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.p2.base_classes.FitnessAnalyzer;
import com.p2.base_classes.Individual;
import com.p2.problem_instance.Patient;
import com.p2.problem_instance.ProblemInstance;
import com.p2.run_utils.ParameterObject;

/**
 * A class for writing a solution found in different formats
 */
public class SolutionWriter {

    FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

    /**
     * Writes an individual to a json file, suitable for visualizing the routes on a map
     * 
     * @param solution The solution found
     * @param problemInstance The problem instance the solution is found for
     */
    public void writeSolutionForMap(Individual solution, ProblemInstance problemInstance) {
        String instanceName = problemInstance.getInstance_name();
        String fileName = "src/main/resources/" + instanceName + "_result_map.json";

        Gson gson = new Gson();

        String jsonString = gson.toJson(solution);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes details about a found solution to a file
     * 
     * @param parameters A parameter object with set parameters
     * @param solution The found solution as an Individual object
     */
    public void writeSolutionDetails(ParameterObject parameters, Individual solution) {
        ProblemInstance problemInstance = parameters.PROBLEM_INSTANCE;
        String instanceName = problemInstance.getInstance_name();
        String fileName = "src/main/resources/" + instanceName + "_result_details.txt";
    
        double fitness = this.fitnessAnalyzer.getFitness(parameters, solution);
        boolean feasible = this.fitnessAnalyzer.isSolutionFeasible(parameters, solution);
    
        StringBuilder detailsBuilder = new StringBuilder();
        detailsBuilder.append("Best solution for ").append(problemInstance.getInstance_name()).append(":\n");
        detailsBuilder.append("\n\nNurse capacity: " + problemInstance.getCapacity_nurse());
        detailsBuilder.append("\nDepot return time: " + problemInstance.getDepot().getReturn_time() + "\n\n");
        detailsBuilder.append(String.format("%-15s %-35s %-35s %-20s %-20s%n", "Nurse", "Route duration (elapsed time)","Route duration (travel time)" , "Covered demand", "Patient sequence"));
        detailsBuilder.append("----------------------------------------------------------------------------------------------------------------------------\n");
    
        int nurseNumber = 1;
        for (List<Integer> route : solution.getRoutes()) {
            StringBuilder patientSequenceBuilder = new StringBuilder();
            double elapsedTime = 0;
            double travelTime = 0;
            int demandOfRoute = 0;
            if (!route.isEmpty()) {
                patientSequenceBuilder.append(" D (0) -> ");
                double timeFromDepotToFirstDestination = problemInstance.getTravelTime(0, route.get(0));
                elapsedTime += timeFromDepotToFirstDestination;
                travelTime += timeFromDepotToFirstDestination;
    
                for (int i = 0; i < route.size(); i++) {
                    int patientNum = route.get(i);
                    Patient patient = problemInstance.getPatients().get(patientNum);
                    int patientStartTime = patient.getStart_time();
                    int patientEndTime = patient.getEnd_time();
                    int patientCareTime = patient.getCare_time();
                    int patientDemand = patient.getDemand();
    
                    if (elapsedTime < patientStartTime) {
                        elapsedTime = patientStartTime;
                    }
                    if ((elapsedTime + patientCareTime) < patientEndTime) {
                        patientSequenceBuilder.append(patientNum).append(" (").append(String.format("%.2f", (double) elapsedTime)).append("-");
                        elapsedTime += patientCareTime;
                        patientSequenceBuilder.append(String.format("%.2f", (double) elapsedTime)).append(")[").append(patientStartTime).append("-").append(patientEndTime).append("] -> ");
                        demandOfRoute += patientDemand;
                    }
                    if (i == route.size() - 1) {
                        double timeToDepot = problemInstance.getTravelTime(patientNum, 0);
                        elapsedTime += timeToDepot;
                        travelTime += timeToDepot;
                    } else {
                        double timeToNextDestination = problemInstance.getTravelTime(patientNum, route.get(i + 1));
                        elapsedTime += timeToNextDestination;
                        travelTime += timeToNextDestination;
                    }
                }
                patientSequenceBuilder.append(" D (").append(String.format("%.2f", (double) elapsedTime)).append(")");
            }
    
            detailsBuilder.append(String.format("%-15s %-35s %-35s %-20s %-20s%n", "Nurse " + nurseNumber, String.format("%.2f", (double) elapsedTime), String.format("%.2f", (double) travelTime), demandOfRoute, patientSequenceBuilder.toString()));
            nurseNumber++;
        }
        detailsBuilder.append("\nObjective value (total duration in travel time): ").append(String.format("%.2f", (double) fitness)).append("\nFeasible: ").append(feasible ? "Yes" : "No");
    
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(detailsBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
