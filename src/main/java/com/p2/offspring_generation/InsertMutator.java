package com.p2.offspring_generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.p2.interfaces.MutationHandler;
import com.p2.run_utils.ParameterObject;

/**
 * A class for performing insert mutation.
 * 
 * Based on lecture notes and Eiben & Smith, 2015, p. 69.
 */
public class InsertMutator implements MutationHandler {

    @Override
    public List<Integer> mutate(ParameterObject parameters, List<Integer> child) {
        Random random = new Random();
        double randomChance = random.nextDouble();

        if (randomChance <= parameters.MUTATION_PROBABILITY) {
            int randomIndex1 = random.nextInt(child.size() - 1);
            int randomIndex2 = random.nextInt(randomIndex1 + 1, child.size());
    
            List<Integer> mutatedChild = new ArrayList<>(child.subList(0, randomIndex1 + 1));
            mutatedChild.add(child.get(randomIndex2));
            mutatedChild.addAll(child.subList(randomIndex1 + 1, randomIndex2));
            mutatedChild.addAll(child.subList(randomIndex2 + 1, child.size()));
            child = mutatedChild;
        }

        return child;
    }
}
