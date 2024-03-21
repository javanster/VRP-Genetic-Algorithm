package com.p2.run_utils;

import com.p2.base_classes.GeneticAlgorithm;

public class Island2 extends Thread {

    ParameterObject parameters;

    /**
     * A thread class for running one island in the island model. Uses IslandPopulations.POPULATION_2
     * 
     * @param parameters A parameter object with set parameters
     */
    public Island2(ParameterObject parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public void run() {
        GeneticAlgorithm ga = new GeneticAlgorithm();

        IslandPopulations.POPULATION_2 = ga.evolvePopulation(this.parameters, IslandPopulations.POPULATION_2);
        
    }
}
