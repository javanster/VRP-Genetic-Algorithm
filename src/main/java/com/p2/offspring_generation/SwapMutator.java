package com.p2.offspring_generation;

import java.util.List;
import java.util.Random;

import com.p2.interfaces.MutationHandler;
import com.p2.run_utils.ParameterObject;

/**
 * A class for performing swap mutation.
 * 
 * Based on lecture notes and Eiben & Smith, 2015, p. 69.
 */
public class SwapMutator implements MutationHandler {

    @Override
    public List<Integer> mutate(ParameterObject parameters, List<Integer> child) {
        Random random = new Random();

        double randomChance = random.nextDouble();

        if (randomChance <= parameters.MUTATION_PROBABILITY) { 
            int randomIndex1 = random.nextInt(child.size());
            int randomIndex2 = random.nextInt(child.size());
            while (randomIndex1 == randomIndex2) {
                randomIndex2 = random.nextInt(child.size());
            }

            int value1 = child.get(randomIndex1);
            int value2 = child.get(randomIndex2);

            child.set(randomIndex1, value2);
            child.set(randomIndex2, value1);
        }

        return child;
    }

    @Override
    public String toString() {
        return "SwapMutator";
    }
    
}
