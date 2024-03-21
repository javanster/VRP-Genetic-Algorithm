package com.p2.interfaces;

import com.p2.base_classes.Population;
import com.p2.run_utils.ParameterObject;

/**
 * Interface for parent selectors
 */
public interface ParentSelector {

    /**
     * Selects parents from a population, used for offspring generation. See directory parent_selectors for
     * implementations
     * 
     * @param parameters A parameter object with set parameters
     * @param population The population to select from
     * @param parentsCount How many parents to select
     * @return A population object containing the selected parents
     */
    public Population selectParents(ParameterObject parameters, Population population, int parentsCount);
}
