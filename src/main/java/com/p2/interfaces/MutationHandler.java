package com.p2.interfaces;

import java.util.List;

import com.p2.run_utils.ParameterObject;

/**
 * Interface for mutation handlers
 */
public interface MutationHandler {

    /**
     * Performs mutation of a child individual. See directory offspring_generation for implementations
     * 
     * @param parameters A parameter object with set parameters
     * @param child 
     * @return The mutated child
     */
    public List<Integer> mutate(ParameterObject parameters, List<Integer> child);
}
