package com.p2.individual_generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.p2.base_classes.Individual;
import com.p2.interfaces.IndividualGenerator;
import com.p2.problem_instance.ProblemInstance;
import com.p2.run_utils.ParameterObject;

/**
 * A class for creating an individual based on clustering and sorting after travel time
 */
public class ClusteringTravelTime_IG implements IndividualGenerator {

    KMeansClusterer clusterer = new KMeansClusterer();

    @Override
    public Individual generateIndividual(ParameterObject parameters, int clusterCount) {
        ProblemInstance problemInstance = parameters.PROBLEM_INSTANCE;
        int nurseCount = parameters.PROBLEM_INSTANCE.getNbr_nurses();

        List<List<Integer>> newIndividualList = new ArrayList<>();
        List<List<Integer>> clusters = clusterer.kMeansClustering(parameters, clusterCount);

        for (int i = 0; i < clusters.size(); i++) {
            List<Integer> cluster = clusters.get(i);
            List<Integer> adjustedCluster = new ArrayList<>();
            int destinationClosestToDepot = cluster.stream().min((a, b) -> Double.compare(problemInstance.getTravelTime(0, a), problemInstance.getTravelTime(0, b))).get();
            int indexOfDestinationClosestToDepot = cluster.indexOf(destinationClosestToDepot);
            cluster.remove(indexOfDestinationClosestToDepot);
            adjustedCluster.add(destinationClosestToDepot);

            while (cluster.size() > 0) {
                int prevDestination = adjustedCluster.get(adjustedCluster.size() - 1);
                int nextDestination = cluster.get(0);
                double travelTime = problemInstance.getTravelTime(prevDestination, nextDestination);
                for (int j = 1; j < cluster.size(); j++) {
                    int destination = cluster.get(j);
                    double travelTimeFromPrev = problemInstance.getTravelTime(prevDestination, destination);
                    if (travelTimeFromPrev < travelTime) {
                        nextDestination = destination;
                        travelTime = travelTimeFromPrev;
                    }
                }
                adjustedCluster.add(nextDestination);
                int indexOfNextDestination = cluster.indexOf(nextDestination);
                cluster.remove(indexOfNextDestination);
            }

            newIndividualList.add(adjustedCluster);
        }

        while (newIndividualList.size() < nurseCount) {
            newIndividualList.add(new ArrayList<>());
        }

        Collections.shuffle(newIndividualList);

        return new Individual(newIndividualList);
    }

    
}
