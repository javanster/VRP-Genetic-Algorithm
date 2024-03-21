package com.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.p2.base_classes.FitnessAnalyzer;
import com.p2.base_classes.Individual;
import com.p2.problem_instance.ProblemInstance;
import com.p2.readers_writers.ProblemInstanceReader;
import com.p2.readers_writers.SolutionWriter;
import com.p2.run_utils.Island1;
import com.p2.run_utils.Island2;
import com.p2.run_utils.Island3;
import com.p2.run_utils.Island4;
import com.p2.run_utils.Island5;
import com.p2.run_utils.Island6;
import com.p2.run_utils.Island7;
import com.p2.run_utils.Island8;
import com.p2.run_utils.IslandPopulations;
import com.p2.run_utils.ParameterObject;
import com.p2.run_utils.ParameterSetter;

/**
 * A class for finding a solution for a problem instance by running eight populations in tandem, Island Model.
 * 
 * Based on lecture notes and Eiben & Smith, 2015, p. 95-97.
 */
public class IslandRun {

    FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

    /**
     * Runs eight instances of genetic algorithms, possibly with differing parameters, to find a solution for
     *  a problem instance. Prints the best solution found, and writes it to file.
     * 
     * @param generations The number of generations each genetic algorithm should run in total
     * @param epoch The number of generations each genetic algorithm should run before individuals are exchanged
     * between their populations
     * @param problemInstanceName The name of the problem instance
     * @param swapBest If true, the algorithm will select the two best individuals for swapping. If false, it will
     * select two good ones, not necessarily the best
     * @param populationReplacement Whether population replacement is allowed, see the class GeneticAlgorithm
     */
    public void evolveIslands(int generations, int epoch, String problemInstanceName, boolean swapBest, boolean populationReplacement) {
        
        IslandPopulations.POPULATION_1 = null;
        IslandPopulations.POPULATION_2 = null;
        IslandPopulations.POPULATION_3 = null;
        IslandPopulations.POPULATION_4 = null;
        IslandPopulations.POPULATION_5 = null;
        IslandPopulations.POPULATION_6 = null;
        IslandPopulations.POPULATION_7 = null;
        IslandPopulations.POPULATION_8 = null;

        int totalRuns = generations / epoch;

        ProblemInstance problemInstance = new ProblemInstanceReader().readProblemInstance(problemInstanceName);

        List<ParameterObject> parameterObjects = new ParameterSetter().setAndReturnParams(problemInstance, epoch, populationReplacement);
        
        for (int i = 0; i < totalRuns; i++) {

            List<Thread> islands = new ArrayList<>();

            islands.add(new Island1(parameterObjects.get(0)));
            islands.add(new Island2(parameterObjects.get(1)));
            islands.add(new Island3(parameterObjects.get(2)));
            islands.add(new Island4(parameterObjects.get(3)));
            islands.add(new Island5(parameterObjects.get(4)));
            islands.add(new Island6(parameterObjects.get(5)));
            islands.add(new Island7(parameterObjects.get(6)));
            islands.add(new Island8(parameterObjects.get(7)));


            for (Thread island : islands) {
                island.start();
            }

            
            try {
                for (Thread island : islands) {
                    island.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Gen " + ((i + 1) * epoch) + "/" + generations + ", Swapping...");
            System.out.println("Fitnesses: " + 
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(0), IslandPopulations.POPULATION_1)) + " | " +
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(1), IslandPopulations.POPULATION_2)) + " | " +
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(2), IslandPopulations.POPULATION_3)) + " | " +
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(3), IslandPopulations.POPULATION_4)) + " | " +
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(4), IslandPopulations.POPULATION_5)) + " | " +
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(5), IslandPopulations.POPULATION_6)) + " | " +
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(6), IslandPopulations.POPULATION_7)) + " | " +
            String.format("%.2f", this.fitnessAnalyzer.getFitnessOfBestIndividual(parameterObjects.get(7), IslandPopulations.POPULATION_8))
            );

            List<Individual> twoChosenPopulation1 = IslandPopulations.POPULATION_1.getTwoGoodIndividuals(parameterObjects.get(0));
            List<Individual> twoChosenPopulation2 = IslandPopulations.POPULATION_2.getTwoGoodIndividuals(parameterObjects.get(1));
            List<Individual> twoChosenPopulation3 = IslandPopulations.POPULATION_3.getTwoGoodIndividuals(parameterObjects.get(2));
            List<Individual> twoChosenPopulation4 = IslandPopulations.POPULATION_4.getTwoGoodIndividuals(parameterObjects.get(3));
            List<Individual> twoChosenPopulation5 = IslandPopulations.POPULATION_5.getTwoGoodIndividuals(parameterObjects.get(4));
            List<Individual> twoChosenPopulation6 = IslandPopulations.POPULATION_6.getTwoGoodIndividuals(parameterObjects.get(5));
            List<Individual> twoChosenPopulation7 = IslandPopulations.POPULATION_7.getTwoGoodIndividuals(parameterObjects.get(6));
            List<Individual> twoChosenPopulation8 = IslandPopulations.POPULATION_8.getTwoGoodIndividuals(parameterObjects.get(7));

            if (swapBest) {
                twoChosenPopulation1 = IslandPopulations.POPULATION_1.getTwoBestIndividuals(parameterObjects.get(0));
                twoChosenPopulation2 = IslandPopulations.POPULATION_2.getTwoBestIndividuals(parameterObjects.get(1));
                twoChosenPopulation3 = IslandPopulations.POPULATION_3.getTwoBestIndividuals(parameterObjects.get(2));
                twoChosenPopulation4 = IslandPopulations.POPULATION_4.getTwoBestIndividuals(parameterObjects.get(3));
                twoChosenPopulation5 = IslandPopulations.POPULATION_5.getTwoBestIndividuals(parameterObjects.get(4));
                twoChosenPopulation6 = IslandPopulations.POPULATION_6.getTwoBestIndividuals(parameterObjects.get(5));
                twoChosenPopulation7 = IslandPopulations.POPULATION_7.getTwoBestIndividuals(parameterObjects.get(6));
                twoChosenPopulation8 = IslandPopulations.POPULATION_8.getTwoBestIndividuals(parameterObjects.get(7));
            }


            IslandPopulations.POPULATION_1.replaceTwoWorst(parameterObjects.get(0), twoChosenPopulation8);
            IslandPopulations.POPULATION_2.replaceTwoWorst(parameterObjects.get(1), twoChosenPopulation1);
            IslandPopulations.POPULATION_3.replaceTwoWorst(parameterObjects.get(2), twoChosenPopulation2);
            IslandPopulations.POPULATION_4.replaceTwoWorst(parameterObjects.get(3), twoChosenPopulation3);
            IslandPopulations.POPULATION_5.replaceTwoWorst(parameterObjects.get(4), twoChosenPopulation4);
            IslandPopulations.POPULATION_6.replaceTwoWorst(parameterObjects.get(5), twoChosenPopulation5);
            IslandPopulations.POPULATION_7.replaceTwoWorst(parameterObjects.get(6), twoChosenPopulation6);
            IslandPopulations.POPULATION_8.replaceTwoWorst(parameterObjects.get(7), twoChosenPopulation7);

        }

        Individual solution1 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(0), IslandPopulations.POPULATION_1);
        Individual solution2 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(1), IslandPopulations.POPULATION_2);
        Individual solution3 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(2), IslandPopulations.POPULATION_3);
        Individual solution4 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(3), IslandPopulations.POPULATION_4);
        Individual solution5 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(4), IslandPopulations.POPULATION_5);
        Individual solution6 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(5), IslandPopulations.POPULATION_6);
        Individual solution7 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(6), IslandPopulations.POPULATION_7);
        Individual solution8 = this.fitnessAnalyzer.getBestIndividualInPopulation(parameterObjects.get(7), IslandPopulations.POPULATION_8);



        List<Individual> solutions = new ArrayList<>(Arrays.asList(solution1, solution2, solution3, solution4, solution5, solution6, solution7, solution8));

        
        Individual fittestIndividual = solutions.get(0);
        double bestFitness = this.fitnessAnalyzer.getFitness(parameterObjects.get(0), fittestIndividual);
        int fittestIndividualIndex = 0;
        
        for (int i = 1; i < solutions.size(); i++) {
            Individual individual = solutions.get(i);
            double fitness = this.fitnessAnalyzer.getFitness(parameterObjects.get(i), individual);
            
            if (fitness < bestFitness) {
                fittestIndividual = individual;
                bestFitness = fitness;
                fittestIndividualIndex = i;
            }
        }
        
        
        System.out.println("\nResult for " + problemInstance.getInstance_name());
        System.out.println("Best fitness: " + bestFitness);
        boolean feasable = this.fitnessAnalyzer.isSolutionFeasible(parameterObjects.get(fittestIndividualIndex), fittestIndividual);
        System.out.println("Feasable: " + (feasable ? "Yes" : "No"));
        System.out.println(fittestIndividual);

        SolutionWriter solutionWriter = new SolutionWriter();
        solutionWriter.writeSolutionForMap(fittestIndividual, problemInstance);
        solutionWriter.writeSolutionDetails(parameterObjects.get(fittestIndividualIndex), fittestIndividual);

        
    }

    
    public static void main(String[] args) {

        IslandRun test = new IslandRun();

        test.evolveIslands(3000, 50, "train_9.json", true, false);

    }


}
