package com.p2.base_classes;

import java.util.List;
import com.p2.problem_instance.Patient;
import com.p2.problem_instance.ProblemInstance;
import com.p2.run_utils.ParameterObject;

public class FitnessAnalyzer {

    /**
     * Calculates and returns the fitness of an individual solution, including added penalty
     * proportional to the number of constraints broken
     * 
     * @param parameters A parameter object with set parameters
     * @param individual The individual to check the fitness of
     * @return The fitness value for the individual
     */
    public double getFitness(ParameterObject parameters, Individual individual) {
        ProblemInstance problemInstance = parameters.PROBLEM_INSTANCE;

        double penaltyValue = 0;
        double totalTravelTime = 0;

        for (List<Integer> route : individual.getRoutes()) {
            double elapsedTimeOfRoute = 0;
            int demandOfRoute = 0;

            if (route.size() > 0) {
                double depotToFirstDestTime = problemInstance.getTravelTime(0, route.get(0));
                elapsedTimeOfRoute += depotToFirstDestTime;
                totalTravelTime += depotToFirstDestTime;

                for (int i = 0; i < route.size(); i++) {
                    int patientNum = route.get(i);
                    Patient patient = problemInstance.getPatients().get(patientNum);
                    int patientStartTime = patient.getStart_time();
                    int patientEndTime = patient.getEnd_time();
                    int patientCareTime = patient.getCare_time();
                    int patientDemand = patient.getDemand();
                    if (elapsedTimeOfRoute < patientStartTime) {
                        elapsedTimeOfRoute = patientStartTime;
                    }
                    if ((elapsedTimeOfRoute + patientCareTime) > patientEndTime) {
                        penaltyValue += 50;
                    } else {
                        elapsedTimeOfRoute += patientCareTime;
                        demandOfRoute += patientDemand;
                    }
                    if (i == route.size() - 1) {
                        double lastDestToDepotTime = problemInstance.getTravelTime(patientNum, 0);
                        elapsedTimeOfRoute += lastDestToDepotTime;
                        totalTravelTime += lastDestToDepotTime;
                    } else {
                        double travelTimeToNextDest = problemInstance.getTravelTime(patientNum, route.get(i + 1));
                        elapsedTimeOfRoute += travelTimeToNextDest;
                        totalTravelTime += travelTimeToNextDest;
                    }
                }

                if (elapsedTimeOfRoute >= problemInstance.getDepot().getReturn_time()) {
                    penaltyValue += 100;
                } 
                if (demandOfRoute > problemInstance.getCapacity_nurse()) {
                    penaltyValue += 100;
                }
            }
        }
        
        return totalTravelTime + penaltyValue;
    }

    /**
     * Checks whether a solution is feasable, i.e. satisfies all constraints
     * 
     * @param parameters A parameter object with set parameters
     * @param individual The individual to check the feasablility of
     * @return Whether the individual is feasable or not
     */
    public boolean isSolutionFeasible(ParameterObject parameters, Individual individual) {
        ProblemInstance problemInstance = parameters.PROBLEM_INSTANCE;

        for (List<Integer> route : individual.getRoutes()) {
            double elapsedTime = 0;
            int demandOfRoute = 0;
            if (route.size() > 0) {
                elapsedTime += problemInstance.getTravelTime(0, route.get(0));
                for (int i = 0; i < route.size(); i++) {
                    int patientNum = route.get(i);
                    Patient patient = problemInstance.getPatients().get(patientNum);
                    int patientStartTime = patient.getStart_time();
                    int patientEndTime = patient.getEnd_time();
                    int patientCareTime = patient.getCare_time();
                    int patientDemand = patient.getDemand();
                    if (elapsedTime > patientEndTime) {
                        return false;
                    }
                    if (elapsedTime < patientStartTime) {
                        elapsedTime += patientStartTime - elapsedTime;
                    }
                    if ((elapsedTime + patientCareTime) > patientEndTime) {
                        return false;
                    } else {
                        elapsedTime += patientCareTime;
                        demandOfRoute += patientDemand;
                    }
                    if (i == route.size() - 1) {
                        elapsedTime += problemInstance.getTravelTime(patientNum, 0);
                    } else {
                        elapsedTime += problemInstance.getTravelTime(patientNum, route.get(i + 1));
                    }
                }
                if (elapsedTime >= problemInstance.getDepot().getReturn_time()) {
                    return false;
                } 
                if (demandOfRoute > problemInstance.getCapacity_nurse()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Finds and returns the fittest individual in the population
     * 
     * @param parameters A parameter object with set parameters
     * @param population
     * @return The fittest individual
     */
    public Individual getBestIndividualInPopulation(ParameterObject parameters, Population population) {
        return population.getIndividuals().stream().min((a, b) -> Double.compare(getFitness(parameters, a), getFitness(parameters, b))).get();
    }

    /**
     * Finds the fittest individual in the population and returns the fitness value of it
     * 
     * @param parameters
     * @param population
     * @return The fitness value of the fittest individual in the population
     */
    public double getFitnessOfBestIndividual(ParameterObject parameters, Population population) {
        Individual bestIndividual = this.getBestIndividualInPopulation(parameters, population);
        return this.getFitness(parameters, bestIndividual);
    }

}
