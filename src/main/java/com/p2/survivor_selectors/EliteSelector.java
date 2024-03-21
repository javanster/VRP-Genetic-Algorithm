package com.p2.survivor_selectors;

import java.util.List;

import com.p2.base_classes.FitnessAnalyzer;
import com.p2.base_classes.Individual;
import com.p2.base_classes.Population;
import com.p2.run_utils.ParameterObject;

/**
 * A class for selecting elites from a population
 */
public class EliteSelector {

    private FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();
    
    /**
     * Finds and returns the elites of a population, i.e. a certain number of individuals that have
     * the highest fitness within the population.
     * 
     * @param parameters A parameter object with set parameters
     * @param population
     * @return The elites of the population
     */
    public List<Individual> getElites(ParameterObject parameters, Population population) {
        List<Individual> individuals = population.getIndividuals();
        individuals.sort((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)));
        return individuals.subList(0, parameters.ELITES);
    }
}
