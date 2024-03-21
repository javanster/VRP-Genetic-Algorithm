package com.p2.readers_writers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.p2.problem_instance.ProblemInstance;

/**
 * A class for reading a problem instance from a json file, and instantiating it as an object
 */
public class ProblemInstanceReader {
    
    /**
     * Reads a problem instance from a json file with the specified name, and returns an
     * instantiated ProblemInstance object based on the read data.
     * 
     * @param instanceName The name of the problem instance to read
     * @return The problem instance object
     */
    public ProblemInstance readProblemInstance(String instanceName) {

        Gson gson = new Gson();

        try {
            Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(instanceName), StandardCharsets.UTF_8);
            ProblemInstance problemInstance = gson.fromJson(reader, ProblemInstance.class);
            reader.close();
            
            return problemInstance;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
