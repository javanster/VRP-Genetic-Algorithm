package com.p2.individual_generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.p2.base_classes.Individual;
import com.p2.interfaces.IndividualGenerator;
import com.p2.run_utils.ParameterObject;

/**
 * A class for generating a new individual greedily. Assigns some destinations randomly
 * before adding additional destinations to the routes where the assignment would increase
 * travel time the least
 */
public class GreedyIndividualGenerator implements IndividualGenerator{
    
    @Override
    public Individual generateIndividual(ParameterObject parameters,  int clusterCount) {
        int nurseCount = parameters.PROBLEM_INSTANCE.getNbr_nurses();
        List<Integer> destinations = parameters.PROBLEM_INSTANCE.getDestinations();

        List<List<Integer>> destinationPartitions = new ArrayList<>();
        Random random = new Random();
        List<Integer> destinationsCopy = new ArrayList<>(destinations);

        for (int i = 0; i < nurseCount; i++) {
            destinationPartitions.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < destinationPartitions.size(); i++) {
            int randomDestinationIndex = random.nextInt(destinationsCopy.size());
            int randomPartitionIndex = random.nextInt(destinationPartitions.size());
            int destination = destinationsCopy.remove(randomDestinationIndex);
            destinationPartitions.get(randomPartitionIndex).add(destination);
        }

        for (Integer destination : destinationsCopy) {
            List<Integer> chosenPartition = destinationPartitions.get(0);
            double chosenPartitionTravelTime = Double.MAX_VALUE;
            for (int i = 0; i < destinationPartitions.size(); i++) {
                List<Integer> partition = destinationPartitions.get(i);
                if (partition.size() > 0) {
                    int partitionLastDestination = partition.get(partition.size() - 1);
                    double travelTime = parameters.PROBLEM_INSTANCE.getTravelTime(partitionLastDestination, destination);
                    if (travelTime < chosenPartitionTravelTime) {
                        chosenPartition = partition;
                        chosenPartitionTravelTime = travelTime;
                    }
                }
            }
            chosenPartition.add(destination);
        }

        Collections.shuffle(destinationPartitions);

        return new Individual(destinationPartitions);
    }
    
}
