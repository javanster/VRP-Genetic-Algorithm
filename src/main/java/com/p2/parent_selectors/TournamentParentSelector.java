package com.p2.parent_selectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.p2.base_classes.FitnessAnalyzer;
import com.p2.base_classes.Individual;
import com.p2.base_classes.Population;
import com.p2.interfaces.ParentSelector;
import com.p2.run_utils.ParameterObject;

/**
 * A class for performing deterministic tournament selection.
 * Tournament candidates are picked with replacement.
 * 
 * Based on lecture notes and Eiben & Smith, 2015, p. 84-86
*/
public class TournamentParentSelector implements ParentSelector {

    private FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

    @Override
    public Population selectParents(ParameterObject parameters, Population population, int parentsCount) {
        List<Individual> selectedParents = new ArrayList<>();
        List<Individual> individuals = population.getIndividuals();

        while (selectedParents.size() < parentsCount) {
            Collections.shuffle(individuals);
            List<Individual> tournament = individuals.subList(0, parameters.TOURNAMENT_SIZE);
            Individual fittestIndividual = tournament.stream()
                .min((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)))
                .orElse(null);
            if (fittestIndividual != null) {
                selectedParents.add(fittestIndividual);
            }
        }
        
        return new Population(selectedParents);
    }

}
