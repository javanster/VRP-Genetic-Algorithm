package com.p2;

import java.util.ArrayList;
import java.util.Arrays;

import com.p2.base_classes.FitnessAnalyzer;
import com.p2.base_classes.GeneticAlgorithm;
import com.p2.base_classes.Individual;
import com.p2.base_classes.Population;
import com.p2.offspring_generation.FirstOrderCrosser;
import com.p2.offspring_generation.InsertMutator;
import com.p2.parent_selectors.TournamentParentSelector;
import com.p2.problem_instance.ProblemInstance;
import com.p2.readers_writers.ProblemInstanceReader;
import com.p2.readers_writers.SolutionWriter;
import com.p2.run_utils.ParameterObject;
import com.p2.survivor_selectors.MuLambdaElitismSurvivorSelector;
import com.p2.survivor_selectors.ReplaceWorstSurvivorSelection;

/**
 * A class for finding a solution to a problem instance by running one genetic algorithm
 */
public class Run {

    FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

    /**
     * Runs one genetic algorithm to find a solution for a problem instance by running one genetic algorithm.
     * Prints the best solution found, and writes it to file.
     * 
     * @param generations How many generations the algorithm should run for
     * @param problemInstanceName The name of the problem instance to find a solution for
     * @param populationReplacement Whether population replacement is allowed, see the class GeneticAlgorithm
     */
    public void runGa(int generations, String problemInstanceName, boolean populationReplacement) {
        ProblemInstance problemInstance = new ProblemInstanceReader().readProblemInstance(problemInstanceName);

        GeneticAlgorithm ga = new GeneticAlgorithm();

        ParameterObject parameterObject = new ParameterObject();

        parameterObject.CLUSTER_COUNTS = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
        parameterObject.PARENT_SELECTOR = new TournamentParentSelector();
        parameterObject.SURVIVOR_SELECTOR = new MuLambdaElitismSurvivorSelector();
        parameterObject.POPULATION_SIZE = 100;
        parameterObject.PARENT_COUNT = parameterObject.SURVIVOR_SELECTOR instanceof ReplaceWorstSurvivorSelection ? 2 : parameterObject.POPULATION_SIZE;
        parameterObject.TOURNAMENT_SIZE = 10;
        parameterObject.ELITES = 2;
        parameterObject.LAMBDA = 7;
        parameterObject.MUTATION_HANDLER = new InsertMutator();
        parameterObject.MUTATION_PROBABILITY = 0.8;
        parameterObject.CROSSOVER_HANDLER = new FirstOrderCrosser();
        parameterObject.PROBLEM_INSTANCE = problemInstance;
        parameterObject.GENERATIONS = generations;
        parameterObject.POPULATION_REPLACEMENT = populationReplacement;

        Population solution = ga.evolvePopulation(parameterObject, null);

        Individual fittestIndividual = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObject, solution);
        double bestFitness = this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObject, solution);

        System.out.println("\nResult for " + problemInstance.getInstance_name());
        System.out.println("Best fitness: " + bestFitness);
        boolean feasable = this.fitnessAnalyzer.isSolutionFeasible(parameterObject, fittestIndividual);
        System.out.println("Feasable: " + (feasable ? "Yes" : "No"));
        System.out.println(fittestIndividual);

        SolutionWriter solutionWriter = new SolutionWriter();
        solutionWriter.writeSolutionForMap(fittestIndividual, problemInstance);
        solutionWriter.writeSolutionDetails(parameterObject, fittestIndividual);
    }

    public static void main(String[] args) {
        
        Run run = new Run();
        run.runGa(5000, "train_9.json", false);
    }
}
