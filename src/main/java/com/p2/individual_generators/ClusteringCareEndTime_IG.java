package com.p2.individual_generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.p2.base_classes.Individual;
import com.p2.interfaces.IndividualGenerator;
import com.p2.problem_instance.ProblemInstance;
import com.p2.run_utils.ParameterObject;

/**
 * A class for creating an individual based on clustering and sorting after care start time
 * + care duration time
 */
public class ClusteringCareEndTime_IG implements IndividualGenerator {

    KMeansClusterer clusterer = new KMeansClusterer();

    @Override
    public Individual generateIndividual(ParameterObject parameters, int clusterCount) {
        ProblemInstance problemInstance = parameters.PROBLEM_INSTANCE;
        int nurseCount = parameters.PROBLEM_INSTANCE.getNbr_nurses();

        List<List<Integer>> clusters = clusterer.kMeansClustering(parameters, clusterCount);

        for (List<Integer> cluster : clusters) {
            cluster.sort((a, b) -> Integer.compare(problemInstance.getPatients().get(a).getStart_time() + problemInstance.getPatients().get(a).getCare_time(), 
            problemInstance.getPatients().get(b).getStart_time() + problemInstance.getPatients().get(b).getCare_time()));
        }

        while (clusters.size() < nurseCount) {
            clusters.add(new ArrayList<>());
        }

        Collections.shuffle(clusters);

        return new Individual(clusters);
    }
    
}
