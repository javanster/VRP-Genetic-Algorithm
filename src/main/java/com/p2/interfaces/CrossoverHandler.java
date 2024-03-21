package com.p2.interfaces;

import java.util.List;

/**
 * Interface for crossover classes
 */
public interface CrossoverHandler {

    /**
     * Performs crossover between two parents, generating two new children. See implementation classes
     * in directory offspring_generation.
     * 
     * @param parent1
     * @param parent2
     * @return the two resulting children
     */
    public List<List<Integer>> cross(List<Integer> parent1, List<Integer> parent2);
}
