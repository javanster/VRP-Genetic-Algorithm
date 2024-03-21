package com.p2.offspring_generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.p2.interfaces.CrossoverHandler;

/**
 * Class for performing first order crossover.
 * 
 * Based on lecture notes and Eiben & Smith, 2015, p. 73-74.
 */
public class FirstOrderCrosser implements CrossoverHandler {

    @Override
    public List<List<Integer>> cross(List<Integer> parent1, List<Integer> parent2) {
        Random random = new Random();

        // Crossover slice somewhere between index 1 and the second last index, ensuring that no child will be equal to one of its parents
        int minIndex = random.nextInt(parent1.size() - 1);
        int maxIndex = random.nextInt(parent1.size() - minIndex) + minIndex;


        List<Integer> child1 = new ArrayList<>(Collections.nCopies(parent1.size(), 0));
        List<Integer> child2 = new ArrayList<>(Collections.nCopies(parent1.size(), 0));

        for (int i = minIndex; i <= maxIndex; i++) {
            child1.set(i, parent1.get(i));
            child2.set(i, parent2.get(i));
        }

        List<Integer> remainingParent1 = new ArrayList<>();
        List<Integer> remainingParent2 = new ArrayList<>();

        for (int i = maxIndex + 1; i < parent1.size(); i++) {
            int destParent1 = parent1.get(i);
            int destParent2 = parent2.get(i);
            if (!child2.contains(destParent1)) {
                remainingParent1.add(destParent1);
            }
            if (!child1.contains(destParent2)) {
                remainingParent2.add(destParent2);
            }
        }

        for (int i = 0; i <= maxIndex; i++) {
            int destParent1 = parent1.get(i);
            int destParent2 = parent2.get(i);
            if (!child2.contains(destParent1)) {
                remainingParent1.add(destParent1);
            }
            if (!child1.contains(destParent2)) {
                remainingParent2.add(destParent2);
            }
        }

        for (int i = maxIndex + 1; i < child1.size(); i++) {
            child1.set(i, remainingParent2.remove(0));
            child2.set(i, remainingParent1.remove(0));
        }

        for (int i = 0; i < minIndex; i++) {
            child1.set(i, remainingParent2.remove(0));
            child2.set(i, remainingParent1.remove(0));
        }

        return new ArrayList<>(Arrays.asList(child1, child2));
    }
    
}
