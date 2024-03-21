package com.p2.run_utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.p2.offspring_generation.FirstOrderCrosser;
import com.p2.offspring_generation.InsertMutator;
import com.p2.offspring_generation.SwapMutator;
import com.p2.parent_selectors.TournamentParentSelector;
import com.p2.problem_instance.ProblemInstance;
import com.p2.survivor_selectors.MuLambdaElitismSurvivorSelector;
import com.p2.survivor_selectors.ReplaceWorstSurvivorSelection;

/**
 * A class for setting parameters of all islands in the island model
 */
public class ParameterSetter {
    
    /**
     * Defines and returns the parameters for all islands for an island run
     * 
     * @param problemInstance The problem instance to find a solution for
     * @param generations The number of generations to evolve each island
     * @param populationReplacement Whether random population replacement is allowed during the evolvement
     * @return A lsit containing the set parameters for all islands
     */
    public List<ParameterObject> setAndReturnParams (ProblemInstance problemInstance, int generations, boolean populationReplacement) {
        ParameterObject parameterObject1 = new ParameterObject();
        ParameterObject parameterObject2 = new ParameterObject();
        ParameterObject parameterObject3 = new ParameterObject();
        ParameterObject parameterObject4 = new ParameterObject();
        ParameterObject parameterObject5 = new ParameterObject();
        ParameterObject parameterObject6 = new ParameterObject();
        ParameterObject parameterObject7 = new ParameterObject();
        ParameterObject parameterObject8 = new ParameterObject();

        parameterObject1.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject1.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject1.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject1.POPULATION_SIZE = 100;
        parameterObject1.PARENT_COUNT = parameterObject1.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject1.POPULATION_SIZE;
        parameterObject1.TOURNAMENT_SIZE = 10;
        parameterObject1.ELITES = 2;
        parameterObject1.LAMBDA = 7;
        parameterObject1.MUTATION_HANDLER = new InsertMutator();
        parameterObject1.MUTATION_PROBABILITY = 1;
        parameterObject1.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject1.PROBLEM_INSTANCE = problemInstance;
        parameterObject1.GENERATIONS = generations;
        parameterObject1.POPULATION_REPLACEMENT = populationReplacement;

        parameterObject2.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject2.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject2.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject2.POPULATION_SIZE = 100;
        parameterObject2.PARENT_COUNT = parameterObject2.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject2.POPULATION_SIZE;
        parameterObject2.TOURNAMENT_SIZE = 10;
        parameterObject2.ELITES = 2;
        parameterObject2.LAMBDA = 7;
        parameterObject2.MUTATION_HANDLER = new SwapMutator();
        parameterObject2.MUTATION_PROBABILITY = 0.5;
        parameterObject2.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject2.PROBLEM_INSTANCE = problemInstance;
        parameterObject2.GENERATIONS = generations;
        parameterObject2.POPULATION_REPLACEMENT = populationReplacement;

        parameterObject3.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject3.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject3.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject3.POPULATION_SIZE = 100;
        parameterObject3.PARENT_COUNT = parameterObject3.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject3.POPULATION_SIZE;
        parameterObject3.TOURNAMENT_SIZE = 10;
        parameterObject3.ELITES = 2;
        parameterObject3.LAMBDA = 7;
        parameterObject3.MUTATION_HANDLER = new InsertMutator();
        parameterObject3.MUTATION_PROBABILITY = 0.5;
        parameterObject3.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject3.PROBLEM_INSTANCE = problemInstance;
        parameterObject3.GENERATIONS = generations;
        parameterObject3.POPULATION_REPLACEMENT = populationReplacement;

        parameterObject4.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject4.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject4.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject4.POPULATION_SIZE = 100;
        parameterObject4.PARENT_COUNT = parameterObject4.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject4.POPULATION_SIZE;
        parameterObject4.TOURNAMENT_SIZE = 10;
        parameterObject4.ELITES = 2;
        parameterObject4.LAMBDA = 7;
        parameterObject4.MUTATION_HANDLER = new SwapMutator();
        parameterObject4.MUTATION_PROBABILITY = 1;
        parameterObject4.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject4.PROBLEM_INSTANCE = problemInstance;
        parameterObject4.GENERATIONS = generations;
        parameterObject4.POPULATION_REPLACEMENT = populationReplacement;

        parameterObject5.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject5.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject5.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject5.POPULATION_SIZE = 100;
        parameterObject5.PARENT_COUNT = parameterObject5.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject5.POPULATION_SIZE;
        parameterObject5.TOURNAMENT_SIZE = 10;
        parameterObject5.ELITES = 2;
        parameterObject5.LAMBDA = 7;
        parameterObject5.MUTATION_HANDLER = new InsertMutator();
        parameterObject5.MUTATION_PROBABILITY = 0.5;
        parameterObject5.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject5.PROBLEM_INSTANCE = problemInstance;
        parameterObject5.GENERATIONS = generations;
        parameterObject5.POPULATION_REPLACEMENT = populationReplacement;

        parameterObject6.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject6.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject6.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject6.POPULATION_SIZE = 100;
        parameterObject6.PARENT_COUNT = parameterObject6.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject6.POPULATION_SIZE;
        parameterObject6.TOURNAMENT_SIZE = 10;
        parameterObject6.ELITES = 2;
        parameterObject6.LAMBDA = 7;
        parameterObject6.MUTATION_HANDLER = new SwapMutator();
        parameterObject6.MUTATION_PROBABILITY = 1;
        parameterObject6.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject6.PROBLEM_INSTANCE = problemInstance;
        parameterObject6.GENERATIONS = generations;
        parameterObject6.POPULATION_REPLACEMENT = populationReplacement;

        parameterObject7.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject7.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject7.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject7.POPULATION_SIZE = 100;
        parameterObject7.PARENT_COUNT = parameterObject7.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject7.POPULATION_SIZE;
        parameterObject7.TOURNAMENT_SIZE = 10;
        parameterObject7.ELITES = 2;
        parameterObject7.LAMBDA = 7;
        parameterObject7.MUTATION_HANDLER = new InsertMutator();
        parameterObject7.MUTATION_PROBABILITY = 1;
        parameterObject7.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject7.PROBLEM_INSTANCE = problemInstance;
        parameterObject7.GENERATIONS = generations;
        parameterObject7.POPULATION_REPLACEMENT = populationReplacement;

        parameterObject8.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        parameterObject8.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject8.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject8.POPULATION_SIZE = 100;
        parameterObject8.PARENT_COUNT = parameterObject8.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject8.POPULATION_SIZE;
        parameterObject8.TOURNAMENT_SIZE = 10;
        parameterObject8.ELITES = 2;
        parameterObject8.LAMBDA = 7;
        parameterObject8.MUTATION_HANDLER = new SwapMutator();
        parameterObject8.MUTATION_PROBABILITY = 0.5;
        parameterObject8.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject8.PROBLEM_INSTANCE = problemInstance;
        parameterObject8.GENERATIONS = generations;
        parameterObject8.POPULATION_REPLACEMENT = populationReplacement;

        return new ArrayList<>(Arrays.asList(parameterObject1, parameterObject2, parameterObject3, parameterObject4, parameterObject5, parameterObject6, parameterObject7, parameterObject8));

    }

}
