package com.p2.offspring_generation;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.p2.interfaces.MutationHandler;
import com.p2.run_utils.ParameterObject;

/**
 * A class for performing inversion mutation.
 * 
 * Based on lecture notes and Eiben & Smith, 2015, p. 69-70.
 */
public class InversionMutator implements MutationHandler {

    @Override
    public List<Integer> mutate(ParameterObject parameters, List<Integer> child) {
        Random random = new Random();
        double randomChance = random.nextDouble();

        if (randomChance <= parameters.MUTATION_PROBABILITY) { 
            int randomIndex1 = random.nextInt(child.size() - 1);
            int randomIndex2 = random.nextInt(randomIndex1 + 1, child.size());
            List<Integer> subList = child.subList(randomIndex1, randomIndex2 + 1);
            Collections.reverse(subList);
        }
        return child;
    }
    
}
