package com.p2.interfaces;

import com.p2.base_classes.Individual;
import com.p2.run_utils.ParameterObject;

/**
 * Interface for infividual generators
 */
public interface IndividualGenerator {

    /**
     * Generates a new individual. See implementation classes in directory individual_generators
     * 
     * @param parameters A parameter object with set parameters
     * @param clusterCount
     * @return A new individual
     */
    public Individual generateIndividual(ParameterObject parameters, int clusterCount);
}
