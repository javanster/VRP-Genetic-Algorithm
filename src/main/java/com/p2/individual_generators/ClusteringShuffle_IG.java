package com.p2.individual_generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.p2.base_classes.Individual;
import com.p2.interfaces.IndividualGenerator;
import com.p2.run_utils.ParameterObject;

/**
 * A class for creating an individual based on clustering and shuffling the destinations
 * within the cluster
 */
public class ClusteringShuffle_IG implements IndividualGenerator {

    KMeansClusterer clusterer = new KMeansClusterer();

    @Override
    public Individual generateIndividual(ParameterObject parameters, int clusterCount) {
        int nurseCount = parameters.PROBLEM_INSTANCE.getNbr_nurses();
        Random random = new Random();
        List<Integer> clusterCounts = parameters.CLUSTER_COUNTS;

        int randomClusterCountIndex = random.nextInt(clusterCounts.size());
        int randomClusterCount = clusterCounts.get(randomClusterCountIndex);

        List<List<Integer>> clusters = clusterer.kMeansClustering(parameters, randomClusterCount);
        for (List<Integer> cluster : clusters) {
            Collections.shuffle(cluster);
        }

        while (clusters.size() < nurseCount) {
            clusters.add(new ArrayList<>());
        }

        Collections.shuffle(clusters);

        return new Individual(clusters);
    }
    
}
