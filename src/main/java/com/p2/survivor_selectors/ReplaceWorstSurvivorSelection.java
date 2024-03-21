package com.p2.survivor_selectors;

import java.util.List;

import com.p2.base_classes.FitnessAnalyzer;
import com.p2.base_classes.Individual;
import com.p2.base_classes.Population;
import com.p2.interfaces.SurvivorSelector;
import com.p2.offspring_generation.OffspringGenerator;
import com.p2.run_utils.ParameterObject;

/**
 * A class for implementing a variant of survival selection to achieve a steady state
 * genetic algorithm.
 * 
 * Based on Simon, 2013, p. 190-191.
 */
public class ReplaceWorstSurvivorSelection implements SurvivorSelector {

    private OffspringGenerator offspringGenerator = new OffspringGenerator();
    private FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

    @Override
    public Population selectSurvivors(ParameterObject parameters, Population parents, Population population) {
        List<Individual> individuals = population.getIndividuals();
        individuals.sort((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)));
        List<Individual> bestIndividuals = individuals.subList(0, individuals.size() - 2);
        List<Individual> offspring = this.offspringGenerator.generateOffspring(parameters, parents.getIndividuals().get(0), parents.getIndividuals().get(1));
        bestIndividuals.addAll(offspring);
        return new Population(bestIndividuals);
    }
    
}
