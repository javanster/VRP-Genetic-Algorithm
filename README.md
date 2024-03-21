## Using Genetic Algorithms for finding near optimal solutions to a Home-Care Vehicle Routing Problem

IT3708 NTNU - Spring 2024 - Jarl Sterkenburg

The task in the project was to find good routes for nurses to perform home-care services, given certain constraints and with the aim of minimizing total travel time. The problems are defined as problem instances (see [resources](/src/main/resources/)).

#### Requirements for running the code
- [Java JDK](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org)
- [Python](https://www.python.org/downloads/) (for vizualizing the resulting routes)
#### Libraries used:
- [Weka 3](https://www.cs.waikato.ac.nz/ml/weka/index.html)
- [Gson](https://github.com/google/gson)


#### How to run

- One single genetic algorithm: see [Run.java](/src/main/java/com/p2/Run.java)

- Eight genetic algorithms in an island model: see [IslandRun.java](/src/main/java/com/p2/IslandRun.java)

Any new problem instance MUST be placed in the [resources folder](/src/main/resources/) before trying to run it, and the format needs to match that of the other problem instances. Pass the name of the problem instance file as an argument in either the [runGa](/src/main/java/com/p2/Run.java) method, or in the [evovleIslands](/src/main/java/com/p2/IslandRun.java) method.

Parameters for each algorithm can be set by either editing the parameters in the ParameterObject in the [runGa](/src/main/java/com/p2/Run.java) method, or in the [setAndReturnParams](/src/main/java/com/p2/run_utils/ParameterSetter.java) method (it is possible in the latter to set the parameters of each island individually).

### How to view results

After running either the single genetic algorithm or the island model, two files will appear or be updated in the [resources](/src/main/resources/) folder, one for textual run details, and one for showing a graphical representation of the routes. To view the latter, run the Python script in [drawSolution.py](/src/main/resources/drawSolution.py). Change the argument to the "drawMap" function to be the name of the problem instance file for which you want to visually show the found solution for (without the file ending).
