package com.p2.run_utils;

import com.p2.base_classes.GeneticAlgorithm;

public class Island3 extends Thread {

    ParameterObject parameters;

    /**
     * A thread class for running one island in the island model. Uses IslandPopulations.POPULATION_3
     * 
     * @param parameters A parameter object with set parameters
     */
    public Island3(ParameterObject parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public void run() {
        GeneticAlgorithm ga = new GeneticAlgorithm();

        IslandPopulations.POPULATION_3 = ga.evolvePopulation(this.parameters, IslandPopulations.POPULATION_3);
        
    }
}
