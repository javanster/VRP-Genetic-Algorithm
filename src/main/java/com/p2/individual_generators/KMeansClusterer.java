package com.p2.individual_generators;

import java.util.ArrayList;
import java.util.List;

import com.p2.problem_instance.ProblemInstance;
import com.p2.run_utils.ParameterObject;

import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * A class for clustering individuals using their x and y coordinates
 */
public class KMeansClusterer {
    
    /**
     * Clusters the destinations/patients together in clusters, the count of which is defined
     * by k. The clustering is based on x and y coordinates
     * 
     * @param parameters
     * @param k
     * @return A list of the resulting clusters, being lists of destinations close to each other
     */
    public List<List<Integer>> kMeansClustering(ParameterObject parameters, int k) {
        List<Integer> destinations = parameters.PROBLEM_INSTANCE.getDestinations();
        List<List<Integer>> clusteredDestinations = new ArrayList<>();

        try {
            Instances data = createInstances(parameters, destinations);
            SimpleKMeans kMeans = new SimpleKMeans();
            kMeans.setNumClusters(k);
            kMeans.buildClusterer(data);

            for (int i = 0; i < data.numInstances(); i++) {
                Instance instance = data.instance(i);
                int clusterIndex = kMeans.clusterInstance(instance);
                int destination = destinations.get(i);
                if (clusterIndex >= clusteredDestinations.size()) {
                    for (int j = clusteredDestinations.size(); j <= clusterIndex; j++) {
                        clusteredDestinations.add(new ArrayList<>());
                    }
                }
                clusteredDestinations.get(clusterIndex).add(destination);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clusteredDestinations;
    }

    private static Instances createInstances(ParameterObject parameters, List<Integer> destinations) {
        Instances data = new Instances("destinations", createAttributes(), destinations.size());
    
        for (int destination : destinations) {
            data.add(createInstance(parameters, destination));
        }
    
        return data;
    }

    private static ArrayList<weka.core.Attribute> createAttributes() {
        ArrayList<weka.core.Attribute> attributes = new ArrayList<>();
        attributes.add(new weka.core.Attribute("x_coord"));
        attributes.add(new weka.core.Attribute("y_coord"));
        return attributes;
    }

    private static DenseInstance createInstance(ParameterObject parameters, int destination) {
        ProblemInstance problemInstance = parameters.PROBLEM_INSTANCE;
        int x_coord = problemInstance.getPatients().get(destination).getX_coord();
        int y_coord = problemInstance.getPatients().get(destination).getY_coord();
        double[] values = new double[] { x_coord, y_coord };
        return new DenseInstance(1.0, values);
    }

}
