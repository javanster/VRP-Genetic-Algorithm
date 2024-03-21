package com.p2.run_utils;

import com.p2.base_classes.GeneticAlgorithm;

public class Island6 extends Thread {

    ParameterObject parameters;

    /**
     * A thread class for running one island in the island model. Uses IslandPopulations.POPULATION_6
     * 
     * @param parameters A parameter object with set parameters
     */
    public Island6(ParameterObject parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public void run() {
        GeneticAlgorithm ga = new GeneticAlgorithm();

        IslandPopulations.POPULATION_6 = ga.evolvePopulation(this.parameters, IslandPopulations.POPULATION_6);
        
    }
}
