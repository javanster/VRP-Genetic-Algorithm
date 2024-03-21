package com.p2.base_classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.p2.individual_generators.ClusteringCareEndTime_IG;
import com.p2.individual_generators.ClusteringEndTime_IG;
import com.p2.individual_generators.ClusteringShuffle_IG;
import com.p2.individual_generators.ClusteringTravelTime_IG;
import com.p2.individual_generators.GreedyIndividualGenerator;
import com.p2.run_utils.ParameterObject;

/**
 * A class representing a population of individuals for the VRP
 */
public class Population {
    
    private List<Individual> individuals;
    private ClusteringCareEndTime_IG clusteringCareEndTime_IG = new ClusteringCareEndTime_IG();
    private ClusteringEndTime_IG clusteringEndTime_IG = new ClusteringEndTime_IG();
    private ClusteringTravelTime_IG clusteringTravelTime_IG = new ClusteringTravelTime_IG();
    private ClusteringShuffle_IG clusteringShuffle_IG = new ClusteringShuffle_IG();
    private GreedyIndividualGenerator greedyIndividualGenerator = new GreedyIndividualGenerator();
    private FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

    /**
     * Constuctor that instantiates the population using clustering. See the individual clustering
     * classes.
     * 
     * @param parameters A parameter object with set parameters
     */
    public Population(ParameterObject parameters) {
        int populationSize = parameters.POPULATION_SIZE;
        List<Integer> clusterCounts = parameters.CLUSTER_COUNTS;

        List<Individual> newIndividualList = new ArrayList<>();


        for (int clusterCount : clusterCounts) {
            newIndividualList.add(this.clusteringCareEndTime_IG.generateIndividual(parameters, clusterCount));
            newIndividualList.add(this.clusteringEndTime_IG.generateIndividual(parameters, clusterCount));
            newIndividualList.add(this.clusteringTravelTime_IG.generateIndividual(parameters, clusterCount));            
        }

        int i = 0;
        while (newIndividualList.size() < populationSize) {

            if (i % 2 == 0) {
                newIndividualList.add(this.clusteringShuffle_IG.generateIndividual(parameters, 0));
            } else {
                newIndividualList.add(this.greedyIndividualGenerator.generateIndividual(parameters, 0));
            }

            i++;
        }

        this.individuals = newIndividualList;
    }

    public Population(List<Individual> individuals) {
        this.individuals = new ArrayList<>(individuals);
    }

    public List<Individual> getIndividuals() {
        return new ArrayList<>(individuals);
    }

    /**
     * Replaces the worst half of the population with newly generated individuals
     * 
     * @param parameters A parameter object with set parameters
     * @param destinations A list of the destinations 
     * @param nurseCount
     */
    public void replaceWorst(ParameterObject parameters) {

        List<Individual> individuals = this.getIndividuals();
        individuals.sort((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)));
        List<Individual> newPopulation = new ArrayList<>(individuals.subList(0, individuals.size() / 2));

        for (int clusterCount : parameters.CLUSTER_COUNTS) {
            newPopulation.add(this.clusteringCareEndTime_IG.generateIndividual(parameters, clusterCount));
            newPopulation.add(this.clusteringEndTime_IG.generateIndividual(parameters, clusterCount));
            newPopulation.add(this.clusteringTravelTime_IG.generateIndividual(parameters, clusterCount));            
        }

        int i = 0;
        while (newPopulation.size() < this.getIndividuals().size()) {

            if (i % 2 == 0) {
                newPopulation.add(this.clusteringShuffle_IG.generateIndividual(parameters, 0));
            } else {
                newPopulation.add(this.greedyIndividualGenerator.generateIndividual(parameters, 0));
            }

            i++;
        }

        this.individuals = newPopulation;
    }

    /**
     * Finds and returns the two fittest individuals in the population
     * 
     * @param parameters A parameter object with set parameters
     * @return The two fittest individuals
     */
    public List<Individual> getTwoBestIndividuals(ParameterObject parameters) {
        List<Individual> individuals = this.getIndividuals();
        individuals.sort((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)));
        return new ArrayList<>(individuals.subList(0, 2));
    }

    /**
     * Selects and returns two random individuals from the population
     * 
     * @param parameters A parameter object with set parameters
     * @return The two selected random individuals
     */
    public List<Individual> getTwoRandomIndividuals(ParameterObject parameters) {
        Random random = new Random();
        int randomIndex1 = random.nextInt(this.individuals.size());
        int randomIndex2 = random.nextInt(this.individuals.size());
        Individual ind1 = this.individuals.get(randomIndex1);
        Individual ind2 = this.individuals.get(randomIndex2);
        return new ArrayList<>(Arrays.asList(ind1, ind2));
    }

    /**
     * Selects two random individuals from the quarter fittest individuals of the population
     * 
     * @param parameters A parameter object with set parameters
     * @return The two selected individuals
     */
    public List<Individual> getTwoGoodIndividuals(ParameterObject parameters) {
        Random random = new Random();
        List<Individual> individuals = this.getIndividuals();
        individuals.sort((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)));
        List<Individual> bestQuarter = individuals.subList(0, this.getIndividuals().size() / 4);
        int randomIndex1 = random.nextInt(bestQuarter.size());
        int randomIndex2 = random.nextInt(bestQuarter.size());
        return new ArrayList<>(Arrays.asList(bestQuarter.get(randomIndex1), bestQuarter.get(randomIndex2)));
    }

    /**
     * Replaces the two least fit individuals of the population with two immigrants
     * 
     * @param parameters A parameter object with set parameters
     * @param immigrants The individuals to replace the two worst with
     */
    public void replaceTwoWorst(ParameterObject parameters, List<Individual> immigrants) {
        List<Individual> individuals = this.getIndividuals();
        individuals.sort((a, b) -> Double.compare(this.fitnessAnalyzer.getFitness(parameters, a), this.fitnessAnalyzer.getFitness(parameters, b)));
        List<Individual> newPopulation = individuals.subList(0, this.getIndividuals().size() - 2);
        newPopulation.addAll(immigrants);
        Collections.shuffle(newPopulation);
        this.individuals = newPopulation;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < individuals.size(); i++) {
            str.append("Solution " + (i + i) + ":\n");
            str.append(individuals.get(i).toString() + "\n\n");
        }
        str.append("]");
        return str.toString();
    }
}
