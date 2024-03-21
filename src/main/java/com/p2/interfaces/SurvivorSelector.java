package com.p2.interfaces;

import com.p2.base_classes.Population;
import com.p2.run_utils.ParameterObject;

/**
 * Interface for survivor selectors
 */
public interface SurvivorSelector {

    /**
     * Creates offspring and selects survivors for the next generation. 
     * See directory survivor selectors for implementations
     * 
     * @param parameters A parameter object with set parameters
     * @param parents The parents of the offspring
     * @param population The current population
     * @return A new generation within the population
     */
    public Population selectSurvivors(ParameterObject parameters, Population parents, Population population);
}
