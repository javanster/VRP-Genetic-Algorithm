package com.p2.base_classes;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a single solution for the VRP.
 * See classes under directory individual_generators to see how individuals may
 * be instantiated.
 * 
 */
public class Individual {
    
    private List<List<Integer>> routes;

    public Individual(List<List<Integer>> routes) {
        this.routes = routes;
    }

    public List<List<Integer>> getRoutes() {
        return new ArrayList<>(routes);
    }

    public void setValue(List<List<Integer>> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < routes.size(); i++) {
            str.append(routes.get(i));
            if (i != routes.size() - 1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    

}
