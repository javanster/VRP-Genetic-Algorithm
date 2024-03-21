package com.p2.offspring_generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.p2.base_classes.Individual;
import com.p2.run_utils.ParameterObject;

/**
 * A class for generating offsping
 */
public class OffspringGenerator {
    
    /**
     * Generates offspring based on two parents. Flattenes the individual list, i.e. transforms the inner
     * lists to one list to ensure that crossover and mutation may also move destinations between routes.
     * 
     * @param parameters A parameter object with set parameters
     * @param parent1
     * @param parent2
     * @return A list containg the two generated children
     */
    public List<Individual> generateOffspring(ParameterObject parameters, Individual parent1, Individual parent2) {
        List<List<Integer>> parent1Value = parent1.getRoutes(); 
        List<List<Integer>> parent2Value = parent2.getRoutes();

        List<Integer> parent1Flattened = this.flattenRouteLists(parent1Value);
        List<Integer> parent2Flattened = this.flattenRouteLists(parent2Value);

        List<List<Integer>> offspring = parameters.CROSSOVER_HANDLER.cross(parent1Flattened, parent2Flattened);

        List<Integer> mutatedChild1 = parameters.MUTATION_HANDLER.mutate(parameters, offspring.get(0));
        List<Integer> mutatedChild2 = parameters.MUTATION_HANDLER.mutate(parameters, offspring.get(1));
        
        List<List<Integer>> child1Reconstructed = this.reconstructRouteList(mutatedChild1);
        List<List<Integer>> child2Reconstructed = this.reconstructRouteList(mutatedChild2);

        return new ArrayList<>(Arrays.asList(new Individual(child1Reconstructed), new Individual(child2Reconstructed)));
    }


    /**
     * Flattenes the the list containg the routes by transforming the list of lists to a list containing all destinations,
     * but negative numbers indicating the beginning of a new route.
     * @param routeLists
     * @return The flattened list
     */
    private List<Integer> flattenRouteLists(List<List<Integer>> routeLists) {
        int routeCount = routeLists.size();
        List<Integer> flattenedRouteList = new ArrayList<>();

        for (int i = 0; i < routeCount; i++) {
            List<Integer> routeList = routeLists.get(i);
            flattenedRouteList.add(- i - 1);
            for (Integer dest : routeList) {
                flattenedRouteList.add(dest);
            }
        }
        
        return flattenedRouteList;
    }

    /**
     * Reconstructs the flattened list into a list of lists
     * 
     * @param flattenedRouteList
     * @return The reconstructed list
     */
    private List<List<Integer>> reconstructRouteList(List<Integer> flattenedRouteList) {
        List<Integer> currentDestList = new ArrayList<>();
        List<List<Integer>> reconstructedRouteList = new ArrayList<>();

        if (flattenedRouteList.get(0) > 0) {
            int firstListIndex = 0;
            for (int i = 0; i < flattenedRouteList.size(); i++) {
                int dest = flattenedRouteList.get(i);
                if (dest < 0) {
                    firstListIndex = i;
                    break;
                }
            }
            for (int i = firstListIndex + 1; i < flattenedRouteList.size(); i++) {
                int dest = flattenedRouteList.get(i);
                    if (dest < 0) {
                        reconstructedRouteList.add(currentDestList);
                        currentDestList = new ArrayList<>();
                    } else {
                        currentDestList.add(dest);
                    }
            }
            for (int i = 0; i < firstListIndex; i++) {
                currentDestList.add(flattenedRouteList.get(i));
            }
            reconstructedRouteList.add(currentDestList);

        } else {
            for (int i = 0; i < flattenedRouteList.size(); i++) {
                if (i != 0) {
                    int dest = flattenedRouteList.get(i);
                    if (dest < 0) {
                        reconstructedRouteList.add(currentDestList);
                        currentDestList = new ArrayList<>();
                    } else {
                        currentDestList.add(dest);
                    }
                }
            }
            reconstructedRouteList.add(currentDestList);
        }

        Collections.shuffle(reconstructedRouteList);

        return reconstructedRouteList;
    }

}
