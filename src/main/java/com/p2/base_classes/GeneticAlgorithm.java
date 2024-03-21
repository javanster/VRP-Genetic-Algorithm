package com.p2.base_classes;

import com.p2.run_utils.ParameterObject;

/**
 * A class which serves as the framework for running a genetic algorithm in order to find a
 * good and feasable solution to the Vehicle Routing Problem (VRP)
 */
public class GeneticAlgorithm {

    private FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();
    
    /**
     * Runs the genetic algorithm in accordence with the parameters set in the parameter
     * object. Supports the replacement of a given fraction of the population after 10
     * generations if fitness has stagnated. See the Population class.
     * 
     * @param parameters A parameter object with set parameters
     * @param population The initial population of the run. Set to null if a completely new
     * population should be created
     * @return The population after evolving it for a given number of generations
     */
    public Population evolvePopulation(ParameterObject parameters, Population population) {

        if (population == null) {
            population = new Population(parameters);
        }
        
        double bestFitness = Double.MAX_VALUE;

        for (int i = 0; i < parameters.GENERATIONS; i++) {
            
            if (parameters.POPULATION_REPLACEMENT && i % 10 == 0) {
                double bestFitnessInCurrentPopulation = this.fitnessAnalyzer.getFitnessOfBestIndividual(parameters, population);
                
                if (bestFitness == bestFitnessInCurrentPopulation) {
                    population.replaceWorst(parameters);
                } 
                bestFitness = bestFitnessInCurrentPopulation;
            }
            Population parents = parameters.PARENT_SELECTOR.selectParents(parameters, population, parameters.PARENT_COUNT);
            Population survivors = parameters.SURVIVOR_SELECTOR.selectSurvivors(parameters, parents, population);
            population = survivors;
        }

        return population;
    }
}
