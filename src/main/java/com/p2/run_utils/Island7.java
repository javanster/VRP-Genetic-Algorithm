package com.p2.run_utils;

import com.p2.base_classes.GeneticAlgorithm;

public class Island7 extends Thread {

    ParameterObject parameters;

    /**
     * A thread class for running one island in the island model. Uses IslandPopulations.POPULATION_7
     * 
     * @param parameters A parameter object with set parameters
     */
    public Island7(ParameterObject parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public void run() {
        GeneticAlgorithm ga = new GeneticAlgorithm();

        IslandPopulations.POPULATION_7 = ga.evolvePopulation(this.parameters, IslandPopulations.POPULATION_7);
        
    }
}
