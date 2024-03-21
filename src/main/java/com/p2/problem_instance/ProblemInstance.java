package com.p2.problem_instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProblemInstance {
    
    private String instance_name;
    private int nbr_nurses;
    private int capacity_nurse;
    private Depot depot;
    private Map<Integer, Patient> patients;
    private List<List<Double>> travel_times;

    public String getInstance_name() {
        return instance_name;
    }
    public int getNbr_nurses() {
        return nbr_nurses;
    }
    public int getCapacity_nurse() {
        return capacity_nurse;
    }
    public Depot getDepot() {
        return depot;
    }
    public Map<Integer, Patient> getPatients() {
        return patients;
    }     
    public double getTravelTime(int dest1, int dest2) {
        return this.travel_times.get(dest1).get(dest2);
    }
    public List<Integer> getDestinations() {
        return new ArrayList<>(getPatients().keySet()); 
    }

}
