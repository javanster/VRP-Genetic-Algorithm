package com.p2.run_utils;

import java.util.List;

import com.p2.interfaces.CrossoverHandler;
import com.p2.interfaces.MutationHandler;
import com.p2.interfaces.ParentSelector;
import com.p2.interfaces.SurvivorSelector;
import com.p2.problem_instance.ProblemInstance;

/**
 * A class for storing parameters used for a run of the genetic algorithm
 */
public class ParameterObject {

    public List<Integer> CLUSTER_COUNTS;
    public ParentSelector PARENT_SELECTOR;
    public SurvivorSelector SURVIVOR_SELECTOR;
    public int POPULATION_SIZE;
    public int PARENT_COUNT;
    public int TOURNAMENT_SIZE;
    public int ELITES;
    public int LAMBDA;
    public MutationHandler MUTATION_HANDLER;
    public double MUTATION_PROBABILITY;
    public CrossoverHandler CROSSOVER_HANDLER;
    public ProblemInstance PROBLEM_INSTANCE;
    public int GENERATIONS;
    public boolean POPULATION_REPLACEMENT;

}
