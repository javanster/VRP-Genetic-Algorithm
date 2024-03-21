package com.p2.survivor_selectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.p2.base_classes.FitnessAnalyzer;
import com.p2.base_classes.Individual;
import com.p2.base_classes.Population;
import com.p2.interfaces.SurvivorSelector;
import com.p2.offspring_generation.OffspringGenerator;
import com.p2.run_utils.ParameterObject;

/**
 * A class for implementing (mu, lambda) survivor selection with elitism.
 * 
 * Based on Simon, 2013, p. 128-130 and p. 188.
 * 
 */
public class MuLambdaElitismSurvivorSelector implements SurvivorSelector {

    private Random random = new Random();
    private OffspringGenerator offspringGenerator = new OffspringGenerator();
    private EliteSelector eliteSelector = new EliteSelector();
    private FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

    @Override
    public Population selectSurvivors(ParameterObject parameters, Population parents, Population population) {
        List<Individual> elites = this.eliteSelector.getElites(parameters, population);

        List<Individual> parentsForOffspringGeneration = new ArrayList<>();

        for (Individual individual : parents.getIndividuals()) {
            for (int i = 0; i < parameters.LAMBDA; i++) {
                parentsForOffspringGeneration.add(individual);
            }
        }

        Collections.shuffle(parentsForOffspringGeneration);

        List<Individual> allChildrenGenerated = new ArrayList<>();

        while (parentsForOffspringGeneration.size() > 0) {
            int randomIndex1 = random.nextInt(parentsForOffspringGeneration.size());
            Individual parent1 = parentsForOffspringGeneration.remove(randomIndex1);
            int randomIndex2 = random.nextInt(parentsForOffspringGeneration.size());
            Individual parent2 = parentsForOffspringGeneration.remove(randomIndex2);
            List<Individual> children = this.offspringGenerator.generateOffspring(parameters, parent1, parent2);
            allChildrenGenerated.addAll(children);
        }

        allChildrenGenerated.sort((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)));

        List<Individual> selectedChildren = allChildrenGenerated.subList(0, parents.getIndividuals().size() - parameters.ELITES);

        selectedChildren.addAll(elites);

        Population offspring = new Population(selectedChildren);

        return offspring;
    }
    
}
